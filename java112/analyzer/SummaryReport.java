/**
 *	Generates summary_report.txt with this input file's
 *	unique report. Report includes the applications name,
 *	the author's name, the author's email, the timestamp
 *	of when the file was written, the absolute path to the
 *	input file, and the total number of individual tokens
 *	processed by the AnalyzeFile object.
 *
 * 	@author Chelsea Greger
 *	@since v1.0
 */

package java112.analyzer;

import java.util.*;
import java.io.*;

public class SummaryReport implements Analyzer {

	// Declare instnace variables
	private int totalTokensCount;
	private Properties properties;

	/**
	 * Empty constructor for SummaryReport
	 */
	public SummaryReport() {}

	/**
	 * Properties constructor for SummaryReport.
	 *
	 * @param  properties properties list for the analyzers
	 */
	public SummaryReport(Properties properties) {

		this.properties = properties;

	}

	/**
	 * Processes the token given by incrementing this
	 * SummaryReport's current token count total
	 *
	 * @param token a single word token to be counted
	 */
	public void processToken(String token) {

		totalTokensCount++;

	}

	/**
	 * Creates, formats, and writes a report of the input file
	 * that was analyzed by the AnalyzeFile object
	 *
	 * @param  inputFilePath  		 the file that was given by the user
	 *                          	 and processed
	 */
	public void writeOutputFile(String inputFilePath) {

		// Create a Calandar object and Date object to add the
		// timestamp for this report
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date analyzedOn = calendar.getTime();

		// Create a file object to add the absolute path of
		// the input file for this report
        File absolutePath = new File(inputFilePath);

		// Create and write summary to summary_report.txt
		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(
				new FileWriter(properties.getProperty("output.dir")
				+ properties.getProperty("output.file.summary"))))) {

			out.println("Application: " + properties.getProperty("application.name"));
            out.println("Author: " + properties.getProperty("author"));
			out.println("Email: " + properties.getProperty("author.email.address"));

		    try {

                out.println("Input file: " + absolutePath.getAbsolutePath());

            } catch (SecurityException securityException) {

                System.out.println("A required system property value could not "
                        + "be accessed.");

                securityException.printStackTrace();
            }

			out.println("Analyzed on: " + analyzedOn);
			out.println("Total token count: " + getTotalTokensCount());

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
	 * Gets the current token count total for this report.
	 * @return totalTokensCount token count total
	 */
	public int getTotalTokensCount() {

		return totalTokensCount;

	}

}
