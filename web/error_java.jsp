<%-- 
    Document   : Error_java
    Created on : Mar 24, 2017, 9:00:55 PM
    Author     : Jeremy M. Baker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Error</title>
    </head>
    <body>
        <h1>Java Error</h1><br>
        <p>Sorry, Java has thrown an exception.</p><br>
        <p>To continue, click the Back button.</p><br>
        
        <h1>Details</h1><br>
        <p>Type: ${pageContext.exception["class"]}</p>
        <p>Message: ${pageContext.exception.message}</p>
        
    </body>
</html>
