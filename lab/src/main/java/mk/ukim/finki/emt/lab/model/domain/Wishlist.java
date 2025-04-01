package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wishlist {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    public Wishlist() {}

    public Wishlist(User user){
        this.user = user;
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
