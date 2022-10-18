package portfolio.shop.domain.item;

import lombok.Data;

@Data
public class ItemSearchCond {

    private String name;

    public ItemSearchCond() {
    }

    public ItemSearchCond(String name) {
        this.name = name;
    }
}
