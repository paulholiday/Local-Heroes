/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author holidayp
 */
public class HeroTag extends TagSupport{

    @Override
    public int doEndTag() throws JspException {
        pageContext.setAttribute("Message", "Hello Jon");
        return EVAL_PAGE; //To change body of generated methods, choose Tools | Templates.
    }
    
}
