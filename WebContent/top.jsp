<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
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


<div class="header">わったい菜掲示板<br/><br/>

	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<br/>
		<a href="signup">新規ユーザー登録をする</a>
		<br/>
	</c:if>

	<c:if test="${ not empty loginUser }">
		<a href="logout">ログアウト</a><br/><br/>
		<a href="newmessage">新規投稿</a><br/><br/>
		<a href="signup">新規ユーザー登録をする</a><br/><br/>
		<a href="http://localhost:8080/Board/usercontrol.jsp">ユーザー管理</a><br/><br/>
	</c:if>
</div>

<!-- ログインユーザーの情報を表示させるためのコード -->
<c:if test="${ not empty loginUser }">
	<h2>ログインID <c:out value="${loginUser.login_id}" /></h2><br/>
	名前【<c:out value="${loginUser.name}" />】<br/>
	支店名【<c:out value="${loginUser.branch_id}" />】<br/>
	部署・役職【<c:out value="${loginUser.department_id}" />】

	<br/><br/><br/>

</c:if>

<!-- 投稿機能 -->
<c:if test="${ isShowMessageForm }">
	<div class="form-area">
		<form action="newMessage" method="post">  <!-- 中身のデータをactionの場所へ送る -->
			コメント
				<br />
				<textarea name="message" cols="100" rows="5" class="message-box"></textarea>
				<br />
				<input type="submit" value="投稿">
			</form>
	</div>
</c:if>


<!-- メッセージをブラウザに表示するためのコード -->

<div class="messages"><h2>投稿されている内容</h2><br/>
	<c:forEach items="${messages}" var="message">
		<c:out value="・件名：${message.subject}" /><br/>
		<c:out value="　${message.text}" />
		<br/><br/>
	</c:forEach>
</div>

</div>
</body>
</html>
