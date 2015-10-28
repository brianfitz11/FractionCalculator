import java.util.Scanner;

public class Main {
	private static Scanner inputScanner = new Scanner(System.in);
	private static String userInput;
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Fraction Calculator");
		while(calculate() == true){
			System.out.println("Input problem or type \"quit\" to quit.");
			// 1/2 + 3/4
			userInput = inputScanner.nextLine();
			
		}
	}
	
	public static boolean calculate() { //Calculate method, put in a method so I can call it unlimited times.
		
    	userInput = inputScanner.nextLine(); //Have the user input the equation or "quit" on the next line
		
		if (userInput.equals("quit")) { //If user inputs "quit" of any case, then quit the application
		   return false;
		}
	   
		String[] fullInput = userInput.split(" "); //Declare String array to store the 1st and 2nd fraction as well as the operand
		
		String[] firstFrac = fullInput[0].split("/");
		String[] secondFrac = fullInput[2].split("/");


		double needsSimplifying = divide(Double.parseDouble(firstFrac[0])
				, Double.parseDouble(firstFrac[1]));
		
		double needsSimplifying2 = divide(Double.parseDouble(secondFrac[0])
				, Double.parseDouble(secondFrac[1]));	
		
		if (fullInput.length != 3) { // Check to see 3 items in the String Array (two fractions and one operand)
			
			System.out.println("Invalid equation");
			
		} else {
			
			System.out.println(Solve(fullInput[1], needsSimplifying, needsSimplifying2));
			
	   	}
		
		for(int i = 0; i < fullInput.length; i++){
			
			System.out.println(fullInput[i]);
			
		}
		
	  	return true; //Call the calculate method that this is in so that when the user is done with the problem they can do another
	}
	
	public static double divide(double num, double den){
		return (num/den);
	}
	
	public static double Solve(String operator, double frac1, double frac2){
		if(operator.equals("+")) {
			return (frac1 + frac2);
		} else if (operator.equals("-")) {
			return (frac1 - frac2);
		} else if (operator.equals("*")) {
			return (frac1 * frac2);
		} else if (operator.equals("/")) {
			return (frac1 / frac2);
		} else {
			System.err.format("\n Not a valid operator");
			System.exit(0);
			return 0;
		}
	}
}
	