/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.tags;

import com.lbi.localheroes.model.Hero;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import javax.servlet.jsp.tagext.TagSupport;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author holidayp
 */
public class HeroTag extends TagSupport{
    
    private static final String CATEGORY_ATTRIBUTE_NAME = "Category";
    private static final String HEROES_ATTRIBUTE_NAME = "Heroes";
    private static final String TAGS_ATTRIBUTE_NAME = "Tags";
    
    private static final String REST_URI = "http://localhost:8080/LocalHeroesProject/rest/";
    private static final String GET_REQUEST_METHOD = "GET";
    private static final String REQUEST_PROPERTY = "ACCEPT";
    private static final String REQUEST_PROPERTY_VALUE = "application/json";
    private static final String HTTP_ERROR_STRING = "Failed : HTTP error code : ";
    
    private static final String HEROES_CONTENT_TYPE = "heroes";
    private static final String TAGS_CONTENT_TYPE = "tags";
    
    private static final String FORWARD_SLASH = "/";
    
    
  
    @Override
    public int doEndTag() throws JspException {

        String category = pageContext.getRequest().getParameter(CATEGORY_ATTRIBUTE_NAME);
        
        pageContext.setAttribute(CATEGORY_ATTRIBUTE_NAME, category);
        pageContext.setAttribute(HEROES_ATTRIBUTE_NAME, getHeroesByCategory(category));
        pageContext.setAttribute(TAGS_ATTRIBUTE_NAME, getTagsByCategory(category));
        

        return EVAL_PAGE;
    }
    
    private List<Hero> getHeroesByCategory(String category) {
        String output = getOutputFromRestServiceCall(HEROES_CONTENT_TYPE, category);
        
        ObjectMapper om = new ObjectMapper();
        List<Hero> heroes = null;
        
        try {
            heroes = om.readValue(output, List.class);
        } catch (JsonParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return heroes;
    }
    
    private List<String> getTagsByCategory(String category) {
        
        List<String> tags = null;
        String output = getOutputFromRestServiceCall(TAGS_CONTENT_TYPE, category);
        System.out.println("Output = " + output);
        ObjectMapper om = new ObjectMapper();
        try {
            tags = om.readValue(output, List.class);
        } catch (JsonParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        System.out.println(tags);
        
        return tags;
    }
    
    
    private String getOutputFromRestServiceCall(String contentType, String category) {
        String restServiceOutput = null;
        
        try {
            URL url = new URL(REST_URI + contentType + FORWARD_SLASH + category);
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
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return restServiceOutput;
    }
    
    
}
