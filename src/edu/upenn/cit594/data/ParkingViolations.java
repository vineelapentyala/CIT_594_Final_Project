package edu.upenn.cit594.data;

public class ParkingViolations {
	private int zip;
	private double fine;
	
	ParkingViolations (int zip, double fine){
		this.zip = zip;
		this.fine = fine;
	}
	
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public int getZip() {
		return zip;
	}
	
}
