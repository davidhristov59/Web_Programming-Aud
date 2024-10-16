package mk.ukim.finki.wp_aud1.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.Category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="CategoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private List<Category> categories = null;

    public void addCategory(String name, String description){

        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            return;
        }

        this.categories.add(new Category(name, description));
    }

    // initialize the categories
    @Override
    public void init() throws ServletException { //inicijalizacija na listata
        super.init();

        this.categories = new ArrayList<>();

        addCategory("Java", "Java related books");
        addCategory("Software", "Software related books");

        //this.categories.add(new Category("Software", "Software related books"));
        //this.categories.add(new Category("Programming", "Programming related books"));
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
        this.categories.forEach(category -> {
            printWriter.println("<li>" + category.getName() + " - " + category.getDescription() + "</li>");
        });
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
        addCategory(name,description);

        resp.sendRedirect("/servlet/category");
    }
}
