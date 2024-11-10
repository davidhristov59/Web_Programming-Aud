package mk.ukim.finki.wp_aud1.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.User;

import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    //filterChain - veriga od filtri - povrzani filtri
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest http_req = (HttpServletRequest) servletRequest; //cast na ServletRequest vo HttpServletRequest
        HttpServletResponse http_resp = (HttpServletResponse) servletResponse; //cast na ServletResponse vo HttpServletResponse

        //proverka dali korisnikot e logiran
        User user = (User) http_req.getSession().getAttribute("user");

        String path = http_req.getServletPath(); //go zema patot na koj e pristapeno

        if (!"/login".equals(path) && !"/register".equals(path) && user == null){
            //ako korisnikot ne e logiran, go prenasocuvame na login stranata
            http_resp.sendRedirect("/servlet/login");
        }
        else {
            //ako korisnikot e logiran, prodolzuvame so sledniot filter vo verigata
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
