package ru.job4j.cars.repository;

import org.hibernate.Session;
import ru.job4j.cars.model.User;
import ru.job4j.cars.utils.HibernateUtil;

import java.util.function.Function;

public class UserRepository {

    private static final class Lazy {
        private static final UserRepository INST = new UserRepository();
    }

    public UserRepository() {
    }

    public static UserRepository instOf() {
        return UserRepository.Lazy.INST;
    }

    public void add(User user) {
        this.tx(
                session -> session.save(user));
    }

    public User findByName(String name) {
        return this.tx(
                session -> session.createQuery("from User u "
                                + "where u.name = :name", User.class)
                        .setParameter("name", name)
                        .uniqueResult());
    }

    public User findByPhoneAndPassword(String phone, String password) {
        return this.tx(
                session -> session.createQuery("from User u "
                                + "where u.password = :password AND u.phone = :phone", User.class)
                        .setParameter("phone", phone)
                        .setParameter("password", password)
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
}
