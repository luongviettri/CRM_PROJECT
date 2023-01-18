package api;

import com.google.gson.Gson;
import model.RoleModel;
import payload.BasicResponse;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RolesApi", urlPatterns = {"/api/roles", "/api/roles/delete/*", "/api/roles/add"})
public class RolesApi extends HttpServlet {

    //! GET--> dùng khi muốn lấy  dữ liệu
    //! POST--> khi CRUD
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getServletPath(); //! trả ra url mà client đang gọi
        BasicResponse basicResponse = new BasicResponse();

        switch (url) {
            case "/api/roles":
                basicResponse = getAllRoles();
                break;
            case "/api/roles/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse = deleteRoleById(id);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("duong dan ko ton tai");
                break;
        }
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse); //! convert về JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        BasicResponse basicResponse = new BasicResponse();
        String url = req.getServletPath(); //! trả ra url mà client đang gọi

        switch (url) {
            case "/api/roles/add":

                String name = req.getParameter("name");
                String desc = req.getParameter("desc");
                basicResponse = addNewRole(name, desc);

                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("duong dan ko ton tai");
                break;
        }

        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse); //! convert về JSON

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    private BasicResponse addNewRole(String name, String desc) {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("add thanh cong");
        response.setData(roleService.addRole(name, desc));
        return response;
    }

    private BasicResponse deleteRoleById(int id) {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("Xoa thanh cong");
        response.setData(roleService.deleteRoleById(id));
        return response;


//        String pathInfo = req.getPathInfo();
//        String[] splits = pathInfo.split("/");
//        String idRole = splits[1];
//        Gson gson = new Gson();
//        String dataJson = gson.toJson(roleDeleted); //! convert về JSON
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.print(dataJson);
//        printWriter.flush();
//        printWriter.close();


    }

    private BasicResponse getAllRoles() {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        List<RoleModel> list = roleService.GetAllRoles();
        response.setStatusCode(200);
        response.setData(list);
        return response;

    }

}




