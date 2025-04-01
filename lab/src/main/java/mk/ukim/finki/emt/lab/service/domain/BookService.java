package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> update(Long id, Book book);
    Optional<Book> save(Book book);
    void deleteById(Long id);
    Optional<Book> rentBook(Long id);
}
