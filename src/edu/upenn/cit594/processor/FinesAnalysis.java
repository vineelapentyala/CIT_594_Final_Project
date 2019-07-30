package edu.upenn.cit594.processor;

import java.util.ArrayList;
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
	private List<ParkingViolation> processedViolations;
	private Map<Integer, Double> totalFines;
	private Map<Integer, Double> finesPerCapita;

	/**
	 * The constructor takes in a list of parking violations and a map of zip
	 * codes to population. It calls the appropriate private methods to 
	 * populate the list and both maps in the instance variables.
	 * @param allParkingViolations A list of all parking violations in PA with zip codes
	 * @param populationMap A Map of PA zip codes to population
	 */
	public FinesAnalysis(List<ParkingViolation> allParkingViolations, Map<Integer, Integer> populationMap) {
	        processedViolations = new ArrayList<ParkingViolation>();
		totalFines = new HashMap<Integer, Double>();
		finesPerCapita = new TreeMap<Integer, Double>();
		processViolations(allParkingViolations);
		populateTotalFines();
		populateFinesPerCapita(populationMap);
	}
	
	/**
	 * processTotalViolations takes in the list of parking violations and 
	 * processes the data to be excluded, here the licenses that are not PA
	 * and any violations with missing zip codes. It populates 
	 * processedViolations with the appropriate data.
	 * 
	 * @param allParkingViolations A list of all parking violations that were read in
	 */
	private void processViolations(List<ParkingViolation> allParkingViolations) {
		for (ParkingViolation violation : allParkingViolations) {
			if (violation.getState().equals("PA") && violation.getZip() != 0) {
				processedViolations.add(violation);
			}
		}
	}

	/**
	 * populateTotalFines uses the list of processed parking violations 
	 * to aggregate the total cost of parking violations in each zip code
	 * in the totalFines hashmap.
	 * 
	 */
	private void populateTotalFines() {
		for (ParkingViolation violation : processedViolations) {
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
	 * getProcessedViolations() is a getter for the processedViolations list.
	 * @return List of violations that occurred in PA with a valid zip code.
	 */
	public List<ParkingViolation> getProcessedViolations() {
		return processedViolations;
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
