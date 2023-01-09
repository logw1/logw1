package Control;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MyDAO;

/**
 *
 * @author fpt shop
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/Login"})
public class LoginControl extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            MyDAO dao = new MyDAO();
            Account a = dao.login(username, password);
            if(a==null){
                request.setAttribute("mess", "wrong user or password");//nếu ng dùng đăng nhập sai sẽ hiện lên mess này
                //lần đầu tiên đăng nhập thì ko có
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                //request.getRequestDispatcher("Home").forward(request, response); cú pháp này là chuyển trang nhưng cần chuyển cả dữ liệu theo
                response.sendRedirect("Home");// cú pháp này thì chỉ chuyển trang
            }
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
