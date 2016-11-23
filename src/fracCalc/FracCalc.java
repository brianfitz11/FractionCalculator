package fracCalc;

import java.util.Scanner;

public class FracCalc {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to the fraction calculator! Enter your fraction or 'quit' to quit.");

		String input = console.nextLine();

		while (input.compareToIgnoreCase("quit") != 0) { // as long as the user doesn't type "quit" run the program and return the answers!
			String answer = produceAnswer(input);
			System.out.println(answer);
			input = console.nextLine();
		}
		console.close();
	}

	public static String produceAnswer(String input) { //the majority of the logic is done here, breaking the user input into computer readable variables (1/2 to 1, 2) 
		int spaceIndex = input.indexOf(" ");
		int spaceIndex2 = spaceIndex + 2;
		int operIndex = spaceIndex + 1;
		
		String operator = input.substring(operIndex, operIndex + 1); // set operator to string. This will be +, -, / or *
		
		String frac1 = input.substring(0, spaceIndex); // full fraction 1 as string
		String frac2 = input.substring(spaceIndex2 + 1); // full fraction 2 as string
		
		String frac1Whole = null; // initialize Strings
		String frac1Num = null;
		String frac1Den = "1";

		int f1_index = frac1.indexOf("_"); //index of the '_'
		int frac1SIndex = frac1.indexOf("/"); //index of the '/'

		if (frac1SIndex != -1) { //check if fraction1 has a whole number
			if (f1_index != -1) { // see if has an underscore
				frac1Whole = frac1.substring(0, f1_index); //set whole number
				if (frac1SIndex != -1) {
					frac1Num = frac1.substring(f1_index + 1, frac1SIndex);
					frac1Den = frac1.substring(frac1SIndex + 1);
				}
			} else {
				frac1Num = frac1.substring(0, frac1SIndex);
				frac1Den = frac1.substring(frac1SIndex + 1);
			}
		} else { // if no '_', set the entirety to the wholeNumber (i.e. 33 has no _ so make frac1Whole 33)
			frac1Whole = frac1;
		}

		String frac2Whole = null;
		String frac2Num = null;
		String frac2Den = "1";
		
		int frac2_index = frac2.indexOf("_");
		int frac2SIndex = frac2.indexOf("/");

		if (frac2SIndex != -1) { // check is fraction 2 has/is a whole number
			if (frac2_index != -1) { // check to see if it has an underscore
				frac2Whole = frac2.substring(0, frac2_index);
				if (frac2SIndex != -1) {
					frac2Num = frac2.substring(frac2_index + 1, frac2SIndex);
					frac2Den = frac2.substring(frac2SIndex + 1);
				}
			} else { 
				frac2Num = frac2.substring(0, frac2SIndex);
				frac2Den = frac2.substring(frac2SIndex + 1);
			}
		} else { // if no '_', set the entirety to the wholeNumber 
			frac2Whole = frac2;
		}

		String result = null;
		//after setting variables to their appropriate values and types, do the math
		if (operator.equals("+")) { // add
			result = add(frac1Whole, frac1Num, frac1Den, frac2Whole, frac2Num, frac2Den); //call the right method and pass the right values
		} else if (operator.equals("-")) { //subtract
			result = subtract(frac1Whole, frac1Num, frac1Den, frac2Whole, frac2Num, frac2Den);//call the right method and pass the right values
		} else if (operator.equals("*")) { //multiply
			result = multiply(frac1Whole, frac1Num, frac1Den, frac2Whole, frac2Num, frac2Den);//call the right method and pass the right values
		} else if (operator.equals("/")) { //divide
			result = divide(frac1Whole, frac1Num, frac1Den, frac2Whole, frac2Num, frac2Den);//call the right method and pass the right values
		}
		
		int resultSlashIndex = result.indexOf("/"); //make sure theres a / in the result
		
		if (resultSlashIndex == -1) { // if there is one, return it how it is. otherwise simplify
			return result;
		}
		
		result = simplify(result); //simplify
		
		return result; //return result
	}



	public static String add(String frac1Whole, String frac1Num, String frac1Den, String frac2Whole, String frac2Num, String frac2Denominator) {
		
		//Create variables
		String result = null;
		int wResultInt = 0;
		int w1AsInt = 0;
		int frac1NumInt = 0;
		int frac1DenInt = 0;
		int w2AsInt = 0;
		int frac2NumInt = 0;
		int frac2DenInt = 0;

		if (frac1Whole != null) { //if there is a whole number in fraction 1
			w1AsInt = Integer.parseInt(frac1Whole); //set to integer from string
		}
		if (frac1Num != null) { //if numerator exists 
			frac1NumInt = Integer.parseInt(frac1Num);
			frac1DenInt = Integer.parseInt(frac1Den);
		}
		
		if (frac1Whole != null && frac1Num != null) { //if there is a whole number and a numerator in fraction 1
			if (w1AsInt < 0) { //check if negative
				frac1NumInt = frac1NumInt * -1;
			}
			frac1NumInt = w1AsInt * frac1DenInt + frac1NumInt; //set values
		}
		
		if (frac2Whole != null) { //if fraction 2  has a whole number, set value
			w2AsInt = Integer.parseInt(frac2Whole);
		}
		
		if (frac2Num != null) { // if the numerator exists,set values
			frac2NumInt = Integer.parseInt(frac2Num);
			frac2DenInt = Integer.parseInt(frac2Denominator);
		}
		
		if (frac2Whole != null && frac2Num !=  null) { //if there is a whole number and a numerator in fraction 2
			if (w2AsInt < 0) { //check if negative
				frac2NumInt = frac2NumInt * -1;
			}
			frac2NumInt = w2AsInt * frac2DenInt + frac2NumInt; //set value
		}
		
		if (frac1Num == null && frac2Num == null) { //if whole number and numerator doesn't exist
			wResultInt = w1AsInt + w2AsInt; //set values
			result = Integer.toString(wResultInt);
			return result; // and return
		}
	
		if (frac1Num == null && frac2Num != null) { // if f1 has a numerator but f2 doesn't, set values accordinly
			frac1NumInt = w1AsInt * frac2DenInt;
			frac1DenInt = frac2DenInt; 
		}
		if (frac2Num == null && frac1Num != null) { //and vice versa
			frac2NumInt = w2AsInt * frac1DenInt;
			frac2DenInt = frac1DenInt;
		}
	 
		//set values
		int intResultCommonDenominator = frac1DenInt;
		int intResultfrac1Num = frac1NumInt;
		int intResultfrac2Num = frac2NumInt;
		
		if (frac1DenInt != frac2DenInt) { // get common denominator
			intResultCommonDenominator = frac1DenInt * frac2DenInt;
			intResultfrac1Num = frac1NumInt * frac2DenInt;
			intResultfrac2Num = frac2NumInt * frac1DenInt;
		}
		
		int intResultNumerator = intResultfrac1Num + intResultfrac2Num; //set final Numerator
		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator); //construct answer as String
		
		return result; //return string form of answer
	}
 
	
	// all code works the same as in addition, just subtracting 
	public static String subtract(String frac1Whole, String frac1Num, String frac1Den, String frac2Whole, String frac2Num, String frac2Denominator) {
		String result = null;
		int wResultInt = 0;
		int w1AsInt = 0;
		int frac1NumInt = 0;
		int frac1DenInt = 0;
		int w2AsInt = 0;
		int frac2NumInt = 0;
		int frac2DenInt = 0;

		if (frac1Whole != null) {
			w1AsInt = Integer.parseInt(frac1Whole);
		}
		if (frac1Num != null) {
			frac1NumInt = Integer.parseInt(frac1Num);
			frac1DenInt = Integer.parseInt(frac1Den);
		}

		if (frac1Whole != null && frac1Num != null) {
			if (w1AsInt < 0) {
				frac1NumInt = frac1NumInt * -1;
			}
			frac1NumInt = w1AsInt * frac1DenInt + frac1NumInt;
		}

		if (frac2Whole != null) {
			w2AsInt = Integer.parseInt(frac2Whole);
		}
		if (frac2Num != null) {
			frac2NumInt = Integer.parseInt(frac2Num);
			frac2DenInt = Integer.parseInt(frac2Denominator);
		}

		if (frac2Whole != null && frac2Num != null) {
			if (w2AsInt < 0) {
				frac2NumInt = frac2NumInt * -1;
			}
			frac2NumInt = w2AsInt * frac2DenInt + frac2NumInt;
		}

		if (frac1Num == null && frac2Num == null) {
			wResultInt = w1AsInt - w2AsInt;
			result = Integer.toString(wResultInt);
			return result;
		}
		
		if (frac1Num == null && frac2Num != null) {
			frac1NumInt = w1AsInt * frac2DenInt;
			frac1DenInt = frac2DenInt;
		}
		if (frac2Num == null && frac1Num != null) {
			frac2NumInt = w2AsInt * frac1DenInt;
			frac2DenInt = frac1DenInt;
		}

		int intResultCommonDenominator = frac1DenInt;
		int intResultfrac1Num = frac1NumInt;
		int intResultfrac2Num = frac2NumInt;

		if (frac1DenInt != frac2DenInt) {
			intResultCommonDenominator = frac1DenInt * frac2DenInt;
			intResultfrac1Num = frac1NumInt * frac2DenInt;
			intResultfrac2Num = frac2NumInt * frac1DenInt;

		}
		int intResultNumerator = intResultfrac1Num - intResultfrac2Num;
		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator);
		
		return result;
	}
	
	// all code works the same as in addition, just multiplying 
	public static String multiply(String frac1Whole, String frac1Num, String frac1Den, String frac2Whole, String frac2Num, String frac2Denominator) {
		String result = null;
		int wResultInt = 0;
		int w1AsInt = 0;
		int frac1NumInt = 0;
		int frac1DenInt = 0;
		int w2AsInt = 0;
		int frac2NumInt = 0;
		int frac2DenInt = 0;

		if (frac1Whole != null) {
			w1AsInt = Integer.parseInt(frac1Whole);
		}
		if (frac1Num != null) {
			frac1NumInt = Integer.parseInt(frac1Num);
			frac1DenInt = Integer.parseInt(frac1Den);
		}

		if (frac1Whole != null && frac1Num != null) {
			if (w1AsInt < 0) {
				frac1NumInt = frac1NumInt * -1;
			}
			frac1NumInt = w1AsInt * frac1DenInt + frac1NumInt;
		}

		if (frac2Whole != null) {
			w2AsInt = Integer.parseInt(frac2Whole);
		}

		if (frac2Num != null) {
			frac2NumInt = Integer.parseInt(frac2Num);
			frac2DenInt = Integer.parseInt(frac2Denominator);
		}

		if (frac2Whole != null && frac2Num != null) {
			if (w2AsInt < 0) {
				frac2NumInt = frac2NumInt * -1;
			}
			frac2NumInt = w2AsInt * frac2DenInt + frac2NumInt;
		}


		if (frac1Num == null && frac2Num == null) {
			wResultInt = w1AsInt * w2AsInt;
			result = Integer.toString(wResultInt);
			return result;
		}
		
		if (frac1Num == null && frac2Num != null) {
			frac1NumInt = w1AsInt * frac2DenInt;
			frac1DenInt = frac2DenInt;
		}
		if (frac2Num == null && frac1Num != null) {
			frac2NumInt = w2AsInt * frac1DenInt;
			frac2DenInt = frac1DenInt;
		}

		int intResultNumerator = frac1NumInt * frac2NumInt;
		int intResultDenominator = frac1DenInt * frac2DenInt;

		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultDenominator);

		return result;
	
	}

	// all code works the same as in addition, just dividing
	public static String divide(String frac1W, String frac1Num, String frac1Den, String frac2W, String frac2Num, String frac2Den) {
		String result = null;
		int w1AsInt = 0;
		int frac1NumInt = 0;
		int frac1DenInt = 0;
		int w2AsInt = 0;
		int frac2NumInt = 0;
		int frac2DenInt = 0;

		if (frac1W != null) {
			w1AsInt = Integer.parseInt(frac1W);
		}
		if (frac1Num != null) {
			frac1NumInt = Integer.parseInt(frac1Num);
			frac1DenInt = Integer.parseInt(frac1Den);
		}

		if (frac1W != null && frac1Num != null) {
			if (w1AsInt < 0) {
				frac1NumInt = frac1NumInt * -1;
			}
			frac1NumInt = w1AsInt * frac1DenInt + frac1NumInt;
		}

		if (frac2W != null) {
			w2AsInt = Integer.parseInt(frac2W);
		}
		if (frac2Num != null) {
			frac2NumInt = Integer.parseInt(frac2Num);
			frac2DenInt = Integer.parseInt(frac2Den);
		}

		if (frac2W != null && frac2Num != null) {
			if (w2AsInt < 0) {
				frac2NumInt = frac2NumInt * -1;
			}
			frac2NumInt = w2AsInt * frac2DenInt + frac2NumInt;
		}

		if (frac1Num == null && frac2Num == null) {
			if (w1AsInt < 0 && w2AsInt < 0) {
				w1AsInt = w1AsInt * -1;
				w2AsInt = w2AsInt * -1;
			}
			if (w1AsInt > 0 && w2AsInt < 0) {
				w1AsInt = w1AsInt * -1;
				w2AsInt = w2AsInt * -1;
			}
			
			result = Integer.toString(w1AsInt) + "/" + Integer.toString(w2AsInt);
			return result;	
		}
		
		if (frac1Num == null && frac2Num != null) {
			frac1NumInt = w1AsInt * frac2DenInt;
			frac1DenInt = frac2DenInt;
		}
		if (frac2Num == null && frac1Num != null) {
			frac2NumInt = w2AsInt * frac1DenInt;
			frac2DenInt = frac1DenInt;
		}

		if (frac2NumInt < 0) {
			frac2NumInt = frac2NumInt * -1;
			frac2DenInt = frac2DenInt * -1;
		}
		int resultNum = frac1NumInt * frac2DenInt;
		int resultDen = frac1DenInt * frac2NumInt;
		result = Integer.toString(resultNum) + "/" + Integer.toString(resultDen);
		return result;
	}
	
	public static String simplify(String fraction) { //simplify the current result
		String result = null;
		
		int slashIndex = fraction.indexOf("/"); //get index of the fraction
		
		//split the fraction into two seperate values
		String finalNum = fraction.substring(0, slashIndex); //Numerator
		String finalDen = fraction.substring(slashIndex + 1); //Denominator
		
		if (finalNum == "0") { //if the numerator is 0
			result = "0"; //return 0
			return result;
		}

		int intNumerator = Integer.parseInt(finalNum); //set current strNum to int
		int intDenominator = Integer.parseInt(finalDen); //and the same with the denominator
		
		boolean isNegativeNumber = false; //isNegative boolean
		
		if (intNumerator < 0) { // if the numeator is negative
			isNegativeNumber = true; //set the boolean to reflect that
			intNumerator = intNumerator * -1; //and then change the value as well
		}

		int intWhole = intNumerator / intDenominator; //divide the whole number
		intNumerator = intNumerator % intDenominator; //and assign the remainder

		if (intNumerator == 0) { //if remained is 0, check if negative and then set the value that way
			if (isNegativeNumber) {
				intWhole = intWhole * -1;
			}
			result = Integer.toString(intWhole);
			return result; // return the end result
		}
		
		if (intNumerator > 1) { // if its greater than 1
			int modNumerator;
			int modDenominator;
			for (int i = intNumerator; i >= 1; i--) { //simplify using for loops and modulus
				modNumerator = intNumerator % i;
				modDenominator = intDenominator % i;
				if (modNumerator == 0 && modDenominator == 0) {
					intNumerator = intNumerator / i;
					intDenominator = intDenominator / i;
				}
			}
		}

		if (intWhole > 0) { // if the whole number is more than 0 (meaning if it exists
			if (isNegativeNumber) { // check if its a negative 
				intWhole = intWhole * -1;
			}
			//then return as such
			result = Integer.toString(intWhole) + "_" + Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
			
		}else {
			if (isNegativeNumber) { //if negative, but whole number exists
				intNumerator = intNumerator * -1;
			}
			//then return as such
			result = Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
		}
		return result; //otherwise, return
	}

}