package hyunbenny.springcore.discount;

import hyunbenny.springcore.member.Grade;
import hyunbenny.springcore.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountRate / 100;
        }else{
            return 0;
        }
    }
}
