package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('USER')")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> save(@RequestBody BookDto book){
        return bookService.save(book)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto book){
        return bookService.update(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (bookService.findById(id).isPresent()){
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> rent(@PathVariable Long id){
        return bookService.rentBook(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


}
