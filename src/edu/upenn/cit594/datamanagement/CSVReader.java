package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader extends ParkingReader {
	public CSVReader(String inputFileName) throws Exception {
		super(inputFileName);
	}

	/**
	 * parseCSV reads the parking violations data from a .csv file and stores all
	 * valid parking violations to 'allParkingViolations' List
	 * 
	 * @param inputFileName .csv file with the parking violations information
	 * @throws FileNotFoundException
	 */
	public void parse() throws FileNotFoundException {
		File inputFile = new File(inputFileName);

		Scanner s = new Scanner(inputFile);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] violationLine = line.split(",");
			if (violationLine.length == 7) {
				if (violationLine[6] != null && violationLine[4].equals("PA")) {
					int zip = Integer.parseInt(violationLine[6]);
					double fine = Integer.parseInt(violationLine[1]);
					String state = violationLine[4];
					storeParkingViolations(zip, fine, state);
				}
			}
		}
		s.close();

	}

}
