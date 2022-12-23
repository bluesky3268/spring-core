package hyunbenny.springcore;

import hyunbenny.springcore.discount.DiscountPolicy;
import hyunbenny.springcore.discount.FixDiscountPolicy;
import hyunbenny.springcore.discount.RateDiscountPolicy;
import hyunbenny.springcore.member.MemberRepository;
import hyunbenny.springcore.member.MemberService;
import hyunbenny.springcore.member.MemberServiceImpl;
import hyunbenny.springcore.member.MemoryMemberRepository;
import hyunbenny.springcore.order.OrderService;
import hyunbenny.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * IOC(제어의 역전)
 * 기존에는 MemberServiceImpl이 어떤 리포지토리를 갖다 쓸 것인지 정해서 사용을 했었다.
 * 하지만 AppConfig로 인해 프로그램의 제어 흐름을 MemberServiceImpl이 직접하는 것이 아니라
 * 외부(AppConfig)에서 관리하고 MemberServiceImpl은 자신의 로직을 실행하는 역할만 한다.
 * 이를 제어의 역전이라고 한다.
 */

/**
  * 순수 자바 코드에서 스프링으로 전환하기
 * @Configuration
 * @Bean
 * 을 붙여주면 스프링 컨테이너에 등록되서 스프링이 관리할 수 있게 된다.
  */
@Configuration // 순수 자바 코드에서 스프링으로 전환하기
public class AppConfig {

    /**
     * V1
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//    }

    /**
     * V2
     * 역할과 구현이 잘 드러나도록 리팩토링
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    // 할인 정책이 변경된다면 이 부분에서의 구현체만 바꿔끼우면 된다.
//    // -> 클라이언트 영역의 코드는 전혀 손댈 필요가 없다.
//    public DiscountPolicy discountPolicy() {
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }

    /**
     * V3
     * 순수 자바 코드에서 스프링으로 전환
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 할인 정책이 변경된다면 이 부분에서의 구현체만 바꿔끼우면 된다.
    // -> 클라이언트 영역의 코드는 전혀 손댈 필요가 없다.
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
