package com.lbi.localheroes.tags.extrainfo;

import com.lbi.localheroes.model.Category;
import java.util.List;
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
        return new VariableInfo[] { new VariableInfo("Categories", List.class.getName(), true, VariableInfo.AT_END)};
    }
    
}
