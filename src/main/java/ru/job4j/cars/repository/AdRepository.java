package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;
import ru.job4j.cars.utils.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public AdRepository() {
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    public void changeStatus(long id, long userId) {
        this.tx(
                session -> session.createQuery("UPDATE Ad ad set ad.isSold = true WHERE ad.id = :id AND ad.author.id = :userId")
                        .setParameter("id", id)
                        .setParameter("userId", userId)
                        .executeUpdate());
    }

    public void add(Ad ad) {
        this.tx(
                session -> session.save(ad));
    }

    public List<Ad> findAll() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "WHERE ad.isSold != true "
                        + "ORDER BY ad.id ").list()
        );
    }

    public List<Ad> findAdById(Long id) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                                + "join fetch ad.car c "
                                + "join fetch c.bodyType "
                                + "join fetch c.mark mr "
                                + "join fetch c.model md "
                                + "WHERE ad.id = :id "
                                + "ORDER BY ad.id ")
                        .setParameter("id", id)
                        .list()
        );
    }

    public List<Ad> findAdsByUserId(Long id) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                                + "join fetch ad.car c "
                                + "join fetch c.bodyType "
                                + "join fetch c.mark mr "
                                + "join fetch c.model md "
                                + "WHERE ad.author.id = :userId "
                                + "ORDER BY ad.id ")
                        .setParameter("userId", id)
                        .list()
        );
    }

    public List<Ad> findByLastDay() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "where DATE(ad.created) = (CURRENT_DATE - 1)ORDER BY ad.id").list()
        );
    }

    @SuppressWarnings("unchecked")
    public List<Ad> findByPeriod(Date start, Date end) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "where DATE(ad.created) BETWEEN "
                        + "DATE('" + start + "') AND "
                        + "DATE('" + end + "') ORDER BY ad.created").list()
        );
    }

    public List<Ad> findAllWithPhotos() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "where ad.photoId != 0 ORDER BY ad.id").list()
        );
    }

    public List<Ad> findByMark(Mark mark) {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                                + "join fetch ad.car c "
                                + "join fetch c.bodyType "
                                + "join fetch c.mark mr "
                                + "join fetch c.model md "
                                + "where c.mark = :fMark ORDER BY ad.id ")
                        .setParameter("fMark", mark)
                        .list()
        );
    }

    @SuppressWarnings("unchecked")
    public List<City> getAllCities() {
        return this.tx(
                session -> session.createQuery("from City").list()
        );
    }

    public City findCityById(Long id) {
        return this.tx(
                session -> session.createQuery("from City c "
                                + "where c.id = :id", City.class)
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
}
