package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader extends ParkingReader {

	public JSONReader(String inputFileName) {
		super(inputFileName);
	}

	/**
	 * parseJSON reads the parking violations data from a .json file and stores all
	 * valid parking violations to 'allParkingViolations' List
	 * 
	 * @param inputFileName .json file with the parking violations information
	 */
	public void parse() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray allViolationLines = (JSONArray) parser.parse(new FileReader(inputFileName));
		Iterator iter = allViolationLines.iterator();
		while (iter.hasNext()) {
			JSONObject violationLine = (JSONObject) iter.next();
			int zip = 0;
			if (!violationLine.get("zip_code").equals("")) {
				zip = Integer.parseInt((String) violationLine.get("zip_code"));
			}
			double fine = ((Long) violationLine.get("fine")).doubleValue();
			String state = (String) violationLine.get("state");
			storeParkingViolations(zip, fine, state);
		}
	}

}
