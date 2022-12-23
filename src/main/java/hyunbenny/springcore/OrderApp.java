package hyunbenny.springcore;

import hyunbenny.springcore.member.Grade;
import hyunbenny.springcore.member.Member;
import hyunbenny.springcore.member.MemberService;
import hyunbenny.springcore.member.MemberServiceImpl;
import hyunbenny.springcore.order.Order;
import hyunbenny.springcore.order.OrderService;
import hyunbenny.springcore.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        /**
         * V1
         */
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        /**
         * V2
         * AppConfig를 통해서 OCP와 DIP를 위반하지 않는 객체 설계
         */
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         * V3
         * 스프링으로 전환하기
         * ApplicationContext : 스프링 컨테이너
         * AnnotationConfigApplicationContext는 ApplicationContext의 구현체
         * Bean의 이름은 항상 중복이 되면 안된다.
         * 스프링 컨테이너는 설정정보(AppConfig)를 참고하여 의존관계를 주입한다.
         *
         * BeanFactory(최상위) : 스프링 빈을 관리, 조회하는 역할
         *
         * ApplicationContext(interface) : BeanFactory의 기능을 모두 상속받아서 제공 + 부가기능 제공
         *  부가기능 :
         *    EnvironmentCapable(로컬, 개발, 운영등 환경변수를 구분해서 처리)
         *    MessageSource(국제화 기능)
         *    ApplicationEventPublisher(이벤트를 발행, 구독하는 모델을 편리하게 지원)
         *    ResourcePatternResolver(파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회)
         *
         * -> BeanFactory를 사용하는 경우는 거의 없고, BeanFactory를 사용한다.
         * BeanFactory와 BeanFactory를 스프링 컨테이너라고 한다.
         *
         * AnnotationConfigApplicationContext: 자바코드로 어노테이션을 사용할 때 쓰는 구현체
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order : " + order);
        System.out.println("discountedPrice : " + order.calculatedPrice());

    }
}
