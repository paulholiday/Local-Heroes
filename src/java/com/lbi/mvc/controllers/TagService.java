/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.mvc.controllers;

import com.lbi.localheroes.DBQuery;
import com.mongodb.AggregationOutput;
import com.mongodb.CommandResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author holidayp
 */
@Path("/tags")
public class TagService {
    
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommandResult getHeroesByCategory(@PathParam("param") String category) {

        DBQuery dbQuery = new DBQuery("heroes");
        AggregationOutput heroes = dbQuery.getTagsFromCategory(category);

        return heroes.getCommandResult();

    }
    
}
