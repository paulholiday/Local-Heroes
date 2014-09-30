/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author holidayp
 */
public class Hero {
    
    private String id;
    private String name;
    private List<String> categories;
    private List<String> tags;
    private Address address;
    private Point point;
    
    public Hero(){
        tags = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    
    public void addTag(String tag) {
        tags.add(tag);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    } 
    
    
    
    
}
