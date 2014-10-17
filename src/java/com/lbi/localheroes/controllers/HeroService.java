/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Hero;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/heroes")
public class HeroService {
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hero> getHeroesByCategory(@PathParam("param") String category) {

        DBQuery dbQuery = new DBQuery("heroes");
        List<Hero> heroes = dbQuery.getHeroesByCategory(category);

        return heroes;

    }
    
    
}
