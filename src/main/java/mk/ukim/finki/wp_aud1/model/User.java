package mk.ukim.finki.wp_aud1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;

    }
