package finalProject;

public class BasicPlan extends MembershipPlan{
    private final double costPerMonth = 9.99;
    private final double gusestCost = 10.00;
    private final double groupCost = 5.00;
    private final double groupCostPerGuest = 5.00;
    private final boolean equipment = true;
    private static int numPeopleBasic = 0;
    private int currentNumberMember;

    public BasicPlan(){
        numPeopleBasic++;
        currentNumberMember = numPeopleBasic;
    }

    public double getCostPerMonth(){
        return costPerMonth;
    }

    public double getGuestCost(){
        return gusestCost;
    }

    public double getGroupCost(){
        return groupCost;
    }

    public double getGroupCostPerGuest(){
        return groupCostPerGuest;
    }

    public boolean getEquipmentStatus(){
        return equipment;
    }

    public int getCurrentMemberNumber(){
        return currentNumberMember;
    }

    public int getTotalNumBasicPlans(){
        return numPeopleBasic;
    }

    public String toString(){
        return "There are " + numPeopleBasic + " people with the basic plan";
    }
}