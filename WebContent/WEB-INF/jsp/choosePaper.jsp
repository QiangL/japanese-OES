	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>选择考卷</title>
<link href="/japanese-OES/css/bootstrap.min.css" rel="stylesheet">
    <link href="/japanese-OES/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="/japanese-OES/css/font-awesome.css" rel="stylesheet">
    <link href="/japanese-OES/css/font-awesome-ie7.css" rel="stylesheet">
    <link href="/japanese-OES/css/boot-business.css" rel="stylesheet">
    <link href="/japanese-OES/css/default.css" rel="stylesheet">

    <script type="text/javascript" src="/japanese-OES/js/jquery.min.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/boot-business.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/choose.js"></script>
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
							<li class="active"><a href="javascript:">选择考卷</a></li>
							<li><a href="/japanese-OES/teacher/grade">评分</a></li>
							<li><a href="/japanese-OES/about.html">关于</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3>选择将要使用的考卷</h3>
				<table class="table">
					<thead>
						<tr>
							<th>试卷名</th>
							<th>组卷时间</th>
							<th>选择</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="paper" items="${papers}" varStatus="index">
						<tr>
							<td>${paper.name}</td>
							<td>${paper.time}</td>
							<td style="display:none" id="examId">${paper.id}</td>
							<td><input type="radio" name="select"></td>
							<td><input class="btn btn-info" type="button" value="试题内容" flag="false"></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<input class="btn btn-warning" type="button" value="提交">
			</div>
			<div class="span8" >
			<h3>试卷内容</h3>
				<table class="table" id="details" style="display:none">
			 	  <thead>
			 	    <tr>
			 	      <th>试题名</th>
			 	      <th>类型</th>
			 	      <th>上传时间</th>
			 	    </tr>
			 	  </thead>
			 	  <tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
