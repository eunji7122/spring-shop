package portfolio.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.member.Member;
import portfolio.shop.domain.order.Orders;
import portfolio.shop.repository.order.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Orders orderItem(Long memberId, List<CartItem> cartItems) {
        Member member = new Member();
        member.setId(memberId);

        Orders orders = new Orders();
        orders.setDate(LocalDate.now());
        orders.setName(LocalDate.now() + "_" + orders.getId());

        return orderRepository.save(orders);
    }

    public List<Orders> findAll(Long memberId) {
        return orderRepository.findAll(memberId);
    }

    public void deleteItem(Long id) {
        orderRepository.delete(id);
    }

}
