package ru.yandex.danikirillov.tacocloud.tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.danikirillov.tacocloud.tacos.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order) {
        log.info("Order submitted " + order);
        return "redirect:/";
    }
}
