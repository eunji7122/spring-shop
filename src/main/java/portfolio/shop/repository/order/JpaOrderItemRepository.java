package portfolio.shop.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.domain.order.OrderItem;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static portfolio.shop.domain.order.QOrderItem.orderItem;

@Repository
@Transactional
public class JpaOrderItemRepository implements OrderItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaOrderItemRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem;
    }

    @Override
    public Optional<OrderItem> findById(Long orderItemId) {
        OrderItem orderItem = em.find(OrderItem.class, orderItemId);
        return Optional.ofNullable(orderItem);
    }

    @Override
    public List<OrderItem> findAll(Long OrderId) {
        return query.select(orderItem)
                .from(orderItem)
                .where(orderItem.orders.id.like(String.valueOf(OrderId)))
                .orderBy(orderItem.id.asc())
                .fetch();
    }

    @Override
    public void delete(Long orderItemId) {
        em.remove(findById(orderItemId).get());
    }
}
