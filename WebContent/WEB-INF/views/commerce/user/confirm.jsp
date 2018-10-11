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

    <title>小牛直销电商--结算</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/static/fkjava/css/offcanvas.css" rel="stylesheet">
    <link href="${ctx }/static/fkjava/css/commerce.css" rel="stylesheet">
  </head>

  <body>
		<jsp:include page="/WEB-INF/views/commons/before.jsp"></jsp:include>
		<c:if test="${ not empty sessionScope['CommerceService.shoppingCart'].items }">
			<form action="${ctx }/commerce/user/submitOrder.action" method="post">
				<!-- 收货地址和联系方式 -->
				<fieldset class="row">
					<legend>收货信息</legend>
					<div class="form-group">
					    <label for="inputAddress" class="col-sm-2 control-label">收货地址</label>
					    <div class="col-sm-10">
					        <div class="row">
						       	<!-- 选择省 -->
						       	<span class="col-sm-3">
							        <select class="form-control" id="selectProvince">
							        	<option value="">-- 选择省份 --</option>
							        </select>
							    </span>
							    <!-- 选择市 -->
						      	<span class="col-sm-3">
							        <select class="form-control" id="selectCity">
							        	<option value="">-- 选择城市 --</option>
							        </select>
							    </span>
							    <!-- 选择区/县 -->
						        <span class="col-sm-3">
							        <select class="form-control" id="selectDistrict">
							        	<option value="">-- 选择区/县 --</option>
							        </select>
							    </span>
							    <!-- 选择街道/镇 -->
						      	<span class="col-sm-3">
							        <select class="form-control" id="selectStreet">
							        	<option value="">-- 选择街道/镇 --</option>
							        </select>
							    </span>
					        </div>
					        <div class="row">
						        <div class="col-sm-12">
						        	<input class="form-control" name="receiver.address" placeholder="具体到门牌号"/>
						        </div>
					        </div>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputName" class="col-sm-2 control-label">收货人</label>
					    <div class="col-sm-10">
					        <input class="form-control" id="inputName" name="receiver.name" placeholder="姓名"/>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputPhone" class="col-sm-2 control-label">联系电话</label>
					    <div class="col-sm-10">
					        <input class="form-control" id="inputPhone" name="receiver.phone" placeholder="联系电话"/>
					    </div>
					</div>
				</fieldset>
				<!-- 支付方式 -->
				<fieldset class="row">
					<legend>支付方式</legend>
					<label><input type="radio" value="alipay"/>支付宝</label>
					<label><input type="radio" value="wechat"/>微信</label>
				</fieldset>
				<fieldset class="row shopping-cart">
					<legend>商品信息</legend>
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
								${item.value.number }
							</div>
						</div>
					</c:forEach>
				</fieldset><!--/row-->
				<div class="row" style="text-align: right;">
					<span>总计: <fmt:formatNumber value="${sessionScope['CommerceService.shoppingCart'].sum }" pattern="#,####.00"/></span>
					<button type="submit" class="btn btn-primary">提交订单</button>
				</div>
			</form>
		</c:if>
		<c:if test="${empty sessionScope['CommerceService.shoppingCart'].items }">
			您还未选购任何商品，请选购商品后再查看购物车。
			<a href="${ctx }/">点击这里选购</a>
		</c:if>
		<jsp:include page="/WEB-INF/views/commons/after.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/static/fkjava/js/commerce.js" charset="UTF-8"></script>
  </body>
</html>