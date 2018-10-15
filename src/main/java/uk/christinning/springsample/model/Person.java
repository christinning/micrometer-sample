package uk.christinning.springsample.model;


import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    @Column(name="last_name")
    private String lastName;

    protected Person(){
        // for hibernate
    }

    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
