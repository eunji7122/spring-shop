package portfolio.shop.repository.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.item.ItemUpdateDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static portfolio.shop.domain.item.QItem.item;

@Repository
@Transactional
public class JpaItemRepository implements ItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setName(updateParam.getName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setDetail(updateParam.getDetail());
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return query.select(item)
                .from(item)
                .where(likeName(cond.getName()))
                .orderBy(item.id.asc())
                .fetch();
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id));
    }

    private BooleanExpression likeName(String name) {
        if (StringUtils.hasText(name)) {
            return item.name.like("%" + name + "%");
        }
        return null;
    }
}
