package jchoi100_jlee381_600_315;

public class Artwork {

	public int objectId;
	public String title;
	public String year;
	public String medium;
	public String width;
	public String height;
	public String depth;
	public String creditLine;
	public String classification;
	public String department;
	public String yearAcquired;
	public String curatorApproved;

	public Artwork(){}
	
	public Artwork(int objectId, String title, String year, String medium, String width, 
					String height, String depth, String creditLine, String classification,
					String department, String yearAcquired, String curatorApproved) {
		this.objectId = objectId;
		this.title = title;
		this.year = year;
		this.medium = medium;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.creditLine = creditLine;
		this.classification = classification;
		this.department = department;
		this.yearAcquired = yearAcquired;
		this.curatorApproved = curatorApproved;
	}
	
	@Override
	public String toString() {
		return this.title + "," + this.year + "," + this.medium + "," + this.width + ","
				+ this.height + "," + this.depth + "," + this.creditLine + "," + this.classification
				+ "," + this.department + "," + this.yearAcquired + "," + this.curatorApproved
				+ "," + this.objectId;
	}
	
	public String sqlStatement() {
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
		
		return "INSERT INTO Artwork(Title,Year,Medium,Width,Height,Depth,CreditLine,Classification,Department,YearAcquired,CuratorApproved,ObjectID) VALUES ('" 
				+ tit + "'," + this.year + ",'" + med + "'," + this.width + ","
				+ this.height + "," + this.depth + ",'" + cred + "','" + this.classification
				+ "','" + this.department + "'," + this.yearAcquired + ",'" + this.curatorApproved
				+ "'," + this.objectId + ");";
	}
	
	public boolean containsError() {
		if (this.objectId == -1) {
			return true;
		}
		if (this.title.equals("error") || this.year.equals("error") || this.medium.equals("error")) {
			return true;
		}
		return false;
	}
	
}
