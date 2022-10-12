/*
Author: Fazal Elahi
Student ID: 201361872
E-mail: sgfelahi@sliverpool.ac.uk
Code: Vendo class takes a input from a file type and
produces an array of charactors.
*/
import java.util.*;
import java.io.*;

public class Vendor{
  // Needs to Return to IO
  public static Item[] itemsFromFile(String fileName) {
    /*
      Take the First Line.
      Break it by ;
      Look for Double, Convert it, Store it and Discard any other
      Anything after ; is stored as Product Name and any Numbers from it are removed.
    */
    List<String> stringValueList = new ArrayList<String>();
    List<String> stringProductNameList = new ArrayList<String>();
    List<Double> productValue = new ArrayList<Double>();
    // Creates a new Array with the Array List Size.
    String text;

    try {
      File fileHandler = new File(fileName);
      Scanner fileReader = new Scanner(fileHandler);

      while ((text = fileReader.nextLine()) != null) {
        // Loop Until the Fill Ends. // Avoids Spaced Etc.
        String separatedWords[] = text.split(";"); // FA becomes "F","A"
        for (int counter = 0;counter < separatedWords.length;counter++) {
          // System.out.println(separatedWords[counter]); // Spliting Testing
          String numbersOnly = separatedWords[counter].replaceAll("[^\\d.]", ""); // Turns ABC00.00 to 00.00
          if (numbersOnly.matches("-?\\d+(\\.\\d+)?")) {
            // Found a Double.
            stringValueList.add(numbersOnly);
            //Double num = Double.parseDouble(string)
          } else {
            stringProductNameList.add(separatedWords[counter]);
          }
        }
      }
    }
    // Checkes for Empty File.
    catch (NoSuchElementException e) {
      System.out.println("End of File Reached or Empty File Detected.");
    }
    // File Not Found.
    catch (FileNotFoundException e) {
      System.out.println("Error - File Not Found.");
    }

    //Got All the Info From File. Converting Numerical String Values into Double
    try {
      // double productValue = Double.parseDouble(text);
      for (int counter =0;counter < stringValueList.size();counter++) {
        double cproductValue = Double.parseDouble(stringValueList.get(counter));
        productValue.add(cproductValue);
        //System.out.println(cproductValue); // Testing Converting.
      }
    }
    catch(NumberFormatException e) {
      System.out.println("Error - Coverting Prices Failed.");
    }
    catch(IndexOutOfBoundsException e){
      System.out.println("Error - Coverting Prices Failed.");
    }

    int Arraysize = (stringValueList.size()+stringProductNameList.size())/2;
    // System.out.println("Test: " + Arraysize);
    // System.out.println("Val Size: " + stringValueList.size());
    // System.out.println("Val Size: " + stringProductNameList.size());
    Item[] item = new Item[Arraysize];

    for (int c =0;c < Arraysize;c++) {
      item[c] = new Item(productValue.get(c), stringProductNameList.get(c));
      // System.out.println("Counter: " + c + " Item: " + item[c]); // Testing Items and It's Pos
    }
    return item;
  }
  // // Tessting Console - Different Files
  // public static void main(String[] args) {
  //   Vendor v = new Vendor();
  //   v.itemsFromFile("items.example.txt");
  // }
}
