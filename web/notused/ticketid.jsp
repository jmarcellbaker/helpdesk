<%-- 
    Document   : ticketid
    Created on : Jul 1, 2017, 12:30:44 AM
    Author     : jmarc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pathway</title>
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <div class="logo">
        <img src="images/pathway.PNG" alt="Pathway Logo">
    </div>
    <div class="sidemenu">
        <ul>
            <li><a href="index.jsp">HOME</a></li>
            <li><a href="open_requests.jsp">TECHNICIAN LOGIN</a></li>
            <li><a href="admin.jsp">ADMIN LOGIN</a></li>
        </ul>
    </div>
    <div class="body">
        <h1>A Technician will be assigned to your ticket shortly!</h1>
        <br>
        <div class="center">
            <label class="successLabel">Your Name:</label>
            <span>${serviceRequest.requestedBy}</span><br>
            <label class="successLabel">Contact Info:</label>
            <span>${serviceRequest.contactInfo}</span><br>
            <label class="successLabel">Issue:</label>
            <span>${serviceRequest.description}</span><br>
            <label class="successLabel">Ticket Number:</label>
            <span>${serviceRequest.requestId}</span><br>
        </div>
</html>
