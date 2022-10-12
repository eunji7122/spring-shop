package portfolio.shop.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.member.Member;
import portfolio.shop.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
