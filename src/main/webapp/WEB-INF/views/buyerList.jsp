<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<style>
table {font-size: 60%;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Buyer List page</title>
</head>
<body>
<h1>Buyer List page</h1>
<table style="text-align: center;" font-size=10% border="1px"  cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="200px">Name</th>
<th width="120px">ID</th>
<th width="150px">Street name</th>
<th width="120px">Post name</th>
<th width="120px">Post code</th>
<th width="120px">Number of bill</th>
<th width="130px">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="buyer" items="${buyerList}">
<tr>
<td>${buyer.name}</td>
<td>${buyer.identifier}</td>
<td>${buyer.street}</td>
<td>${buyer.town}</td>
<td>${buyer.postCode}</td>
<td><a href="${pageContext.request.contextPath}/buyer/buyerInvoices/${buyer.id}">Number of ${buyer.numberOfInvoice}</a></td>
<td>
<%-- <a href="${pageContext.request.contextPath}/buyer/edit/${buyer.id}">Edit</a><br/> --%>
<a href="${pageContext.request.contextPath}/buyer/delete/${buyer.id}">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
<br/>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>