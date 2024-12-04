package mk.ukim.finki.wp_aud1.model;

import lombok.Data;
import mk.ukim.finki.wp_aud1.model.enumeration.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Product> products; //produkti vo kosnickata
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
