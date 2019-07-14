package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import product.Product;
import resources.*;

public class Cart {
	
    private static ArrayList<Product> myListGood= new ArrayList<Product>();
    public static void main (String[] args) throws java.lang.Exception
    
    {
    	
    	/* JUST TEST
    	 * double input1 = 16.489;11.8125

    	// Math.round
    	System.out.println(Math.round(100 * input1) / 100.0);

    	// Decimal format
    	System.out.println(new DecimalFormat("#.##").format(input1));

    	// printf
    	System.out.printf("%.2f", input1);*/
    	
    	File file = new File(".\\src\\main\\resources\\input.txt"); 
    	List<String> lines = new ArrayList<String>();
    	Scanner sc = new Scanner(file); 
    	  
        while (sc.hasNextLine()) { 
        	lines.add(sc.nextLine()); 
      	} 
            
        //saving all the items of these shopping baskets
	    for (String line : lines) {
	    	boolean imported = false;
			//System.out.println(line);
        	String[] input= line.split(" ");
        	String[] onlyDescription = Arrays.copyOfRange(input, 1, input.length-2); //extract only description
            String joinedString = String.join(" ", onlyDescription);
            
            if(onlyDescription[0].equals("imported") || joinedString.contains("imported"))
            	imported = true;
        	//System.out.println(joinedString);     	            
        	myListGood.add(new Product( Integer.parseInt(input[0]),  joinedString, new BigDecimal(input[input.length-1]), imported));
        }
        
	    //applying sales taxes 
        ArrayList<String> foods = new ArrayList<String>(Arrays.asList(Foods.foodList)); 
        ArrayList<String> medicines = new ArrayList<String>(Arrays.asList(Medicines.medicineList)); 

	    for (Product good : myListGood) {
	    	//System.out.println(good.getFinalPrice());
	    	if(good.isImported()) {
	    		good.setInflaction(0.05); //Apply 5%
	    	}
	    	
            /*remove imported and some parts useless from description*/        	
        	String goodString=good.getDescription().replace("imported","").replaceAll(".*of ","").trim();
        	
	    	if ( !foods.contains(goodString) &&
	    		 !medicines.contains(goodString) &&
	    		 !good.getDescription().equals("book"))
	    		{
	    			good.setInflaction(0.10); //Apply 10%
	    		}
    		
	    	good.setFinalPrice();
		}
	    
	    /*OUTPUT*/
        //print the receipt details for these shopping baskets

	    printListGoodsOutput();
        printSalesTaxes();
        printTotal();
        
    }
    
    private static void printTotal() {
    	BigDecimal total = new BigDecimal(0);
        for (Product good : myListGood) {
			total= total.add(good.getFinalPrice().multiply(new BigDecimal(good.getQuantity())));
		}
        System.out.println("Total: " + total);
	}
    
    private static void printSalesTaxes() {
    	BigDecimal totalSalesTaxes = new BigDecimal(0);
        for (Product good : myListGood) {
			totalSalesTaxes = totalSalesTaxes.add( (good.getFinalPrice().subtract(good.getPrice()).multiply(new BigDecimal(good.getQuantity()))));
		}
        totalSalesTaxes = new BigDecimal(
                Math.ceil(totalSalesTaxes.doubleValue() * 100.0) / 100.0)
                .setScale(2, RoundingMode.HALF_UP);
    	System.out.println("Sales Taxes: "+totalSalesTaxes);
	}

	public void printListGoodsInput() {
        for (Product good : myListGood) {
			System.out.println(good.toStringInput());
		}
    }
    
    public static void printListGoodsOutput() {
        for (Product good : myListGood) {
			System.out.println(good.toStringOutput());
		}
    }

}
