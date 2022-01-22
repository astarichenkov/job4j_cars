package ru.job4j.cars.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", password='" + password + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
