<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
	<h1>服务正常</h1>
	<button onclick="startUp()">启动 </button>
	<button onclick="startUp()">启动 </button>
</body>
<script type="text/javascript">
	function startUp() {
		alert("服务已启动");
		
	}
	
	function shutdown() {
		alert("服务已停止");
	}
</script>
</html>