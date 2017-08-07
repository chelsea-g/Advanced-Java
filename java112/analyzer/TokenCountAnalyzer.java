/**
 * Generates a text file that holds a list of
 * unique tokens in a file along with the number
 * of occurences of each token in the file.
 *
 * @author Chelsea Greger
 * @since v2.0
 */

package java112.analyzer;

import java.util.*;
import java.io.*;

public class TokenCountAnalyzer implements Analyzer {

	// Declare instance variables
	private Properties properties;
	private Map<String, Integer> tokenCounts;

	/**
	 * Empty constructor for TokenCountAnalyzer
	 * Initializes the tokenCounts Hash Map
	 */
	public TokenCountAnalyzer() {

		tokenCounts = new TreeMap<String, Integer>();

	}

	/**
	 * Properties constructor for TokenCountAnalyzer
	 * Initializes the properties for the analyzer
	 *
	 * @param  properties list of properties for this analyzer
	 */
	public TokenCountAnalyzer(Properties properties) {

		this();
		this.properties = properties;

	}

	/**
	 * Creates, formats, and writes a list of unique tokens
	 * and the number of occurences of each token that was in
	 * the input file that was analyzed by the AnalyzeFile object
	 *
	 * @param  inputFilePath  		 the file that was given by the user
	 *                          	 and processed
	 */
	public void writeOutputFile(String inputFilePath) {

		// Create and write each unique token to the output file
		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(new FileWriter(properties.getProperty("output.dir") + properties.getProperty("output.file.token.count"))))) {

			// Iterate through the tokenCounts to output each token to the file
			for(String key : getTokenCounts().keySet()) {
				
				out.println(key + "\t" + getTokenCounts().get(key));

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
	 * Increments the count of each token when there is
	 * another occurence of the token.
	 *
	 * @param token a unique token from the input file
	 */
	public void processToken(String token) {

		// The HashMap already contains this token
		if (tokenCounts.containsKey(token)) {

			// Increment the token's count
			tokenCounts.put(token, tokenCounts.get(token) + 1);

		// This is the token's first occurence
		} else {

			// Add the token to the HashMap and set it's count to 1
			tokenCounts.put(token, 1);

		}

	}

	/**
	 * Gets the current map of tokens and counts.
	 *
	 * @return tokenCounts a HashMap of token keys and count values
	 */
	public Map<String, Integer> getTokenCounts() { return tokenCounts; }

}
