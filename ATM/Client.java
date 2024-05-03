package assignment4;

import java.util.Date;

public class Client {
    private static int ID_num = 0;
    private int myID = 0;
    private Checking checkingAccount;
    private Savings savingsAccount;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private long phoneNumber;
    private String email;
    private int pin;

    /**
     * No parameter constructor off the Client class
     */
    public Client() {
        ID_num = ID_num + 1;
        myID = ID_num;
        this.checkingAccount = new Checking();
        this.savingsAccount = new Savings();
        this.firstName = "John";
        this.lastName = "Doe";
        this.dateOfBirth = new Date(); // unsure how to do this
        this.address = "3399 North Rd, Poughkeepsie, NY";
        this.phoneNumber = 0000000000;
        this.email = "john.doe@email.com"; // case sensitive
        this.pin = 1234;
    }

    /**
     * Parameter constructor of the Client class
     * 
     * @param checkingAccount is the checking account linked to this client
     * @param savingsdAccount is the savings account linked to this client
     * @param firstName       is the first name of the client
     * @param lastName        is the last name of the client
     * @param dateOfBirth     is the date of birth of the client
     * @param address         is the address of the client
     * @param phoneNumber     is the phone number of the client
     * @param email           is the email of the client
     * @param pin             is the PIN number of the client
     */
    public Client(Checking checkingAccount, Savings savingsdAccount, String firstName,
            String lastName, Date dateOfBirth, String address, long phoneNumber, String email, int pin) {
        ID_num = ID_num + 1;
        myID = ID_num;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsdAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth; // unsure how to do this
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email; // case sensitive
        this.pin = pin;
    }

    /**
     * Checking account setter
     * 
     * @param checkingAccount sets the current clients checking account to this
     */
    public void setCheckingAccount(Checking checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    /**
     * Savings account setter
     * 
     * @param savingsAccount sets the current clients savings account to this
     */
    public void setSavingsAccount(Savings savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    /**
     * First name setter
     * 
     * @param firstName sets the first name of the clien to this parameter
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name setter
     * 
     * @param lastName sets the last name of the client to this parameter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Date of birth setter
     * 
     * @param dateOfBirth sets the current clients date of birth to this parameter
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Address setter
     * 
     * @param address sets the address of the current client to this parameter
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Phone number setter
     * 
     * @param phoneNumber sets the phone number of the current client
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Email setter
     * 
     * @param email sets the email of the current client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Pin setter
     * 
     * @param pin sets the PIN of the current client
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * Pin getter
     * 
     * @return returns the int pin of the client
     */
    public int getPin() {
        return pin;
    }

    /**
     * Checking account getter
     * 
     * @return returns the Checking object checking account of the current client
     */
    public Checking getCheckingAccount() {
        return checkingAccount;
    }

    /**
     * Savings account getter
     * 
     * @return returns the Savings object savings account of the current client
     */
    public Savings getSavingsAccount() {
        return savingsAccount;
    }

    /**
     * First name getter
     * 
     * @return returns the String first name of the current client
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Last name getter
     * 
     * @return returns the String last name of the current client
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Date of birth getter
     * 
     * @return returns a Date object that is the date of birth of the current client
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Address getter
     * 
     * @return returns the String address of the current client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Phone number getter
     * 
     * @return returns the long phone number of the current client
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Email getter
     * 
     * @return returns the String email of the current client
     */
    public String getEmail() {
        return email;
    }

    /**
     * ID getter
     * 
     * @return returns the ID of the current client
     */
    public int getID() {
        return myID;
    }

    /**
     * Checks to see if this client can open a checking account
     * 
     * @return returns a boolean of if this client can open a checking account
     */
    public boolean canOpenChecking() {
        return checkingAccount == null;
    }

    /**
     * Checks to see if this client can open a savings account
     * 
     * @return returns a boolean of if this client can open a savings account
     */
    public boolean canOpenSavings() {
        return savingsAccount == null;
    }

    /**
     * Closes the current clients savings account
     */
    public void closeSavings() {
        this.savingsAccount = null;
    }

    /**
     * Closes the current clients checking account
     */
    public void closeChecking() {
        this.checkingAccount = null;
    }

    /**
     * Overridden toString of the Client class
     */
    public String toString() {
        return "Name: " + lastName + ", " + firstName + "; ID number: " + myID + "; PIN number: " + pin;
    }
}