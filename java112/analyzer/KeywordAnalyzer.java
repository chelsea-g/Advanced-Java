/**
 *	KeywordAnalyzer processes tokens in a
 *	file to output a list of the locations of
 *	specified keywords in a file.
 *
 * 	@author Chelsea Greger
 *	@since v1.0
 */

package java112.analyzer;

import java.util.*;
import java.io.*;

public class KeywordAnalyzer implements Analyzer {

	// Declare instance variables
    private Map<String, List<Integer>> keywordMap;
    private Properties properties;
    private int tokenOccurence;

	/**
	 * Empty constructor for KeywordAnalyzer.
	 * Initilizes the tokenOccurence variable
	 * and the keywordMap TreeMap.
	 */
    public KeywordAnalyzer() {

        keywordMap = new TreeMap<String, List<Integer>>();
        tokenOccurence = 1;

    }


	/**
	 * Properties constructor for KeywordAnalyzer.
	 *
	 * @param properties list of properties to be used
	 *                   by the analyzer.
	 */
    public KeywordAnalyzer(Properties properties) {

        this();
        this.properties = properties;
        loadKeywordsFile();

    }


    /**
     * Loads and parses keywords file and add's each keywords
     * to the keywordMap's TreeMap with a value of an empty
     * ArrayList.
     */
    public void loadKeywordsFile() {

        //Load the function words from it's config file
		try (BufferedReader in =
                new BufferedReader(
                new FileReader(properties.getProperty("file.path.keywords")))) {

			// Define local variables
			String line = null;
			Iterator<String> i = null;
			List<String> tokens= null;

			// Read from the keywords file
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

                        //Add the keyword to the keywordMap
                        //and instantiate a new ArrayList for
                        //each keyword to hold it's number of
                        //occurences.
						keywordMap.put(token, new ArrayList<Integer>());

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
	 * Processes the token by adding the token's occurence index
	 * to the keywords occurences list if the token occurs as a
	 * keyword within the input file.
	 *
	 * @param token a single word token to be counted
	 */
	public void processToken(String token) {

        // If the token is a keyword
        if (getKeywordMap().containsKey(token)) {

            // Add it's occurence index to the appropriate
            // keyword's occurences list
            getKeywordMap().get(token).add(tokenOccurence);

        }

        // Increment the tokenOccurence counter for the
        // next processed token
        tokenOccurence++;

	}

    /**
	 * Creates, formats, and writes a list of the keywords and
	 * their occurences within the input file that was analyzed
	 * by the AnalyzeFile object.
	 *
	 * @param  inputFilePath  the file that was given by the user
	 *                        and processed
	 */
    public void writeOutputFile(String inputFilePath) {


		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir") + properties.getProperty("output.file.keyword"))))) {

            generateKeywordsList(out);

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
	 * Format's and displays each keyword's
	 * list of occurrences.
	 *
	 * @param out PrintWriter being used for output
	 */
    public void generateKeywordsList(PrintWriter out) {

        // For each keyword in the keyword map
        for (String keyword : getKeywordMap().keySet()) {

            // Retrieve the list for this keyword
            List<Integer> list = getKeywordMap().get(keyword);

            // Output the keyword
            out.println(keyword + " =");

            // If the list is empty...
            if (list.size() == 0) {

                outputEmptyOccurencesList(out);

            // If list not empty...
            } else {

                outputPopulatedOccurencesList(list, out);

            }

        }
    }

    /**
     * Outputs an occurences list that is empty.
     *
     * @param  out PrintWriter being used for output
     */
    public void outputEmptyOccurencesList(PrintWriter out) {

        // Output empty brackets
        out.println("[]");
        out.println();

    }

    /**
     * Outputs an occurences list that is populated.
     *
     * @param  list Current kewywords occurences list
     * @param  out  PrintWriter being used for output
     */
    public void outputPopulatedOccurencesList(List<Integer> list, PrintWriter out) {

        // Would normally be set in properties file,
        // but for some reason analyzer tests throw
        // Number Format Exception.
        int itemsPerLine = 10;

        // Open the brackets
        out.print("[");

        // For each occurences in the list of occurences
        for (int i = 0; i < list.size(); i++) {

            // If the occurence is last in the list,
            // output the occurence without a comma
            if (i == list.size() - 1) {

                out.print(list.get(i));

            // If the occurence is not a 10th occurence on the line,
            // output the occurence with a comma after
            } else if ((i + 1) % (itemsPerLine + 1) != 0 || i == 0) {

                out.print(list.get(i) + ", ");

            // If this occurence - 1 was the 10th occurence on the line,
            // output a new line
            } else {

                out.println();

            }

        }

        // Close the brackets.
        out.println("]");
        out.println();

    }

    /**
	 * Gets the current map of keywords.
	 * @return keywordMap
	 */
    public Map<String, List<Integer>> getKeywordMap() {

        return keywordMap;

    }

    /* KEEPING FOR LEARNING PURPOSES...
    public String formatOccurencesList(String keyword) {

        List<Integer> list = getKeywordMap().get(keyword);
        int itemsPerLine = 10 + 1;

        StringBuilder output = new StringBuilder();

        output.append(keyword + " =\n[");

        for (int i : list) {

            if ((list.indexOf(i) + 1) % (itemsPerLine + 1) != 0) {

                output.append(i);
                output.append(", ");

            } else if (list.indexOf(i) != 0) {

                output.append("\n");

            }

        }

        output.delete(output.length() - 2, output.length());
        output.append("]\n\n");

        if (list.size() == 0) {

            output.delete(0, output.length());
            output.append(keyword + " =\n[]\n\n");

        }

        return output.toString();

    }
    */

}
