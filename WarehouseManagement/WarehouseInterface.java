package WarehouseManagement;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * This class is designed to act as an interface to be used by warehouses when
 * it comes to ordering products and taking orders as well as printing picklists
 */
public class WarehouseInterface {
    public static void main(String[] args) {
        // begin by creating a supplier and then reading information in from the
        // pdocuts.csv file
        int errorLineNumber = 2; // tracks the line of the misinput from the .csv file and it starts at 2 because
                                 // we have to ignore the first line because it is just the information row
                                 // without an products
        Supplier supplier = new Supplier("Marist");
        try {
            Scanner input;
            if (args.length == 2) {
                input = new Scanner(new File(args[0]));
            } else {
                input = new Scanner(new File("src/WarehouseManagement/products.csv"));
            }
            input.useDelimiter("/n");
            // this while loop reads through each line in the products.csv and seperates the
            // information by "," and by newline
            input.nextLine();
            while (input.hasNext()) {
                String[] line = stripSpaces(input.nextLine().split(","));
                Warehouse currentWarehouse = supplier.findWarehouse(line[6]);
                Integer.parseInt(line[0]);
                if (currentWarehouse != null) { // if the warehouse exists that is trying to be refrenced
                    currentWarehouse.addProduct(new Product(Integer.parseInt(line[0]), line[1],
                            Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]),
                            Integer.parseInt(line[5]), currentWarehouse, Integer.parseInt(line[7]),
                            Integer.parseInt(line[8]), Integer.parseInt(line[9]),
                            parseStringToDouble(line[10], "$"), Integer.parseInt(line[11])));
                } else { // if there is no warehouse by the name input from the file, create a new
                         // warehouse by that name
                    currentWarehouse = new Warehouse(line[6]);
                    currentWarehouse.addProduct(new Product(Integer.parseInt(line[0]), line[1],
                            Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]),
                            Integer.parseInt(line[5]), currentWarehouse, Integer.parseInt(line[7]),
                            Integer.parseInt(line[8]), Integer.parseInt(line[9]),
                            parseStringToDouble(line[10], "$"), Integer.parseInt(line[11])));
                    supplier.addWarehouse(currentWarehouse);
                }
                // these if statements are here to create products from the .csv file input and
                // then add them to the correct warehouse, if the warehouse being refrenced does
                // not exist it creates that warehouse and then adds it to the supplier
                errorLineNumber++;
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Something was wrong with the product list, please check line " + errorLineNumber
                    + " in 'products.csv'"); // error message
            System.exit(0);
        }
        try {
            // repeat the same process as the first block of code but this time its for the
            // orders.csv file which we read in to figure out what products to pick and in
            // what order/how to sort them
            errorLineNumber = 2; // tracks the line of the misinput from the .csv file and it starts at 2 because
                                 // we have to ignore the first line because it is just the information row
                                 // without an products
            Scanner input;
            if (args.length == 2) {
                input = new Scanner(new File(args[1]));
            } else {
                input = new Scanner(new File("src/WarehouseManagement/orders.csv"));
            }
            input.useDelimiter("/n");
            // this while loop reads through each line in the products.csv and seperates the
            // information by "," and by newline
            Date date = new Date();
            // used to enforce a date structure
            ArrayList<Product> productsOrdered = new ArrayList<Product>();
            ArrayList<Product> restock = new ArrayList<Product>();
            ArrayList<Order> orders = new ArrayList<Order>();
            // these lists keep track of the list of products that are being ordered, which
            // items need to be restocked and then order objects which allow us to refrence
            // the order number to then sort the orders by ticket number
            input.nextLine();
            while (input.hasNext()) {
                String[] line = stripSpaces(input.nextLine().split(","));
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    date = sdf.parse(line[1]);
                } catch (ParseException p) {
                    System.out.println(
                            "Dates need to be input in the correct format (MM/dd/yyyy)");
                }
                // checking if the order is valid meaning the product must be non null and there
                // must be enough of the product in stock, if these conditions are met we add
                // the product to the ordered list and keep track of how many of that product is
                // being ordered. If this is not the case it prints messages accordingly
                if (validProductOrder(Integer.parseInt(line[2]), Integer.parseInt(line[3]), supplier)) {
                    productsOrdered.add(supplier.findProduct(Integer.parseInt(line[2])));
                    supplier.findProduct(Integer.parseInt(line[2])).setAmountToRemove(Integer.parseInt(line[3]));
                    orders.add(new Order(supplier.findProduct(Integer.parseInt(line[2])), Integer.parseInt(line[0])));
                } else if (supplier.findProduct(Integer.parseInt(line[2])) != null) {
                    System.out.println();
                    System.out.println("***********************************************************************");
                    System.out.println("Product: " + supplier.findProduct(Integer.parseInt(line[2])).getName()
                            + " is backordered from Warehouse: "
                            + supplier.findProduct(Integer.parseInt(line[2])).getStoredWarehouse().getName());
                    System.out.println("***********************************************************************");
                    restock.add(supplier.findProduct(Integer.parseInt(line[2])));
                } else {
                    System.out.println();
                    System.out.println("*************************************");
                    System.out.println("No product with the ID number: " + line[2]);
                    System.out.println("*************************************");
                }
                errorLineNumber++;
            }
            printAllTickets(productsOrdered, restock, orders);
            // this line above is the function call to print the pick list tickets
            System.exit(1); // trying to figure out how to JUnit test
        } catch (Exception e) {
            System.out.println("Something was wrong with the order list, please check line " + errorLineNumber
                    + " in 'orders.csv'");
            System.exit(0);
        }
    }

    /**
     * this method takes in a string that is a number and parses it into a double
     * while also removing a certian string
     * 
     * @param stringToDouble this variable is the string we want to parse to a
     *                       double
     * @param detect         this string is the string we are removing from the
     *                       string input (typically $)
     * @return returns a double from the string input
     */
    public static double parseStringToDouble(String stringToDouble, String detect) {
        if (stringToDouble.indexOf(detect) != -1) {
            return Double.parseDouble(stringToDouble.replace(detect, ""));
        } else {
            return Double.parseDouble(stringToDouble);
        }
    }

    /**
     * this method takes in a string array and strips the spaces from each element
     * in the array
     * 
     * @param s string list to have the spaces stripped
     * @return returns a string array of the original array but with the spaces
     *         stripped
     */
    public static String[] stripSpaces(String[] s) {
        String[] returnable = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            returnable[i] = s[i].strip();
        }
        return returnable;
    }

    /**
     * this method checks to see if the input information cooresponds to an actual
     * product
     * 
     * @param productID this is the productID of the product we are validating
     * @param amount    amount of the product we are validating
     * @param supplier  the supplier to check to see if the product is in and if it
     *                  has the correct amount
     * @return if the supplier has the correct amount and actually has the product
     *         it returns a boolean of true
     */
    public static boolean validProductOrder(int productID, int amount, Supplier supplier) {
        Product currentProduct = supplier.findProduct(productID);
        if (currentProduct == null) {
            return false;
        } else if (amount > currentProduct.getAmount()) {
            return false;
        }
        return true;
    }

    /**
     * this method is the pick list printer, it prints the graphic of a single
     * ticket
     * 
     * @param products     this is the list of products we are using to sort and
     *                     then print the picklist for
     * @param ticketNumber this is the ticket number we are tracking to print
     * @param orders       this is the list of orders cooresponding to the products
     *                     and is used to sort by the orderID
     */
    public static void printPickList(ArrayList<Product> products, int ticketNumber, ArrayList<Order> orders) {
        Collections.sort(products);
        System.out.println();
        System.out.println("====================================================================");
        System.out.println("Ticket Number " + ticketNumber + ": " + products.get(0).getStoredWarehouse().getName());
        printByID(products, orders); // calls the printByID method to print a sorted pick list
        System.out.println("====================================================================");
    }

    /**
     * this method loops and calls the printPickList method, this methods purpose is
     * to print every ticket
     * 
     * @param products this is the list of products we are using to sort and then
     *                 print the picklist for
     * @param restock  this is a list of items we are going to restock
     * @param orders   this is the list of orders cooresponding to the products and
     *                 is used to sort by the orderID
     */
    public static void printAllTickets(ArrayList<Product> products, ArrayList<Product> restock,
            ArrayList<Order> orders) {
        ArrayList<Product> currentProducts = new ArrayList<Product>();
        ArrayList<Product> remove = new ArrayList<Product>();
        int ticketNumber = 1;
        while (products.size() > 0) {
            String warehouseName = products.get(0).getStoredWarehouse().getName();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getStoredWarehouse().getName().equals(warehouseName)) {
                    currentProducts.add(products.get(i));
                    remove.add(products.get(i));
                }
            }
            printPickList(currentProducts, ticketNumber, orders);
            currentProducts.clear();
            products.removeAll(remove);
            remove.clear();
            ticketNumber++;
        }
        System.out.println("\n");
        restock(restock); // restocks the items that need to be restocked
    }

    /**
     * this method is the sorting system we call to print the tickets sorted not
     * only by the bin, shelf, and aisle but also the warehouse it is in and the
     * orderID in that specific warehouse
     * 
     * @param products list of products that we are going to be printing the pick
     *                 list for
     * @param orders   list of orders cooresponding to the product list
     */
    public static void printByID(ArrayList<Product> products, ArrayList<Order> orders) {
        ArrayList<Product> currentProducts = new ArrayList<Product>();
        ArrayList<Product> remove = new ArrayList<Product>();
        int id = 0;
        while (products.size() > 0) {
            currentProducts = sortByOrderNumber(orders, id, products);
            if (currentProducts.size() > 0) {
                System.out.println();
                System.out.println("Order Number: " + id);
                for (int i = 0; i < currentProducts.size(); i++) {
                    System.out.println((i + 1) + ": " + currentProducts.get(i).getName() + ", Quantity: "
                            + currentProducts.get(i).getAmountToRemove() + ", [Aisle: "
                            + currentProducts.get(i).getAisle() + ", Shelf: " + currentProducts.get(i).getShelf()
                            + ", Bin: " + currentProducts.get(i).getBin() + "]");
                    remove.add(currentProducts.get(i));
                }
            }
            currentProducts.clear();
            products.removeAll(remove);
            remove.clear();
            id++;

        }
    }

    /**
     * this method restocks the list of items that need to be restocked and it
     * defaults to restocking 200 units of the product
     * 
     * @param restock this is the kist of items that need to be printed to be
     *                restocked
     */
    public static void restock(ArrayList<Product> restock) {
        if (restock.size() > 0) {
            int units = 200;
            System.out.println("***********************************************************");
            System.out.println("Restocking Items:");
            for (int i = 0; i < restock.size(); i++) {
                System.out.println("-" + units + " units of " + restock.get(i).getName() + " in Warehouse: "
                        + restock.get(i).getStoredWarehouse().getName());
                restock.get(i).setAmount(restock.get(i).getAmount() + units);
            }
            System.out.println("***********************************************************");
        }
    }

    /**
     * this method is used in the printByID and it is used to get around the
     * different in the list of orders and the list of products. When the product
     * list calls the sort() method it is difficult to find the cooresponding order
     * for it if they are in different sport i ntheir respective lists. This makes
     * it hard to sort by orderID. This method is a way to get around that and it
     * sorts the elemtnts input into the method by orderID
     * 
     * @param orders   this is the list of orders that cooresponds to the product
     *                 list
     * @param orderID  this is the orderID we are finding a list for (1 -> we are
     *                 looking for all orders with orderID 1)
     * @param products this is the product list
     * @return we return a list of items in the product list with the correct
     *         orderID
     */
    public static ArrayList<Product> sortByOrderNumber(ArrayList<Order> orders, int orderID,
            ArrayList<Product> products) {
        ArrayList<Product> returnable = new ArrayList<Product>();
        String warehouseName = products.get(0).getStoredWarehouse().getName();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID
                    && orders.get(i).getProduct().getStoredWarehouse().getName().equals(warehouseName)) {
                returnable.add(orders.get(i).getProduct());
            }
        }
        return returnable;
    }

    /**
     * this methods purpose is to take products.csv input and check to make sure that it
     * is being parsed correctly. In the method you will see values that are being
     * parsed but are not stored, in this case it doesnt matter because we are just
     * seeing if there is no error when parsing and it does not require them to be
     * stored
     * 
     * @param f file to check to see if we are parsing correctly
     * @return boolean to show whether we parsed the file correctly or not
     */
    public static boolean validateProductCSV(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            Integer.parseInt(line[0]);
            Integer.parseInt(line[2]);
            Integer.parseInt(line[3]);
            Integer.parseInt(line[4]);
            Integer.parseInt(line[5]);
            Integer.parseInt(line[7]);
            Integer.parseInt(line[8]);
            Integer.parseInt(line[9]);
            parseStringToDouble(line[10], "$");
            Integer.parseInt(line[11]);
            input.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * this methods purpose is to take orders.csv input and check to make sure that it
     * is being parsed correctly. In the method you will see values that are being
     * parsed but are not stored, in this case it doesnt matter because we are just
     * seeing if there is no error when parsing and it does not require them to be
     * stored
     * 
     * @param f file to check to see if we are parsing correctly
     * @return boolean to show whether we parsed the file correctly or not
     */
    public static boolean validateOrderCSV(File f) {
        try {
            Scanner input = new Scanner(f);
            input.useDelimiter("/n");
            input.nextLine();
            String[] line = stripSpaces(input.nextLine().split(","));
            Integer.parseInt(line[0]);
            Integer.parseInt(line[2]);
            Integer.parseInt(line[3]);
            input.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
