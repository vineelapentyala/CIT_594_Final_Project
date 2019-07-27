package edu.upenn.cit594.datamanagement;
import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.ParkingViolation;

public class ParkingReader {

    private List<ParkingViolation> allParkingViolations;
    
    private void storeParkingViolations(int zip, double fine) {
	ParkingViolation thisViolation = new ParkingViolation(zip, fine);
	allParkingViolations.add(thisViolation);
    }
    
    public void parseCSV(String inputFileName) {
	File inputFile = new File(inputFileName);
	String[] violationLine = new String[7];
	try {
	    Scanner s = new Scanner(inputFile);
	    s.nextLine();
	    while (s.hasNextLine()) {
		String line = s.nextLine();
		violationLine = line.split(",");
		if (violationLine[6]!= "" && violationLine[4] == "PA") {
		    int zip = Integer.parseInt(violationLine[6]);
		    double fine = Integer.parseInt(violationLine[1]);
		    storeParkingViolations(zip, fine);
		}
	    }
	    s.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect input File name"); //error message to be displayed when input filename cannot be found
	}
    }
    
    private void parseJSON(String inputFileName) {
	JSONParser parser = new JSONParser();
	
	try {
	    JSONArray allViolationLines = (JSONArray)parser.parse(new FileReader("inputFileName"));
	    Iterator iter = allViolationLines.iterator();
	    while (iter.hasNext()) {
		JSONObject  violationLine = (JSONObject) iter.next();
		if (violationLine.get("zip_code")!= "" && violationLine.get("state") == "PA") {
		    int zip = (int) violationLine.get("zip_code");
		    double fine = (double)violationLine.get("state");
		    storeParkingViolations(zip, fine);
		}
		
	    }
	    
	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect input File name");
	} catch (IOException e) {
	    System.out.println("IO Exception");
	} catch (ParseException e) {
	    System.out.println("ParseException");
	}
    }

    public List<ParkingViolation> getAllParkingViolations() {
        return allParkingViolations;
    }
    
    
}
