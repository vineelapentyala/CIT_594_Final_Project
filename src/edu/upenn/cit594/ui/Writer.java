package edu.upenn.cit594.ui;

import edu.upenn.cit594.*;
import edu.upenn.cit594.data.ParkingViolations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Writer {

	// Prints a ArrayList of data?
	public void txtWriter(List<ParkingViolations> myData, String fileName) {
		// initialize file to be overwritten
		PrintWriter destFile = null;
		
		try {
			destFile = new PrintWriter(new FileOutputStream(fileName, false));
		} catch (FileNotFoundException e) {
			System.out.println("Error in writing to destination file");
			e.printStackTrace();
		}
		
		for (ParkingViolations data : myData) {
			// space delimited
			destFile.print(data.getZip() + " " + data.getFine() + "\n");
		}
		destFile.close();
	}
	
	public void txtWriter(HashMap<Integer, Double> myData, String fileName) {
		// initialize file to be overwritten
		PrintWriter destFile = null;
		
		try {
			destFile = new PrintWriter(new FileOutputStream(fileName, false));
		} catch (FileNotFoundException e) {
			System.out.println("Error in writing to destination file");
			e.printStackTrace();
		}
		
		for (Map.Entry<Integer, Double> entry : myData.entrySet()) {
			// space delimited
			destFile.print(entry.getKey() + " " + entry.getValue() + "\n");
		}
		destFile.close();
	}

	public void consoleWriter(TreeMap<Integer, Double> myData) {
		// print in ascending order from TreeSet
		for (Map.Entry<Integer, Double> entry : myData.entrySet()) {
			// space delimited
			System.out.print(entry.getKey() + " " + entry.getValue() + "\n");
		}
	}
}
