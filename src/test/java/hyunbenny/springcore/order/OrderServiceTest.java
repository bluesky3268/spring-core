package hyunbenny.springcore.order;

import hyunbenny.springcore.AppConfig;
import hyunbenny.springcore.member.Grade;
import hyunbenny.springcore.member.Member;
import hyunbenny.springcore.member.MemberService;
import hyunbenny.springcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder() {
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 50000);

        // Fixed일때
//        assertThat(order.getDiscountPrice()).isEqualTo(1000);
//        assertThat(order.calculatedPrice()).isEqualTo(49000);

        // Rate일 때
        assertThat(order.getDiscountPrice()).isEqualTo(5000);
        assertThat(order.calculatedPrice()).isEqualTo(45000);
    }
}
