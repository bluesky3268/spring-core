package hyunbenny.springcore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 인터페이스(추상화)에만 의존해야 하는데 구현체(구체화)에도 의존하고 있다.
    // 변경에 생기면 문제가 발생할 수 있다. -> DIP위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 추상화에만 의존하게 수정하기
     * -> 구체화에 대한 DI는 AppConfig에서 한다. 어떤 구체클래스가 들어올 지에 대해서는 전혀 고민할 필요가 없다.
     * 구체클래스 주입에 대한 고민은 AppConfig에서 해준다
     * => '생성자 주입'이라고 한다.
     */
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
