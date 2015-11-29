public class WarData {
	String[] data;
	
	WarData(String[] array) {
		data = new String[13];
		this.data = array;
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < 12; i++) {
			output += data[i] + ",";
		}
		output += data[12];
		output += "\n";
		return output;
	}
}