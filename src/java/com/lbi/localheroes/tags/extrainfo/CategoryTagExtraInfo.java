package com.lbi.localheroes.tags.extrainfo;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 *
 * @author destreej
 */
public class CategoryTagExtraInfo extends TagExtraInfo{
    
    @Override
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[] { new VariableInfo("Categories", "String", true, VariableInfo.AT_END)};
    }
    
}
