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
<%@taglib prefix="hero" uri="WEB-INF/tlds/Hero.tld"%>
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
        <hero:gohero/>
        
        <div class="topBar">
            <a href=""><h1>Local Heroes</h1></a>
            <div class="topRight">
                <span>Brick Lane, London</span>
                <img class="toggleButton london" src="resources/images/toggle.jpg"/>
                <span>Timber Bush, Edinburgh</span>
            </div>
        </div>
        <div class="body">
            
            <h1>Results for ${pageContext.getAttribute("Category")}</h1>
            <div id="map-canvas"></div>
            <%--
                String category = request.getParameter("Category");

                DBQuery dbQuery = new DBQuery("heroes");
            --%>
            <div class="leftResultsTab">
                <div class="scrollableResults">
                    //TODO: call ajax to get results and query with url parameters category and tags
                    <%--<%@include file="/WEB-INF/jspf/resultsList.jspf" %>--%>

                </div>
            </div>
            <div class="tags">
                //TODO: Deal with getting tags parameter from URL
                <h3>Refine your search by selecting tags:</h3><br>
            </div>
        </div>
        
       <script type="text/javascript">

            //$('.tag').click(function() {
            //    $(this).addClass("selected");
            //    selectedTags.push($(this).html());
//                doAjax();
//                function doAjax() {
//                    $.ajax({
//                        url: '/Results?Category=' + ${pageContext.getAttribute("Category")} + '&Tags=' + selectedTags,
//                        success: function(response) {
//                            //$('.scrollableResults').load('/WEB-INF/jspf/resultsList.jspf');
//                            $('.scrollableResults').html(response);
//                        }
//                    });
//                }
                
//                getResults();
//                function getResults() {
//                    var titleString = $('.body h1').html();
//                    var categoryString = titleString.substring(12); //hard-coded value to get Category
//                    var urlString = 'rest/heroes/' + categoryString;
//                    
//                    
//                    $.getJSON(urlString, function(data) {
////                        var count = 0;
////                        outputResults(jd, count);
////                        function outputResults(arg, count) {
//                        $.each(data, function(idx, obj) {
//                            console.log(obj);
//                            $('.scrollableResults').append('<div class="result" id="result-' + idx +'"><h3>' + obj.name + '</h3></div>');
//                            if(obj.address !== null) {
//                                $('#result-' + idx).append('<p>' + obj.address.line1 + ', ' + obj.address.county + ', ' + obj.address.postCode + '</p>');
//                            }
//                            
//                            var lat = obj.point.latitude;
//                            var long = obj.point.longitude;
//
//                            coordsList.push(lat, long);
//
//                        });
////                            if (arg instanceof Object) {
////                                Object.keys(arg).forEach(function(key) {
//////                                    if(typeof arg.name !== 'undefined') {
//////                                        $('.result').append('<h3>' + arg.name + '</h3>');
//////                                        $('.result').append('<p>');
//////                                    }
////                                    
////
////                                        doIt(arg[key]);
////                                    
////                                    
//////                                    console.log(arg.name + ', ' + arg.county);
//////                                    if(typeof arg !== 'undefined') {
//////                                        outputResults(arg[key]);
//////                                    }
//////                                    else {
//////                                        $('.result').html('<h3>' + arg.name + '</h3>');
//////                                        if(arg.address !== null) {
//////                                            $('.result').append('<p>' + arg.address + '</p> ');
//////                                            $('.result').append(arg.county + ', ');
//////                                            $('.result').append(arg.postCode + '</p>');
//////                                        }
//////                                    }
//////                                    console.log(arg);
////                                    
////                                });     
////                            }
////                            else if (typeof arg === "string") {
////                                count++;
////                                console.log(count);
////                                console.log(arg);
////                                $('.result').append('<p>' + arg + ', ');
////                                
////                            }
////                            
////                        }
////                        
////                        
////                        function doIt(heroInfo) {
////                            if (typeof heroInfo === "string") {
////
////                                    console.log(heroInfo);
////                                    $('.result').append('<p>' + heroInfo + '</p>');
////
////                            }
////                         }
//                        
//                        
//                        
//                     });
//                     
//                     
//                     
//
//                }
                
            //});
        </script>
        
    </body>
</html>