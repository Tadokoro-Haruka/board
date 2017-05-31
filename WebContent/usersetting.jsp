<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
</head>
<body>
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
<form action="usersetting" method="post" >${editUser.login_id}の編集<br /><br />

	<input name="id" type="hidden" value="${editUser.id}" />
	<label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.login_id}" /><br /><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br /><br />

	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/><br /><br />

	<label for="branch_id">支店</label>
	<select name="branch_id">
	<OPTION selected="selected"></OPTION>
	<OPTION value="a">支店A</OPTION>
	<OPTION value="b">支店B</OPTION>
	<OPTION value="c">支店C</OPTION>
	<OPTION value="d">支店D</OPTION>
	</select><br /><br /><br /><br /><br />

	<label for="department_id">部署・役職</label>
	<select name="department_id">
	<OPTION selected="selected"></OPTION>
	<OPTION value="人事総務部">人事総務部</OPTION>
	<OPTION value="情報管理部">情報管理部</OPTION>
	<OPTION value="支店長">支店長</OPTION>
	<OPTION value="一般社員">一般社員</OPTION>
	</select><br /><br /><br /><br /><br />

	<input type="submit" value="登録" />
	<a href="./" >戻る</a>
</form>
</body>
</html>