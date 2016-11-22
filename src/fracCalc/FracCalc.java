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
		int sp1Ind = input.indexOf(" "); //index of space 1
		int sp2Ind = sp1Ind + 2; //index of space 2
		int oInd = sp1Ind + 1; //operator index
		
		String operator = input.substring(oInd, oInd + 1); // operator as string 
		String f1 = input.substring(0, sp1Ind); //full first fraction as string
		String f2 = input.substring(sp2Ind + 1); //full second fraction as string
		
		//initialize fraction 1 values, but don't assign
		String f1Whole = null;
		String f1Num = null;
		String f1Den = "1";

		int f1UnderscoreIndex = f1.indexOf("_");
		int f1SlashIndex = f1.indexOf("/");

		if (f1SlashIndex != -1)
			if (f1UnderscoreIndex != -1) {
				f1Whole = f1.substring(0, f1UnderscoreIndex);
				if (f1SlashIndex != -1) {
					f1Num = f1.substring(f1UnderscoreIndex + 1, f1SlashIndex);
					f1Den = f1.substring(f1SlashIndex + 1);
				}
			else {
				f1Num = f1.substring(0, f1SlashIndex);
				f1Den = f1.substring(f1SlashIndex + 1);
			}
		} else
			f1Whole = f1;

		String f2Whole = null;
		String f2Num = null;
		String f2Den = "1";
		
		int f2UnderscoreIndex = f2.indexOf("_");
		int f2SlashIndex = f2.indexOf("/");

		if (f2SlashIndex != -1)
			if (f2UnderscoreIndex != -1) {
				f2Whole = f2.substring(0, f2UnderscoreIndex);
				if (f2SlashIndex != -1) {
					f2Num = f2.substring(f2UnderscoreIndex + 1, f2SlashIndex);
					f2Den = f2.substring(f2SlashIndex + 1);
				}
			} else {
				f2Num = f2.substring(0, f2SlashIndex);
				f2Den = f2.substring(f2SlashIndex + 1);
			}
		else
			f2Whole = f2;

		String result = "";
		
		//Do math
		if (operator.equals("+")) // if addition, add
			result = add(f1Whole, f1Num, f1Den, f2Whole, f2Num,
					f2Den);
		else if (operator.equals("-")) // if subtraction, subtract 
			result = subtr(f1Whole, f1Num, f1Den, f2Whole,
					f2Num, f2Den);
		else if (operator.equals("*")) // if multiplication, multiply
			result = mult(f1Whole, f1Num, f1Den, f2Whole,
					f2Num, f2Den);
		else if (operator.equals("/")) // if division, divide
			result = div(f1Whole, f1Num, f1Den, f2Whole, f2Num,
					f2Den);
		
		int resultSlashIndex = result.indexOf("/");
		
		if (resultSlashIndex == -1)
			return result;
		
		result = doSimplify(result);
		return result;
	}



	public static String add(String f1Whole, String f1Num, String f1Den,
			String f2Whole, String f2Num, String f2Den) {

		String result = null;
		int intResultWholeNumber = 0;
		int intf1Whole = 0;
		int intf1Num = 0;
		int intf1Den = 0;
		int intf2Whole = 0;
		int intf2Num = 0;
		int intf2Den = 0;

		if (f1Whole != null)
			intf1Whole = Integer.parseInt(f1Whole);

		if (f1Num != null) {
			intf1Num = Integer.parseInt(f1Num);
			intf1Den = Integer.parseInt(f1Den);
		}
		
		if (f1Whole != null && f1Num != null) {
			if (intf1Whole < 0) 
				intf1Num = intf1Num * -1;
			intf1Num = intf1Whole * intf1Den + intf1Num;
		}
		
		if (f2Whole != null)
			intf2Whole = Integer.parseInt(f2Whole);
	
		if (f2Num != null) {
			intf2Num = Integer.parseInt(f2Num);
			intf2Den = Integer.parseInt(f2Den);
		}
		
		if (f2Whole != null && f2Num != null) {
			if (intf2Whole < 0)
				intf2Num = intf2Num * -1;

			intf2Num = intf2Whole * intf2Den + intf2Num;
		}
		
		if (f1Num == null && f2Num == null) {
			intResultWholeNumber = intf1Whole + intf2Whole;
			result = Integer.toString(intResultWholeNumber);
			return result;
		}
	
		if (f1Num == null && f2Num != null) {
			intf1Num = intf1Whole * intf2Den;
			intf1Den = intf2Den;
		}
		if (f2Num == null && f1Num != null) {
			intf2Num = intf2Whole * intf1Den;
			intf2Den = intf1Den;
		}
	
		int intResultCommonDenominator = intf1Den;
		int intResultf1Num = intf1Num;
		int intResultf2Num = intf2Num;
		
		if (intf1Den != intf2Den) {
			intResultCommonDenominator = intf1Den * intf2Den;
			intResultf1Num = intf1Num * intf2Den;
			intResultf2Num = intf2Num * intf1Den;
		}
		
		int intResultNumerator = intResultf1Num + intResultf2Num;
		
		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator);

		return result;
	}

	public static String subtr(String f1Whole, String f1Num, String f1Den,
			String f2Whole, String f2Num, String f2Den) {
		String result = null;
		int intResultWholeNumber = 0;
		int intf1Whole = 0;
		int intf1Num = 0;
		int intf1Den = 0;
		int intf2Whole = 0;
		int intf2Num = 0;
		int intf2Den = 0;

		if (f1Whole != null) {
			intf1Whole = Integer.parseInt(f1Whole);
		}
		if (f1Num != null) {
			intf1Num = Integer.parseInt(f1Num);
			intf1Den = Integer.parseInt(f1Den);
		}

		if (f1Whole != null && f1Num != null) {
			if (intf1Whole < 0) {
				intf1Num = intf1Num * -1;
			}
			intf1Num = intf1Whole * intf1Den + intf1Num;
		}

		if (f2Whole != null) {
			intf2Whole = Integer.parseInt(f2Whole);
		}
		if (f2Num != null) {
			intf2Num = Integer.parseInt(f2Num);
			intf2Den = Integer.parseInt(f2Den);
		}

		if (f2Whole != null && f2Num != null) {
			if (intf2Whole < 0) {
				intf2Num = intf2Num * -1;
			}
			intf2Num = intf2Whole * intf2Den + intf2Num;
		}

		if (f1Num == null && f2Num == null) {
			intResultWholeNumber = intf1Whole - intf2Whole;
			result = Integer.toString(intResultWholeNumber);
			return result;
		}
		
		if (f1Num == null && f2Num != null) {
			intf1Num = intf1Whole * intf2Den;
			intf1Den = intf2Den;
		}
		if (f2Num == null && f1Num != null) {
			intf2Num = intf2Whole * intf1Den;
			intf2Den = intf1Den;
		}

		int intResultCommonDenominator = intf1Den;
		int intResultf1Num = intf1Num;
		int intResultf2Num = intf2Num;

		if (intf1Den != intf2Den) {
			intResultCommonDenominator = intf1Den * intf2Den;
			intResultf1Num = intf1Num * intf2Den;
			intResultf2Num = intf2Num * intf1Den;

		}

		int intResultNumerator = intResultf1Num - intResultf2Num;

		result = Integer.toString(intResultNumerator) + "/" + Integer.toString(intResultCommonDenominator);

		return result;
	}

	public static String mult(String f1Whole, String f1Num, String f1Den,
		String f2Whole, String f2Num, String f2Den) {
		String result = null;
		int finalNum, finalDen, whole1Int = 0, num1 = 0, den1 = 0, whole2Int = 0, num2 = 0, den2 = 0;

		if (f1Whole != null)
			whole1Int = Integer.parseInt(f1Whole);

		if (f1Num != null) {
			num1 = Integer.parseInt(f1Num);
			den1 = Integer.parseInt(f1Den);
		}

		if (f1Whole != null && f1Num != null) {
			if (whole1Int < 0)
				num1 *= -1;
			num1 = whole1Int * den1 + num1;
		}

		if (f2Whole != null)
			whole2Int = Integer.parseInt(f2Whole);

		if (f2Num != null) {
			num2 = Integer.parseInt(f2Num);
			num1 = Integer.parseInt(f2Den);
		}

		if (f2Whole != null && f2Num != null) {
			if (whole2Int < 0)
				num2 *= -1;
			num2 = whole2Int * den2 + den2;
		}


		if (f1Num == null && f2Num == null)
			return Integer.toString(whole1Int * whole2Int);
		
		if (f1Num == null && f2Num != null) {
			num1 = whole2Int * den2;
			den1 = den2;
		}
		
		if (f2Num == null && f1Num != null) {
			num2 = whole2Int * den1;
			den2 = den1;
		}

		finalNum = num1 * num2;
		finalDen = den1 * den2;

		result = Integer.toString(finalNum) + "/" + Integer.toString(finalDen);

		return result;
	
	}

	public static String div(String f1Whole, String f1Num, String f1Den,
			String f2Whole, String f2Num, String f2Den) {
		String result = null;
		int whole1Int = 0, num1 = 0, den1 = 0, whole2Int = 0, num2 = 0, den2 = 0;


		if (f1Whole != null)
			whole1Int = Integer.parseInt(f1Whole);
		
		if (f1Num != null) {
			num1 = Integer.parseInt(f1Num);
			den1 = Integer.parseInt(f1Den);
		}

		if (f1Whole != null && f1Num != null) {
			if (whole1Int < 0)
				num1 *= -1;
			num1 = whole1Int * den1 + num1;
		}

		if (f2Whole != null)
			whole2Int = Integer.parseInt(f2Whole);
		
		if (f2Num != null) {
			num2 = Integer.parseInt(f2Num);
			den2 = Integer.parseInt(f2Den);
		}

		if (f2Whole != null && f2Num != null) {
			if (whole2Int < 0)
				num2 = num2 * -1;
			num2 = whole2Int * den2 + num2;
		}

		if (f1Num == null && f2Num == null) {
			if (whole1Int < 0 && whole2Int < 0) {
				whole1Int = whole1Int * -1;
				whole2Int = whole2Int * -1;
			}
			
			if (whole1Int > 0 && whole2Int < 0) {
				whole1Int = whole1Int * -1;
				whole2Int = whole2Int * -1;
			}
			result = Integer.toString(whole1Int) + "/" + Integer.toString(whole2Int);
			return result;
		}
		
		if (f1Num == null && f2Num != null) {
			num1 = whole1Int * den2;
			den1 = den2;
		}
		
		if (f2Num == null && f1Num != null) {
			num2 = whole2Int * den1;
			den2 = den1;
		}

		if (num2 < 0) {
			num2 = num2 * -1;
			den2 = den2 * -1;
		}
		
		int intResultNumerator = num1 * den2;
		int intResultDenominator = den1 * num2;
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
			if (isNegativeNumber)
				intWhole = intWhole * -1;
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
			if (isNegativeNumber)
				intWhole = intWhole * -1;
			result = Integer.toString(intWhole) + "_" + Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
		} else {
			if (isNegativeNumber)
				intNumerator = intNumerator * -1;
			result = Integer.toString(intNumerator) + "/" + Integer.toString(intDenominator);
		}
		return result;
	}
}
