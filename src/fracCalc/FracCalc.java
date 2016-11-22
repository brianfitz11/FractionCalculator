package fracCalc;

import java.util.Scanner;

public class FracCalc {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		
		while (input.compareToIgnoreCase("quit") != 0) {
			String answer = produceAnswer(input);
			System.out.println(answer);
			input = console.nextLine();
		}
		console.close();
	}

	public static String produceAnswer(String input) {
		int firstSpaceIndex = input.indexOf(" ");
		int secondSpaceIndex = firstSpaceIndex + 2;
		int operatorIndex = firstSpaceIndex + 1;
		
		String operator = input.substring(operatorIndex, operatorIndex + 1);
		String operand1 = input.substring(0, firstSpaceIndex);
		String operand2 = input.substring(secondSpaceIndex + 1);
		
		String operand1Whole = null;
		String operand1Numerator = null;
		String operand1Denominator = "1";

		int operand1UnderscoreIndex = operand1.indexOf("_");
		int operand1SlashIndex = operand1.indexOf("/");

		if (operand1SlashIndex != -1) {
			if (operand1UnderscoreIndex != -1) {
				operand1Whole = operand1.substring(0, operand1UnderscoreIndex);
				if (operand1SlashIndex != -1) {
					operand1Numerator = operand1.substring(operand1UnderscoreIndex + 1, operand1SlashIndex);
					operand1Denominator = operand1.substring(operand1SlashIndex + 1);
				}

			}

			else {
				operand1Numerator = operand1.substring(0, operand1SlashIndex);
				operand1Denominator = operand1.substring(operand1SlashIndex + 1);
			}
		} else {
			operand1Whole = operand1;
		}

		String operand2Whole = null;
		String operand2Numerator = null;
		String operand2Denominator = "1";
		
		int operand2UnderscoreIndex = operand2.indexOf("_");
		int operand2SlashIndex = operand2.indexOf("/");

		if (operand2SlashIndex != -1) {
			if (operand2UnderscoreIndex != -1) {
				operand2Whole = operand2.substring(0, operand2UnderscoreIndex);
				if (operand2SlashIndex != -1) {
					operand2Numerator = operand2.substring(operand2UnderscoreIndex + 1, operand2SlashIndex);
					operand2Denominator = operand2.substring(operand2SlashIndex + 1);
				}
			} else {
				operand2Numerator = operand2.substring(0, operand2SlashIndex);
				operand2Denominator = operand2.substring(operand2SlashIndex + 1);
			}
		} else {
			operand2Whole = operand2;
		}

		String result = null;
		if (operator.equals("+")) {
			result = doAddition(operand1Whole, operand1Numerator, operand1Denominator, operand2Whole, operand2Numerator,
					operand2Denominator);
		} else if (operator.equals("-")) {

			result = doSubtraction(operand1Whole, operand1Numerator, operand1Denominator, operand2Whole,
					operand2Numerator, operand2Denominator);

		} else if (operator.equals("*")) {
			result = doMultiplication(operand1Whole, operand1Numerator, operand1Denominator, operand2Whole,
					operand2Numerator, operand2Denominator);
		} else if (operator.equals("/")) {
			result = doDivision(operand1Whole, operand1Numerator, operand1Denominator, operand2Whole, operand2Numerator,
					operand2Denominator);
		}
		
		int resultSlashIndex = result.indexOf("/");
		if (resultSlashIndex == -1) {
			return result;
		}
		result = doSimplify(result);
		return result;
	}



	public static String doAddition(String operand1Whole, String operand1Numerator, String operand1Denominator,
		String operand2Whole, String operand2Numerator, String operand2Denominator) {

		String result = null;
		int intResultWholeNumber = 0;
		int intOperand1Whole = 0;
		int intOperand1Numerator = 0;
		int intOperand1Denominator = 0;
		int intOperand2Whole = 0;
		int intOperand2Numerator = 0;
		int intOperand2Denominator = 0;

		if (operand1Whole != null) {
			intOperand1Whole = Integer.parseInt(operand1Whole);
		}
		if (operand1Numerator != null) {
			intOperand1Numerator = Integer.parseInt(operand1Numerator);
			intOperand1Denominator = Integer.parseInt(operand1Denominator);
		}
		
		if (operand1Whole != null && operand1Numerator != null) {
			if (intOperand1Whole < 0) {
				intOperand1Numerator = intOperand1Numerator * -1;
			}
			intOperand1Numerator = intOperand1Whole * intOperand1Denominator + intOperand1Numerator;
		}
		
		if (operand2Whole != null) {
			intOperand2Whole = Integer.parseInt(operand2Whole);
		}
		if (operand2Numerator != null) {
			intOperand2Numerator = Integer.parseInt(operand2Numerator);
			intOperand2Denominator = Integer.parseInt(operand2Denominator);
		}
		
		if (operand2Whole != null && operand2Numerator != null) {
			if (intOperand2Whole < 0) {
				intOperand2Numerator = intOperand2Numerator * -1;
			}
			intOperand2Numerator = intOperand2Whole * intOperand2Denominator + intOperand2Numerator;
		}
		
		if (operand1Numerator == null && operand2Numerator == null) {
			intResultWholeNumber = intOperand1Whole + intOperand2Whole;
			result = Integer.toString(intResultWholeNumber);
			return result;
		}
	
		if (operand1Numerator == null && operand2Numerator != null) {
			intOperand1Numerator = intOperand1Whole * intOperand2Denominator;
			intOperand1Denominator = intOperand2Denominator;
		}
		if (operand2Numerator == null && operand1Numerator != null) {
			intOperand2Numerator = intOperand2Whole * intOperand1Denominator;
			intOperand2Denominator = intOperand1Denominator;
		}
	
		int intResultCommonDenominator = intOperand1Denominator;
		int intResultOperand1Numerator = intOperand1Numerator;
		int intResultOperand2Numerator = intOperand2Numerator;
		
		if (intOperand1Denominator != intOperand2Denominator) {
			intResultCommonDenominator = intOperand1Denominator * intOperand2Denominator;
			intResultOperand1Numerator = intOperand1Numerator * intOperand2Denominator;
			intResultOperand2Numerator = intOperand2Numerator * intOperand1Denominator;

		}
		
		int intResultNumerator = intResultOperand1Numerator + intResultOperand2Numerator;
		
		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator);

		return result;
	}

	public static String doSubtraction(String operand1Whole, String operand1Numerator, String operand1Denominator,
			String operand2Whole, String operand2Numerator, String operand2Denominator) {
		String result = null;
		int intResultWholeNumber = 0;
		int intOperand1Whole = 0;
		int intOperand1Numerator = 0;
		int intOperand1Denominator = 0;
		int intOperand2Whole = 0;
		int intOperand2Numerator = 0;
		int intOperand2Denominator = 0;

		if (operand1Whole != null) {
			intOperand1Whole = Integer.parseInt(operand1Whole);
		}
		if (operand1Numerator != null) {
			intOperand1Numerator = Integer.parseInt(operand1Numerator);
			intOperand1Denominator = Integer.parseInt(operand1Denominator);
		}

		if (operand1Whole != null && operand1Numerator != null) {
			if (intOperand1Whole < 0) {
				intOperand1Numerator = intOperand1Numerator * -1;
			}
			intOperand1Numerator = intOperand1Whole * intOperand1Denominator + intOperand1Numerator;
		}

		if (operand2Whole != null) {
			intOperand2Whole = Integer.parseInt(operand2Whole);
		}
		if (operand2Numerator != null) {
			intOperand2Numerator = Integer.parseInt(operand2Numerator);
			intOperand2Denominator = Integer.parseInt(operand2Denominator);
		}

		if (operand2Whole != null && operand2Numerator != null) {
			if (intOperand2Whole < 0) {
				intOperand2Numerator = intOperand2Numerator * -1;
			}
			intOperand2Numerator = intOperand2Whole * intOperand2Denominator + intOperand2Numerator;
		}

		if (operand1Numerator == null && operand2Numerator == null) {
			intResultWholeNumber = intOperand1Whole - intOperand2Whole;
			result = Integer.toString(intResultWholeNumber);
			return result;
		}
		
		if (operand1Numerator == null && operand2Numerator != null) {
			intOperand1Numerator = intOperand1Whole * intOperand2Denominator;
			intOperand1Denominator = intOperand2Denominator;
		}
		if (operand2Numerator == null && operand1Numerator != null) {
			intOperand2Numerator = intOperand2Whole * intOperand1Denominator;
			intOperand2Denominator = intOperand1Denominator;
		}

		int intResultCommonDenominator = intOperand1Denominator;
		int intResultOperand1Numerator = intOperand1Numerator;
		int intResultOperand2Numerator = intOperand2Numerator;

		if (intOperand1Denominator != intOperand2Denominator) {
			intResultCommonDenominator = intOperand1Denominator * intOperand2Denominator;
			intResultOperand1Numerator = intOperand1Numerator * intOperand2Denominator;
			intResultOperand2Numerator = intOperand2Numerator * intOperand1Denominator;

		}

		int intResultNumerator = intResultOperand1Numerator - intResultOperand2Numerator;

		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator);

		return result;
	}

	public static String doMultiplication(String operand1Whole, String operand1Numerator, String operand1Denominator,
		String operand2Whole, String operand2Numerator, String operand2Denominator) {
		String result = null;
		int intResultWholeNumber = 0;
		int intOperand1Whole = 0;
		int intOperand1Numerator = 0;
		int intOperand1Denominator = 0;
		int intOperand2Whole = 0;
		int intOperand2Numerator = 0;
		int intOperand2Denominator = 0;

		if (operand1Whole != null) {
			intOperand1Whole = Integer.parseInt(operand1Whole);
		}
		if (operand1Numerator != null) {
			intOperand1Numerator = Integer.parseInt(operand1Numerator);
			intOperand1Denominator = Integer.parseInt(operand1Denominator);
		}

		if (operand1Whole != null && operand1Numerator != null) {
			if (intOperand1Whole < 0) {
				intOperand1Numerator = intOperand1Numerator * -1;
			}
			intOperand1Numerator = intOperand1Whole * intOperand1Denominator + intOperand1Numerator;
		}

		if (operand2Whole != null) {
			intOperand2Whole = Integer.parseInt(operand2Whole);
		}

		if (operand2Numerator != null) {
			intOperand2Numerator = Integer.parseInt(operand2Numerator);
			intOperand2Denominator = Integer.parseInt(operand2Denominator);
		}

		if (operand2Whole != null && operand2Numerator != null) {
			if (intOperand2Whole < 0) {
				intOperand2Numerator = intOperand2Numerator * -1;
			}
			intOperand2Numerator = intOperand2Whole * intOperand2Denominator + intOperand2Numerator;
		}


		if (operand1Numerator == null && operand2Numerator == null) {
			intResultWholeNumber = intOperand1Whole * intOperand2Whole;
			result = Integer.toString(intResultWholeNumber);
			return result;
		}
		
		if (operand1Numerator == null && operand2Numerator != null) {
			intOperand1Numerator = intOperand1Whole * intOperand2Denominator;
			intOperand1Denominator = intOperand2Denominator;
		}
		if (operand2Numerator == null && operand1Numerator != null) {
			intOperand2Numerator = intOperand2Whole * intOperand1Denominator;
			intOperand2Denominator = intOperand1Denominator;
		}

		int intResultNumerator = intOperand1Numerator * intOperand2Numerator;
		int intResultDenominator = intOperand1Denominator * intOperand2Denominator;

		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultDenominator);

		return result;
	
	}

	public static String doDivision(String operand1Whole, String operand1Numerator, String operand1Denominator,
			String operand2Whole, String operand2Numerator, String operand2Denominator) {
		String result = null;
		int intOperand1Whole = 0;
		int intOperand1Numerator = 0;
		int intOperand1Denominator = 0;
		int intOperand2Whole = 0;
		int intOperand2Numerator = 0;
		int intOperand2Denominator = 0;

		if (operand1Whole != null) {
			intOperand1Whole = Integer.parseInt(operand1Whole);
		}
		if (operand1Numerator != null) {
			intOperand1Numerator = Integer.parseInt(operand1Numerator);
			intOperand1Denominator = Integer.parseInt(operand1Denominator);
		}

		if (operand1Whole != null && operand1Numerator != null) {
			if (intOperand1Whole < 0) {
				intOperand1Numerator = intOperand1Numerator * -1;
			}
			intOperand1Numerator = intOperand1Whole * intOperand1Denominator + intOperand1Numerator;
		}

		if (operand2Whole != null) {
			intOperand2Whole = Integer.parseInt(operand2Whole);
		}
		if (operand2Numerator != null) {
			intOperand2Numerator = Integer.parseInt(operand2Numerator);
			intOperand2Denominator = Integer.parseInt(operand2Denominator);
		}

		if (operand2Whole != null && operand2Numerator != null) {
			if (intOperand2Whole < 0) {
				intOperand2Numerator = intOperand2Numerator * -1;
			}
			intOperand2Numerator = intOperand2Whole * intOperand2Denominator + intOperand2Numerator;
		}

		if (operand1Numerator == null && operand2Numerator == null) {
			if (intOperand1Whole < 0 && intOperand2Whole < 0) {
				intOperand1Whole = intOperand1Whole * -1;
				intOperand2Whole = intOperand2Whole * -1;
			}
			if (intOperand1Whole > 0 && intOperand2Whole < 0) {
				intOperand1Whole = intOperand1Whole * -1;
				intOperand2Whole = intOperand2Whole * -1;
			}
				
			
			result = Integer.toString(intOperand1Whole) + "/" + Integer.toString(intOperand2Whole);
			return result;
			
		}
		
		if (operand1Numerator == null && operand2Numerator != null) {
			intOperand1Numerator = intOperand1Whole * intOperand2Denominator;
			intOperand1Denominator = intOperand2Denominator;
		}
		if (operand2Numerator == null && operand1Numerator != null) {
			intOperand2Numerator = intOperand2Whole * intOperand1Denominator;
			intOperand2Denominator = intOperand1Denominator;
		}

		if (intOperand2Numerator < 0) {
			intOperand2Numerator = intOperand2Numerator * -1;
			intOperand2Denominator = intOperand2Denominator * -1;
		}
		int intResultNumerator = intOperand1Numerator * intOperand2Denominator;
		int intResultDenominator = intOperand1Denominator * intOperand2Numerator;
		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultDenominator);
		return result;
	}
	
	public static String doSimplify(String fraction) {
		String result = null;
		int slashIndex = fraction.indexOf("/");
		String numerator = fraction.substring(0, slashIndex);
		String denominator = fraction.substring(slashIndex + 1);
		if (numerator == "0") {
			result = "0";
			return result;
		}

		int intNumerator = Integer.parseInt(numerator);
		int intDenominator = Integer.parseInt(denominator);
		boolean isNegativeNumber = false;
		if (intNumerator < 0) {
			isNegativeNumber = true;
			intNumerator = intNumerator * -1;
		}

		int intWhole = intNumerator / intDenominator;
		intNumerator = intNumerator % intDenominator;
		if (intNumerator == 0) {
			if (isNegativeNumber) {
				intWhole = intWhole * -1;
			}
			result = Integer.toString(intWhole);
			return result;
		}
		
		if (intNumerator > 1) {
			int modNumerator;
			int modDenominator;
			for (int i = intNumerator; i >= 1; i--) {
				modNumerator = intNumerator % i;
				modDenominator = intDenominator % i;
				if (modNumerator == 0 && modDenominator == 0) {
					intNumerator = intNumerator / i;
					intDenominator = intDenominator / i;
				}
			}
		}

		if (intWhole > 0) {
			if (isNegativeNumber) {
				intWhole = intWhole * -1;
			}
			result = Integer.toString(intWhole) + "_" + Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
			
		}else {
			if (isNegativeNumber) {
				intNumerator = intNumerator * -1;
			}
			result = Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
		}
		return result;
	}

}
