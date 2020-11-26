<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%//浏览器不缓存 
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Expires","0");
%>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>租房网 - 用户登录</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
</div>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<div class="box">
			<h4>用户登录</h4>
			<s:form action="login">
				<div class="infos">
					<table class="field">							
						<tr>
							<td colspan="2" ><s:fielderror/><s:property value="message"/></td>
						</tr>
						<tr>
							<td class="field">用 户 名：</td>
							<td><!-- <input type="text" class="text" name="name" /> -->
								<s:textfield name="username" cssClass="text" required="true"/>
							</td>
						</tr>
						<tr>
							<td class="field">密　　码：</td>
							<td><!-- <input type="password" class="text" name="password" /> -->
							<s:password name="password" cssClass="text" required="true"/>
							</td>
						</tr>
					</table>
					<div class="buttons"><s:submit value="立即登录" /></div>
				</div>
			</s:form>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	<dl>
        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
    </dl>
</div>
</body>
</html>

