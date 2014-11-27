/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

import com.lbi.localheroes.DBQuery;
import com.mongodb.AggregationOutput;
import com.mongodb.CommandResult;
import java.util.List;
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
    public List<String> getHeroesByCategory(@PathParam("param") String category) {

        DBQuery dbQuery = new DBQuery();
        return dbQuery.getTagsFromCategory(category);
    }
    
}
