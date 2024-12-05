package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;

@Data
@NoArgsConstructor
@Entity //ako sakame da bide tabela - klasa koja se povrzuva so bazata na pdoatoci - klasa koja perzistira
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //da ne odam so dopolnitelni tabeli tuku da odam so generirano id - do kade stignala bazata na podatoci
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    public Category(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }

}
