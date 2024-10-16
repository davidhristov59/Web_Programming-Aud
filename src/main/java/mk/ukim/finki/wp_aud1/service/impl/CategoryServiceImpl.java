package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.Category;
import mk.ukim.finki.wp_aud1.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //ke startuva na pocetok koga ke se startuva spring boot aplikacijata
public class CategoryServiceImpl implements CategoryService {

    private static final InMemoryCategoryRepository repository = new InMemoryCategoryRepository();

    @Override
    public Category create(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return repository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return repository.save(category);
    }

    @Override
    public void delete(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }

        repository.delete(name);
    }

    @Override
    public List<Category> listCategories() {
        return repository.findAll();
    }

    @Override
    public List<Category> searchCategories(String name) {
        return repository.search(name);
    }
}
