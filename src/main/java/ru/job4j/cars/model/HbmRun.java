package ru.job4j.cars.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();


            Mark markOne = Mark.of("Audi");
            Mark markTwo = Mark.of("Lada");

            Model modelOne = Model.of("A3");
            Model modelTwo = Model.of("A4");
            Model modelThree = Model.of("Vesta");
            Model modelFour = Model.of("Granta");

            modelOne.setMark(markOne);
            modelTwo.setMark(markOne);
            modelThree.setMark(markTwo);
            modelFour.setMark(markTwo);


            BodyType bodyOne = BodyType.of("Седан");
            BodyType bodyTwo = BodyType.of("Хетчбек");
            BodyType bodyThree = BodyType.of("Универсал");
            BodyType bodyFour = BodyType.of("Внедорожник");

            Car carTwo = Car.of(markTwo, modelTwo, bodyTwo, 2011, 98, 272000, false);

            Car carOne = Car.of(markOne, modelOne, bodyOne, 2020, 150, 76000, true);

            City cityOne = new City("Нижний Новгород");
            City cityTwo = new City("Москва");

            User userOne = User.of("Anton", "+79506024452", "123");
            User userTwo = User.of("Ivan", "+79990005555", "123");
            session.save(userOne);
            session.save(userTwo);
            Ad adOne = Ad.of(carOne, "Продается ауди", cityOne, userTwo, 1000000);
            Ad adTwo = Ad.of(carTwo, "Продается Лада Калина", cityTwo, userOne, 200000);


            session.save(adOne);
            session.save(adTwo);

            session.save(cityOne);
            session.save(cityTwo);

            session.save(markOne);
            session.save(markTwo);
            session.save(modelOne);
            session.save(modelTwo);
            session.save(modelThree);
            session.save(modelFour);

            session.save(carOne);
            session.save(carTwo);

            session.save(bodyOne);
            session.save(bodyTwo);
            session.save(bodyThree);
            session.save(bodyFour);


            session.getTransaction().commit();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}