package Jaako.PKproject.controller;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PartyForm {
    private String trainerName;
    private String partyDescription;
    private List<PokemonSampleForm> pokemonSamples = new ArrayList<>();

    // 폼이 생성될 때 6개의 빈 포켓몬 입력칸을 미리 만들어둡니다.
    public PartyForm() {
        for (int i = 0; i < 6; i++) {
            pokemonSamples.add(new PokemonSampleForm());
        }
    }

    @Getter @Setter
    public static class PokemonSampleForm {
        private String pokemonName;
        private String item;
        private String ability;
        private String nature;
        private String effortValues;
        private String moves;
    }
}