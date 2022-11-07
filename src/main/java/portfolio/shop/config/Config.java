package portfolio.shop.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portfolio.shop.repository.cart.CartRepository;
import portfolio.shop.repository.cart.JpaCartRepository;
import portfolio.shop.repository.item.ItemRepository;
import portfolio.shop.repository.item.JpaItemRepository;
import portfolio.shop.repository.member.JpaMemberRepository;
import portfolio.shop.repository.member.MemberRepository;
import portfolio.shop.repository.order.JpaOrderItemRepository;
import portfolio.shop.repository.order.JpaOrderRepository;
import portfolio.shop.repository.order.OrderItemRepository;
import portfolio.shop.repository.order.OrderRepository;
import portfolio.shop.service.aws.AwsS3Service;
import portfolio.shop.service.cart.CartService;
import portfolio.shop.service.item.ItemService;
import portfolio.shop.service.member.MemberService;
import portfolio.shop.service.order.OrderItemService;
import portfolio.shop.service.order.OrderService;

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

    @Bean
    public CartRepository cartRepository() {
        return new JpaCartRepository(em);
    }

    @Bean
    public CartService cartService() {
        return new CartService(cartRepository());
    }

    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository(em);
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository());
    }

    @Bean
    public OrderItemRepository orderItemRepository() {
        return new JpaOrderItemRepository(em);
    }

    @Bean
    public OrderItemService orderItemService() {
        return new OrderItemService(orderItemRepository());
    }
}
