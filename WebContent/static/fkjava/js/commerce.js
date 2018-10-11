// $(function(){}); 初始化的函数
// 所有在里面声明的变量、函数都局部有效
$(function() {
	// 绑定商品记录的点击事件，找到的所有元素都会绑定相同的事件
	// click里面的函数，就是元素onclick干的事情
	$(".article-item").click(function() {
		// 1.获取id的值，this表示点击哪个函数
		var id = $(this).attr("data-id");
		// 2.把页面转向到详情页面
		document.location.href = "./commerce/detail.action?id=" + id;
	});

	// 加入购物车的按钮
	$(".article-item .add-to-cart").click(
			function(event) {
				// 阻止事件冒泡，避免被上面的div（外层的div）触发点击事件

				// 取消元素的默认动作，比如a标签的href属性的动作
				// event.preventDefault();
				// 取消事件继续传递、避免事件冒泡
				event.stopPropagation();

				var id = $(this).parent().parent().attr("data-id");
				// 如果登录了，加入购物车会把数据记录到数据库，下次登录可以肯定之前加入购物车的数据。
				// 如果没有登录，那么重新访问网页的时候，购物车数据就被清空。
				document.location.href = contextPath
						+ "/commerce/addToCart.action?number=1&id=" + id;
			});
	// 收藏按钮
	$(".article-item .add-to-collection, .article-detail .add-to-collection")
			.click(
					function(event) {
						event.stopPropagation();
						var id = $(this).parent().parent().attr("data-id");
						document.location.href = contextPath
								+ "/commerce/user/collect.action?id=" + id;
					});

	// 购物车的数量数据库的事件绑定
	$(".item-number input").keydown(function() {
		inputBuyNumber(event);
	});
	// 修改后的数量，要更新到Session里面购物车中
	$(".item-number input").keyup(
			function() {
				// input的id，有item_开头
				var id = $(this).attr("id");
				id = id.substring(5);

				// 获取修改后的值
				var number = $(this).val();
				// 这里应该使用AJAX方式访问服务器，AJAX是异步的HTTP请求，不会刷新页面
				// 现在还没有学习AJAX，所以直接刷新页面
				document.location.href = contextPath
						+ "/commerce/updateNumber.action?number=" + number
						+ "&id=" + id;
			});

	$(".item-number .glyphicon-menu-up").click(
			function() {
				var id = $(this).attr("id");
				id = id.substring(10);

				// 获取输入框的值
				var number = $("#item_" + id).val();
				number++;
				// $("#item_" + id).val(number);

				document.location.href = contextPath
						+ "/commerce/updateNumber.action?number=" + number
						+ "&id=" + id;
			});
	$(".item-number .glyphicon-menu-down").click(
			function() {
				var id = $(this).attr("id");
				id = id.substring(12);

				// 获取输入框的值
				var number = $("#item_" + id).val();
				number--;
				if (number < 1) {
					number = 1;
				}
				// $("#item_" + id).val(number);

				document.location.href = contextPath
						+ "/commerce/updateNumber.action?number=" + number
						+ "&id=" + id;
			});

	// 下拉列表的级联操作，现在这些数据是模拟的，以后需要通过AJAX从服务器实时读取
	var addressData = [ {
		name : "广东省",// 省级
		cities : [ {
			name : "广州市",// 市级
			districts : [ {
				name : "天河区",// 区级
				street : [ {
					name : "车陂街"// 街道
				}, {
					name : "前进街道"
				}, {
					name : "棠德街道"
				}, {
					name : "华景街道"
				}, {
					name : "石牌街道"
				} ]
			}, {
				name : "白云区"
			}, {
				name : "越秀区"
			}, {
				name : "海珠区",
				street : [ {
					name : "东晓街道"
				}, {
					name : "西晓街道"
				}, {
					name : "街道1"
				}, {
					name : "街道2"
				}, {
					name : "街道3"
				} ]
			}, {
				name : "番禺区"
			}, {
				name : "荔湾区"
			}, {
				name : "黄浦区"
			}, {
				name : "从化区"
			}, {
				name : "南沙区"
			}, {
				name : "花都区"
			} ]
		}, {
			name : "深圳市",
			districts: [
				{name:"罗湖区"},
				{name:"福田区"},
				{name:"南山区"}
			]
		}, {
			name : "梅州市"
		} ]
	}, {
		name : "湖南省",
		cities : [ {
			name : "长沙市"
		}, {
			name : "郴州市"
		}, {
			name : "岳阳市"
		} ]
	}, {
		name : "海南省"
	}, {
		name : "台湾省"
	} ];
	
	// 把数组里面的数据生成下拉列表的选项
	var generateOptions = function( data, targetId){
		if(!data){
			return;
		}
		for(var i = 0; i < data.length; i ++){
			var x = data[i];
			// var html = "<option value='" + province.name + "'>" +
			// province.name +
			// "</option>";
			
			// 使用数字键1左边的引号
			// ECMA Script 2015的规范，可以使用表达式获取对象的值，也能够换行
			// ${}表达式不能放到JSP里面，只能在js文件里面
			var html = `<option value='${x.name}'>
	    ${x.name}
	</option>`;
			// console.log(html);
			
			// 把html字符串转换为一个元素，并且追加到#selectProvince对象里面
			$(html).appendTo( $(targetId) );
		}
	};
	// 初始化选择省的下拉框里面的内容
	generateOptions(addressData, "#selectProvince");
	
	// 绑定选择省份的事件
	$("#selectProvince").change(function(){
		// 获取当前选择的option的值
		var value = $(this).val();
		// console.log(value);
		
		// 清空【选择城市】的下拉列表里面的所有option
		$("#selectCity").html("<option value=''>-- 选择城市 --</option>");
		
		// 把对应省里面的城市加入【选择城市】的下拉列表
		var cities = [];
		for( var i = 0; i < addressData.length; i++ ){
			var p = addressData[i];
			// 当省份的名字相同，获取对应的城市
			if(p.name === value){
				cities = p.cities;
				break;
			}
		}
		generateOptions(cities, "#selectCity");
	});
	
	// 绑定选择城市的事件
	$("#selectCity").change(function(){
		var provinceName = $("#selectProvince").val();
		var cityName = $(this).val();
		$("#selectDistrict").html("<option value=''>-- 选择区/县 --</option>");
		
		ALL:
		for( var i = 0; i < addressData.length; i++ ){
			var p = addressData[i];
			// 当省份的名字相同，获取对应的城市
			if(p.name === provinceName){
				// 省份相同以后，再匹配城市
				for( var j = 0; j < p.cities.length; j++){
					// 匹配到城市以后，把街道获取、生成下拉列表
					if(p.cities[j].name === cityName){
						generateOptions(p.cities[j].districts, "#selectDistrict");
						break ALL;
					}
				}
			}
		}
	});
});

// 声明全局函数，才能被HTML的元素调用到
// onkeydown : 当键盘按下的时候
var inputBuyNumber = function(event) {

	// 获取键盘的输入字符
	var keyCode = event.keyCode;// ASCII码
	var key = event.key;// 输入的字符

	// 使用==的时候JS会自动进行数据类型转换
	// 使用===的时候不会进行类型转换
	// 123 == "123" 返回true
	// 123 === "123" 返回false
	if (keyCode === 8 || keyCode === 116) {
		// 退格键、F5键不要处理
		return;
	}

	// 取消事件的默认动作，字符不会进入输入框
	event.preventDefault();

	if (keyCode >= 48 && keyCode <= 57) {
		// 数字范围的才是需要的
		// 把输入的字符放入文本框
		// $(this) this表示触发事件的对象（元素），$(this)把元素包装成jQuery对象
		// .val()用于获取文本框的值，如果有参数则是设置文本框的值。
		// event.target就是触发事件的对象，使用this不行的时候可以改为event.target。
		var value = $(event.target).val();
		value = value + key;

		$(event.target).val(value);
	}
};
