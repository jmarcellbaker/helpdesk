<%-- 
    Document   : tech_addnotes
    Created on : Jul 7, 2017, 9:42:41 PM
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
    <a href="index.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Logout</a> 
    <a href="requestAdmin" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Open Requests</a> 
    <a href="TechRequestServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">My Assigned Requests</a>
    <a href="TechRequestServlet?action=closed_requests&amp;techName=${tech.firstName} ${tech.lastName}" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">My Closed Requests</a>
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
<div class="w3-main" style="margin-left:340px;margin-right:40px">

  <!-- Header -->
  <!-- <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b></b></h1>
  </div> -->
  
  <!-- Assign Ticket -->
  <div class="w3-container" id="contact" style="margin-top:75px">
      
    <h1 class="w3-xxxlarge w3-text-red"><b>Add Notes</b></h1>
    <br>
    <p>Add Notes and Submit</p>
        <form action="requestAdmin" method="POST">
        <input type="hidden" name="action" value="notes_request">
        <div class="w3-section">
        <label>Request ID:</label>
        <input type="text" name="requestId" value="${serviceRequest.requestId}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Request Status:</label>
        <input type="text" name="requestStatus" value="${serviceRequest.requestStatus}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Requested By:</label>
        <input type="text" name="requestedBy" value="${serviceRequest.requestedBy}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Contact Info:</label>
        <input type="text" name="contactInfo" value="${serviceRequest.contactInfo}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Request Date:</label>
        <input type="text" name="requestDate" value="${serviceRequest.requestDate}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Description:</label>
        <input type="text" name="description" value="${serviceRequest.description}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Technician:</label>
        <input type="text" name="technician" value="${tech.firstName} ${tech.lastName}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Completion Date:</label>
        <input type="text" name="completionDate" value="${serviceRequest.completionDate}" readonly><br>
        </div>
        <div class="w3-section">
        <label>Notes:</label>
        <input type="text" name="notes" value="${serviceRequest.notes}"><br>
        </div>
        <label>&nbsp;</label>
        <input type="submit" value="Save Notes" class="w3-button w3-block w3-padding-large w3-red w3-margin-bottom">
    </form>
        
     <!-- End page content -->
</div>

<!-- Footer -->
<div class="w3-light-grey w3-container w3-padding-32" style="margin-top:95px;padding-right:58px"><p class="w3-right">Pathway Help Desk LLC</p></div>

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
