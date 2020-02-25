package ru.yandex.danikirillov.tacocloud.tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.yandex.danikirillov.tacocloud.tacos.Order;
import ru.yandex.danikirillov.tacocloud.tacos.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByZip(String zip);

    List<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}

