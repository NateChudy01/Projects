package MaristCampus; // puts Classroom.java in the same package as the other classes

import java.lang.Math;

// public class Classroom extends Room{}
// This class's purpose is to be able to create a classroom object that 
// has certain attributes. The starting attributes this class has that is
// in its constructor is the COURSE[] CLASSCOURSE which is a list of courses
// that are taught in the classroom, STRING BOARDTYPE which tells us what
// type of borad the classroom has and, INT NUMDESKS which is an indicator
// of how many desks the classroom has in it
public class Classroom extends Room {
    private Course[] classCourse; // stores the courses
    private String boardType; // stores the type of board in the classroom
    private int numDesks; // stores the number of desks

    public Classroom() { // no parameter constructor
        classCourse = new Course[2]; // adds two basic courses into the classroom list
        classCourse[0] = new Course();
        classCourse[1] = new Course();
        boardType = "Chalk board"; // initalizes the board type to a chalkboard
        numDesks = 30; // sets the number of desks to 30
    }

    public Classroom(Course[] c, String boardType, int numDesks) { // constructor
                                                                   // with parameters
        this.numDesks = numDesks;
        this.boardType = boardType;
        classCourse = new Course[c.length];
        for (int i = 0; i < c.length; i++) {
            classCourse[i] = c[i];
        }
    }

    public Course[] getClassCourse() { // classCourse getter
        return classCourse;
    }

    public String getBoardType() { // boardType getter
        return boardType;
    }

    public int getNumDesks() { // numDesks getter
        return numDesks;
    }

    public void setClassCourse(Course[] c) { // classCourse setter
        this.classCourse = new Course[c.length];
        for (int i = 0; i < c.length; i++) {
            classCourse[i] = c[i];
        }
        // maybe this.classCourse = c, im not sure
    }

    public void setBoardType(String boardType) { // boardType setter
        this.boardType = boardType;
    }

    public void setNumDesks(int numDesks) { // numDesks setter
        this.numDesks = numDesks;
    }

    // public void writeOnBoard(String message)
    // This method's purpose is to "write a message on the board" meaning that is
    // prints a string to the terminal one character at a time. There is only one
    // parameter and it is STRING MESSAGE which is the message that will be written
    // on the board. There is no return type
    public void writeOnBoard(String message) {
        if (message.length() <= 0) { // if there is no string input is prompts this message
            System.out.println("No string was input");
        } else {
            for (int i = 0; i < message.length(); i++) { // loops through each char in the string
                System.out.println(message.charAt(i)); // print each char
            }
        }
    }

    // public String[] shuffleStudents(String[] students)
    // This method's purpose is to take a list of students and return a list of the
    // same students shuffled up. Thre is one parameter and it is STRING[] STUDENTS
    // which is a list of students that will then be returned mixed up. The return
    // type of this method is String[]
    public String[] shuffleStudents(String[] students) {
        String tempSwap = ""; // creates an empty string to use to swap
        int randIndex = 0; // creates a random index variable
        for (int i = 0; i < students.length; i++) { // loops through each student in the list
            randIndex = (int) (Math.random() * students.length); // creates a random number from 0 to students.length
                                                                 // not inluding students.length
            tempSwap = students[i]; // these three lines swap the student at index i with the student at index
                                    // randIndex
            students[i] = students[randIndex];
            students[randIndex] = tempSwap;
        }
        return students; // returns the mixed list of students
    }

    // public boolean areDesksEmpty(Course c)
    // This methods purpose is to check and see if there are any empty desks in the
    // course in the classroom. There is one parameter and is it COURSE which is the
    // course that takes place in the classroom. The return type is a boolean and
    // this function must be called against a Classroom object
    public boolean areDesksEmpty(Course c) {
        if (this.numDesks > c.getNumStudents()) { // checks to see if the number of desks are greater than the number of
                                                  // students
            return true; // if yes, return true
        } else {
            return false; // else return false
        }
    }

    // public String toString()
    // this method is the toString of the Classroom class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        String tostring = "Classroom: " + classCourse.length + " different courses taught in this class:\n";
        int counter = 0;
        for (Course c : classCourse) {
            if (counter != 0) {
                tostring = tostring + "\n            *" + c.toString();
                counter++;
            } else {
                tostring = tostring + "            *" + c.toString();
                counter++;
            }
        }
        return tostring;
    }
}