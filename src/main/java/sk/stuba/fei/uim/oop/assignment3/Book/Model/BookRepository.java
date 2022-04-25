package sk.stuba.fei.uim.oop.assignment3.Book.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    Book getBookById(Long id);
}

