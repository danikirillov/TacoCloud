package ru.yandex.danikirillov.tacocloud.tacos.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.yandex.danikirillov.tacocloud.tacos.Order;
import ru.yandex.danikirillov.tacocloud.tacos.User;
import ru.yandex.danikirillov.tacocloud.tacos.data.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository orderRepository;
    private final OrderProperties properties;

    public OrderController(OrderRepository orderRepository, OrderProperties properties) {
        this.orderRepository = orderRepository;
        this.properties = properties;
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders", orderRepository.findByUserOrderByCreatedAtDesc(user, PageRequest.of(0, properties.getPageSize())));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "order") Order order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "orderForm";

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/orderComplete";
    }


}
