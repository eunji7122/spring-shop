package portfolio.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.member.Member;
import portfolio.shop.domain.order.OrderState;
import portfolio.shop.domain.order.Orders;
import portfolio.shop.repository.order.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long order(Long memberId, List<CartItem> cartItems) {
        Member member = new Member();
        member.setId(memberId);

        Orders orders = new Orders();
        orders.setDate(LocalDate.now());
        orders.setName(LocalDate.now() + "_" + orders.getId());
        orders.setPrice(getTotalPrice(cartItems));
        orders.setState(OrderState.PAYMENT);
        orders.setMember(member);

        orderRepository.save(orders);

        return orders.getId();
    }

    public List<Orders> findAll(Long memberId) {
        return orderRepository.findAll(memberId);
    }

    public void deleteItem(Long id) {
        orderRepository.delete(id);
    }

    private int getTotalPrice(List<CartItem> cartItems) {
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getItem().getPrice() * cartItem.getCount();
        }
        return totalPrice;
    }
}
