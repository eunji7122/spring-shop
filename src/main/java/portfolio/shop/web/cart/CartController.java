package portfolio.shop.web.cart;

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
import portfolio.shop.service.cart.CartService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String cart(Model model, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        List<CartItem> cartItems = cartService.findCartItems(member.getId());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartService.getTotalPrice(cartItems));
        model.addAttribute("totalCount", cartItems.size());

        return "cart/cart";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "checkbox-list", required = false) Long[] checkboxList) {
        if (checkboxList != null) {
            for (Long value : checkboxList)
                cartService.delete(value);
        }
        return "redirect:/cart";
    }
}
