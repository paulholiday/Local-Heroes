package com.lbi.localheroes.controller;

import com.lbi.localheroes.model.Category;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategorySelectionController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public CategorySelectionController(){
        super();
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String categorySelected = request.getParameter("Category");
        
        Category category = new Category(categorySelected);
        
        RequestDispatcher rd = null;
        request.setAttribute("Category", category);
        
        rd = request.getRequestDispatcher("/results.jsp");
            
        rd.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
        
        String categorySelected = request.getParameter("Category");
        String tagsSelected = request.getParameter("Tags");
         
        if(tagsSelected != null) {
            String[] tagsList = tagsSelected.split(",");
            request.setAttribute("Tags", tagsList);
        }
        
        Category category = new Category(categorySelected);
        
        RequestDispatcher rd = null;
        request.setAttribute("Category", category);
        
        rd = request.getRequestDispatcher("/results.jsp");
        
        rd.forward(request, response);
    }
    
    
}
