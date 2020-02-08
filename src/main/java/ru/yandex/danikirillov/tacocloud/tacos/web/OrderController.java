package ru.yandex.danikirillov.tacocloud.tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.yandex.danikirillov.tacocloud.tacos.Order;
import ru.yandex.danikirillov.tacocloud.tacos.data.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "order") Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors())
            return "orderForm";
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }


}
