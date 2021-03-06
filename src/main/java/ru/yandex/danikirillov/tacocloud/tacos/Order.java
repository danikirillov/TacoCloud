package ru.yandex.danikirillov.tacocloud.tacos;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// я долго не хотел использовать ломбок, так как не совсем понятно  зачем делать приватными переменные, а потом автоматически генерировать для них геттеры и сеттеры,
// где это требуется, но тк это всего лишь задаие из книги для понимания основных моментов использования Spring 5, то вынужденно пойду на уступки.
@Data
@Entity
@Table(name = "Taco_Order")//указывает таблицу, в котороую будут сохранены данные
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdAt;

    @ManyToOne
    private User user;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Street is required.")
    private String street;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Zip code is required.")
    private String zip;

    @CreditCardNumber(message = "Invalid credit card number.")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV.")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist//метод вызовется перед сохранением данных
    public void setNowAsTheDateOfCreation() {
        this.createdAt = new Date();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
