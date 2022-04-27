package sk.stuba.fei.uim.oop.assignment3.Book.Web.bodies;

import lombok.Data;

@Data
public class BookRequest {
    private String name;
    private String description;
    private Long author;
    private int pages;
    private int amount;
    private int lendCount;
}
