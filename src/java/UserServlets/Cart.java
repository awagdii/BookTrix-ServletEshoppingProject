package UserServlets;

import Beans.Book;
import controllers.ControlServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {

    ControlServlet controller;
    
    public void init(){
        controller = new ControlServlet();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            
            String userName = request.getParameter("userName");
            HashMap<Book,Integer> allBooksWithQuantities = controller.getAllBooksInCart(userName); 
           
            System.out.println(userName);
            HttpSession session = request.getSession(true);
            session.setAttribute("booksWithQuantities", allBooksWithQuantities);
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String userName = request.getParameter("userName");
            String bookId = request.getParameter("bookId");
            String bookQuantity = request.getParameter("Quantity");
            if (controller.addBookToCart(userName, Integer.parseInt(bookId),Integer.parseInt(bookQuantity))) {
                out.print("your book has been added! :) ");
            } else {
                out.print("there is an error");
            }
        }

    }
}
