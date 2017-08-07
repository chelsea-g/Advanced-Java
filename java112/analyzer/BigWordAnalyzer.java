/**
 *	BigWordAnalyzer processes tokens in a file
 *	to generate a text file that lists the longer
 *	words in the file.
 *
 * @author Chelsea Greger
 * @since v2.0
 *
 */
package java112.analyzer;

import java.util.*;
import java.io.*;

public class BigWordAnalyzer implements Analyzer {

	// Declare instance variables
	private Properties properties;
	private Set<String> bigWords;
	private int minimumWordLength;

	/**
	 * Empty constructor for BigWordAnalyzer.
	 * Initializes the bigWords TreeSet.
	 */
	public BigWordAnalyzer() {

		bigWords = new TreeSet<String>();

	}

	/**
	 * Properties constructor for BigWordAnalyzer.
	 * Sets the minimumWordLength using the properties file.
	 *
	 * @param  properties list of properties to be used by the analyzers
	 */
	public BigWordAnalyzer(Properties properties) {

		this();
		this.properties = properties;

		minimumWordLength = Integer.parseInt(properties.getProperty("bigwords.minimum.length"));

	}

	/**
	 * Creates, formats, and writes a list of the larger words in
	 * the input file that was analyzed by the AnalyzeFile object.
	 *
	 * @param  inputFilePath  		 the file that was given by the user
	 *                          	 and processed
	 */
	public void writeOutputFile(String inputFilePath) {

		// Create and write each unique token to the output file
		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir") + properties.getProperty("output.file.bigwords"))))) {

			for (String token : getBigWords()) {

				out.println(token);

			}

		} catch (FileNotFoundException fileNotFoundException) {

			System.out.println("There was a problem creating the file.");

			fileNotFoundException.printStackTrace();

        } catch (IOException ioException) {

            System.out.println("There was a problem writing to the file.");

            ioException.printStackTrace();

        } catch (Exception exception) {

            System.out.println("There was some other problem.");

            exception.printStackTrace();

        }

	}

	/**
	 * Processes the token by adding only longer words
	 * to the BigWords list.
	 *
	 * @param token a single word token to be counted
	 */
	public void processToken(String token) {

		// Add the word if it is equal to or greater than the
		// minimumWordLength
		if (token.length() >= minimumWordLength) {

			// Add the token to the BigWords list
			getBigWords().add(token);

		}

	}

	/**
	 * Gets the current set of big words.
	 *
	 * @return bigWords
	 */
	public Set<String> getBigWords() {

		return bigWords;

	}
}
