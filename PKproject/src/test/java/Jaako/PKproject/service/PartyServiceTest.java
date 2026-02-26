package Jaako.PKproject.service;

import Jaako.PKproject.domain.Member;
import Jaako.PKproject.domain.Party;
import Jaako.PKproject.domain.PokemonSample;
import Jaako.PKproject.repository.PartyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PartyServiceTest {

    @Autowired MemberService memberService;
    @Autowired PartyService partyService;
    @Autowired PartyRepository partyRepository;

    @Test
    public void 파티_샘플_함께_저장_테스트() throws Exception {
        // given (상황 설정)
        // 1. 나(Member) 등록
        Member me = new Member();
        me.setLoginId("jaako123");
        me.setNickname("코딩하는지우");
        Long memberId = memberService.join(me);

        // 2. 남의 파티(Party) 생성
        Party party = new Party();
        party.setTrainerName("박세준"); // 실제 파티 주인 이름
        party.setPartyDescription("2014 월드챔피언십 우승 파티");

        // 3. 포켓몬 샘플들 생성 및 파티에 추가
        PokemonSample p1 = createSample("파치리스", "자뭉열매");
        PokemonSample p2 = createSample("한카리아스", "기합의띠");
        // (6마리 다 채워도 되지만 테스트용으로 2마리만 넣어봅시다)

        party.addPokemonSample(p1);
        party.addPokemonSample(p2);

        // when (실행)
        Long partyId = partyService.saveParty(memberId, party);

        // then (검증)
        Party findParty = partyRepository.findById(partyId).get();

        assertThat(findParty.getTrainerName()).isEqualTo("박세준");
        assertThat(findParty.getPokemonSamples().size()).isEqualTo(2);
        assertThat(findParty.getPokemonSamples().get(0).getPokemonName()).isEqualTo("파치리스");
    }

    // 샘플 생성 편의 메서드
    private PokemonSample createSample(String name, String item) {
        PokemonSample sample = new PokemonSample();
        sample.setPokemonName(name);
        sample.setItem(item);
        return sample;
    }
}