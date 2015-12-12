package jchoi100_jlee381_600_315;

public class Artist {

	public String name;
	public String nationality;
	public String bornCountry;
	public String bornYear;
	public String deadYear;
	
	public Artist() {
		this.name = null;
		this.nationality = null;
		this.bornCountry = null;
		this.bornYear = null;
		this.deadYear = null;
	}
	
	@Override
	public String toString() {
		return this.name + "," + this.nationality + "," + this.bornCountry + ","
				+ this.bornYear + "," + this.deadYear;
	}
	
	@Override
	public boolean equals(Object o) {
		Artist a = (Artist) o;
		return this.name.equals(a.name) && this.bornYear.equals(a.bornYear);
	}
	
}
