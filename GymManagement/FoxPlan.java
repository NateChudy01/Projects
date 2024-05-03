package finalProject;

public class FoxPlan extends MembershipPlan{
    private final double costPerMonth = 19.99;
    private final double gusestCost = 0.00;
    private final double groupCost = 0.00;
    private final double groupCostPerGuest = 0.00;
    private final boolean equipment = true;
    private static int numPeopleFox = 0;
    private int currentNumberMember;

    public FoxPlan(){
        numPeopleFox++;
        currentNumberMember = numPeopleFox;
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

    public int getTotalNumFoxPlans(){
        return numPeopleFox;
    }

    public String toString(){
        return "There are " + numPeopleFox + " people with the fox plan";
    }
}
