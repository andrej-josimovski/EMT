package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;

public record CreateAuthorDto(Country country, String name, String surname) {
    public Author toAuthor() {
        return new Author(country,name,surname);
    }
}
