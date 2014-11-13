<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="resources/css/style.css">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <script type="text/javascript" src="resources/js/jquery-1.10.2.min.js" ></script>
        <script type="text/javascript" src="resources/js/localHeroes.js" ></script>
        <title>Login - Local Heroes</title>
    </head>
    
    <body>
        <div class="topBar">
            <a href="/LocalHeroesProject"><h1>Local Heroes</h1></a>
        </div>
        <div class="body">
            
            <form action="Success" method="post">
                Username: <input type="text" name="username"> <br>
                Password: <input type="password" name="password"> <br>
                <input type="submit">
                
            </form>
        </div>
        
    </body>
</html>