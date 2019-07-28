package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingViolation;

/**
 * FinesAnalysis class is part of the Processor tier. It performs the
 * calculations of total fines per zip code and fines per capita in each zip
 * code, then stores this data into appropriate maps for the UI tier to access.
 *
 */
public class FinesAnalysis {
	private Map<Integer, Double> totalFines;
	private Map<Integer, Double> finesPerCapita;

	/**
	 * The constructor takes in a list of parking violations and a map of zip
	 * codes to population. It calls the appropriate private methods to 
	 * populate both maps in the instance variables.
	 * @param allParkingViolations A list of all parking violations in PA with zip codes
	 * @param populationMap A Map of PA zip codes to population
	 */
	public FinesAnalysis(List<ParkingViolation> allParkingViolations, Map<Integer, Integer> populationMap) {
		totalFines = new HashMap<Integer, Double>();
		finesPerCapita = new TreeMap<Integer, Double>();
		
		populateTotalFines(allParkingViolations);
		populateFinesPerCapita(populationMap);
	}

	/**
	 * populateTotalFines takes in the list of parking violations and uses its
	 * data to aggregate the total cost of parking violations in each zip code
	 * in the totalFines hashmap.
	 * @param allParkingViolations A list of all parking violations in PA with zip codes
	 */
	private void populateTotalFines(List<ParkingViolation> allParkingViolations) {
		for (ParkingViolation violation : allParkingViolations) {
			int zip = violation.getZip();
			double fine = violation.getFine();
			
			// If zip exists, add to existing fine; otherwise add new zip code and fine
			totalFines.put(zip, totalFines.getOrDefault(zip, 0.0) + fine);
		}
		
	}

	/**
	 * populateFinesPerCapita uses the existing totalFines hashmap and map of
	 * zip codes to population to create a TreeMap of fines per capita.
	 * @param populationMap A Map of PA zip codes to population
	 */
	private void populateFinesPerCapita(Map<Integer, Integer> populationMap) {
		for (int zip : totalFines.keySet()) {
			double totalFine = totalFines.get(zip);
			
			// TODO Check to make sure this is what they want
			if (!populationMap.containsKey(zip)) {
				continue;
			}
			
			int population = populationMap.get(zip);
			
			// Calculate fine per capita and add to zip
			double finePerCapita = totalFine / population;
			
			// Automatically excludes zips in population without fines
			finesPerCapita.put(zip, finePerCapita);
		}
	}

	/**
	 * getTotalFines() is a getter for the totalFines hashmap.
	 * @return Map of zip code : total fines in that zip code.
	 */
	public Map<Integer, Double> getTotalFines() {
		return totalFines;
	}

	/**
	 * getFinesPerCapita() is a getter for the finesPerCapita treemap.
	 * @return Map of zip code : fines per capita in that zip code.
	 */
	public Map<Integer, Double> getFinesPerCapita() {
		return finesPerCapita;
	}

}
