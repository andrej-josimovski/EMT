package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {

        Country usa = countryRepository.save(new Country("North America", "USA"));
        Country uk = countryRepository.save(new Country("Europe", "UK"));
        Country france = countryRepository.save(new Country("Europe", "France"));
        Country germany = countryRepository.save(new Country("Europe", "Germany"));

        Author author1 = authorRepository.save(new Author(usa, "Mark", "Twain"));
        Author author2 = authorRepository.save(new Author(uk, "George", "Orwell"));
        Author author3 = authorRepository.save(new Author(france, "Victor", "Hugo"));
        Author author4 = authorRepository.save(new Author(germany, "Johann", "Goethe"));

        bookRepository.save(new Book(author1, 10, Category.CLASSICS, "Adventures of Huckleberry Finn"));
        bookRepository.save(new Book(author2, 15, Category.THRILLER, "1984"));
        bookRepository.save(new Book(author3, 5, Category.HISTORY, "Les Misérables"));
        bookRepository.save(new Book(author4, 8, Category.DRAMA, "Faust"));
        bookRepository.save(new Book(author1, 12, Category.NOVEL, "The Adventures of Tom Sawyer"));
        bookRepository.save(new Book(author2, 7, Category.BIOGRAPHY, "Down and Out in Paris and London"));
        bookRepository.save(new Book(author3, 20, Category.FANTASY, "The Hunchback of Notre-Dame"));
        bookRepository.save(new Book(author4, 9, Category.CLASSICS, "The Sorrows of Young Werther"));


    }
}
