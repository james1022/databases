package jchoi100_jlee381_600_315;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class WorkedOnTableMaker {

	private static final String INPUT_FILE = "artist-parsed.csv";
	private static final String OUTPUT_FILE = "out-workedon.csv";
	private static final String SQL_FILE = "worked_on.sql";
	private static final String ERROR_FILE = "error-workedon.csv";
	private static final int NUM_ELEMENTS = 3;
	private static HashSet<String> workedOn = new HashSet<>();
	private static HashSet<String> errorWorkedOn = new HashSet<>();
	
	private static void parse(File inFile, File outFile, File errorFile) throws IOException {
		FileWriter writer = new FileWriter(outFile);
		FileWriter errorWriter = new FileWriter(errorFile);
		FileWriter sqlWriter = new FileWriter(SQL_FILE);
		ArrayList<WorkedOnTuple> tupleList = new ArrayList<>();
		tupleSeparator(tupleList, inFile, outFile);

		writer.write("ArtistName,ArtistBornYear,ObjectId\n");
		errorWriter.write("ArtistName,ArtistBornYear,ObjectId\n");
		sqlWriter.write("DROP TABLE WorkedOn;\n");
		sqlWriter.write("CREATE TABLE IF NOT EXISTS WorkedOn(\n");
		sqlWriter.write("    ArtistName       VARCHAR(74) NOT NULL\n");
		sqlWriter.write("   ,ArtistBornYear   INTEGER NOT NULL\n");
		sqlWriter.write("   ,ObjectId         INTEGER NOT NULL\n");
		sqlWriter.write("   ,PRIMARY KEY(ArtistName,ArtistBornYear,ObjectId)\n");
		sqlWriter.write(");\n");

		//Write the result in a csv file
		for (int i = 0; i < tupleList.size(); i++) {
			WorkedOnTuple oneTuple = tupleList.get(i);
			if (!oneTuple.artistName.equals("") && !oneTuple.artistBornYear.equals("")) {
				String key = oneTuple.artistName + oneTuple.artistBornYear + oneTuple.objectId;
				if (!workedOn.contains(key)) {
					workedOn.add(key);
					writer.write(oneTuple.toString() + "\n");
					sqlWriter.write(oneTuple.toSqlStatement() + "\n");
				}
			} else {
				String key = oneTuple.artistName + oneTuple.artistBornYear + oneTuple.objectId;
				if (!errorWorkedOn.contains(key)) {
					errorWorkedOn.add(key);
					errorWriter.write(oneTuple.toString() + "\n");
				}
			}
		}
		writer.close();
		sqlWriter.close();
		errorWriter.close();
	}
	
	private static void tupleSeparator(ArrayList<WorkedOnTuple> tupleList, File inFile, File outFile) 
																			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
		String line;
		while ((line = br.readLine()) != null) {
			//Get one tuple. A tuple may possibly have several artists in it.
			int lineLen = line.length();
			int positionStart = 0;
			int positionEnd = 0;
			int index = 0;
			String item = "";
			WorkedOnTuple tupleToAdd = new WorkedOnTuple();
			
			while (positionStart < lineLen && index != NUM_ELEMENTS) {
				positionEnd = line.indexOf(',', positionStart);
				if (positionEnd == -1) {
					item = line.substring(positionStart);
				} else {
					item = line.substring(positionStart, positionEnd);
				}
				
				int positionStart1 = 0;
				int positionEnd1 = 0;
				
				if (index == 0) { //column 0: artist name
					tupleToAdd.artistName = item.trim();
				} else if (index == 1) { //column 1: artist bio. ex) American | born Germany 1930-1999
					positionEnd1 = item.indexOf('|', positionStart1);
					if (positionEnd1 != -1) {
						String bornPart = item.substring(positionEnd1); // born Germany 1945
						
						positionStart1 = positionEnd1;
						positionEnd1 = positionEnd;
						
						Scanner s = new Scanner(bornPart);
						while (s.hasNext()) {
							String nextWord = s.next();
							if (nextWord.contains("–")) { //1930-1999
								String bornYear = nextWord.substring(0, 4);
								tupleToAdd.artistBornYear = bornYear;
							} else if (isNumeric(nextWord)) { //no dead year
								String bornYear = nextWord.trim();
								tupleToAdd.artistBornYear = bornYear;
							} //else nextWord == "born"
						}
						s.close();
					}
				} else { //objectId
					tupleToAdd.objectId = Integer.parseInt(item);
				}
				positionStart = positionEnd + 1;
				index++;
			}
			tupleList.add(tupleToAdd);
		}
		br.close();
	}
	
	private static boolean isNumeric(String str) {  
	  try {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);
	  } catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static void main(String args[]) throws IOException {
		File inFile = new File(INPUT_FILE);
		File outFile = new File(OUTPUT_FILE);
		File errorFile = new File(ERROR_FILE);
		parse(inFile, outFile, errorFile);
		System.out.println("Parsing successful!");
	}
	
}
