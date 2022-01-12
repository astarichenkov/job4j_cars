package ru.job4j.cars.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public AdRepository() {
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    public List<Ad> findByLastDay() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.engine e "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "join fetch c.drivers dr "
                        + "where DATE(ad.created) = (CURRENT_DATE - 1)ORDER BY ad.id").list()
        );
    }

    public List<Ad> findByPeriod(Date start, Date end) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.engine e "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "join fetch c.drivers dr "
                        + "where DATE(ad.created) BETWEEN "
                        + "DATE('" + start + "') AND "
                        + "DATE('" + end + "') ORDER BY ad.created").list()
        );
    }

    public List<Ad> findAllWithPhotos() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.engine e "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "join fetch c.drivers dr "
                        + "where ad.photoId != 0 ORDER BY ad.id").list()
        );
    }

    public List<Ad> findByMark(Mark mark) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                                + "join fetch ad.car c "
                                + "join fetch c.engine e "
                                + "join fetch c.bodyType "
                                + "join fetch c.mark mr "
                                + "join fetch c.model md "
                                + "join fetch c.drivers dr "
                                + "where c.mark = :fMark ORDER BY ad.id ")
                        .setParameter("fMark", mark)
                        .list()
        );
    }

    public Mark findMarkByName(String name) {
        return (Mark) this.tx(
                session -> session.createQuery("from Mark m "
                                + "where m.name = :fName")
                        .setParameter("fName", name)
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
