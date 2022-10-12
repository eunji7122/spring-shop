package portfolio.shop.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.shop.domain.Role;
import portfolio.shop.domain.member.Member;
import portfolio.shop.service.member.MemberService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("member") Member member) {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String registerPost(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("registrationFail", "정보를 입력해 주세요.");
            return "auth/registration";
        }

        String encodePwd = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodePwd);

        member.setRole(Role.USER);
        memberService.save(member);

        return "redirect:/auth/login";
    }
}
