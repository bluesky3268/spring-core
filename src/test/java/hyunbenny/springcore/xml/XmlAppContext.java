package hyunbenny.springcore.xml;

import hyunbenny.springcore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        /**
         * appConfig.xml 내용
         *
         *     <bean id="memberService" class="hyunbenny.springcore.member.MemberServiceImpl">
         *         <constructor-arg name="memberRepository" ref="memberRepository"/>
         *     </bean>
         *
         *     <bean id="memberRepository" class="hyunbenny.springcore.member.MemoryMemberRepository"/>
         *
         *     <bean id="orderService" class="hyunbenny.springcore.order.OrderServiceImpl">
         *         <constructor-arg name="memberRepository" ref="memberRepository"/>
         *         <constructor-arg name="discountPolicy" ref="discountPolicy"/>
         *     </bean>
         *
         *     <bean id="discountPolicy" class="hyunbenny.springcore.discount.RateDiscountPolicy"></bean>
         *
         */
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
