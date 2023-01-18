package servlet;

import config.MysqlConfig;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        //! Khởi tạo cookie
//        Cookie cookie = new Cookie("username", URLEncoder.encode("Nguyen Van A", "UTF-8"));
//        cookie.setMaxAge(8 * 60 * 60); //! set thời gian hết hạn của cookie
//        resp.addCookie(cookie); //! yêu cầu trình duyệt phía client tạo cookie


//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            String name = cookie.getName();
//            if (name.equals("username")) {
//                System.out.println(" Giá trị " + URLDecoder.decode(cookie.getValue(), "UTF-8"));
//            }
//        }
        //! sự khác nhau giữa cookie và session: cookie lưu trên client còn session lưu trên server--> giá trị của session bảo mật hơn cookie và giá trị của session lưu trên thanh RAM của server ----> càng nhiều client xài thì server càng dễ hết RAM

        //! lấy session từ trên server 
        HttpSession session = req.getSession(); //todo: lấy session trên server  dựa vào session ID từ client ( dc lưu mặc định trong cookie)
        session.setAttribute("username", "Trần Văn A");
        //todo: set thời gian hết hạn của session ( đơn vị second )
        session.setMaxInactiveInterval(8 * 60 * 60);

        //! lấy giá trị session ra
        String data = (String) session.getAttribute("username");

        System.out.println("Giá trị session:" + data);

        //---------------------- ghi nhớ-------------------------
        //! prepare statement: gồm excuteQuery và excuteUpdate
        //todo: excuteQuery: dành cho tất cả  Select
        //todo: excuteUpdate: CRUD operation
        //--------------------------end-----------------------------

        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT * FROM users"; //! câu này copy từ workbench qua luôn
        try {
            //! chuẩn bị câu truy vấn cho prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            //! kết quả câu truy vấn sẽ lưu vào resultSet
            ResultSet resultSet = statement.executeQuery();
            //! duyệt giá trị trong resultSet và lấy ra thông tin cột mong muốn
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                int roleId = resultSet.getInt("role_id");
//                System.out.println("Email: " + email);
//                System.out.println("Role Id: " + roleId);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi thực thi câu truy vấn login" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    //! method POST: tham số sẽ dc truyền ngầm và ko giới hạn về mặt ký tự
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginService loginService = new LoginService();
        boolean isSuccess = loginService.checkLogin(email, password);
//        System.out.println("Kiem tra" + isSuccess);

        if (isSuccess) {
            //! Khởi tạo cookie
            Cookie cookie = new Cookie("username", email);
            cookie.setMaxAge(8 * 60 * 60); //! set thời gian hết hạn của cookie
            resp.addCookie(cookie); //! yêu cầu trình duyệt phía client tạo cookie
            //! dùng get contextPath để nếu deploy thì nó tự ấy context path
            resp.sendRedirect(req.getContextPath() + "/roles");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

