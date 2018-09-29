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

    <title>小牛直销电商</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<%-- 这里放一个按钮，用于在最小屏幕的时候使用 --%>
				<%-- 所用是为了在最小屏幕的时候，隐藏右侧的菜单，点击按钮显示菜单 --%>
				<p class="pull-right visible-xs">
		          	<button type="button" class="btn btn-primary btn-xs" 
		          		data-toggle="offcanvas">显示菜单</button>
				</p>
<%-- 				<div class="jumbotron" style="text-align: center;">
		          <img style="width: 50%" src="${ctx }/static/fkjava/images/map.png"/>
          		</div> --%>
          		<div class="row">
          			<c:forEach items="${page.articles }" var="a">
		            <div class="col-xs-6 col-lg-4">
						<h2>${a.title }</h2>
						<p>${a.description }</p>
						<p><a class="btn btn-default" href="index.html#" role="button">按钮</a></p>
		            </div><!--/.col-xs-6.col-lg-4-->
		            </c:forEach>
	            </div>
	            <div class="row" style="text-align: center;">
	            	<%-- 分页按钮 --%>
					<nav aria-label="Page navigation">
					    <ul class="pagination">
					        <li>
					            <a href="#" aria-label="Previous">
					                <span aria-hidden="true">&laquo;</span>
					            </a>
					        </li>
					        <li><a href="#">1</a></li>
					        <li><a href="#">2</a></li>
					        <li><a href="#">3</a></li>
					        <li><a href="#">4</a></li>
					        <li><a href="#">5</a></li>
					        <li>
					            <a href="#" aria-label="Next">
					                <span aria-hidden="true">&raquo;</span>
					            </a>
					        </li>
					    </ul>
					</nav>
	            </div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="list-group">
					<c:forEach items="${page.topTypes }" var="t">
					<a href="${ctx }/commerce/index.action?typeCode=${t.code}" 
						class="list-group-item ${t.code eq param.typeCode ? 'active' : '' }">${t.name }</a>
					</c:forEach>
				</div>
			</div><!--/.sidebar-offcanvas-->
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
  </body>
</html>