/*
Author: Fazal Elahi
Student ID: 201361872
E-mail: sgfelahi@sliverpool.ac.uk
Code: VendoMachine class takes an Array of Object Items
Prends to Imitate a vending machine.
*/

import java.util.*;

public class VendingMachine extends Vendor{
  // Array List, since we don't know the value of the order.
  ArrayList<String> itemsSold= new ArrayList<>();
  ArrayList<Double> cassetteIncrements = new ArrayList<>();
  double[] validCassetteValue = {0.01,0.02,0.05,0.1,0.2,0.5,1,2};
  private double cassette;
  // private int arrayObjectLength;
  // I couldn't get it to work without telling it it's about 100 for safe measure.
  Item[] productItemsList = new Item[100];

  public VendingMachine(Item[] items) {
    // Take the Orginal and Cline it.
    //System.out.println("Test: " + arrayObjectLength);
    //arrayObjectLength = arrayObjectLength + items.length;
    //arrayObjectLength = items.length;
    for (int c=0;c < items.length;c++) {
      //System.out.println("Item PriceO: " + items[c].getPrice() + "  DescriptionO: " + items[c].getDescription());
      productItemsList[c] = new Item(items[c].getPrice(),items[c].getDescription());
      //System.out.println("Item PriceC: " + productItemsList[c].getPrice() + "  DescriptionC: " + productItemsList[c].getDescription());
    }
  }

  // Only Values Allowed.
  public void insertCoin(double coin) {
    boolean matchFound = false;
    // Test For Values.
    for (int c=0;c <= validCassetteValue.length-1;c++) { // Length Returns with 1
      if (coin == validCassetteValue[c]) {
        cassetteIncrements.add(validCassetteValue[c]); // Stores Value of Increment
        cassette = cassette + coin;
        System.out.println("Amount: $" + coin + " Successfully Added.");
        matchFound = true;
      }
    }
    // System.out.println("Amount: $" + cassette + " is New System Balance Added.");
    // Checking for Value not Matching and Throwing Expection.
    if (matchFound == false) {
      throw new IllegalArgumentException("Error - The Value Enter isn't Allowed." + '\n' + "Amount needs to Match: $0.01, $0.02, $0.05, $0.1, $0.2, $0.5, $1, $2");
    }
  }
  // Get Current Cassette Value.
  public double getCassette(){
    return this.cassette;
  }

  public double returnCoins(){
    return this.cassette = 0.0;
  }

  public String display(){
    return String.format("$%.2f", getCassette());
  }

  public Item getItem(int i) {
    /*
      Get the Value of I and check if a Item can be called. - RunTime Error.
      Else, Check if SoldItems has a object that matchs Des of the called one.
      If it's sold than deny and Throw Expection. Otherwise, record it in SoldItems and
      Than move along to pass that specific item as return value.
    */
    try {

      Double productValue = productItemsList[i].getPrice();
      String productDescription = productItemsList[i].getDescription();

      // Check the whole list of sold Items Size and Than looks for any matchs
      if (cassette >= productValue){
        cassette = cassette-productValue;
        for (int x=0;x < itemsSold.size();x++) {
          boolean checkingSold = itemsSold.contains(productDescription);
          if (checkingSold == false) {
            itemsSold.add(productDescription);
          }
          else {
            throw new ItemSoldException();
          }
        }
      }
      else {
        throw new CassetteException();
      }

      System.out.println("Product Value: $" + productValue + " Name: " + productDescription);
      System.out.println(String.format("New Balance: $%.2f",+ cassette));
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Error - Not Able to Get Products List.");
    }

    catch(NullPointerException e) {
      System.out.println("Error - Not Able to Load Product List. Item Doesn't Exist");
    }

    catch(ItemSoldException e){
      System.out.println("Sorry This Item has been Sold Out.");
    }

    catch(CassetteException e){
      System.out.println("Your Account Balance is insufficient");
    }

    return productItemsList[i];
  }

  // public static void main(String[] args) {
  //   Vendor iV = new Vendor();
  //   VendingMachine v = new VendingMachine(iV.itemsFromFile("items.example.txt"));
  //   v.insertCoin(2);
  //   System.out.println("Output:" + v.getItem(1));
  //   for (int i=0;i <= 2;i++) {
  //     System.out.println(v.getItem(10));
  //   }
  //    // Methods Testor.
  //   System.out.println("Cassette Value: " + v.getCassette());
  //   System.out.println("Cassette Format: " + v.display());
  //   System.out.println("Reset Value: " + v.returnCoins());
  // }
}
