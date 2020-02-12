package ru.yandex.danikirillov.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.danikirillov.tacocloud.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
