<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 在Session里面没有提示信息，直接重定向到首页 --%>
<c:if test="${empty sessionScope.title }">
	<c:redirect url="/"></c:redirect>
</c:if>
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

    <title>${sessionScope.title }</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row">
			${sessionScope.message }
			<%-- 从Session里面删除名为message的对象 --%>
			<c:remove var="message" scope="session"/>
			<c:remove var="title" scope="session"/>
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
	</body>
</html>