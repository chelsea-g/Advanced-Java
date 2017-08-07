package java112.labs1;

import java.util.*;

public class ListDemo {

    public static void main(String[] args) {

        // Instantiate a list
        // the second <string> is not needed but can be used for explicity
        List<String> myList = new ArrayList<String>();

        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.add("four");

        System.out.println(myList);

        if (myList.contains("one")) {
            System.out.println("One is in the list");
        }

        // Enhanced for loop
        // For Each String (reffered to as myString) within myList, do something
        for (String myString : myList) {

            System.out.println(myString);

        }

        // Instantiate a set
        // Duplicates are not allowed
        Set<String> mySet = new TreeSet<String>();

        mySet.add("one");
        mySet.add("one");
        mySet.add("two");
        mySet.add("three");
        mySet.add("four");

        System.out.println(mySet);

        if (mySet.contains("one")) {
            System.out.println("One is in the list");
        }

        // Enhanced for loop
        // For Each String (reffered to as myString) within myList, do something
        for (String myString : mySet) {

            System.out.println(mySet);

        }



    }

}
