/**
 * 	Analyzes the file given by the user by generating the individual
 * 	tokens in the file and passes the tokens generated to SummaryReport
 * 	and UniqueTokenAnalyzer to be processed. Finishes by outputing
 * 	the newly created text files:
 * 	summary_report.txt and unique_tokens.txt
 *
 * 	@author Chelsea Greger
 *	@since v1.0
 */

package java112.analyzer;

import java.io.*;
import java.util.*;


public class AnalyzeFile {

	// Define constants
	private static final int        VALID_ARGUMENT_COUNT = 2;

	// Declare instance variables
	private String 					inputFilePath;
	private ArrayList<Analyzer>     analyzers;
	private Properties 				properties;

	/**
	 * Empty constructor for AnalyzeFile
	 */
	public AnalyzeFile() { }

	/**
	 * Properties constructor for AnalyzeFile.
	 * Loads the properties for the analyzers.
	 *
	 * @param  propertiesFilePath file path for the properties list
	 */
	public AnalyzeFile(String propertiesFilePath) {

		this.loadProperties(propertiesFilePath);

	}

	public void loadProperties(String propertiesFilePath) {

		// Create a new instance of the Properties object
		this.properties = new Properties();

		// Load the properties file
        try {

            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {

            System.out.println("Can't load the properties file");

            ioe.printStackTrace();

        } catch (Exception e) {

            System.out.println("Problem: " + e);

            e.printStackTrace();

        }

	}


	/**
	 * Calls all methods needed to run the application completely
	 *
	 * @param arguments input file path given through
	 *                  the command line
	 */
	public void runAnalysis(String[] arguments) {

		inputFilePath = arguments[0];

		this.testArgumentCount(arguments);
		this.instantiateAnalyzers(properties);
		this.generateIndividualTokens(inputFilePath);
		this.writeAllOutputFiles();

	}


	/**
	 * Tests the number of arguments that the user passed through
	 * the command line
	 *
	 * @param arguments input file path given through
	 *                  the command line
	 */
	private void testArgumentCount(String[] arguments) {

		if (arguments.length != VALID_ARGUMENT_COUNT) {

			// Output error and exit application
			System.out.println("Please enter the input file path");

			System.exit(0);

		} else {

			inputFilePath = arguments[0];

		}

	}

	/**
	 * Instantiates this SummaryReport object
	 * @param properties properties file for the analyzers
	 */
	private void instantiateAnalyzers(Properties properties) {

		analyzers = new ArrayList<Analyzer>();

		analyzers.add(new SummaryReport(properties));
		analyzers.add(new UniqueTokenAnalyzer(properties));
		analyzers.add(new BigWordAnalyzer(properties));
		analyzers.add(new TokenCountAnalyzer(properties));
		analyzers.add(new LexicalDensityAnalyzer(properties));
		analyzers.add(new TokenSizeAnalyzer(properties));
		analyzers.add(new KeywordAnalyzer(properties));

	}

	/**
	 * Opens and reads the input file. The input file is stripped of
	 * punctuation and the individual tokens are processed
	 * by this SummaryReport and this UniqueTokenAnalyzer.
	 * <p>
	 * The file is closed automatically by using try with resources.
	 *
	 * @param inputFilePath 		 input file path given through
	 *                         		 the command line
	 * @throws FileNotFoundException problem opening file
	 * @throws IOException			 problem reading file
	 * @throws Exception			 other problem
	 */
	private void generateIndividualTokens(String inputFilePath) {


		try (BufferedReader in = new BufferedReader(new FileReader(inputFilePath))) {

			// Define local variables
			String line = null;
            List<String> tokens= null;
            ArrayList<String> strippedTokens= null;
            Iterator<String> i = null;

			// Read from the input file
			while (in.ready()) {

	            line = in.readLine();

				// Generate tokens from each line, exclude non
				// word characters
	            tokens = Arrays.asList(line.split("\\W"));

				// Instantiate an ArrayList for the generated
				// tokens
                strippedTokens = new ArrayList<String>(tokens);

                i = strippedTokens.iterator();

				// Remove all empty tokens from the ArrayList
                while (i.hasNext()) {

                    String token = i.next();

                    if (token.isEmpty()) {

                        i.remove();

                    }

                }

				processTokens(strippedTokens);

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
	 * Passes each token through each analyzer's
	 * process method.
	 *
	 * @param tokens the unique token from the inputFile.
	 */
	private void processTokens(ArrayList<String> tokens) {

		// Process each token in this line
		for (String token : tokens) {

			// Have each analyzer proecess the token
			for (Analyzer a : analyzers) {

				a.processToken(token);

			}

		}

	}

	/**
	 * Calls methods necessary to write each output file
	 */
	private void writeAllOutputFiles() {

		// Write each analyzer's output file
		for (Analyzer a : analyzers) {

			a.writeOutputFile(this.inputFilePath);

		}

	}

}
