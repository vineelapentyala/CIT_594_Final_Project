package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.ParkingViolation;

public class FinesAnalysis {
	private Map<Integer, Double> totalFines;
	private Map<Integer, Double> finesPerCapita;
	
	public FinesAnalysis(List<ParkingViolation> allParkingViolations, Map<Integer, Double> populationMap) {
		
		populateTotalFines();
		
		populateFinesPerCapita(populationMap);
	}
	
	//TODO
	private void populateTotalFines() {
		
	}
	
	
	//TODO
	private void populateFinesPerCapita(Map<Integer, Double> populationMap) {
		
	}

	public Map<Integer, Double> getTotalFines() {
		return totalFines;
	}

	public Map<Integer, Double> getFinesPerCapita() {
		return finesPerCapita;
	}
	
	
	
	
}
