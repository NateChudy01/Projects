package assignment5;

public class Order implements Comparable<Order> {
    private Product product;
    private int orderID;

    /**
     * No parameter constructor
     */
    public Order() {
        this.orderID = 0;
        this.product = null;
    }

    /**
     * One parameter constructor
     * 
     * @param orderID orderID for the particular order
     */
    public Order(int orderID) {
        this.orderID = orderID;
        this.product = null;
    }

    /**
     * All parameter constructor
     * 
     * @param product the product in the order
     * @param orderID orderID for the particular order
     */
    public Order(Product product, int orderID) {
        this.orderID = orderID;
        this.product = product;
    }

    /**
     * Product getter
     * 
     * @return returns the product of the current order
     */
    public Product getProduct() {
        return product;
    }

    /**
     * OrderID getter
     * 
     * @return returns the orderID of the current order
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Product setter
     * 
     * @param product product we are setting the current product to
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * toString to print out an order
     * 
     * @return returns a String to print out an order object
     */
    public String toString() {
        return "Order ID: " + orderID + ", Product: " + product.getName();
    }

    /**
     * Implementing the compareTo function of a comparable object
     */
    @Override
    public int compareTo(Order o) {
        return this.getProduct().compareTo(o.getProduct());
    }
}
