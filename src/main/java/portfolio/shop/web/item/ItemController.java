package portfolio.shop.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.domain.auth.OAuth2UserDetails;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.member.Member;
import portfolio.shop.service.cart.CartService;
import portfolio.shop.service.item.ItemService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CartService cartService;

    @GetMapping("/")
    public String items(@ModelAttribute("itemSearch")ItemSearchCond itemSearch, Model model, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        List<Item> items = itemService.findItems(itemSearch);
        model.addAttribute("items", items);
        model.addAttribute("cartItemCount", cartService.findCartItems(member.getId()).size());
        return "home";
    }

    @GetMapping("/item/{itemId}")
    public String item(@PathVariable Long itemId, Model model, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        model.addAttribute("cartItemCount", cartService.findCartItems(member.getId()).size());

        return "item/item";
    }

    @PostMapping("/item/{itemId}/cart/add")
    public String AddCart(@PathVariable Long itemId, @ModelAttribute CartItem cartItem, @AuthenticationPrincipal OAuth2UserDetails oAuth2UserDetails) {
        Member member = oAuth2UserDetails.getMember();
        cartService.addItemToCart(itemId, member.getId(), cartItem.getCount());

        return "redirect:/item/{itemId}";
    }
}
