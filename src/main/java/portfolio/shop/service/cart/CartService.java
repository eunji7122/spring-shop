package portfolio.shop.service.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.cart.CartItem;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.member.Member;
import portfolio.shop.repository.cart.CartRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void addItemToCart(Long itemId, Long memberId, int itemCount) {
        List<CartItem> cartItems = cartRepository.findAll(memberId);
        Optional<CartItem> existingCartItem = cartItems.stream().filter(cartItem -> Objects.equals(cartItem.getItem().getId(), itemId)).findFirst();
        if (existingCartItem.isPresent()) {
            update(existingCartItem.get().getId(), existingCartItem.get().getCount() + itemCount);
        } else {
            Item item = new Item();
            item.setId(itemId);
            Member member = new Member();
            member.setId(memberId);
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setMember(member);
            cartItem.setCount(itemCount);
            save(cartItem);
        }
    }

    public CartItem save(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    public void update(Long cartItemId, int count) {
        cartRepository.update(cartItemId, count);
    }

    public List<CartItem> findCartItems(Long memberId) {
        return cartRepository.findAll(memberId);
    }

    public void delete(Long cartItemId) {
        cartRepository.delete(cartItemId);
    }
}
