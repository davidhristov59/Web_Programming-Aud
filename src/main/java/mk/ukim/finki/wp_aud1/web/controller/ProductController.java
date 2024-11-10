package mk.ukim.finki.wp_aud1.web.controller;

import mk.ukim.finki.wp_aud1.model.Category;
import mk.ukim.finki.wp_aud1.model.Manufacturer;
import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.service.CategoryService;
import mk.ukim.finki.wp_aud1.service.ManufacturerService;
import mk.ukim.finki.wp_aud1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    //da gi zemam soodvetitnite produkti - treba da go injektiram servisot
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String errorMessage, Model model){ //the Model parameter is used to pass data from the controller to the view. When you add attributes to the Model, they become available in the view (e.g., a Thymeleaf template) for rendering.

        if(errorMessage != null && !errorMessage.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", errorMessage);
        }

        List<Product> products = productService.findAll(); //listata na produkti ja zimam od servisot
        model.addAttribute("products", products); // Add the list of products to the model

         return "products"; //ke vratam View koj sto se vika products
    }

    // /products/67 (ova e path variable) - id-to za sekoj produkt
    // /products?id=8 (query string) - so RequestParam
    @DeleteMapping("/delete/{id}") //na koj mapping  - variabilen del e i oznacuvame koe id e
    public String deleteProduct(@PathVariable  Long id){ //id-to mozam da go zemam kako query parametar ili kako path variable

        this.productService.deleteById(id);

        return "redirect:/products"; //otkako ke izbrisam ke redirectiram na istata strana
    }

    //imam 2 getMapping, mora da specificiram na koj url

    @GetMapping("/add-form") //ke imam kopce addProduct i koga ke se povika toa kopce ke se povika ovaa akcija
    public String addProductPage(Product product, Model model){

        List<Category> categories = this.categoryService.listCategories();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);

        return "add-product";
    }

    //PostMapping so cel da se izvrsi i da se zacuva ovaa forma
    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer){

        productService.save(name, price, quantity, category, manufacturer); //da go zacuvam proizvodot so ovie parametri

        return "redirect:/products";
    }

    //Edit Product
    @GetMapping("/editForm/{id}")
    public String editProduct(@PathVariable  Long id, Model model){

        if(productService.findById(id).isPresent()){

            Product product = productService.findById(id).get();

            List<Category> categories = categoryService.listCategories();
            List<Manufacturer> manufacturers = manufacturerService.findAll();

            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("product", product);

            return "add-product";
        }
            return "redirect:/products?error=ProductNotFound";
    }




}
