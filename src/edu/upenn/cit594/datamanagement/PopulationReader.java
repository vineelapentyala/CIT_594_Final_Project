package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class PopulationReader {
    
   private  HashMap<Integer, Integer> populationMap;
   
   
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
		    populationMap.put(zip, population);
	    }
	    s.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Incorrect input File name"); //error message to be displayed when input filename cannot be found
	}
   }

   public HashMap<Integer, Integer> getPopulationMap() {
    return populationMap;
   }
   
   
}
