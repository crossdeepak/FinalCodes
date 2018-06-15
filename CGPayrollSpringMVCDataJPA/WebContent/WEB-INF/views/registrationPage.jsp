<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
<head>
<title>Login</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<h2>Enroll Associate Details Here</h2>
		<table>
			<form:form action="registerUser" method="post" 
					modelAttribute="associate">
			
				<tr>
					<td>FirstName:</td>
					<td><form:input path="firstName" size="30" /></td>
					<td><form:errors path="firstName" cssClass="error" /></td>
				</tr>
				<tr>
					<td>LastName:</td>
					<td><form:input path="lastName" size="30" /></td>
					<td><form:errors path="lastName" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:input path="password" size="30" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td><form:input path="department" size="30" /></td>
					<td><form:errors path="department" cssClass="error" /></td>
				</tr>
				<tr>
					<td>EmailID:</td>
					<td><form:input path="emailId" size="30" /></td>
					<td><form:errors path="emailId" cssClass="error" /></td>
				</tr>

				<tr>
					<td>Designation:</td>
					<td><form:input path="designation" size="30" /></td>
					<td><form:errors path="designation" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Pancard:</td>
					<td><form:input path="pancard" size="30" /></td>
					<td><form:errors path="pancard" cssClass="error" /></td>
				</tr>
				<tr>
					<td>yearlyInvestmentUnder8oC:</td>
					<td><form:input path="yearlyInvestmentUnder80C" size="30" /></td>
					<td><form:errors path="yearlyInvestmentUnder80C" cssClass="error" /></td>
				</tr>
				<tr>
					<td>BankName:</td>
					<td><form:input path="bankDetail.bankName" size="30" /></td>
					<td><form:errors path="bankDetail.bankName" cssClass="error" /></td>
				</tr>
				<tr>
					<td>AccountNo:</td>
					<td><form:input path="bankDetail.accountNumber" size="30" /></td>
					<td><form:errors path="bankDetail.accountNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td>IifscCode:</td>
					<td><form:input path="bankDetail.ifscCode" size="30" /></td>
					<td><form:errors path="bankDetail.ifscCode" cssClass="error" /></td>
				</tr>
				<tr>
					<td>BasicSalary:</td>
					<td><form:input path="salary.basicSalary" size="30" /></td>
					<td><form:errors path="salary.basicSalary" cssClass="error" /></td>
				</tr>
				<tr>
					<td>EPF:</td>
					<td><form:input path="salary.epf" size="30" /></td>
					<td><form:errors path="salary.epf" cssClass="error" /></td>
				</tr>
				<tr>
					<td>companyPf:</td>
					<td><form:input path="salary.companyPf" size="30" /></td>
					<td><form:errors path="salary.companyPf" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>