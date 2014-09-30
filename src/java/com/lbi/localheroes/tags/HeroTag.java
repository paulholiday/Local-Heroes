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
 * @author holidayp
 */
public class HeroTag extends TagSupport{
    
    private static final String CATEGORY_ATTRIBUTE_NAME = "Category";
    private static final String HEROES_ATTRIBUTE_NAME = "Heroes";
    private static final String TAGS_ATTRIBUTE_NAME = "Tags";
    
    private static final String REST_URI = "http://localhost:8080/MVC/rest/";
    private static final String GET_REQUEST_METHOD = "GET";
    private static final String REQUEST_PROPERTY = "ACCEPT";
    private static final String REQUEST_PROPERTY_VALUE = "application/json";
    private static final String HTTP_ERROR_STRING = "Failed : HTTP error code : ";
    
    private static final String HEROES_CONTENT_TYPE = "heroes";
    private static final String TAGS_CONTENT_TYPE = "tags";
    
    private static final String RESULT = "result";
    
    private static final String ADDRESS = "address";
    private static final String NAME = "name";
    private static final String LINE1 = "line1";
    private static final String COUNTY = "county";
    private static final String POSTCODE = "postCode";
    
    private static final String RESULTS_DIV = "<div class=\"result\" id=\"result-";
    private static final String CLOSE_TAG = ">";
    private static final String HEADER_3_TAG = "<h3>";
    private static final String CLOSE_HEADER_3_TAG = "</h3>";
    private static final String PARAGRAPH_TAG = "<p>";
    private static final String COMMA = ", ";
    private static final String CLOSE_PARAGRAPH_TAG = "</p>";
    private static final String CLOSE_DIV_TAG = "</div>";
    private static final String TAG_SPAN = "<span class=\"tag\">";
    private static final String END_SPAN = "</span>";
    
    private static final String FORWARD_SLASH = "/";
    
    
  
    @Override
    public int doEndTag() throws JspException {

        String category = pageContext.getRequest().getParameter(CATEGORY_ATTRIBUTE_NAME);
        
        pageContext.setAttribute(CATEGORY_ATTRIBUTE_NAME, category);
        pageContext.setAttribute(HEROES_ATTRIBUTE_NAME, getHeroesByCategory(category));
        pageContext.setAttribute(TAGS_ATTRIBUTE_NAME, getTagsByCategory(category));
        

        return EVAL_PAGE;
    }
    
    private String getHeroesByCategory(String category) {
        String markupForHeroes = null;
        
        try {

            String output = getOutputFromRestServiceCall(HEROES_CONTENT_TYPE, category);
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(output);
            markupForHeroes = getMarkupForHeroes(jsonArray);
            
        } catch (ParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return markupForHeroes;
    }
    
    
    private String getMarkupForHeroes (JSONArray jsonArray) {
        StringBuilder markupString = new StringBuilder();
        
        JSONObject json = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            json = (JSONObject) jsonArray.get(i);
            
            markupString.append(RESULTS_DIV);
            markupString.append(i);
            markupString.append("\"");
            
            JSONArray tagsArray = null;
            try {
                if(json.get("tags") != null) {
                    tagsArray = (JSONArray) new JSONParser().parse(json.get("tags").toString());
                }
                
            } catch (ParseException ex) {
                Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(tagsArray != null) {
                markupString.append("heroTags=\"");
                
                for(int j = 0; j < tagsArray.size(); j++) {
                    markupString.append(tagsArray.get(j));

                    if(j < tagsArray.size() - 1) {
                        markupString.append(";");
                    } 
                }
                
                markupString.append("\"");
            }
            
            
            markupString.append(CLOSE_TAG);
            markupString.append(HEADER_3_TAG);
            markupString.append(json.get(NAME));
            markupString.append(CLOSE_HEADER_3_TAG);
            
            if(json.get(ADDRESS) != null) {
                try {
                    JSONObject jsonAddress = (JSONObject) new JSONParser().parse(json.get(ADDRESS).toString());
                    markupString.append(PARAGRAPH_TAG);
                    markupString.append(jsonAddress.get(LINE1));
                    markupString.append(COMMA);
                    markupString.append(jsonAddress.get(COUNTY));
                    markupString.append(COMMA);
                    markupString.append(jsonAddress.get(POSTCODE));
                    markupString.append(CLOSE_PARAGRAPH_TAG);
                } catch (ParseException ex) {
                    Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            markupString.append(CLOSE_DIV_TAG);
        }
        
        return markupString.toString();
    }
    
    
    private String getTagsByCategory(String category) {
        String markupForTags = null;
        
        try {
            
            String output = getOutputFromRestServiceCall(TAGS_CONTENT_TYPE, category);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(output);
            markupForTags = getMarkupForTags(jsonObject);

        } catch (ParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return markupForTags;
    }
    
    private String getMarkupForTags (JSONObject jsonObject) {
        StringBuilder markupString = new StringBuilder();

        JSONArray jsonResults = null;
        
        try {
            jsonResults = (JSONArray) new JSONParser().parse(jsonObject.get(RESULT).toString());
            
            JSONObject json = null;
            for (int i = 0; i < jsonResults.size(); i++) {
                json = (JSONObject) jsonResults.get(i);
                markupString.append(TAG_SPAN);
                markupString.append(json.get(NAME));
                markupString.append(END_SPAN);
            
            }
        }catch (ParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return markupString.toString();
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
