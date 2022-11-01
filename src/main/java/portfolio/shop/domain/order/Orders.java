package portfolio.shop.domain.order;

import lombok.Data;
import portfolio.shop.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String name;

    private String price;

    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    public Orders() {
    }

    public Orders(LocalDate date, String name, String price, OrderState state, Member member) {
        this.date = date;
        this.name = name;
        this.price = price;
        this.state = state;
        this.member = member;
    }
}
