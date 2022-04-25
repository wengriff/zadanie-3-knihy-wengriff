package sk.stuba.fei.uim.oop.assignment3.Book.Service;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

public interface IBookService {

    List<Book> getAll();

    Book create(BookRequest request);

    Book getById(long id) throws NotFoundException;

    Book update(long id, BookUpdateRequest request) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    int getAmount(long id) throws NotFoundException;

    void addAmount(long id, int amount) throws NotFoundException;
    
}
