import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class HistoryParser1 {
	
	public static void main(String[] args) throws IOException {
		File infile = new File("Extra-StateWarData_v4.0.csv");
		Scanner sc = new Scanner(infile);
		File outFile = new File("non_output.csv");
		
		FileWriter writer = new FileWriter(outFile);
		
//		List<WarData> datas = new ArrayList<>();
		
		String[] data = new String[12];
		
		int positionStart = 0;
		int positionEnd = 0;
		int index = 0;
/*		String WarNum = "";
		String WarName = "";
		String WarType = "";
		String ccode1 = "";
		String Side1 = "";
		String ccode2 = "";
		String Side2 = "";
		String StartYear = "";
		String EndYear = "";
		String Initiator = "";
		String Outcome = "";
		String WhereFought = "";
		String BatDeath = "";
		*/
		
		String line;
		String item;
		
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();
			int lineLen = line.length();
			ArrayList<String> sideTwos = new ArrayList<>();
			
			while (positionStart < lineLen && index != 11) {
				if (line.charAt(positionStart) == '"') {
					positionStart++;
					positionEnd = line.indexOf('"', positionStart);
					if (positionEnd == -1) {
						item = line.substring(positionStart);
					} else {
						item = line.substring(positionStart, positionEnd);
						positionEnd++;
					}
				} else {
					positionEnd = line.indexOf(',', positionStart);
					if (positionEnd == -1) {
						item = line.substring(positionStart);
					} else {
						item = line.substring(positionStart, positionEnd);
					}
				}
				data[index++] = item;
				positionStart = positionEnd + 1;
			}
			index = 0;
			positionEnd = 0;
			positionStart = 0;
			
//			for (int i = 0; i < sideTwos.size(); i++) {
//				WarData d = new WarData(data, sideTwos.get(i));
//				writer.write(d.toString());
//			}
			

			
		}
		sc.close();
		writer.close();
	}
	
}
