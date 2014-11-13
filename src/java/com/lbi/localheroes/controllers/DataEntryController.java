/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Address;
import com.lbi.localheroes.model.Category;
import com.lbi.localheroes.model.Hero;
import com.lbi.localheroes.model.Point;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author holidayp
 */
public class DataEntryController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public DataEntryController(){
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        Hero hero = new Hero();
        ArrayList<String> categories = new ArrayList<String>();
        categories.add(request.getParameter("Category"));
        hero.setCategories(categories);
        hero.setName(request.getParameter("Name"));
        ArrayList<String> tagsList = new ArrayList<String>();
        String[] tagsArray = request.getParameter("Tags").split(",");
        
        for(int i = 0; i < tagsArray.length; i++) {
            tagsList.add(tagsArray[i]);
        }
        
        hero.setTags(tagsList);
        
        Address address = new Address();
        address.setLine1(request.getParameter("Line1"));
        address.setCounty(request.getParameter("County"));
        address.setPostCode(request.getParameter("PostCode"));
        
        hero.setAddress(address);
        
        Point point = new Point();
        point.setLatitude(Double.parseDouble(request.getParameter("Latitude")));
        point.setLongitude(Double.parseDouble(request.getParameter("Longitude")));
        
        hero.setPoint(point);
        
        DBQuery dbQuery = new DBQuery("heroes");
        dbQuery.addHero(hero);
        
        RequestDispatcher rd = null; 

        rd = request.getRequestDispatcher("/addHero.jsp");
   
        rd.forward(request, response);
    }
}
