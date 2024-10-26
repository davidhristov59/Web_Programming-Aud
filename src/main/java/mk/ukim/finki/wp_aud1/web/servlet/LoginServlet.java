package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud1.service.AuthenticationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "login-servlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    //dependencies that will be injected by Spring
    private final SpringTemplateEngine templateEngine;
    private final AuthenticationService authenticationService;

    public LoginServlet(SpringTemplateEngine templateEngine, AuthenticationService authenticationService) {
        this.templateEngine = templateEngine;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        templateEngine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        //get the username and password from the request
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //try to login the user - authentication the user
        User user = null;

        //handling the exceptions
        try {
            user = authenticationService.login(username, password);
        } catch (RuntimeException exception) {
            context.setVariable("hasErrors", true);
            context.setVariable("error", exception.getMessage());

            templateEngine.process("login.html", context, resp.getWriter());
        }

        //ako uspesno prosol login-ot treba da se zapocne session za korisnikot
        //informacijata za korisnikot treba da dodademe vo sesija

        if (user != null) {
            req.getSession().setAttribute("user", user); //dodavame vo sesija - uspesno se najavil korisnikot
            //vo session mozeme da koristime celi objekti

            resp.sendRedirect("/servlet/thymeleaf/category"); //redirect to the same page
        }
    }


}
