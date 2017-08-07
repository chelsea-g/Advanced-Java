package java112.labs1;

/** LabThree built for Lab 3 - Advanced Java Programming
 *  @author cgreger
 */

public class LabThree {
    
    /** Main method to run the LabThree
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        //Instantiate a LabThree object
        LabThree test = new LabThree();
        
        //Validate user input
        if (args.length != 1) {
            
            System.out.println("Please enter one argument on the command line");
            
        } else {
            
            test.run(args[0]);
            
        }
        
    }
  
    /** 
     * Output the user's input
     * @param test The input string to be displayed
     */
    public void run(String test) {
        
        System.out.println("input: " + test);
        
    }
}