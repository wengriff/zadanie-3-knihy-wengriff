package sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies;

import java.util.List;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;

@Data
public class AuthorRequest {
    private String name;
    private String surname;
    private List<Book> books;
}
