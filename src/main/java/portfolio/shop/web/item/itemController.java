package portfolio.shop.web.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.shop.service.item.ItemService;

@Controller
@RequiredArgsConstructor
public class itemController {

    private final ItemService itemService;
}
