package GymManagement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class RFFCTest {
    @Test
    public void Test01() {
        Product p = new Product();
        p.setName("Headband");
        assertEquals(p.getName(), "Headband");
    }

    @Test
    public void Test02() {
        // this is for creating a time for a certain time on a certain day
        assertEquals(RFFC.findTime(2), "9:00am-10:00am");
    }

    @Test
    public void Test03() {
        // this is for creating a time for a certain time on a certain day
        assertEquals(RFFC.findDayOfWeek(4), "Friday");
    }

    @Test
    public void Test04() {
        Gym gym = new Gym();
        gym.addMember(new Member());
        assertEquals(gym.findMember(1).getFirstName(), "John");
    }

    @Test
    public void Test05() {
        Gym gym = new Gym();
        gym.addEmployee(new Employee());
        assertEquals(gym.findEmployee(1).getFirstName(), "John");
    }

    @Test
    public void Test06() {
        Member member = new Member();
        Classes classes = new Classes();
        member.enroll(classes);
        assertEquals(member.findClassID(1), true);
    }

    @Test
    public void Test07() {
        Member member = new Member();
        member.setBasicPlan(new BasicPlan());
        member.setFoxPlan(new FoxPlan());
        assertEquals(member.getBasicPlan(), null);
    }

    @Test
    public void Test08() {
        Member member = new Member();
        member.setBasicPlan(new BasicPlan());
        member.cancelAccount();
        assertEquals(member.getBasicPlan(), null);
        assertEquals(member.getFoxPlan(), null);
    }

    @Test
    public void Test09() {
        Classes classes = new Classes();
        Classes classes2 = new Classes();
        Classes classes3 = new Classes();
        ArrayList<Classes> allClasses = new ArrayList<Classes>(Arrays.asList(classes, classes2, classes3));
        Member member = new Member();
        member.setClasses(allClasses);
        assertEquals(member.findClass(classes), true);
        assertEquals(member.findClass(classes2), true);
        assertEquals(member.findClass(classes3), true);
    }

    @Test
    public void Test10() {
        Concession c = new Concession();
        Product p = new Product("Shoes");
        c.addProduct(p);
        c.addProduct(new Product("Towel"));
        c.addProduct(new Product("Water Bottles"));
        assertEquals(c.findProductFromName("Shoes"), p);
    }

    @Test
    public void Test11() {
        File check = new File("src/finalProject/classes.csv");
        assertEquals(RFFC.parseClasses(check), true);
    }

    @Test
    public void Test12() {
        File check = new File("src/finalProject/employees.csv");
        assertEquals(RFFC.parseEmployees(check), true);
    }

    @Test
    public void Test13() {
        File check = new File("src/finalProject/members.csv");
        assertEquals(RFFC.parseMembers(check), true);
    }

    @Test
    public void Test14() {
        File check = new File("src/finalProject/products.csv");
        assertEquals(RFFC.parseProducts(check), true);
    }

    @Test
    public void Test15() {
        FoxPlan f = new FoxPlan();
        FoxPlan temp = new FoxPlan();
        assertEquals(f.getTotalNumFoxPlans(), 3);
        // Test07 initilized another fox plan so there has to be three
    }
}
