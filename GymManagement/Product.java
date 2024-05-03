package finalProject;

public class Product {
    private static int ID = 0;
    private String name;
    private int productID;
    private Concession currentConcession;
    private double cost;
    private int amount;

    public Product(){
        ID++;
        this.productID = ID;
        this.name = "Water Bottle";
        this.currentConcession = new Concession();
        this.cost = 0;
        this.amount = 0;
    }

    public Product(String name){
        ID++;
        this.productID = ID;
        this.name = name;
        this.currentConcession = new Concession();
        this.cost = 0;
        this.amount = 0;
    }

    public Product(String name, Concession currentConcession, double cost, int amount){
        ID++;
        this.productID = ID;
        this.name = name;
        this.currentConcession = currentConcession;
        this.cost = cost;
        this.amount = amount;
    }

    public double getCost(){
        return cost;
    }

    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public Concession getCurrentConcession(){
        return currentConcession;
    }

    public int getProductID(){
        return productID;
    }

    public void setConcession(Concession currentConcession){
        this.currentConcession = currentConcession;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void remove(int amount){
        this.amount = this.amount - amount;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public String toString(){
        return "Name: " + name + ", ID: " + productID;
    }
}
