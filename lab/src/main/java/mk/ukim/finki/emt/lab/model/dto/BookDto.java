package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;

@Data
public class BookDto {
    private String name;
    private String category;
    private Long author;
    private Integer availableCopies;

    BookDto(){}

    public BookDto(Long author, Integer availableCopies, String category, String name) {
        this.author = author;
        this.availableCopies = availableCopies;
        this.category = category;
        this.name = name;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
