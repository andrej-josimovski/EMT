package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.dto.WishlistDto;
import mk.ukim.finki.emt.lab.service.application.WishlistApplicationService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String userId, Long bookId) {
        return wishlistService.addBookToWishlist(userId, bookId)
                .map(WishlistDto::from);
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishlist(Long wishlistId) {
        return DisplayBookDto.from(wishlistService.listAllBooksInWishlist(wishlistId));
    }

    @Override
    public Optional<WishlistDto> rentWishlist(String userId) {
        return wishlistService.rentWishlist(userId)
                .map(WishlistDto::from);
    }
}
