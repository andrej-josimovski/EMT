package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Country;

public record CreateCountryDto(String continent, String name) {
    public Country toCountry() {
        return new Country(continent, name);
    }
}
