package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.exceptions.NotAvailableCopiesOfBook;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.repository.WishlistRepository;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final BookRepository bookRepository;
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(BookRepository bookRepository, WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (book.getAvailableCopies() <= 0){
            throw new NotAvailableCopiesOfBook();
        }

        Wishlist wishlist= wishlistRepository.findByUser(user)
                .orElseGet(Wishlist::new);

        wishlist.getBooks().add(book);
        return Optional.of(wishlistRepository.save(wishlist));
    }

    @Override
    public List<Book> listAllBooksInWishlist(Long wishlistId) {
        if (wishlistRepository.findById(wishlistId).isEmpty())
            throw new RuntimeException("Wishlist is empty");
        return wishlistRepository.findById(wishlistId).get().getBooks();
    }

    @Override
    public Optional<Wishlist> rentWishlist(String userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Wishlist wishlist=wishlistRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<Book> rentedBooks=new ArrayList<>();
        for (Book book:new ArrayList<>(wishlist.getBooks())){
            if (book.getAvailableCopies() > 0){
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookRepository.save(book);
                rentedBooks.add(book);
            }
        }
        wishlist.getBooks().removeAll(rentedBooks);
        return Optional.of(wishlistRepository.save(wishlist));
    }
}
