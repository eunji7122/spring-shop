package portfolio.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.order.OrderItem;
import portfolio.shop.domain.order.Orders;
import portfolio.shop.repository.order.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void saveOrderItem(Long orderId, Long itemId, int itemCount) {
        Item item = new Item();
        item.setId(itemId);

        Orders orders = new Orders();
        orders.setId(orderId);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrders(orders);
        orderItem.setCount(itemCount);

        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAll(orderId);
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.delete(orderItem.getId());
        }
    }
}
