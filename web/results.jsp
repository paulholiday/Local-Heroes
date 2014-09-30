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
        <script type="text/javascript" src="resources/js/refineSearch.js" ></script>
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
            <div class="leftResultsTab">
                <div class="scrollableResults">
                    ${pageContext.getAttribute("Heroes")}
                </div>
            </div>
            <div class="tags">
                <h3>Refine your search by selecting tags:</h3><br>
                ${pageContext.getAttribute("Tags")}
                <br><br>
                <span class="clearButton">Clear Search</span>
            </div>
        </div>
        
    </body>
</html>