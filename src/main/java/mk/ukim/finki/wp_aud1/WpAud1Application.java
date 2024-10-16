package mk.ukim.finki.wp_aud1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //look for the servlets in the classpath - work with servlets, not with controllers
@SpringBootApplication // annotation that tells Spring Boot that this is the main class of the application
public class WpAud1Application {

    public static void main(String[] args) {
        SpringApplication.run(WpAud1Application.class, args);
    }

}
