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

public class ParkingReader {

    private List<ParkingViolation> allParkingViolations;

    public ParkingReader(String inputFileName, String fileFormat) {
	allParkingViolations = new ArrayList<ParkingViolation>();

	if (fileFormat.equals("csv")) {
	    parseCSV(inputFileName);
	} else if (fileFormat.equals("json")) {
	    parseJSON(inputFileName);
	}
    }

    /**
     * storeParkingViolations takes in zip and fines as arguments, creates a
     * ParkingViolation Object from them and stores them to allParkingViolations
     * List
     * 
     * @param zip  zipcode where a parking violation occurred
     * @param fine amount of fine issued for the parking violation
     */
    private void storeParkingViolations(int zip, double fine) {
	ParkingViolation thisViolation = new ParkingViolation(zip, fine);
	allParkingViolations.add(thisViolation);
    }

    /**
     * parseCSV reads the parking violations data from a .csv file and stores all
     * valid parking violations to 'allParkingViolations' List
     * 
     * @param inputFileName .csv file with the parking violations information
     * @throws FileNotFoundException
     */
    private void parseCSV(String inputFileName) {
	File inputFile = new File(inputFileName);

	String[] violationLine = new String[7];
	try {
	    Scanner s = new Scanner(inputFile);
	    s.nextLine();
	    while (s.hasNextLine()) {
		String line = s.nextLine();
		violationLine = line.split(",");
		if (violationLine.length == 7) {
		    if (violationLine[6] != null && violationLine[4] == "PA") {
			int zip = Integer.parseInt(violationLine[6]);
			double fine = Integer.parseInt(violationLine[1]);
			storeParkingViolations(zip, fine);
		    }
		}
	    }
	    s.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect CSV input File name"); // error message to be displayed when input filename
	    // cannot
	    // be found
	}
    }

    /**
     * parseJSON reads the parking violations data from a .json file and stores all
     * valid parking violations to 'allParkingViolations' List
     * 
     * @param inputFileName .json file with the parking violations information
     */
    private void parseJSON(String inputFileName) {
	JSONParser parser = new JSONParser();

	try {
	    JSONArray allViolationLines = (JSONArray) parser.parse(new FileReader(inputFileName));
	    Iterator iter = allViolationLines.iterator();
	    while (iter.hasNext()) {
		JSONObject violationLine = (JSONObject) iter.next();
		if (violationLine.get("zip_code") != "" && violationLine.get("state") == "PA") {
		    int zip = (int) violationLine.get("zip_code");
		    double fine = (double) violationLine.get("state");
		    storeParkingViolations(zip, fine);
		}

	    }

	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect JSON input File name");
	} catch (IOException e) {
	    System.out.println("IO Exception");
	} catch (ParseException e) {
	    System.out.println("ParseException");
	}
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
