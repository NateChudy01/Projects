package GymManagement;

public class Product {
    private static int ID = 0;
    private String name;
    private int productID;
    private Concession currentConcession;
    private double cost;
    private int amount;

    /**
     * No arg constructor
     */
    public Product() {
        ID++;
        this.productID = ID;
        this.name = "Water Bottle";
        this.currentConcession = new Concession();
        this.cost = 0;
        this.amount = 0;
    }

    /**
     * One are constructor
     * 
     * @param name String product name
     */
    public Product(String name) {
        ID++;
        this.productID = ID;
        this.name = name;
        this.currentConcession = new Concession();
        this.cost = 0;
        this.amount = 0;
    }

    /**
     * Four arg constructor
     * 
     * @param name              String anme of the product
     * @param currentConcession Concession of the product
     * @param cost              double cost of the product
     * @param amount            int amount of the product
     */
    public Product(String name, Concession currentConcession, double cost, int amount) {
        ID++;
        this.productID = ID;
        this.name = name;
        this.currentConcession = currentConcession;
        this.cost = cost;
        this.amount = amount;
    }

    /**
     * This emethod gets the cost of the product
     * 
     * @return double cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * This method gets the name of the product
     * 
     * @return String product name
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the amount of the product in the concession stand
     * 
     * @return int amount of the product
     */
    public int getAmount() {
        return amount;
    }

    /**
     * This method gets the current concession of the product
     * 
     * @return Concession of the product
     */
    public Concession getCurrentConcession() {
        return currentConcession;
    }

    /**
     * This method gets the productID
     * 
     * @return int productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method is the concession setter
     * 
     * @param currentConcession Concession
     */
    public void setConcession(Concession currentConcession) {
        this.currentConcession = currentConcession;
    }

    /**
     * This method is the name setter
     * 
     * @param name String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is the amount setter
     * 
     * @param amount int amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * This method removes a certain amount of the product from the concession
     * 
     * @param amount int amount to remove
     */
    public void remove(int amount) {
        this.amount = this.amount - amount;
    }

    /**
     * This method is the cost setter
     * 
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * This method is the overridden toString of the Product class
     */
    public String toString() {
        return "Name: " + name + ", Amount: " + amount + ", Cost: $" + (int) cost;
    }
}
