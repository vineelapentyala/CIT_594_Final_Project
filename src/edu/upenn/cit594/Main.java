package edu.upenn.cit594;

import java.io.File;
import java.io.IOException;

import edu.upenn.cit594.processor.FinesAnalysis;
import edu.upenn.cit594.ui.ConsoleWriter;

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

			// Call UI tier and print to txt and console as appropriate
			ConsoleWriter myWriter = new ConsoleWriter();
			myWriter.txtWriter(analysis.getProcessedViolations(), "fines.txt");
			myWriter.txtWriter(analysis.getTotalFines(), "total.txt");
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
		
		// Check that files exist
		if (!parkingFile.exists() || !populationFile.exists()) {
			throw new IOException("At least one of the files does not exist.");
		}

		// Check that files can be opened
		if (!parkingFile.canRead() || !populationFile.canRead()) {
			throw new IOException("At least one of the files cannot be opened.");
		}

	}

}
