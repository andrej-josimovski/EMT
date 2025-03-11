package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rentBook(Long id) {
        return bookRepository.findById(id)
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    return bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (book.getName() != null){
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory() != null){
                        existingBook.setCategory(Category.valueOf((book.getCategory())));
                    }
                    if (book.getAuthor() != null && authorService.findById(book.getAuthor()).isPresent()){
                        existingBook.setAuthor(authorService.findById(book.getAuthor()).get());
                    }
                    if (book.getAvailableCopies() != null){
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> save(BookDto book) {
        if (book.getAvailableCopies() != null && authorService.findById(book.getAuthor()).isPresent() && book.getCategory() != null && book.getName() != null && book.getAuthor() != null){
            Category category = Category.valueOf((book.getCategory()));
            return Optional.of(bookRepository.save(new Book(authorService.findById(book.getAuthor()).get(), book.getAvailableCopies(), category, book.getName())));
        }
        return Optional.empty();
    }
}
