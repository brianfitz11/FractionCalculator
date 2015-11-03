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

	public int greatestCommonDenom(int gcdNum, int gcdDenom) { //Finds the Greatest common denominator
  	  if (gcdDenom == 0) return gcdNum;
  	  return greatestCommonDenom (gcdDenom, gcdNum % gcdDenom);
    }
	
	public static boolean calculate() { //Calculate method, put in a method so I can call it unlimited times.
		
    	userInput = inputScanner.nextLine(); //Have the user input the equation or "quit" on the next line
		
		if (userInput.equals("quit")) { //If user inputs "quit" of any case, then quit the application
		   return false;
		}
	   
		String[] fullInput = userInput.split(" "); //Declare String array to store the 1st and 2nd fraction as well as the operand
		
		if(fullInput[0].contains("_")){
			String[] firstFrac = fullInput[0].split("_");
			
			String[] numDen = firstFrac[1].split("/");

			int firstFracNum = (Integer.parseInt(firstFrac[0]) * Integer.parseInt(numDen[1]))+Integer.parseInt(numDen[0]);
			
			firstFrac[0] = firstFracNum+"";
			firstFrac[1] = numDen[1];
			
			String[] secondFrac = fullInput[2].split("/");
			
			double needsSimplifying = divide(Double.parseDouble(firstFrac[0])
					, Double.parseDouble(firstFrac[1]));
			
			double needsSimplifying2 = divide(Double.parseDouble(secondFrac[0])
					, Double.parseDouble(secondFrac[1]));
			
			System.out.println(ToMixedFraction(Solve(fullInput[1], needsSimplifying, needsSimplifying2)));	

		} else if(fullInput[2].contains("_")){
			String[] secondFrac = fullInput[0].split("_");
			
			String[] numDen = secondFrac[1].split("/");
			
			int secondFracNum = (Integer.parseInt(secondFrac[0]) * Integer.parseInt(numDen[1]))+Integer.parseInt(numDen[0]);
			
			secondFrac[0] = secondFracNum+"";
			secondFrac[1] = numDen[1];
			
			String[] firstFrac = fullInput[0].split("/");
			
			double needsSimplifying = divide(Double.parseDouble(firstFrac[0])
					, Double.parseDouble(firstFrac[1]));
			
			double needsSimplifying2 = divide(Double.parseDouble(secondFrac[0])
					, Double.parseDouble(secondFrac[1]));
			
			System.out.println(ToMixedFraction(Solve(fullInput[1], needsSimplifying, needsSimplifying2)));	

		} else {
			
			String[] firstFrac = fullInput[0].split("/");
			String[] secondFrac = fullInput[2].split("/");
	
			double needsSimplifying = divide(Double.parseDouble(firstFrac[0])
					, Double.parseDouble(firstFrac[1]));
			
			double needsSimplifying2 = divide(Double.parseDouble(secondFrac[0])
					, Double.parseDouble(secondFrac[1]));	
			
			System.out.println(ToMixedFraction(Solve(fullInput[1], needsSimplifying, needsSimplifying2)));	

		}
		
	  	return true; 
	}
	
	public static double divide(double num, double den){
		return (num/den);
	}
	
	public static String ToMixedFraction(double x) {
	    int w = (int)x,
	        n = (int)(x * 4096) % 4096,
	        a = n & -n;
	    if(w==0)
		    return (n == 0 ? "" : "" + n / a + "/" + 4096 / a);
	    else
	    	return w + (n == 0 ? "" : " " + n / a + "/" + 4096 / a);
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
	