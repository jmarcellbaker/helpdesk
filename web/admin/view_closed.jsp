<%-- 
    Document   : view_closed
    Created on : Jul 8, 2017, 7:36:32 PM
    Author     : jmarc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Pathway</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<link rel="stylesheet" href="styles/main.css" type="text/css"> 
<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {font-size:16px;}
.w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
.w3-half img:hover{opacity:1}
</style>
<body>

<!-- Side menu -->
<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container">
    <h3 class="w3-padding-64"><b>PATHWAY<br></b></h3>
  </div>
  <div class="w3-bar-block">
    <a href="./index.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Logout</a> 
    <a href="./RequestServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">View Open Requests</a>
    <a href="./RequestServlet?action=view_closed" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">View Closed Requests</a>
    <a href="./admin/create_tech.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">New Tech</a>
    <a href="./newTech?action=view_techs" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Manage Tech Account</a>
    <a href="./admin/create_employee.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">New Employee</a>
    <a href="./newEmployee?action=view_employees" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Manage Employee Account</a>
  </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span>PATHWAY</span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:340px;margin-right:40px;padding-bottom:100px">
    
<!-- Header -->
<!-- <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b></b></h1>
</div> -->
 
<div class="w3-container" style="margin-top:75px">
<h1 class="w3-xxxlarge w3-text-red"><b>Closed Service Requests</b></h1>
  <table class="w3-table-all w3-hoverable">
  <tr>
    <th>Request ID</th>
    <th>Status</th>
    <th>Requested By</th>
    <th>Contact Info</th>
    <th>Request Date</th>
    <th>Description</th>
    <th>Technician</th>
    <th>Completion Date</th>
    <th>Notes</th>
  </tr>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:forEach var="request" items="${serviceRequests}">
  <tr>
    <td>${request.requestId}</td>
    <td>${request.requestStatus}</td>
    <td>${request.requestedBy}</td>
    <td>${request.contactInfo}</td>
    <td>${request.requestDate}</td>
    <td>${request.description}</td>
    <td>${request.technician}</td>
    <td>${request.completionDate}</td>
    <td>${request.notes}</td>
  </tr>
  </c:forEach>
</table>
</div>

<p><a href=#>Refresh</a></p>

<!-- End page content -->
</div>

<!-- Footer -->
<div class="w3-light-grey w3-container w3-padding-32" style="height:100px;position:absolute;bottom:0"><p class="w3-right">Pathway Help Desk LLC</p></div>

<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}
</script>

    </body>
</html>