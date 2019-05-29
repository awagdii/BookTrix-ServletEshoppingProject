package AdminServlets;

import Beans.Book;
import Beans.Category;
import DBconnectivity.ManipulateDB;
import controllers.ControlServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ahmed
 */
public class AddProduct extends HttpServlet {
    ControlServlet controlServlet;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String bookName =null,bookAuthor =null,category =null,desc =null,img =null;
                    double quantity =0,price =0;
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
//processFormField(item);
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println(name);
                    System.out.println(value);
                    
                     if (name.equalsIgnoreCase("bookName")) {
                        bookName = value;
                    } else if (name.equalsIgnoreCase("bookAuthor")) {
                        bookAuthor = value;
                    } else if (name.equalsIgnoreCase("quantity")) {
                        quantity = Double.parseDouble(value);
                    } else if (name.equalsIgnoreCase("category")) {
                        category = value;

                    } else if (name.equalsIgnoreCase("desc")) {
                        desc = value;
                    } else if (name.equalsIgnoreCase("price")) {
                        price = Double.parseDouble(value);
                    } 

                } else {
                    if (!item.isFormField()) {

                        System.out.println(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 27) + "/web/Resources/pics/" + item.getName()));
                        String imagesPath = "C:\\serverUpload";
                        item.write(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 27) + "/web/Resources/pics/" + item.getName()));
                        img =item.getName();
                    }
                }
            }
            
            ManipulateDB m = new ManipulateDB();
            Category categoryObj=m.selectCategoryFromName(category);
            
            
            Book b = new Book(bookName,(int)quantity,bookAuthor ,categoryObj,(int) price,img, desc);
            System.out.println(b);
            m.insertBook(b);

            response.sendRedirect("AdminHome.jsp");

        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            String categoryName = request.getParameter("categoryName");
            controlServlet=new ControlServlet();
            out = response.getWriter();
            boolean inserted = controlServlet.addCategory(categoryName);
            if(inserted==true){
                out.println("Done");
            }
            else{
                out.println("That category already exists");
            }
        } catch (IOException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}
