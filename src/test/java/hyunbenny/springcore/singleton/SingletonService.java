package hyunbenny.springcore.singleton;

/**
 * 그냥 AppConfig를 사용하면 요청이 들어올 떄마다 객체를 생성하는데
 * tps가 많으면...?
 * <p>
 * -> 딱 하나만 생성하고 생성된 객체 인스턴스를 공유해서 사용하는 것이 좋다.
 * => 싱글톤 패턴
 * 
 * 스프링의 기본 빈 등록방식은 싱글톤
 * 
 * 싱글톤 방식의 주의점
 * - 인스턴스를 공유하기 때문에 객체의 상태를 유지하도록 설계하면 안된다.
 * 스프링 빈은 항상 무상태(stateless)로 설게해야 함!!!

 */
public class SingletonService {
    // static으로 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 객체가 필요하면 singletonService() 를 호출하여 인스턴스를 받아서 쓰도록 함
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private로 선언하여 객체 생성을 못하게 막음 
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
