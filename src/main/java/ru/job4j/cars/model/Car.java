package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}