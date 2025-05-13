package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.dto.WishlistDto;

import java.util.*;

public interface WishlistApplicationService {
    List<DisplayBookDto> listAllBooksInWishlist(Long wishlistId);
    Optional<WishlistDto> addBookToWishlist(String userId, Long bookId);
    Optional<WishlistDto> rentWishlist(String userId);
    Optional<WishlistDto> getWishlist(String username);
}
