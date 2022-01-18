//package ru.job4j.cars.model;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Entity
//@Table(name = "basic")
//public class BasicInformation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Model> models = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Mark> marks = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<BodyType> bodies = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<City> cities = new HashSet<>();
//
//    public BasicInformation() {
//    }
//
//    public static BasicInformation of(Set<Model> models, Set<Mark> marks, Set<BodyType> bodies, Set<City> cities) {
//        BasicInformation bs = new BasicInformation();
//        bs.models = models;
//        bs.marks = marks;
//        bs.bodies = bodies;
//        bs.cities = cities;
//        return bs;
//    }
//
//    public void addModel(Model model) {
//        this.models.add(model);
//    }
//
//    public void addMark(Mark mark) {
//        this.marks.add(mark);
//    }
//
//    public void addBodytype(BodyType bodyType) {
//        this.bodies.add(bodyType);
//    }
//
//    public void addCity(City city) {
//        this.cities.add(city);
//    }
//
//    @Override
//    public String toString() {
//        return "BasicInformation{"
//                + "id=" + id
//                + ", models=" + models
//                + ", marks=" + marks
//                + ", bodies=" + bodies
//                + ", cities=" + cities
//                + '}';
//    }
//}
