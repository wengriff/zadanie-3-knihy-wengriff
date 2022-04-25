package sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies;

import lombok.Data;

@Data
public class BookUpdateRequest {
    private String name;
    private String description;
    private long author;
    private int pages;
}
