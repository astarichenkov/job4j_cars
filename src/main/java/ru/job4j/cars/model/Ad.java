package ru.job4j.cars.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private LocalDateTime created;

    private int price;
    private String description;
    private int  photoId;
    private boolean isSold;

    @ManyToOne
    private City city;

    @ManyToOne
    private User author;

    @ManyToOne
    private Car car;

    public Ad() {
    }

    public static Ad of(Car car, String description, City city, User author, int price) {
        Ad ad = new Ad();
        ad.setCar(car);
        ad.setDescription(description);
        ad.setCity(city);
        ad.setAuthor(author);
        ad.setPrice(price);
        ad.setCreated(LocalDateTime.now());
        return ad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Ad{"
                + "id=" + id
                + ", created=" + created
                + ", price=" + price
                + ", description=" + description
                + ", photoId=" + photoId
                + ", isSold=" + isSold
                + ", city=" + city
                + ", author=" + author
                + ", car=" + car
                + '}';
    }
}
