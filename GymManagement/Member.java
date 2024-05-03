package GymManagement;

import java.util.ArrayList;

public class Member {
    private static int MemberID = 0;
    private int MyMemberID;
    private String firstName;
    private String lastName;
    private String gender;
    private FoxPlan foxPlan;
    private BasicPlan basicPlan;
    private int points;
    private double money;
    private boolean checkedIn;
    private ArrayList<Classes> classes;

    /**
     * No arg constructor
     */
    public Member() {
        MemberID++;
        this.classes = new ArrayList<Classes>();
        this.MyMemberID = MemberID;
        this.firstName = "John";
        this.lastName = "Doe";
        this.gender = "Male";
        this.foxPlan = null;
        this.basicPlan = new BasicPlan();
        this.points = 0;
        this.money = 0;
        this.checkedIn = false;
    }

    /**
     * Three arg constructor
     * 
     * @param firstName String first name of the member
     * @param lastName  String last name of the member
     * @param gender    String gender of the member
     */
    public Member(String firstName, String lastName, String gender) {
        MemberID++;
        this.classes = new ArrayList<Classes>();
        this.MyMemberID = MemberID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.foxPlan = null;
        this.basicPlan = new BasicPlan();
        this.points = 0;
        this.money = 0;
        this.checkedIn = false;
    }

    /**
     * This method gets the memberID
     * 
     * @return int memberID
     */
    public int getMemberID() {
        return MyMemberID;
    }

    /**
     * This method is the classes getter
     * 
     * @return Arraylist of Classes
     */
    public ArrayList<Classes> getClasses() {
        return classes;
    }

    /**
     * This method is the clas list setter
     * 
     * @param classes Arraylist of Classes
     */
    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    /**
     * This method adds a class to the class list of the current member
     * 
     * @param classes Class to add to the class list
     */
    public void enroll(Classes classes) {
        this.classes.add(classes);
    }

    /**
     * This method gets the checked in status
     * 
     * @return boolean checked in
     */
    public boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     * This method sets the checked in status of the current member
     * 
     * @param checkedIn boolean checked in
     * @throws MemberAlreadyCheckedInException This exception is thrown when the
     *                                         member trie to check in when already
     *                                         checked in
     */
    public void setCheckedIn(boolean checkedIn) throws MemberAlreadyCheckedInException {
        if (this.checkedIn && checkedIn) {
            throw new MemberAlreadyCheckedInException("You are already checked in");
        } else if (!this.checkedIn && !checkedIn) {
            throw new MemberAlreadyCheckedInException("You are already checked out");
        } else {
            this.checkedIn = checkedIn;
        }
    }

    /**
     * This method gets the members money
     * 
     * @return double money
     */
    public double getMoney() {
        return money;
    }

    /**
     * This method gets the points for the current member
     * 
     * @return int points
     */
    public int getPoints() {
        return points;
    }

    /**
     * This method adds 10 points to the current members points when checked in
     */
    public void checkIn() {
        points += 10;
    }

    /**
     * This method gets the name of the Member
     * 
     * @return String name
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * This method gets the first name of the member
     * 
     * @return String first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method gets the last name of the member
     * 
     * @return String last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method gets the gender of the member
     * 
     * @return String gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method gets the FoxPlan and returns null of there is none
     * 
     * @return FoxPlan
     */
    public FoxPlan getFoxPlan() {
        return foxPlan;
    }

    /**
     * This method gets the BasicPlan and returns null if there is none
     * 
     * @return BasicPlan
     */
    public BasicPlan getBasicPlan() {
        return basicPlan;
    }

    /**
     * This method is the money setter
     * 
     * @param money double money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * This method withdraws money from the members account
     * 
     * @param money double money
     */
    public void withdraw(double money) {
        this.money = this.money - money;
    }

    /**
     * THis method deposits money into their account
     * 
     * @param money double money
     */
    public void deposit(double money) {
        this.money = this.money + money;
    }

    /**
     * This method is the FoxPlan setter
     * 
     * @param foxPlan FoxPlan
     */
    public void setFoxPlan(FoxPlan foxPlan) {
        this.basicPlan = null;
        this.foxPlan = foxPlan;
    }

    /**
     * This method is the BasicPlan setter
     * 
     * @param basicPlan BasicPlan
     */
    public void setBasicPlan(BasicPlan basicPlan) {
        this.basicPlan = basicPlan;
        this.foxPlan = null;
    }

    /**
     * This method is the first name setter
     * 
     * @param firstNam String first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method is the last name setter
     * 
     * @param lastName String last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method is the points setter
     * 
     * @param points int points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * This method is the gender setter
     * 
     * @param gender String gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method adds points to the current members points
     * 
     * @param points int points
     */
    public void addPoints(int points) {
        this.points = this.points + points;
    }

    /**
     * This method checks to see if the current members is enrolled in the class
     * 
     * @param classes Class that is being checked if the member is enrolled in it
     * @return boolean if the member is in that class
     */
    public boolean findClass(Classes classes) {
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i).getID() == classes.getID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method cancels both plans
     */
    public void cancelAccount() {
        this.foxPlan = null;
        this.basicPlan = null;
    }

    /**
     * This method checks to see if the class ID is in the members list
     * 
     * @param ID int class ID
     * @return boolean if the class ID is in the members class list
     */
    public boolean findClassID(int ID) {
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i).getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method finds the class in the members class list by its ID
     * 
     * @param ID int class ID
     * @return Class that has the corresponding class ID
     */
    public Classes findClassByID(int ID) {
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i).getID() == ID) {
                return this.classes.get(i);
            }
        }
        return null;
    }

    /**
     * This method is the overridden toString od the Member class
     */
    public String toString() {
        String returnable = "Name: " + firstName + " " + lastName + ", ID: " + MyMemberID + ", ";
        if (foxPlan != null) {
            returnable = returnable + "Plan: FoxPlan";
        } else {
            returnable = returnable + "Plan: BasicPlan";
        }
        return returnable;
    }
}
