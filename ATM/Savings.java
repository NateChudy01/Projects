package ATM;

import java.util.Date;

public class Savings extends Account {
    private final double interestRate = 0.01;
    private int transactions;

    /**
     * No parameter constructor
     */
    public Savings() {
        super();
        this.transactions = 0;
    }

    /**
     * Parameter constructor
     * 
     * @param client1 is the first client of the Savings account
     * @param client2 is the second client of the savings account
     * @param balance is the balance of the savings account
     */
    public Savings(Client client1, Client client2, double balance) {
        super(client1, client2, balance);
        this.transactions = 0;
    }

    /**
     * This method withdraws from the savings account, it overrides the Account
     * class method because it has to check the number of transactions of the
     * withdraws
     */
    public void withdraw(double withdraw) {
        if (transactions <= 5) {
            double balance = this.getBalance();
            try {
                this.setBalance(balance - withdraw);
            } catch (AccountNotOpenException a) {
                System.out.print("Account is closing because the amount is less than $5, ");
            }
            transactions++;
        } else {
            System.out.println(
                    "You have withdrawn from this account 5 times this month, you will not be able to withdraw from this account for the rest of the month");
        }
    }

    /**
     * Calculates the interest of the savings account
     * @return returns the double amount of interest
     */
    public double calculateSimpleInterest() {
        Date currentDate = new Date();
        Long elapsedTime = currentDate.getTime() - this.getOpeningDate().getTime();
        return this.getBalance() * interestRate * elapsedTime;
    }

    /**
     * Overridden toString of the Savings class
     */
    public String toString() {
        return super.toString();
    }
}