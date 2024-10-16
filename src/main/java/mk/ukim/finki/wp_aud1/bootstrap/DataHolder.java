package mk.ukim.finki.wp_aud1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud1.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //tells Spring that this class is a bean - this class will be instanced when the app starts
public class DataHolder {

    // In-memory data holder

    public static List<Category> categories = new ArrayList<>();

    public void addCategory(String name, String description){
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            return;
        }

        categories.add(new Category(name, description));
    }

    // On application startup, initialize the categories list
    // On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct //annotation that tells Spring to execute this method after the bean is created. Odma ke se izvrsi ovoj metod cim ovaa klasa se instancira
    public void init(){
        //initialize the categories
        addCategory("Java", "Java related books");
        addCategory("Software", "Software related books");
    }
}
