/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.History;
import entity.Person;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.util.GregorianCalendar;
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
import session.UserRolesFacade;


/**
 *
 * @author artur
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/buyProductForm",
    "/buyProduct",
    "/addMoneyToPerson",
    "/addMoney",
})

public class UserServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private PersonFacade personFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;

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
            request.getRequestDispatcher("showLoginForm").forward(request, response); 
            return;   
        }
        
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            request.setAttribute("info", "У вас нет прав! Пожалуйста войдите в систему!");
            request.getRequestDispatcher("showLoginForm").forward(request, response); 
            return;
        }
        boolean isRole = userRolesFacade.isRole("CUSTOMER", user);
        if(!isRole ){
            request.setAttribute("info", "У вас нет прав! Пожалуйста войдите в систему!");
            request.getRequestDispatcher("showLoginForm").forward(request, response); 
            return;       
        }
        String path = request.getServletPath();
        
        switch (path) {               
            case "/buyProductForm":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                List<Product> listBuyProducts = historyFacade.findBuyProduct(user.getPerson());
                request.setAttribute("listBuyProducts", listBuyProducts);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("buyProduct")).forward(request, response);
                break;
                
            case "/buyProduct":
                String productId = request.getParameter("productId");
                String personId = request.getParameter("personId");
                String count = request.getParameter("count");
                String quantity = request.getParameter("quantity");
                
                Product product = productFacade.find(Long.parseLong(productId));
                Person person = personFacade.find(Long.parseLong(personId));
                
                personFacade.edit(person);
                product.setQuantity(product.getQuantity()- 1);
                productFacade.edit(product);
                History history = new History(person, product, new GregorianCalendar().getTime());
                historyFacade.create(history);               
                request.setAttribute("info", "Товар куплен!");
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
                break;
                                   
            case "/addMoneyToPerson":
                List<Person>listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("addMoney")).forward(request, response);
                break;
                
            case "/addMoney":
                String money = request.getParameter("money");
                personId = request.getParameter("personId");
                
                if ("".equals(personId) || personId == null
                        || money == null || "".equals(money)                  
                        ){
                    
                    request.setAttribute("info", "Заполните все поля!");
                    listPersons = personFacade.findAll();
                    request.setAttribute("listPersons", listPersons);
                    request.getRequestDispatcher("/WEB-INF/addMoneyToPerson.jsp").forward(request, response);
                    break;
                }
                person = personFacade.find(Long.parseLong(personId));
                person.setCash(person.getCash()+Integer.parseInt(money));
                personFacade.edit(person);

                request.setAttribute("info", "Кошелек пополнен!");
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
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
