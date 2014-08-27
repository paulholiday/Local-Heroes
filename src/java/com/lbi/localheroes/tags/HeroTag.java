/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author holidayp
 */
public class HeroTag extends TagSupport{
    
    @Override
    public int doEndTag() throws JspException {

        pageContext.setAttribute("Category", pageContext.getRequest().getParameter("Category"));
//        try {
//            pageContext.getOut().write("Hello Tag World");
//        } catch (IOException ex) {
//            Logger.getLogger(HeroTag.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return EVAL_PAGE;
    }

    
    
}
