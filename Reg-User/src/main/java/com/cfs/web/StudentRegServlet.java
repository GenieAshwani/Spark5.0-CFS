package com.cfs.web;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class StudentRegServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            res.setContentType("text/html");

            String name=req.getParameter("username");
            String rollno=req.getParameter("rollno");
            String course=req.getParameter("course");
            int marks= Integer.parseInt(req.getParameter("marks"));

            Student s=new Student(name,rollno,course,marks);
            System.out.println(s);
    }
}
