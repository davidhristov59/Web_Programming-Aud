package mk.ukim.finki.wp_aud1.service;

import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.model.ShoppingCart;
import mk.ukim.finki.wp_aud1.model.enumeration.ShoppingCartStatus;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

        List<Product> listProductsInShoppingCart(Long cartId);
        ShoppingCart getActiveShoppingCart(String username);
        ShoppingCart addProductToShoppingCart(String username, Long productId);

}
