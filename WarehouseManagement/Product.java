package WarehouseManagement;

public class Product implements Comparable<Product> {
    private int ID;
    private String name;
    private int length;
    private int width;
    private int height;
    private int weight;
    private Warehouse storedWarehouse;
    private int aisle;
    private int shelf;
    private int bin;
    private double cost;
    private int amount;
    private int amountToRemove;

    /**
     * No parameter constructor
     */
    public Product() {
        this.aisle = 0;
        this.shelf = 0;
        this.bin = 0;
        this.ID = -1;
        this.name = "N/A";
        this.length = 0;
        this.width = 0;
        this.height = 0;
        this.weight = 0;
        this.storedWarehouse = null;
        this.cost = 0.0;
        this.amount = 0;
        this.amountToRemove = 0;
    }

    /**
     * Parameter constructor
     * 
     * @param ID              ID number of the product
     * @param name            name of the product
     * @param length          length of the product
     * @param width           width of the product
     * @param height          height of the product
     * @param weight          weight of the product
     * @param storedWarehouse warehouse the product is being stored
     * @param aisle           aisle the product is stored in
     * @param shelf           shelf the product is stored in
     * @param bin             bin the product is stored in
     * @param cost            cost of the product
     * @param amount          number of products
     */
    public Product(int ID, String name, int length, int width, int height, int weight, Warehouse storedWarehouse,
            int aisle, int shelf, int bin, double cost, int amount) {
        this.ID = ID;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.storedWarehouse = storedWarehouse;
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
        this.cost = cost;
        this.amount = amount;
        this.amountToRemove = 0;
    }

    /**
     * ID getter
     * 
     * @return returns the ID number of the product
     */
    public int getID() {
        return ID;
    }

    /**
     * Amount to remove getter
     * 
     * @return returns the amount that the current product is going to be
     *         decremented by
     */
    public int getAmountToRemove() {
        return amountToRemove;
    }

    /**
     * Name getter
     * 
     * @return returns the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * length getter
     * 
     * @return returns the length of the product
     */
    public int getLength() {
        return length;
    }

    /**
     * width getter
     * 
     * @return returns the width of the product
     */
    public int getWitdh() {
        return width;
    }

    /**
     * height getter
     * 
     * @return returns the height of the product
     */
    public int getHeight() {
        return height;
    }

    /**
     * weight getter
     * 
     * @return returns the weight of the product
     */
    public int getWeight() {
        return weight;
    }

    /**
     * stored warehouse getter
     * 
     * @return returns the current warehouse the object is stored in
     */
    public Warehouse getStoredWarehouse() {
        return storedWarehouse;
    }

    /**
     * aisle getter
     * 
     * @return returns the aisle number
     */
    public int getAisle() {
        return aisle;
    }

    /**
     * shelf getter
     * 
     * @return returns the shelf number
     */
    public int getShelf() {
        return shelf;
    }

    /**
     * bin getter
     * 
     * @return returns the bin number
     */
    public int getBin() {
        return bin;
    }

    /**
     * cost getter
     * 
     * @return returns the cost of the product
     */
    public double getCost() {
        return cost;
    }

    /**
     * amount getter
     * 
     * @return returns the amount of this product
     */
    public int getAmount() {
        return amount;
    }

    /**
     * sets the amount to remove
     * 
     * @param amount amount to remove from "amount"
     */
    public void setAmountToRemove(int amount) {
        this.amountToRemove = amount;
    }

    /**
     * ID setter
     * 
     * @param ID ID to set the current ID
     */
    public void setID(int ID) {
        this.ID = ID;
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
     * length setter
     * 
     * @param length length to set to the current length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * width setter
     * 
     * @param width width the set to the current width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * height setter
     * 
     * @param height height to set to the current height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * weight setter
     * 
     * @param weight weight to set to the current weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * storedWarehouse setter
     * 
     * @param storedWarehouse storedWarehouse to set to the current storedWarehouse
     */
    public void setStoredWarehouse(Warehouse storedWarehouse) {
        this.storedWarehouse = storedWarehouse;
    }

    /**
     * aisle setter
     * 
     * @param aisle aisle to set to the current aisle
     */
    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    /**
     * shelf setter
     * 
     * @param shelf shelf to set to the current shelf
     */
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    /**
     * bin setter
     * 
     * @param bin bin to set to the current bin
     */
    public void setBin(int bin) {
        this.bin = bin;
    }

    /**
     * sets the bin, shelf, and aisle of a product
     * 
     * @param bin   bin to be set to the current bin
     * @param shelf shelf to be set to the current shelf
     * @param aisle aisle to be set to the current aisle
     */
    public void setLocation(int bin, int shelf, int aisle) {
        this.bin = bin;
        this.shelf = shelf;
        this.aisle = aisle;
    }

    /**
     * cost setter
     * 
     * @param cost cost to set to the current cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * amount setter
     * 
     * @param amount amount to set to the current amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * overridden toString
     * 
     * @return string to return for the toString
     */
    public String toString() {
        return "Product Name: " + name + ", Product ID: " + ID + ", Product Cost: $" + cost + ", Amount: " + amount;
    }

    /**
     * compareTo method overridden from the interface implementation
     */
    @Override
    public int compareTo(Product o) {
        if (this.getAisle() > o.getAisle()) {
            return 1;
        } else if (this.getAisle() < o.getAisle()) {
            return -1;
        } else {
            if (this.getShelf() > o.getShelf()) {
                return 1;
            } else if (this.getShelf() < o.getShelf()) {
                return -1;
            } else {
                if (this.getBin() > o.getBin()) {
                    return 1;
                } else if (this.getBin() < o.getBin()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
