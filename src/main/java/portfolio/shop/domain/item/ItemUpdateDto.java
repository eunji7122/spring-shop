package portfolio.shop.domain.item;

import lombok.Data;

@Data
public class ItemUpdateDto {

    private String name;

    private int price;

    private String detail;

    private String imageName;

    private String imagePath;
}
