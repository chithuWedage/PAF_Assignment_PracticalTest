<%@page import="model.Researchers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
			pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Component/jquery-3.4.0.min.js"></script> 
<script src="Component/researchers.js"></script>
<meta charset="ISO-8859-1">

<title>Researcher Management</title> 
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
	
	<h1><b> Researcher Management </b></h1>
	
	<form id="formResearcher" name="formResearcher" >
		Researcher Name : <span style="color: red;">*</span>
		<input id="rName" name="rName" type="text" class="form-control form-control-sm"><br>
		
		<br>Researcher Contact Number : <span style="color: red;">*</span>
		<input id="rPhone" name="rPhone" type="text" class="form-control form-control-sm"><br>
		
		<br>Researcher Email : <span style="color: red;">*</span>
		<input id="rEmail" name="rEmail" type="text" class="form-control form-control-sm"><br> 
		
		<br>Researcher Address : 
		<input id="rAddress" name="rAddress" type="text" class="form-control form-control-sm"><br>
		
		
		<label > Project Name :<span style="color: red;">*</span></label> <select id="projectName"
			name="projectName" class="form-control">
			<option value="" selected>Choose...</option>
			<option value="ProjectA">ProjectA</option>
			<option value="ProjectAB">ProjectAB</option>
			<option value="ProjectABC">ProjectABC</option>
		</select>
		
		<br>Researcher Cost :
		<input id="rCost" name="rCost" type="text" class="form-control form-control-sm"><br>
	 	
	 	<br>
	 	<input id="btnSubmit" name="btnSubmit" type="submit" value="Save" 
	 				class="btn btn-primary">
	 	<input type="hidden" id="hidRIDSave" name="hidRIDSave" value="">
	 
	 </form>
	 
	 <div id="alertSuccess" class="alert alert-success"></div>
	 <div id="alertError" class="alert alert-danger"></div>
	 
	 <br>
	 <div id="divresearcherGrid">
		 <% 
		 	Researchers researcherObj = new Researchers();
		 	out.print(researcherObj.readResearcher());
		 %>
	 </div>
	 </div></div></div>
</body>
</html>