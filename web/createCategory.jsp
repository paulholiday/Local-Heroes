<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="hero" uri="WEB-INF/tlds/Hero.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="resources/css/style.css">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <title>Admin - Local Heroes - Create a category</title>
    </head>
    
    <body>
        <div class="topBar">
            <a href="/LocalHeroesProject"><h1>Local Heroes</h1></a>
        </div>
        <div class="body">
            <h1>Welcome ${requestScope["username"]}</h1>
            
            <p>Add a Category: </p>
            <form class="addCategoryForm" action="AddCategory" method="POST">
                <label>Category name: </label><input type="text" name="Name"/><br>
                
                <input id="submit" type="submit" value="Add Category">
            </form>
        </div>
        
    </body>
</html>
