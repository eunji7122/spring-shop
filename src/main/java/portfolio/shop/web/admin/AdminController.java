package portfolio.shop.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.member.Member;
import portfolio.shop.service.item.ItemService;
import portfolio.shop.service.member.MemberService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping()
    public String admin() {
        return "admin/item/items";
    }

    @GetMapping("/items")
    public String items(@ModelAttribute("itemSearch") ItemSearchCond itemSearch, Model model) {
        List<Item> items = itemService.findItems(itemSearch);
        model.addAttribute("items", items);
        return "admin/item/items";
    }

    @GetMapping("/members")
    public String members(@ModelAttribute Member member, Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "admin/member/members";
    }
}
