<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>

<body>
<h2>Please enter eithe usr name</h2>

<form:form method="POST" commandName="UserInput">
<table>
<tr>
<td>UserName : </td>
<td><form:input path="userName" /></td>
</tr>
<tr>
<td colspan="3"><input type="submit" /></td>
</tr>
</table>
</form:form>

</body>
</html>