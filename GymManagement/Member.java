package finalProject;

public class Member {
    private static int MemberID = 0;
    private int MyMemberID;
    private String firstName;
    private String lastName;
    private String gender;
    private int discountMoney;
    private FoxPlan foxPlan;
    private BasicPlan basicPlan;
    private int points;

    public Member(){
        MemberID++;
        this.MyMemberID = MemberID;
        this.firstName = "John";
        this.lastName = "Doe";
        this.gender = "Male";
        this.discountMoney = 0;
        this.foxPlan = null;
        this.basicPlan = new BasicPlan();
        this.points = 0;
    }

    public Member(String firstName, String lastName, String gender){
        MemberID++;
        this.MyMemberID = MemberID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.discountMoney = 0;
        this.foxPlan = null;
        this.basicPlan = new BasicPlan();
        this.points = 0;
    }

    public int getMemberID() {
        return MyMemberID;
    }

    public int getPoints(){
        return points;
    }

    public void checkIn(){
        points+=10;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public FoxPlan getFoxPlan(){
        return foxPlan;
    }

    public BasicPlan getBasicPlan(){
        return basicPlan;
    }

    public void setFoxPlan(FoxPlan foxPlan){
        this.basicPlan = null;
        this.foxPlan = foxPlan;
    }

    public void setBasicPlan(BasicPlan basicPlan){
        this.basicPlan = basicPlan;
        this.foxPlan = null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addDiscountMoney(int money){
        this.discountMoney = this.discountMoney + money;
    }

    public void cancelAccount(){
        this.foxPlan = null;
        this.basicPlan = null;
    }

    public String toString(){
        String returnable = "Name: " + firstName + " " + lastName + ", ID: " + MyMemberID + ", ";
        if(foxPlan != null){
            returnable = returnable + "Plan: FoxPlan";
        }else{
            returnable = returnable + "Plan: BasicPlan";
        }
        return returnable;
    }
}
