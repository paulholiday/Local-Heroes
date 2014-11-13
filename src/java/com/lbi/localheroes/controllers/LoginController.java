/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author holidayp
 */
public class LoginController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public LoginController() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        RequestDispatcher requestDispatcher = null;
        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        
        if("success".equals(result)) {
            request.setAttribute("username", username);
            requestDispatcher = request.getRequestDispatcher("/admin.jsp");
        }
        else {
            requestDispatcher = request.getRequestDispatcher("/error.jsp");
        }
        
        requestDispatcher.forward(request, response);
        
    }
}
