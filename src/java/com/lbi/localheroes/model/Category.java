/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.model;



/**
 *
 * @author holidayp
 */

public class Category {
    
    private String id;
    private String name;
    
    public Category(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Category(String name) {
        this.name = name;
    }
    
     
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
