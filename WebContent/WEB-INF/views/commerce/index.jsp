<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fk" uri="http://fkjava.org/jsp/jstl/fk" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="${ctx }/static/fkjava/css/commerce.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<%-- 这里放一个按钮，用于在最小屏幕的时候使用 --%>
				<%-- 所用是为了在最小屏幕的时候，隐藏右侧的菜单，点击按钮显示菜单 --%>
				<p class="pull-right visible-xs">
		          	<button type="button" class="btn btn-primary btn-xs" 
		          		data-toggle="offcanvas">显示分类</button>
				</p>
<%-- 				<div class="jumbotron" style="text-align: center;">
		          <img style="width: 50%" src="${ctx }/static/fkjava/images/map.png"/>
          		</div> --%>
          			<%-- xs是最小屏幕；sm是小屏幕（平板）；md是中等屏幕；lg超大屏幕 --%>
        		<form action="${ctx }/commerce/index.action" method="get" 
        			class="search-form">
	          		<div class="row">
          				<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 form-group">
          					<div class="input-group">
		          				<input class="form-control" name="keyword" value="${param.keyword }"/>
		          				<div class="input-group-addon">
		          					<button type="submit" class="btn btn-default">搜索</button>
		          				</div>
	          				</div>
          				</div>
	          		</div>
	          		<div class="row types">
          				<div class="col-sm-12 form-group">
          					<c:forEach items="${page.types }" var="t">
          						<c:if test="${(not empty param.typeCode) or (empty param.typeCode and fn:length(t.code) eq 4) }">
	          						<label>
	          							<input type="radio" name="typeCode" 
	          								value="${t.code }" 
	          								${param.typeCode eq t.code ? 'checked="checked"' : '' }/>
	          							${fn:replace(t.name, '-', '') }
	          						</label>
          						</c:if>
          					</c:forEach>
          				</div>
	          		</div>
          		</form>
          		<div class="row">
          			<%-- data-开头的属性，是HTML 5里面新增的属性，用于给开发者自定义属性的 --%>
          			<%-- data-id希望把id存储在div里面，点击div的时候把id获取出来，并且根据id获取商品的详情 --%>
          			<c:forEach items="${page.articles }" var="a">
		            <div class="col-xs-6 col-lg-4 article-item" title="${a.title }" data-id="${a.id }">
		            	<div>
		            		<img src="${ctx }/static/fkjava/images/article/${a.image}"/>
		            	</div>
						<p class="aticle-title">${a.title }</p>
						<p>
							<span class="price">
								￥${a.price }
							</span>
							<span class="discount-price">
								<b>￥${ a.discountPrice }</b>
							</span>
							<a class="btn btn-default add-to-cart">加入购物车</a>
							<a class="btn btn-default add-to-collection">收藏</a>
						</p>
		            </div><!--/.col-xs-6.col-lg-4-->
		            </c:forEach>
	            </div>
	            <%-- 所有的查询参数在分页的时候都要携带过去 --%>
	            <fk:pager url="${ctx }/commerce/index.action?typeCode=${param.typeCode }&keyword=${param.keyword}"/>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="list-group">
					<c:forEach items="${page.topTypes }" var="t">
					<%-- fn:startsWith('长的字符串', '短的字符串'), 判断【长的字符串】是否以【短的字符串】开始 --%>
					<a href="${ctx }/commerce/index.action?typeCode=${t.code}" 
						class="list-group-item ${fn:startsWith(param.typeCode, t.code) ? 'active' : '' }">${t.name }</a>
					</c:forEach>
				</div>
			</div><!--/.sidebar-offcanvas-->
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/static/fkjava/js/commerce.js" charset="UTF-8"></script>
  </body>
</html>