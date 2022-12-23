package hyunbenny.springcore.order;

import hyunbenny.springcore.discount.DiscountPolicy;
import hyunbenny.springcore.discount.FixDiscountPolicy;
import hyunbenny.springcore.discount.RateDiscountPolicy;
import hyunbenny.springcore.member.Member;
import hyunbenny.springcore.member.MemberRepository;
import hyunbenny.springcore.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * 이렇게 수정하면
     * DIP 위반 : 인터페이스(추상화)에 의존해야 하는데 인터페이스 + 구체클래스에 의존하고 있음
     * OCP 위반 : 난 할인 정책을 바꾸고 싶은데 클라이언트의 코드도 수정을 해야 되니깐..
     *
     * -> 이를 해결하기 위해서는 구현체를 대신 넣어줄 것이 필요하다. => AppConfig
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    /**
     * 추상화에만 의존하게 수정하기
     * -> 구체화에 대한 DI는 AppConfig에서 한다. 어떤 구체클래스가 들어올 지에 대해서는 전혀 고민할 필요가 없다.
     * 구체클래스 주입에 대한 고민은 AppConfig에서 해준다
     * => '생성자 주입'이라고 한다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
