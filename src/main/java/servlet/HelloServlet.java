package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    // GET: client gõ đường dẫn trên browser
    // POST: gọi ngầm bằng code hoặc thẻ form

    /**
     * phương thức doGet lắng nghe method GET
     * @param req : request từ user
     * @param resp : response trả về user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //! Phương thức GET sẽ thực thi code ở đây

        System.out.println("Hello Phương thức get");


    }
}
