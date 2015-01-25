<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style>
table { font-size: 60%;}
</style>
<title>Buyer invoice List page</title>
</head>
<body>
<h1>Buyer invoice List page</h1>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="150px">Name</th>
<th width="120px">ID</th>
<th width="150px">Invoice number</th>
<th width="120px">Invoice date</th>
<th width="120px">Invoice due date</th>
<th width="120px">Invoice amount</th>
<th width="120px">invoice free text</th>
<!-- <th width="130px">actions</th> -->
</tr>
</thead>
<tbody>
<c:forEach var="buyerInvoice" items="${buyerInvoiceList}">
<tr>
<td>${buyerInvoice.buyerName}</td>
<td>${buyerInvoice.buyerIdentifier}</td>
<td>${buyerInvoice.invoiceNumber}</td>
<td>${buyerInvoice.invoiceDate}</td>
<td>${buyerInvoice.invoiceDueDate}</td>
<td>${buyerInvoice.invoiceAmount}</td>
<td>${buyerInvoice.invoiceFreeText}</td>
<!-- <td> -->
<%-- <a href="${pageContext.request.contextPath}/buyer/edit/${buyer.id}">Edit</a><br/> --%>
<%-- <a href="${pageContext.request.contextPath}/buyer/delete/${buyer.id}">Delete</a><br/> --%>
<!-- </td> -->
</tr>
</c:forEach>
</tbody>
</table>
<br/>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>