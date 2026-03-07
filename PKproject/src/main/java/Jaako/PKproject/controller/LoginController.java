// PKproject/src/main/java/Jaako/PKproject/controller/LoginController.java
package Jaako.PKproject.controller;

import Jaako.PKproject.domain.Member;
import Jaako.PKproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 1. 로그인 화면 보여주기
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    // 2. 실제 로그인 처리 로직
    @PostMapping("/login")
    public String login(LoginForm form, HttpServletRequest request, Model model) {
        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            // 로그인 실패 시 폼으로 다시 이동 (에러 메시지 포함)
            model.addAttribute("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 시: 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관 ("loginMember"라는 이름의 사물함에 객체 저장)
        session.setAttribute("loginMember", loginMember);

        return "redirect:/parties"; // 성공하면 파티 리스트 화면으로 이동
    }

    // 3. 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 날려버리기
        }
        return "redirect:/login";
    }
}