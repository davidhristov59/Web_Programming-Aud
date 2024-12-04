package mk.ukim.finki.wp_aud1.repository;

import mk.ukim.finki.wp_aud1.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud1.model.ShoppingCart;
import mk.ukim.finki.wp_aud1.model.enumeration.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id){ //Optional ne vraka null, vraka objekt sto e prazen - ili ima ili nema

        return DataHolder.shoppingCarts
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){

        DataHolder.shoppingCarts.removeIf(s -> s.getUser().getUsername().equals(shoppingCart.getUser().getUsername())); //ako postoi kosnickata

        DataHolder.shoppingCarts.add(shoppingCart);

        return shoppingCart;
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus shoppingCartStatus){
        return DataHolder.shoppingCarts
                .stream()
                .filter(s -> s.getUser().getUsername().equals(username) && s.getShoppingCartStatus().equals(shoppingCartStatus))
                .findFirst();
    }

}
