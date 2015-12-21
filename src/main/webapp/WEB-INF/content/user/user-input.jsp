<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>增加用户</h1>
	<s:form action="user-input-submit">
		<s:textfield name="userName"></s:textfield>
		<s:textfield name="role.roleId"></s:textfield>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>