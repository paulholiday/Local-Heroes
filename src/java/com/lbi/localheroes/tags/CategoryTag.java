/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.tags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import javax.servlet.jsp.tagext.TagSupport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author destreej
 */
public class CategoryTag extends TagSupport{
    
    private static final String CATEGORIES_ATTRIBUTE_NAME = "Categories";
    
    private static final String REST_URI = "http://localhost:8080/LocalHeroesProject/rest/";
    private static final String GET_REQUEST_METHOD = "GET";
    private static final String REQUEST_PROPERTY = "ACCEPT";
    private static final String REQUEST_PROPERTY_VALUE = "application/json";
    private static final String HTTP_ERROR_STRING = "Failed : HTTP error code : ";
    
    private static final String CATEGORIES_CONTENT_TYPE = "categories";
    
    private static final String NAME = "name";
    
    private static final String START_OPTION_TAG = "<option>";
    private static final String CLOSE_OPTION_TAG = "</option>";
    
  
    @Override
    public int doEndTag() throws JspException {
       pageContext.setAttribute(CATEGORIES_ATTRIBUTE_NAME, getCategories());
        return EVAL_PAGE;
    }
    
    private String getCategories() {
        String markupForCategories = null;
        
        try {

            String output = getOutputFromRestServiceCall(CATEGORIES_CONTENT_TYPE);
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(output);
            markupForCategories = getMarkupForCategories(jsonArray);
            
        } catch (ParseException ex) {
            Logger.getLogger(CategoryTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return markupForCategories;
    }
    
    
    private String getMarkupForCategories (JSONArray jsonArray) {
        StringBuilder markupString = new StringBuilder();
        
        JSONObject json = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            json = (JSONObject) jsonArray.get(i);
            
            markupString.append(START_OPTION_TAG);
            markupString.append(json.get(NAME));
            markupString.append(CLOSE_OPTION_TAG);
        }
        
        return markupString.toString();
    }
    
    private String getOutputFromRestServiceCall(String contentType) {
        String restServiceOutput = null;
        
        try {
            URL url = new URL(REST_URI + contentType);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET_REQUEST_METHOD);
            conn.setRequestProperty(REQUEST_PROPERTY, REQUEST_PROPERTY_VALUE);

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException(HTTP_ERROR_STRING
                                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            restServiceOutput = br.readLine();
            
            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(CategoryTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CategoryTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return restServiceOutput;
    }
    
    
}
