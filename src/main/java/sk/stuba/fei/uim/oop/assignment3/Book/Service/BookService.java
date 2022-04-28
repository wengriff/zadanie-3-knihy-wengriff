package sk.stuba.fei.uim.oop.assignment3.Book.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Service.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private IAuthorService authorService;
    
    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws IllegalOperationException, NotFoundException {
        Author author = this.authorService.getById(request.getAuthor());
        if(author == null) {
            throw new IllegalOperationException();
        }
        Book book = new Book(request, author);
        author.getBooks().add(book);
        return this.repository.save(book);
    }

    @Override
    public Book getById(Long id) throws NotFoundException {
        Book book = this.repository.findBookById(id);
        if(book == null) {
            // throw new NotFoundException();
        }
        return book;
    }

    @Override
    public Book update(Long id, BookUpdateRequest request) throws NotFoundException {
        Book book = this.getById(id);
        if(request.getName() != null) {
            book.setName(request.getName());
        }
        if(request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        if(request.getAuthor() != 0) {
            book.setAuthor(this.authorService.getById(request.getAuthor()));
        }
        if(request.getPages() != 0) {
            book.setPages(request.getPages());
        }
        return this.repository.save(book);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Book book = this.getById(id);
        this.authorService.getById(book.getAuthor().getId()).getBooks().remove(book);
        this.repository.delete(book);
    }

    @Override
    public int getAmount(Long id) throws NotFoundException {
        Book book = this.getById(id);
        if(book == null) {
            throw new NotFoundException();
        }
        return book.getAmount();
    }

    @Override
    public int addAmount(Long id, int amount) throws NotFoundException {
        Book book = this.getById(id);
        if(book == null) {
            throw new NotFoundException();
        }
        book.setAmount(book.getAmount() + amount);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public int getLendCount(Long id) throws NotFoundException {
        Book book = this.getById(id);
        if(book == null) {
            throw new NotFoundException();
        }
        return book.getLendCount();
    }
}
