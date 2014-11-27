/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Category;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author destreej
 */
public class CategoryEntryController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public CategoryEntryController(){
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        Category category = new Category(request.getParameter("Name"));
        
        DBQuery dbQuery = new DBQuery("categories");
        dbQuery.addCategory(category);
        
        RequestDispatcher rd = request.getRequestDispatcher("/addCategory.jsp");
   
        rd.forward(request, response);
    }
}
