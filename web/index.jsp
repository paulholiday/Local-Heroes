<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="hero" uri="WEB-INF/tlds/Hero.tld"%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value='resources/css/style.css'/>">
        <title>Local Heroes</title>
    </head>

    <body>
        <div class="topBar">
            <a href=""><h1>Local Heroes</h1></a>
        </div>
        <div class="body">
            <p class="title">Choose a category you want to search for</p>
            <form id="search-form" action="Results" method="post">
                <select id="category"  name="Category">
                    <hero:getcategories/>
                    <c:forEach var="category" items="${categories}">
                        <option><c:out value="${category.name}"/></option>
                    </c:forEach>
                </select>
                <input id="submit" type="submit" value="Go">
            </form>
        </div>          
        <script type="text/javascript" src="resources/js/jquery-1.10.2.min.js" ></script>
        <script type="text/javascript" src="resources/js/localHeroes.js" ></script>
    </body>
</html>
