/**
 * Lexical Density Analyzer calculates the lexical density
 * for the given input file. Lexical Density is determined
 * by the number of non-lexical words (function words)
 * divided by the total number of words in the given phrase.
 *
 * @author Chelsea Greger
 * @since v2.0
 */
package java112.analyzer;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class LexicalDensityAnalyzer implements Analyzer {

	// Declare instance variables
	private Properties properties;
	private ArrayList<String> functionWords;
	private double totalWordCount;
	private int lexicalWordCount;

	/**
	 * Empty constructor for LexicalDensityAnalyzer.
	 * Initializes the functionWords ArrayList.
	 */
	public LexicalDensityAnalyzer() {

		// Instantiate an ArrayList to hold the function words
		functionWords = new ArrayList<String>();

	}

	/**
	 * Properties and function words constructor for LexicalDensityAnalyzer.
	 *
	 * @param  properties properties list for the analyzers
	 */
	public LexicalDensityAnalyzer(Properties properties) {

		this();
		this.properties = properties;
		totalWordCount = 0;
		lexicalWordCount = 0;

	}

	/**
	 * Loads and parses function words file and add's each
	 * function word to the functionWord's ArrayList.
	 */
	public void loadFunctionWordsFile() {

		//Load the function words from it's config file
		try (BufferedReader in = new BufferedReader(
								 new FileReader(properties.getProperty("function.words")))) {

			// Define local variables
			String line = null;
			Iterator<String> i = null;
			List<String> tokens= null;

			// Read from the function words file
			while (in.ready()) {

	            line = in.readLine();

				// Generate tokens from each line
	            tokens = Arrays.asList(line.split("\\W"));

                i = tokens.iterator();

				// Remove all empty tokens (returns) from the ArrayList
                while (i.hasNext()) {

                    String token = i.next();

                    if (token.isEmpty()) {

                        i.remove();

                    } else {

						// Add the word the functionWords ArrayList
						functionWords.add(token);

					}
                }
			}

		} catch (FileNotFoundException fileNotFoundException) {

            System.out.println("There was a problem opening the file.");

            fileNotFoundException.printStackTrace();

        } catch (IOException ioException) {

            System.out.println("There was a problem reading the file.");

            ioException.printStackTrace();

        } catch (Exception exception) {

            System.out.println("There was some other problem.");

            exception.printStackTrace();

        }

	}

	/**
	 * Increments the totalWordCount for every token in
	 * the file. If the token is found in the file
	 * of function words, then the lexicalWordCount is
	 * incremented as well.
	 *
	 * @param token the current token from the input file.
	 */
	public void processToken(String token) {

		//The words need to match the lowercase
		//words in the function_words text file.
		token = token.toLowerCase();

		totalWordCount++;

		if (!functionWords.contains(token)) {

			lexicalWordCount++;

		}

	}


	/**
	 * Calculates the percentage of lexical density
	 * for the input file given by the user.
	 *
	 * @return The lexical density percentage for the input file.
	 */
	//Normally, this would be private, but I needed it public for testing...
	public double calculateLexicalDensity() {

		DecimalFormat df = new DecimalFormat("#.00");
		double ld = Double.valueOf(df.format((lexicalWordCount / totalWordCount) * 100));

		return ld;

	}


	/**
	 * Creates, formats, and outputs the lexical density percentage
	 * for this file.
	 *
	 * @param  inputFilePath  		 the file that was given by the user
	 *                          	 and processed
	 */
	public void writeOutputFile(String inputFilePath) {

		// Create and write each unique token to the output file
		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir")
				+ properties.getProperty("output.file.lexical.density"))))) {

			out.println(calculateLexicalDensity() + "%");

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

}
