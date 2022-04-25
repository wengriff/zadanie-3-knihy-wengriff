package sk.stuba.fei.uim.oop.assignment3.Author.Service;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.Author.Model.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

public interface IAuthorService {

    List<Author> getAll();

    Author create(AuthorRequest body);

    Author getById(long id) throws NotFoundException;

    Author update(long id, AuthorUpdateRequest request) throws NotFoundException;

    void delete(long id) throws NotFoundException;

}
