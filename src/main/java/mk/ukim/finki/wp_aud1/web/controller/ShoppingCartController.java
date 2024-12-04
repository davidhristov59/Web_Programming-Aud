package mk.ukim.finki.wp_aud1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.model.ShoppingCart;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.service.ProductService;
import mk.ukim.finki.wp_aud1.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping
    public String  getShoppingCartPage(@RequestParam (required = false) String error, HttpSession httpSession, Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = (User) httpSession.getAttribute("user");

        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());

        List<Product> products = shoppingCartService.listProductsInShoppingCart(shoppingCart.getId());

        model.addAttribute("products", products);

        return "shopping-cart";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id,HttpSession httpSession){

        try {
            User user = (User) httpSession.getAttribute("user");
            shoppingCartService.addProductToShoppingCart(user.getUsername(), id);

            return "redirect:/shopping-cart";

        } catch(RuntimeException exception){
            return "redirect:/shopping-cart?error=" + exception.getMessage();

        }
    }

}
