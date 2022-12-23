package hyunbenny.springcore.discount;

import hyunbenny.springcore.member.Grade;
import hyunbenny.springcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 정액 할인 1000원

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
