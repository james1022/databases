package jchoi100_jlee381_600_315;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ArtistTableMaker {

	private static final String INPUT_FILE = "artist-table-parsed.csv";
	private static final String OUTPUT_FILE = "artist-table-final-whole.csv";
	private static final String ERROR_FILE = "artist-table-final-error-whole.csv";
	private static final int NUM_ELEMENTS = 5;
	private static HashSet<String> artists = new HashSet<>();
	private static HashSet<String> errorArtists = new HashSet<>();
	
	private static void parse(File inFile, File outFile, File errorFile) throws IOException {
		FileWriter writer = new FileWriter(outFile);
		FileWriter errorWriter = new FileWriter(errorFile);
		ArrayList<Artist> tupleList = new ArrayList<>();
		tupleSeparator(tupleList, inFile, outFile);

		writer.write("ArtistName,ArtistNationality,ArtistBornCountry,ArtistBornYear,ArtistDeadYear\n");
		errorWriter.write("ArtistName,ArtistNationality,ArtistBornCountry,ArtistBornYear,ArtistDeadYear\n");

		//Write the result in a csv file
		for (int i = 0; i < tupleList.size(); i++) {
			Artist oneArtist = tupleList.get(i);
			if (!oneArtist.name.equals("") && !oneArtist.bornYear.equals("")) {
				String key = oneArtist.name + oneArtist.bornYear;
				if (!artists.contains(key)) {
					artists.add(key);
					writer.write(oneArtist.toString() + "\n");
				}
			} else {
				String key = oneArtist.name + oneArtist.bornYear;
				if (!errorArtists.contains(key)) {
					errorArtists.add(key);
					errorWriter.write(oneArtist.toString() + "\n");
				}
			}
		}
		writer.close();
		errorWriter.close();
	}
	
	private static void tupleSeparator(ArrayList<Artist> tupleList, File inFile, File outFile) 
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
			Artist artistToAdd = new Artist();
			
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
					artistToAdd.name = item.trim();
				} else if (index == 1) { //column 1: artist bio. ex) American | born Germany 1930-1999
					positionEnd1 = item.indexOf('|', positionStart1);
					if (positionEnd1 == -1) {
						String nationality = item.substring(positionStart1).trim();
						artistToAdd.nationality = nationality;
						artistToAdd.bornYear = "";
						artistToAdd.deadYear = "";
						artistToAdd.bornCountry = "";
					} else {
						String nationality = item.substring(positionStart1, positionEnd1); //American
						String bornPart = item.substring(positionEnd1); // born Germany 1945
						Scanner s = new Scanner(nationality);
						artistToAdd.nationality = s.next().trim(); //only adds the first nationality
						s.close();
						
						positionStart1 = positionEnd1;
						positionEnd1 = positionEnd;
						
						s = new Scanner(bornPart);
						while (s.hasNext()) {
							String nextWord = s.next();
							if (nextWord.contains("–")) { //1930-1999
								String bornYear = nextWord.substring(0, 4);
								String deadYear = nextWord.substring(5);
								artistToAdd.bornYear = bornYear;
								artistToAdd.deadYear = deadYear;
							} else if (isNumeric(nextWord)) { //no dead year
								String bornYear = nextWord.trim();
								artistToAdd.bornYear = bornYear;
								artistToAdd.deadYear = "";
							} else if (!nextWord.equals("born")) {
								if (!nextWord.trim().equals("|")) {
									String bCountry = nextWord.trim();
									if (bCountry.contains(".")) {
										bCountry = bCountry.substring(0, bCountry.length() - 1);
									}
									artistToAdd.bornCountry = bCountry;
								} else {
									artistToAdd.bornCountry = "";
								}
							} //else nextWord == "born"
						}
						if (artistToAdd.bornCountry == null) {
							artistToAdd.bornCountry = "";
						}
						if (artistToAdd.bornYear == null) {
							artistToAdd.bornYear = "";
						}
						if (artistToAdd.deadYear == null) {
							artistToAdd.deadYear = "";
						}
						s.close();
					}

				}
				positionStart = positionEnd + 1;
				index++;
			}
			tupleList.add(artistToAdd);
		}
		br.close();
	}
	
	private static boolean isNumeric(String str) {  
	  try {  
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
