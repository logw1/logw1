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

@WebServlet(name = "DetailControl", urlPatterns = {"/Detail"})
public class DetailControl extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( 
            PrintWriter out = response.getWriter()) {
            String id = request.getParameter("pid");
            MyDAO dao = new MyDAO();
            Dishes dish = dao.getDishDetail(id);//vào detail của món
            Dishes newdish = dao.getNew();//lấy món mới nhất
            List<Category> listc = dao.getAllCategory();//lấy tất cả category
            
            request.setAttribute("detail", dish);
            request.setAttribute("listC", listc);
            request.setAttribute("p", newdish);
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
