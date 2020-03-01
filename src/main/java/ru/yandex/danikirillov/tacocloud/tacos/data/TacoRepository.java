package ru.yandex.danikirillov.tacocloud.tacos.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import ru.yandex.danikirillov.tacocloud.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Slice<Taco> findAll(PageRequest pageRequest);
}