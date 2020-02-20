package ru.yandex.danikirillov.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.danikirillov.tacocloud.tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
