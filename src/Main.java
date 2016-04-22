import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static String operator;

    public static void main(String[] args) {
        System.out.println("Welcome to the Fraction Calculator!");

        System.out.println("Enter an expression (or \"quit\"):");
        String initialIn = scan.nextLine();

        while(!initialIn.equalsIgnoreCase("quit")) {
            parse(initialIn);
            System.out.println("Enter an expression (or \"quit\"):");
            initialIn = scan.nextLine();
        }
    }

    public static void parse(String rawInput) {
        String[] inputSegments = rawInput.split(" ");

        String fraction1 = inputSegments[0];
        String fraction2 = inputSegments[2];

        operator = inputSegments[1];

        String[] fraction1Numbers = fraction1.split("/");
        String[] fraction2Numbers = fraction2.split("/");

        calculate((Double.parseDouble(fraction1Numbers[0]) / Double.parseDouble(fraction1Numbers[1])), (Double.parseDouble(fraction2Numbers[0]) / Double.parseDouble(fraction2Numbers[1])));
    }

    public static void calculate(Double frac1, Double frac2) {
        double calculatedOutput = 0.0;
        switch(operator){
            case "+":
                calculatedOutput = frac1 + frac2;
                break;
            case "-":
                calculatedOutput = frac1 - frac2;
                break;
            case "/":
                calculatedOutput = frac1 / frac2;
                break;
            case "*":
                calculatedOutput = frac1 * frac2;
                break;
            default:
                System.out.println("Error: Not a Valid Operator.");
                break;
        }

        if(calculatedOutput != 0.0) {
            int w = (int) calculatedOutput,
                    n = (int) (calculatedOutput * 64) % 64,
                    a = n & -n;
            System.out.println("Result: " + w + (n == 0 ? "" : " " + n / a + "/" + 64 / a));
        }
    }
}
