package ru.yandex.danikirillov.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;
import ru.yandex.danikirillov.tacocloud.tacos.Ingredient.Type;
import ru.yandex.danikirillov.tacocloud.tacos.Order;
import ru.yandex.danikirillov.tacocloud.tacos.Taco;
import ru.yandex.danikirillov.tacocloud.tacos.data.IngredientRepository;
import ru.yandex.danikirillov.tacocloud.tacos.data.TacoRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
//утверждает хранение аттрибута order во всех request ах этой сессии.(сохранение заказа в бд будет происходить в OrderController)
public class
DesignTacoController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private void fillListIngredients(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        for (Type type : Ingredient.Type.values())
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    @GetMapping
    public String showDesignForm(Model model) {
        fillListIngredients(model);
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, Model model, @ModelAttribute Order order) {
        if (errors.hasErrors())
            return showDesignForm(model);

        order.addDesign(tacoRepository.save(design));

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
