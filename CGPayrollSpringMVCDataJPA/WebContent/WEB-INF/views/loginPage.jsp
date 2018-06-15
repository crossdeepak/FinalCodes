<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<f:form action="loginUser" method="post" modelAttribute="associate">
		<table>
			<tr>
				<td>Enter Associate ID :</td>
				<td><f:input path="associateID" size="20" /></td>
				<td><f:errors path="associateID" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><f:password path="password" size="20" /></td>
				<td><f:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Click Here"></td>
			</tr>
		</table>
	</f:form>

	<div>
		<font color='red'>
			<c:if test="${not empty exceptionMsg}">
				${exceptionMsg}
			</c:if> 
			
			<c:if test="${not empty errorMsg}">
				${errorMsg}
			</c:if> 
		</font>
	</div>
</body>
</html>