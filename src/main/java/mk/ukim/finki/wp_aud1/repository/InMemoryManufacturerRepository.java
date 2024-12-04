package mk.ukim.finki.wp_aud1.repository;

import mk.ukim.finki.wp_aud1.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud1.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findByID(Long id){
        return DataHolder.manufacturers.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public Optional<Manufacturer> findByName(String name){
        return DataHolder.manufacturers.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst();
    }


}
