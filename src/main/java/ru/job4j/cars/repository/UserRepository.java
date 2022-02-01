package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;

import java.util.function.Function;

public class UserRepository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

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
        Session session = sf.openSession();
        session.beginTransaction();
        T result = command.apply(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
