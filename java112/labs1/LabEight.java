package java112.labs1;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.io.*;

public class LabEight {

	private Set<String> set;

	public static void main(String[] args) {

		if (args.length != 1) {

			System.out.println("Please enter one argument on the command line, "
					+ "an output file name");

		} else {

			LabEight lab8 = new LabEight();

			lab8.run(args[0]);

		}

	}

	public void run(String outputFile) {

		set = new TreeSet<String>(Arrays.asList("one", "one", "five", "two",
				"four", "two", "three", "three", "four", "three"));

		this.writeSetToOutputFile(outputFile);

	}

	public void writeSetToOutputFile(String outputFile) {

		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {

			for(String word : set) {

				out.println(word);

			}

		} catch (FileNotFoundException fileNotFoundException) {

            //output context to the raised exception
            System.out.println("There was a problem opening the file.");

            //printStackTrace() will out put the errors to the console
            //that raised the exception
            fileNotFoundException.printStackTrace();

        } catch (IOException ioException) {

            System.out.println("There was a problem reading the file.");

            ioException.printStackTrace();

        } catch (Exception exception) {

            System.out.println("There was some other problem.");

            exception.printStackTrace();

        }

	}

}
