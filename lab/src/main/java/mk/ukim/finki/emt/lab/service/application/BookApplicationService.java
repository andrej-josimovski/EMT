package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto);
    Optional<DisplayBookDto> save(CreateBookDto bookDto);
    Optional<DisplayBookDto> findById(Long id);
    List<DisplayBookDto> findAll();
    void deleteById(Long id);
    Optional<DisplayBookDto> rentBook(Long id);
}
