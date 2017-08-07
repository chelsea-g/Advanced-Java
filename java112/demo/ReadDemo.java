
package java112.demo;

import java.io.*;

public class ReadDemo {
    /**
     * main method for starting the program
     * @param args the command line arguments
    **/
    public static void main(String[] args) {

        ReadDemo demo = new ReadDemo();
        demo.run();

    }

    /**
     * Open a file for reading, iterate over each line
     * of the file and output each line to the console
    **/
    public void run() {

        //Normally use try with resources instead of this stuff...
        
        BufferedReader in = null;

        try {

            in = new BufferedReader(new FileReader("testFile.tst"));

            while (in.ready()) {

                System.out.println(in.readLine());

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

        //if no exceptions are raised then run final code
        } finally {

            if (in != null) {

                try {

                    //close the read file
                    in.close();

                } catch (IOException ioException) {

                    System.out.println("There was a problem closing the file.");
                    ioException.printStackTrace();

                } catch (Exception exception) {

                    System.out.println("There was some other problem.");
                    exception.printStackTrace();

                }

            }

        }

    }

}
