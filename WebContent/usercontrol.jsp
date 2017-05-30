<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<!-- <link href="./css/style.css" rel="stylesheet" type="text/css"> -->
</head>
<body>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
ユーザー管理<br />
<br /><br />
	<table border="4">
		<tr>
		<th>ログインID</th>
		<th>名前</th>
		<th>支店</th>
		<th>部署・役職</th>
		<th>ユーザー編集</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
			<td><span class="login_id"><c:out value="${user.login_id}" /></span></td>
			<td><span class="name"><c:out value="${user.name}" /></span></td>
			<td><span class="branch_id"><c:out value="${user.branch_id}" /></span></td>
			<td><span class="department_id"><c:out value="${user.department_id}" /></span></td>
			<td><a href="usersetting?id=${user.id}"name=id>編集</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br /><br />
<a href="./" >戻る</a>
</body>
</html>
