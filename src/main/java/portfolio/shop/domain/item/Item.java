package portfolio.shop.domain.item;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private String detail;

    private String imageName;

    private String imagePath;

    public Item() {
    }

    public Item(String name, int price, String detail) {
        this.name = name;
        this.price = price;
        this.detail = detail;
    }
}
