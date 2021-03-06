package jchoi100_jlee381_600_315;

public class Film {

	public int objectId;
	public String title;
	public String year;
	public String medium;
	public String minutes;
	public String creditLine;
	public String classification;
	public String department;
	public String yearAcquired;
	public String curatorApproved;

	public Film(){}

	@Override
	public String toString() {
		return this.title + "," + this.year + "," + this.medium + "," + this.minutes + ","
			    + this.creditLine + "," + this.classification
				+ "," + this.department + "," + this.yearAcquired + "," + this.curatorApproved
				+ "," + this.objectId;
	}
	
	public String toSqlStatement() {
		String tit = this.title;
		int marker = 0;

		while (this.title.indexOf('\'', marker) != -1) {
			int index = this.title.indexOf('\'', marker);
			String first = tit.substring(0, index);
			String last = tit.substring(index);
			tit = (first + '\'' + last);
			marker = index + 1;
		}
		
		String med = this.medium;
		int marker2 = 0;

		while (this.medium.indexOf('\'', marker2) != -1) {
			int index = this.medium.indexOf('\'', marker2);
			String first = med.substring(0, index);
			String last = med.substring(index);
			med = (first + '\'' + last);
			marker2 = index + 1;
		}
		
		String cred = this.creditLine;
		int marker3 = 0;

		while (this.creditLine.indexOf('\'', marker3) != -1) {
			int index = this.creditLine.indexOf('\'', marker3);
			String first = cred.substring(0, index);
			String last = cred.substring(index);
			cred = (first + '\'' + last);
			marker3 = index + 1;
		}
		
		return "INSERT INTO Film(Title,Year,Medium,Minutes,CreditLine,Classification,Department,YearAcquired,CuratorApproved,ObjectID) VALUES ('" 
				+ tit + "'," + this.year + ",'" + med + "'," + this.minutes + ",'" + cred + "','" + this.classification
				+ "','" + this.department + "'," + this.yearAcquired + ",'" + this.curatorApproved
				+ "'," + this.objectId + ");";
	}
	
	public boolean containsError() {
		if (this.objectId == -1) {
			return true;
		}
		if (this.title.equals("error") || this.year.equals("error") || this.medium.equals("error")
			|| this.yearAcquired.equals("error")) {
			return true;
		}
		if (!isNumeric(this.minutes)) {
			return true;
		}
		return false;
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
}
