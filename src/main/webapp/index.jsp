<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>余票查询服务</title>
</head>
<script src="jquery/jquery-1.11.3.min.js"></script>
<body>
	<h1>余票查询服务</h1>
	<label for="userName">用户名：</label><input id="userName" name="userName" type="text" value="kalvenmeng" /> <br>
	<br>
	<label for="password">密&nbsp;码：</label><input id="password" name="password" type="password" value="111111" /> <br>
	<br>
	<button id="startup" onclick="startUp()" >启动 </button>
	<button id="shutdown" onclick="shutdown()" disabled="disabled">停止 </button>
	<button id="startup" onclick="getStatus()" >状态查询</button>
</body>
<script type="text/javascript">
	
	function startUp() {
		if (!check()) {
			return;
		}
		var userName = $("#userName").val();
		var password = $("#password").val();
		$.post(
				"monitor",
				{
					controlStr : "start",
					userName : userName,
					password : password
				},
				function(data,status){
					if (status == "success") {
						var result = $.parseJSON(data);
						if (result.status == "running") {
							$("#startup").attr("disabled","disabled");
							$("#shutdown").removeAttr("disabled");
							alert("服务启动成功！！");
						} else {
							alert("服务启动失败："+result.message);
						}
					} else {
						alert("请求失败！！");
					}
				}
		);
	}
	function shutdown() {
		if (!check()) {
			return;
		}
		var userName = $("#userName").val();
		var password = $("#password").val();
		$.post(
				"monitor",
				{
					controlStr:"stop",
					userName : userName,
					password : password
				},
				function(data,status){
					if (status == "success") {
						var result = $.parseJSON(data);
						if (result.status == "stoped") {
							alert("停止服务成功！！");
							$("#startup").removeAttr("disabled");
							$("#shutdown").attr("disabled","disabled");
						} else {
							alert("停止服务失败：" + result.message);
						}
					} else {
						alert("请求失败！！");
					}
					
					
					
				}
		);
	}
	
	function getStatus() {
		if (!check()) {
			return;
		}
		var userName = $("#userName").val();
		var password = $("#password").val();
		$.post(
				"monitor",
				{
					controlStr:"getStatus",
					userName : userName,
					password : password
				},
				function(data,status){
					var result = $.parseJSON(data);
					if (status == "success") {
						alert(result.message);
					} else {
						alert("请求失败！！");
					}
					
					
					
				}
		);
	}
	
	function check(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		if (userName == "" || password == "") {
			alert("用户名/密码 不能为空！");
			return false;
		} else {
			return true;
		}
		
		
	}
	
</script>
</html>