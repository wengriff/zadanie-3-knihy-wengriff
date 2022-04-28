package sk.stuba.fei.uim.oop.assignment3.Book.Service;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

public interface IBookService {
    List<Book> getAll();

    Book create(BookRequest request) throws IllegalOperationException, NotFoundException;

    Book getById(Long id) throws NotFoundException;

    Book update(Long id, BookUpdateRequest request) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    int getAmount(Long id) throws NotFoundException;

    int addAmount(Long id, int amount) throws NotFoundException;

    int getLendCount(Long id) throws NotFoundException;
    
}
