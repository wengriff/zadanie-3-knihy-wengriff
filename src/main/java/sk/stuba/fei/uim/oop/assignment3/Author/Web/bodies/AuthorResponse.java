package sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies;

import java.util.List;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;

@Getter
public class AuthorResponse {
    
    private final long id;
    private final String name;
    private final String surname;
    private final List<Book> books;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.books = author.getBooks();
    }
}
