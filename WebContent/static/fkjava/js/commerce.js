$(function(){
	// 绑定商品记录的点击事件，找到的所有元素都会绑定相同的事件
	// click里面的函数，就是元素onclick干的事情
	$(".article-item").click(function(){
		// 1.获取id的值，this表示点击哪个函数
		var id = $(this).attr("data-id");
		// 2.把页面转向到详情页面
		document.location.href= "./commerce/detail.action?id=" + id;
	});
	
	// 加入购物车的按钮
	$(".article-item .add-to-cart").click(function(event){
		// 阻止事件冒泡，避免被上面的div（外层的div）触发点击事件
		
		// 取消元素的默认动作，比如a标签的href属性的动作
		//event.preventDefault();
		// 取消事件继续传递、避免事件冒泡
		event.stopPropagation();
		
		var id = $(this).parent().parent().attr("data-id");
		// 如果登录了，加入购物车会把数据记录到数据库，下次登录可以肯定之前加入购物车的数据。
		// 如果没有登录，那么重新访问网页的时候，购物车数据就被清空。
		document.location.href= "./commerce/addToCart.action?id=" + id;
	});
	// 收藏按钮
	$(".article-item .add-to-collection").click(function(event){
		event.stopPropagation();
		var id = $(this).parent().parent().attr("data-id");
		document.location.href= "./commerce/user/collect.action?id=" + id;
	});
});