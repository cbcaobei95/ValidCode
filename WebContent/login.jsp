<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<style>
body {
	text-align: center;
}

#changeimg {
	margin-top: 0px;
	width: 74px;
	height: 18px;
}

.all {
	margin-top: 100px;
}

input {
	background-color: transparent;
}

.myButton {
	width: 75px;
}
</style>
<script type="text/javascript">
	function changeImg() {//验证码图片更改
		var imgSrc = $("#changeimg");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", chgUrl(src));
	}

	//时间戳   
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
	function chgUrl(url) {
		if ((url.indexOf("?") < 0)) { //检查url中是否有?,没有则添加.  
			url = url + "?";
		} else { //有?,则添加&,传多个参数.
			url = url + "&";
		}
		url = url + "tm=" + Math.random(); //参数名tm,值随机  
		return url; //返回新的url,改变原来图片的src. 
	}
</script>
<body>
	<div class="all">
		<form method="post" action="./Check">
			<table cellpadding="5" align="center">
				<tr>
					<td>请输入验证码:</td>
					<td><input type="text" id="checkcode" class="text" size="7" name="checkcode">
						<img src="CheckCode" id="changeimg"
						onclick="changeImg()"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="myButton" value="提交"/>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>