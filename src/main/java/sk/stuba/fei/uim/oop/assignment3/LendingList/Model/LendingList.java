package sk.stuba.fei.uim.oop.assignment3.LendingList.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Model.Book;

@Entity
@Data
@AllArgsConstructor
public class LendingList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Book> list;

    private boolean lended;

    public LendingList() {
        this.list = new ArrayList<>();
    }
}
