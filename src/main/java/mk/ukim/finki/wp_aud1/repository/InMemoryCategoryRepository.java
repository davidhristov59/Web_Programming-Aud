package mk.ukim.finki.wp_aud1.repository;

import mk.ukim.finki.wp_aud1.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud1.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Repository for handling the in-memory storage of categories
@Repository
public class InMemoryCategoryRepository {

    //CRUD

    public List<Category> findAll(){

        return DataHolder.categories;
    }

    public Category save(Category category){

        // If the category already exists, remove it and add the new one
        DataHolder.categories.removeIf(r -> r.getName().equals(category.getName()));

        DataHolder.categories.add(category);

        return category;
    }

    public List<Category> search(String text){

        return DataHolder.categories.stream()
                .filter(r -> r.getName().contains(text) || r.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public void delete(String name){
        DataHolder.categories.removeIf(r -> r.getName().equals(name));
    }

}
