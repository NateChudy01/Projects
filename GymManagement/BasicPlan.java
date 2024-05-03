package GymManagement;

public class BasicPlan extends MembershipPlan {
    private final double costPerMonth = 9.99;
    private final double gusestCost = 10.00;
    private final double groupCost = 5.00;
    private final double groupCostPerGuest = 5.00;
    private final boolean equipment = true;
    private static int numPeopleBasic = 0;
    private int currentNumberMember;

    /**
     * No arg constructor
     */
    public BasicPlan() {
        numPeopleBasic++;
        currentNumberMember = numPeopleBasic;
    }

    /**
     * This method gets the cost per month of the BasicPlan
     * 
     * @return double cost per month
     */
    public double getCostPerMonth() {
        return costPerMonth;
    }

    /**
     * This method gets the cost per guest
     * 
     * @return double cost per guest
     */
    public double getGuestCost() {
        return gusestCost;
    }

    /**
     * This method gets the group class price
     * 
     * @return double group class cost
     */
    public double getGroupCost() {
        return groupCost;
    }

    /**
     * This method gets the group cost per guest
     * 
     * @return double group cost per guest
     */
    public double getGroupCostPerGuest() {
        return groupCostPerGuest;
    }

    /**
     * This method gets the equipment status
     * 
     * @return boolean equipment status
     */
    public boolean getEquipmentStatus() {
        return equipment;
    }

    /**
     * This method gets the current number member with the BasicPlan
     * 
     * @return int current number member
     */
    public int getCurrentMemberNumber() {
        return currentNumberMember;
    }

    /**
     * This method gets the current number of members with the BasicPlan
     * 
     * @return int number of people with the BasicPlan
     */
    public int getTotalNumBasicPlans() {
        return numPeopleBasic;
    }

    /**
     * This method is the overridden toString of the BasicPlan class
     */
    public String toString() {
        return "There are " + numPeopleBasic + " people with the basic plan";
    }
}