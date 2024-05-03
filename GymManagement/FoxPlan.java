package GymManagement;

public class FoxPlan extends MembershipPlan {
    private final double costPerMonth = 19.99;
    private final double gusestCost = 0.00;
    private final double groupCost = 0.00;
    private final double groupCostPerGuest = 0.00;
    private final boolean equipment = true;
    private static int numPeopleFox = 0;
    private int currentNumberMember;

    /**
     * No arg constructor
     */
    public FoxPlan() {
        numPeopleFox++;
        currentNumberMember = numPeopleFox;
    }

    /**
     * This method gets the cost per month of the FoxPlan
     * 
     * @return double cost per month
     */
    public double getCostPerMonth() {
        return costPerMonth;
    }

    /**
     * This method gets the guest cost of the FoxPlan
     * 
     * @return double guest cost
     */
    public double getGuestCost() {
        return gusestCost;
    }

    /**
     * This method gets the group cost of the FoxPlan
     * 
     * @return double group cost
     */
    public double getGroupCost() {
        return groupCost;
    }

    /**
     * This method gets the group cost per guest of the FoxPlan
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
     * This method gets the current members number of the FoxPlan
     * 
     * @return int current number member
     */
    public int getCurrentMemberNumber() {
        return currentNumberMember;
    }

    /**
     * This method gets the total amount of people with the FoxPlan
     * 
     * @return int number of people with the fox plan
     */
    public int getTotalNumFoxPlans() {
        return numPeopleFox;
    }

    /**
     * This method is the overridden toString of the FoxPlan class
     */
    public String toString() {
        return "There are " + numPeopleFox + " people with the fox plan";
    }
}
