package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.lab.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);
    void deleteById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
}
