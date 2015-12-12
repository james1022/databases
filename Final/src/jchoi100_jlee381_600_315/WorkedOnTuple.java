package jchoi100_jlee381_600_315;

public class WorkedOnTuple {

	public String artistName;
	public String artistBornYear;
	public int objectId;
	
	public WorkedOnTuple() {
		this.artistName = "";
		this.artistBornYear = "";
		this.objectId = -1;
	}
	
	@Override
	public String toString() {
		return this.artistName + "," + this.artistBornYear + "," + this.objectId;
	}
	
	public String sqlStatement() {
		String name = this.artistName;
		int marker = 0;
		
		while (this.artistName.indexOf('\'', marker) != -1) {
			int index = this.artistName.indexOf('\'', marker);
			String first = name.substring(0, index);
			String last = name.substring(index);
			name = (first + '\'' + last);
			marker = index + 1;
		}
		
		return "INSERT INTO WorkedOn(ArtistName,ArtistBornYear,ObjectId) VALUES ('" 
				+ name + "'," + this.artistBornYear + "," + this.objectId + ");";
	}
	
	
	@Override
	public boolean equals(Object o) {
		WorkedOnTuple w = (WorkedOnTuple) o;
		return this.artistName.equals(w.artistName) 
				&& this.artistBornYear.equals(w.artistBornYear)
				&& this.objectId == w.objectId;
	}
}
