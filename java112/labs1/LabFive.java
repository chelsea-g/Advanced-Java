package java112.labs1;

import java.io.*;

public class LabFive {

	private PrintWriter out;
	private String fileName;
	private String message;

    public static void main(String[] args) throws java.io.IOException {

		//Check that the user entered 2 argurments
        if (args.length != 2) {

			//The user did not enter 2 arguments
			System.out.println("Please enter two arguments on the command"
					+ "line, a file name and a message");

        } else {

			//The user entered 2 arguments
			//Instantiate a LabFive object
			LabFive lab5 = new LabFive();

			lab5.fileName = args[0];
			lab5.message = args[1];

			//Call the run method to write to the file
			lab5.run(lab5.fileName, lab5.message);

        }

    }

	private void run(String fileName, String message) throws java.io.IOException {

		out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

		//Write the user's message to the file
		out.println(message);

		//Close the file
		out.close();


	}

}
