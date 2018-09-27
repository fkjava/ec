<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 引入自定义标签库 --%>
<%@ taglib prefix="fk" uri="/WEB-INF/fkjava.tld" %>
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

    <title>用户登录</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row">
			<form action="${ctx }/identity/login.action" method="post"
				class="form-horizontal register-form">
				<c:if test="${not empty sessionScope.message }">
					<div class="alert alert-danger" role="alert">${sessionScope.message }</div>
				</c:if>
				<c:remove var="message" scope="session"/>
				<div class="form-group">
					<label for="inputLoginName" class="col-sm-3 control-label">登录名</label>
					<%-- 使用[]和使用.都可以获取对象里面的属性 --%>
					<%-- value="${param['user.loginName'] }" --%>
					<div class="col-sm-9">
						<input type="text" 
							class="form-control" 
							id="inputLoginName" 
							name="user.loginName"
							value="${ not empty sessionScope.allParams ? sessionScope.allParams['user.loginName'][0] : param['user.loginName'] }"
							placeholder="登录名"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-3 control-label">登录密码</label>
					<div class="col-sm-9">
						<input type="password" 
							class="form-control" 
							id="inputPassword" 
							name="user.password"
							placeholder="登录密码，必须有字母、数字、下划线"
							required="required"
							pattern="[\w\d_]{6,15}"/>
					</div>
				</div>
				<%-- 使用自定义的标签 --%>
				<fk:validateCode/>
				<div class="col-md-offset-3 col-sm-4">
					<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
				</div>
				<div class="col-sm-4">
					<a class="btn btn-lg btn-default btn-block" href="${ctx }/identity/registerForm.action">注册</a>
				</div>
			</form>
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
  </body>
</html>