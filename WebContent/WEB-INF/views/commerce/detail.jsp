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
		<div class="row article-detail">
			<!-- 图片显示 -->
			<div class="col-xs-12 col-sm-3" style="text-align: center;">
				<img src="${ctx }/static/fkjava/images/article/${page.article.image}" alt="图片离家出走了"/>
			</div>
			<form action="${ctx }/commerce/addToCart.action" method="post">
				<input name="id" value="${page.article.id }"/>
				<div class="col-xs-12 col-sm-7" data-id="${page.article.id }">
					<!-- 标题（商品名称）、价格、库存。。。 -->
					<p class="article-title"><h4>${page.article.title }</h4></p>
					<p>
						<span>库存${page.article.storage }件</span>
						<span class="price">
							￥${page.article.price }
						</span>
						<span class="discount-price">
							<b>￥${ page.article.discountPrice }</b>
						</span>
					</p>
					<p>
						<label>购买<input class="buy-number" name="number" onkeydown="inputBuyNumber(event)" value="1"/>件 
					</p>
					<p>
						<button type="submit" class="btn btn-primary">加入购物车</button>
						<button class="btn btn-default add-to-collection">加入收藏</button>
					</p>
				</div>
			</form>
		</div><!--/row-->
		<hr/>
		<%-- 商品介绍 --%>
		<div>
			<p style="text-align: center;">
				<img src="${ctx }/static/fkjava/images/article/${page.article.image}" 
					alt="图片离家出走了" style="width: 30%;"/>
			</p>
			<p>
				${page.article.description }
			</p>
		</div>
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/static/fkjava/js/commerce.js" charset="UTF-8"></script>
  </body>
</html>