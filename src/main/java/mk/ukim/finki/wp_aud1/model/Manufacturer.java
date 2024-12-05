package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "manufacturers") //ime na  tabelata - preimenuvame klasata
public class Manufacturer {

    @Id
    private Long id;
    private String name;

    //preimenuvanje i menuvanje na karakteristiki
    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.id = (long) (Math.random() * 1000 );
        this.name = name;
        this.address = address;
    }
}
