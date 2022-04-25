package sk.stuba.fei.uim.oop.assignment3.Author.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getAuthorById(Long id);
}