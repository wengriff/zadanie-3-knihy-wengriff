package sk.stuba.fei.uim.oop.assignment3.Author.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Model.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository repository;
    
    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.repository.save(new Author(request));
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        Author author = this.repository.getAuthorById(id);

        if(author == null) {
            throw new NotFoundException();
        }

        return author;
    }

    @Override
    public Author update(long id, AuthorUpdateRequest request) throws NotFoundException {
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
    public void delete(long id) throws NotFoundException {
        Author author = this.repository.findById(id).orElseThrow(() -> new NotFoundException());
        this.repository.delete(author);
    }
}
