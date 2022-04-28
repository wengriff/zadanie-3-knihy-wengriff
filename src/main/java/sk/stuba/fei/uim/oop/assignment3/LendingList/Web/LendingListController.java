package sk.stuba.fei.uim.oop.assignment3.LendingList.Web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Service.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Web.bodies.LendingListResponse;

@RestController
@RequestMapping("/list")
public class LendingListController {
    
    @Autowired
    private ILendingListService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LendingListResponse> getAllLendingLists() {
        return this.service.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LendingListResponse> addLendingList() throws IllegalOperationException, NotFoundException {
        return new ResponseEntity<>(new LendingListResponse(this.service.create()), HttpStatus.CREATED); 
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse getLendingListById(@PathVariable("id") Long lendingListId) throws NotFoundException {
        return new LendingListResponse(this.service.getById(lendingListId));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteLendingList(@PathVariable("id") Long lendingListId) throws NotFoundException {
        this.service.delete(lendingListId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse addBookToLendingList(@PathVariable("id") Long lendingListId, @RequestBody BookIdRequest body) throws IllegalOperationException, NotFoundException {
        return new LendingListResponse(this.service.addBookToLendingList(body, lendingListId));
    }
}
