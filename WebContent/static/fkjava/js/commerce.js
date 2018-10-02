// $(function(){}); 初始化的函数
// 所有在里面声明的变量、函数都局部有效
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
		document.location.href= contextPath + "/commerce/addToCart.action?number=1&id=" + id;
	});
	// 收藏按钮
	$(".article-item .add-to-collection, .article-detail .add-to-collection").click(function(event){
		event.stopPropagation();
		var id = $(this).parent().parent().attr("data-id");
		document.location.href= contextPath + "/commerce/user/collect.action?id=" + id;
	});
});

// 声明全局函数，才能被HTML的元素调用到
// onkeydown : 当键盘按下的时候
var inputBuyNumber = function(event){
	
	// 获取键盘的输入字符
	var keyCode = event.keyCode;//ASCII码
	var key = event.key;//输入的字符
	
	// 使用==的时候JS会自动进行数据类型转换
	// 使用===的时候不会进行类型转换
	// 123 == "123" 返回true
	// 123 === "123" 返回false
	if(keyCode === 8 || keyCode === 116){
		// 退格键、F5键不要处理
		return;
	}

	// 取消事件的默认动作，字符不会进入输入框
	event.preventDefault();
	
	if( keyCode >= 48 && keyCode <= 57 ){
		// 数字范围的才是需要的
		// 把输入的字符放入文本框
		//  $(this) this表示触发事件的对象（元素），$(this)把元素包装成jQuery对象
		// .val()用于获取文本框的值，如果有参数则是设置文本框的值。
		// event.target就是触发事件的对象，使用this不行的时候可以改为event.target。
		var value = $(event.target).val();
		value = value + key;

		$(event.target).val(value);
	}
};


