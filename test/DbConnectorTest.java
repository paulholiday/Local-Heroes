/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.lbi.localheroes.DBConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author holidayp
 */
public class DbConnectorTest {
    
    @Test
    public void validConnectionTest(){
        //Given
        DBConnector dbConnector = new DBConnector();
        
        //When
        dbConnector.connect();
        
        //Then
        Assert.assertEquals(dbConnector.getDatabase().getName(), "local-heroes");
    }
    
    @Test
    public void createTableTest(){
        //Given
        DBConnector dbConnector = new DBConnector();
        dbConnector.connect();
        
        //When
        String tableName = dbConnector.getTable("categories").getName();
        
        //Then
        Assert.assertEquals(tableName, "categories");
    }
    
    @Test
    public void getAllCategories(){
        //Given
        DBConnector dbConnector = new DBConnector();
        dbConnector.connect();
        DBCollection categories = dbConnector.getTable("categories");
        
        //When
        DBCursor cursor = dbConnector.selectAllFromTable(categories);
        
        //Then
        Assert.assertTrue(cursor.count() > 1);
    }
    
    @Test
    public void setupCategories(){
        //Given
        DBConnector dbConnector = new DBConnector();
        dbConnector.connect();
        DBCollection categories = dbConnector.getTable("categories");
        
        //When
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Pubs");
        categories.insert(document);
        document = new BasicDBObject();
        document.put("name", "Restaurants");
        categories.insert(document);
        document = new BasicDBObject();
        document.put("name", "Phone shops");
        categories.insert(document);
        document = new BasicDBObject();
        document.put("name", "Food shops");
        categories.insert(document);
        document = new BasicDBObject();
        document.put("name", "Post Offices");
        categories.insert(document);     
        DBCursor cursor = dbConnector.selectAllFromTable(categories);
        
        //Then
        Assert.assertTrue(cursor.count() >=5);
    }
    
    @Test
    public void retrieveCategories(){
        //Given
        DBConnector dbConnector = new DBConnector();
        dbConnector.connect();
        DBCollection categories = dbConnector.getTable("categories");
        
        //When
        BasicDBObject allQuery = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        fields.put("name", 1);
        DBCursor cursor = categories.find(allQuery, fields);
        
        //Then
        Assert.assertTrue(cursor.count() >=5);
    }
}