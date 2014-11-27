package com.lbi.localheroes;

import com.lbi.localheroes.model.Address;
import com.lbi.localheroes.model.Category;
import com.lbi.localheroes.model.Hero;
import com.lbi.localheroes.model.Point;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
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
    
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<Category>();
        DBCursor cursor = table.find();
        
        while (cursor.hasNext()){
            DBObject jsonString = cursor.next();
            JSONObject json;
            try {
                json = (JSONObject) new JSONParser().parse(jsonString.toString());
                Category category = new Category(json.get("name").toString());
                categories.add(category);

            } catch (ParseException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
        
        return categories;
    }
    
    public List<Hero> getHeroesByCategory(String category){
        List<Hero> heroes = new ArrayList<Hero>();
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
                JSONArray tags = (JSONArray)json.get("tags");
                
                if(tags != null) {
                    for(int i = 0; i < tags.size(); i++) {
                        hero.addTag(tags.get(i).toString());
                    }
                    
                }
                
                if(address != null){
                    Address heroAddress = new Address();
                    heroAddress.setLine1(address.get("line1").toString());
                    heroAddress.setCounty(address.get("county").toString());
                    //heroAddress.setTown(address.get("town").toString());
                    heroAddress.setPostCode(address.get("postcode").toString());
                    hero.setAddress(heroAddress);
                }
                
                
                if(point != null){
                    Point heroPoint = new Point();
                    heroPoint.setLatitude(Double.parseDouble(point.get("latitude").toString()));
                    heroPoint.setLongitude(Double.parseDouble(point.get("longitude").toString()));
                    hero.setPoint(heroPoint);
                }
                
                heroes.add(hero);

            } catch (ParseException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }                    
                                
        
        return heroes;
    }
    
    public List<String> getTagsFromCategory(String category){
        
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
        
        List<String> tagsList = new ArrayList<String>();
        for(DBObject dbObject : output.results()) {
            tagsList.add(dbObject.get("name").toString());
        }
        
        return tagsList;
        
    }

    public void addHero(Hero hero) {
        BasicDBObject heroToAdd = new BasicDBObject("name", hero.getName());
        heroToAdd.append("categories", "[" + hero.getCategories().get(0) + "]");
        heroToAdd.append("tags", "[]");
        
        BasicDBObject addressToAdd = new BasicDBObject("line1", hero.getAddress().getLine1());
        addressToAdd.append("county", hero.getAddress().getCounty());
        addressToAdd.append("postcode", hero.getAddress().getPostCode());
        
        heroToAdd.append("address", addressToAdd);
        
        BasicDBObject pointToAdd = new BasicDBObject("latitude", hero.getPoint().getLatitude());
        pointToAdd.append("longitude", hero.getPoint().getLongitude());
        heroToAdd.append("point", pointToAdd);
        
        BasicDBObject locationToAdd = new BasicDBObject("type", "Point");
        double[] coordsArray = {hero.getPoint().getLongitude(), hero.getPoint().getLatitude()};
        locationToAdd.append("coordinates", coordsArray);
        
        heroToAdd.append("loc", locationToAdd);
        
        table.insert(heroToAdd, WriteConcern.JOURNAL_SAFE);
    }
    
    public void addCategory(Category category) {
        BasicDBObject categoryToAdd = new BasicDBObject("name", category.getName());
        table.insert(categoryToAdd, WriteConcern.JOURNAL_SAFE);
    }
    
}
