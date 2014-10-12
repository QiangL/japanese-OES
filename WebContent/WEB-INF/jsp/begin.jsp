<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认考试</title>
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
						<li><a href="javaScript:;" class="active-link">登陆</a></li>
						<li><a href="about.html">关于</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</header>
	<div class="container-fluid">
	<div class="span12">
		<div class="row-fluid">
		
			<div class="span8">
			<h3>考生信息</h3>
				<label name="${user.userId}">学号：${user.userId}</label> 
				<label>姓名：${user.userName}</label>
				<label>班级：${user.classNumber}</label>
				<h3>${classNumber}</h3>
			</div>
			<div class="span4">
			<h3>确认考试</h3>
				<ol>
					<li>${es.name}</li>
				</ol>
			</div>
		
		</div>
			<a href="/japanese-OES/user/examBegin?examId=${es.id}" class="btn btn-info">开始考试</a>
		</div>
	</div>
</body>
</html>