package Jaako.PKproject.controller;

import Jaako.PKproject.domain.Party;
import Jaako.PKproject.domain.PokemonSample;
import Jaako.PKproject.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    // 1. 등록 화면 보여주기
    @GetMapping("/parties/new")
    public String createForm(Model model) {
        model.addAttribute("partyForm", new PartyForm());
        return "parties/createPartyForm";
    }

    // 2. 실제 등록 처리
    @PostMapping("/parties/new")
    public String create(PartyForm form) {
        Party party = new Party();
        party.setTrainerName(form.getTrainerName());
        party.setPartyDescription(form.getPartyDescription());

        for (PartyForm.PokemonSampleForm p : form.getPokemonSamples()) {
            if (p.getPokemonName() == null || p.getPokemonName().isEmpty()) continue;

            PokemonSample sample = new PokemonSample();
            sample.setPokemonName(p.getPokemonName());
            sample.setItem(p.getItem());
            sample.setAbility(p.getAbility());
            sample.setNature(p.getNature());
            sample.setEffortValues(p.getEffortValues());
            sample.setMoves(p.getMoves());

            party.addPokemonSample(sample);
        }

        // 로그인 기능이 아직 없으므로, 임시로 1번 회원(본인)으로 저장합니다.
        // 테스트 전 MemberServiceTest 등을 통해 회원 1명이 미리 가입되어 있어야 합니다.
        partyService.saveParty(1L, party);

        return "redirect:/parties"; // 홈으로 리다이렉트
    }
    @GetMapping("/parties")
    public String list(Model model) {
        // 현재는 로그인 기능이 없으므로 하드코딩된 1번 회원의 파티만 가져옵니다.
        List<Party> parties = partyService.findParties(1L);
        model.addAttribute("parties", parties);
        return "parties/partyList";
    }
}