

public class Fraction { //The Class where I keep my methods and where I do my math
 
	   private Integer numWhole; //Create an Integer (different than int) for Whole Numbers
       private Integer numerator; //Create an Integer for Numerator.
       private Integer denominator; //Create an Integer for Denominator.
      
       public Fraction() { // Constructor
    	   numWhole = 0;
           numerator = 0;
           denominator = 0;
       }

        public Fraction(int n, int d) { // Constructor
        	  numWhole = 0;
              numerator = n;
              denominator = d;
       }

        public Fraction(int w, int n, int d) { // Constructor
      	    numWhole = w;
            numerator = n;
            denominator = d;
     }
       
       public Fraction Add(Fraction f2) { //Add fractions
    	   	  int w = this.numWhole + f2.numWhole; //whole number
              int n = (numerator * f2.denominator) + (denominator * f2.numerator); //numerator
              int d = denominator * f2.denominator; //denominator
              
              Fraction result = new Fraction(w, n, d);
              return result;
       }
       
       public Fraction Subtract(Fraction f2) { //Subtract fractions
    	   int w = this.numWhole - f2.numWhole;
           int n = (numerator * f2.denominator) - (denominator * f2.numerator);
           int d = denominator * f2.denominator;

           Fraction result = new Fraction(w, n, d);
           return result;
       }
       
       public Fraction Multiply(Fraction f2) { //Multiply Fractions
    	   int w = this.numWhole * f2.numWhole;
           int n = (numerator * f2.denominator) * (denominator * f2.numerator);
           int d = denominator * f2.denominator;

           Fraction result = new Fraction(w, n, d);
           return result;
       }

       public Fraction Divide(Fraction f2) { //Divide fractions
    	   int w = this.numWhole / f2.numWhole;
           int n = (numerator * f2.denominator) / (denominator * f2.numerator);
           int d = denominator * f2.denominator;

           Fraction result = new Fraction(w, n, d);
           return result;
       }

       
       public int greatestCommonDenom(int gcdNum, int gcdDenom) { //Finds the Greatest common denominator
    	   if (gcdDenom == 0) return gcdNum;
    	   return greatestCommonDenom (gcdDenom, gcdNum % gcdDenom);
       }
       

       public void Print() { //Print
              System.out.print(numerator + "/" + denominator);
       }
      
       public int getWhole() { //Get the Whole Number Amount
           return this.numWhole;
       }

       public int getNumerator() { //Get the Numerator Amount
              return this.numerator;
       }
 
       public int getDenominator() { //Get the Denominator Amount
              return this.denominator;
       }
       
       public void setWhole(int w) { //Set the Whole Number Amount to int w
           this.numWhole = w;
       }

       public void setNumerator(int n) { //Set the Numerator Amount to int n
           this.numerator = n;
       }

       public void setDenominator(int d) { //Set the Denominator Amount to int d
              this.denominator = d;
       }
 
       public boolean fromString(String funcString) { //Check to see if the user input is correctly formatted

	       	int iWholeIndex = 0;
	       	int iDenIndex = 0;
	       	
	       	if (funcString.contains("_")) { //Check to see if the user input includes a whole number
	       		iWholeIndex = funcString.indexOf("_");
	       		this.numWhole = Integer.valueOf(funcString.substring(0, iWholeIndex++));
	       	}

	       	if (funcString.contains("/")) { //Check to see if the user input includes a fraction
	       		iDenIndex = funcString.indexOf("/");
	       		this.numerator = Integer.valueOf(funcString.substring(iWholeIndex, iDenIndex));	       		
	       		this.denominator = Integer.valueOf(funcString.substring(iDenIndex+1));
	       	} 
	       	else { //Otherwise, point out that the user has not put in a valid statement, print Invalid Fraction and return false.
	       		System.out.println("Invalid fraction");
	       		return false;
	       	}
    	   return true; //If the user fulfills the input checks, return true
       }
       
       public String toString() { //Converts the Integers and ints to a string to present to the user, after going through a couple of checks. 
    	   String strFunc = new String(); //Creates the output string  
    	   strFunc = "";
    	   
    	   while (this.numerator > this.denominator) { //Check to see if the numerator is greater than the denominator, and if it it, make it proper.
    		   numWhole++;
    		   this.numerator = this.numerator - this.denominator;
    	   }

    	   if(this.numerator == 0 && this.denominator == 0) { //Check if the numerator and denominator is 0, then just output the whole number
    		   strFunc = this.numWhole.toString();
        	   return strFunc; //Return the output string.
    	   }
    	   
    	   if(this.numerator == this.denominator) { //Checks if the numerator is equal to the denominator, then add 1 to the whole number for example, 2/2 = 1
    		   this.numWhole++;
    		   this.numerator = 0;
    		   this.denominator = 0;
    		   strFunc = this.numWhole.toString(); // + " " + this.numerator.toString() + "/" + this.denominator.toString();
        	   return strFunc; //Return the output string.
    	   }
    	   
    	   if(this.numerator == 0 && this.numWhole == 0) { //Check if the numerator is 0 and the whole numbers are 0, then make the output 0
    		   strFunc = "0";
        	   return strFunc; //Return the output string.
    	   }

    	   int iGCD = greatestCommonDenom(this.numerator, this.denominator);
    	   this.numerator = this.numerator/iGCD;
    	   this.denominator = this.denominator/iGCD;
    	   
    	   if(this.numWhole != 0)
    		   strFunc = this.numWhole.toString() + " ";
    	   
    	   strFunc = strFunc + this.numerator.toString() + "/" + this.denominator.toString();
    	   return strFunc; //Return the output string.
       }
}