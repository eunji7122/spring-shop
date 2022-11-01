package portfolio.shop.domain.order;

import lombok.Data;
import portfolio.shop.domain.item.Item;

import javax.persistence.*;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;

    private int count;
}
