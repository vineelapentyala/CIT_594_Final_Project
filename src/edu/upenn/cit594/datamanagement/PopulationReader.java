package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * PopulationReader class is part of the DataManagement tier. It performs the
 * task of reading the data from input files related to different zip codes in
 * PA and their population It stores this data into appropriate data structure
 * for the processor tier to access.
 *
 */

public class PopulationReader {

	private Map<Integer, Integer> populationMap;

	/**
	 * PopulationReader constructor initializes its map instance variable and calls
	 * a parse method to read the population text file and store its data in the map
	 * variable.
	 * 
	 * @param inputFileName The name of the population file to be read
	 */
	public PopulationReader(String inputFileName) {
		populationMap = new HashMap<Integer, Integer>();

		parseTxt(inputFileName);
	}

	/**
	 * storePopulation takes in zip and population as arguments, adds the map from
	 * zip code to its population to 'populationMap'
	 * 
	 * @param zip        zipcode where a parking violation occurred
	 * @param population population of the zip code
	 */
	private void storePopulation(int zip, int population) {
		populationMap.put(zip, population);
	}

	/**
	 * parseTxt reads the population data from a .txt file and stores the data to
	 * populationMap
	 * 
	 * @throws IOException
	 */
	private void parseTxt(String inputFileName) {
		File inputFile = new File(inputFileName);

		String[] zipAndpopulation = new String[2];
		try {
			Scanner s = new Scanner(inputFile);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				zipAndpopulation = line.split(" ");
				int zip = Integer.parseInt(zipAndpopulation[0]);
				int population = Integer.parseInt(zipAndpopulation[1]);
				storePopulation(zip, population);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Incorrect input File name"); // error message to be displayed when input filename cannot
																// be found
		}
	}

	/**
	 * getPopulationMap is a getter method for 'populationMap' HashMap
	 * 
	 * @return populationMap A HasMap of zipcodes to their Populations
	 */
	public Map<Integer, Integer> getPopulationMap() {
		return populationMap;
	}

}
