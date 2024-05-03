package GymManagement;

import java.util.Calendar;
import java.util.Date;

public class OneOnOneClass extends Classes {
    private boolean full;
    private Member member;
    private Calendar cal;

    /**
     * No arg constructor
     */
    public OneOnOneClass() {
        super();
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.member = null;
        this.full = false;
    }

    /**
     * Two arg constructor
     * 
     * @param className  String of the one on one class name
     * @param instructor String of the instructors name
     */
    public OneOnOneClass(String className, String instructor) {
        super(className, instructor);
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.member = null;
        this.full = false;
    }

    /**
     * Three arg constructor
     * 
     * @param className  String name of the class
     * @param instructor String name of the instructor
     * @param member     Member
     */
    public OneOnOneClass(String className, String instructor, Member member) {
        super(className, instructor);
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.member = member;
        this.full = true;
    }

    /**
     * This method adds a member to the one on one class
     * 
     * @param member Member to be added
     * @return boolean if the members was added
     */
    public boolean addMember(Member member) {
        if (!full) {
            this.member = member;
            return true;
        } else {
            System.out.println("This One on One class already has someone taking it");
            return false;
        }
    }

    /**
     * This method removes a member from the one on one class
     * 
     * @return boolean if the members was succesfully removed
     */
    public boolean removeMember() {
        if (full) {
            this.member = null;
            return true;
        } else {
            System.out.println("There is no one taking the class");
            return false;
        }
    }

    /**
     * This method gets the status of how full the one on one class
     * 
     * @return boolean if the class is full
     */
    public boolean isFull() {
        return full;
    }

    /**
     * This method gets the calendar of the one on one class
     * 
     * @return Calendar cal
     */
    public Calendar getCalendar() {
        return cal;
    }

    /**
     * This method gets the memeber of the one on one class
     * 
     * @return Member member
     */
    public Member getMember() {
        return member;
    }

    /**
     * This method is the overridden toString of the OneOnOne class
     */
    public String toString() {
        if (member != null) {
            return "Class: " + super.getClassName() + ", Instructor: " + super.getInstructor() + ", Member: "
                    + member.getFirstName();
        }
        return "Class: " + super.getClassName() + ", Instructor: " + super.getInstructor() + ", Member: No one";
    }
}
