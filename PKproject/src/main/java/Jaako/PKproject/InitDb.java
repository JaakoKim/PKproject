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
        try {
            Member member = new Member();
            member.setLoginId("jaako");
            member.setNickname("코딩하는지우");
            memberService.join(member); // 여기서 1번 회원이 생성됨
            System.out.println("✅ 초기 회원 1명 세팅 완료");
        } catch (IllegalStateException e) {
            // MemberService에서 중복 회원 에러를 던지면 여기로 빠져서 무시됨
            System.out.println("⚠️ 이미 초기 회원이 DB에 존재합니다. 세팅을 건너뜁니다.");
        } catch (Exception e) {
            System.out.println("⚠️ 알 수 없는 에러가 발생했지만 서버는 띄웁니다.");
        }
    }
}