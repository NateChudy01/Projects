package assignment3; // puts Office.java in the same package as the other classes

import java.lang.Math;

// public class Office extends Room
// the purpose of the Office class is to be able to create an office
// that has its own list of attributes. These attributes being
// BOOLEAN VIEW which stores the value of if the office has a view or not, 
// BOOLEAN CORNEROFFICE which stores the value of if you have a corner
// office, and EMPLOYEE[] EMPLOYEES which is a list of Employee objects
// being the employees in the office
public class Office extends Room {
    private boolean view; // stores if office has a view
    private boolean cornerOffice; // stores if you have a corner office
    private Employee[] employees; // stores employees in office

    public Office() { // no parameter constructor
        view = true;
        cornerOffice = true;
        employees = new Employee[3];
        employees[0] = new Employee();
        employees[1] = new Employee();
        employees[2] = new Employee();
    }

    public Office(Employee[] employees, boolean view, boolean cornerOffice) { // constructor with parameters
        this.view = view;
        this.cornerOffice = cornerOffice;
        this.employees = new Employee[employees.length];
        for (int i = 0; i < employees.length; i++) {
            this.employees[i] = employees[i];
        }
    }

    public boolean getView() { // view getter
        return view;
    }

    public boolean getCornerOffice() { // view getter
        return cornerOffice;
    }

    public Employee[] getEmployees() { // employees getter
        return employees;
    }

    public void setView(boolean view) { // view setter
        this.view = view;
    }

    public void setCornerOffice(boolean cornerOffice) { // cornerOffice setter
        this.cornerOffice = cornerOffice;
    }

    public void setEmployees(Employee[] employees) { // employee setter
        this.employees = new Employee[employees.length];
        for (int i = 0; i < employees.length; i++) {
            this.employees[i] = employees[i];
        }
    }

    // public String[] shuffleToDoList(String[] toDo)
    // this method's is to shuffle a list of items in a todo list. This method takes
    // in one parameter which is STRING[] TODO which is a list of Strings that act
    // as a todo list. This method returns a String[] which is the shuffled todo
    // list
    public String[] shuffleToDoList(String[] toDo) {
        int randNum = 0; // random number to help shuffle indexes
        String switchTemp = ""; // temporary string to help swap
        for (int i = 0; i < toDo.length; i++) { // loops through the list
            randNum = (int) (Math.random() * toDo.length); // generates random number to use at the random index
            switchTemp = toDo[i]; // these three lines swap the value at index i with the value at the random
                                  // index allowing for the list to be shuffled
            toDo[i] = toDo[randNum];
            toDo[randNum] = switchTemp;
        }
        return toDo; // returns the shuffled list
    }

    // public void changeOffice()
    // this method's purpose is to change the attributes of an office bject randomly
    // including the view and cornerOffice values. This method has no parameters and
    // has no return type
    public void changeOffice() {
        int randView = (int) (Math.random() * 2); // generates either a 1 or a 0 (Math.random() is exclusive meaning
                                                  // that it will not generate a 2)
        if (randView == 0) { // if the random number is a zero set the vew to false
            view = false;
        } else {
            view = true; // if the random number is a 1 set the view to true
        }
        int randCornerOffice = (int) (Math.random() * 2); // this section of code does the same thing as the above part
                                                          // but with the cornerOffice variable. There is however a
                                                          // different random number making the view and cornerOffice
                                                          // variables independent of each other
        if (randCornerOffice == 0) {
            cornerOffice = false;
        } else {
            cornerOffice = true;
        }
    }

    // public void addOfficeThing(String thing)
    // this method's purpose is to print a statement to the terminal of something
    // being added to the office. This method takes in one parameter STRING THING
    // which is just the thing the user wants o add to the office. There is no
    // return type
    public void addOfficeThing(String thing) {
        System.out.println("Adding a " + thing + " to the office!"); // there is only a print statement and it doesnt
                                                                     // really add the random thing to the office object
    }

    // public String toString()
    // this method is the toString of the Office class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        return "Office: " + employees.length + " people in it";
    }
}