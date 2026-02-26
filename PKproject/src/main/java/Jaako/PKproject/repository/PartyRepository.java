package Jaako.PKproject.repository;

import Jaako.PKproject.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    // 내가 기록한 파티들만 모아보기 위해 추가
    List<Party> findByMemberId(Long memberId);
}