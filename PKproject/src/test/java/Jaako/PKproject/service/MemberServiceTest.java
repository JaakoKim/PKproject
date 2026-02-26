package Jaako.PKproject.service;

import Jaako.PKproject.domain.Member;
import Jaako.PKproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너를 띄워서 테스트 (실제 빈 주입)
@Transactional   // 테스트가 끝나면 DB를 롤백해줘서 반복 테스트 가능하게 함
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given (이런 상황에서)
        Member member = new Member();
        member.setLoginId("pika123");
        member.setNickname("피카츄매니아");

        // when (이걸 실행하면)
        Long savedId = memberService.join(member);

        // then (결과가 이래야 한다)
        Member findMember = memberRepository.findById(savedId).get();
        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given (동일한 ID를 가진 두 회원)
        Member member1 = new Member();
        member1.setLoginId("duplicate");

        Member member2 = new Member();
        member2.setLoginId("duplicate");

        // when (첫 번째는 성공, 두 번째는 실패해야 함)
        memberService.join(member1);

        // then (예외가 발생해야 함)
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}