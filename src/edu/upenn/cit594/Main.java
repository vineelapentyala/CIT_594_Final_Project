package edu.upenn.cit594;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.upenn.cit594.*;
import edu.upenn.cit594.processor.FinesAnalysis;
import edu.upenn.cit594.ui.Writer;
import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.JSONReader;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class Main {

	public static void main(String[] args) {
		try {
			errorCheckArgs(args);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Store arguments
		String fileFormat = args[0];
		String parkViolateFilename = args[1]; // this String has file suffix .csv or .json
		String populationInputFilename = args[2];

		// Build Processor tier with appropriate arguments
		FinesAnalysis analysis;
		
		try {
			analysis = new FinesAnalysis(fileFormat, parkViolateFilename, populationInputFilename);

			// Call ui Writer, write fines.txt based on processed parking violations
			Writer myWriter = new Writer();
			myWriter.txtWriter(analysis.getProcessedViolations(), "fines.txt");

			// print total.txt
			myWriter.txtWriter(analysis.getTotalFines(), "total.txt");

			// print result to console
			myWriter.consoleWriter(analysis.getFinesPerCapita());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Helper method to throw exceptions if arguments are incorrect
	 * 
	 * @param args Arguments from command line
	 * @throws IOException
	 */
	private static void errorCheckArgs(String[] args) throws IOException {
		// check number of args
		if (args.length != 3) {
			throw new IllegalArgumentException("Exactly 3 arguments required");
			// check if args[0] is either csv or json
		} else if (!args[0].equals("csv") && !args[0].equals("json")) {
			throw new IllegalArgumentException("First argument must be either csv or json, case sensitive");
		}

		File parkingFile = new File(args[1]);
		File populationFile = new File(args[2]);
		// Check that file exists
		if (!parkingFile.exists() || !populationFile.exists()) {
			throw new IOException("At least one of the files does not exist.");
		}

		// Check that file can be opened
		if (!parkingFile.canRead() || !populationFile.canRead()) {
			throw new IOException("At least one of the files cannot be opened.");
		}

	}

}
