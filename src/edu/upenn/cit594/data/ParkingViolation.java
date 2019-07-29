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
	private String state;
	
	/**
	 * Initializes the zip code and fine instance variables
	 * @param zip Zip Code of parking violation
	 * @param fine Price of the parking violation fine
	 */
	public ParkingViolation (int zip, double fine, String state){
		this.zip = zip;
		this.fine = fine;
		this.state = state;
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
	
	/**
	 * Gets the state.
	 * @return String value of state
	 */
	public String getState() {
		return state;
	}
	
}
