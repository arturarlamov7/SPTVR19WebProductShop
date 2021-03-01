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
@WebServlet(name = "LoginServlet", urlPatterns = {
    "/showLoginForm",
    "/login",
    "/logout",
    "/addPerson",
    "/createPeson",
    "/listProducts"
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB 
    private PersonFacade personFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private HistoryFacade historyFacade;
            

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
            case "/showLoginForm":
                request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response); 
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if (user == null) {
                    request.setAttribute("info", "Не верный пользователь или пароль!");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Не верный пользователь или пароль!");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;                  
                }
                HttpSession session = request.getSession(true); //создаем сессию
                session.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли!");
                request.getRequestDispatcher("/index.jsp").forward(request, response); 
                break;
            case "/logout":
                session = request.getSession(false); //сессия создаваться не будет
                if(session != null) {
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли!");
                request.getRequestDispatcher("/index.jsp").forward(request, response); 
                break;
            case "/addPerson":
                request.getRequestDispatcher("/WEB-INF/addPersonForm.jsp").forward(request, response); 
                break;
                
            case "/createPerson":             
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                password = request.getParameter("password");         
                String cash = request.getParameter("cash");
                
                if ("".equals(name) || name == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(cash) || cash == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null                        
                        ){
                    request.setAttribute("info", "Пожалуйста заполните все поля формы!");
                    request.setAttribute("name", name);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("cash", cash);
                    request.setAttribute("login", login);
                    request.setAttribute("password", password);             
                    
                    request.getRequestDispatcher("/addPersonForm").forward(request, response);
                    break;
                }
                Person person = new Person(name, lastname, phone, Integer.parseInt(cash));
                personFacade.create(person);
                user = new User(login, password, person); //создаем пользоветеля и указываем что это покупатель
                userFacade.create(user);
                request.setAttribute("info","Добавлен новый покупатель" + person.toString());
                request.getRequestDispatcher("/index.jsp").forward(request, response);               
                break;
                
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
        }

    }  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
