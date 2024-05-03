package WarehouseManagement;

import java.util.ArrayList;

public class Supplier {
    private String name;
    private ArrayList<Warehouse> warehouses;

    /**
     * no parameter constructor
     */
    public Supplier() {
        this.name = "Unknown";
        this.warehouses = new ArrayList<Warehouse>();
    }

    /**
     * one parameter constructor
     * 
     * @param name name of the supplier
     */
    public Supplier(String name) {
        this.name = name;
        this.warehouses = new ArrayList<Warehouse>();
    }

    /**
     * parameter constructor
     * 
     * @param name       name of the supplier
     * @param warehouses a list of warehouses related to the supplier
     */
    public Supplier(String name, ArrayList<Warehouse> warehouses) {
        this.name = name;
        this.warehouses = warehouses;
    }

    /**
     * name getter
     * 
     * @return returns the name of the supplier
     */
    public String getName() {
        return name;
    }

    /**
     * warehouses getter
     * 
     * @return returns the list of warehouses for the supplier
     */
    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    /**
     * name setter
     * 
     * @param name name to be set to the current name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * warehouses setter
     * 
     * @param warehouses warehouses to be set to the current warehouses
     */
    public void setWarehouses(ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    /**
     * searches through the warehouse list to find a warehouse by name
     * 
     * @param name name to search for in the warehouse list
     * @return returns the warehouse that has the name String name
     */
    public Warehouse findWarehouse(String name) {
        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).getName().equals(name)) {
                return warehouses.get(i);
            }
        }
        return null;
    }

    /**
     * adds a warehouse to the warehouse list
     * 
     * @param w warehouse to add to the list of warehouses
     */
    public void addWarehouse(Warehouse w) {
        warehouses.add(w);
    }

    /**
     * finds a product in one of the warehouses in the list
     * 
     * @param productID productID to search for
     * @return returns the product with the cooresponding ID
     */
    public Product findProduct(int productID) {
        for (int i = 0; i < warehouses.size(); i++) {
            for (int j = 0; j < warehouses.get(i).getProducts().size(); j++) {
                if (warehouses.get(i).getProducts().get(j).getID() == productID) {
                    return warehouses.get(i).getProducts().get(j);
                }
            }
        }
        return null;
    }

    /**
     * overridden toString to return the supplier description
     */
    public String toString() {
        String returnable = "Supplier Name: " + name + "\n" + "Warehouses: \n";
        for (Warehouse warehouse : warehouses) {
            returnable = returnable + "+" + warehouse.toString() + "\n";
        }
        return returnable;
    }
}
