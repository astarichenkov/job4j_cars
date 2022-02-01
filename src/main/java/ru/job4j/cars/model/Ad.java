package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private int price;
    private String description;

    @Column(name = "photo_id")
    private int  photoId;

    @Column(name = "is_sold")
    private boolean isSold;

    @ManyToOne
    private City city;

    @ManyToOne
    private User author;

    @ManyToOne
    private Car car;

    public static Ad of(Car car, String description, City city, User author, int price) {
        Ad ad = new Ad();
        ad.setCar(car);
        ad.setDescription(description);
        ad.setCity(city);
        ad.setAuthor(author);
        ad.setPrice(price);
        ad.setCreated(new Date(System.currentTimeMillis()));
        return ad;
    }
}
