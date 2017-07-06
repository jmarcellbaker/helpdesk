<%-- 
    Document   : index
    Created on : Jun 28, 2017, 8:31:47 PM
    Author     : jmarc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pathway</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <div id="wrapper">
    <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
        <img src="images/pathway.PNG" alt="Pathway Logo" width="300px" height="300px">
        <h3 class="w3-bar-item">Menu</h3>
        <ul>
            <li><a href="index.jsp" class="w3-bar-item w3-button">HOME</a></li>
            <li><a href="open_requests.jsp" class="w3-bar-item w3-button">TECHNICIAN LOGIN</a></li>
            <li><a href="admin.jsp" class="w3-bar-item w3-button">ADMIN LOGIN</a></li>
        </ul>
    </div>
        
    <!--Content-->
    <div style="margin-left:25%">
        
        
    <div class="w3-container w3-teal">
        
    </div>
        <p class="error">${message}</p>
        <form action="createRequest" method="POST">
            <h3>Service Request</h3><br>
            <label>Name:</label><br>
            <input type="text" name="requestedBy" value="${requestedBy}"><br>
            <label>Phone or Email:</label><br>
            <input type="text" name="contactInfo" value="${contactInfo}"><br>
            <label>Service Description</label><br>
            <input type="text" name="description" value="${description}"><br>
            <input type="submit" value="Submit">
        </form>
    </main>
    </div>
</html>
