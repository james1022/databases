import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenericParser {

	public static final String INPUT_FILE = "Extra-StateWarData_v4.0.csv";
	public static final String OUTPUT_FILE = "extra-state-table.csv";
	public static final int NUM_ELEMENTS = 13;
	
	public static ArrayList<WarData> dataList;
	
	public static void parse(File inFile, File outFile) throws IOException {
		Scanner sc = new Scanner(inFile);
		FileWriter writer = new FileWriter(outFile);

		dataList = new ArrayList<>();

		String[] data = new String[13];
		int positionStart = 0;
		int positionEnd = 0;
		int index = 0;
		
		String line;
		String item;
		
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();
			int lineLen = line.length();
			
			while (positionStart < lineLen && index != 13) {

				positionEnd = line.indexOf(',', positionStart);
				if (positionEnd == -1) {
					item = line.substring(positionStart);
				} else {
					item = line.substring(positionStart, positionEnd);
				}
				data[index++] = item;
				positionStart = positionEnd + 1;
			}
			index = 0;
			positionEnd = 0;
			positionStart = 0;
			WarData d = new WarData(data);
			dataList.add(d);
		}

			String tmpString = "abcd | ef";
			positionStart = 0;
			positionEnd = 0;
			ArrayList<String> tmp = new ArrayList<String>();
			String sbstr;
			while (tmpString.substring(positionStart).contains("|")) {
				positionEnd = tmpString.indexOf('|', positionStart);
				sbstr = tmpString.substring(positionStart, positionEnd).trim();
				tmp.add(sbstr);
				positionStart = positionEnd + 1;
			}
			sbstr = tmpString.substring(positionStart).trim();
			tmp.add(sbstr);
			positionStart = 0;
			positionEnd = 0;
			
			
		
		for (WarData d : dataList) {
			writer.write(d.toString());
		}
		
		sc.close();
		writer.close();
		
	}
	
	public static void main(String args[]) throws IOException {
		File inFile = new File(INPUT_FILE);
		File outFile = new File(OUTPUT_FILE);
		parse(inFile, outFile);
		

	}
	
//	public static void parse() {
//		int positionStart = 0;
//		int positionEnd = 0;
//		for (int i = 0; i < NUM_ELEMENTS; i++) {
//			ArrayList<WarData> newDataList = new ArrayList<WarData>();
//			for (WarData d : dataList) {
//				String[] newData = new String[NUM_ELEMENTS];
//				while (d.data[i].substring(positionStart).contains("|")) {
//					positionEnd = d.data[i].indexOf('|', positionStart);
//					for (int j = 0; j < NUM_ELEMENTS; j++) {
//						if (j == i) {
//							newData[j] = d.data[j].substring(positionStart, positionEnd).trim();
//						} else {
//							newData[j] = d.data[j].trim();
//						}
//					}
//					WarData newD = new WarData(newData);
//					newDataList.add(newD);
//					positionStart = positionEnd + 1;
//					// get element, one by one
//					// add everything else the same, except for separated item at position i
//				}
//				for (int k = 0; k < NUM_ELEMENTS; k++) {
//					if (k == i) {
//						newData[k] = d.data[k].substring(positionStart).trim();
//					} else {
//						newData[k] = d.data[k].trim();
//					}
//				}
//				WarData newD = new WarData(newData);
//				newDataList.add(newD);
//				positionStart = 0;
//				positionEnd = 0;
//			}
//			dataList = new ArrayList<WarData>();
//			for (WarData nd : newDataList) {
//				dataList.add(nd);
//			}
//		}
//	}
	
	
}