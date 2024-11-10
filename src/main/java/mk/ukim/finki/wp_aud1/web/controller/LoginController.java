package mk.ukim.finki.wp_aud1.web.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud1.service.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/login")
public class LoginController {

        private final AuthenticationService authService;

        public LoginController(AuthenticationService authService) {
                this.authService = authService;
        }

        @GetMapping //DA BIDE GET BARANJE
        public String getLoginPage(){
                return "login"; // Return the name of the Thymeleaf template that will be used to render the login page
        }

        @PostMapping
        public String login(HttpServletRequest req, Model model){

                User user;

                String username = req.getParameter("username");
                String password = req.getParameter("password");

                try{

                        user = authService.login(username, password);

                        req.getSession().setAttribute("user", user);
                        //ako nekoj uspesno se logiral
                        return "redirect:/home";
                }catch(InvalidUserCredentialsException e){
                        model.addAttribute("hasError", true);
                        model.addAttribute("error", e.getMessage());
                        return "login";
                }

        }

}
