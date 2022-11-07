package portfolio.shop.web.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portfolio.shop.domain.auth.OAuth2UserDetails;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.member.Member;
import portfolio.shop.domain.order.OrderState;
import portfolio.shop.domain.order.Orders;
import portfolio.shop.service.cart.CartService;
import portfolio.shop.service.order.OrderItemService;
import portfolio.shop.service.order.OrderService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CartService cartService;

    @GetMapping()
    public String orders(Model model, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        List<Orders> orders = orderService.findAll(member.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("cartItemCount", cartService.findCartItems(member.getId()).size());

        return "order/orders";
    }

    @PostMapping("/new")
    public String orderItem(@AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();

        List<CartItem> cartItems = cartService.findCartItems(member.getId());

        Long orderId = orderService.order(member.getId(), cartItems);

        for (CartItem cartItem : cartItems) {
            orderItemService.saveOrderItem(orderId, cartItem.getItem().getId(), cartItem.getCount());
        }

        for (CartItem cartItem : cartItems) {
            cartService.delete(cartItem.getId());
        }
        return "redirect:/order";
    }

    @PostMapping("/cancel")
    public String CancelOrder(@RequestParam(value = "cancel-button", required = false) Long orderId) {
        orderService.findById(orderId).setState(OrderState.CANCEL);

//        orderItemService.deleteOrderItem(orderId);
//        orderService.deleteItem(orderId);

        return "redirect:/order";
    }
}
