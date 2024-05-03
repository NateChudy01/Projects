package GymManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Gym {
    private Calendar cal;
    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<Member> members;
    private Concession concession;
    private static double monthlyConcessionSales = 0;
    private static double monthlyMemberSales = 0;
    private ArrayList<Classes> classes;
    private Classes[][] naming;
    private String[][] instructor;
    private Boolean beenShuffled;

    /**
     * No arg constructor
     */
    public Gym() {
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.beenShuffled = false;
        this.instructor = new String[7][7];
        this.naming = new Classes[7][7];
        this.classes = new ArrayList<Classes>();
        this.name = "Marist Gym";
        this.employees = new ArrayList<Employee>();
        this.members = new ArrayList<Member>();
        this.concession = new Concession();
    }

    /**
     * One arg constructor
     * 
     * @param name String name of the gym
     */
    public Gym(String name) {
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.beenShuffled = false;
        this.instructor = new String[7][7];
        this.naming = new Classes[7][7];
        this.classes = new ArrayList<Classes>();
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.members = new ArrayList<Member>();
        this.concession = new Concession();
    }

    /**
     * Four arg constructor
     * 
     * @param name       String name of the gym
     * @param employees  Arraylist of Employees at the gym
     * @param members    Arraylist of Members at the gym
     * @param concession Concession at the gym
     */
    public Gym(String name, ArrayList<Employee> employees, ArrayList<Member> members, Concession concession) {
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.beenShuffled = false;
        this.instructor = new String[7][7];
        this.naming = new Classes[7][7];
        this.classes = new ArrayList<Classes>();
        this.name = name;
        this.employees = employees;
        this.members = members;
        this.concession = concession;
    }

    /**
     * This method gets the classes list at the gym
     * 
     * @return Arraylist of Classes at the gym
     */
    public ArrayList<Classes> getClasses() {
        return classes;
    }

    /**
     * This method gets the 2D array of instructor names of the schedule
     * 
     * @return 2D Array of Strings with the name of the instructors
     */
    public String[][] getInstructor() {
        return instructor;
    }

    /**
     * This method is the instructor names setter
     * 
     * @param instructor 2D Array of Strings of instructor names
     */
    public void setInstructor(String[][] instructor) {
        this.instructor = instructor;
    }

    /**
     * This method is the classes setter
     * 
     * @param classes Arraylist of Classes for the gym
     */
    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    /**
     * This method is the name getter
     * 
     * @return String name of the gym
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the calendar of the gym
     * 
     * @return Calendar of the gym class
     */
    public Calendar getCal() {
        return cal;
    }

    /**
     * This method gets the employee list of the gym
     * 
     * @return Arraylist of Employees
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * This method gets the monthly concession sales
     * 
     * @return double monthly concession sales
     */
    public double getMonthlyConcessionSales() {
        return monthlyConcessionSales;
    }

    /**
     * This method gets the monthly membership costs
     * 
     * @return double membership sales
     */
    public double getMonthlyMemberSale() {
        return monthlyMemberSales;
    }

    /**
     * This method gets the list of members at the gym
     * 
     * @return Arraylist of Members of the gym
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * This method is the setter for the 2D array of classes used for the schedule
     * 
     * @param naming 2D array of Classes
     */
    public void setNaming(Classes[][] naming) {
        this.naming = naming;
    }

    /**
     * This method gets the concession at the gym
     * 
     * @return Concession
     */
    public Concession getConcession() {
        return concession;
    }

    /**
     * This method adds a member to the members list
     * 
     * @param newMember Member to be added to the members list
     */
    public void addMember(Member newMember) {
        members.add(newMember);
    }

    /**
     * This method adds an employee to the employee list
     * 
     * @param newEmployee Employee to be added to the employee list
     */
    public void addEmployee(Employee newEmployee) {
        employees.add(newEmployee);
    }

    /**
     * This method gets the status of if the class schedule has been shuffled
     * 
     * @return boolean been shuffled
     */
    public boolean getBeenShuffled() {
        return this.beenShuffled;
    }

    /**
     * This method shuffles the schedule once and once its done it then does not
     * shuffle once it is called again
     */
    public void shuffleSchedule() {
        if (!beenShuffled) {
            Random random = new Random();
            for (int i = this.naming.length - 1; i > 0; i--) {
                for (int j = this.naming[i].length - 1; j > 0; j--) {
                    int m = random.nextInt(i + 1);
                    int n = random.nextInt(j + 1);

                    Classes temp = this.naming[i][j];
                    this.naming[i][j] = this.naming[m][n];
                    this.naming[m][n] = temp;

                    String temp2 = this.instructor[i][j];
                    this.instructor[i][j] = this.instructor[m][n];
                    this.instructor[m][n] = temp2;
                }
            }
            this.beenShuffled = true;
        }
    }

    /**
     * This method is the name setter
     * 
     * @param name String name of the gym
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is the members list setter
     * 
     * @param members Arraylist of Members
     */
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    /**
     * This method adds to the monthly concession sales
     * 
     * @param money double amount of money to add to the monthly concession sales
     */
    public void addToMonthlyConcessionSales(double money) {
        monthlyConcessionSales = monthlyConcessionSales + money;
    }

    /**
     * This method resets the monthly concession sales
     */
    public void resetMonthlyConcessionSales() {
        monthlyConcessionSales = 0;
    }

    /**
     * This method gets the 2D array of classes for the schedule
     * 
     * @return 2D array of Classes
     */
    public Classes[][] getNaming() {
        return naming;
    }

    /**
     * This method adds to the monthly member sales
     * 
     * @param money double amount to add to the monthly members sales
     */
    public void addToMonthlyMemberSales(double money) {
        monthlyMemberSales = monthlyMemberSales + money;
    }

    /**
     * This method resets the monthly member sales
     */
    public void resetMonthlyMemberSales() {
        monthlyConcessionSales = 0;
    }

    /**
     * This method is the employee list setter
     * 
     * @param employees Arraylist of Employees
     */
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * This method is the concession setter
     * 
     * @param concession Concession
     */
    public void setConcession(Concession concession) {
        this.concession = concession;
    }

    /**
     * This method finds an employee by their employee ID number
     * 
     * @param targetID int employeeID
     * @return Employee that has the targetID
     */
    public Employee findEmployee(int targetID) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == targetID) {
                return employee;
            }
        }
        return null;
    }

    /**
     * This method finds a member in the gym by their memberID
     * 
     * @param memberID int memberID
     * @return Member with the targer memberID
     */
    public Member findMember(int memberID) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberID() == memberID) {
                return members.get(i);
            }
        }
        return null;
    }

    /**
     * This method sets the date for the one on one classes
     */
    public void setDates() {
        for (int i = this.naming.length - 1; i > 0; i--) {
            for (int j = this.naming[i].length - 1; j > 0; j--) {
                if (this.naming[j][i] instanceof OneOnOneClass) {
                    OneOnOneClass currentClass = (OneOnOneClass) this.naming[j][i];
                    currentClass.getCalendar().add(Calendar.DATE, i + 1);
                }
            }
        }
    }

    /**
     * This method is the overridden toString of the gym class
     */
    public String toString() {
        return "Gym Name: " + name + "\nMembers:\n" + members.toString() + "\nEmployees:\n" + employees.toString()
                + "\nCpncession:\n" + concession.toString();
    }
}
