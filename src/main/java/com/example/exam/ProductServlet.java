package com.example.exam;

import com.example.exam.dao.ProductDao;
import com.example.exam.models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isLogin = Boolean.parseBoolean(session.getAttribute("isLogin").toString());
        if (!isLogin) {
            response.sendRedirect(request.getContextPath() + "/login");
        }

        request.setAttribute("products", ProductDao.getAll());
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
