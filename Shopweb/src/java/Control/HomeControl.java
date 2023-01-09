
package Control;

import entity.Category;
import entity.Dishes;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author fpt shop
 */
@WebServlet(name = "HomeControl", urlPatterns = {"/Home"})
public class HomeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //B1:get data from MyDAO
        MyDAO DAO = new MyDAO();
        List<Dishes> list = DAO.getAllDish();
        List<Category> listc = DAO.getAllCategory();
        Dishes dish = DAO.getNew();
        //B2:set data to home.jsp
        request.setAttribute("listP", list);//load all dish
        request.setAttribute("listC", list); //load all category
        request.setAttribute("p", dish);//load new dish
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
