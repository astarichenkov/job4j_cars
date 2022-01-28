package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "car")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private int mileage;
    private int power;
    private boolean isBroken;

    @ManyToOne
    @JoinColumn(name = "bodytype_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;

    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    public Car() {
    }

    public static Car of(Mark mark, Model model, BodyType bodyType, int year, int power, int mileage, boolean isBroken) {
        Car car = new Car();
        car.mark = mark;
        car.model = model;
        car.bodyType = bodyType;
        car.year = year;
        car.power = power;
        car.mileage = mileage;
        car.isBroken = isBroken;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", year=" + year
                + ", mileage=" + mileage
                + ", isBroken=" + isBroken
                + ", power =" + power
                + ", bodyType=" + bodyType
                + ", model=" + model
                + ", mark=" + mark
                + '}';
    }
}