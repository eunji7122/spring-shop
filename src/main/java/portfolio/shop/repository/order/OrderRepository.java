package portfolio.shop.repository.order;

import portfolio.shop.domain.order.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Orders save(Orders orders);

    Optional<Orders> findById(Long id);

    List<Orders> findAll(Long memberId);

    void delete(Long id);
}
