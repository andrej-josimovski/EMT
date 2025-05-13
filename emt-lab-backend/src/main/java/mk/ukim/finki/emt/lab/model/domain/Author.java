package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country country;

    public Author() {
    }

    public Author(Country country, String name, String surname) {
        this.country = country;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public Long getCountry() {
        return country.getId();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
