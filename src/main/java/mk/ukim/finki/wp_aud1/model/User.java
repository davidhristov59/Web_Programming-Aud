package mk.ukim.finki.wp_aud1.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }


}
