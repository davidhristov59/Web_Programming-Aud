package mk.ukim.finki.wp_aud1.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) { //ApplicationContextListener - listener koj se povikuva pri startuvanje na aplikacijata
        ServletContextListener.super.contextInitialized(sce);
        sce.getServletContext().setAttribute("userViews", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        sce.getServletContext().removeAttribute("userViews");
    }
}
