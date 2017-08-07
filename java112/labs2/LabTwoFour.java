package java112.labs2;

import java.util.*;
import java.io.*;

public class LabTwoFour {

    private Map<String, Integer> hashMap;
    private Map<String, Integer> treeMap;

    public static void main(String[] args) {
    
        LabTwoFour lab4 = new LabTwoFour();
        
        lab4.run();
    
    }
    
    public void run() {
    
        hashMap = new HashMap<String, Integer>();
       
        
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        hashMap.put("Four", 4);
        hashMap.put("Five", 5);
        
        treeMap = new TreeMap<String, Integer>(hashMap);
        
        
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
        
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            System.out.println(key + " --> " + value);
        
        }
        
        System.out.println(hashMap);
        
        if (hashMap.containsKey("Three")) {
        
            System.out.println("Three --> " + hashMap.get("Three"));
        
        } else {
        
            System.out.println("Key not found in hashMap");
        
        }
        
        System.out.println(treeMap);
        
    
    }

}