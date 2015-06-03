<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MachineRepair View Orders</title>
<style type="text/css">
	</style>
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

tr.odd {background-color: #EEDDEE}

tr.even {background-color: #EEEEDD}

.pg-normal {
color: #000000;
font-size: 15px;
cursor: pointer;
background: #D0B389;
padding: 2px 4px 2px 4px;
}

.pg-selected {
color: #fff;
font-size: 15px;
background: #000000;
padding: 2px 4px 2px 4px;
}

table.yui {
font-family:arial;
border-collapse:collapse;
border: solid 3px #7f7f7f;
font-size:small;
}

table.yui td {
padding: 5px;
border-right: solid 1px #7f7f7f;
}

table.yui.even {
background-color: #EEE8AC;
}

table.yui.odd {
background-color: #F9FAD0;
}

table.yui th {
border: 1px solid #7f7f7f;
padding: 5px;
height: auto;
background: #D0B389;
}

table.yui th a {
text-decoration: none;
text-align: center;
padding-right: 20px;
font-weight:bold;
white-space:nowrap;
}

table.yui tfoot td {
border-top: 1px solid #7f7f7f;
background-color:#E1ECF9;
}

table.yui thead td {
vertical-align:middle;
background-color:#E1ECF9;
border:none;
}

table.yui thead.tableHeader {
font-size:larger;
font-weight:bold;
}

table.yui thead.filter {
text-align:right;
}

table.yui tfoot {
background-color:#E1ECF9;
text-align:center;
}

table.yui.tablesorterPager {
padding: 10px 0 10px 0;
}

table.yui.tablesorterPager span {
padding: 0 5px 0 5px;
}

table.yui.tablesorterPager input.prev {
width: auto;
margin-right: 10px;
}

table.yui.tablesorterPager input.next {
width: auto;
margin-left: 10px;
}

table.yui.pagedisplay {
font-size:10pt; 
width: 30px;
border: 0px;
background-color: #E1ECF9;
text-align:center;
vertical-align:top;
}

#sidebar {
  position: fixed;
  top: 40px;
  left: 40px;
  width: 200px;
  border-right:2px solid #aaa;
}
 
#content {
  margin: 0 40px 40px 280px;
}
</style>
</head>
<body>
<title>View orders history</title>
</head>
<body>
<p><a href="<c:url value="/clientpage"/>">Home</a><br><br>
<br>
<H1>ORDERS HISTORY:</H1>
<table border="1" style="width:900px" id="tablepaging4" class="yui" align="center">
<tr><th>NUMBER</th> <th>DATE</th><th>NAME</th> <th>PRICE($)</th> <th>DURATION(Hrs)</th> <th>STATUS</th>
</tr>
<c:forEach var="ms" items="${orderMachineRepair}"  varStatus="loopStatus">  
 <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	  <td>${ms.start}</td>  <td> ${ms.name}</td> 
    	  <td> ${ms.price}</td> <td> ${ms.duration}</td> <td> ${ms.status}</td></tr>  	
    </c:forEach>
    </table>

</body>
</html>