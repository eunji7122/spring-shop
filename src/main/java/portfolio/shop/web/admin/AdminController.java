package portfolio.shop.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import portfolio.shop.domain.item.Item;
import portfolio.shop.domain.item.ItemSearchCond;
import portfolio.shop.domain.item.ItemUpdateDto;
import portfolio.shop.domain.member.Member;
import portfolio.shop.service.aws.AwsS3Service;
import portfolio.shop.service.item.ItemService;
import portfolio.shop.service.member.MemberService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final AwsS3Service awsS3Service;

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

    @GetMapping("/items/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "admin/item/item";
    }

    @GetMapping("/items/new")
    public String addItem() {
        return "admin/item/new";
    }

    @PostMapping("/items/new")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile files) throws IOException {
        String imagePath = awsS3Service.uploadFile(files);

        Item savedItem = itemService.save(item, files.getOriginalFilename(), imagePath);

        redirectAttributes.addAttribute("itemId", savedItem.getDetail());
        return "redirect:/admin/items";
    }

    @GetMapping("items/edit/{itemId}")
    public String editItem(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "admin/item/edit";
    }

    @PostMapping("items/edit/{itemId}")
    public String editItem(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateDto) {
        itemService.update(itemId, updateDto);
        return "redirect:/admin/items/{itemId}";
    }

    @PostMapping("/items/delete")
    public String delete(@RequestParam(value = "checkbox-list", required = false) Long[] checkboxList) {
        if (checkboxList != null) {
            for (Long value : checkboxList)
                itemService.delete(value);
        }
        return "redirect:/admin/items";
    }

    @GetMapping("/members")
    public String members(@ModelAttribute Member member, Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "admin/member/members";
    }
}
