package mk.ukim.finki.wp_aud1.web.rest;

import mk.ukim.finki.wp_aud1.model.Manufacturer;
import mk.ukim.finki.wp_aud1.service.ManufacturerService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")//site rest controller odat so /api
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer>  findAll(){ //vrakame podatoci
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}") //ResponseEntity is a class in Spring that represents the entire HTTP response, including the status code, headers, and body. It allows you to control the HTTP response returned by your REST API. In this code, ResponseEntity is used to return either the found Manufacturer with a 200 OK status or a 404 Not Found status if the Manufacturer is not found.
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return manufacturerService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address ){
       return manufacturerService.save(name, address)
               .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
               .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (this.manufacturerService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
