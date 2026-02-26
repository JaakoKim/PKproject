package Jaako.PKproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PokemonSample {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pokemonName; // 포켓몬 이름
    private String item;        // 지닌 물건
    private String ability;     // 특성
    private String nature;      // 성격

    // 노력치와 기술은 일단 문자열로 저장하고, 나중에 필요하면 객체로 분리합시다.
    private String effortValues; // 예: H252 A252 S4
    private String moves;        // 예: 지진, 스톤샤워, 맹독, 방어

    // [중요] 이 샘플을 작성한 유저와 연결 (다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 가입 날짜처럼 생성 시간도 있으면 관리하기 편하겠죠?
    // 나중에 공통 시간 엔티티로 분리할 수도 있지만 일단은 필드로 넣겠습니다.
}