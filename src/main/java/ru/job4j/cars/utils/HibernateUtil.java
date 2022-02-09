package ru.job4j.cars.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil  implements AutoCloseable {
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY)
            .buildMetadata().buildSessionFactory();

     public static Session openSession() {
        return SF.openSession();
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(REGISTRY);
    }
}