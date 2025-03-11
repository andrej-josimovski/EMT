package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto author) {
        if (author.getCountry() != null && author.getName() != null
        && author.getSurname() != null && countryService.findById(author.getCountry()).isPresent()) {
            return Optional.of(
                    authorRepository.save(new Author(countryService.findById(author.getCountry()).get(), author.getSurname(), author.getName()))
            );
        }
        return Optional.empty();
    }


    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto author) {
        return authorRepository.findById(id)
                .map(existingAuthor ->{
                    if (author.getName() != null) {
                        existingAuthor.setName(author.getName());
                    }
                    if (author.getSurname() != null) {
                        existingAuthor.setSurname(author.getSurname());
                    }
                    if (author.getCountry() != null && countryService.findById(author.getCountry()).isPresent()) {
                        existingAuthor.setCountry(countryService.findById(author.getCountry()).get());
                    }
                    return authorRepository.save(existingAuthor);
                });
    }

}
