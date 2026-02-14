import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class FirstApp extends GenericServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Int() method called......");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<h1>Hello Dada.</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() method called......");
    }
}
