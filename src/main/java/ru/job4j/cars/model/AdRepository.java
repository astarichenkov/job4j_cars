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

    public void add(Ad ad) {
        this.tx(
                session -> session.createQuery("insert into Ad (description, created, isSold, photoId, price, author, car, city) "
                                + "select ad.description, ad.created, ad.isSold, ad.photoId, ad.price, ad.author, ad.car, ad.city "
                                + "from Ad ad where ad.id = :fId")
                        .setParameter("fId", ad.getId())
                        .executeUpdate());
    }

    public List<Ad> findAll() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.engine e "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "join fetch c.drivers dr").list()
        );
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

    public List<City> getAllCities() {
        return this.tx(
                session -> session.createQuery("from City").list()
        );
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

//    public BasicInformation getBasicInformation() {
//        return (BasicInformation) this.tx(
//                session -> session.createQuery("select distinct bs from BasicInformation bs "
//                        + "join fetch bs.models mo "
//                        + "join fetch bs.marks ma "
//                        + "join fetch bs.bodies bo "
//                        + "join fetch bs.cities ci "
//                ).uniqueResult()
//        );
//    }

    public List<Model> getModels() {
        return this.tx(
                session -> session.createQuery("from Model").list()
        );
    }

    public List<Mark> getMarks() {
        return this.tx(
                session -> session.createQuery("from Mark").list()
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
