package com.lbi.mvc.controllers;

import com.lbi.mvc.model.ButtonContent;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public MyController() {
        super();
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String buttonClicked = request.getParameter("buttonClicked");
        
        ButtonContent buttonContent = new ButtonContent(buttonClicked);
        
        RequestDispatcher rd = null;
        request.setAttribute("buttonContent", buttonContent);
        rd = request.getRequestDispatcher("/test.jsp");
            
        rd.forward(request, response);
    }
 
}
