package hyunbenny.springcore.beanFind;

import hyunbenny.springcore.AppConfig;
import hyunbenny.springcore.discount.DiscountPolicy;
import hyunbenny.springcore.member.MemberRepository;
import hyunbenny.springcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameTypeBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름 지정해야 함")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllBeanByName() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        Set<Map.Entry<String, MemberRepository>> entries = beansOfType.entrySet();
        for (Map.Entry<String, MemberRepository> entry : entries) {
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
            System.out.println("====================");
        }

        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + ", value : " + beansOfType.get(key));
        }
        System.out.println("beansOfType : " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }


}
