package Control;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MyDAO;

@WebServlet(name = "SignupControl", urlPatterns = {"/Signup"})
public class SignupControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String re_pass = request.getParameter("repass");
            if(!pass.equals(re_pass)){//repass ko giống pass thì chuyển qua trang login
                response.sendRedirect("Login.jsp");
            }else{
            //sign up tại đây
                MyDAO dao = new MyDAO();
                Account a = dao.checkAccountExist(user);
                if(a == null){
                    //được signup
                    dao.signup(user, pass);
                    response.sendRedirect("Home");//signup thành công sẽ chuyển về trang home
                }else{
                    // nếu user đã có thì quay về trang login
                    response.sendRedirect("Login.jsp");
                }
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
