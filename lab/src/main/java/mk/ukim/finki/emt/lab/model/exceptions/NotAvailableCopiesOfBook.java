package mk.ukim.finki.emt.lab.model.exceptions;

public class NotAvailableCopiesOfBook extends RuntimeException {
    public NotAvailableCopiesOfBook() {
        super("There is no available copies of this book");
    }
}
