package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    public List<DisplayAuthorDto> findAll() {
        return this.authorApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Finds a manufacturer by its ID.")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return this.authorApplicationService.findById(id)
                .map(a-> ResponseEntity.ok().body(a))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new author", description = "Creates a new author based on the given AuthorDto.")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto) {
        return this.authorApplicationService.save(createAuthorDto)
                .map(a-> ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing author", description = "Updates a manufacturer by ID.")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
        return this.authorApplicationService.update(id, createAuthorDto)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
