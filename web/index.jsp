<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.lbi.localheroes.DBConnector,
                com.mongodb.DBCollection,
                com.mongodb.DBCursor,
                com.mongodb.BasicDBObject,
                org.json.simple.JSONObject,
                org.json.simple.parser.JSONParser,
                com.mongodb.DBObject,
                com.mongodb.AggregationOutput"  %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value='resources/css/style.css'/>">
        <title>Local Heroes</title>
    </head>

    <body>
        <div class="topBar">
            <a href=""><h1>Local Heroes</h1></a>
            <div class="topRight">
                <span>Brick Lane, London</span>
                <img class="toggleButton london" src="resources/images/toggle.jpg"/>
                <span>Timber Bush, Edinburgh</span>
            </div>
        </div>
        <div class="body">
            <p class="title">Choose a category you want to search for</p>
            <form id="search-form" action="Results" method="post">
                <select id="category"  name="Category">
                    <option>Please select a category</option>
                    <% 
                        DBConnector dbConnector = new DBConnector();
                        dbConnector.connect();
                        DBCollection categories = dbConnector.getTable("categories");
                        
                        DBObject allQuery = new BasicDBObject();
                        DBCursor cursor = categories.find(allQuery).sort(new BasicDBObject("name", 1));

                        while(cursor.hasNext()){
                            DBObject jsonString = cursor.next();
                            JSONObject json = (JSONObject)new JSONParser().parse(jsonString.toString());
                            %><option><c:out value='<%=json.get("name") %>' /></option><%
                        }
                    %>

                </select>
                <input id="submit" type="submit" value="Go">
            </form>
        </div>          
        <script type="text/javascript" src="resources/js/jquery-1.10.2.min.js" ></script>
        <script type="text/javascript" src="resources/js/localHeroes.js" ></script>
    </body>
</html>
