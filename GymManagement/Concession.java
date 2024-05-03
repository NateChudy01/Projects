package GymManagement;

import java.util.ArrayList;

public class Concession {
    private String name;
    private ArrayList<Product> products;

    /**
     * No arg constructor
     */
    public Concession() {
        this.products = new ArrayList<Product>();
        this.name = "Marist Concesion";
    }

    /**
     * One arg constructor
     * 
     * @param products Arraylist of Products in the concession
     */
    public Concession(ArrayList<Product> products) {
        this.products = products;
        this.name = "Marist Concesion";
    }

    /**
     * This method gets the products in the concession
     * 
     * @return Arraylist of Products in the concession
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * This method gets the name of the concession
     * 
     * @return String name of the concession
     */
    public String getName() {
        return name;
    }

    /**
     * This method is the name setter
     * 
     * @param name String name of the concesion
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is the products setter
     * 
     * @param products Arraylist of Products in the concession
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * This method adds a product to the Arraylist of Products in the concession
     * 
     * @param product Product to be added to the concession
     */
    public void addProduct(Product product) {
        products.add(product);
        product.setConcession(this);
    }

    /**
     * This method finds the Product object in the Products Arraylist just by the
     * name
     * 
     * @param name String name to search for in the concession stand
     * @return Product that is being searched for
     */
    public Product findProductFromName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method is the overridden toString of the Concession class
     */
    public String toString() {
        String returnable = "";
        for (Product product : products) {
            returnable = returnable + "-" + product.toString() + "\n";
        }
        return returnable;
    }
}
