package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.service.AuthenticationService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;

@WebServlet(name="logout-servlet", urlPatterns = "/servlet/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate(); //se brise sesijata na korisnikot i se odjavuva

        resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
