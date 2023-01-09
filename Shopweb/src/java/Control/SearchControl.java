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
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class SearchControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");//để search có thể hiểu cả tiếng việt
        try ( PrintWriter out = response.getWriter()) {
            String txtSearch = request.getParameter("txt");
            MyDAO dao = new MyDAO();
            List<Dishes> list = dao.searchByName(txtSearch);//search theo name
            Dishes newdish = dao.getNew();//lấy món mới nhất
            List<Category> listc = dao.getAllCategory();//lấy tất cả category
            
            request.setAttribute("listP", list);
            request.setAttribute("listC", listc);
            request.setAttribute("p", newdish);
            request.setAttribute("txts", txtSearch);
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
