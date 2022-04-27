package sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Long author;
    private int pages;
    private int amount;
    private int lendCount;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor().getId();
        this.pages = book.getPages();
        this.amount = book.getAmount();
        this.lendCount = book.getLendCount();
    }
}
