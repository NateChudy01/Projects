package GymManagement;

import java.util.ArrayList;

public class Classes {
    private String className;
    private String instructor;
    private ArrayList<Member> members;
    private int guests;
    private String time;
    private static int classID = 0;
    private int myID;
    private String dayOfWeek;

    /**
     * No args constructor
     */
    public Classes() {
        classID++;
        this.guests = 0;
        this.dayOfWeek = "";
        this.myID = classID;
        this.time = "";
        this.className = "";
        this.instructor = "";
        this.members = new ArrayList<Member>();
    }

    /**
     * Two arg constructor
     * 
     * @param className  String of the name of the class
     * @param instructor String of the name of the instructor
     */
    public Classes(String className, String instructor) {
        this.guests = 0;
        classID++;
        this.dayOfWeek = "";
        this.myID = classID;
        this.time = "";
        this.className = className;
        this.instructor = instructor;
        this.members = new ArrayList<Member>();
    }

    /**
     * Four arg constructor
     * 
     * @param className  String of the class name
     * @param instructor String of the instructor name
     * @param members    Arraylist of Members in the class
     * @param time       String of the display of the time of the day
     */
    public Classes(String className, String instructor, ArrayList<Member> members, String time) {
        classID++;
        this.guests = 0;
        this.dayOfWeek = "";
        this.myID = classID;
        this.time = time;
        this.className = className;
        this.instructor = instructor;
        this.members = members;
    }

    /**
     * This method gets the number of guests
     * 
     * @return int guests
     */
    public int getGuests() {
        return guests;
    }

    /**
     * This method gets the name of the class
     * 
     * @return String of the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * This method gets the ID number of the class
     * 
     * @return in ID of the class
     */
    public int getID() {
        return myID;
    }

    /**
     * This method gets the day of the week
     * 
     * @return String representation of the day of the week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * This method is the day of the week setter
     * 
     * @param dayOfWeek String day of the week
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * This method gets the time of the class
     * 
     * @return String representation of the time
     */
    public String getTime() {
        return time;
    }

    /**
     * This method enrolls a member in this current class
     * 
     * @param member Member that is enrolling in the current class
     */
    public void enroll(Member member) {
        this.members.add(member);
    }

    /**
     * This method is the time setter
     * 
     * @param time String time representation
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * This method gets the instructors name
     * 
     * @return String instructor name
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * This method gets the members in the class
     * 
     * @return Arraylist of Members in the class
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * This method is the class name setter
     * 
     * @param className String class name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * This method is the instructor setter
     * 
     * @param instructor String instructor name
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * This method is the members setter
     * 
     * @param members Arraylist of Members in the class
     */
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    /**
     * This method is the guest setter
     * 
     * @param guests int value of the number of guests
     */
    public void setGuests(int guests) {
        this.guests = guests;
    }

    /**
     * This method is the overridden toString for the Classes class
     */
    public String toString() {
        String returnable = "Class Name: " + className + "\nInstructor: " + instructor + "\n";
        for (Member member : members) {
            returnable = returnable + member.toString();
        }
        return returnable;
    }
}
