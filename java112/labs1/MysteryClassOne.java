package java112.labs1;

/** MysteryClassOne built for Lab 2 - Advanced Java Programming
 *  @author cgreger
 */

public class MysteryClassOne {
    
    /** Main method to run the MysteryClassOne
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        MysteryClassOne labOne = new MysteryClassOne();
        
        System.out.println("The number is: " + labOne.mysteryMethodOne());
        
        System.out.println("The first item in the array is: " + args[0]);
        System.out.println("The second item in the array is: " + args[1]);
        
    }

    /** 
     * @return an integer
     */
    public int mysteryMethodOne() {
        
        return 1;
        
    }

}
