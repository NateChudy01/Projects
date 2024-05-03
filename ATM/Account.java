package ATM;

import java.util.Date;

public class Account {
    private Client client1; // first client of the account
    private Client client2; // second client of the account
    private Date openingDate; // opening date of the account
    private double balance; // amount of money in the account
    private boolean status; // status of the account(open/closed) which has no setter

    /**
     * Empty cunstructor of the Account class
     */
    public Account() {
        this.client1 = new Client(); // defaults to an account with one generic client
        this.client2 = null;
        this.openingDate = new Date();
        this.balance = 5.0;
        // need to ask about this line below
        this.status = true; // true means the account is open
    }

    /**
     * Constructor with parameters of the Account class
     * 
     * @param client1 is the first client of the current account
     * @param client2 is the second client of the account which makes it a joint
     *                account
     * @param balance is the amount of money being initaly put into the account
     */
    public Account(Client client1, Client client2, double balance) {
        this.client1 = client1;
        this.client2 = client2;
        this.openingDate = new Date();
        this.balance = balance;
        if (balance < 5.0) {
            status = false;
        } else {
            status = true;
        }
    }

    /**
     * Client1 setter
     * 
     * @param client1 sets the first client as this parameter
     */
    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    /**
     * Client2 setter
     * 
     * @param client2 sets the second client at this parameter
     */
    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    /**
     * Balance setter
     * 
     * @param balance sets the current balance to this parameter
     * @throws AccountNotOpenException This exceptions is thrown when the balance is
     *                                 under $5 closing the account
     */
    public void setBalance(double balance) throws AccountNotOpenException {
        this.balance = balance;
        if (balance < 5.0) {
            status = false;
            throw new AccountNotOpenException("This account will be closed because there is less that $5 in it");
        } else {
            status = true;
        }
    }

    /**
     * Client1 getter
     * 
     * @return returns client1 which is a Client object
     */
    public Client getClient1() {
        return client1;
    }

    /**
     * Client2 getter
     * 
     * @return returns client2 which is a Client object
     */
    public Client getClient2() {
        return client2;
    }

    /**
     * Opening date getter
     * 
     * @return returns the opening date of the account which is a Date object
     */
    public Date getOpeningDate() {
        return openingDate;
    }

    /**
     * Balance getter
     * 
     * @return returns the balance of the account which is of type double
     */
    public double getBalance() {
        return balance;
    }

    /**
     * status getter
     * 
     * @return returns the status of the account(open/closed) which is a boolean
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Withdraws a certain amount from the current account
     * 
     * @param withdraw is the amount the user is trying to withdraw from the account
     */
    public void withdraw(double withdraw) {
        double balance = this.getBalance();
        try {
            this.setBalance(balance - withdraw);
        } catch (AccountNotOpenException a) {
            System.out.println("Account is closing");
        }
    }

    /**
     * Deopsits a certain amount to the current account
     * 
     * @param deposit is the amount the user wants to deposit
     */
    public void deposit(double deposit) {
        double balance = this.getBalance();
        try {
            this.setBalance(balance + deposit);
        } catch (AccountNotOpenException a) {
            System.out.print("Account is closing because the amount is less than $5, ");
        }
    }

    /**
     * Checks if the current account is a joint account
     * 
     * @return returns a boolean of if the account is a joint account or not
     */
    public boolean checkJointAccount() {
        return (client1 != null) && (client2 != null);
    }

    /**
     * This is the over ridden toString of the Account class
     */
    public String toString() {
        String returnString = "";
        if (client1 != null && client2 != null) {
            returnString = returnString + "This account has 2 clients: " + client1.getFirstName() + ", "
                    + client2.getFirstName() + ", with a balance of $" + balance;
        } else {
            returnString = returnString + "This account has 1 client: " + client1.getFirstName()
                    + ", with a balance of $" + balance;
        }
        return returnString;
    }
}