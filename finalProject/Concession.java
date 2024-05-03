package finalProject;

import java.util.ArrayList;

public class Concession {
    private String name;
    private ArrayList<Product> products;

    public Concession(){
        this.products = new ArrayList<Product>();
        this.name = "Marist Concesion";
    }

    public Concession(ArrayList<Product> products){
        this.products = products;
        this.name = "Marist Concesion";
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
        product.setConcession(this);
    }

    public String toString(){
        String returnable = "";
        for(Product product: products){
            returnable = returnable + product.toString();
        }
        return returnable;
    }
}
