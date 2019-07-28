package edu.upenn.cit594;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.upenn.cit594.*;
import edu.upenn.cit594.processor.FinesAnalysis;
import edu.upenn.cit594.ui.Writer;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class Main {

	public static void main(String[] args) {
		try {
			errorCheckArgs(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("no errors");

		//Take arguments 
		String fileFormat = args[0];
		String parkViolateFilename = args[1]; // this String has file suffix .csv or .json
		String populationInputFilename = args[2];
		
		// For all parking violations: create AllParkingViolations in constructor of ParkingReader
		ParkingReader myParkingReader = new ParkingReader(parkViolateFilename, fileFormat); 
		PopulationReader myPopulationReader = new PopulationReader(populationInputFilename);
		
		//Call ui Writer, write fines.txt based on allParkingViolations
		Writer myWriter = new Writer();
		myWriter.txtWriter(myParkingReader.getAllParkingViolations(), "fines.txt");
				
		//Pass list of allParkingViolations and population data to Constructor of Processor
		FinesAnalysis myFinesAnalysis = new FinesAnalysis(myParkingReader.getAllParkingViolations(), myPopulationReader.getPopulationMap());
		
		// print total.txt
		myWriter.txtWriter(myFinesAnalysis.getTotalFines(), "total.txt");		
		
		// print result to console
		myWriter.consoleWriter(myFinesAnalysis.getFinesPerCapita());

	}
	
	private static void errorCheckArgs(String[] args) throws IOException {
		// check number of args
		if(args.length != 3) {
			throw new IllegalArgumentException("Exactly 3 arguments required");		
		// check if args[0] is either csv or json
		} else if (!args[0].equals("csv") && !args[0].equals("json")) {
			throw new IllegalArgumentException("First argument must be either csv or json, case sensitive");
		}
		
		File parkingFile = new File(args[1]);
		File populationFile = new File(args[2]);
		// Check that file exists
		if (!parkingFile.exists() || !populationFile.exists()) {
			throw new FileNotFoundException("At least one of the files does not exist.");
		}
		
		// Check that file can be opened
		if (!parkingFile.canRead() || !populationFile.exists()) {
			throw new IOException("At least one of the files cannot be opened.");
		}

	}

}
