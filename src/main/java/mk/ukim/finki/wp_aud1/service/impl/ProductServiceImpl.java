package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.Category;
import mk.ukim.finki.wp_aud1.model.Manufacturer;
import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud1.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud1.repository.InMemoryProductRepository;
import mk.ukim.finki.wp_aud1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository productRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;
    private final InMemoryCategoryRepository categoryRepository;

    public ProductServiceImpl(InMemoryProductRepository productRepository, InMemoryManufacturerRepository manufacturerRepository, InMemoryCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturer_ID) { //cuvam IDs - NE OBJEKTI

        Manufacturer manufacturer = manufacturerRepository.findByID(manufacturer_ID).orElse(null);
        Category category = categoryRepository.findById(categoryID).orElse(null);

        return productRepository.save(name, price, quantity, category, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
