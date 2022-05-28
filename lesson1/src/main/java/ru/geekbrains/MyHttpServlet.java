package ru.geekbrains;

import ru.geekbrains.product.Product;
import ru.geekbrains.product.ProductStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/products/*")
public class MyHttpServlet extends HttpServlet {
    private ProductStorage productStorage;
    ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.productStorage = new ProductStorage();
        productStorage.insert(new Product("Cucumber", 6));
        productStorage.insert(new Product("Potato", 5));
        productStorage.insert(new Product("Onion", 3));
        productStorage.insert(new Product("Carrot", 4));
        productStorage.insert(new Product("Apple", 6));
        productStorage.insert(new Product("Pineapple", 10));
        productStorage.insert(new Product("Cauliflower", 10));
        productStorage.insert(new Product("Broccoli", 8));
        productStorage.insert(new Product("Orange", 6));
        productStorage.insert(new Product("Kiwi", 11));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("product", productStorage.findAll());
            req.getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
            return;
        }
        for (Product product: productStorage.findAll()){
                if (req.getPathInfo().equals(String.format("/%d",product.getId()))){
                    req.setAttribute("id", product.getTitle());
                    req.getServletContext().getRequestDispatcher("/product_form.jsp").forward(req, resp);
                }
            }
//        PrintWriter writer = resp.getWriter();
//        writer.println("<table>");
//        writer.println("<tr>");
//        writer.println("<th>id</th>");
//        writer.println("<th>title</th>");
//        writer.println("<th>cost</th>");
//        writer.println("</tr>");
//        if (req.getPathInfo()==null){
//            for (Product product: productStorage.findAll()){
//                writer.println("<tr>");
//                writer.println(String.format("<td>%d</td>", product.getId()));
//                writer.println(String.format("<td>%s</td>", product.getTitle()));
//                writer.println(String.format("<td>%d</td>", product.getCost()));
//                writer.println("</tr>");
//            }
//
//        } else {
//            for (Product product: productStorage.findAll()){
//                if (req.getPathInfo().equals(String.format("/%d",product.getId()))){
//                    writer.println("<tr>");
//                    writer.println(String.format("<td>%d</td>", product.getId()));
//                    writer.println(String.format("<td>%s</td>", product.getTitle()));
//                    writer.println(String.format("<td>%d</td>", product.getCost()));
//                    writer.println("</tr>");
//                }
//            }
//        }
//
//
//        writer.println("</table>");


    }
}
