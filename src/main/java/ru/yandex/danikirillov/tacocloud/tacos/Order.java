package ru.yandex.danikirillov.tacocloud.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

// я долго не хотел использовать ломбок, так как не совсем понятно  зачем делать приватными переменные, а потом автоматически генерировать для них геттеры и сеттеры,
// где это требуется, но тк это всего лишь задаие из книги для понимания основных моментов использования Spring 5, то вынужденно пойду на уступки.
@Data
public class Order {
    private long id;
    private Date createdAt;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "city is required")
    private String city;
    @NotBlank(message = "state is required")
    private String state;
    @NotBlank(message = "zip code is required")
    private String zip;
    @CreditCardNumber(message = "Invalid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
}
