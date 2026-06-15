package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UserUpdateServlet extends HttpServlet {
    private UserDAO userDAO;
    public void init() { userDAO = new UserDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            String password = request.getParameter("password");
            if (password != null && !password.isEmpty()) user.setPassword(password);
            userDAO.update(user);
        }
        response.sendRedirect("users");
    }
}
