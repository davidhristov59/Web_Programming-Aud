package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.model.ShoppingCart;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.wp_aud1.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wp_aud1.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp_aud1.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp_aud1.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp_aud1.repository.InMemoryShoppingCartRepository;
import mk.ukim.finki.wp_aud1.repository.InMemoryUserRepository;
import mk.ukim.finki.wp_aud1.service.ProductService;
import mk.ukim.finki.wp_aud1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository inMemoryShoppingCartRepository;
    private final InMemoryUserRepository inMemoryUserRepository;
    private final ProductService productService;

    public ShoppingCartImpl(InMemoryShoppingCartRepository inMemoryShoppingCartRepository, InMemoryUserRepository inMemoryUserRepository, ProductService productService) {
        this.inMemoryShoppingCartRepository = inMemoryShoppingCartRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listProductsInShoppingCart(Long cartId) {

        if(inMemoryShoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }

        return inMemoryShoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return inMemoryShoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> { //ako neam aktivna kosnicka, kreiraj nova
                    User user = this.inMemoryUserRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

                    ShoppingCart shoppingCart = new ShoppingCart(user);

                    return inMemoryShoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {

        ShoppingCart shoppingCart =getActiveShoppingCart(username);

        Product product = this.productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        if(!shoppingCart.getProducts().stream().filter(p -> p.getId().equals(productId)).collect(Collectors.toList()).isEmpty()){
            throw new ProductAlreadyInShoppingCartException(productId , username);
        }

        shoppingCart.getProducts().add(product);

        return this.inMemoryShoppingCartRepository.save(shoppingCart);
    }
}
