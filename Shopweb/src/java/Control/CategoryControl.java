/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
@WebServlet(name = "CategoryControl", urlPatterns = {"/Category"})
public class CategoryControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( 
            PrintWriter out = response.getWriter()) {
            String cateID = request.getParameter("cid");//lấy categoryID
            MyDAO dao = new MyDAO();
            List<Dishes> list = dao.getAllDishByCategory(cateID);
            List<Category> listc = dao.getAllCategory();
            Dishes dish = dao.getNew();
            request.setAttribute("listP", list);//load all dish theo category
            request.setAttribute("listC", listc);//load all category
            request.setAttribute("p", dish);//load new dish
            request.setAttribute("tag", cateID);//sẽ nhận cateID rồi 
            request.getRequestDispatcher("Home.jsp").forward(request, response);
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
