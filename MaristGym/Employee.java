package finalProject;

public class Employee {
    private static int EmployeeID = 0;
    private int MyEmployeeID;
    private String firstName;
    private String lastName;
    private String gender;

    public Employee(){
        EmployeeID++;
        this.MyEmployeeID = EmployeeID;
        this.firstName = "John";
        this.lastName = "Doe";
        this.gender = "Male";
    }

    public Employee(String firstName, String lastName, String gender){
        EmployeeID++;
        this.MyEmployeeID = EmployeeID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmployeeID(){
        return MyEmployeeID;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
}
