
package java112.labs2;

import java.io.*;
import java.util.*;

public class LabThree {

    // Instance variables
    private Properties properties;
    private String propertiesFilePath;

    public static void main(String[] args) {
    
        LabThree lab3 = new LabThree();

        lab3.run(args[0]);

    }

    public void loadProperties(String propertiesFilePath)  {

        properties = new Properties();

        try {

            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {

            System.out.println("Can't load the properties file");

            ioe.printStackTrace();

        } catch (Exception e) {

            System.out.println("Problem: " + e);

            e.printStackTrace();

        }

    }

    public void run(String input) {

        propertiesFilePath = input;

        loadProperties(propertiesFilePath);

        System.out.println(properties.getProperty("appearance.color"));
        System.out.println(properties.getProperty("mood.happy"));
        System.out.println(properties.getProperty("home.maxhouseholdmembers"));

    }



}
