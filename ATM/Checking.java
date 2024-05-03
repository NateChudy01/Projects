package assignment4;

import java.util.Date;

public class Checking extends Account {
    private final double interestRate = 0.0005;

    /**
     * No parameter constructor of the Checking class
     */
    public Checking() {
        super();
    }

    /**
     * Parameter constructor of the Checking class
     * 
     * @param client1 is the first client of the current Checking account
     * @param client2 is the second client of the current Checking account
     * @param balance is the balance of the current Checking account
     */
    public Checking(Client client1, Client client2, double balance) {
        super(client1, client2, balance);
    }

    /**
     * Calculates the simple interest from the accounts opening date to when this
     * method is called
     * 
     * @return returns the double amount of interest
     */
    public double calculateSimpleInterest() {
        Date currentDate = new Date();
        long elapsedTime = currentDate.getTime() - this.getOpeningDate().getTime();
        return this.getBalance() * interestRate * elapsedTime;
    }

    /**
     * Overridden toString of the Checking class
     */
    public String toString() {
        return super.toString();
    }
}