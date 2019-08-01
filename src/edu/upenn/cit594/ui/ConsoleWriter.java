package edu.upenn.cit594.ui;

import java.util.Map;

import edu.upenn.cit594.processor.FinesAnalysis;

/**
 * Writer class handles the user interface with presenting the analyzed data to
 * the user. It is able to print to txt file and console. It has an instance of
 * the processor tier's FinesAnalysis object. 
 *
 */
public class ConsoleWriter {
	
	FinesAnalysis analysis;
	
	public ConsoleWriter (FinesAnalysis analysis) {
		this.analysis = analysis;
	}

	/**
	 * write takes a Map of zip codes to fines per capita, accessed via the 
	 * FinesAnalysis object it contains, and prints the information to console
	 * with one zip code and fine per line. 
	 * 
	 */
	public void write() {
		Map<Integer, Double> finesPerCapita = analysis.getFinesPerCapita();
		// print in ascending order from TreeSet
		for (Map.Entry<Integer, Double> entry : finesPerCapita.entrySet()) {
			// space delimited
			// Only print 4 decimal places
			System.out.format("%d %.4f\n", entry.getKey(), entry.getValue());
		}
	}
}
