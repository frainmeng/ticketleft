<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ʊ��ѯ����</title>
</head>
<script src="jquery/jquery-1.11.3.min.js"></script>
<body>
	<h1>��Ʊ��ѯ����</h1>
	<label for="userName">�û�����</label><input id="userName" name="userName" type="text" value="kalvenmeng" /> <br>
	<br>
	<label for="password">��&nbsp;�룺</label><input id="password" name="password" type="password" value="111111" /> <br>
	<br>
	<button id="startup" onclick="startUp()" >���� </button>
	<button id="shutdown" onclick="shutdown()" disabled="disabled">ֹͣ </button>
	<button id="startup" onclick="getStatus()" >״̬��ѯ</button>
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
							alert("���������ɹ�����");
						} else {
							alert("��������ʧ�ܣ�"+result.message);
						}
					} else {
						alert("����ʧ�ܣ���");
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
							alert("ֹͣ����ɹ�����");
							$("#startup").removeAttr("disabled");
							$("#shutdown").attr("disabled","disabled");
						} else {
							alert("ֹͣ����ʧ�ܣ�" + result.message);
						}
					} else {
						alert("����ʧ�ܣ���");
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
						alert("����ʧ�ܣ���");
					}
					
					
					
				}
		);
	}
	
	function check(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		if (userName == "" || password == "") {
			alert("�û���/���� ����Ϊ�գ�");
			return false;
		} else {
			return true;
		}
		
		
	}
	
</script>
</html>