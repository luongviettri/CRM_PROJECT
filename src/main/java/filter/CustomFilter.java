package filter;

import service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebFilter(urlPatterns = {"/roles",})
//! urlPatterns: những link nào khi client gọi sẽ kích hoạt filter
public class CustomFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; //! kiến thức ép kiểu cha con OOP
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            boolean isLogin = false;

            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    isLogin = true;
                    break;
                } else {
                    isLogin = false;
                }
            }

            if (isLogin) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }


    }
}
