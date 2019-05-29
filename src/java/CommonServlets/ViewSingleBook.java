/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import Beans.Book;
import DBconnectivity.ManipulateDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
public class ViewSingleBook extends HttpServlet {

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            int bookId= Integer.parseInt(request.getParameter("bookid"));
            ManipulateDB m = new ManipulateDB();
            Book myBook = m.selectSingleBook(bookId);

            System.out.println("iam in select single books");

            HttpSession session = request.getSession(true);
            session.setAttribute("book", myBook);
        }
    }
