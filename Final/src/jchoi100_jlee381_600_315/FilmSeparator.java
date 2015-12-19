package jchoi100_jlee381_600_315;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Takes a raw artwork file and sorts by film and non-film.
 * @author Joon Hyuck Choi, Joo Chang Lee
 *
 */
public class FilmSeparator {
	
	private static final String INPUT_FILE = "Artwork 100001-end.csv";
	private static final String ARTWORK_FILE = "Art 100001-.csv";
	private static final String FILM_FILE = "films.csv";

	public static void parse(File inFile, File artFile, File filmFile) throws IOException {
		
		FileWriter artWriter = new FileWriter(artFile);
		BufferedWriter filmWriter = new BufferedWriter(new FileWriter(filmFile, true));
		
		BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
		
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains("min. ,") || line.contains("min.,")) {
				filmWriter.write(line + "\n");
			} else {
				artWriter.write(line + "\n");
			}
		}
		br.close();
		artWriter.close();
		filmWriter.close();
	}

	
	public static void main(String args[]) throws IOException {
		File inFile = new File(INPUT_FILE);
		File artFile = new File(ARTWORK_FILE);
		File filmFile = new File(FILM_FILE);
		parse(inFile, artFile, filmFile);
		System.out.println("Parsing successful!");
	}
	
}
