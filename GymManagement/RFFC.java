package GymManagement;

import java.io.File;
import java.util.*;

/**
 * This class is created as an interface of a Gym. It includes an employee mode
 * and a member mode, each with their respective pathways
 */
public class RFFC {
    public static void main(String args[]) {
        Gym maristGym = new Gym("Marist Gym");
        // maristGym.getCal().add(Calendar.DATE, 8);
        // here to show that the scheduling works for one on one classes
        // to test, uncomment and register to the class with Nate as the instructor
        // and mess around with the time
        Concession concession = new Concession();
        maristGym.setConcession(concession);
        Scanner input = new Scanner(System.in);
        try {
            Scanner classes = new Scanner(new File("src/finalProject/classes.csv"));
            Scanner product = new Scanner(new File("src/finalProject/products.csv"));
            Scanner members = new Scanner(new File("src/finalProject/members.csv"));
            Scanner employees = new Scanner(new File("src/finalProject/employees.csv"));
            maristGym.setClasses(createClassList(classes, maristGym));
            maristGym.getConcession().setProducts(createProductList(product, maristGym));
            maristGym.setMembers(createMembers(members, maristGym));
            maristGym.setEmployees(createEmployees(employees, maristGym));
        } catch (Exception e) {
            System.out.println("There was a problem with one of the file inputs");
        }
        // The lines above create the gym, concession, and also takes in 4 csv files for
        // the employees, members, products, and the different classes
        while (true) { // System while loop
            String prompt = "\nWelcome to " + maristGym.getName()
                    + "!\n \nE: Employee Mode\nM: Member Mode\nX: Leave\n \nEnter Here: ";
            ArrayList<String> letters = new ArrayList<String>(Arrays.asList("E", "M", "X"));
            String entry = correctLetterInput(letters, prompt, input);
            if (entry.equals("E")) { // User wants to go to Employee mode
                while (true) {
                    prompt = "\n \nEmployee Mode:\n \nL: login as a current employee\nN: create a new employee account\nX: return home\n \nEnter Here: ";
                    letters = new ArrayList<String>(Arrays.asList("N", "L", "X"));
                    entry = correctLetterInput(letters, prompt, input);
                    if (entry.equals("N")) { // User wants to create a new Employee account
                        input.nextLine();
                        Employee currentEmployee = createNewEmployee(input);
                        maristGym.addEmployee(currentEmployee);
                        continue;
                    } else if (entry.equals("L")) { // User wants to load into a current Employee account
                        Employee currentEmloyee = findEmployee(input, maristGym);
                        if (currentEmloyee == null) {
                            continue;
                        }
                        while (true) { // Employee while loop
                            prompt = "\n \nEmployee Mode:\n \nS: sell items\nT: track inventory\nCI: check in members\nCO: check out members\nV: view reports\nX: return to the employee page\n \nEnter Here:  ";
                            letters = new ArrayList<String>(Arrays.asList("S", "T", "CI", "CO", "V", "X"));
                            entry = correctLetterInput(letters, prompt, input);
                            // if statement below corresponds to what the user wants to do is described in
                            // the lines above
                            if (entry.equals("S")) {
                                sellItemToMember(maristGym, input);
                            } else if (entry.equals("T")) {
                                trackInventory(maristGym, input);
                            } else if (entry.equals("CI")) {
                                checkInMember(maristGym, input);
                            } else if (entry.equals("V")) {
                                viewReport(maristGym);
                            } else if (entry.equals("CO")) {
                                checkOutMember(maristGym, input);
                            } else {
                                // if X is input
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            } else if (entry.equals("M")) { // User wants to go to Member mode
                while (true) { // User while loop
                    prompt = "\n \nMember Mode:\n \nL: login as a current member\nN: create a new member account\nX: return home\n \nEnter Here:  ";
                    letters = new ArrayList<String>(Arrays.asList("N", "L", "X"));
                    entry = correctLetterInput(letters, prompt, input);
                    if (entry.equals("N")) { // User wants to create a new Member
                        Member currentMember = createNewMember(input);
                        maristGym.addMember(currentMember);
                        continue;
                    } else if (entry.equals("L")) { // User wants to log into an existing Member
                        Member currentMember = findMember(input, maristGym);
                        if (currentMember == null) {
                            // makes sure to check if the user exists before continuing
                            continue;
                        }
                        // If statements below are here to check if the user that logged in currently is
                        // registered to a plan and goes accordingly, if they are not then it prompts
                        // them to register for one
                        if (currentMember.getBasicPlan() == null && currentMember.getFoxPlan() == null) {
                            prompt = "\nYou recently cancelled your plan, you need to reregister for a plan to enter the Gym\n \nFoxPlan: register for the FoxPlan\nBasicPlan: register for the BasicPlan\nX: exit\n \nEnter Here: ";
                            letters = new ArrayList<String>(Arrays.asList("BasicPlan", "FoxPlan", "X"));
                            entry = correctLetterInput(letters, prompt, input);
                            if (entry.equals("BasicPlan")) {
                                currentMember.setBasicPlan(new BasicPlan());
                                System.out.println("Registering you for the BasicPlan!");
                            } else if (entry.equals("FoxPlan")) {
                                currentMember.setFoxPlan(new FoxPlan());
                                System.out.println("Registering you for the FoxPlan!");
                            } else {
                                continue;
                            }
                        }
                        while (true) { // Member actions while loop
                            prompt = "\n \nMember Mode:\n \nV: view class the schedule\nR: regester for a class\nU: unregister for a class\nL: look at your current class list\nS: switch plans\nC: cancel plan\nF: find current plan\nX: return home\n \nEnter Here:  ";
                            letters = new ArrayList<String>(Arrays.asList("S", "V", "R", "X", "C", "U", "L", "F"));
                            entry = correctLetterInput(letters, prompt, input);
                            // if statement below corresponds to what the user wants to do is described in
                            // the lines above
                            if (entry.equals("R")) {
                                regesterForClass(maristGym, input, currentMember);
                            } else if (entry.equals("V")) {
                                viewCalendarSchedule(maristGym);
                            } else if (entry.equals("S")) {
                                switchPlan(currentMember, maristGym, input);
                            } else if (entry.equals("C")) {
                                closePlan(currentMember, maristGym, input);
                                break;
                            } else if (entry.equals("U")) {
                                unregister(maristGym, input, currentMember);
                            } else if (entry.equals("F")) {
                                findCurrentPlan(input, currentMember);
                            } else if (entry.equals("L")) {
                                printClassList(maristGym, currentMember);
                            } else {
                                break;
                            }
                        }
                        if (entry.equals("C")) {
                            // if the Member closes their plan it then must take them back to the home
                            // screen to reregister
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        System.out.println("\nThank you for coming to the " + maristGym.getName() + "!");
    }

    /**
     * This method is used to make sure the user inputs something from a list of
     * possibly correct inputs, one example being that they have to input "Y" or "N"
     * to a yes or no questions and this method ensures that
     * 
     * @param letters List of strings that are the options that the user must input
     * @param prompt  String that is the prompt that is asked to the user
     * @param scan    Scanner that reads the users input
     * @return String that is in the list of possibly correct inputs in letters
     */
    public static String correctLetterInput(ArrayList<String> letters, String prompt, Scanner scan) {
        System.out.print(prompt);
        String temp = scan.nextLine();
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
            temp = scan.nextLine();
        }
    }

    /**
     * This method checks to make sure that the user inputs a non negative int when
     * prompted by a scanner
     * 
     * @param enter String that is used as the prompt
     * @param scan  Scanner to read the input of the user
     * @return int that is non negative
     */
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
                if (validInt < 0) {
                    System.out.print("Please enter a non negative number: ");
                } else {
                    valid = true;
                }
            }
        }
        scan.nextLine();
        return validInt;
    }

    /**
     * This method prompts the user to input their employee information and finds
     * the employee in the gym, but if the employee does not exist it returns null
     * 
     * @param scan Scanner to read the users input
     * @param gym  Gym that we want to find the specific employee in
     * @return Employee that the user is looking for
     */
    public static Employee findEmployee(Scanner scan, Gym gym) {
        System.out.print("Enter employees first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter employees last name: ");
        String lastName = scan.nextLine();
        String prompt = "Enter your employee ID: ";
        int employeeCode = checkUserIntInput(prompt, scan);
        if (gym.findEmployee(employeeCode) == null || !gym.findEmployee(employeeCode).getFirstName().equals(firstName)
                || !gym.findEmployee(employeeCode).getLastName().equals(lastName)) {
            System.out.println("This employee does not exist");
            return null;
        }
        return gym.findEmployee(employeeCode);
    }

    /**
     * This method is used to create a new employee from user input
     * 
     * @param scan Scanner to read the users input
     * @return Employee that the user is trying to create
     */
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

    /**
     * This method is specifically here to read Products from a csv file
     * "products.csv", the scanner that is input in this method is the scanner for
     * this csv file
     * 
     * @param input Scanner with "products.csv" in it allowing it to create the list
     *              into Products
     * @param gym   Gym we want to add each product to
     * @return Arraylist of Products that will be used in the concession
     */
    public static ArrayList<Product> createProductList(Scanner input, Gym gym) {
        ArrayList<Product> returnable = new ArrayList<Product>();
        input.useDelimiter("/n");
        input.nextLine();
        while (input.hasNext()) {
            String[] line = stripSpaces(input.nextLine().split(","));
            returnable.add(
                    new Product(line[0], gym.getConcession(), Double.parseDouble(line[1]), Integer.parseInt(line[2])));
        }
        return returnable;
    }

    /**
     * This method is specifically here to read Members from a csv file
     * "members.csv", the scanner that is input in this method is the scanner for
     * this csv file
     * 
     * @param input Scanner with "members.csv" in it allowing it to create the list
     *              into Members
     * @param gym   Gym we want to add each Member to
     * @return Arraylist of Members
     */
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

    /**
     * This method is specifically here to read Employees from a csv file
     * "employees.csv", the scanner that is input in this method is the scanner for
     * this csv file
     * 
     * @param input Scanner with "employees.csv" in it allowing it to create the
     *              list into Employees
     * @param gym   Gym we want to add each Employee to
     * @return Arraylist of Employees
     */
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

    /**
     * This method strips the spaces from each element that is read when taking in a
     * csv file
     * 
     * @param s Array of Strings that we would get from a csv file that we want to
     *          strip the spaces from
     * @return Array of Strings that is the same as the inital Array but with no
     *         spaces
     */
    public static String[] stripSpaces(String[] s) {
        String[] returnable = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            returnable[i] = s[i].trim();
        }
        return returnable;
    }

    /**
     * This method sells an item to a member from the concession at the gym, it
     * takes into account points, the list of products, inventory, as well as it
     * finds the member from the user input
     * 
     * @param gym  Gym that we are selling products from in its concession
     * @param scan Scanner to read the users input
     */
    public static void sellItemToMember(Gym gym, Scanner scan) {
        double discountMoney = 0.0;
        System.out.print("Enter members first name: ");
        String firstName = scan.next();
        System.out.print("Enter members last name: ");
        String lastName = scan.next();
        int memberID = checkUserIntInput("Enter your member ID: ", scan);
        if (gym.findMember(memberID).getFirstName().equals(firstName)
                && gym.findMember(memberID).getLastName().equals(lastName)) {
            System.out
                    .print("\nWhat would you like to purchase:\n" + gym.getConcession().toString() + "\nEnter here: ");
            String item = scan.nextLine();
            String itemName = validateItem(gym, scan, item);
            int amount = checkUserIntInput("How many would you like?: ", scan);
            if (gym.findMember(memberID).getPoints() > 0) {
                String prompt = "Would you like to use your " + gym.findMember(memberID).getPoints()
                        + " points? [Y/N]: ";
                ArrayList<String> letters = new ArrayList<String>(Arrays.asList("N", "Y"));
                String entry = correctLetterInput(letters, prompt, scan);
                if (entry.equals("Y")) {
                    discountMoney = (gym.findMember(memberID).getPoints() / 100.0);
                }
            }
            purchaseItem(itemName, amount, gym, memberID, discountMoney);
        } else {
            System.out.println("Member does not exist");
        }
    }

    /**
     * This method is used in the previous sellItemToMember() method and it
     * basically takes in a String item we are trying to find and either it finds
     * the item and returns it or it does not and prompts the user to enter the name
     * of a product that is in the concession
     * 
     * @param gym  Gym we want to buy the product from
     * @param scan Scanner to read the users input
     * @param item String name of the item the user wants to purchase
     * @return String name of the item that the user wants to purchase that also is
     *         in the concession
     */
    public static String validateItem(Gym gym, Scanner scan, String item) {
        String temp = item;
        while (true) {
            for (int i = 0; i < gym.getConcession().getProducts().size(); i++) {
                if (temp.equalsIgnoreCase(gym.getConcession().getProducts().get(i).getName())) {
                    return temp;
                }
            }
            System.out.print("This item is not in the concession, please enter something else: ");
            temp = scan.nextLine();
        }
    }

    /**
     * This method is used in the previous sellItemToMember() method and it called
     * when we know exactly which product is getting purchased also in what amount
     * and who is purchasing it and then it updates the concession list
     * 
     * @param itemName      String name of the item that the Member wants to
     *                      purchase
     * @param amount        int amount that the user wants to purchase of the
     *                      product
     * @param gym           Gym that the member is buying this product from
     * @param memberID      int ID of the member that is purchasing the product
     * @param discountMoney double of how much discount money the member is going to
     *                      use corresponding to the number of points the user has
     *                      in their account from checking into the gym
     */
    public static void purchaseItem(String itemName, int amount, Gym gym, int memberID, double discountMoney) {
        int points = (int) discountMoney * 100;
        double cost = getCostOfItem(itemName, amount, gym, memberID, discountMoney, points);
        if (gym.getConcession().findProductFromName(itemName).getAmount() >= amount) {
            gym.getConcession().findProductFromName(itemName).remove(amount);
            gym.findMember(memberID).withdraw(gym.getConcession().findProductFromName(itemName).getCost() * amount);
            System.out.println("\n" + gym.findMember(memberID).getFirstName() + " just purchased " + amount + " "
                    + itemName + "'s worth $" + cost);
            gym.addToMonthlyConcessionSales(cost);
        } else {
            System.out.println("There is not enough " + itemName + " to purchase");
        }
    }

    /**
     * This method prints the inventory of the concession stand for the employee to
     * look at and possible restock if necessary
     * 
     * @param gym  Gym of the products
     * @param scan Scanner to read the users input
     */
    public static void trackInventory(Gym gym, Scanner scan) {
        while (true) {
            System.out.println("\nInventory:\n" + gym.getConcession().toString());
            String prompt = "Would you like to restock? [Y/N]: ";
            ArrayList<String> letters = new ArrayList<String>(Arrays.asList("N", "Y"));
            String entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                System.out.print("Which item would you like to restock?: ");
                String item = scan.nextLine();
                String itemName = validateItem(gym, scan, item);
                int amount = checkUserIntInput("How many would you like to restock: ", scan);
                gym.getConcession().findProductFromName(itemName)
                        .setAmount(gym.getConcession().findProductFromName(itemName).getAmount() + amount);
            } else {
                break;
            }
        }
    }

    /**
     * This method is used by the employee and it finds a member of the gym and
     * checks them in. This also takes care of cases where the user is already
     * checked in, if the user wants to bring guests in and others
     * 
     * @param gym  Gym we are checking the member into
     * @param scan Scanner to read the user input
     */
    public static void checkInMember(Gym gym, Scanner scan) {
        System.out.print("Enter members first name: ");
        String firstName = scan.next();
        System.out.print("Enter members last name: ");
        String lastName = scan.next();
        int memberID = checkUserIntInput("Enter your member ID: ", scan);
        if (!gym.findMember(memberID).getFirstName().equals(firstName)
                || !gym.findMember(memberID).getLastName().equals(lastName)) {
            System.out.println("Member does not exist");
            return;
        }
        String prompt = "Hi " + gym.findMember(memberID).getFirstName() + ", are you bringing in any guests? [Y/N]: ";
        ArrayList<String> letters = new ArrayList<String>(Arrays.asList("Y", "N"));
        String entry = correctLetterInput(letters, prompt, scan);
        if (entry.equals("Y") && gym.findMember(memberID).getBasicPlan() != null) {
            entry = "How many guests are you bringing in: ";
            int numGuests = checkUserIntInput(entry, scan);
            prompt = "This will cost you an extra $" + 10 * numGuests + " [Y/N]: ";
            letters = new ArrayList<String>(Arrays.asList("Y", "N"));
            entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                gym.addToMonthlyMemberSales(10.0 * numGuests);
                System.out.println("Adding your " + numGuests + " guests");
            } else {
                System.out.println("Not checking your guests in");
            }
        } else if (entry.equals("Y") && gym.findMember(memberID).getFoxPlan() != null) {
            System.out.println("You are allowed to bring in as many guests as you would like with the FoxPlan");
        }
        if (gym.findMember(memberID).getFirstName().equals(firstName)
                && gym.findMember(memberID).getLastName().equals(lastName)) {
            if (gym.findMember(memberID).getCheckedIn() == true) {
                System.out.println("You are already checked in");
            } else {
                try {
                    gym.findMember(memberID).setCheckedIn(true);
                    gym.findMember(memberID).addPoints(10);
                    System.out.println("You have been checked in, have a good workout " + firstName + "!");
                } catch (MemberAlreadyCheckedInException e) {
                }
            }
        } else {
            System.out.println("Member does not exist");
        }
    }

    /**
     * This method is used when a person walks into the gym and does not have a
     * membership plan, it prompts them for information and then creates an account
     * for them
     * 
     * @param scan Scanner to read the users input
     * @return Member that was created from the information given by the user
     */
    public static Member createNewMember(Scanner scan) {
        System.out.print("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter your gender: ");
        String gender = scan.nextLine();
        String prompt = "Which plan would you like to subscribe to? [Fox/Basic]: ";
        ArrayList<String> types = new ArrayList<String>(Arrays.asList("Fox", "Basic"));
        String program = correctLetterInput(types, prompt, scan);
        Member newMember = new Member(firstName, lastName, gender);
        if (program.equalsIgnoreCase("Fox")) {
            newMember.setBasicPlan(null);
            newMember.setFoxPlan(new FoxPlan());
        }
        System.out.println("Your Member ID number is: " + newMember.getMemberID() + "\n");
        return newMember;
    }

    /**
     * This method prints a report of the gym to the employee, it includes the
     * monthly profits by the membership/concession/extra fees, and also includes a
     * list of all members and what plan they have, and how much they are paying
     * 
     * @param gym Gym that we want the report of
     */
    public static void viewReport(Gym gym) {
        for (Member member : gym.getMembers()) {
            if (member.getFoxPlan() != null) {
                gym.addToMonthlyMemberSales(member.getFoxPlan().getCostPerMonth());
            } else {
                gym.addToMonthlyMemberSales(member.getBasicPlan().getCostPerMonth());
            }
        }
        System.out.println();
        System.out.println("Members:");
        for (Member member : gym.getMembers()) {
            if (member.getBasicPlan() != null) {
                System.out.println("Member Name: " + member.getLastName() + ", " + member.getFirstName()
                        + "; Member ID: " + member.getMemberID() + "; Payment: $9.99/Month");
            } else {
                System.out.println("Member Name: " + member.getLastName() + ", " + member.getFirstName()
                        + "; Member ID: " + member.getMemberID() + "; Payment: $19.99/Month");
            }
        }
        System.out.println();
        System.out.printf("Member payments: $%.2f this month\n", gym.getMonthlyMemberSale());
        System.out.println("Concession payments: $" + gym.getMonthlyConcessionSales() + " this month");
    }

    /**
     * This method is used when a member is purchasing an item or a number of items
     * and is also using their points for the purchase. It calculated out how much
     * the user is purchasing at one time and then applies the max number of points
     * it can use for the purchase to take the money off the items
     * 
     * @param itemName      String name of item that is being purchased
     * @param amount        int amount of the item we are purchasing
     * @param gym           Gym that we are purchasing the item from
     * @param memberID      int ID of the member that is purchasing this amount of
     *                      items
     * @param discountMoney double amount of discount money that is being used
     * @param points        int number of points that the user has
     * @return double of how much the transaction will cost the Member
     */
    public static double getCostOfItem(String itemName, int amount, Gym gym, int memberID, double discountMoney,
            int points) {
        if ((gym.getConcession().findProductFromName(itemName).getCost() * amount) >= discountMoney) {
            gym.findMember(memberID).setPoints(0);
            return (gym.getConcession().findProductFromName(itemName).getCost() * amount) - discountMoney;
        } else {
            gym.findMember(memberID).setPoints(gym.findMember(memberID).getPoints() - points);
            return 0.0;
        }
    }

    /**
     * This method is used by the employee and it finds a member of the gym and
     * checks them out. This also takes care of case where the user is already
     * checked out
     * 
     * @param gym  Gym that the member is checking out of
     * @param scan Scanner to read the users input
     */
    public static void checkOutMember(Gym gym, Scanner scan) {
        System.out.print("Enter members first name: ");
        String firstName = scan.next();
        System.out.print("Enter members last name: ");
        String lastName = scan.next();
        int memberID = checkUserIntInput("Enter your member ID: ", scan);
        if (gym.findMember(memberID).getFirstName().equals(firstName)
                && gym.findMember(memberID).getLastName().equals(lastName)) {
            if (gym.findMember(memberID).getCheckedIn() == false) {
                System.out.println("You are already checked out");
            } else {
                try {
                    gym.findMember(memberID).setCheckedIn(false);
                    System.out.println("You have been checked out, have a good day " + firstName + "!");
                } catch (MemberAlreadyCheckedInException e) {
                }
            }
        } else {
            System.out.println("Member does not exist");
        }
    }

    /**
     * This method is the error checking for the findMember() method in the Gym
     * class making sure the member exists before finding them but also allows us to
     * prompt the user directly using a scanner
     * 
     * @param scan Scanner to read the users input
     * @param gym  Gym to find the member in
     * @return Member that was being looked for
     */
    public static Member findMember(Scanner scan, Gym gym) {
        System.out.print("Enter members first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter members last name: ");
        String lastName = scan.nextLine();
        String prompt = "Enter your member ID: ";
        int memberCode = checkUserIntInput(prompt, scan);
        if (gym.findMember(memberCode) == null || !gym.findMember(memberCode).getFirstName().equals(firstName)
                || !gym.findMember(memberCode).getLastName().equals(lastName)) {
            System.out.println("This member does not exist");
            return null;
        }
        return gym.findMember(memberCode);
    }

    /**
     * This method is specifically here to read Classes from a csv file
     * "classes.csv", the scanner that is input in this method is the scanner for
     * this csv file
     * 
     * @param scan Scanner that has the "classes.csv" in it allowing us to read the
     *             file
     * @param gym  Gym that we want to put the class list in
     * @return Arraylist of classes that we will input into the gym
     */
    public static ArrayList<Classes> createClassList(Scanner scan, Gym gym) {
        ArrayList<Classes> returnable = new ArrayList<Classes>();
        scan.useDelimiter("/n");
        scan.nextLine();
        while (scan.hasNext()) {
            String[] line = stripSpaces(scan.nextLine().split(","));
            if (line[2].equals("Normal")) {
                returnable.add(new Classes(line[0], line[1]));
            } else {
                returnable.add(new OneOnOneClass(line[0], line[1]));
            }
        }
        return returnable;
    }

    /**
     * This method creates a class list at random every time the system is ran and
     * displays it to the screen. It uses the class list from the input
     * "classes.csv" and randomizes them in a grid and is stored in a 2D array
     * 
     * @param gym Gym that we are printing the class schedule for
     */
    public static void viewCalendarSchedule(Gym gym) {
        int track = 0;
        if (gym.getClasses().size() > 49) {
            System.out.println("Too many classes");
            return;
        }
        Classes[][] naming = gym.getNaming();
        String[][] instructor = gym.getInstructor();
        if (!gym.getBeenShuffled()) {
            for (int row = 0; row < 7; row++) {
                for (int col = 0; col < 7; col++) {
                    if (gym.getClasses().size() > track && !gym.getClasses().get(track).getInstructor().equals("")) {
                        instructor[row][col] = "(" + gym.getClasses().get(track).getInstructor() + ")";
                    } else {
                        instructor[row][col] = "";
                    }

                    if (gym.getClasses().size() > track) {
                        naming[row][col] = gym.getClasses().get(track);
                        track++;
                    } else {
                        naming[row][col] = new Classes();
                        track++;
                    }
                }
            }
        }

        gym.setInstructor(instructor);
        gym.setNaming(naming);
        gym.shuffleSchedule();

        // This is the display for the calendar that is displayed in the terminal
        System.out.println();
        System.out.printf("%20s %15s %15s %15s %15s %15s %15s", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday", "Sunday\n");
        System.out.println(
                "====================================================================================================================");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n8:00am-", naming[0][0].getClassName(),
                naming[0][1].getClassName(), naming[0][2].getClassName(), naming[0][3].getClassName(),
                naming[0][4].getClassName(), naming[0][5].getClassName(), naming[0][6].getClassName());
        System.out.printf("%s %12s %15s %15s %15s %15s %15s %16s", "\n9:00am", instructor[0][0], instructor[0][1],
                instructor[0][2], instructor[0][3], instructor[0][4], instructor[0][5], instructor[0][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n9:00am-", naming[1][0].getClassName(),
                naming[1][1].getClassName(), naming[1][2].getClassName(), naming[1][3].getClassName(),
                naming[1][4].getClassName(), naming[1][5].getClassName(), naming[1][6].getClassName());
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %16s", "\n10:00am", instructor[1][0], instructor[1][1],
                instructor[1][2], instructor[1][3], instructor[1][4], instructor[1][5], instructor[1][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %10s %15s %15s %15s %15s %15s %15s", "\n11:00am-", naming[2][0].getClassName(),
                naming[2][1].getClassName(), naming[2][2].getClassName(), naming[2][3].getClassName(),
                naming[2][4].getClassName(), naming[2][5].getClassName(), naming[2][6].getClassName());
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %16s", "\n12:00pm", instructor[2][0], instructor[2][1],
                instructor[2][2], instructor[2][3], instructor[2][4], instructor[2][5], instructor[2][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n2:00pm-", naming[3][0].getClassName(),
                naming[3][1].getClassName(), naming[3][2].getClassName(), naming[3][3].getClassName(),
                naming[3][4].getClassName(), naming[3][5].getClassName(), naming[3][6].getClassName());
        System.out.printf("%s %12s %15s %15s %15s %15s %15s %16s", "\n3:00pm", instructor[3][0], instructor[3][1],
                instructor[3][2], instructor[3][3], instructor[3][4], instructor[3][5], instructor[3][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n5:00pm-", naming[4][0].getClassName(),
                naming[4][1].getClassName(), naming[4][2].getClassName(), naming[4][3].getClassName(),
                naming[4][4].getClassName(), naming[4][5].getClassName(), naming[4][6].getClassName());
        System.out.printf("%s %12s %15s %15s %15s %15s %15s %16s", "\n6:00pm", instructor[4][0], instructor[4][1],
                instructor[4][2], instructor[4][3], instructor[4][4], instructor[4][5], instructor[4][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n7:00pm-", naming[5][0].getClassName(),
                naming[5][1].getClassName(), naming[5][2].getClassName(), naming[5][3].getClassName(),
                naming[5][4].getClassName(), naming[5][5].getClassName(), naming[5][6].getClassName());
        System.out.printf("%s %12s %15s %15s %15s %15s %15s %16s", "\n8:00pm", instructor[5][0], instructor[5][1],
                instructor[5][2], instructor[5][3], instructor[5][4], instructor[5][5], instructor[5][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %11s %15s %15s %15s %15s %15s %15s", "\n8:00pm-", naming[6][0].getClassName(),
                naming[6][1].getClassName(), naming[6][2].getClassName(), naming[6][3].getClassName(),
                naming[6][4].getClassName(), naming[6][5].getClassName(), naming[6][6].getClassName());
        System.out.printf("%s %12s %15s %15s %15s %15s %15s %16s", "\n9:00pm", instructor[6][0], instructor[6][1],
                instructor[6][2], instructor[6][3], instructor[6][4], instructor[6][5], instructor[6][6] + "\n");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * This method asks the user to look at the schedule and then asks for the day
     * and time that they want to enroll, it then converts these inputs into the row
     * and column of the 2D array of classes and then proceeds to the enrollment
     * process accounting for different plans, different types of classes, whether
     * your are already enrolled in that class, the total number of people in the
     * class already and others
     * 
     * @param gym           Gym that we are enrolling in classes
     * @param scan          Scanner to read the users input
     * @param currentMember Member that is enrolling in this specific class
     */
    public static void regesterForClass(Gym gym, Scanner scan, Member currentMember) {
        viewCalendarSchedule(gym);
        String prompt = "Which day of the week would you like to register for a class: ";
        ArrayList<String> letters = new ArrayList<String>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        String dayOfWeek = correctLetterInput(letters, prompt, scan);
        int numberOfWeek = 0;
        if (dayOfWeek.equalsIgnoreCase("Monday")) {
            numberOfWeek = 0;
        } else if (dayOfWeek.equalsIgnoreCase("Tuesday")) {
            numberOfWeek = 1;
        } else if (dayOfWeek.equalsIgnoreCase("Wednesday")) {
            numberOfWeek = 2;
        } else if (dayOfWeek.equalsIgnoreCase("Thursday")) {
            numberOfWeek = 3;
        } else if (dayOfWeek.equalsIgnoreCase("Friday")) {
            numberOfWeek = 4;
        } else if (dayOfWeek.equalsIgnoreCase("Saturday")) {
            numberOfWeek = 5;
        } else if (dayOfWeek.equalsIgnoreCase("Sunday")) {
            numberOfWeek = 6;
        }
        int timeRow = findTimeRow(gym, scan);
        if (gym.getInstructor()[timeRow - 1][numberOfWeek].equals("")) {
            System.out.println("There is no session at this time on this day, please check the schedule and try again");
        } else {
            Classes currentClass = gym.getNaming()[timeRow - 1][numberOfWeek];
            prompt = "You would like to register for the " + currentClass.getClassName() + " class with instructor "
                    + currentClass.getInstructor() + "? [Y/N]: ";
            letters = new ArrayList<String>(Arrays.asList("N", "Y"));
            String entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                if (currentClass instanceof OneOnOneClass) {
                    OneOnOneClass current = (OneOnOneClass) currentClass;
                    current.getCalendar().add(Calendar.DATE, numberOfWeek);
                    if (current.getCalendar().getTimeInMillis() - gym.getCal().getTimeInMillis() < 86400000) {
                        System.out.println(
                                "Unable to register for the One on One class, need to register 24 hours in advance");
                        return;
                    }
                }
                register(currentClass, gym, scan, currentMember, numberOfWeek, timeRow);
            } else if (entry.equals("N")) {
                System.out.println(
                        "No worries, check the schedule again to find the class you would like to register to!");
            }
        }
    }

    /**
     * This method gets the users input for what time of the day they want to enroll
     * and turns it into a row number of the 2D array of classes
     * 
     * @param gym  Gym we are getting the classes from
     * @param scan Scanner to read the user inputs
     * @return int that we use to find the class
     */
    public static int findTimeRow(Gym gym, Scanner scan) {
        System.out.println("What time of the day would you like to register:\n");
        System.out.printf("%20s %15s %15s %15s %15s %15s %15s", "8:00am-", "9:00am-", "11:00am-", "2:00pm-", "5:00pm-",
                "7:00pm-", "8:00pm-\n");
        System.out.printf("%19s %15s %15s %15s %15s %15s %14s", "9:00am", "10:00am", "12:00pm", "3:00pm", "6:00pm",
                "8:00pm", "9:00pm");
        System.out.println();
        System.out.printf("%s %10s %15s %15s %15s %15s %15s %15s", "\nEnter:", "1", "2", "3", "4", "5", "6", "7\n");
        String prompt = "\nEnter one of the enter number from above cooresponding to the time you would like to register for: ";
        int current = checkUserIntInput(prompt, scan);
        while (current < 1 || current > 7) {
            prompt = "The number has to be between 1 and 7: ";
            current = checkUserIntInput(prompt, scan);
        }
        return current;
    }

    /**
     * This method does the registration for the current member into the class they
     * want to participate in while also doing error checking, they check what type
     * of class it is and what type of plan you have, it checks the time of the
     * class compared to when youre enrolling if you are going for a one on one
     * class, and also takes the number of guests you want to bring into account
     * 
     * @param currentClass  Class that the member wants to enroll in
     * @param gym           Gym that is hosting the class
     * @param scan          Scanner to read the users input
     * @param currentMember Member that is registering for a specific class
     * @param numberOfWeek  int number corresponding to the day of the week
     * @param timeNumber    int number corresponding to the time of the day
     */
    public static void register(Classes currentClass, Gym gym, Scanner scan, Member currentMember, int numberOfWeek,
            int timeNumber) {
        boolean chargePerSession = false;
        if (currentMember.getBasicPlan() == null) {
            chargePerSession = false;
        } else {
            chargePerSession = true;
        }
        if (currentClass instanceof OneOnOneClass) {
            OneOnOneClass current = (OneOnOneClass) currentClass;
            String prompt = "One on one classes cost $10, would you still like to register? [Y/N]: ";
            ArrayList<String> letters = new ArrayList<String>(Arrays.asList("Y", "N"));
            String entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                if (current.addMember(currentMember)) {
                    System.out.println("You have been registered!");
                    currentMember.enroll(current);
                    current.addMember(currentMember);
                    current.setTime(findTime(timeNumber));
                    current.setDayOfWeek(findDayOfWeek(numberOfWeek));
                    gym.addToMonthlyMemberSales(10.0);
                } else {
                    System.out.println("This one on one class already has a member registered");
                }
            } else {
                System.out.println("Exiting the register screen");
            }
        } else {
            int numGuests = checkUserIntInput("How many guests would you like to bring: ", scan);
            if (currentClass.getMembers().size() >= 25) {
                System.out.println("You are unable to register, there is already 25 member in this class");
            } else if (currentClass.getMembers().size() + numGuests + 1 > 25) {
                System.out.println("You are bringing in too many guests exceeding the 25 person limit");
            } else if (currentMember.findClass(currentClass)) {
                System.out.println("You are already registered in this class");
            } else {
                if (chargePerSession) {
                    System.out.println(
                            "The fee for this class is $" + 5 * (1 + numGuests) + " total for you and your guests ");
                    gym.addToMonthlyMemberSales(5.0 * (1.0 + numGuests));
                }
                currentMember.enroll(currentClass);
                currentClass.enroll(currentMember);
                currentClass.setTime(findTime(timeNumber));
                currentClass.setDayOfWeek(findDayOfWeek(numberOfWeek));
                gym.addToMonthlyMemberSales(10.0);
                System.out.println("You have been registered!");
            }
        }
    }

    /**
     * This method prints the class list of a member, showing them which classes
     * they are enrolled in
     * 
     * @param gym           Gym that the member is in
     * @param currentMember Member that we are printing the class list of
     */
    public static void printClassList(Gym gym, Member currentMember) {
        System.out.println("\nCurrent classes you are enrolled in:\n");
        if (currentMember.getClasses().size() == 0) {
            System.out.println("You are registered to no classes");
        } else {
            for (int i = 0; i < currentMember.getClasses().size(); i++) {
                System.out.println("Name: " + currentMember.getClasses().get(i).getClassName() + ", Instructor: "
                        + currentMember.getClasses().get(i).getInstructor() + ", Day: "
                        + currentMember.getClasses().get(i).getDayOfWeek() + ", Time: "
                        + currentMember.getClasses().get(i).getTime() + ", ID number: "
                        + currentMember.getClasses().get(i).getID());
            }
        }
        System.out.println();
    }

    /**
     * This method converts the number of the time of day back into its String
     * 
     * @param timeNumber int time number we want to convert
     * @return String that is the time of the day we want to display
     */
    public static String findTime(int timeNumber) {
        switch (timeNumber) {
            case 1:
                return "8:00am-9:00am";
            case 2:
                return "9:00am-10:00am";
            case 3:
                return "11:00am-12:00pm";
            case 4:
                return "2:00pm-3:00pm";
            case 5:
                return "5:00pm-6:00pm";
            case 6:
                return "7:00pm-8:00pm";
            case 7:
                return "8:00pm-9:00pm";
            default:
                return "";
        }
    }

    /**
     * This method turns the day number that was used in the 2D array of classes
     * back into the String day of the week
     * 
     * @param dayNumber int day number that we want to revert back to a String
     * @return String of the day of the week ready to be displayed
     */
    public static String findDayOfWeek(int dayNumber) {
        switch (dayNumber) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                return "";
        }
    }

    /**
     * This method unregisters a member from any class that they are enrolled in, it
     * unenrolls by the ID number of the class
     * 
     * @param gym           Gym with the class being unenrolled in
     * @param scan          Scanner to read the users input
     * @param currentMember Member unenrolling in a class
     */
    public static void unregister(Gym gym, Scanner scan, Member currentMember) {
        if (currentMember.getClasses().size() == 0) {
            System.out.println("You are registered to no classes");
            return;
        }
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        printClassList(gym, currentMember);
        String prompt = "Enter the ID number of the class above you would like to unregister for: ";
        int ID = checkUserIntInput(prompt, scan);
        for (int i = 0; i < currentMember.getClasses().size(); i++) {
            IDs.add(currentMember.getClasses().get(i).getID());
        }
        while (!currentMember.findClassID(ID)) {
            prompt = "This class ID is not in your class list, please enter a different ID: ";
            ID = checkUserIntInput(prompt, scan);
        }
        Classes currentClass = currentMember.findClassByID(ID);
        currentMember.getClasses().remove(currentClass);
        currentClass.getMembers().remove(currentMember);
        System.out.println("You have unregistered this class!");
    }

    /**
     * This method is used by the user and it allows them to switch from one plan to
     * the other and since there is only two types of plan it doesnt ask which plan
     * they want, it just switches them
     * 
     * @param currentMember Member that wants to switch plans
     * @param gym           Gym that the member is in
     * @param scan          Scanner used to read the users input
     */
    public static void switchPlan(Member currentMember, Gym gym, Scanner scan) {
        String prompt = "";
        ArrayList<String> letters = new ArrayList<String>();
        if (currentMember.getBasicPlan() == null && currentMember.getFoxPlan() == null) {
            System.out.println("You can not switch plans because you do not currently have a plan");
            return;
        }
        if (currentMember.getBasicPlan() != null) {
            prompt = "You would like to switch from the BasicPlan to the FoxPlan? [Y/N]: ";
            letters = new ArrayList<String>(Arrays.asList("Y", "N"));
            String entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                System.out.println("Switching you from the BasicPlan to the FoxPlan");
                currentMember.setFoxPlan(new FoxPlan());
            } else {
                System.out.println("Exiting back out");
            }
        } else {
            prompt = "You would like to switch from the FoxPlan to the BasicPlan? [Y/N]: ";
            letters = new ArrayList<String>(Arrays.asList("Y", "N"));
            String entry = correctLetterInput(letters, prompt, scan);
            if (entry.equals("Y")) {
                System.out.println("Switching you from the FoxPlan to the BasicPlan");
                currentMember.setBasicPlan(new BasicPlan());
            } else {
                System.out.println("Exiting back out");
            }
        }
    }

    /**
     * This method closes the users plan and exits them back out to the main screen
     * of the display
     * 
     * @param currentMember Member that wants to stop their plan
     * @param gym           Gym that the member is in
     * @param scan          Scanner to read the users input
     */
    public static void closePlan(Member currentMember, Gym gym, Scanner scan) {
        if (currentMember.getBasicPlan() == null && currentMember.getFoxPlan() == null) {
            System.out.println("You can not close your plan because you do not currently have a plan");
            return;
        } else {
            System.out.println("Closing your account!");
            currentMember.cancelAccount();
        }
    }

    /**
     * This method is basically an informational method that prints out the
     * description of each plan that the user could possibly have
     * 
     * @param scan          Scanner to read the users input
     * @param currentMember Member that want to know the information
     */
    public static void findCurrentPlan(Scanner scan, Member currentMember) {
        if (currentMember.getBasicPlan() != null) {
            System.out.println();
            System.out.println("You are currently registered for the BasicPlan:\n");
            System.out.println("Details:");
            System.out.println("Cost - $9.99/Month");
            System.out.println("Equipment privileges - included");
            System.out.println("Guests - $10 per session - only equipment");
            System.out.println("Group fitness class - $5 per session - additional $5 for each guest per session");
        } else {
            System.out.println();
            System.out.println("You are currently registered for the FoxPlan:\n");
            System.out.println("Details:");
            System.out.println("Cost - $19.99/Month");
            System.out.println("Equipment privileges - included");
            System.out.println("Guests privileges - included");
            System.out.println("Group fitness class - free");
        }
    }

    /**
     * This method is a tester method to make sure we are parsing the "classes.csv"
     * file correctly
     * 
     * @param f File that is being tested, will be "classes.csv"
     * @return boolean that lets us know if the scanner works correctly
     */
    public static boolean parseClasses(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            input.close();
            return line[0].equals("Zumba") && line[1].equals("Sam") && line[2].equals("Normal");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is a tester method to make sure we are parsing the
     * "employees.csv"
     * file correctly
     * 
     * @param f File that is being tested, will be "employees.csv"
     * @return boolean that lets us know if the scanner works correctly
     */
    public static boolean parseEmployees(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            input.close();
            return line[0].equals("Nate") && line[1].equals("Chudy") && line[2].equals("Male");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is a tester method to make sure we are parsing the "members.csv"
     * file correctly
     * 
     * @param f File that is being tested, will be "members.csv"
     * @return boolean that lets us know if the scanner works correctly
     */
    public static boolean parseMembers(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            input.close();
            return line[0].equals("Jessica") && line[1].equals("Toscano") && line[2].equals("Female");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is a tester method to make sure we are parsing the "products.csv"
     * file correctly
     * 
     * @param f File that is being tested, will be "products.csv"
     * @return boolean that lets us know if the scanner works correctly
     */
    public static boolean parseProducts(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            input.close();
            Double.parseDouble(line[1]);
            Integer.parseInt(line[2]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
