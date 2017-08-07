package java112.labs1;

import java.util.List;
import java.util.Arrays;
import java.io.*;

public class LabSeven {

	private List<String> list;

	public static void main(String[] args) {

		if (args.length != 1) {

			System.out.println("Please enter one argument on the command line, "
					+ "an output file name");

		} else {

			LabSeven lab7 = new LabSeven();

			lab7.run(args[0]);

		}

	}

	public void run(String outputFile) {

		list = Arrays.asList("one", "two", "three", "four",
				"five", "six", "seven", "eight", "nine", "ten");

		this.writeListToOutputFile(outputFile);

	}

	public void writeListToOutputFile(String outputFile) {

		try (PrintWriter out =
				new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {

			for(String word : list) {

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
