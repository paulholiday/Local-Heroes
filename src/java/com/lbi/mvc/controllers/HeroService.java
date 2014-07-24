/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.mvc.controllers;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Hero;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
@Path("/heroes")
public class HeroService {
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hero> getHeroesByCategory(@PathParam("param") String category) {

        DBQuery dbQuery = new DBQuery("heroes");
        ArrayList<Hero> heroes = dbQuery.getHeroesByCategory(category);

        return heroes;

    }
    
    
}
