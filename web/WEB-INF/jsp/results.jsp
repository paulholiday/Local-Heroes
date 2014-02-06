<%@page import="com.lbi.localheroes.model.Hero"%>
<%@page import="com.lbi.localheroes.DBQuery"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mongodb.AggregationOutput"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.lbi.localheroes.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value='resources/css/style.css'/>">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJsIRBzqaCKo2NATEMk_8TvBzG9e1FcYI&sensor=true">
        </script>
        <script type="text/javascript" src="resources/js/jquery-1.10.2.min.js" ></script>
        <script type="text/javascript" src="resources/js/localHeroes.js" ></script>
        <title>Local Heroes</title>
    </head>
    <body>
        <div class="topBar">
            <a href="index.htm"><h1>Local Heroes</h1></a>
            <div class="topRight">
                <span>Brick Lane, London</span>
                <img class="toggleButton london" src="resources/images/toggle.jpg"/>
                <span>Timber Bush, Edinburgh</span>
            </div>
        </div>
        <div class="body">
            <h1>Results for <%= request.getParameter("Category")%></h1>
            <div id="map-canvas"></div>
            <div class="leftResultsTab">
                <div class="scrollableResults">
                    <%
                        String category = request.getParameter("Category");

                        DBQuery dbQuery = new DBQuery("heroes");
                        ArrayList<Hero> heroes = dbQuery.getHeroesByCategory(category);

                        for (Hero hero : heroes) {
                    %>          <div class="result">
                        <h3><c:out value='<%= hero.getName()%>' /></h3>
                        <c:if test='<%= hero.getAddress() != null%>'>
                            <p><c:out value='<%= hero.getAddress().getLine1()%>' />, 
                                <c:out value='<%= hero.getAddress().getCounty()%>' />, 
                                <c:out value='<%= hero.getAddress().getPostCode()%>' /></p>
                            </c:if>

                    </div>
                    <script type="text/javascript">
                        var lat = <%= Double.parseDouble(hero.getPoint().getLatitude())%>;
                        var long = <%= Double.parseDouble(hero.getPoint().getLongitude())%>;

                        coordsList.push(lat, long);
                    </script>
                    <%
                        }
                    %>

                </div>
            </div>
            <div class="tags">
                <h3>Refine you search by selecting tags:</h3><br>
                <%
                    AggregationOutput output = dbQuery.getTagsFromCategory(category);

                    for (DBObject object : output.results()) {
                        String tagName = object.get("name").toString();
                        %><span class="tag"> <%= tagName%> </span><%
                    }
                %>
                    ${name}
            </div>
        </div>

        <script type="text/javascript">
            var selectedTags = new Array();
            $('.tag').click(function() {
                selectedTags.push($(this).html());
                <c:set var="tagsList" value="selectedTags" /> //getDB to return all places with the category and selected tags then reset list and map
                alert("ajax about to start");
                doAjax();
                function doAjax(){
                    $.ajax({
                        url: 'resultsDefined.htm',
                        success: function(response){
                            $('.tags').html(response);
                        }
                    });
                    alert("during ajax");
                }
                alert("ajax done");
            });
        </script>
    </body>
</html>