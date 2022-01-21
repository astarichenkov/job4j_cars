package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
//@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @OneToMany(mappedBy = "mark")
//    @JoinColumn(name = "marks_id")
//    private List<Model> models = new ArrayList<>();
//
//    public void addModel(Model model) {
//        this.models.add(model);
//    }

    public Mark() {
    }

    public static Mark of(String name) {
        Mark mark = new Mark();
        mark.name = name;
        return mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mark{"
                + "id=" + id
                + ", name='" + name
                + '}';
    }
}

