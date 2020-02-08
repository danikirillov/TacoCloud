package ru.yandex.danikirillov.tacocloud.tacos.data;

import ru.yandex.danikirillov.tacocloud.tacos.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
