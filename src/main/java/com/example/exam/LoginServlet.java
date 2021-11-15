package com.example.exam;

import com.example.exam.dao.ProductDao;
import com.example.exam.dao.UserDao;
import com.example.exam.models.Product;
import com.example.exam.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!UserDao.login(username, password)) {
            request.setAttribute("errorMessage", "Username or password is not valid!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", true);
        response.sendRedirect(request.getContextPath() + "/product");
    }
}
