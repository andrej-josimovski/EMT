package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> save(AuthorDto author);
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id, AuthorDto author);
    void deleteById(Long id);
}
