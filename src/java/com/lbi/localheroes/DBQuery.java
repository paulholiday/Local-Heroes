package com.lbi.localheroes;

import com.lbi.localheroes.model.Address;
import com.lbi.localheroes.model.Hero;
import com.lbi.localheroes.model.Point;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author holidayp
 */
public class DBQuery {
    
    private DBConnector connector;
    private DBCollection table;
    
    public DBQuery(String tableName){
        connector = new DBConnector();
        connector.connect();
        this.table = connector.getTable(tableName);
    }
    
    public ArrayList<Hero> getHeroesByCategory(String category){
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        DBObject query = new BasicDBObject();
        query.put("categories", category);
        DBObject id = new BasicDBObject("_id", 0);
        DBCursor cursor = table.find(query, id);
        
        while(cursor.hasNext()){
             DBObject jsonString = cursor.next();
             JSONObject json;
             Hero hero = new Hero();
            try {
                json = (JSONObject)new JSONParser().parse(jsonString.toString());
                //hero.setId(json.get("id").toString());
                hero.setName(json.get("name").toString());
                JSONObject address = (JSONObject)json.get("address");
                JSONObject point = (JSONObject)json.get("point");
                if(address != null && point != null){
                    Address heroAddress = new Address();
                    heroAddress.setLine1(address.get("line1").toString());
                    heroAddress.setCounty(address.get("county").toString());
                    //heroAddress.setTown(address.get("town").toString());
                    heroAddress.setPostCode(address.get("postcode").toString());
                    hero.setAddress(heroAddress);
                }
                
                if(point != null){
                    Point heroPoint = new Point();
                    heroPoint.setLatitude(point.get("latitude").toString());
                    heroPoint.setLongitude(point.get("longitude").toString());
                    hero.setPoint(heroPoint);
                }
                
                heroes.add(hero);

            } catch (ParseException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }                    
                                
        
        return heroes;
    }
    
    public AggregationOutput getTagsFromCategory(String category){
        ArrayList<String> tagsList = new ArrayList<String>();
        
        DBObject categoryQuery = new BasicDBObject();
        categoryQuery.put("categories", category);
        DBObject match = new BasicDBObject();
        match.put("$match", categoryQuery);
        DBObject tags = new BasicDBObject();
        tags.put("_id", 0);
        tags.put("tags", 1);
        DBObject project = new BasicDBObject();
        project.put("$project", tags);
        DBObject unwind = new BasicDBObject();
        unwind.put("$unwind", "$tags");
        DBObject sum = new BasicDBObject();
        sum.put("$sum", 1);
        DBObject count = new BasicDBObject();
        count.put("count", sum);
        count.put("_id", "$tags");
        DBObject groupTags = new BasicDBObject();
        groupTags.put("$group", count);
        DBObject countTags = new BasicDBObject();
        countTags.put("count", 1);
        countTags.put("name", "$_id");
        countTags.put("_id", 0);
        DBObject projectTags = new BasicDBObject();
        projectTags.put("$project", countTags);
        DBObject sortCount = new BasicDBObject();
        sortCount.put("count", -1);
        DBObject sort = new BasicDBObject();
        sort.put("$sort", sortCount);
        AggregationOutput output = table.aggregate(match, project, unwind, groupTags, projectTags, sort);
        
        return output;
        
    }

    public String getHeroesByCategoryAndTags(String[] tags, String category) {
//        ArrayList<Hero> heroes = new ArrayList<Hero>();
//        String[] tagList = tags.split(",");
//        DBObject query = new BasicDBObject();
//        query.put("categories", category);
//        for(String tag : tagList){
//            tag = tag.trim();
//            
//        }
//        
//        DBObject id = new BasicDBObject("_id", 0);
//        DBCursor cursor = table.find(query, id);
//        
//        while(cursor.hasNext()){
//             DBObject jsonString = cursor.next();
//             JSONObject json;
//             Hero hero = new Hero();
//            try {
//                json = (JSONObject)new JSONParser().parse(jsonString.toString());
//                //hero.setId(json.get("id").toString());
//                hero.setName(json.get("name").toString());
//                JSONObject address = (JSONObject)json.get("address");
//                JSONObject point = (JSONObject)json.get("point");
//                if(address != null && point != null){
//                    Address heroAddress = new Address();
//                    heroAddress.setLine1(address.get("line1").toString());
//                    heroAddress.setCounty(address.get("county").toString());
//                    //heroAddress.setTown(address.get("town").toString());
//                    heroAddress.setPostCode(address.get("postcode").toString());
//                    hero.setAddress(heroAddress);
//                }
//                
//                if(point != null){
//                    Point heroPoint = new Point();
//                    heroPoint.setLatitude(point.get("latitude").toString());
//                    heroPoint.setLongitude(point.get("longitude").toString());
//                    hero.setPoint(heroPoint);
//                }
//                
//                heroes.add(hero);
//
//            } catch (ParseException ex) {
//                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
//            }
//             
//        }   
        
        return "a string";
    }
    
}
