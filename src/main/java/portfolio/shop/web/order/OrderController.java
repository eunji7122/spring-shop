package portfolio.shop.web.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.shop.domain.auth.OAuth2UserDetails;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.member.Member;
import portfolio.shop.domain.order.Orders;
import portfolio.shop.service.order.OrderService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public String orders(Model model, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        List<Orders> orders = orderService.findAll(member.getId());
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @PostMapping("/new")
    public String orderItem(@ModelAttribute List<CartItem> cartItems, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        orderService.orderItem(member.getId(), cartItems);
        return "redirect:/order";
    }
}
