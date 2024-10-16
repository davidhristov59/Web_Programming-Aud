package mk.ukim.finki.wp_aud1.service;

import mk.ukim.finki.wp_aud1.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

//business-logic

public interface CategoryService {

    //CRUD operations
    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String text);
}
