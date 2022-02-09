package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.BodyType;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Model;
import ru.job4j.cars.utils.HibernateUtil;

import java.util.List;
import java.util.function.Function;

public class CarRepository {

    private static final class Lazy {
        private static final CarRepository INST = new CarRepository();
    }

    public CarRepository() {
    }

    public static CarRepository instOf() {
        return CarRepository.Lazy.INST;
    }

    public void add(Car car) {
        this.tx(
                session -> session.save(car));
    }

    public Mark findMarkById(int id) {
        return this.tx(
                session -> session.createQuery("from Mark m "
                                + "where m.id = :id", Mark.class)
                        .setParameter("id", id)
                        .uniqueResult());
    }

    public Model findModelById(int id) {
        return this.tx(
                session -> session.createQuery("from Model m "
                                + "where m.id = :id", Model.class)
                        .setParameter("id", id)
                        .uniqueResult());
    }

    public BodyType findBodyById(int id) {
        return this.tx(
                session -> session.createQuery("from BodyType b "
                                + "where b.id = :id", BodyType.class)
                        .setParameter("id", id)
                        .uniqueResult());
    }



    private <T> T tx(final Function<Session, T> command) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        T result = command.apply(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Model> getAllModels() {
        return this.tx(
                session -> session.createQuery("from Model").list()
        );
    }

    public List<Mark> getAllMarks() {
        return this.tx(
                session -> session.createQuery("from Mark ").list()
        );
    }

    public List<BodyType> getAllBodytypes() {
        return this.tx(
                session -> session.createQuery("from BodyType ").list()
        );
    }

    public List<Model> getModelsById(int id) {
        return this.tx(
                session -> session.createQuery("from Model m where m.mark.id = :id")
                        .setParameter("id", id)
                        .list()
        );
    }


    public Mark findMarkByName(String name) {
        return this.tx(
                session -> session.createQuery("from Mark m "
                                + "where m.name = :fName", Mark.class)
                        .setParameter("fName", name)
                        .uniqueResult());
    }
}
