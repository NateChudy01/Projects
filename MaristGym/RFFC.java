package finalProject;

import java.io.File;
import java.util.*;

public class RFFC {
    public static void main(String args[]) {
        Gym maristGym = new Gym("Marist Gym");
        Concession concession = new Concession();
        maristGym.setConcession(concession);
        Scanner input = new Scanner(System.in);
        try {
            Scanner product = new Scanner(new File("src/finalProject/products.csv"));
            Scanner members = new Scanner(new File("src/finalProject/members.csv"));
            Scanner employees = new Scanner(new File("src/finalProject/employees.csv"));
            maristGym.getConcession().setProducts(createProductList(product, maristGym));
            maristGym.setMembers(createMembers(members, maristGym));
            maristGym.setEmployees(createEmployees(employees, maristGym));
        } catch (Exception e) {
            System.out.println("There was a problem with one of the file inputs");
        }
        while (true) {
            String prompt = "\nWelcome to " + maristGym.getName()
                    + "! \nEnter E for employee mode, M for member mode, or X to leave: ";
            ArrayList<String> letters = new ArrayList<String>(Arrays.asList("E", "M", "X"));
            String entry = correctLetterInput(letters, prompt, input);
            if (entry.equals("E")) {
                while (true) {
                    prompt = "Enter L to login as a current employee, N to create a new employee account, or X to return home: ";
                    letters = new ArrayList<String>(Arrays.asList("N", "L", "X"));
                    entry = correctLetterInput(letters, prompt, input);
                    if (entry.equals("N")) {
                        input.nextLine();
                        Employee currentEmployee = createNewEmployee(input);
                        maristGym.addEmployee(currentEmployee);
                        continue;
                    } else if (entry.equals("L")) {
                        Employee currentEmloyee = findEmployee(input, maristGym);
                        if (currentEmloyee == null) {
                            continue;
                        }
                        while (true) {
                            prompt = "Enter S to sell items, T to track inventory, C to check in members, V to view reports, or X to return to the employee page: ";
                            letters = new ArrayList<String>(Arrays.asList("S", "T", "C", "V", "X"));
                            entry = correctLetterInput(letters, prompt, input);
                            if (entry.equals("S")) {
                                
                            } else if (entry.equals("T")) {

                            } else if (entry.equals("C")) {

                            } else if (entry.equals("V")) {

                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            } else if (entry.equals("M")) {

            } else {
                break;
            }
        }
        System.out.println("\nThank you for coming to the " + maristGym.getName() + "!");
    }

    public static String correctLetterInput(ArrayList<String> letters, String prompt, Scanner scan) {
        System.out.print(prompt);
        String temp = scan.next();
        String list = "";
        while (true) {
            for (int i = 0; i < letters.size(); i++) {
                if (temp.equals(letters.get(i))) {
                    return temp;
                }
                if (i == 0) {
                    list = list + " " + letters.get(i);
                } else {
                    list = list + ", " + letters.get(i);
                }
            }
            System.out.print("Please enter either" + list + ": ");
            list = "";
            temp = scan.next();
        }
    }

    public static int checkUserIntInput(String enter, Scanner scan) {
        boolean valid = false;
        int validInt = 0;
        System.out.print(enter);
        while (!valid) {
            if (!scan.hasNextInt()) {
                scan.next();
                System.out.print("Please enter a number: ");
            } else {
                validInt = scan.nextInt();
                if (validInt <= 0) {
                    System.out.print("Please enter a non negative number: ");
                } else {
                    valid = true;
                }
            }
        }
        return validInt;
    }

    public static Employee findEmployee(Scanner scan, Gym gym) {
        String prompt = "Enter your employee ID: ";
        int employeeCode = checkUserIntInput(prompt, scan);
        if (gym.findEmployee(employeeCode) == null) {
            System.out.println("This employee does not exist");
        }
        return gym.findEmployee(employeeCode);
    }

    public static Employee createNewEmployee(Scanner scan) {
        System.out.print("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter your gender: ");
        String gender = scan.nextLine();
        Employee newEmployee = new Employee(firstName, lastName, gender);
        System.out.println("Your Employee ID number is: " + newEmployee.getEmployeeID() + "\n");
        return newEmployee;
    }

    public static ArrayList<Product> createProductList(Scanner input, Gym gym) {
        ArrayList<Product> returnable = new ArrayList<Product>();
        input.useDelimiter("/n");
        input.nextLine();
        while (input.hasNext()) {
            String[] line = stripSpaces(input.nextLine().split(","));
            returnable.add(new Product(line[0], gym.getConcession(), Double.parseDouble(line[1]), Integer.parseInt(line[2])));
        }
        return returnable;
    }

    public static ArrayList<Member> createMembers(Scanner input, Gym gym) {
        ArrayList<Member> returnable = new ArrayList<Member>();
        input.useDelimiter("/n");
        input.nextLine();
        while (input.hasNext()) {
            String[] line = stripSpaces(input.nextLine().split(","));
            returnable.add(new Member(line[0], line[1], line[2]));
        }
        return returnable;
    }

    public static ArrayList<Employee> createEmployees(Scanner input, Gym gym) {
        ArrayList<Employee> returnable = new ArrayList<Employee>();
        input.useDelimiter("/n");
        input.nextLine();
        while (input.hasNext()) {
            String[] line = stripSpaces(input.nextLine().split(","));
            returnable.add(new Employee(line[0], line[1], line[2]));
        }
        return returnable;
    }

    public static String[] stripSpaces(String[] s) {
        String[] returnable = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            returnable[i] = s[i].strip();
        }
        return returnable;
    }
}
