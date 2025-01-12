package hiber.model;

import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "series")
    private String series;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car() {

    }

    public Car(String name, String series) {
        this.name = name;
        this.series = series;
    }

}
