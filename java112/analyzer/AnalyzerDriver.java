/**
 * Text Inspector analyzes any text file to extract every unique
 * token within the file. A text file of the list of unique tokens
 * within the file is produced as well as a separate text file with
 * the summary report of the input file.
 * <p>
 * The summary report contains the name of the application, the
 * program author, the author's email, the path of the input file,
 * the date the file was analyzed, and the total number of tokens
 * in the file. The total token count includes unique and non-unique
 * tokens.
 *
 * @author Chelsea Greger
 * @since v1.0
 *
 */

package java112.analyzer;

public class AnalyzerDriver {

	/**
	 * The main method to start Text Inspector.
	 *
	 * @param args - The input file given through the command line
	 */
	public static void main(String[] args) {

		// Instantiate an AnalyzeFile object
		AnalyzeFile analyzer = new AnalyzeFile(args[1]);

		// Run the analyzer
		analyzer.runAnalysis(args);

	}

}
