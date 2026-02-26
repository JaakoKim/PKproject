package Jaako.PKproject.service;

import Jaako.PKproject.domain.Member;
import Jaako.PKproject.domain.Party;
import Jaako.PKproject.domain.PokemonSample;
import Jaako.PKproject.repository.MemberRepository;
import Jaako.PKproject.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;

    /**
     * 파티 등록
     */
    @Transactional
    public Long saveParty(Long memberId, Party party) {
        // 1. 엔티티 조회 (기록하는 사람)
        Member member = memberRepository.findById(memberId).get();

        // 2. 연관관계 설정
        party.setMember(member);

        // 3. 파티 저장 (Cascade 설정 덕분에 안에 담긴 샘플들도 같이 저장됨)
        partyRepository.save(party);
        return party.getId();
    }

    /**
     * 내가 기록한 모든 파티 조회
     */
    public List<Party> findParties(Long memberId) {
        return partyRepository.findByMemberId(memberId);
    }
}