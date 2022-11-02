package portfolio.shop.repository.order;

import portfolio.shop.domain.order.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {

    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findById(Long orderItemId);

    List<OrderItem> findAll(Long OrderId);

    void delete(Long orderItemId);
}
