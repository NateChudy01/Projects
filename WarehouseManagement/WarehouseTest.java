package assignment5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WarehouseTest {
    @Test
    public void Test01() {
        Warehouse w = new Warehouse();
        w.setName("Marist");
        assertEquals(w.getName(), "Marist");
    }

    @Test
    public void Test02() {
        Product p1 = new Product();
        Product p2 = new Product();
        assertEquals(p1.compareTo(p2), 0);
        p1.setLocation(1, 2, 3);
        p2.setLocation(1, 2, 1);
        assertEquals(p1.compareTo(p2), 1);
    }

    @Test
    public void Test03() {
        File check = new File("src/assignment5/products.csv");
        assertEquals(WarehouseInterface.validateProductCSV(check), true);
    }

    @Test
    public void Test04() {
        Supplier s = new Supplier();
        Warehouse w = new Warehouse();
        s.addWarehouse(w);
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();
        Product p5 = new Product();
        p1.setLocation(1, 2, 3);
        p1.setID(1);
        p2.setLocation(1, 2, 1);
        p2.setID(2);
        p3.setLocation(4, 2, 5);
        p3.setID(3);
        p4.setLocation(2, 2, 3);
        p4.setID(4);
        p5.setLocation(5, 2, 3);
        p5.setID(5);
        ArrayList<Product> test = new ArrayList<Product>(Arrays.asList(p1, p2, p3, p4, p5));
        w.setProducts(test);
        Product temp = s.findProduct(3);
        assertEquals(temp.getID(), p3.getID());
    }

    @Test
    public void Test05() {
        Product p1 = new Product();
        p1.setLocation(1, 2, 3);
        assertEquals(p1.getBin(), 1);
        assertEquals(p1.getShelf(), 2);
        assertEquals(p1.getAisle(), 3);
    }

    @Test
    public void Test06() {
        int count = 0;
        String[] test = { " hi", " hello", " hey" };
        String[] key = { "hi", "hello", "hey" };
        String[] result = WarehouseInterface.stripSpaces(test);
        for (int i = 0; i < result.length; i++) {
            if (result[0].equals(key[0])) {
                count++;
            }
        }
        assertEquals(count, result.length);
    }

    @Test
    public void Test07() {
        String change = "$100.0";
        assertEquals(WarehouseInterface.parseStringToDouble(change, "$"), 100.0);
    }

    @Test
    public void Test08() {
        Supplier s = new Supplier();
        ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>(
                Arrays.asList(new Warehouse("California"), new Warehouse("Italy"), new Warehouse("Berlin")));
        s.setWarehouses(warehouses);
        assertEquals(s.findWarehouse("Italy").getName(), "Italy");
    }

    @Test
    public void Test09() {
        File check = new File("src/assignment5/orders.csv");
        assertEquals(WarehouseInterface.validateOrderCSV(check), true);
    }

    @Test
    public void Test10() {
        int count = 0;
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();
        Product p5 = new Product();
        p1.setLocation(1, 2, 3);
        p1.setID(1);
        p2.setLocation(1, 2, 1);
        p2.setID(2);
        p3.setLocation(4, 2, 5);
        p3.setID(3);
        p4.setLocation(2, 2, 3);
        p4.setID(4);
        p5.setLocation(5, 2, 3);
        p5.setID(5);
        ArrayList<Product> testSort = new ArrayList<Product>(Arrays.asList(p1, p2, p3, p4, p5));
        Collections.sort(testSort);
        ArrayList<Product> key = new ArrayList<Product>(Arrays.asList(p2, p1, p4, p5, p3));
        for (int i = 0; i < testSort.size(); i++) {
            if (testSort.get(i).getID() == key.get(i).getID()) {
                count++;
            }
        }
        assertEquals(count, testSort.size());
    }
}
