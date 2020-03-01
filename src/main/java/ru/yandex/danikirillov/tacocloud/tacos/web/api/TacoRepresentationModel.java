package ru.yandex.danikirillov.tacocloud.tacos.web.api;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;
import ru.yandex.danikirillov.tacocloud.tacos.Taco;

import java.util.Date;
import java.util.List;

//The generic self-typing is necessary to let RepresentationModel.add(…) return instances of itself.
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoRepresentationModel extends RepresentationModel<TacoRepresentationModel> {
    private final String name;
    private final Date createdAt;
    private final List<IngredientRepresentationModel> ingredients;

    private static final IngredientModelAssembler ingredientModelAssembler = new IngredientModelAssembler();

    public TacoRepresentationModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientModelAssembler.toCollectionModel(taco.getIngredients());
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<IngredientRepresentationModel> getIngredients() {
        return ingredients;
    }
}
