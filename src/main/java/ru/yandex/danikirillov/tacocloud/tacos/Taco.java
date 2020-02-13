package ru.yandex.danikirillov.tacocloud.tacos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdAt;
    @NotNull(message = "name required")
    @Size(min = 5, message = "Sorry:((( but you must create more than 5 letters name")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)//cause taco can have many Ingredient objects, and Ingredient can be part of many tacos
    @NotNull(message = "You must choose st least 1 ingredient.")
    @Size(min = 1, message = "You must choose st least 1 ingredient.")
    private List<Ingredient> ingredients;

    @PrePersist//метод вызовется перед сохранением данных
    public void setNowAsTheDateOfCreation() {
        this.createdAt = new Date();
    }
}
