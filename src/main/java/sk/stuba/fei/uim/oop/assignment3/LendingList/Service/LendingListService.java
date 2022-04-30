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
    public LendingList addBookToLendingList(Long id, BookIdRequest body) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getById(id);
        if(lendingList == null) {
            throw new NotFoundException();
        }
        Book book = this.bookService.getById(body.getId());
        if(lendingList.getList().contains(book) || lendingList.isLended()) {
            throw new IllegalOperationException();
        }
        lendingList.getList().add(book);
        return this.repository.save(lendingList);
    }

    @Override
    public LendingList removeBookFromLendingList(Long id, BookIdRequest body) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getById(id);
        Book book = this.bookService.getById(body.getId());
        if(!lendingList.getList().contains(book)) {
            throw new IllegalOperationException();
        }
        lendingList.getList().remove(book);
        return this.repository.save(lendingList);
    }

    @Override
    public void lendList(Long id) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getById(id);
        if(lendingList.isLended()) {
            throw new IllegalOperationException();
        }
        lendingList.setLended(true);
        for(Book book : lendingList.getList()) {
            book.setLendCount(book.getLendCount() + 1);
        }
        this.repository.save(lendingList);
    }
}
