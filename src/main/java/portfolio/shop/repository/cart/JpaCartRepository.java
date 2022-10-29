package portfolio.shop.repository.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.domain.cart.CartItem;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static portfolio.shop.domain.cart.QCartItem.cartItem;

@Slf4j
@Repository
@Transactional
public class JpaCartRepository implements CartRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaCartRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        em.persist(cartItem);
        return cartItem;
    }

    @Override
    public void update(Long cartItemId, int count) {
        CartItem findCartItem = em.find(CartItem.class, cartItemId);
        findCartItem.setCount(count);
    }

    @Override
    public List<CartItem> findAll(Long memberId) {
        return query.select(cartItem)
                .from(cartItem)
                .where(cartItem.member.id.like(String.valueOf(memberId)))
                .fetch();
    }

    @Override
    public Optional<CartItem> findById(Long cartItemId) {
        CartItem cartItem = em.find(CartItem.class, cartItemId);
        return Optional.ofNullable(cartItem);
    }

    @Override
    public void delete(Long cartItemId) {
        em.remove(findById(cartItemId).get());
    }
}
