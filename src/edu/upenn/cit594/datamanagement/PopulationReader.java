package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * PopulationReader class is part of the DataManagement tier. It performs the
 * task of reading the data from input files related to different zip codes in PA and their population
 * It  stores this data into appropriate data structure for the processor tier to access.
 *
 */

public class PopulationReader {
    
   private  HashMap<Integer, Integer> populationMap;
   
   /**
    * storePopulation takes in zip and population as arguments, 
    * adds the map from zip code to its population to 'populationMap'
    * @param zip zipcode where a parking violation occurred
    * @param population population of the zip code
    */
   private void storePopulation(int zip, int population) {
       populationMap.put(zip, population);
   }
   
   /**
    * parseTxt reads the population data from a .txt file and stores 
    * the data to populationMap
    * @param inputFileName .txt file with population data by zip code
    */
   public void parseTxt(String inputFileName) {
	File inputFile = new File(inputFileName);
	String[] zipAndpopulation = new String[2];
	try {
	    Scanner s = new Scanner(inputFile);
	    s.nextLine();
	    while (s.hasNextLine()) {
		String line = s.nextLine();
		zipAndpopulation = line.split(" ");
		    int zip = Integer.parseInt(zipAndpopulation[0]);
		    int population = Integer.parseInt(zipAndpopulation[1]);
		    storePopulation(zip, population);
	    }
	    s.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect input File name"); //error message to be displayed when input filename cannot be found
	}
   }

   /**
    * getPopulationMap is a getter method for 'populationMap' HashMap
    * @return populationMap A HasMap of zipcodes to their Populations
    */
   public HashMap<Integer, Integer> getPopulationMap() {
    return populationMap;
   }
   
   
}
