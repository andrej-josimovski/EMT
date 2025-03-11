package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    String name;
    String surname;
    private Long country;

    public AuthorDto(Long country, String name, String surname) {
        this.country = country;
        this.name = name;
        this.surname = surname;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
