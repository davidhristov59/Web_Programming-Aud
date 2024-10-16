package mk.ukim.finki.wp_aud1.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.Category;
import mk.ukim.finki.wp_aud1.service.CategoryService;
import mk.ukim.finki.wp_aud1.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="CategoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //prikaz na celosna lista od kategorii

        PrintWriter printWriter = resp.getWriter();

        String ip_address = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent"); //koj e agentot sto go prakja requestot - browserot ili nesto dr

        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<h3>User-Info</h3>");
        printWriter.println("IP Address: " + ip_address);
        printWriter.println("<br>");
        printWriter.println("User-Agent: " + clientAgent);

        printWriter.println("<h3>Categories</h3>");
        printWriter.println("<ul>");

        categoryService.listCategories()
                .forEach(c -> printWriter.format("<li>%s (%s)</li>", c.getName(), c.getDescription()));

        printWriter.println("</ul>");
        printWriter.println("</body>");
        printWriter.println("</html>");


        printWriter.flush();

        //http://localhost:9090/servlet/category
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dodavanje na nova kategorija
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        categoryService.create(name, description);

        resp.sendRedirect("/servlet/category");
    }
}
