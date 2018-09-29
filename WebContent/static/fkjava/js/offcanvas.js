$(document).ready(function() {
	$('[data-toggle="offcanvas"]').click(function() {
		$('.row-offcanvas').toggleClass('active')
	});

	// 选中当前页面的按钮
	// 1.获取当前页面的URL
	var href = document.location.href;
	//console.log(href);
	// 2.判断URL是否跟a标签的href相同，如果相同则加上active类
	// $ 就是jQuery对象
	// 在JS里面对象其实本质就是函数
	// $() == jQuery()
	// ".navbar-nav li a"是一个CSS选择器，表示先找到应用了.navbar-nav类的元素，然后在里面找li，再接着在li里面找a
	// 找到的元素返回的是一个数组
	var links = $(".navbar-nav li a");
	for( var i = 0; i < links.length; i++ ){
		// 取出一个a标签
		var link = links[i];
		// $(link) : link是一个元素，这里就是a标签。整个表达式表示把a标签转换为jQuery对象
		// 所有的jQuery对象都可以直接调用attr方法获取【属性】的值，或者设置【属性】的值
		// attr 是 Attribute的简写
		var url = $(link).attr("href");
		//console.log(url);
		// 当前页面地址，以url结尾，选中链接！
		if( href.endsWith(url) ){
			// 在链接的上级元素增加active类
			$(link).parent().addClass("active");
			break;
		}
	}
});