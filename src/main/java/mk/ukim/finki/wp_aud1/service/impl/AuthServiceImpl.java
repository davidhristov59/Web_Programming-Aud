package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidPasswordException;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud1.model.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.wp_aud1.repository.InMemoryUserRepository;
import mk.ukim.finki.wp_aud1.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthenticationService {

    private final InMemoryUserRepository repository;


    //ovoj metod se povikuva na start na samiot SpringBoot
    public AuthServiceImpl(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User login(String username, String password) {

        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }

        return repository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {

        if (username == null && username.isEmpty() && password == null && password.isEmpty() && repeatPassword == null && repeatPassword.isEmpty() && name == null && name.isEmpty() && surname == null && surname.isEmpty()){
            throw new InvalidArgumentException();
        }

        if (!password.equals(repeatPassword)){ //ako ne se sovpagaat passwordite
            throw new InvalidPasswordException();
        }

        if (repository.findByUsername(username).isPresent()){ //ako postoi korisnik so toa korisnicko ime
            throw new UserAlreadyExistsException();
        }

        return repository.saveOrUpdate(new User(username, password, name, surname));
    }
}
