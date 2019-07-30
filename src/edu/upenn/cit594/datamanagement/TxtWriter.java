package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.ParkingViolation;

public class TxtWriter {
	String fileName; // To be written to

	public TxtWriter(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * writeFromList takes a List of ParkingViolation data and print it to a txt
	 * file with one zip code and fine per line.
	 * 
	 * @param myData   A List of Parking Violations
	 */
	public void writeFromList(List<ParkingViolation> myData) {
		// initialize file to be overwritten
		PrintWriter destFile = null;

		try {
			destFile = new PrintWriter(new FileOutputStream(fileName, false));
		} catch (FileNotFoundException e) {
			System.out.println("Error in writing to destination file: " + fileName);
			e.printStackTrace();
		}

		for (ParkingViolation data : myData) {
			// space delimited, print fine as int
			destFile.print(data.getZip() + " " + (int) data.getFine() + "\n");
		}
		destFile.close();
	}

	/**
	 * writeFromMap takes a Map of zip codes to total fines and print to a txt file
	 * with one zip code and fine per line.
	 * 
	 * @param myData   a Map of zip codes to total fines
	 */
	public void writeFromMap(Map<Integer, Double> myData) {
		// initialize file to be overwritten
		PrintWriter destFile = null;

		try {
			destFile = new PrintWriter(new FileOutputStream(fileName, false));
		} catch (FileNotFoundException e) {
			System.out.println("Error in writing to destination file:  " + fileName);
			e.printStackTrace();
		}

		for (Map.Entry<Integer, Double> entry : myData.entrySet()) {
			// space delimited, print fine as int
			destFile.print(entry.getKey() + " " + entry.getValue().intValue() + "\n");
		}
		destFile.close();
	}

}
