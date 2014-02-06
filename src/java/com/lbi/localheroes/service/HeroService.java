/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.service;

import com.lbi.localheroes.DBQuery;
import com.lbi.localheroes.model.Hero;
import java.util.ArrayList;

/**
 *
 * @author holidayp
 */
public class HeroService {
    
    private DBQuery dbQuery;
    
    public HeroService(){
        dbQuery = new DBQuery("heroes");
    }
    
    public String getHeroesByCategoryAndTags(String[] tags, String category){
        return dbQuery.getHeroesByCategoryAndTags(tags, category);
    }
}
