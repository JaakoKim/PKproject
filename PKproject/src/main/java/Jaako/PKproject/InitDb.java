package Jaako.PKproject;

import Jaako.PKproject.domain.Member;
import Jaako.PKproject.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final MemberService memberService;

    @PostConstruct // 서버가 뜨자마자 실행됨
    public void init() {
        Member member = new Member();
        member.setLoginId("jaako");
        member.setNickname("코딩하는지우");
        memberService.join(member); // 여기서 1번 회원이 생성됨
    }
}