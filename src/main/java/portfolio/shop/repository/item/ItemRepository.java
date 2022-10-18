package portfolio.shop.repository.item;

import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.item.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

    void delete(Long id);
}
