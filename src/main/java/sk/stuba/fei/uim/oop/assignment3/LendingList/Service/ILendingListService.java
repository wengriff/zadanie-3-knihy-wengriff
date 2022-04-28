package sk.stuba.fei.uim.oop.assignment3.LendingList.Service;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Model.LendingList;

public interface ILendingListService {
    List<LendingList> getAll();

    LendingList create() throws IllegalOperationException, NotFoundException;

    LendingList getById(Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    LendingList addBookToLendingList(BookIdRequest body, Long id) throws NotFoundException;
}
