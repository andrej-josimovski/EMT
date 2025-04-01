package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> rentBook(Long id) {
        return bookService.rentBook(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.author());
        Category category=createBookDto.category();
        return bookService.update(id,
                createBookDto.toBook(
                        category,
                        author.orElse(null)
                )
        ).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Optional<Author> author=authorService.findById(createBookDto.author());
        Category category=createBookDto.category();
        if (author.isPresent() && category!=null) {
            return bookService.save(createBookDto.toBook(category,author.get()))
                    .map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
}
