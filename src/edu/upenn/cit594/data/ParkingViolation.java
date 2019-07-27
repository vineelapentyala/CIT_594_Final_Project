package edu.upenn.cit594.data;

/**
 * 
 * ParkingViolation class stores each parking violation parsed from
 * OpenDataPhilly with the zip code it occurred in and the fine charged.
 *
 */
public class ParkingViolation {
	private int zip;
	private double fine;
	
	/**
	 * Initializes the zip code and fine instance variables
	 * @param zip Zip Code of parking violation
	 * @param fine Price of the parking violation fine
	 */
	public ParkingViolation (int zip, double fine){
		this.zip = zip;
		this.fine = fine;
	}
	
	/**
	 * Gets the fine.
	 * @return Double value of fine
	 */
	public double getFine() {
		return fine;
	}
	
	/**
	 * Gets the zip.
	 * @return Int value of zip
	 */
	public int getZip() {
		return zip;
	}
	
}
