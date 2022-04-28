package sk.stuba.fei.uim.oop.assignment3.LendingList.Web.bodies;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.LendingList.Model.LendingList;

@Data
public class LendingListResponse {
    private Long id;
    private List<BookResponse> lendingList;
    private boolean lended;

    public LendingListResponse(LendingList lendingList) {
        this.id = lendingList.getId();
        this.lendingList = new ArrayList<>();
        if(lendingList.getList() != null) {
            this.lendingList = lendingList.getList().stream().map(BookResponse::new).collect(Collectors.toList());
        }
        this.lended = lendingList.isLended();
    }
}
