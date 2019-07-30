package edu.upenn.cit594.ui;

import java.util.Map;

/**
 * Writer class handles the user interface with presenting the analyzed data to
 * the user. It is able to print to txt file and console.
 *
 */
public class ConsoleWriter {

	/**
	 * write takes a Map of zip codes to fines per capita and prints the information
	 * to console, with one zip code and fine per line.
	 * 
	 * @param myData a Map of zip codes to fines per capita
	 */
	public void write(Map<Integer, Double> myData) {
		// print in ascending order from TreeSet
		for (Map.Entry<Integer, Double> entry : myData.entrySet()) {
			// space delimited
			// Only print 4 decimal places
			System.out.format("%d %.4f\n", entry.getKey(), entry.getValue());
		}
	}
}
