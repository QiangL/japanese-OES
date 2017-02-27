<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>ITing Login</title>
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
<script type="text/javascript" src="/japanese-OES/js/grade.js"></script>
</head>
<body>
	<header>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="/japanese-OES/index.html" class="brand brand-bootbus">Japanese</a>
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li><a href="/japanese-OES/teacher/upload.html">试题上传</a></li>
						<li><a href="/japanese-OES/teacher/paperGenerate.html">组卷</a></li>
						<li><a href="/japanese-OES/teacher/papers">选择考卷</a></li>
						<li class="active"><a href="#">评分</a></li>
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
				<ul class="nav nav-tabs">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="#">资料</a></li>
					<li class="disabled"><a href="#">信息</a></li>
				</ul>

				<div class="span2">
					<h4 class="widget-header1">班级</h4>
					<div class="widget-body">
						<div class="center-align">
							<ol>
								<c:forEach var="clazz" items="${classes}">
									<li class="clazzNumber" onmouseover="">${clazz}</li>
								</c:forEach>
							</ol>
						</div>
					</div>
				</div>

				<div class="span2">
					<h4 class="widget-header1">学生</h4>
					<div class="widget-body">
						<div class="center-align">
							<ol id="studentList">

							</ol>
						</div>
					</div>
				</div>

				<div class="span8">
					<h4 class="widget-header1">答题信息</h4>
					<div class="widget-body">
						<div>
							姓名：<span id="sName"></span><br> 学号：<span id="sId"></span>
							<div class="center-align">
								<audio src="" id="player" preload="auto" controls="controls"></audio>
							</div>
						</div>

					</div>
					<br>
					<div class="center-align">
						<table class="table">
							<thead>
								<tr>
									<th>序号</th>
									<th>习题名</th>
									<th>试听</th>
									<th>打分</th>
									<th>确认</th>
								</tr>
							</thead>
							<tbody id="records">

							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
	</div>
	<footer>
	<div class="container">
		<p>&copy; 2014 Japanese, Inc. All Rights Reserved.</p>
	</div>
	</footer>
</body>
</html>