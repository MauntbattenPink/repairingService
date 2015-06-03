<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MachineRepair Add new Machine Page</title>

<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}

h3.left {
    position: absolute;
    left: 10px;
    top: 0px;
}

h3.right {
    position: absolute;
    right: 10px;
    top: 0px;
}
</style>
</head>
<body>
<title>Add new Machine</title>
</head>
<body>
<p><a href="<c:url value="/clientpage"/>">Home</a><br><br>
		<form method="get" action="addmachine/submitmachine">
  <table>
  		<tr>
  			<td>Name:*</td>
  			<td><input type="text" name="name" /></td>
  		</tr>
  		<tr>
  			<td>Trademark:*</td>
  			<td><input type="text" name="trademark"/></td>
  		</tr>
  		<tr>
  			<td>Country:*</td>
  			<td><input type="text" name="country" /></td>
  		</tr>
  		
  		<tr>
  			<td><button>Submit</button></td>
  		</tr>
  	</table>
  	<div class="error">
	<c:out value="${message}"/>
	</div>
	
		</form>


</body>
</html>