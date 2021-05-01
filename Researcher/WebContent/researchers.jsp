<%@page import="model.Researchers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
			pageEncoding="ISO-8859-1"%>
<%
	//Insert researcher---------------------------------
	if (request.getParameter("rName") != null)
	{
		Researchers researchObj = new Researchers();
		
		String stsMsg = researchObj.insertResearcher(request.getParameter("rName"), request.getParameter("rPhone"),
						request.getParameter("rEmail"), request.getParameter("rAddress"), 
						request.getParameter("projectName"), request.getParameter("rCost"));
						
		session.setAttribute("statusMsg", stsMsg);
	}

	//Delete researcher----------------------------------
	if (request.getParameter("RID") != null) 
	{
		Researchers researchObj = new Researchers();
		String stsMsg = researchObj.deleteResearcher(request.getParameter("RID")); 
		
		session.setAttribute("statusMsg", stsMsg);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">

<title>Researchers Management</title> 
</head>
<body>

	<h1>Items Management</h1>
	
	<form method="post" action="Researchers.jsp">
		Researcher Name : <input name="rName" type="text"><br>
		Researcher Contact Number : <input name="rPhone" type="text"><br>
		Researcher Email : <input name="rEmail" type="text"><br> 
		Researcher Address : <input name="rAddress" type="text"><br>
		Researcher Project Name : <input name="projectName" type="text"><br>
		Researcher Cost : <input name="rCost" type="text"><br>
	 	<input name="btnSubmit" type="submit" value="Save">
	 
	 </form>
	 <%
	 	out.print(session.getAttribute("statusMsg"));
	 %>
	 <% 
	 	Researchers researchObj = new Researchers();
	 	out.print(researchObj.readResearcher());
	 %>
</body>
</html>