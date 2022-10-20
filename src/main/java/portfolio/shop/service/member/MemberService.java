package portfolio.shop.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.member.Member;
import portfolio.shop.repository.member.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
