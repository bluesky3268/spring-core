package hyunbenny.springcore.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    /**
     * 실무에서 동시성 이슈가 발생할 가능성이 있는 경우 ConcurrentHashMap을 사용해야 한다.
     */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
