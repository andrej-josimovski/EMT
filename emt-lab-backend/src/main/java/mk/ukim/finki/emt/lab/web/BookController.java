package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name="Book API", description = "Endpoints for managing books")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public BookController(BookApplicationService bookApplicationService, AuthorApplicationService authorApplicationService) {
        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all books",description = "Retrieves a list of all available books.")
    public List<DisplayBookDto> findAll(){
        return bookApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id){
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Creates a new book based on the given BookDto.")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto){
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing book", description = "Updates a book by ID using BookDto.")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id,
                                                 @RequestBody CreateBookDto book){
        return bookApplicationService.update(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (bookApplicationService.findById(id).isPresent()){
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Rents a book", description = "Rents a book by its ID.")
    public ResponseEntity<DisplayBookDto> rent(@PathVariable Long id){
        return bookApplicationService.rentBook(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/per-author")
    @Operation(summary = "List number of books per author for every author")
    public ResponseEntity<?> findAllNumbersOfBooksPerAuthor(){
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findBooksPerAuthor());
    }

    @GetMapping("/per-author/{id}")
    @Operation(summary = "List number of books per author for a given author")
    public ResponseEntity<?> findNumberOfBooksPerAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findBooksPerAuthor(id));
    }


}
