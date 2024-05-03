package WarehouseManagement;

import java.util.ArrayList;

public class Warehouse {
    private String name;
    private ArrayList<Product> products;

    /**
     * no parameter constructor
     */
    public Warehouse() {
        this.name = "Marist";
        products = new ArrayList<Product>();
    }

    /**
     * one parameter constructor
     * 
     * @param name
     */
    public Warehouse(String name) {
        this.name = name;
        products = new ArrayList<Product>();
    }

    /**
     * name getter
     * 
     * @return returns the name of the warehouse
     */
    public String getName() {
        return name;
    }

    /**
     * product getter
     * 
     * @return returns the list of products in a warehouse
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * products setter
     * 
     * @param products products to set to the current products
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * adds a product to the list of products
     * 
     * @param product product to add to the list
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * name setter
     * 
     * @param name name to set to the current name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * overridden toString for the warehouse class
     */
    public String toString() {
        String returnable = name + " Warehouse;\n";
        for (int i = 0; i < products.size(); i++) {
            returnable = returnable + "-" + products.get(i).toString() + "\n";
        }
        return returnable;
    }
}
