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


<div id="wrapper" style="max-width:25%;margin:0 auto; padding: 20px;">

<h3>Add a photo:</h3>

<form class="form-horizontal" action="upload" method="post" enctype="multipart/form-data">
	<fieldset>
	<label>Taken by:</label>
	<input type="text" name="name" value="name">
	<br/>
	<!-- automatically sets date to current date and time -->
	
	<label>Photo:</label>
     <input style="margin-left: 5px;" type="file" name="file" size="50">
	<br/>
	<button style="margin-left: 5em;"type="submit" class="btn">Submit</button>
	<br/>
	</fieldset>
</form>

</div>

</body>
</html>