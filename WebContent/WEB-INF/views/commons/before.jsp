<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
	    <div class="container">
	        <div class="navbar-header">
	            <button type="button" 
	            	class="navbar-toggle collapsed" 
	            	data-toggle="collapse" 
	            	data-target="#navbar" >
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="${ctx }/">
	            	<img src="${ctx }/static/fkjava/images/logo.png"/>小牛直销电商
	            </a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	            <ul class="nav navbar-nav">
	                <li><a href="${ctx }/">首页</a></li>
	                <li><a href="index.html#about">关于</a></li>
	                <li><a href="index.html#contact">联系我们</a></li>
	                <li><a href="index.html#contact">招贤纳士</a></li>
	            </ul>
	            <ul class="nav navbar-nav navbar-right">
	            	<c:if test="${not empty sessionScope.user }">
	            		<li><a href="${ctx }/identity/profile.action">${sessionScope.user.name }</a></li>
	            		<li><a href="${ctx }/identity/logout.action">退出</a></li>
	            	</c:if>
	            	<c:if test="${empty sessionScope.user }">
		                <li><a href="${ctx }/identity/loginForm.action">登录</a></li>
		                <li><a href="${ctx }/identity/registerForm.action">注册</a></li>
	                </c:if>
	                <li><a href="${ctx }/commerce/showCart.action">购物车</a></li>
	                <li><a href="${ctx }/commerce/user/listOrder.action">订单</a></li>
	            </ul>
	        </div><!--/.nav-collapse -->
	    </div>
	</nav>

	<div class="container">
