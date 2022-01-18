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

            Engine engineOne = Engine.of(150);
            Engine engineTwo = Engine.of(249);

            Driver driverOne = Driver.of("Oleg");
            Driver driverTwo = Driver.of("Eduard");
            Driver driverThree = Driver.of("Ilya");

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

            Car carTwo = Car.of(markTwo, modelTwo, bodyTwo);
            carTwo.setEngine(engineTwo);
            carTwo.addDriver(driverOne);
            carTwo.addDriver(driverThree);

            Car carOne = Car.of(markOne, modelOne, bodyOne);
            carOne.setEngine(engineOne);
            carOne.addDriver(driverOne);
            carOne.addDriver(driverTwo);

            City city = new City("MSK");
            User user = User.of("Ivan", "+7951234455", "");
            Ad ad = Ad.of(carTwo, "Продается ауди", city, user, 1000000);


            session.save(ad);
            session.save(user);
            session.save(city);

            session.save(markOne);
            session.save(markTwo);
            session.save(modelOne);
            session.save(modelTwo);
            session.save(modelThree);
            session.save(modelFour);

            session.save(carOne);
            session.save(carTwo);

            session.save(driverOne);
            session.save(driverTwo);

            session.save(engineOne);
            session.save(engineTwo);

            session.save(bodyOne);
            session.save(bodyTwo);
            session.save(bodyThree);
            session.save(bodyFour);

            AdRepository adRepository = new AdRepository();
            adRepository.add(ad);

            session.getTransaction().commit();


            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}