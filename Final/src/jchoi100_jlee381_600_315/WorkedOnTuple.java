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
	
	
	@Override
	public boolean equals(Object o) {
		WorkedOnTuple w = (WorkedOnTuple) o;
		return this.artistName.equals(w.artistName) 
				&& this.artistBornYear.equals(w.artistBornYear)
				&& this.objectId == w.objectId;
	}
}
