package portfolio.shop.domain.cart;

import lombok.Data;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.member.Member;

import javax.persistence.*;

@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;

    private int count;
}
