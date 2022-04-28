package sk.stuba.fei.uim.oop.assignment3.LendingList.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingListRepository extends JpaRepository<LendingList, Long> {
    List<LendingList> findAll();
    LendingList findLendingListById(Long id);
}
