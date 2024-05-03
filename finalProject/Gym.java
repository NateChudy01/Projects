package finalProject;

import java.util.ArrayList;

public class Gym {
    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<Member> members;
    private Concession concession;

    public Gym() {
        this.name = "Marist Gym";
        this.employees = new ArrayList<Employee>();
        this.members = new ArrayList<Member>();
        this.concession = new Concession();
    }

    public Gym(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.members = new ArrayList<Member>();
        this.concession = new Concession();
    }

    public Gym(String name, ArrayList<Employee> employees, ArrayList<Member> members, Concession concession) {
        this.name = name;
        this.employees = employees;
        this.members = members;
        this.concession = concession;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public ArrayList<Member> getMembers(){
        return members;
    }

    public Concession getConcession(){
        return concession;
    }

    public void addMember(Member newMember){
        members.add(newMember);
    }

    public void addEmployee(Employee newEmployee){
        employees.add(newEmployee);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMembers(ArrayList<Member> members){
        this.members = members;
    }

    public void setEmployees(ArrayList<Employee> employees){
        this.employees = employees;
    }

    public void setConcession(Concession concession){
        this.concession = concession;
    }

    public Employee findEmployee(int targetID){
        for(Employee employee: employees){
            if(employee.getEmployeeID() == targetID){
                return employee;
            }
        }
        return null;
    }

    public String toString(){
        return "Gym Name: " + name + "\nMembers:\n" + members.toString() + "\nEmployees:\n" + employees.toString() + "\nCpncession:\n" + concession.toString();
    }
}
