/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.mvc.controllers;

/**
 *
 * @author holidayp
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import static com.lbi.web.resource.service.context.ContentServiceContext.CATEGORY_TYPE;
import static com.lbi.web.resource.url.UrlContext.BY_CATEGORY;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
        
        @GET
        @Path("/{" + BY_CATEGORY + "}/{" + CATEGORY_TYPE + "}")
        public Response getContentByCategory(@PathParam(CATEGORY_TYPE) String categoryType) {
            String output = "Jersey say : " + categoryType;
 
		return Response.status(200).entity(output).build();
 
        }
        
 
}
