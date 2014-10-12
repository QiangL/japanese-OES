<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>数据导入</title>
    <link href="/japanese-OES/css/bootstrap.min.css" rel="stylesheet">
    <link href="/japanese-OES/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="/japanese-OES/css/font-awesome.css" rel="stylesheet">
    <link href="/japanese-OES/css/font-awesome-ie7.css" rel="stylesheet">
    <link href="/japanese-OES/css/boot-business.css" rel="stylesheet">
    <link href="/japanese-OES/css/default.css" rel="stylesheet">

    <script type="text/javascript" src="/japanese-OES/js/jquery.min.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/boot-business.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/move.js"></script>
    <script type="text/javascript" src="/japanese-OES/js/default-login.js"></script>
  </head>
  <body >
    <header>
      <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <a href="index.html" class="brand brand-bootbus">Japanese</a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <div class="nav-collapse collapse">        
              <ul class="nav pull-right">
             	  <li><a href="javaScript:;" class="active-link">数据导入</a></li>
         	     <li><a href="/japanese-OES/admin/students">信息管理</a></li>
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
          <div class="span6 offset3" id="manager">
            <h4 class="widget-header">数据导入</h4>
            <div class="widget-body">
              <div class="center-align">

                <form enctype="multipart/form-data" class="form-horizontal form-signin-signup" id="signupForm" action="/japanese-OES/admin/import" method="post">
                  <div class="inputHolder" class="import">
                    <div id="student_import" class="import">
                      <span class="importSpan">学生导入</span>
                      <input type="radio" name="type" value="student" />
                    </div>
                    <div id="teacher_import" class="import">
                      <span class="importSpan">教师导入</span>
                      <input type="radio" name="type"  value="teacher"/>
                    </div>

                    <br>
                    <input type="file" id="manFile" name = "file" accept=".xls">
                  </div>

                  <input type="submit" value="导入" class="btn btn-primary btn-large">
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer>
      <div class="container">
        <p>
          &copy; 2014 Japanese, Inc. All Rights Reserved.
        </p>
      </div>
    </footer>
  </body>
</html>