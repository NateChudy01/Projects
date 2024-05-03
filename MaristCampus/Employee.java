package MaristCampus; // puts Employee.java in the same package as the other classes

// public class Employee
// this class was created in order to make an Employee object. This employee
// will then be used in the office object. Each employee has their own
// attibutes such as INT HEIGHT which is the measure of the employees
/// height in inches, INT WEIGHT which is the weight of the employee in
// pounds, and BOOLEAN MALE which is a variable which store the boolean
// value of if the employee is a male (true means the employee is a male
// and false means the employee is a female)
public class Employee {
    private int height; // stores the employees height in inches
    private int weight; // stores the employees weight in lbs
    private boolean male; // stores a boolean vaslue to tell the gender of
                          // the employee

    public Employee() { // no parameter constructor
        height = 65; // initializes the height to 65 inches
        weight = 185; // initializes the weight to 185 lbs
        male = true; // sets the generic employee to a male
    }

    public Employee(int height, int weight, boolean male) { // constructor with parameters
        this.height = height;
        this.weight = weight;
        this.male = male;
    }

    public int getHeight() { // height getter
        return height;
    }

    public int getWeight() { // weight getter
        return weight;
    }

    public boolean getGender() { // gender getter
        return male; // this returns true if the employee is a male and
                     // false if the employee is a female
    }

    public void setHeight(int height) { // height setter
        this.height = height;
    }

    public void setWeight(int weight) { // weight setter
        this.weight = weight;
    }

    public void setGender(boolean male) { // gender setter
        this.male = male; // set true for male and false for female
    }

    // public String toString()
    // this method is the toString of the Classroom class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        if (male) {
            return "This employee is a male";
        } else {
            return "This employee is a female";
        }
    }
}