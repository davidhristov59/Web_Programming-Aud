package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*
@Service
@Controller
@RestController
 */

@WebServlet(name="Hello", urlPatterns = "/hello") // annotation that tells the servlet container to register this servlet
public class HelloWorldServlet extends HttpServlet {

    // /hello -> na koja pateka da se povika servletot
    //localhost:9090/hello - na browser

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        if (name == null){
            name = "X";
        }

        if (surname == null){
            surname = "Y";
        }

        //write the response
        printWriter.format("<html><head></head><body><h1>Hello %s %s</h1></body></html>", name, surname);

        //http://localhost:9090/hello?name=David&surname=Hristov so query string zadavam parametri

        printWriter.flush();
    }
}
