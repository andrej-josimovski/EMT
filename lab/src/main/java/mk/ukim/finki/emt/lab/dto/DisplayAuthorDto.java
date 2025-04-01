package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(Long id, Long country, String name, String surname){
    public static DisplayAuthorDto from (Author author) {
        return new DisplayAuthorDto(author.getId(), author.getCountry(), author.getName(), author.getSurname());
    }

    public static List<DisplayAuthorDto> from (List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }
}
