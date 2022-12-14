package com.controller;

import com.dao.UserDaoImplementation;
import com.entity.User;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String userName = req.getParameter("username").trim();
        String password = req.getParameter("password");

        User user = new UserDaoImplementation().getUserByName(userName);
        int userId = user.getUserId();
        ServletContext servletContext=req.getServletContext();
        servletContext.setAttribute("userId",userId);

        if(user.getPassword().equals(password)) {

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("LoginSuccessful.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            out.println("Invalid Credentials Try Again");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.include(req,resp);
        }
    }
}
