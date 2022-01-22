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

    public void add1(Ad ad) {
        this.tx(
                session -> session.createQuery("insert into Ad (description, created, isSold, photoId, price, author, car, city) "
                                + "select ad.description, ad.created, ad.isSold, ad.photoId, ad.price, ad.author, ad.car, ad.city "
                                + "from Ad ad where ad.id = :fId")
                        .setParameter("fId", ad.getId())
                        .executeUpdate());
    }

    public void add(Ad ad) {
        this.tx(
                session -> session.save(ad));
    }

    public void add(Car car) {
        this.tx(
                session -> session.save(car));
    }

    public void add(City city) {
        this.tx(
                session -> session.save(city));
    }

    public void add(User user) {
        this.tx(
                session -> session.save(user));
    }

    public void add(Model model) {
        this.tx(
                session -> session.save(model));
    }

    public void add(Mark mark) {
        this.tx(
                session -> session.save(mark));
    }

    public void add(BodyType bodyType) {
        this.tx(
                session -> session.save(bodyType));
    }

    public User findUserByName(String name) {
        return this.tx(
                session -> session.createQuery("from User u "
                                + "where u.name = :name", User.class)
                        .setParameter("name", name)
                        .uniqueResult());
    }

    public User findUserByPhoneAndPassword(String phone, String password) {
        return this.tx(
                session -> session.createQuery("from User u "
                                + "where u.password = :password AND u.phone = :phone", User.class)
                        .setParameter("phone", phone)
                        .setParameter("password", password)
                        .uniqueResult());
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

    public City findCityById(Long id) {
        return this.tx(
                session -> session.createQuery("from City c "
                                + "where c.id = :id", City.class)
                        .setParameter("id", id)
                        .uniqueResult());
    }


    public List<Ad> findAll() {
        return this.tx(
                session -> session.createQuery("select distinct ad from Ad ad "
                        + "join fetch ad.car c "
                        + "join fetch c.bodyType "
                        + "join fetch c.mark mr "
                        + "join fetch c.model md "
                        + "ORDER BY ad.id ").list()
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


    public List<Mark> getMarks() {
        return this.tx(
                session -> session.createQuery("from Mark")
                        .list()
        );
    }

    public List<Model> getModels(int id) {
        return this.tx(
                session -> session.createQuery("from Model m where m.mark.id = :id")
                        .setParameter("id", id)
                        .list()
        );
    }

    public List<BodyType> getBodies() {
        return this.tx(
                session -> session.createQuery("from BodyType")
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
