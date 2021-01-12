/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.History;
import entity.Person;
import entity.Product;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.HistoryFacade;
import session.PersonFacade;
import session.ProductFacade;


/**
 *
 * @author artur
 */
@WebServlet(name = "Myservlet", urlPatterns = {
    "/addProduct",
    "/createProduct",
    "/listProducts",
    "/addPerson",
    "/createPerson",
    "/listPersons",
    "/buyProductForm",
    "/buyProduct",
    "/addMoneyToPerson",
    "/addMoney"
})

public class Myservlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private PersonFacade personFacade;
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
                Product product = new Product( name, Integer.parseInt(count), Integer.parseInt(quantity));
                productFacade.create(product);
                request.setAttribute("info","Добавлен товар" + product.toString());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "/addPerson":
                request.getRequestDispatcher("/WEB-INF/addPersonForm.jsp").forward(request, response); 
                break;
                
            case "/createPerson":             
                name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String cash = request.getParameter("cash");
                
                if ("".equals(name) || name == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(cash) || cash == null
                        ){
                    request.setAttribute("info", "Пожалуйста заполните все поля формы!");
                    request.setAttribute("name", name);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("cash", cash);
                    
                    request.getRequestDispatcher("/WEB-INF/addPersonForm.jsp").forward(request, response);
                    break;
                }
                Person person = new Person(name, lastname, phone, Integer.parseInt(cash));
                personFacade.create(person);
                request.setAttribute("info","Добавлен новый покупатель" + person.toString());
                request.getRequestDispatcher("/index.jsp").forward(request, response);               
                break;
                
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
                
            case "/listPersons":
                List<Person> listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/listPersons.jsp").forward(request, response);
                break;
                
            case "/buyProductForm":
                listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                break;
                
            case "/buyProduct":
                String productId = request.getParameter("productId");
                String personId = request.getParameter("personId");
                count = request.getParameter("count");
                quantity = request.getParameter("quantity");
                
                if ("".equals(personId) || personId == null
                        || "".equals(productId) || productId == null
                        || "".equals(count) || count == null
                        || "".equals(quantity) || quantity == null
                        ){
                    
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("productId", productId);
                    request.setAttribute("personId", personId);
                    request.setAttribute("count", count);
                    request.setAttribute("quantity", quantity);
                    
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listPersons = personFacade.findAll();
                    request.setAttribute("listPersons", listPersons);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;
                }
                product = productFacade.find(Long.parseLong(productId));
                person = personFacade.find(Long.parseLong(personId));
                if (person.getCash()<product.getCount()) {
                    request.setAttribute("info", "У вас недостаточно средств!");
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listPersons = personFacade.findAll();
                    request.setAttribute("listPersons", listPersons);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;
                }
                if (product.getQuantity()>person.getCash()) {
                    request.setAttribute("info", "У нас нет такого количества товара!");
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listPersons = personFacade.findAll();
                    request.setAttribute("listPersons", listPersons);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;                  
                }
                person.setCash(person.getCash()-(product.getCount()*Integer.parseInt(quantity)));
                personFacade.edit(person);
                product.setQuantity(product.getQuantity()-Integer.parseInt(count));
                productFacade.edit(product);
                request.setAttribute("info", "Товар куплен!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "/addMoneyToPerson":
                listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/addMoneyToPerson.jsp").forward(request, response);
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
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
