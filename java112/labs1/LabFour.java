package java112.labs1;

import java.io.*;

/** LabFour built for Lab 4 - Advanced Java Programming
 *  @author cgreger
 */

public class LabFour {
    
    /** Main method to run the LabFour
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        //Instantiate a LabFour object
        LabFour test = new LabFour();
        
        //Validate user input
        if (args.length != 1) {
            
            System.out.println("Please enter one argument on the command line");
            
        } else {
            
            test.run(args[0]);
            
        }    
        
    }
  
    /** 
     * Open a file for reading, iterate over each line
     * of the file and output each line to the console
     * and output the user's given file line by line
     * @param test The input file name to be displayed
     */
    public void run(String test) {
        
        //uses try-with-resources 
        //try to use BufferedReader and catch any possible exceptions 
        //that BufferedReader may raise
        try (BufferedReader in = 
                new BufferedReader(new FileReader (test))) {
            
            while (in.ready()) {
             
                System.out.println(in.readLine());
                
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