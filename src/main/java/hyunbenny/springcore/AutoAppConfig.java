package hyunbenny.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
//        , basePackages = {"hyunbenny.springcore.member"}
)
public class AutoAppConfig {

    /**
     * V4
     * 컴포넌트 스캔과 autowired
     * @ComponentScan : 설정정보(AppConfig)가 없어도 @Component가 붙은 애들을 찾아서 빈으로 등록
     * @Autowired : 의존관계 자동 주입
     * 
     * -> 빈으로 등록할 대상 클래스에 @Component를 붙여주면 됨
     * -> 자동의존관계 주입을 위해 생성자에 @Autowired를 붙여준다.
     */


}
