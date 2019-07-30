package edu.upenn.cit594.datamanagement;

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
     * storeParkingViolations takes in zip and fines as arguments, creates a
     * ParkingViolation Object from them and stores them to allParkingViolations
     * List
     * 
     * @param zip  zipcode where a parking violation occurred
     * @param fine amount of fine issued for the parking violation
     */
    public void storeParkingViolations(int zip, double fine, String state) {
	ParkingViolation thisViolation = new ParkingViolation(zip, fine, state);
	allParkingViolations.add(thisViolation);
    }

    public abstract void parse(String inputFileName) throws Exception;

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
