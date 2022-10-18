package portfolio.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portfolio.shop.repository.item.ItemRepository;
import portfolio.shop.repository.item.JpaItemRepository;
import portfolio.shop.repository.member.JpaMemberRepository;
import portfolio.shop.repository.member.MemberRepository;
import portfolio.shop.service.item.ItemService;
import portfolio.shop.service.member.MemberService;

import javax.persistence.EntityManager;

@Configuration
public class Config {

    private final EntityManager em;

    public Config(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(em);
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }
}
