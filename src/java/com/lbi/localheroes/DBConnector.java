/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lbi.localheroes;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author holidayp
 */
public class DBConnector {
    
    private DB db;

    public void connect() {
        try {
            String textUri = "mongodb://paul-the-hero:thedarkknight@ds029778.mongolab.com:29778/local-heroes";
            MongoClientURI uri = new MongoClientURI(textUri);
            MongoClient mongoClient = new MongoClient(uri);
            db = mongoClient.getDB("local-heroes");
        } catch (UnknownHostException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DB getDatabase(){
        return db;
    }
    
    public DBCollection getTable(String tableName){
        return db.getCollection(tableName);
    }

    public DBCursor selectAllFromTable(DBCollection categories) {
        return categories.find();
    }
    
}
