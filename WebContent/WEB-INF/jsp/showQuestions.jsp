<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师组卷</title>
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
            <a href="/japanese-OES/index.html" class="brand brand-bootbus">Japanese</a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
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
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			
			<div class="row-fluid">
				<div class="span8">
					<h3>
						试题选择
					</h3>
					<iframe src="">
					<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">首页</a>
				</li>
				<li>
					<a href="#">资料</a>
				</li>
				<li class="disabled">
					<a href="#">信息</a>
				</li>
				
			</ul>
					<h4>普通试题</h4>
					<table class="table">
						<thead>
										<tr>
											<td>试题名</td>
											<td>主题</td>
											<td>上传时间</td>
											<td>资源</td>
											<td></td>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="generals" items="${GENERAL}" varStatus="index">
										<tr>
											<td id="gen_name_${index}">${generals.description}</td>
											<td id="gen_title_${index}">${generals.title}</td>
											<td id="gen_date_${index}">${generals.upDate}</td>
											<td>无</td>
											<td><input class="btn btn-info"
											id="gen_select_${index}" type="button" value="选择"></td>
										</tr>

									</c:forEach>
									</tbody>
					</table>
					<h4>图片试题</h4>
					<table class="table">
						<thead>
										<tr>
											<td>试题名</td>
											<td>主题</td>
											<td>上传时间</td>
											<td>资源</td>
											<td></td>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="generals" items="${IMG}" varStatus="index">
										<tr>
											<td id="gen_name_${index}">${generals.description}</td>
											<td id="gen_title_${index}">${generals.title}</td>
											<td id="gen_date_${index}">${generals.upDate}</td>
											<td>无</td>
											<td><input class="btn btn-info"
											id="gen_select_${index}" type="button" value="选择"></td>
										</tr>

									</c:forEach>
									</tbody>
					</table>
					<div class="pagination">
						<ul>
					<!-- 	<c:set var="current" value="${currentPage}"></c:set>
				<c:set var="index" value="${i.count}"></c:set>
				<c:forEach varStatus="i" begin="1" end="${pageCount}">
					<c:if test="${i.count == currentPage}">
						<li class="active-link">
					</c:if>
					<c:if test="${i.count != currentPage}">
						<li>
					</c:if>
					<a href="/japanese-OES/teacher/questions?page=${i.count}">${i.count}
					</a>
					</li>
				</c:forEach>
						-->	
						</ul>
					</div>
				
				</iframe>
				</div>
				<div class="span4">
					<h3 class="text-center">
						选中的试题
					</h3>
					<p class="lead">
						试题名：
					</p> <button class="btn btn-info btn-large" type="button">按钮</button>
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