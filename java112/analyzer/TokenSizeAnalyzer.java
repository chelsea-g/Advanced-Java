/**
 * TokenSizeAnalyzer processes tokens of a file
 * to produce a list of the token lengths of each
 * token next to how many tokens have that length.
 *
 * It also produces a horizontal histogram
 * and a vertical histogram to visualize
 * the token length data.
 *
 * @author Chelsea Greger
 * @since v1.0
 */

package java112.analyzer;

import java.util.*;
import java.io.*;

public class TokenSizeAnalyzer implements Analyzer {

    //Declare instance variables
    private Properties properties;
    private int maximumSize;
    private Map<Integer, Integer> tokenSizes; // key = length of each token
                                              // value = number of tokens with
                                              // key's length

    /**
     * Empty constructor for TokenSizeAnalyzer.
     * Initializes the tokenSizes TreeMap.
     */
    public TokenSizeAnalyzer() {

        tokenSizes = new TreeMap<Integer, Integer>();

    }

    /**
	 * Properties constructor for TokenSizeAnalyzer.
	 *
	 * @param properties list of properties to be used
	 *                   by the analyzer.
	 */
    public TokenSizeAnalyzer(Properties properties) {

        this();
        this.properties = properties;

    }

    /**
     * Processes the token by adding the token's size to the
     * tokenSizes map. If the token already exists in the map,
     * it's counter is incremented by 1.
     *
     * @param token a single word token to be counted
     */
	public void processToken(String token) {

        int key = token.length();

        // If the token size is already in the Map, increment the counter for that token size.
        if (getTokenSizes().containsKey(key)) {

            getTokenSizes().put(key, getTokenSizes().get(key) + 1);

        // If the token size is not yet in the Map, add the token size and initialize counter to 1.
        } else {

            getTokenSizes().put(key, 1);

        }

	}

    /**
     * Generates a list of all token sizes within a file next to the
     * count of tokens that have that character length.
     *
     * @param out PrintWriter being used for output
     */
    public void generateTokenSizeList(PrintWriter out) {

        // Output every tokenSize with a tab and the tokenSize's count,
        // each on a new line
        for (Integer key : getTokenSizes().keySet()) {

            out.println(key + "\t" + getTokenSizes().get(key));

        }

    }

    /**
     * Generates a vertical histogram representing the token
     * sizes within the input file.
     *
     * @param out PrintWriter being used for output
     */
    public void generateVerticalHistorgram(PrintWriter out) {

        int maxSymbols = Integer.parseInt(properties.getProperty("vertical.max.symbols"));

        // Space it out from other output
        out.print("\n\n\n\n");

        // Until the printerLineNumber reaches 0... (initialized at the max ammount)
        for (int printerLineNumber = maxSymbols; printerLineNumber >= 0; printerLineNumber--) {

            outputVerticalHistogramPrinterLine(out, maxSymbols, printerLineNumber);

        }

    }

    /**
     * Prints a single line that builds part of the vertical histogram.
     *
     * @param out                   PrintWriter being used for output
     * @param maxSymbols            maximum number of symbols to be printed in the
     *                              histogram
     * @param printerLineNumber     current line that the printer is printing
     */
    public void outputVerticalHistogramPrinterLine(PrintWriter out, int maxSymbols, int printerLineNumber) {

        int symbolCount = 0;
        String symbol = " " + properties.getProperty("vertical.histogram.symbol") + " ";
        String blank = "   ";

        // For each size in tokenSizes
        for (Integer key: getTokenSizes().keySet()) {

            // Calculate the number of symbols to output for the tokenSize
            symbolCount = calculateSymbolCount(getTokenSizes().get(key), maxSymbols);

            // If the printer is within the range of this tokenSize's
            // symbolCount, then the printer will output a symbol.
            if ((symbolCount >= printerLineNumber) && printerLineNumber > 0) {

                out.print(symbol);

            // If the printer has reached the end of the vertical line,
            // output the tokenSize that the vertical line represents
            } else if (printerLineNumber == 0) {

                // If the tokenSize is more than 2 digits
                // adjust the spacing between the tokenSizes
                if (String.valueOf(key).length() > 1) {

                    out.print(" " + key);

                } else {

                    out.print(" " + key + " ");

                }

            // If the printer is out of range of this tokenSize's
            // symbolCount, then the printer will output a blank.
            } else {

                out.print(blank);

            }

        }

        // Go to the next printer line
        out.println();

    }

    /**
     * Generates a horizontal histogram representing the token
     * sizes within the input file.
     *
     * @param out PrintWriter being used for output
     */
    public void generateHorizontalHistogram(PrintWriter out) {

        int symbolCount = 0;
        int symbol = Integer.parseInt(properties.getProperty("horizontal.max.symbols"));

        // Space it out from other output
        out.print("\n\n\n\n");

        for (Integer key: getTokenSizes().keySet()) {

            // Calculate this keys symbol count
            symbolCount = calculateSymbolCount(getTokenSizes().get(key), symbol);

            // Print the token size along with the symbolCountString
            out.println(key + "\t" + generateSymbolCountString(symbolCount));

        }

    }

    /**
     * Generates the string that will be printed for each token size in the
     * horizontal histogram.
     *
     * @param  symbolCount          maximum number of symbols to be printed for
     *                              the histogram
     * @return symbolCountString    string that represents the token size in the
     *                              horizontal histogram
     */
    public String generateSymbolCountString(int symbolCount) {

        String symbolCountString = "";
        String symbol = properties.getProperty("horizontal.histogram.symbol");

        // Generate the symbolCountString
        // (symbols repeated symbolCount number of times)
        for (int i = 0; i < symbolCount; i++) {

            symbolCountString += symbol;

        }

        return symbolCountString;

    }

    /**
     * Calculates the number of symbols that will be printed next to/
     * above each tokenSize in the histogram.
     *
     * @param   tokenSize       current token size to calculate symbol
     *                          count for
     * @param   maxSymbolCount  maximum number of symbols to be printed for
     *                          the histogram
     * @return  symbolCount     number of symbols that will be printed
     *                          next to/above each tokenSize in the histogram.
     */
    public int calculateSymbolCount(Integer tokenSize, int maxSymbolCount) {

        int mostCommonSize = getMaximumSize();
        double symbolPercent = 0.0;
        int symbolCount = 0;

        // Calculate the tokenSize's symbol count based on the percentage of
        // occurances * mostCommonSize
        symbolPercent = tokenSize / (double) mostCommonSize;

        // Calculate the symbolCount by finding what value that percent
        // represents in symbols
        symbolCount = (int) Math.round(symbolPercent * maxSymbolCount);

        // If the percentage turned out to be less than 1 symbol, symbolCount
        // will be 0 so let 0 default to 1 symbol.
        if (symbolCount == 0) {

            symbolCount = 1;

        }

        return symbolCount;

    }

    /**
	 * Creates, formats, and writes the tokenSizeList, horizontal histogram,
	 * and vertical histogram for the input file that was analyzed by the
	 * AnalyzeFile object.
	 *
	 * @param  inputFilePath  		 the file that was given by the user
	 *                          	 and processed
	 */
    public void writeOutputFile(String inputFilePath) {

		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir") + properties.getProperty("output.file.token.size"))))) {

			generateTokenSizeList(out);
            generateHorizontalHistogram(out);
            generateVerticalHistorgram(out);

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
     * Gets the current map of tokenSizes.
     *
     * @return tokenSizes
     */
    public Map<Integer, Integer> getTokenSizes() {

        return tokenSizes;

    }

    /**
     * Uses the tokenSizes map to find the token size with the most
     * occurances in the file.
     *
     * @return maximumSize token size with the most occurances
     *                     in the file.
     */
    public int getMaximumSize() {

        for (Integer key: getTokenSizes().keySet()) {

            // If the current maximumSize is less than the
            // new size, define the new size as the maximumSize
            if (getTokenSizes().get(key) > maximumSize) {

                maximumSize = getTokenSizes().get(key);

            }

        }

        return maximumSize;

    }

}
