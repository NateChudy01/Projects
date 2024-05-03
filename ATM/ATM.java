package assignment4;

import java.util.Scanner; //Imports the scanner class in order to prompt the user questions
import java.text.SimpleDateFormat; //Used for the date
import java.util.Date; //Used for the date
import java.text.ParseException; //Used to for the date error checking
import java.util.ArrayList; // Allows to append clients to a client list
import java.util.Arrays; // Used for checking the correct input through a scanner

/**
 * This program is meant to simulate a real world ATM including creating
 * different accounts, withdrawing and depositing money, creating join fhecking
 * accounts, keeping a list fo clients on the ATM and so on...
 * 
 * @author Nate Chudy, 10/23/2023
 */
public class ATM {
    public static void main(String[] args) {

        /**
         * Initilizing things that need to be only prompted when you login once have to
         * be outside of the loop
         */
        double ATMMoney = 5000.00;
        Bank newBank = new Bank();
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to " + newBank.getName() + "!");

        /**
         * Creating the while loop to keep prompting for actions on what to do in the
         * ATM
         */
        while (true) {
            String prompt = "Enter L to login as an existing client, N to create a new account, or X to leave the ATM: ";
            ArrayList<String> letters = new ArrayList<String>(Arrays.asList("L", "N", "X"));
            String choseLoop = correctLetterInput(letters, prompt, input);
            Client currentClient = null;
            Account current = null;
            String temp = "";

            /**
             * This if statement is the decision making process of what the user wants to
             * do, L to login as an existing client, N to create a new account, or X to
             * leave the ATM. Input grabbed from the previous block of code
             */
            if (choseLoop.equals("L")) {
                String enter = "Input PIN number: ";
                String notNumber = "Pleae enter a number: ";
                String negative = "Enter a non negative number: ";
                int pin = checkUserIntInput(enter, notNumber, negative, input);
                enter = "Input ID number: ";
                int ID = checkUserIntInput(enter, notNumber, negative, input);

                /**
                 * For loop finds the user from the input information in the previous block. The
                 * previous block is also user proof. Once found it is stores to then update
                 * values
                 */
                for (int i = 0; i < newBank.getClients().size(); i++) {
                    if (newBank.getClients().get(i).getID() == ID && newBank.getClients().get(i).getPin() == pin) {
                        currentClient = newBank.getClients().get(i);
                    }
                }

                /**
                 * If there was no client found it states there was no one under that PIN/ID and
                 * then returns to the beginning of the while loop
                 */
                if (currentClient == null) {
                    System.out.println("Invalid PIN or ID");
                    continue;
                }

                /**
                 * Once a user is found, it then prints out a nice welcome message and then
                 * sends you into another while loop inside of the account which allows you to
                 * do multiple things inside one account without having to relogin
                 */
                System.out.println("\nWelcome back Mr. " + currentClient.getLastName() + ": \n");
                while (true) {
                    int check = printAccounts(currentClient); // check value explained in the printAccounts method
                    prompt = "Enter D to deposit, W to withdraw, O to open an account, C to close an account, R to return to the homescreen: ";
                    letters = new ArrayList<String>(Arrays.asList("D", "W", "O", "C", "R"));
                    String options = correctLetterInput(letters, prompt, input);

                    /**
                     * After checking what the user wants to do in the account, D to deposit, W to
                     * withdraw, O to open an account, C to close an account, R to return to the
                     * homescreen, it then enters into another if statement to process each of those
                     * inputs
                     */
                    if (options.equals("D")) { // User wants to deposit money

                        if (check == 0) {
                            prompt = "Which account would you like to access (S/C): ";
                            letters = new ArrayList<String>(Arrays.asList("S", "C"));
                            temp = correctLetterInput(letters, prompt, input);
                            if (temp.equals("C")) {
                                current = currentClient.getCheckingAccount();
                            } else if (temp.equals("S")) {
                                current = currentClient.getSavingsAccount();
                            }
                        } else if (check == 1) {
                            current = currentClient.getCheckingAccount();
                        } else if (check == 2) {
                            current = currentClient.getSavingsAccount();
                        } else {
                            System.out.println("No account under this ID");
                        }
                        /**
                         * This block above figures out if the user has both a checking and savings
                         * account, just checking, or just savings and then accesses the correct account
                         * accordingly
                         */

                        /**
                         * Asks for an amount to deposit and updates the account whether its a joint
                         * account or a normal account
                         */
                        enter = "Amount: $";
                        double depositedAmount = checkValidMoney(enter, input);
                        current.deposit(depositedAmount);
                        ATMMoney = ATMMoney + depositedAmount;
                        System.out.println("Amount deposited successfully!");
                        System.out.println();

                    } else if (options.equals("W")) { // user wants to withdraw money

                        if (check == 0) {
                            prompt = "Which account would you like to access (S/C): ";
                            letters = new ArrayList<String>(Arrays.asList("S", "C"));
                            temp = correctLetterInput(letters, prompt, input);
                            if (temp.equals("C")) {
                                current = currentClient.getCheckingAccount();
                            } else if (temp.equals("S")) {
                                current = currentClient.getSavingsAccount();
                            }
                        } else if (check == 1) {
                            current = currentClient.getCheckingAccount();
                        } else if (check == 2) {
                            current = currentClient.getSavingsAccount();
                        } else {
                            System.out.println("No account under this ID");
                        }
                        /**
                         * This block above figures out if the user has both a checking and savings
                         * account, just checking, or just savings and then accesses the correct account
                         * accordingly
                         */

                        /**
                         * Asks for an amount to withdraw making sure there is enough money int the ATM
                         * and updates the account whether its a joint account or a normal account
                         */
                        double withdrawnAmount = withdrawFromATM(input, ATMMoney);
                        current.withdraw(withdrawnAmount);
                        System.out.println("Amount withdrawn successfully!");
                        System.out.println();

                    } else if (options.equals("O")) { // User wants to open an account

                        /**
                         * Asks what type of account to open and then does the checking for if you
                         * already have that type of account and then once the checking is done is
                         * creates the proper account
                         */
                        prompt = "What type of account do you want to open (S/C): ";
                        letters = new ArrayList<String>(Arrays.asList("S", "C"));
                        String open = correctLetterInput(letters, prompt, input);
                        boolean isJoint = createJoint(input); // asks if you want a joint account
                        if (open.equals("S") && !currentClient.canOpenSavings()) {
                            System.out.println("You already have a savings account, cannot open another one");
                            System.out.println();
                        } else if (open.equals("C") && !currentClient.canOpenChecking()) {
                            System.out.println("You already have a checking account, cannot open another one");
                            System.out.println();
                        } else if (open.equals("C")) {
                            /**
                             * Opens a checking account if the checks all passed
                             */

                            /**
                             * Prompts for a balance to enter and then creates the checking account. It also
                             * runs a
                             * block depending on if you want to create a joint account. If the user wants
                             * a joint account it then asks for an ID number of the person you want to
                             * create the account with. It does the checks on if you both can create one and
                             * then makes it printing out your names on the Status: part of the account
                             */
                            enter = "Enter a balance: $";
                            double balance1 = checkValidMoney(enter, input);
                            Client createdJoint = null;
                            if (isJoint) {
                                createdJoint = createNewJoint(input, newBank);
                                if (createdJoint == null) {
                                    System.out.println("There is no account with that ID\n");
                                    continue;
                                } else if (createdJoint.canOpenChecking() && currentClient.canOpenChecking()) {
                                    Checking jointChecking = new Checking(createdJoint, currentClient, balance1);
                                    currentClient.setCheckingAccount(jointChecking);
                                    createdJoint.setCheckingAccount(jointChecking);
                                    System.out.println("Joint checking account created!");
                                    continue;
                                } else {
                                    System.out.println(
                                            "One or both of the clients are not able to open a joint account\n");
                                }
                            }
                            Checking currentChecking = new Checking(currentClient, null, balance1);
                            currentClient.setCheckingAccount(currentChecking);
                            System.out.println("Checking account created successfully!");
                            System.out.println();

                        } else if (open.equals("S")) { // make this a function
                            /**
                             * Opens a checking account if the checks all passed
                             */

                            /**
                             * Prompts for a balance to enter and then creates the savings account. It also
                             * runs a
                             * block depending on if you want to create a joint account. If the user wants
                             * a joint account it then asks for an ID number of the person you want to
                             * create the account with. It does the checks on if you both can create one and
                             * then makes it printing out your names on the Status: part of the account
                             * which is the same as the checking account section
                             */
                            enter = "Enter a balance: $";
                            double balance2 = checkValidMoney(enter, input);
                            Client createdJoint = null;
                            if (isJoint) {
                                createdJoint = createNewJoint(input, newBank);
                                if (createdJoint == null) {
                                    System.out.println("There is no account with that ID\n");
                                    continue;
                                }
                                if (createdJoint.canOpenSavings() && currentClient.canOpenSavings()) {
                                    Savings jointSaving = new Savings(createdJoint, currentClient, balance2);
                                    currentClient.setSavingsAccount(jointSaving);
                                    createdJoint.setSavingsAccount(jointSaving);
                                    System.out.println("Joint savings account created!");
                                    continue;
                                } else {
                                    System.out.println(
                                            "One or both of the clients are not able to open a joint account\n");
                                }
                            }
                            Savings currentSavings = new Savings(currentClient, null, balance2);
                            currentClient.setSavingsAccount(currentSavings);
                            System.out.println("Savings account created successfully!");
                            System.out.println();
                        }
                    } else if (options.equals("C")) { // User wants to close an account

                        /**
                         * Asks which account you want to close and then closes it. There is extra code
                         * for it its a joint account which it then closes the account for both of the
                         * users with the account which makes sense
                         */
                        prompt = "Which account would you like to close (S/C): ";
                        letters = new ArrayList<String>(Arrays.asList("S", "C"));
                        temp = correctLetterInput(letters, prompt, input);
                        if (temp.equals("S")) { // Wants to close their savings account
                            if (currentClient.getID() == currentClient.getSavingsAccount().getClient2().getID()) {
                                Client otherClient = currentClient.getSavingsAccount().getClient1();
                                currentClient.closeSavings();
                                otherClient.closeSavings();
                            } else if (currentClient.getID() == currentClient.getSavingsAccount().getClient1()
                                    .getID()) {
                                Client otherClient = currentClient.getSavingsAccount().getClient2();
                                currentClient.closeSavings();
                                otherClient.closeSavings();
                            }
                            System.out.println("Your savings account has been closed");
                            System.out.println();
                        } else if (temp.equals("C")) { // Wants to close the checking account
                            if (currentClient.getID() == currentClient.getCheckingAccount().getClient2().getID()) {
                                Client otherClient = currentClient.getCheckingAccount().getClient1();
                                currentClient.closeChecking();
                                otherClient.closeChecking();
                            } else if (currentClient.getID() == currentClient.getCheckingAccount().getClient1()
                                    .getID()) {
                                Client otherClient = currentClient.getCheckingAccount().getClient2();
                                currentClient.closeChecking();
                                otherClient.closeChecking();
                            }
                            System.out.println("Your checking account has been closed");
                            System.out.println();
                        }
                    } else if (options.equals("R")) { // User wants to return to the main part of the ATM
                        System.out.println();
                        break;
                    }
                }
            } else if (choseLoop.equals("N")) {
                /**
                 * This part executes if the user wants to create a new account, it then prompts
                 * a list of different input statements aboutpersonal information that is
                 * necessary to create an account. All of the prompts are userproof
                 */
                System.out.print("Enter your first name: ");
                String firstName = input.next();
                System.out.print("Enter your last name: ");
                String lastName = input.next();
                input.nextLine();
                Date dateOfBirth = validBirthDate(input);
                System.out.print("Enter your address: ");
                String address = input.nextLine();
                long phoneNumber = validPhoneNumber(input);
                input.nextLine();
                String email = validGmail(input);
                int pin1 = validPIN(input);

                /**
                 * Creates the client
                 */
                currentClient = new Client(null, null, firstName, lastName, dateOfBirth, address, phoneNumber,
                        email, pin1);

                /**
                 * Prints out the clients given ID number
                 */
                System.out.println("Your ID number is " + currentClient.getID());
                // creates a client with not accounts to then be altered later
                System.out.println();

                /**
                 * Asks if you want to either open a Checking, Savings, or both and then opens
                 * accordingly
                 */
                prompt = "Would you like to open a checking account, savings account or both? (enter either C, S, B): ";
                letters = new ArrayList<String>(Arrays.asList("C", "S", "B"));
                String account = correctLetterInput(letters, prompt, input);
                if (account.equals("C")) { // User wants to open a checking account

                    String enter = "Enter a balance: $";
                    double balance1 = checkValidMoney(enter, input);
                    Checking currentChecking = new Checking(currentClient, null, balance1);
                    currentClient.setCheckingAccount(currentChecking);
                    System.out.println("Checking account created successfully!");
                    System.out.println();
                    // break

                } else if (account.equals("S")) { // User wants to open a savings account

                    String enter = "Enter a balance: $";
                    double balance2 = checkValidMoney(enter, input);
                    Savings currentSavings = new Savings(currentClient, null, balance2);
                    currentClient.setSavingsAccount(currentSavings);
                    System.out.println("Savings account created successfully!");
                    System.out.println();

                } else if (account.equals("B")) { // User wants to open both accounts

                    String enter = "Enter a balance for the checking account: $";
                    double balance3 = checkValidMoney(enter, input);
                    Checking currentChecking = new Checking(currentClient, null, balance3);
                    currentClient.setCheckingAccount(currentChecking);
                    System.out.println("Checking account created successfully!");
                    System.out.println();

                    enter = "Enter a balance for the savings account: $";
                    double balance4 = checkValidMoney(enter, input);
                    Savings currentSavings = new Savings(currentClient, null, balance4);
                    currentClient.setSavingsAccount(currentSavings);
                    System.out.println("Savings account created successfully!");
                    System.out.println();
                    // break

                }
                newBank.addClient(currentClient); // Adds the client to the bank
            } else if (choseLoop.equals("X")) { // User wants to leave the ATM
                System.out.println();
                break;
            }
        }
        System.out.println("*****Thank you for using " + newBank.getName() + "'s ATM!*****\n");
        input.close();
    }

    /**
     * This method prints out all of the input private information given when
     * signing up, only happens when logged into the account
     * 
     * @param c is the client we are printing the personal info about
     */
    public static void printPersonalInfo(Client c) {
        System.out.println("Personal Information:\n");
        System.out.println("Name: " + c.getLastName() + ", " + c.getFirstName());
        System.out.println("Date of Birth: " + c.getDateOfBirth());
        System.out.println("Address: " + c.getAddress());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Phone number: " + formatPhoneNumber(c));
        System.out.println();
    }

    /**
     * This method takes in a client and then returns a formatted String of the
     * phone number of the client which can be printed later
     * 
     * @param c is the client we are using to format the phone number and then
     *          return it
     * @return returns a formatted String phone number
     */
    public static String formatPhoneNumber(Client c) {
        String number = "" + c.getPhoneNumber();
        String formattedNumber = "";
        for (int i = 0; i <= 2; i++) {
            formattedNumber = formattedNumber + number.charAt(i);
        }
        formattedNumber = formattedNumber + "-";
        for (int i = 3; i <= 5; i++) {
            formattedNumber = formattedNumber + number.charAt(i);
        }
        formattedNumber = formattedNumber + "-";
        for (int i = 6; i <= 9; i++) {
            formattedNumber = formattedNumber + number.charAt(i);
        }
        return formattedNumber;
    }

    /**
     * This method prints the accounts of the client that is input as the parameter
     * 
     * @param c is the client which we are printing their accounts
     * @return returns an int which tells you what accounts the client has open, 0
     *         is both checkings and savings, 1 is checking, and 2 is savings, and
     *         -1 is invalid
     */
    public static int printAccounts(Client c) {
        printPersonalInfo(c);
        boolean checking = (c.getCheckingAccount() != null);
        boolean savings = (c.getSavingsAccount() != null);
        if (checking) {
            System.out.println("Checking Account:");
            System.out.printf("Balance: $%.2f\n", c.getCheckingAccount().getBalance());
            if (c.getCheckingAccount().getStatus()) {
                System.out.println("Status: Open");
            } else {
                System.out.println("Status: Closed");
            }
            if (c.getCheckingAccount().getClient1() != null && c.getCheckingAccount().getClient2() != null) {
                if (c.getCheckingAccount().getClient1().getID() == c.getID()) {
                    System.out.println("Joint status: " + c.getCheckingAccount().getClient2().getLastName() + ", "
                            + c.getCheckingAccount().getClient2().getFirstName() + "\n");
                } else {
                    System.out.println("Joint status: " + c.getCheckingAccount().getClient1().getLastName() + ", "
                            + c.getCheckingAccount().getClient1().getFirstName() + "\n");
                }
            } else {
                System.out.println("Joint Status: N/A\n");
            }
        }
        if (savings) {
            System.out.println("Savings Account:");
            System.out.printf("Balance: $%.2f\n", c.getSavingsAccount().getBalance());
            if (c.getSavingsAccount().getStatus()) {
                System.out.println("Status: Open");
            } else {
                System.out.println("Status: Closed");
            }
            if (c.getSavingsAccount().getClient1() != null && c.getSavingsAccount().getClient2() != null) {
                if (c.getSavingsAccount().getClient1().getID() == c.getID()) {
                    System.out.println("Joint status: " + c.getSavingsAccount().getClient2().getLastName() + ", "
                            + c.getSavingsAccount().getClient2().getFirstName() + "\n");
                } else {
                    System.out.println("Joint status: " + c.getSavingsAccount().getClient1().getLastName() + ", "
                            + c.getSavingsAccount().getClient1().getFirstName() + "\n");
                }
            } else {
                System.out.println("Joint Status: N/A\n");
            }
        }
        if (checking && savings) {
            return 0;
        } else if (checking && !savings) {
            return 1;
        } else if (!checking && savings) {
            return 2;
        } else {
            return -1;
        }
    }

    /**
     * This method is here to return a String of one of the String inputs in the
     * list of letters. Its a way of enforcing that the user inputs the correct
     * letter of a list of letters
     * 
     * @param letters is an ArrayList of letters which will act as the key of
     *                possible inputs from the user
     * @param prompt  is the String input that will be printed as the entrance
     *                scanner String
     * @param scan    is the scanner used in the method
     * @return This method returns a String of one of the letters in the ArrayList
     */
    public static String correctLetterInput(ArrayList<String> letters, String prompt, Scanner scan) {
        System.out.print(prompt);
        String temp = scan.next();
        String list = "";
        while (true) {
            for (int i = 0; i < letters.size(); i++) {
                if (temp.equals(letters.get(i))) {
                    return temp;
                }
                if (i == 0) {
                    list = list + " " + letters.get(i);
                } else {
                    list = list + ", " + letters.get(i);
                }
            }
            System.out.print("Please enter either" + list + ": ");
            list = "";
            temp = scan.next();
        }
    }

    /**
     * This method checks if the int input is infact a valid int
     * 
     * @param enter     is a String that is the prompt for the scanner asking the
     *                  user to input an int
     * @param notNumber is a String that prints if the int input is not an int
     * @param negative  is a String that prints if the int is an int but it is
     *                  negative
     * @param scan      is the Scanner to prompt the user for inputs
     * @return This method returns a valid int from the user
     */
    public static int checkUserIntInput(String enter, String notNumber, String negative, Scanner scan) {
        boolean valid = false;
        int validInt = 0;
        System.out.print(enter);
        while (!valid) {
            if (!scan.hasNextInt()) {
                scan.next();
                System.out.print(notNumber);
            } else {
                validInt = scan.nextInt();
                if (validInt <= 0) {
                    System.out.print(negative);
                } else {
                    valid = true;
                }
            }
        }
        return validInt;
    }

    /**
     * This method prompts the user for a valid double as an amount of money
     * 
     * @param enter is a String that is the prompt for the scanner asking the user
     *              to input an int
     * @param scan  is the Scanner to prompt the user for inputs
     * @return This method returns a double as a valid amount of money
     */
    public static double checkValidMoney(String enter, Scanner scan) {
        boolean valid = false;
        double validDouble = 0.0;
        System.out.print(enter);
        while (!valid) {
            if (!scan.hasNextDouble()) {
                scan.next();
                System.out.print("Please enter a number: $");
            } else {
                validDouble = scan.nextDouble();
                if (validDouble <= 0) {
                    System.out.print("Please enter an non negative amount: $");
                } else {
                    valid = true;
                }
            }
        }
        return validDouble;
    }

    /**
     * This method prompts the user for a date as the date of birth and makes sure
     * the input is in the correct Date format
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @return This method returns a valid date as the users birthdate
     */
    public static Date validBirthDate(Scanner scan) {
        while (true) {
            try {
                System.out.print("Enter date of birth (MM/dd/yyyy): ");
                String start = scan.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date firstDate = sdf.parse(start);
                return firstDate;
            } catch (ParseException p) {
                System.out.println(
                        "Please input your birthdate in the correct format (MM/dd/yyyy)");
                continue;
            }
        }
    }

    /**
     * This method prompts the user to input a valid gmail and keeps reasking until
     * a valid email is input
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @return This method returns a String which is a valid gmail
     */
    public static String validGmail(Scanner scan) {
        System.out.print("Enter your gmail: ");
        String gmail = scan.nextLine();
        final String gmailCheck = "@gmail.com";
        while (true) {
            if (gmail.indexOf(gmailCheck) == gmail.length() - 10) {
                return gmail;
            }
            System.out.print("Please enter a valid gmail: ");
            gmail = scan.nextLine();
        }
    }

    /**
     * This method withdraws an amount from the ATM and makes sure the amount is
     * left in the ATM before withdrawing
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @param ATM  is a double representing the amount of money that the user is
     *             trying to withdraw and needs to be checked
     * @return This method returns a double as a valid withdraw from the ATM
     */
    public static double withdrawFromATM(Scanner scan, double ATM) {
        boolean valid = false;
        double withdraw = 0;
        System.out.print("Amount: $");
        while (!valid) {
            if (!scan.hasNextDouble()) {
                scan.next();
                System.out.print("Please enter a number: $");
            } else {
                withdraw = scan.nextDouble();
                if (withdraw <= 0) {
                    System.out.print("You cant withdraw a negative amount: $");
                } else if (ATM - withdraw >= 0) {
                    valid = true;
                } else {
                    System.out.print("The ATM only has $" + ATM + " left, please enter an amount less than that: $");
                }
            }
        }
        return withdraw;
    }

    /**
     * This method prompts the user for a phone number and then makes sure that the
     * input is a 10 digit number
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @return This method returns a long which is the valid phone number input by
     *         the user
     */
    public static long validPhoneNumber(Scanner scan) {
        boolean valid = false;
        long validPhoneNumer = 0;
        String validPhone = "";
        System.out.print("Enter your phone number: ");
        while (!valid) {
            if (!scan.hasNextLong()) {
                scan.next();
                System.out.print("Please enter a phone number: ");
            } else {
                validPhoneNumer = scan.nextLong();
                validPhone = "" + validPhoneNumer;
                if (validPhoneNumer <= 0) {
                    System.out.print("Please enter a phone number: ");
                } else if (validPhone.length() != 10) {
                    System.out.print("Number must be 10 digits: ");
                } else {
                    valid = true;
                }
            }
        }
        return validPhoneNumer;
    }

    /**
     * This method prompts the user to input a PIN number and then makes sure its a
     * valid 4 digit number before returning it
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @return This method returns an in which is a valid PIN number
     */
    public static int validPIN(Scanner scan) {
        boolean valid = false;
        int validPIN = 0;
        String validPINString = "";
        System.out.print("Create a PIN number: ");
        while (!valid) {
            if (!scan.hasNextInt()) {
                scan.next();
                System.out.print("Please enter a number: ");
            } else {
                validPIN = scan.nextInt();
                validPINString = "" + validPIN;
                if (validPIN <= 0) {
                    System.out.print("Please enter a number: ");
                } else if (validPINString.length() != 4) {
                    System.out.print("Number must be 4 digits: ");
                } else {
                    valid = true;
                }
            }
        }
        return validPIN;
    }

    /**
     * This method prompts the user to see if they want to make a joint account and
     * returns a boolean
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @return This method returns a boolean of if the user wants to open a joint
     *         account or not
     */
    public static boolean createJoint(Scanner scan) {
        String prompt = "Make this a joint account? (Y/N): ";
        ArrayList<String> letters = new ArrayList<String>(Arrays.asList("Y", "N"));
        String joint = correctLetterInput(letters, prompt, scan);
        if (joint.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method begins the process of making a joint account
     * 
     * @param scan is the Scanner to prompt the user for inputs
     * @param b    is the bank input which has the client list
     * @return This method returns a Client that matches the ID input from the
     *         scanner
     */
    public static Client createNewJoint(Scanner scan, Bank b) {
        String enter = "Enter ID number of desired joint account member: ";
        String notNumber = "Please enter a number: ";
        String negative = "Please enter a number: ";
        int validID = checkUserIntInput(enter, notNumber, negative, scan);
        Client desiredClient = null;
        for (int i = 0; i < b.getClients().size(); i++) {
            if (b.getClients().get(i).getID() == validID) {
                desiredClient = b.getClients().get(i);
            }
        }
        return desiredClient;
    }

}