package portfolio.shop.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static portfolio.shop.domain.member.QMember.member;

@Repository
@Transactional
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> findMember = query.select(member)
                .from(member)
                .where(member.email.like(email))
                .fetch();
        if (findMember.size() != 0) {
            return Optional.ofNullable(findMember.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return query.select(member)
                .from(member)
                .orderBy(member.id.asc())
                .fetch();
    }
}
