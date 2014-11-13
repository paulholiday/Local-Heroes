/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes.controllers;

/**
 *
 * @author holidayp
 */
public class Authenticator {
    
    public String authenticate(String username, String password) {
        if(("paul".equalsIgnoreCase(username)) && ("password".equals(password))) {
            return "success";
        }
        else {
            return "failure";
        }
    }
    
}
