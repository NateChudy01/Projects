package GymManagement;

import java.io.Serializable;

public class Employee implements Serializable {
    private static int EmployeeID = 0;
    private int MyEmployeeID;
    private String firstName;
    private String lastName;
    private String gender;

    /**
     * No arg constructor
     */
    public Employee() {
        EmployeeID++;
        this.MyEmployeeID = EmployeeID;
        this.firstName = "John";
        this.lastName = "Doe";
        this.gender = "Male";
    }

    /**
     * Three arg constructor
     * 
     * @param firstName String first name of the employee
     * @param lastName  String last name of the employee
     * @param gender    String gender of the employee
     */
    public Employee(String firstName, String lastName, String gender) {
        EmployeeID++;
        this.MyEmployeeID = EmployeeID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * This method gets the employee ID of the current employee
     * 
     * @return int employee ID
     */
    public int getEmployeeID() {
        return MyEmployeeID;
    }

    /**
     * This method gets the name of the employee
     * 
     * @return String full name of the employee
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * This method gets the first name of the employee
     * 
     * @return String first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method gets the last name of the employee
     * 
     * @return String last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method gets the gender of the employee
     * 
     * @return String gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method is the first name setter
     * 
     * @param firstName String first name
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
     * This method is the gender setter
     * 
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method is the overridden toString of the employee class
     */
    public String toString() {
        return "Name: " + firstName + " " + lastName + ", Gender: " + gender;
    }
}
