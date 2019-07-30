package edu.upenn.cit594.ui;

import edu.upenn.cit594.data.ParkingViolation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Writer class handles the user interface with presenting the analyzed data to
 * the user. It is able to print to txt file and console.
 *
 */
public class Writer {

	/**
	 * txtWriter is overloaded to take a List of ParkingViolation data and 
	 * print it to a txt file with one zip code and fine per line.
	 * 
	 * @param myData A List of Parking Violations
	 * @param fileName Name of file to be printed to 
	 */
	public void txtWriter(List<ParkingViolation> myData, String fileName) {
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
	 * txtWriter is overloaded to take a Map of zip codes to total fines and 
	 * print to a txt file with one zip code and fine per line.
	 * 
	 * @param myData a Map of zip codes to total fines
	 * @param fileName Name of file to be printed to
	 */
	public void txtWriter(Map<Integer, Double> myData, String fileName) {
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

	/**
	 * consoleWriter takes a Map of zip codes to fines per capita and prints
	 * the information to console, with one zip code and fine per line.
	 * @param myData a Map of zip codes to fines per capita
	 */
	public void consoleWriter(Map<Integer, Double> myData) {
		// print in ascending order from TreeSet
		for (Map.Entry<Integer, Double> entry : myData.entrySet()) {
			// space delimited
			// Only print 4 decimal places 
			System.out.format("%d %.4f\n", entry.getKey(), entry.getValue());
		}
	}
}
