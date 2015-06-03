<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
			 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- //TODO ORDER LIST-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="../resources/css/ufd-base.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/plain.css" rel="stylesheet" type="text/css" />

<script src="<c:url value="../resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="../resources/js/jquery-ui.js" />"></script>
<script src="<c:url value="../resources/js/jquery.ui.ufd.js" />"></script>
<script src="<c:url value="../resources/js/pager.js" />"></script>

<title>Administrative Tools</title>
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

<script type="text/javascript">

function Pager(tableName, itemsPerPage) {
this.tableName = tableName;
this.itemsPerPage = itemsPerPage;
this.currentPage = 1;
this.pages = 0;
this.inited = false;

this.showRecords = function(from, to) {
var rows = document.getElementById(tableName).rows;
// i starts from 1 to skip table header row
for (var i = 1; i < rows.length; i++) {
if (i < from || i > to)
rows[i].style.display = 'none';
else
rows[i].style.display = '';
}
}

this.showPage = function(pageNumber) {
if (! this.inited) {
alert("not inited");
return;
}

var oldPageAnchor = document.getElementById('pg'+tableName+this.currentPage);
oldPageAnchor.className = 'pg-normal';
this.currentPage = pageNumber;
var newPageAnchor = document.getElementById('pg'+tableName+this.currentPage);
newPageAnchor.className = 'pg-selected';
var from = (pageNumber - 1) * itemsPerPage + 1;
var to = from + itemsPerPage - 1;
this.showRecords(from, to);
}

this.prev = function() {
if (this.currentPage > 1)
this.showPage(this.currentPage - 1);
}

this.next = function() {
if (this.currentPage < this.pages) {
this.showPage(this.currentPage + 1);
}
}

this.init = function() {
var rows = document.getElementById(tableName).rows;
var records = (rows.length - 1);
this.pages = Math.ceil(records / itemsPerPage);
this.inited = true;
}

this.showPageNav = function(pagerName, positionId) {
if (! this.inited) {
alert("not inited");
return;
}

var element = document.getElementById(positionId);
var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> « Prev </span> ';
for (var page = 1; page <= this.pages; page++)
pagerHtml += '<span id="pg' + tableName + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="' + pagerName + '.next();" class="pg-normal"> Next »</span>';
element.innerHTML = pagerHtml;
}
}
</script>

</head>

<body>
	
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate var="current_year" value="${now}" pattern="yyyy" />
	
	<h1 align = "center">ADMINISTRATIVE TOOLS</h1>
	
	<div id="sidebar">
		<p><a href="<c:url value="/index"/>">Home</a></p>		
		<p><a href="<c:url value="/managerpage"/>">Switch to orders management</a></p>
		<hr>
	<dl class="tabs vertical">
  		<dd class="active"><a href="#users">Users</a></dd>
  		<dd><a href="#user_auths">User Authorizations</a></dd>
  		<dd><a href="#clients">Clients</a></dd>
  		<dd><a href="#machines">Machines</a></dd>
  		<dd><a href="#machinesServiceables">Machine Serviceable</a></dd>
  		<dd><a href="#orders">Orders</a></dd>  		
		<dd><a href="#repair_types">Repair Types</a></dd>  		
	</dl>
		<hr>
		
		<p><a href="<c:url value="/logout"/>">Log out</a></p>
	</div>
	
	<div id="content">
	<div class="tabs-content">
	<div class="content active" id="users">
  	<h1>Users</h1>
  	<table border="1" style="width:900px" id="tablepaging4" class="yui" align="center">
	<tr><th align="center"></th><th align="center">Login:</th>
	<th align="center">Password:</th></tr>
  	<c:forEach var="u" items="${users}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${u.login}</td> 
    	<td>${u.password}</td>
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition4" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager4 = new Pager('tablepaging4', 5);
		pager4.init();
		pager4.showPageNav('pager4', 'pageNavPosition4');
		pager4.showPage(1);
	</script>
  	
  	<h2>Add New User</h2>
  	<form:form method="post" commandName="user" action="addUser">
  	<table>
  		<tr>
  			<td><label for="loginInput">Login: </label></td>
  			<td><form:input path="login" id="loginInput" maxlength="50"/></td>
  			<td><form:errors path="login" cssClass="error" /></td>  			
  		</tr>
  		<tr>
  			<td><label for="passwordTextInput">Password: </label></td>
  			<td><form:input path="passwordText" id="passwordTextInput" maxlength="50"/></td>
  			<td><form:errors path="passwordText" cssClass="error" /></td>  			
  		</tr>
  		<tr>
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  	</form:form>
  	</div>
  	
  	<br><hr>
  	
  	<div class="content" id="user_auths">
  	<h1>User Authorizations</h1>
  	<table border="1" style="width:900px" id="tablepaging5" class="yui" align="center">
	<tr><th align="center"></th><th align="center">Login:</th>
	<th align="center">Role:</th></tr>
  	<c:forEach var="ua" items="${user_authorizations}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${ua.user.login}</td> 
    	<td>
    		<c:choose>
  				<c:when test="${ua.role == 'ROLE_ADMIN'}">
  					<c:out value="Administrator"/>  					
  				</c:when>
  				<c:when test="${ua.role == 'ROLE_MANAGER'}">
  					<c:out value="Manager"/>  					
  				</c:when>
  				<c:otherwise>
  					<c:out value="Client"/>  					
  				</c:otherwise>
  			</c:choose>
    	</td>    	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition5" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager5 = new Pager('tablepaging5', 5);
		pager5.init();
		pager5.showPageNav('pager5', 'pageNavPosition5');
		pager5.showPage(1);
	</script>
  	
  	<h2>Add New User Authorization</h2>
  	<form:form method="post" commandName="userAuthorization" action="addUserAuthorization">
  	<table>
  		<tr>
  		<td>User: </td>  		
  		<td><select name="userId">
  			<option value="0"><c:out value="-Select user-" /></option>
  			<c:forEach var="u" items="${users}">
  				<c:choose>
  					<c:when test="${selected_user_authorization_user_id == u.userId}">
  						<option selected value="${u.userId}">
  							<c:out value="${u.login}"/>
  						</option>
  					</c:when>
  					<c:otherwise>
  						<option value="${u.userId}">
  							<c:out value="${u.login}"/>
  						</option>
  					</c:otherwise>
  				</c:choose>  				
  			</c:forEach>
  		</select></td>
  		<td>
  			<div class="error">
  				<c:out value="${message_user_authorization_user_id}"/>
  			</div>
  		</td>
  		</tr>
  		<tr>  		
  		<td>Role: </td>
  		<td><form:select path="role">
  			<form:option value=""><c:out value="-Select role-" /></form:option>
  			<c:forEach var="r" items="${user_roles}">
  				<form:option value="${r}">
  					<c:out value="${r}"/></form:option>
  			</c:forEach>
  		</form:select></td>
  		<td><form:errors path="role" cssClass="error" /></td>
  		</tr>
  		<tr>  		  
  		<td><button>Add</button></td>
  		</tr>
  	</table>
  	</form:form>
  	</div>
  	
  	<br><hr>
  	
  	<div class="content" id="clients">  
  	<h1>Clients</h1>
  	<table border="1" style="width:900px" id="tablepaging6" class="yui" align="center">
	<tr><th align="center"></th><th align="center">Name:</th>
	<th align="center">Login:</th></tr>
  	<c:forEach var="c" items="${clients}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${c.clientName}</td> 
    	<td>${c.clientUser.login}</td>    	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition6" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager6 = new Pager('tablepaging6', 5);
		pager6.init();
		pager6.showPageNav('pager6', 'pageNavPosition6');
		pager6.showPage(1);
	</script>
  
  	<h2>Add New Client</h2>
  	<form:form method="post" commandName="client" action="addClient">
  	<table>
  		<tr>
  			<td><label for="clientNameInput">Name: </label></td>
  			<td><form:input path="clientName" id="clientNameInput" maxlength="50"/></td>
  			<td><form:errors path="clientName" cssClass="error" /></td>  			
  		</tr>
  		<tr>
  			<td>User:</td>
  			<td><select name="userId">
  				<option value="0"><c:out value="-Select user-" /></option>
  				<c:forEach var="u" items="${users}">
  					<c:choose>
  					<c:when test="${selected_client_user_id == u.userId}">
  						<option selected value="${u.userId}">
  							<c:out value="${u.login}"/>
  						</option>
  					</c:when>
  					<c:otherwise>
  						<option value="${u.userId}">
  							<c:out value="${u.login}"/>
  						</option>
  					</c:otherwise>
  					</c:choose>  					
  				</c:forEach>
  			</select></td>
  			<td>
  			<div class="error">
  				<c:out value="${message_client_user_id}"/>
  			</div>
  			</td>
  		</tr>
  		<tr>  		
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  	</form:form>
  	</div>  
  	
  	











  	<div class="content" id="machines">  
  	<h1>Machines</h1>
  	<table border="1" style="width:900px" id="tablepaging7" class="yui" align="center">
	<tr><th align="center"></th><th align="center">MachineServiceable ID:</th>
	<th align="center">Serial Number:</th><th align="center">Times Repaired:</th>
	<th align="center">Year:</th></tr>
  	<c:forEach var="c" items="${machines}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${c.ms_id}</td> <td>${c.serial_number}</td>
    	<td>${c.times_repaired}</td> 
    	<td>${c.year}</td>    	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition7" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager7 = new Pager('tablepaging7', 5);
		pager7.init();
		pager7.showPageNav('pager7', 'pageNavPosition7');
		pager7.showPage(1);
	</script>
  
  	
  	
  	
  	
  	

 	
  	
  	<h2>Add New Machine</h2>
  	<form method="get" action="adminpage/submitm">
  <table>
  		<tr>
  			<td>Serial Number:</td>
  			<td><input type="text" name="mserial_number" /></td><td><div class="error">
	<c:out value="${mmessageserialnumber}"/>
	</div></td></tr>
  		<tr>
  			<td>Year:</td>
  			<td><input type="text" name="myear" /></td><td><div class="error">
	<c:out value="${mmessageyear}"/>
	</div></td></tr>
  			<tr>
  			<td>Machine Serviceable:</td>
  			<td><select name="mms">
  			<c:forEach var="ms" items="${machinesServiceables}"  varStatus="loopStatus">  
 <option value="${ms.ms_id}">${ms}</option>
 </c:forEach>
</select></td><td><div class="error">
	<c:out value="${mmessagems}"/>
	</div></td>
  		</tr>
  		
  			
  		
  		<tr>	
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  </form>
  	





  
  	
  	
  	<div class="content" id="machinesServiceables">  
  	<h1>Machines Serviceable</h1>
  	<table border="1" style="width:900px" id="tablepaging8" class="yui" align="center">
	<tr><th align="center"></th><th align="center">ID:</th>
	<th align="center">Country:</th><th align="center">Client ID:</th>
	<th align="center">Name:</th><th align="center">Trademark:</th></tr>
  	<c:forEach var="c" items="${machinesServiceables}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${c.ms_id}</td> <td>${c.country}</td>
    	<td>${c.clientid}</td>
    	<td>${c.name}</td>
    	<td>${c.trademark}</td>   
    	 	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition8" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager8 = new Pager('tablepaging8', 5);
		pager8.init();
		pager8.showPageNav('pager8', 'pageNavPosition8');
		pager8.showPage(1);
	</script>
  
  	
  	
  	
  	
  	<h2>Add New Machine Serviceable</h2>
  	<form method="get" action="adminpage/submitms">
  <table>
  		<tr>
  			<td>Country:</td>
  			<td><input type="text" name="mscountry" /></td><td><div class="error">
	<c:out value="${msmessagecountry}"/>
	</div></td></tr>
  		<tr>
  			<td>Name:</td>
  			<td><input type="text" name="msname" /></td><td><div class="error">
	<c:out value="${msmessagename}"/>
	</div></td></tr>
  			<tr>
  			<td>Trademark:</td>
  			<td><input type="text" name="mstrademark" /></td><td><div class="error">
	<c:out value="${msmessagetrademark}"/>
	</div></td></tr>
  			<tr>
  			<td>Client:</td>
  			<td><select name="msclient">
  			<c:forEach var="ms" items="${clients}"  varStatus="loopStatus">  
 <option value="${ms.clientId}">${ms.clientName}</option>
 </c:forEach>
</select></td><td><div class="error">
	<c:out value="${mmessageclient}"/>
	</div></td>
  		</tr>
  		
  			
  		
  		<tr>	
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  </form>
  	
  	
  
  
  
  
  
  	
  	<div class="content" id="orders">  
  	<h1>Orders</h1>
  	<table border="1" style="width:900px" id="tablepaging9" class="yui" align="center">
	<tr><th align="center"></th><th align="center">ID:</th>
	<th align="center">Start:</th><th align="center">Status:</th>
	<th align="center">Type ID:</th><th align="center">Clients ID:</th>
	<th align="center">Machine ID:</th></tr>
  	<c:forEach var="c" items="${orders}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${c.order_id}</td>
    	 <td>${c.start}</td>
    	<td>${c.status}</td>
    	<td>${c.type_id}</td>
    	<td>${c.clients_id}</td>   
    	<td>${c.machine_id}</td> 	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition9" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager9 = new Pager('tablepaging9', 5);
		pager9.init();
		pager9.showPageNav('pager9', 'pageNavPosition9');
		pager9.showPage(1);
	</script>
  
  
  
  
  
  
  	<h2>Add New Order</h2>
  	<form method="get" action="adminpage/submitorder">
  <table>
   		<tr>
  			<td>Status:</td>
  			<td><input type="text" name="ostatus" /></td><td><div class="error">
	<c:out value="${omessagestatus}"/>
	</div></td></tr>
  			<tr>
  			<td>Machine:</td>
  			<td><select name="omachine">
  			<c:forEach var="ms" items="${machines}"  varStatus="loopStatus">  
 <option value="${ms.id}">${ms}</option>
 </c:forEach>
</select></td><td><div class="error">
	<c:out value="${omessagemachine}"/>
	</div></td>
  		</tr>
  		<tr>
  			<td>Client:</td>
  			<td><select name="oclient">
  			<c:forEach var="ms" items="${clients}"  varStatus="loopStatus">  
 <option value="${ms.clientId}">${ms.clientName}</option>
 </c:forEach>
</select></td><td><div class="error">
	<c:out value="${omessageclient}"/>
	</div></td>
  		</tr>
  		<tr>
  			<td>Type:</td>
  			<td><select name="otype">
  			<c:forEach var="ms" items="${repairTypes}"  varStatus="loopStatus">  
 <option value="${ms.type_id}">${ms.name}</option>
 </c:forEach>
</select></td><td><div class="error">
	<c:out value="${omessagetype}"/>
	</div></td>
  		</tr>
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  </form>
  	
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  	<div class="content" id="repair_types">  
  	<h1>Repair Types</h1>
  	<table border="1" style="width:900px" id="tablepaging10" class="yui" align="center">
	<tr><th align="center"></th><th align="center">ID:</th>
	<th align="center">Name:</th><th align="center">Price:</th>
	<th align="center">Duration:</th></tr>
  	<c:forEach var="c" items="${repairTypes}" varStatus="loopStatus">    	
    <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
    	<td><c:out value="${loopStatus.index + 1}"/></td>
    	<td>${c.type_id}</td>
    	 <td>${c.name}</td>
    	<td>${c.price}</td>
    	<td>${c.duration}</td> 	    	   	
    </tr>
  	</c:forEach>
  	</table>
  	<div id="pageNavPosition10" style="padding-top: 20px" align="center">
	</div>
	<script type="text/javascript"><!--
		var pager10 = new Pager('tablepaging10', 5);
		pager10.init();
		pager10.showPageNav('pager10', 'pageNavPosition10');
		pager10.showPage(1);
	</script>
  
  
  	<h2>Add New Repair Type</h2>
  	<form method="get" action="adminpage/submittype">
  <table>
  		<tr>
  			<td>Name:</td>
  			<td><input type="text" name="rtname" /></td><td><div class="error">
	<c:out value="${rtmessagename}"/>
	</div></td></tr>
  			<tr><td>Price (hrn):</td><td><input type="text" name="rtprice" /></td><td><div class="error">
	<c:out value="${rtmessageprice}"/>
	</div></td></tr>
  			<tr><td>Duration (hours):</td><td><input type="text" name="rtduration" /></td><td><div class="error">
	<c:out value="${rtmessageduration}"/>
	</div></td>
  		</tr>
  			<td><button>Add</button></td>
  		</tr>
  	</table>
  </form>
  	</div>
  	





















  
  	
  		
  	</div>
  	</div>
</body>
</html>