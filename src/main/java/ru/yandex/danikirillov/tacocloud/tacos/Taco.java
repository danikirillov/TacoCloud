package ru.yandex.danikirillov.tacocloud.tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private long id;

    private Date createdAt;

    @NotNull(message = "name required")
    @Size(min = 5, message = "Sorry:((( but you must create more than 5 letters name")
    private String name;

    @Size(min = 1, message = "You must choose st least 1 ingredient.")
    private List<Ingredient> ingredients;

}
