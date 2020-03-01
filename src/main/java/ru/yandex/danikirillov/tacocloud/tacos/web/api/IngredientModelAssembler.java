package ru.yandex.danikirillov.tacocloud.tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientRepresentationModel> {

    public IngredientModelAssembler() {
        super(, IngredientRepresentationModel.class);
    }

    @Override
    protected IngredientRepresentationModel instantiateModel(Ingredient ingredient) {
        return new IngredientRepresentationModel(ingredient);
    }

    @Override
    public IngredientRepresentationModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}
