package mk.ukim.finki.wp_aud1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud1.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //tells Spring that this class is a bean - this class will be instanced when the app starts
public class DataHolder {

    // In-memory data holder

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public void addCategory(String name, String description){
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            return;
        }

        categories.add(new Category(name, description));
    }

    //Initializes and runs at the start of the spring app
    // On application startup, initialize the categories list
    // On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct //annotation that tells Spring to execute this method after the bean is created. Odma ke se izvrsi ovoj metod cim ovaa klasa se instancira
    public void init(){
        //initialize the categories
        addCategory("Java", "Java related books");
        addCategory("Software", "Software related books");

        //initialize the users
        users.add(new User("davidhristov123", "Davidh2003@", "David", "Hristov"));
        users.add(new User("simonadimi123", "Simona123@", "Simona", "Dimitrievska"));

        manufacturers.add(new Manufacturer("Apple", "ASNOM 21"));
        manufacturers.add(new Manufacturer("Tesla", "AVNOJ 32"));
        manufacturers.add(new Manufacturer("Nvidia", "Jane Sandanski"));

        Category category = new Category("Phone", "Phone category");
        categories.add(category);

        Manufacturer manufacturer = new Manufacturer("Apple", "Dizonska");
        manufacturers.add(manufacturer);

        products.add(new Product("Iphone 15", 750.00, 200, category, manufacturer));
        products.add(new Product("Iphone 16", 850.00, 100, category, manufacturer));
        products.add(new Product("Samsung S24", 950.00, 400, category, manufacturer));

    }
}
