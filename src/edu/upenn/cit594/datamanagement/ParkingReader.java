package edu.upenn.cit594.datamanagement;

import java.util.*;

import edu.upenn.cit594.data.ParkingViolation;

/**
 * ParkingReader class is part of the DataManagement tier. It performs the task
 * of reading the data from input files related to parking violations then
 * stores this data into appropriate structures for the processor tier to
 * access.
 *
 */

public abstract class ParkingReader {

	private List<ParkingViolation> allParkingViolations;

	/**
	 * ParkingReader constructor initializes the allParkingViolations list and calls
	 * the parse method to store the data read into the list.
	 * 
	 * @param inputFileName String name of file to be read
	 * @throws Exception
	 */
	public ParkingReader(String inputFileName) throws Exception {
		allParkingViolations = new ArrayList<ParkingViolation>();
		parse(inputFileName);
	}

	/**
	 * parse will parse the input file and store its data into allParkingViolations.
	 * 
	 * @param inputFileName String name of file to be read
	 * @throws Exception
	 */
	public abstract void parse(String inputFileName) throws Exception;

	/**
	 * storeParkingViolations takes in zip and fines as arguments, creates a
	 * ParkingViolation Object from them and stores them to allParkingViolations
	 * List
	 * 
	 * @param zip   zipcode where a parking violation occurred
	 * @param fine  amount of fine issued for the parking violation
	 * @param state state where the parking violation occurred
	 */
	public void storeParkingViolations(int zip, double fine, String state) {
		ParkingViolation thisViolation = new ParkingViolation(zip, fine, state);
		allParkingViolations.add(thisViolation);
	}

	/**
	 * getAllParkingViolations is a getter method for 'allParkingViolations' List
	 * 
	 * @return allParkingViolations A list of all parking violations in PA with zip
	 *         codes
	 */
	public List<ParkingViolation> getAllParkingViolations() {
		return allParkingViolations;
	}

}
