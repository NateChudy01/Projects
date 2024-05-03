package finalProject;

import java.util.Date;

public abstract class MembershipPlan{
    private Member member;
    private Date openingDate;

    public void setMember(Member member){
        this.member = member;
    }

    public Member getMember(){
        return member;
    }

    public Date getOpeningDate(){
        return openingDate;
    }
}