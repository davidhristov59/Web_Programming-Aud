package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {

    @Id
    private Long id; //nekoj key vo bazata na podatoci -
    private String name; //unikatno ime
    private Double price;
    private Integer quantity;

    @ManyToOne //poveke produkti vo 1 kategorija
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToMany (mappedBy = "products")
    private List<ShoppingCart> shoppingCarts;

    public Product(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.id =(long) ( Math.random() * 1000 );
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Product() {

    }
}
