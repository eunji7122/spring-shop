package portfolio.shop.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String admin() {
        return "admin/item/items";
    }

    @GetMapping("/items")
    public String items() {
        return "admin/item/items";
    }

    @GetMapping("/members")
    public String members() {
        return "admin/member/members";
    }
}
