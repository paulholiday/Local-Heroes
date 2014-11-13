<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="hero" uri="WEB-INF/tlds/Hero.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="resources/css/style.css">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <title>Admin - Local Heroes</title>
    </head>
    
    <body>
        <div class="topBar">
            <a href="/LocalHeroesProject"><h1>Local Heroes</h1></a>
        </div>
        <div class="body">
            <h1>Welcome ${requestScope["username"]}</h1>
            
            <p>Add a Hero: </p>
            <form class="addHeroForm" action="AddHero" method="POST">
                <select id="category"  name="Category">
                    <option>Please select a category to add a hero to:</option>
                    <hero:getcategories/>
                    <c:forEach var="category" items="${categories}">
                        <option><c:out value="${category.name}"/></option>
                    </c:forEach>
                </select>
                <br><br>
                <label>Hero name: </label><input type="text" name="Name"/><br>
                <label>Tags: </label><input type="text" name="Tags" /><br><br>
                
                <p>Address</p>
                <label>Line1: </label><input type="text" name="Line1" /><br>
                <label>County: </label><input type="text" name="County" /><br>
                <label>Post Code: </label><input type="text" name="PostCode" /><br><br>
                
                <p>Location</p>
                <label>Longitude: </label><input type="text" name="Longitude" /><br>
                <label>Latitude: </label><input type="text" name="Latitude" /><br>
                
                <input id="submit" type="submit" value="Add Hero">
            </form>
        </div>
        
    </body>
</html>
