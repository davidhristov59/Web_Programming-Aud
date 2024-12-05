package mk.ukim.finki.wp_aud1.web.controller;

import mk.ukim.finki.wp_aud1.model.exceptions.InvalidPasswordException;
import mk.ukim.finki.wp_aud1.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthenticationService authenticationService ;

    public RegisterController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping//za da ja vratam taa strana
    public String getRegisterPage(@RequestParam(required = false) String errorMessage, Model model){ //ne e required parametarot

        if(errorMessage != null && !errorMessage.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", errorMessage);

        }

        return "register";
    }

    @PostMapping
    public String register( //@RequestParam stavam pred site bidejki tie se predavaat kako parametri vo ramki na formata
                                            @RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String repeatedPassword,
                                            @RequestParam String name,
                                            @RequestParam String surname){

        try{
            //this.authenticationService.register(username, password, repeatedPassword, name, surname);
            return "redirect:/login";
        }catch(InvalidPasswordException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
