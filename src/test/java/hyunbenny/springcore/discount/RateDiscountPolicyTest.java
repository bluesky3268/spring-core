package hyunbenny.springcore.discount;

import hyunbenny.springcore.member.Grade;
import hyunbenny.springcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {


    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인")
    void VIP10PercentDiscount() {
        // given
        Member member = new Member(1L, "VIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 50000);

        // then
        assertThat(discount).isEqualTo(5000);
    }

    @Test
    @DisplayName("VIP아니면 할인 적용X")
    void NotVIPNoDiscount() {
        // given
        Member member = new Member(1L, "BASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 50000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}