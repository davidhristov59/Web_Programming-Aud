package mk.ukim.finki.wp_aud1.service;

import mk.ukim.finki.wp_aud1.model.User;
import org.springframework.stereotype.Service;


public interface AuthenticationService { //Avtentikacija na samite korisnici

    public User login(String username, String password);
    public User register(String username, String password, String repeatPassword, String name, String surname);

}
