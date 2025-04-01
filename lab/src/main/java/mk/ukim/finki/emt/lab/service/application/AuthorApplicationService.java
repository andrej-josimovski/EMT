package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(long id);
    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author);
    void deleteById(Long id);
    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto);
}
