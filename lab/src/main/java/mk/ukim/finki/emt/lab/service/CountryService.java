package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> save(Country country);
    Optional<Country> findById(Long id);
    Optional<Country> update(Long id, Country country);
    void deleteById(Long id);
}
