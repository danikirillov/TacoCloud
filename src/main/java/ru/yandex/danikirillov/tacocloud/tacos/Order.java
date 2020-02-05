package ru.yandex.danikirillov.tacocloud.tacos;

import lombok.Data;

// я долго не хотел использовать ломбок, так как не совсем понятно  зачем делать приватными переменные, а потом автоматически генерировать для них геттеры и сеттеры,
// где это требуется, но тк это всего лишь задаие из книги для понимания основных моментов использования Spring 5, то вынужденно пойду на уступки.
@Data
public class Order {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
