<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fk" uri="http://fkjava.org/jsp/jstl/fk" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<c:if test="${ not empty sessionScope['CommerceService.shoppingCart'].items }">
		<div class="row shopping-cart">
			<div class="col-xs-10">商品</div>
			<div class="col-xs-2">数量</div>
			<%-- sessionScope['CommerceService.shoppingCart']拿到购物车对象 --%>
			<%-- .items调用购物车对象的getItems()方法，返回一个Map --%>
			<c:forEach items="${sessionScope['CommerceService.shoppingCart'].items }" var="item">
				<%-- 注意：这里的item是一个Map里面的键值对，getKey可以得到键、getValue得到值 --%>
				<%-- getValue得到的是ShoppingCartItem对象 --%>
				<div class="col-xs-12 cart-item">
					<div class="col-xs-10">
						<div class="col-xs-2 col-md-1">
							<img alt="图片" src="${ctx }/static/fkjava/images/article/${item.value.article.image}"
								style="width: 46px;"/>
						</div>
						<div class="col-xs-7 col-md-8">
							${item.value.article.title }
						</div>
						<div class="col-xs-3 article-detail">
							原价<span class="price">￥${item.value.article.price }</span>
							折扣价<span class="discount-price">￥${item.value.article.discountPrice }</span>
							<br/>
							小计<span class="sum">￥<fmt:formatNumber value="${item.value.sum }" pattern="#,####.00"/></span>
						</div>
					</div>
					<div class="col-xs-2">
						<div class="col-xs-9">
							<div class="input-group item-number">
								<input class="form-control" name="number" id="item_${item.value.article.id }" value="${item.value.number }"/>
								<div class="input-group-addon number-buttons">
									<span class="glyphicon glyphicon-menu-up" id="number_up_${item.value.article.id }"></span>
									<span class="glyphicon glyphicon-menu-down" id="number_down_${item.value.article.id }"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-3">
							<a class="btn btn-default" title="删除商品" style="padding-right: 6px;"
								href="${ctx }/commerce/deleteCart.action?id=${item.value.article.id}">
								<span class="glyphicon glyphicon-trash"></span>
								&nbsp;
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div><!--/row-->
		<div class="row" style="text-align: right;">
			<span>总计: <fmt:formatNumber value="${sessionScope['CommerceService.shoppingCart'].sum }" pattern="#,####.00"/></span>
			<a href="${ctx }/commerce/user/confirm.action" class="btn btn-primary">确认订单</a>
		</div>
		</c:if>
		<c:if test="${empty sessionScope['CommerceService.shoppingCart'].items }">
			您还未选购任何商品，请选购商品后再查看购物车。
			<a href="${ctx }/">点击这里选购</a>
		</c:if>
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/static/fkjava/js/commerce.js" charset="UTF-8"></script>
  </body>
</html>