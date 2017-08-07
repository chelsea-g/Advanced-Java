/**
 * 	Interface that describes how an object processes tokens and
 * 	what information is written to a file
 *
 * 	@author Chelsea Greger
 *	@since v1.0
 */

package java112.analyzer;


public interface Analyzer {

	/**
	 * Processes the token
	 *
	 * @param token single piece of text that was filtered by a
	 *              token generator
	 */
	void processToken(String token);

	/**
	 * Creates, formats, and writes to the output file
	 *
	 * @param  inputFilePath  the file that was given by the user
	 *                        and processed
	 */
	void writeOutputFile(String inputFilePath);

}
