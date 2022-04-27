package sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;

@Getter
public class AuthorResponse {
    
    private final Long id;
    private final String name;
    private final String surname;
    private final List<Long> books;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.books = new ArrayList<>();
        if(author.getBooks() != null) {
            this.books.addAll(author.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
        }
    }
}
