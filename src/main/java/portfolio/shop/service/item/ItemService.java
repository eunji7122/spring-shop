package portfolio.shop.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.item.ItemUpdateDto;
import portfolio.shop.repository.item.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(Item item, String imageName, String imagePath) {
        item.setImageName(imageName);
        item.setImagePath(imagePath);
        return itemRepository.save(item);
    }

    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemRepository.update(itemId, updateParam);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findItems(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }

    public void delete(Long id) {
        itemRepository.delete(id);
    }
}
