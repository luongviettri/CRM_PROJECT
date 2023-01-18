package api;

import com.google.gson.Gson;
import model.RoleModel;
import model.UserModel;
import payload.BasicResponse;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersApi", urlPatterns = {"/api/users", "/api/user/*"})
public class UserApi extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicResponse basicResponse = new BasicResponse();

        int userID = Integer.parseInt(req.getParameter("userID"));

        //! start xử lí database
        UserService userService = new UserService();

        basicResponse.setStatusCode(200);
        basicResponse.setMessage("Xóa thành công rồi nà");
        basicResponse.setData(userService.deleteUserById(userID));
        //! end xử lí database

        //! gửi data  về cho client
        responseHandler(basicResponse, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getServletPath(); //! trả ra url mà client đang gọi

        BasicResponse basicResponse = new BasicResponse();
        UserService userService = new UserService();
        System.out.println("vo toi day");
        switch (url) {
            case "/api/users":
                List<UserModel> allUsers = userService.getAllUsers();
                basicResponse.setStatusCode(200);
                basicResponse.setData(allUsers);
                break;
            case "/api/user":
                int userID = Integer.parseInt(req.getParameter("userID"));
                UserModel userDetail = userService.getUserByID(userID);
                basicResponse.setStatusCode(200);
                basicResponse.setData(userDetail);

                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("duong dan ko ton tai");
                break;
        }
        //! start xử lí database


        //! end xử lí database

        //! gửi data  về cho client
        responseHandler(basicResponse, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("vo day");
        BasicResponse basicResponse = new BasicResponse();
        //! start xử lí database

        UserService userService = new UserService();

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int phoneNumber = Integer.parseInt(req.getParameter("phone"));
        String country = req.getParameter("country");

        basicResponse.setStatusCode(200);
        basicResponse.setMessage("add thanh cong");
        basicResponse.setData(userService.createUser(fullName, email, password, phoneNumber, country));

        //! end xử lí database

        //! gửi data  về cho client
        responseHandler(basicResponse, resp);
    }

    public void responseHandler(BasicResponse basicResponse, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse); //! convert về JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }
}
