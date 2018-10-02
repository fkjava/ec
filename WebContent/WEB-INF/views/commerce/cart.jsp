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

    <title>小牛直销电商--我的购物车</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
    <link href="${ctx }/static/fkjava/css/commerce.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<div class="row shopping-cart">
			<div class="col-xs-10">商品</div>
			<div class="col-xs-2">数量</div>
			<%-- sessionScope['CommerceService.shoppingCart']拿到购物车对象 --%>
			<%-- .items调用购物车对象的getItems()方法，返回一个Map --%>
			<c:forEach items="${sessionScope['CommerceService.shoppingCart'].items }" var="item">
				<%-- 注意：这里的item是一个Map里面的键值对，getKey可以得到键、getValue得到值 --%>
				<%-- getValue得到的是ShoppingCartItem对象 --%>
				<div class="col-xs-12" style="border-bottom: 1px solid #ccc;">
					<div class="col-xs-10">
						<div class="col-xs-2 col-md-1">
							<img alt="图片" src="${ctx }/static/fkjava/images/article/${item.value.article.image}"
								style="width: 46px;"/>
						</div>
						<div class="col-xs-8 col-md-9">
							${item.value.article.title }
						</div>
						<div class="col-xs-2">
							<span class="price">￥${item.value.article.price }</span>
							<span class="discount-price">￥${item.value.article.discountPrice }</span>
						</div>
					</div>
					<div class="col-xs-2">
						${item.value.number }
					</div>
				</div>
			</c:forEach>
		</div><!--/row-->
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/static/fkjava/js/commerce.js" charset="UTF-8"></script>
  </body>
</html>