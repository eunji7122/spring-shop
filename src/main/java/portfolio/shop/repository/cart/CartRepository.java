package portfolio.shop.repository.cart;

import portfolio.shop.domain.cart.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    CartItem save(CartItem cartItem);

    void update(Long cartItemId, int count);

    List<CartItem> findAll(Long memberId);

    Optional<CartItem> findById(Long cartItemId);

    void delete (Long cartItemId);
}
