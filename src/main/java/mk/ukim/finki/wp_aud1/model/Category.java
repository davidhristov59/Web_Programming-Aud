package mk.ukim.finki.wp_aud1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
public class Category {
    private String name;
    private String description;
    private Long id;

    public Category(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }
}
