/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.web.resource.service.context;

import javax.ws.rs.PathParam;

/**
 *
 * @author holidayp
 */
public class ContentServiceContext {
    public static final String CATEGORY_TYPE = "categoryType";
    
    @PathParam(CATEGORY_TYPE)
    private String categoryType;
    
    public ContentServiceContext() {
       // validationAspect.validate(this);
    }
}
