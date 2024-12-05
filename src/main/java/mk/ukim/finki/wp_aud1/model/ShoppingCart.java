package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp_aud1.model.enumeration.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToOne //eden user moze da ima poveke shopping carts i naoopaku
    private User user;

    @ManyToMany
    private List<Product> products; //produkti vo kosnickata

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus shoppingCartStatus;

    public ShoppingCart(){
        this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.shoppingCartStatus = ShoppingCartStatus.CREATED;
    }
}
