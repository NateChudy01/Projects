import java.io.*; // imports all java.io to use
import java.util.Scanner; //Imports the scanner class in order to prompt the user questions
import java.text.SimpleDateFormat; //Used for the date
import java.util.Date; //Used for the date
import java.text.ParseException; //Used to for the date error checking

// public class Investment.java {}
// the purpose of this class is to take in inital parameters regarding an loan/investment type and then figure out 
// the simple or compund interest of this loan/investment
public class Investment {
    public static void main(String[] args) {
        if (args.length > 0) { // detects if the user input a file
            double amount = 0.0;
            double elapsedTime = 0.0;
            String type = "";
            double rate = 0.0;
            int count = 0;
            try {
                Scanner sc = new Scanner(new File(args[0]));
                sc.useDelimiter("/n"); // scanner reads in chunks seperated by the newline character
                while (sc.hasNext()) { // while there is still another line to read
                    String[] line = sc.nextLine().split(","); // splits the current line up at the points where there is
                                                              // a comma
                    amount = parseStringToDouble(line[0], "$"); // calls the parseStringToDouble method to remove the
                                                                // "$" if given and convert the input to a double
                    elapsedTime = timeBetweenDatesFile(line[1], line[2]); // calls the timeBetweenDatesFile method to
                                                                          // find the time between two dates WITHOUT
                                                                          // prompting the user for an input
                    type = line[3]; // stores the type of interest
                    rate = parseStringToDouble(line[4], "%"); // calls the same parseStringToDouble method to remove
                                                              // the "%" character from the string and then convert it
                                                              // to a double
                    count++; // allows to track and print which line we are currently calculating the
                             // interest for
                    printInput(count, type, amount, elapsedTime, rate); // prints the interest using the
                                                                                       // method
                    // printFileInput
                }

            } catch (Exception e) { // this line has to be here to make sure that if the file is input incorrectly
                                    // or if the file is the wrong type of if anything goes wrong it defaults to
                                    // this print statement
                System.out.println("The file contents is input incorrectly");
            }
        } else { // only reached if the user did not input a file
            Scanner input = new Scanner(System.in); // creates scanner to use for the user input
            int count = 1; // needs to be initalized for the printInput() function

            // This block of code below is here to call the method checkUserDoubleInput,
            // see method for description, for the inital amount of money. It sets the
            // strings for prompting the question as well as the error messages
            String enterInitalAmount = "Enter an inital amount: $";
            String initalAmountNumber = "Please enter a number: $";
            String initalAmountNeg = "Please enter an inital amount above zero: $";
            double initalAmount = checkUserDoubleInput(enterInitalAmount, initalAmountNumber, initalAmountNeg, input);

            // This line is very important, even though the variable correction is not being
            // used. The nextInt() method does not read the newline character created by
            // hitting the enter key after inputting an int. This means that the nextLine()
            // method after it reads the enter key and therefore skips the operation all
            // together. This corrrection variable was added to store the newline left from
            // method to work as intended
            String correction = input.nextLine();

            // this line calls the method timeBetweenDates and stores the double returned
            // at the elapsed time, check the method header for more information on the
            // method
            double timeElapsed = timeBetweenDates(input);

            // This block of code below prompts the user for an interest type and will not
            // stop asking until the words either Simple or Compound are entered in any
            // form. Once accepted it is used for the calculations on the interest
            System.out.print("Enter an interest type (Simple/Compound): ");
            String interestType = input.nextLine();
            while (!(interestType.toUpperCase().equals("SIMPLE") || interestType.toUpperCase().equals("COMPOUND"))) {
                System.out.print("Please enter either Simple or Compound: ");
                interestType = input.nextLine();
            }

            // This block of code below is here to call the method checkUserDoubleInput,
            // see method for description, for the interest rate. It sets the strings for
            // prompting the question as well as the error messages
            String enterRate = "Enter an interest rate (in percentage): ";
            String rateNotNumber = "Please enter a number (without %): ";
            String rateNegative = "Please enter an interest rate above zero: ";
            double interestRate = checkUserDoubleInput(enterRate, rateNotNumber, rateNegative, input);

            // this line of code below prints out the simple/compound interest, check
            // method header for more information
            printInput(count, interestType, initalAmount, timeElapsed, interestRate);
            input.close(); // closes the scanner when done using it
        }

    }

    /*
     * static double simpleInterest(double money, double time, double rate) the
     * purpose of this method is to calculate the simple interest of the given
     * inputs from the user upon calling it. The preconditions are that each of the
     * variables can not be NULL. Postconditions are that the method returns an
     * double. there are three parameters, DOUBLE MONEY is the amount of money that
     * you have initally, DOUBLE TIME is the amount of time the money is accruing
     * interest in years, and DOUBLE RATE is the interest rate.
     */
    public static double simpleInterest(double money, double time, double rate) {
        return money * (rate / 100.00) * time; // returns the formula for the simple interest
    }

    /*
     * static double compoundInterest(double money, double time, double rate) the
     * purpose of this method is to calculate the compound interest of the
     * inputted values upon the methods call. Preconditions: Each parameter must
     * be a non NULL double. Postconditions: this method returns a double. there
     * are three parameters, DOUBLE MONEY is the amount of money that you have
     * initally, DOUBLE TIME is the amount of time the money is accruing interest in
     * years, and DOUBLE RATE is the interest rate.
     */
    public static double compoundInterest(double money, double time, double rate) {
        double adjRate = rate / 100; // adjusts the rate input from a percent to a decimal for calculations
        double compoundPow = Math.pow((1 + (adjRate / 365)), 365 * time); // These next two steps are just the combined
                                                                          // formula for the compound interest formula
        return money * compoundPow;
    }

    /*
     * static double checkUserDoubleInput(String enter, String notNumber, String
     * negative, Scanner scan)
     * the purpose of this method is to simplify the checking of an input by the
     * user. In order to enhance the code we need to make sure that there is no
     * input that from the user that can cause an error. The method below takes
     * strings as the prompts and error print messages in the specific case its
     * being called on. The scanner input is the scanner allowing for the method
     * to ask the user questions. The return type is a double which is the only
     * times we use the method is when we are looking for a double
     */
    public static double checkUserDoubleInput(String enter, String notNumber, String negative, Scanner scan) {
        boolean valid = false;
        double validDouble = 0.0;
        System.out.print(enter); // prints out the enter message
        while (!valid) {
            if (!scan.hasNextDouble()) { // when the input is not a double this if statement activates
                scan.nextLine(); // takes input and doesnt store it
                System.out.print(notNumber);
            } else {
                validDouble = scan.nextDouble(); // stores the double in validDouble to be checked
                if (validDouble <= 0) {
                    System.out.print(negative); // if the double input is negative is prompts you and then reasks for a
                                                // double
                } else {
                    valid = true; // if the input is a double and is non negative it then breaks from the while
                                  // loop
                }
            }
        }
        return validDouble;
    }

    /*
     * public static double timeBetweenDates(Scanner scan)
     * this method below takes a scanner as an input and returns a double of the
     * time, in years, of how far apart each date is. This is used during the
     * caculations of the interest
     */
    public static double timeBetweenDates(Scanner scan) {
        while (true) {
            try {
                System.out.print("Insert a starting date (MM/dd/yyyy): ");
                String start = scan.nextLine(); // asks for a startin date

                System.out.print("Insert an ending date (MM/dd/yyyy): ");
                String end = scan.nextLine(); // asks for an ending date

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // creates the date format we are using

                Date firstDate = sdf.parse(start); // parses both string dates to actual date objects
                Date secondDate = sdf.parse(end);

                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime()); // finds different in time in
                                                                                           // milliseconds
                double years = (double) diffInMillies / 1000.0 / 60.0 / 60.0 / 24.0 / 365.0; // miliseconds to years
                if ((secondDate.getTime() - firstDate.getTime()) <= 0) {
                    System.out.println("Please enter an ending date after the starting date!");
                    continue;
                }
                return years; // returns the number of years
            } catch (ParseException p) { // if at any point the input of the dates are messed up and not input correctly
                                         // this message will prompt saying to reinput them and will repeat
                System.out.println(
                        "One of these inputs are not in the date format, please input them as shown (MM/dd/yyyy)");
                continue; // loops back to top of the while loop to reask for the dates
            }
        }
    }

    /*
     * public static double timeBetweenDatesFile(String start, String end)
     * this method is similar to the method timeBetweenDates but it had to be
     * made specifically for the file input. This method does not prompt theuser
     * for the correct format therefore it had to be rewritten. It takes in two
     * parameters, STRING START is the first of the two dates and STRING END is the
     * second of the two dates
     */
    public static double timeBetweenDatesFile(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // creates date format

            Date firstDate = sdf.parse(start); // turns strings into dates
            Date secondDate = sdf.parse(end);

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            double years = (double) diffInMillies / 1000.0 / 60.0 / 60.0 / 24.0 / 365.0; // miliseconds to years
            return years;
        } catch (ParseException p) {
            System.out
                    .println("One of these inputs are not in the date format, please input them as shown (MM/dd/yyyy)");
        }
        return -1.0;
    }

    /*
     * public static double parseStringToDouble(String stringToDouble, String
     * detect)
     * this method takes in a string to be turned into a double and also takes in a
     * string that should be removed from the inital string first in order to caste
     * it into a double. STRING STRING_TO_DOUBLE is the string that should be turned
     * into a double but has an extra character that needs to be removed, and STRING
     * DETECT is the character/string that needs to be removed from the inital
     * string in order to make it into a double
     */
    public static double parseStringToDouble(String stringToDouble, String detect) {
        if (stringToDouble.indexOf(detect) != -1) {
            return Double.parseDouble(stringToDouble.replace(detect, ""));
        } else {
            return Double.parseDouble(stringToDouble);
        }
    }

    /*
     * public static void printInput(int current, String interestT, double money,
     * double time, double Irate, boolean isFile)
     * this function is a print function that prints out either the simple or
     * compound interest depending on the input. INT CURRENT is the variable to count the
     * line of the file we are calculating. STRING INTERESTP is the interest type
     * either simple or compound. DOUBLE MONEY is the inital amount of money input.
     * DOUBLE TIME is the elapsed time between the dates entered in. DOUBLE IRATE is
     * the interest rate.
     */
    public static void printInput(int current, String interestT, double money, double time, double Irate) {
        if (interestT.toUpperCase().equals("SIMPLE")) {
            System.out.printf("The simple interest accrued for account %d is $%.2f\n", current,
                    simpleInterest(money, time, Irate));
        } else {
            System.out.printf("The compound interest accrued for account %d is $%.2f\n", current,
                    compoundInterest(money, time, Irate));
        }

    }
}
