package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CSVReader inherits from ParkingReader class. It specifically manages reading
 * from a CSV input file.
 *
 */
public class CSVReader extends ParkingReader {
	public CSVReader(String inputFileName) throws Exception {
		super(inputFileName);
	}

	/**
	 * parse reads the parking violations data from a .csv file and stores all valid
	 * parking violations to 'allParkingViolations' List
	 * 
	 * @param inputFileName String name of file to be read
	 * @throws FileNotFoundException
	 * 
	 */
	
	@Override
	public void parse(String inputFileName) throws FileNotFoundException {
		File inputFile = new File(inputFileName);

		Scanner s = new Scanner(inputFile);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] violationLine = line.split(",");
			int zip = 0;
			if (violationLine.length == 7) {
				zip = Integer.parseInt(violationLine[6]);
			}
			double fine = Integer.parseInt(violationLine[1]);
			String state = violationLine[4];
			storeParkingViolations(zip, fine, state);
		}
		s.close();
	}

}
