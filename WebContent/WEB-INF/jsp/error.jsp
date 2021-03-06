	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>登陆出错</title>
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
							<li><a href="/japanese-OES/about.html">关于</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>
		<div class="container">
		<div style="margin:100px auto;width:200px;">
		<div class="panel panel-danger">
      <div class="panel-heading">
        <h3 class="panel-title">登录出错</h3>
      </div>
      <div class="panel-body">
        错误原因：<br>
        ${errorMsg}
      </div>
  </div>
  	</div>
	</div>
  </body>
</html>
