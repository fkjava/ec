<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${ctx }/static/fkjava/images/logo.png">

    <title>欢迎注册新用户</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row">
			<form action="${ctx }/identity/register.action" method="post"
				class="form-horizontal register-form">
				<div class="form-group">
					<label for="inputName" class="col-sm-3 control-label">姓名</label>
					<div class="col-sm-9">
						<input type="text" 
							class="form-control" 
							id="inputName" 
							name="user.name"
							placeholder="姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputLoginName" class="col-sm-3 control-label">登录名</label>
					<div class="col-sm-9">
						<input type="text" 
							class="form-control" 
							id="inputLoginName" 
							name="user.loginName"
							placeholder="登录名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="col-sm-3 control-label">电子邮箱</label>
					<div class="col-sm-9">
						<input type="text" 
							class="form-control" 
							id="inputEmail" 
							name="user.email"
							placeholder="用于激活和找回密码的邮箱地址"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-3 control-label">登录密码</label>
					<div class="col-sm-9">
						<input type="text" 
							class="form-control" 
							id="inputPassword" 
							name="user.password"
							placeholder="登录密码，必须有字母、数字、下划线"
							required="required"
							pattern="[\w\d_]{6,15}"/>
					</div>
				</div>
				<div class="col-md-offset-3 col-sm-4">
					<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
				</div>
				<div class="col-sm-4">
					<a class="btn btn-lg btn-default btn-block">登录</a>
				</div>
			</form>
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
  </body>
</html>