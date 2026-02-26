package Jaako.PKproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainerName; // 파티를 쓴 실제 유저 이름
    private String partyDescription; // 파티 설명 (예: 시즌 20 최종 1위 파티)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 이 파티를 기록한 사람(나)

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<PokemonSample> pokemonSamples = new ArrayList<>();

    public void addPokemonSample(PokemonSample sample) {
        this.pokemonSamples.add(sample);
        sample.setParty(this);
    }
}
