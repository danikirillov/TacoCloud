package ru.yandex.danikirillov.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.danikirillov.tacocloud.tacos.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByZip(String zip);
}

