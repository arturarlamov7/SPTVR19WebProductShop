/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Person;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.HistoryFacade;
import session.PersonFacade;
import session.ProductFacade;
import session.UserFacade;

/**
 *
 * @author artur
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addProduct",
    "/createProduct",
    "/editProductForm",
    "/editProduct",
    "/listPersons",
})
public class ManagerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private PersonFacade personFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;
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
        //Аутенфикация
        HttpSession session = request.getSession(false);
        if(session == null) {
            request.setAttribute("info", "У вас нет прав! Пожалуйста войдите в систему!");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response); 
            return;   
        }
        
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            request.setAttribute("info", "У вас нет прав! Пожалуйста войдите в систему!");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response); 
            return;
        }
        
        if (!"manager".equals(user.getLogin())) {
            request.setAttribute("info", "У вас нет прав! Пожалуйста войдите в систему!");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response); 
            return;           
        }
        String path = request.getServletPath();
        
        switch (path) {
            case "/addProduct":
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response); 
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
                Product product = new Product( name, Integer.parseInt(count) , Integer.parseInt(quantity));
                productFacade.create(product);
                request.setAttribute("info","Добавлен товар" + product.toString());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "/editProductForm":
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/editProductForm.jsp").forward(request, response);
                break;
                
            case "/editProduct": 
                productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                name = request.getParameter("name");
                count = request.getParameter("count");
                quantity = request.getParameter("quantity");
                if("".equals(name) || name == null 
                        || "".equals(count) || count == null
                        || "".equals(quantity) || quantity == null){
                    request.setAttribute("info","Поля не должны быть пустыми");
                    request.setAttribute("productId", product.getId());
                    request.getRequestDispatcher("/editProductForm").forward(request, response);
                    break; 
                }
                product.setName(name);
                product.setCount(Integer.parseInt(count));
                product.setQuantity(Integer.parseInt(quantity));
                productFacade.edit(product);
                request.setAttribute("info","Товар отредактирован");
                request.setAttribute("productId", product.getId());
                request.getRequestDispatcher("/editProductForm").forward(request, response);         
                break;  
                              
            case "/listPersons":
                List<Person> listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/listPersons.jsp").forward(request, response);
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
