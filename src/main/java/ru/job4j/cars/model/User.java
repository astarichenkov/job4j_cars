package ru.job4j.cars.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String password;
    private String phone;

    public User() {
    }

    public static User of(String name, String phone, String password) {
        User user = new User();
        user.name = name;
        user.phone = phone;
        user.password = password;
        return user;
    }
}
