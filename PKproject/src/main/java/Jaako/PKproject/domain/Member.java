package Jaako.PKproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter // 롬복 사용
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // 아이디 중복 방지
    private String loginId;   // 로그인용 ID

    private String password;  // 비밀번호
    private String nickname;  // 유저 닉네임 (포켓몬 샘플 작성자로 표시될 이름)

    // 나중에 가입 날짜 같은 것도 넣으면 좋겠죠?
}
