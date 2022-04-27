package sk.stuba.fei.uim.oop.assignment3.Author.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Model.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Service.IBookService;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private IBookService bookService;
    
    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest body) {
        return this.repository.save(new Author(body));
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
        Author author = this.repository.findAuthorById(id);
        if(author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author update(Long id, AuthorUpdateRequest request) throws NotFoundException {
        Author author = this.getById(id);
        if(author == null) {
            throw new NotFoundException();
        }

        if(request.getName() != null) {
            author.setName(request.getName());
        }

        if(request.getSurname() != null) {
            author.setSurname(request.getSurname());
        }

        return this.repository.save(author);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Author author = this.getById(id);
        for(Book book : this.bookService.getAll()) {
            if(book.getAuthor() == author) {
                this.bookService.delete(book.getId());
            }
        }
        this.repository.delete(author);
    }
}
