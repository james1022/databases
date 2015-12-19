package jchoi100_jlee381_600_315;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class FilmParser {

	private static final String INPUT_FILE = "films.csv";
	private static final String OUTPUT_FILE = "out-film.csv";
	private static final String ERROR_FILE = "error-film.csv";
	private static final String SQL_FILE = "films.sql";
	private static final int NUM_ELEMENTS = 10;
	private static HashSet<String> artwork = new HashSet<>();
	private static HashSet<String> errorArtwork = new HashSet<>();
	
	private static void parse(File inFile, File outFile, File errorFile) throws IOException {
		FileWriter writer = new FileWriter(outFile);
		FileWriter errorWriter = new FileWriter(errorFile);
		FileWriter sqlWriter = new FileWriter(SQL_FILE);
		ArrayList<Film> tupleList = new ArrayList<>();
		tupleSeparator(tupleList, inFile, outFile);
		
		sqlWriter.write("CREATE TABLE IF NOT EXISTS Film(\n");
		sqlWriter.write("    Title       VARCHAR(100)\n");
		sqlWriter.write("   ,Year   INTEGER NOT NULL\n");
		sqlWriter.write("   ,Medium         VARCHAR(80)\n");
		sqlWriter.write("   ,Minutes         DECIMAL(10,2)\n");
		sqlWriter.write("   ,CreditLine         VARCHAR(70)\n");
		sqlWriter.write("   ,Classification         VARCHAR(30)\n");
		sqlWriter.write("   ,Department         VARCHAR(30)\n");
		sqlWriter.write("   ,YearAcquired         INTEGER\n");
		sqlWriter.write("   ,CuratorApproved         VARCHAR(2)\n");
		sqlWriter.write("   ,ObjectId         INTEGER NOT NULL\n");
		sqlWriter.write("   ,PRIMARY KEY(ObjectId)\n");
		sqlWriter.write(");\n");

		writer.write("Title,Year,Medium,Minutes,CreditLine,Classification,Department,YearAcquired,CuratorApproved,ObjectID\n");
		errorWriter.write("Title,Year,Medium,Minutes,CreditLine,Classification,Department,YearAcquired,CuratorApproved,ObjectID\n");

		//Write the result in a csv file
		for (int i = 0; i < tupleList.size(); i++) {
			Film oneFilm = tupleList.get(i);
			if (!oneFilm.containsError() && isNumeric(oneFilm.year)) {
				String key = oneFilm.objectId + "";
				if (!artwork.contains(key)) {
					artwork.add(key);
					writer.write(oneFilm.toString() + "\n");
					sqlWriter.write(oneFilm.sqlStatement() + "\n");
				}
			} else {
				String key = oneFilm.objectId + "";
				if (!errorArtwork.contains(key)) {
					errorArtwork.add(key);
					errorWriter.write(oneFilm.toString() + "\n");
				}
			}
		}
		writer.close();
		errorWriter.close();
		sqlWriter.close();
	}
	
	private static void tupleSeparator(ArrayList<Film> tupleList, File inFile, File outFile) 
																			throws IOException, StringIndexOutOfBoundsException {

		BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
		String line;
		while ((line = br.readLine()) != null) {
			//Get one tuple. A tuple may possibly have several artists in it.
			int lineLen = line.length();
			int positionStart = 0;
			int positionEnd = 0;
			int index = 0;
			String item = "";
			Film filmToAdd = new Film();
			
			while (positionStart < lineLen && index != NUM_ELEMENTS) {
				positionEnd = line.indexOf(',', positionStart);
				if (positionEnd == -1) {
					item = line.substring(positionStart);
				} else {
					item = line.substring(positionStart, positionEnd);
				}
				
				switch(index) {
				case 0: //title
					filmToAdd.title = item.trim();
					break;
					
				case 1: //year
					System.out.println(item);

					if (item.contains("c. ")) {
						item = item.substring(3, 7);
					} else if (item.contains("c.")) {
						item = item.substring(2, 6);
					} else if (item.contains("Unknown") || item.length() == 0
							|| item.contains("n.d") || item.contains("n/a")) {
						item = "-1";
					} else if (item.contains("after")) {
						item = item.substring(6, 10) + "";
					} else if (item.contains("early")) {
						item = item.substring(6, 10) + "";
					} else if (item.contains("before")) {
						item = item.substring(7, 11) + "";
					} else {
						item = item.substring(0, 4);
					}
					filmToAdd.year = item.trim();

					break;
					
				case 2: //medium
					filmToAdd.medium = item.trim();
					break;
					
				case 3: //dimensions
					
					if (item.contains(":")) {
						filmToAdd.minutes = item.substring(0, item.indexOf(":"));
					} else {
						try {
							Integer.parseInt(item);
							filmToAdd.minutes = item;
						} catch (Exception e) {
							filmToAdd.minutes = "error";
						}
					}
					break;
					
				case 4: //creditLine
					filmToAdd.creditLine = item.trim();
					break;
					
				case 5: //classification
					filmToAdd.classification = item.trim();
					break;
					
				case 6: //department
					filmToAdd.department = item.trim();
					break;
					
				case 7: //yearAcquired
					item = item.trim();
					try {
						filmToAdd.yearAcquired = item.substring(item.length() - 4);
					} catch(NumberFormatException e) {
						filmToAdd.yearAcquired = "error";
					} catch(StringIndexOutOfBoundsException se) {
						filmToAdd.yearAcquired = "error";
					}
					break;
					
				case 8: //curatorApproved
					filmToAdd.curatorApproved = item.trim();
					break;
					
				case 9: //objectId
					try {
						filmToAdd.objectId = Integer.parseInt(item);
					} catch(NumberFormatException e) {
						filmToAdd.objectId = -1;
					} catch(StringIndexOutOfBoundsException se) {
						filmToAdd.objectId = -1;
					}
					break;
					default:
						break;
				}
				
				positionStart = positionEnd + 1;
				index++;
			}
			tupleList.add(filmToAdd);
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
		System.out.println("Program started...");
		File inFile = new File(INPUT_FILE);
		File outFile = new File(OUTPUT_FILE);
		File errorFile = new File(ERROR_FILE);
		parse(inFile, outFile, errorFile);
		System.out.println("Parsing successful!");
	}
	
}
