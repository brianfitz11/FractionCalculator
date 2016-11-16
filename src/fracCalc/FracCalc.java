package fracCalc;

import java.util.Scanner;

public class FracCalc {

	private static double num1, num2, den1, den2, frac1, frac2, wn1 = 0, wn2 = 0;
	private static String oper;
	
	public static void main(String[] args) {
		System.out.println("need more input! ");
		Scanner scan = new Scanner(System.in);
    	String userInput = scan.nextLine();
    	while(userInput.compareToIgnoreCase("quit") != 0){
    		String answer = produceAnswer(userInput);
    		System.out.println(answer);
    		userInput = scan.nextLine();
    	}
    	scan.close();
	}

	public static String produceAnswer(String input) { //EXAMPLE: 1_2/3 + 3/4
		Scanner lineRdr = new Scanner(input);

    	String frac1Str = lineRdr.next(); // 1_2/3
        oper = lineRdr.next(); // +
        String frac2Str = lineRdr.next(); // 3/4
        
        lineRdr.close();
        if(frac1Str.indexOf("_") != -1)
        	wn1 = Double.parseDouble(frac1Str.substring(0, frac1Str.indexOf("_")));
        
        if(frac2Str.indexOf("_") != -1)
    		wn2 = Double.parseDouble(frac2Str.substring(0, frac2Str.indexOf("_")));

		num1 = Double.parseDouble(frac1Str.substring(frac1Str.indexOf("_")+1, frac1Str.indexOf("/")));
		num2 = Double.parseDouble(frac2Str.substring(frac2Str.indexOf("_")+1, frac2Str.indexOf("/")));

		den1 = Double.parseDouble(frac1Str.substring(frac1Str.indexOf("/")+1, frac1Str.length()));
		den2 = Double.parseDouble(frac2Str.substring(frac2Str.indexOf("/")+1, frac2Str.length()));
		
		frac1 = ((wn1*den1)+num1) / den1;
		frac2 = ((wn2*den2)+num2) / den2;

		String answer;

		switch (oper) {
			case "+":
				answer = ""+(frac1 + frac2);
				return answer;
			case "-":
				answer = ""+(frac1 - frac2);
				return answer;
			case "/":
				answer = ""+(frac1 / frac2);
				return answer;
			case "*":
				answer = ""+(frac1 * frac2);
				return answer;
			default:
				return "result: WRONG";
		}
	}

}
