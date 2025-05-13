package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;

import java.util.*;

public interface WishlistService {
    List<Book> listAllBooksInWishlist(Long wishlistId);
    Optional<Wishlist> addBookToWishlist(String userId, Long BookId);
    Optional<Wishlist> rentWishlist(String userId);
    Optional<Wishlist> getWishlist(String username);

}
