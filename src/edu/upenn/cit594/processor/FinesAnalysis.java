package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.*;

/**
 * FinesAnalysis class is part of the Processor tier. It performs the
 * calculations of total fines per zip code and fines per capita in each zip
 * code, then stores this data into appropriate maps for the UI tier to access.
 *
 */
public class FinesAnalysis {
	private List<ParkingViolation> processedViolations; // Violations in PA with zip code
	private Map<Integer, Double> totalFines;
	private Map<Integer, Double> finesPerCapita;

	private ParkingReader parkingReader;
	private PopulationReader populationReader;

	/**
	 * FinesAnalysis constructor initializes the appropriate instance variables and
	 * constructs the appropriate ParkingReader and PopulationReader objects. It
	 * uses these objects to populate its own list and maps.
	 * 
	 * @param parkingFormat   "csv" or "json"
	 * @param parkingFileName Full name of file with parking violation data
	 * @param popFileName     Full name of file with population data
	 * @throws Exception Thrown from JSONReader parsing
	 */
	public FinesAnalysis(String parkingFormat, String parkingFileName, String popFileName) throws Exception {
		processedViolations = new ArrayList<ParkingViolation>();
		totalFines = new HashMap<Integer, Double>();
		finesPerCapita = new TreeMap<Integer, Double>();

		if (parkingFormat.equals("csv")) {
			parkingReader = new CSVReader(parkingFileName);
		} else {
			parkingReader = new JSONReader(parkingFileName);
		}

		populationReader = new PopulationReader(popFileName);

		processViolations();
		printViolations();
		populateTotalFines();
		printTotalFines();
		populateFinesPerCapita();
	}

	/**
	 * processTotalViolations uses the list of all parking violations and processes
	 * the data to be excluded, here the licenses that are not PA and any violations
	 * with missing zip codes. It populates processedViolations with the appropriate
	 * data.
	 * 
	 */
	private void processViolations() {
		for (ParkingViolation violation : parkingReader.getAllParkingViolations()) {
			if (violation.getState().equals("PA") && violation.getZip() != 0) {
				processedViolations.add(violation);
			}
		}
	}
	
	private void printViolations() {
		TxtWriter violationWriter = new TxtWriter("fines.txt");
		violationWriter.writeFromList(processedViolations);
	}

	/**
	 * populateTotalFines uses the list of processed parking violations to aggregate
	 * the total cost of parking violations in each zip code in the totalFines
	 * hashmap.
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
	
	private void printTotalFines() {
		TxtWriter totalFineWriter = new TxtWriter("total.txt");
		totalFineWriter.writeFromMap(totalFines);
	}

	/**
	 * populateFinesPerCapita uses the existing totalFines hashmap and map of zip
	 * codes to population to create a TreeMap of fines per capita.
	 * 
	 */
	private void populateFinesPerCapita() {
		for (int zip : totalFines.keySet()) {
			double totalFine = totalFines.get(zip);

			Map<Integer, Integer> populationMap = populationReader.getPopulationMap();

			// Skip zip codes that do not have populations
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
	 * getFinesPerCapita() is a getter for the finesPerCapita treemap.
	 * 
	 * @return Map of zip code : fines per capita in that zip code.
	 */
	public Map<Integer, Double> getFinesPerCapita() {
		return finesPerCapita;
	}

}
