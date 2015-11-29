import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JamesParser {
	
	private static int count = 0;
	private static final String INPUT_FILE = "Non-StateWarData_v4.0.csv";
	private static final String OUTPUT_FILE = "non-state-table.csv";
	private static final int NUM_ELEMENTS = 13;

	private static void parseHelper(File inFile, File outFile) throws IOException {
		
		Scanner sc = new Scanner(inFile);
		FileWriter writer = new FileWriter(outFile);
		ArrayList<ArrayList<ArrayList<String>>> dataList = new ArrayList<>();
		ArrayList<ArrayList<String>> ultList = new ArrayList<>();
		
		while (sc.hasNextLine()) {
			ArrayList<ArrayList<String>> warTuple = new ArrayList<>();
			String line = sc.nextLine().trim();
			int lineLen = line.length();
			int positionStart = 0;
			int positionEnd = 0;
			int index = 0;
			String item = "";
			
			while (positionStart < lineLen && index != 13) {
				ArrayList<String> tupleColumn = new ArrayList<>();
				positionEnd = line.indexOf(',', positionStart);
				if (positionEnd == -1) {
					item = line.substring(positionStart);
				} else {
					item = line.substring(positionStart, positionEnd);
				}
				
				int positionStart1 = 0;
				int positionEnd1 = 0;
				String sbstr;
				while (item.substring(positionStart1).contains("|")) {
					positionEnd1 = item.indexOf('|', positionStart1);
					sbstr = item.substring(positionStart1, positionEnd1).trim();
					tupleColumn.
					add(sbstr);
					positionStart1 = positionEnd1 + 1;
				}
				sbstr = item.substring(positionStart1);
				if (positionStart1 == positionEnd1 && positionStart == lineLen - 1) {
					System.out.println("here!!!!!!");
					sbstr = "";
				} else {
					sbstr = sbstr.trim();
				}
				tupleColumn.add(sbstr);
				
				positionStart = positionEnd + 1;
				warTuple.add(tupleColumn);
				index++;
			}
			dataList.add(warTuple);
		}
		
		for (int i = 0; i < dataList.size(); i++) {
			parse(ultList, dataList.get(i));
		}
	
		for (int i = 0; i < ultList.size(); i++) {
			ArrayList<String> oneTuple = ultList.get(i);
			for (int j = 0; j < oneTuple.size() - 1; j++) {
				writer.write(oneTuple.get(j) + ",");
			}
			writer.write(oneTuple.get(oneTuple.size() - 1) + "\n");
		}

		sc.close();
		writer.close();
		
	}
	
	private static void parse(ArrayList<ArrayList<String>> ultList, ArrayList<ArrayList<String>> warTuple) {
		ArrayList<String> resultList = new ArrayList<>();
		parse(ultList, resultList, warTuple, 0);
	}
	
	private static void parse(ArrayList<ArrayList<String>> ultList, ArrayList<String> resultList, 
								ArrayList<ArrayList<String>> warTuple, int index) {
		if (index == NUM_ELEMENTS) {
			ultList.add(resultList);
		} else {
			ArrayList<String> newResult = new ArrayList<>();
			newResult.addAll(resultList);
			for (int i = 0; i < warTuple.get(index).size(); i++) {
				if (newResult.size() == index + 1) {
					newResult.remove(index);
				}
				newResult.add(index, warTuple.get(index).get(i));
				parse(ultList, newResult, warTuple, index + 1);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		File inFile = new File(INPUT_FILE);
		File outFile = new File(OUTPUT_FILE);
		parseHelper(inFile, outFile);
		System.out.println("Parsing successful!");
	}
	
}
