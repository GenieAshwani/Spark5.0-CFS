package com.cfs.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {

    private static final String KEY="STD_LIST";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Student> list=getList();

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>All Students ("+list.size()+")</h2>");
        out.println("<a href='index.html'>+ Add Students </a> </br></br>");

        out.println("<table border='1' cellpadding='7' cellspacing='0' >");
        out.println("<tr><th>#</th><th>Name</th><th>Roll</th><th>Course</th><th>Marks</th></tr>");

        int i=1;
        for(Student s:list)
        {
            out.println("<tr>");
            out.println("<td>"+(i++)+"</td>");
            out.println("<td>"+s.getName()+"</td>");
            out.println("<td>"+s.getRoll()+"</td>");
            out.println("<td>"+s.getCourse()+"</td>");
            out.println("<td>"+s.getMarks()+"</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }

    private List<Student> getList() {
        ServletContext ctx=getServletContext();
        List<Student> list=(List<Student>)ctx.getAttribute(KEY);
        return (list==null) ? Collections.emptyList():list;
    }


}
