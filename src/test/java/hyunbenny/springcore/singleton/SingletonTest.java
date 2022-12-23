package hyunbenny.springcore.singleton;

import hyunbenny.springcore.AppConfig;
import hyunbenny.springcore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이터")
    void pureDIContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴 적용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        /**
         * isEqualTo : .equals() 비교
         * isSameAs : == 비교
         */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
