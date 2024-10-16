package mk.ukim.finki.wp_aud1.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="thymeleaf_category_servlet", urlPatterns = "/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine; //engine za thymeleaf
    private final CategoryService categoryService;

    public ThymeleafCategoryServlet(SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    //method that handles the HTTP GET request
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange); //this context will be used for passing variables in the Thymeleaf template

        //key, value
        context.setVariable("ipAddress", req.getRemoteAddr());  //get the IP address of the client making the request
        context.setVariable("clientAgent", req.getHeader("User-Agent")); //get the User-Agent header from the request
        context.setVariable("categories", this.categoryService.listCategories());

        this.springTemplateEngine.process("categories.html", context, resp.getWriter()); //the output is written to the response writer , which sends rendered HTML to the client
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("name");
        String description = req.getParameter("description");

        categoryService.create(categoryName, description);
        resp.sendRedirect("/servlet/thymeleaf/category");

    }
}
