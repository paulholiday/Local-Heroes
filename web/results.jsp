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
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <%
                String category = request.getParameter("Category");

                DBQuery dbQuery = new DBQuery("heroes");
            %>
            <div class="leftResultsTab">
                <div class="scrollableResults">
                    <%@include file="/WEB-INF/jspf/resultsList.jspf" %>
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
                ${refinedHeroes.toString()}
            </div>
        </div>

        <script type="text/javascript">
            $('.tag').click(function() {
                $(this).addClass("selected");
                selectedTags.push($(this).html());
                doAjax();
                function doAjax() {
                    $.ajax({
                        url: '/resultsDefined',
                        data: { selectedTags: selectedTags, category: '<%= category %>' },
                        success: function(response) {
                            //$('.scrollableResults').load('/WEB-INF/jspf/resultsList.jspf');
                            $('.scrollableResults').html(response);
                        }
                    });
                }
            });
        </script>
    </body>
</html>