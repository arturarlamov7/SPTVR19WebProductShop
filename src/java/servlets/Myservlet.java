/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author artur
 */
@WebServlet(name = "Myservlet", urlPatterns = {
    "/addProduct",
    "/createProduct",
    "",

        

})




public class Myservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        
        switch (path) {
            case "/addProduct":
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response); //forward означает что после выполнения
                break;
                
            case "/createProduct":
                String name = request.getParameter("name");
                String count = request.getParameter("count");
                String quantity = request.getParameter("quantity");
                
                if ("".equals(name) || name == null
                        || "".equals(count) || count == null
                        || "".equals(quantity) || quantity == null
                        ){
                    request.setAttribute("info", "Пожалуйста заполните все поля формы!");
                    request.setAttribute("name", name);
                    request.setAttribute("count", count);
                    request.setAttribute("quantity", quantity);
                    
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break;
                }
                Product product = new Product( name, Integer.parseInt(count), Integer.parseInt(quantity));
                
                request.setAttribute("info","Добавлен товар" + product.toString());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
        }
        
        
        
        
        
        
        
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
