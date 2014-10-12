<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Japanese Change</title>
<link href="/japanese-OES/css/bootstrap.min.css" rel="stylesheet">
<link href="/japanese-OES/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="/japanese-OES/css/font-awesome.css" rel="stylesheet">
<link href="/japanese-OES/css/font-awesome-ie7.css" rel="stylesheet">
<link href="/japanese-OES/css/boot-business.css" rel="stylesheet">
<link href="/japanese-OES/css/default.css" rel="stylesheet">

<script type="text/javascript" src="/japanese-OES/js/jquery.min.js"></script>
<script type="text/javascript" src="/japanese-OES/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/japanese-OES/js/boot-business.js"></script>
<script type="text/javascript" src="/japanese-OES/js/move.js"></script>
<script type="text/javascript" src="/japanese-OES/js/default-js.js"></script>
</head>
<body>
	<header>
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a href="index.html" class="brand brand-bootbus">Japanese</a>
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="nav-collapse collapse">
						<ul class="nav pull-right">
							<li><a href="/japanese-OES/admin/getImport">数据导入</a></li>
							<li><a href="javaScript:;" class="active-link">信息管理</a></li>
							<li><a href="/japanese-OES/about.html">关于</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="container">
			<div class="row">

				<div class="span6 offset3" id="changeTab">
					<h4 class="widget-header">教师/学生</h4>
					<div class="widget-body">
						<div id="tabContent">
							<a type="button" href="/japanese-OES/admin/teachers" >教师信息</a> <br> <br>
							<a type="button" href="javaScript:;" > 学生信息</a>
						</div>
					</div>
				</div>

				<div class="span6 offset3" id="changeStudent">
					<h4 class="widget-header">学生信息管理</h4>
					<div class="widget-body">
						<table class="changeTable" id="t_student">
							<tr>
								<td>学号</td>
								<td>姓名</td>
								<td>班级</td>
							</tr>
							<c:forEach var="user" items="${students}" varStatus="i">
								<tr class="changeContentOn" id="tr_${i.count}">
									<td id="t_userId_${i.count}">${user.userId}</td>
									<td id="t_userName_${i.count}">${user.userName }</td>
									<td id="t_classNumber_${i.count}">${user.classNumber}</td>
									<td><input type="button" id="s" index="${i.count}" value="修改"
										class="btn change btn-primary btn-medium"> </td>
									<td><input type="button" id="s" index="${i.count}" value="删除"
										class="btn delete btn-primary btn-medium"></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>

				
				<div class="span6 offset3" id="changeShow">
					<h4 class="widget-header">内容修改</h4>
					<div class="widget-body">
						<div class="changeContentOff" id="changeContentOff">
							<form id="changeForm" name="changeForm" method="post">
								<span>学号：</span> 
								<input id="userId" type="text"style="width: 80px;" disabled="disabled" /> <br> 
								<span>姓名：</span>
								<input id="userName" type="text" style="width: 80px;" /> <br>
								<span>班级：</span> 
								<input id="classNumber" type="text" style="width: 80px;" /> <br> 
								<input type="submit"id="changeConfirm" value="确定修改"class="btn btn-primary btn-large">
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="pagination pagination-centered">
			<ul>
				<c:set var="current" value="${currentPage}"></c:set>
				<c:set var="index" value="${i.count}"></c:set>
				<c:forEach varStatus="i" begin="1" end="${pageCount}">
					<c:if test="${i.count == currentPage}">
						<li class="active-link">
					</c:if>
					<c:if test="${i.count != currentPage}">
						<li>
					</c:if>
					<a href="/japanese-OES/admin/students?page=${i.count}">${i.count}
					</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<footer>
		<div class="container">
			<p>&copy; 2014 Japanese, Inc. All Rights Reserved.</p>
		</div>
	</footer>
</body>
</html>