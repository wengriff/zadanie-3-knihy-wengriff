package sk.stuba.fei.uim.oop.assignment3.Author.Web.bodies;

import lombok.Data;

@Data
public class AuthorUpdateRequest {
    private String name;
    private String surname;
}
