package ru.yandex.danikirillov.tacocloud.tacos.data;

import ru.yandex.danikirillov.tacocloud.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
