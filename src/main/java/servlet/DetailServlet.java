package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//! handle detail route
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //! sẽ hiển thị nội dung trong file login_test.jsp
        req.getRequestDispatcher("detail_test.jsp").forward(req, resp);
    }
}
