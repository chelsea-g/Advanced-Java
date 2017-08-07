package java112.labs1;

import java.io.*;

public class LabSix {

	public static void main(String[] args) {

		//Check that the user entered 2 argurments
        if (args.length != 2) {

			//The user did not enter 2 arguments
			System.out.println("Please enter two arguments on the command line,"
					+ " an input file name and an output file name");
		} else {

			//Instantiate a lab six object
			LabSix lab6 = new LabSix();

			lab6.run(args[0], args[1]);


		}
	}

	public void run(String inputFile, String outputFile) {

		//uses MULTIPLE try-with-resources, Resources separated with ";"
        //try to use BufferedReader/PrintWriter and catch any possible exceptions
        //that they may raise
        try (BufferedReader in =
                new BufferedReader(new FileReader (inputFile));
			 PrintWriter out =
			 	new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {

            while (in.ready()) {

                out.println(in.readLine());

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
