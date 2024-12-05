package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
//@NoArgsConstructor
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String password;
    private String name;
    private String surname;

    //imeto na svojstvoto
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) //1 user kon poveke shopping carts
    private List<ShoppingCart> shoppingCarts;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User() {

    }

}
