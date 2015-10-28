/************************************************
 * Project 1 - Fraction Calculator				*
 * Created by Ben Poile							*
 * Period 1 - Mr. Bradley's AP Computer Science	*
 ************************************************/

import java.util.Scanner;

public class FractionCalculator {
	public static Scanner scanTest = new Scanner(System.in); //Scanner to user for fractions
	public static Fraction frac1 = new Fraction(); //Create the First Fraction
    public static Fraction frac2 = new Fraction(); //Create the Second Fraction
    public static Fraction fracResult = new Fraction(); //Create the Fraction to be output as the result
    public static String strInput; //Create the output string
	
    public static void Line() { //Method to create a line that separates when a user is done with their operation
    	System.out.println("\n");
    	
    	for(int i=1; i <= 75; i++) {
    		System.out.print("=");
    	}
    	
    	System.out.println("\n");
    }
    
    public static boolean calculate() { //Calculate method, put in a method so I can call it unlimited times.

    	fracResult.setWhole(0);
    	fracResult.setNumerator(0);
    	fracResult.setDenominator(0);
    	
    	System.out.println("Enter your calculation on the line below! <WHOLENUMBER>_<NUMERATOR>/<DENOMINATOR> <OPERATOR> <WHOLENUMBER>_<NUMERATOR>/<DENOMINATOR>"); //Show the user how to input their problem
    	System.out.println("Or type \"Quit\" to quit!"); //Tell the user that they can quit
    	
    	strInput = scanTest.nextLine(); //Have the user input the equation or "quit" on the next line
	   
	   if (strInput.equalsIgnoreCase("quit")) { //If user inputs "quit" of any case, then quit the application
		   return false;
	   }
	   
	   String[] fullInput = strInput.split(" "); //Declare String array to store the 1st and 2nd fraction as well as the operand

	   if (fullInput.length != 3) { // Check to see 3 items in the String Array (two fractions and one operand)
		   System.out.println("Invalid equation");
	   }
	   else {
		   frac1.fromString(fullInput[0]); //Convert the first string in the array to the first fraction
		   frac2.fromString(fullInput[2]); //Convert the third string in the array to the second fraction
		   
    	   switch (fullInput[1]) {  //Use the second array string as the operator to set the math operation to carry out
	            case "+":
	                fracResult = frac1.Add(frac2); //Add or
	                break;
	            case "-":
	                fracResult = frac1.Subtract(frac2); //Subtract or
	                break;
	            case "*":
	                fracResult = frac1.Multiply(frac2); //Multiply or
	                break;
	            case "/":
	                fracResult = frac1.Divide(frac2); //Divide or
	                break;
	            default:
		       		System.out.println("Invalid operand"); //Output invalid operand
	    	   }
	       
    	    System.out.println("Answer = " + fracResult.toString()); //Output "Answer = " then call the toString method in the Fraction class.
	       	Line(); //Call the Line method on line 17

	   	}
	   
      	return true; //Call the calculate method that this is in so that when the user is done with the problem they can do another
	}
    
	public static void main(String[] args) { //Main
		System.out.println("Welcome to Ben's Fraction Calculator"); //Welcome to my Calculator
		
		while (calculate() == true);
	}
}
