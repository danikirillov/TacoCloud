package ru.yandex.danikirillov.tacocloud.tacos.data;

import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
