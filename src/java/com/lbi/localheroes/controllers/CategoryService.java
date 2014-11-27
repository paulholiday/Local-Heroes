/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Category;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/categories")
public class CategoryService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories() {

        DBQuery dbQuery = new DBQuery("categories");
        List<Category> categories = dbQuery.getCategories();

        return categories;

    }
    
    
}
