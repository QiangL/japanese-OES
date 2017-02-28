<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>开始考试</title>
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
						<li><a href="javaScript:;" class="active-link">登陆</a></li>
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
			<div class = "span8">
				<ul class="nav nav-tabs">
					<li id="general_question" class="active"><a href="#">普通题</a></li>
					<li id="img_question" ><a href="#">图片题</a></li>
				</ul>
				</div>
				<div class="span4">
				姓名：<span id="userName">${user.userName}</span><br>
				学号：<span id="userId">${user.userId}</span>
				</div>
				
				<div class="span8">
				<h3 id="name">${es.name}</h3>
					<h4 class="widget-header1">题目信息</h4>
					<div class="widget-body">
						<div style="margin-left:70px;">
							<p>
								<audio id="general_src" src="" controls="true"></audio><br>
								<img id="img_src" src="" style="display: none"/>
							</p>
							<p>
								<span>录音时长：</span><span id="time">0:00</span> 
								<input type="hidden" value="" id="qid_input"/>
								
							</p>
						</div>
						<div class="center-align">
							<!-- 
							<input type="button" value="试听" class="btn" onclick="" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							-->
							 <input type="button" value="录音" class="btn" onclick="record()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="button" value="停止" class="btn" onclick="stop()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="button" value="播放" class="btn" onclick="play()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="button" value="提交" class="btn" onclick="upload()" />
						</div>
					</div>
				</div>


				<div class="span2">
					<h4 class="widget-header1">题目</h4>
					<div class="widget-body">
						<div class="center-align">
							<ol id="questions">
							</ol>
						</div>
					</div>
				</div>
				<div class="span2">
					<div id="container"></div>
				</div>
			</div>
		</div>
	</div>
	<footer>

	<div class="container">
		<p>&copy; 2014 Japanese, Inc. All Rights Reserved.</p>
	</div>
	</footer>
	<script type="text/javascript" src="/japanese-OES/js/recorder.js"></script>
	<script type="text/javascript" src="/japanese-OES/js/exam.js"></script>
</body>
</html>

