package GymManagement;

public class MemberAlreadyCheckedInException extends Exception {
    /**
     * This method is the constructor of the MemberAlreadyCheckedInException class
     * which creates a throwable error of a specific string
     * 
     * @param s is the message printed when the exception is thrown
     */
    public MemberAlreadyCheckedInException(String s) {
        super(s);
    }
}
