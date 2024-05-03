package GymManagement;

import java.util.Date;

public abstract class MembershipPlan {
    private Member member;
    private Date openingDate;

    /**
     * This method is the member setter
     * 
     * @param member Member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * This method gets the current member of the plan
     * 
     * @return Member that has the curent Membership plan
     */
    public Member getMember() {
        return member;
    }

    /**
     * This method gets the opening date of the membership plan
     * 
     * @return Date that the membership was opened
     */
    public Date getOpeningDate() {
        return openingDate;
    }
}