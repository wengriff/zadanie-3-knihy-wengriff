package sk.stuba.fei.uim.oop.assignment3.LendingList.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Service.IBookService;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Model.LendingList;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Model.LendingListRepository;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private LendingListRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<LendingList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LendingList create() throws IllegalOperationException, NotFoundException {
        LendingList lendingList = new LendingList();
        return this.repository.save(lendingList);
    }

    @Override
    public LendingList getById(Long id) throws NotFoundException {
        LendingList lendingList = this.repository.findLendingListById(id);
        if(lendingList == null) {
            throw new NotFoundException();
        }
        return lendingList;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        LendingList lendingList = this.getById(id);
        this.repository.delete(lendingList);        
    }

    @Override
    public LendingList addBookToLendingList(BookIdRequest body, Long id) throws NotFoundException {
        LendingList lendingList = this.getById(id);
        Book book = this.bookService.getById(body.getBookId());
        if(lendingList == null) {
            throw new NotFoundException();
        }
        lendingList.getList().add(book);
        return this.repository.save(lendingList);
    }
}
