package ru.yandex.danikirillov.tacocloud.tacos.web.api;

import org.springframework.hateoas.RepresentationModel;
import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;

public class IngredientRepresentationModel extends RepresentationModel<IngredientRepresentationModel> {
    private final String name;
    private final Ingredient.Type type;

    public IngredientRepresentationModel(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }

    public String getName() {
        return name;
    }

    public Ingredient.Type getType() {
        return type;
    }
}
