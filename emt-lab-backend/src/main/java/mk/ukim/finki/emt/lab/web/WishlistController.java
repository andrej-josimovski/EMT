package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.lab.dto.WishlistDto;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.exceptions.NotAvailableCopiesOfBook;
import mk.ukim.finki.emt.lab.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API", description = "Endpoints for managing the wishlist")
public class WishlistController {
    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @PostMapping("/add-book/{id}")
    @Operation(summary = "Add book to wishlist", description = "Adds a book to the wishlist for the logged in user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200", description = "Book added to wishlist successfully"
    ), @ApiResponse(
            responseCode = "400", description = "Invalid request"
    ), @ApiResponse(responseCode = "404", description = "Book not found")})
    public ResponseEntity<?> addBookToWishlist(@PathVariable Long id, Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            Optional<WishlistDto> updatedWishlist= wishlistApplicationService.addBookToWishlist(user.getUsername(), id);
            return updatedWishlist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all books from wishlist", description = "Retrieves a list of all books from the wishlist.")
    @GetMapping("/books/{id}")
    public ResponseEntity<?> listAllBooksInWishlist(@PathVariable Long id){
        return ResponseEntity.ok(wishlistApplicationService.listAllBooksInWishlist(id));
    }

    @Operation(
            summary = "Get wish list",
            description = "Retrieves the wish list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Wish list retrieved succesfully"
            ), @ApiResponse(responseCode = "404", description = "Wishlist not found")}
    )
    @GetMapping
    public ResponseEntity<WishlistDto> getWishList(HttpServletRequest request){
        String username= request.getRemoteUser();
        return wishlistApplicationService.getWishlist(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rent all books from wish list", description = "Rents all books and clears the wish list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books rented successfully"),
            @ApiResponse(responseCode = "400", description = "No available copies for one or more books"),
            @ApiResponse(responseCode = "404", description = "Wish list not found")
    })
    @PostMapping("/rent")
    public ResponseEntity<?> rentWishlistBooks(Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            wishlistApplicationService.rentWishlist(user.getUsername());
            return ResponseEntity.ok("All books rent successfully");
        }catch (NotAvailableCopiesOfBook e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

}
