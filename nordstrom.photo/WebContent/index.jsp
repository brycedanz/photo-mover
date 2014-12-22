<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nordstrom PhotoMagic</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body style="background-color: #D4EBF2">
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      
     
      <a class="navbar-brand" href="index.html">
        <i class="fa fa-camera" width="100" height="100" style="margin-right: 25px; margin-left: 15px;"></i>
        Nordstrom Photomover Demo
      </a>
      
    </div>
  </div>
</nav>

<div id="wrapper" style="max-width:75%;margin:0 auto; padding: 20px;">
<div style="padding: 15px;">
<a href="upload"><button type="button" class="btn btn-default"><i class="fa fa-upload" style="margin-right: 15px;"></i>Upload a photo</button></a>
<h3 style="margin-left:8em; display: inline;">Current Photos:</h3>
</div>

 <table style="background-color: #FFFFFF" class="table table-striped table-bordered table-condensed">
 <tr>
  <th>Uploaded By</th>
  <th>Date</th>
  <th>Preview</th>
  <th></th>
  <th></th>
  
  </tr>
  
  <c:forEach items="${photos}" var="current">
      <tr>
      <td><c:out value="${current.getCreatedBy()}" /></td>
      <td><c:out value="${current.getCreatedAt()}" /></td>
      <td><img style="max-width: 200px; max-height: 200px;" src="<c:out value="${current.getFilepath()}" />" /></td>
      <td>

      	<form action="delete" method="post">
      		<input type="hidden" name="filepath" value="<c:out value="${current.getFilepath()}" />">
      		<button type="submit" value="Delete" class="btn btn-default">Delete</button>
      	</form>
      </td>
      
      <td>
      	<form action="download" method="post">
      		<input type="hidden" name="filepath" value="<c:out value="${current.getFilepath()}" />">
			<button type="submit" value="Download" class="btn btn-default">Download</button>
      	</form>
      </td>
      
      
    </tr>
  </c:forEach>
</table>
</div>
</body>
</html>