package hyunbenny.springcore.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

//        // Thread1
//        statefulService1.order("userA", 10000);
//
//        // Thread2
//        statefulService2.order("userB", 30000);
//
//        // Thread1 : userA가 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
//
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(30000);

        // Thread1
        int userAPrice = statefulService1.order("userA", 10000);

        // Thread2
        int userBPrice = statefulService2.order("userB", 30000);


        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userBPrice).isEqualTo(30000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
