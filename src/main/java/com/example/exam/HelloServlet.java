package com.example.exam;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("isLogin") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            boolean isLogin = Boolean.parseBoolean(session.getAttribute("isLogin").toString());
            if (!isLogin) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                response.sendRedirect(request.getContextPath() + "/product");
            }
        }

    }

    public void destroy() {
    }
}