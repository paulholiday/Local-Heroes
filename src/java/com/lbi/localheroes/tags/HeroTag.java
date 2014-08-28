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
  
    @Override
    public int doEndTag() throws JspException {

        String category = pageContext.getRequest().getParameter("Category");
        
        pageContext.setAttribute("Category", category);
        
        try {
            URL url = new URL("http://localhost:8080/MVC/rest/heroes/" + category);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse(output);
                    output = getMarkupForHeroes(jsonArray);
                    pageContext.setAttribute("Heroes", output);
            }

            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return EVAL_PAGE;
    }
    
    
    private String getMarkupForHeroes (JSONArray jsonArray) {
        StringBuilder markupString = new StringBuilder();
        
        JSONObject json = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            json = (JSONObject) jsonArray.get(i);
            
            markupString.append("<div class=\"result\" id=\"result-");
            markupString.append(i);
            markupString.append("\">");
            markupString.append("<h3>");
            markupString.append(json.get("name"));
            markupString.append("</h3>");
            
            if(json.get("address") != null) {
                try {
                    JSONObject jsonAddress = (JSONObject) new JSONParser().parse(json.get("address").toString());
                    markupString.append("<p>");
                    markupString.append(jsonAddress.get("line1"));
                    markupString.append(", ");
                    markupString.append(jsonAddress.get("county"));
                    markupString.append(", ");
                    markupString.append(jsonAddress.get("postCode"));
                    markupString.append("</p>");
                } catch (ParseException ex) {
                    Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            markupString.append("</div>");
        }
        
        return markupString.toString();
    }
    
    

    
    
}
