package ATM;

public class AccountNotOpenException extends Exception {
    /**
     * This method is the constructor of the AccountNotOpenException class which
     * creates a throwable error of a specific string
     * 
     * @param s is the message printed when the exception is thrown
     */
    public AccountNotOpenException(String s) {
        super(s);
    }
}