package sk.stuba.fei.uim.oop.assignment3.Book.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository repository;
    
    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) {
        return this.repository.save(new Book(request));
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        Book book = this.repository.findBookById(id);

        if(book == null) {
            throw new NotFoundException();
        }

        return book;
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        Book book = this.getById(id);

        if(book == null) {
            throw new NotFoundException();
        }

        if(request.getName() != null) {
            book.setName(request.getName());
        }

        if(request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }

        if(request.getAuthor() != 0) {
            book.setAuthor(request.getAuthor());
        }

        if(request.getPages() != 0) {
            book.setPages(request.getPages());
        }

        return this.repository.save(book);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        Book book = this.getById(id);

        if(book == null) {
            throw new NotFoundException();
        }

        return book.getAmount();
    }

    @Override
    public void addAmount(long id, int amount) throws NotFoundException {
        Book book = this.getById(id);

        if(book == null) {
            throw new NotFoundException();
        }

        book.setAmount(book.getAmount() + amount);
        this.repository.save(book);
    }
}
