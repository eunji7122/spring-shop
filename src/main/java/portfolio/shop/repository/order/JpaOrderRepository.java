package portfolio.shop.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.domain.order.Orders;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static portfolio.shop.domain.order.QOrders.orders;


@Repository
@Transactional
public class JpaOrderRepository implements OrderRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaOrderRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Orders save(Orders orders) {
        em.persist(orders);
        return orders;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        Orders orders = em.find(Orders.class, id);
        return Optional.ofNullable(orders);
    }

    @Override
    public List<Orders> findAll(Long memberId) {
        return query.select(orders)
                .from(orders)
                .where(orders.member.id.like(String.valueOf(memberId)))
                .orderBy(orders.id.asc())
                .fetch();
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id).get());
    }
}
