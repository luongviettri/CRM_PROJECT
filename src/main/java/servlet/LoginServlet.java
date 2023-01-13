package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Anotation( tên định danh )
 * ký hiệu: @
 */
//!         name: tên của servlet  , urlPatterns--> đường dẫn của servlet
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    //! truyền và nhận tham số bằng method GET
    //todo:----> truyền tham số trên trình duyệt, nhiều tham số thì cách nhau dấu &
    //todo:link : localhost:8080/login?tenthamso=giatri&tenthamso=giatri
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    //! method POST: tham số sẽ dc truyền ngầm và ko giới hạn về mặt ký ự
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}

