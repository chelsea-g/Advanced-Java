/**
 * A class to test the LexicalDensityAnalyzer
 *
 * @author Chelsea Greger
 * @version v2.0
 */

package java112.analyzer;

import java.util.*;
import java.io.*;

public class LexicalTest {

	public static void main (String[] args) {

		LexicalTest lt = new LexicalTest();

		lt.run();

	}

	public void run() {

		// Create a new instance of the Properties object
		Properties properties = new Properties();

		// Load the properties file
		try {

			properties.load(this.getClass().getResourceAsStream("/analyzer.properties"));

		} catch (IOException ioe) {

			System.out.println("Can't load the properties file");

			ioe.printStackTrace();

		} catch (Exception e) {

			System.out.println("Problem: " + e);

			e.printStackTrace();

		}




		//////////////////////////////////////////////////////////////////////
		//Test 1															//
		//////////////////////////////////////////////////////////////////////
		System.out.println("---------------Test #1---------------");
		LexicalDensityAnalyzer test1 = new LexicalDensityAnalyzer(properties);


		System.out.println("The quick brown fox jumped swiftly over the lazy dog. LD of 70.0%");
		String[] testTokens1 = {"The", "quick", "brown", "fox", "jumped", "swiftly",
								"over", "the", "lazy", "dog"};

		System.out.println("Processing...");
		for (String token : testTokens1) {

			test1.processToken(token);

		}

		System.out.println("Calculating...");
		if (test1.calculateLexicalDensity() == 70.0) {

			System.out.println("PASSED");

		} else {

			System.out.println("FAILED");

		}

		//////////////////////////////////////////////////////////////////////
		//Test 2															//
		//////////////////////////////////////////////////////////////////////
		System.out.println("---------------Test #2---------------");
		LexicalDensityAnalyzer test2 = new LexicalDensityAnalyzer(properties);


		System.out.println("She told him that she loved him. LD of 28.57%");
		String[] testTokens2 = {"She", "told", "him", "that", "she", "loved",
							   "him"};

		System.out.println("Processing...");
		for (String token : testTokens2) {

			test2.processToken(token);

		}

		System.out.println("Calculating...");
		if (test2.calculateLexicalDensity() == 28.57) {

			System.out.println("PASSED");

		} else {

			System.out.println("FAILED");

		}

		//////////////////////////////////////////////////////////////////////
		//Test 3															//
		//////////////////////////////////////////////////////////////////////
		System.out.println("---------------Test #3---------------");
		LexicalDensityAnalyzer test3 = new LexicalDensityAnalyzer(properties);


		System.out.println("High above the city, on a tall column, "
				+ "stood the statue of the Happy Prince. LD of 53.33%");
		String[] testTokens3 = {"High", "above", "the", "city", "on", "a", "tall",
								"column", "stood", "the", "statue", "of", "the",
								"Happy", "Prince"};

		System.out.println("Processing...");
		for (String token : testTokens3) {

			test3.processToken(token);

		}

		System.out.println("Calculating...");
		if (test3.calculateLexicalDensity() == 53.33) {

			System.out.println("PASSED");

		} else {

			System.out.println("FAILED");

		}


	}
}
