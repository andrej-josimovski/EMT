package mk.ukim.finki.emt.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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
    Country country;

    public Author() {
    }

    public Author(Country country, String name, String surname) {
        this.country = country;
        this.name = name;
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
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
