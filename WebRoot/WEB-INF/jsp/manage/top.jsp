<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<link href="${basePath}css/manage/css.css" rel="stylesheet" type="text/css" />
	<link href="${basePath}css/manage/style.css" rel="stylesheet" type="text/css" />
	<link href="${basePath}css/home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function openApp(url) {
		window.top.location = url;
	}
	function delCookie() {
		top.document.cookie = "TopNode=;expires=Fri, 31 Dec 1999 23:59:59 GMT;";
	}
</script>
</head>

<body>
	<!-- 头部{ -->
	<table width="1222" border="0" align="center" cellpadding="0" cellspacing="0" background="${basePath}images/manage/xingzheng.png" class="top">
		<tr>
			<td width="26" height="106">&nbsp;</td>
			<td width="416" height="110" align="left" valign="middle">
				<a href="http://zjg.just.edu.cn/" target="blank"><img class="zxx_test_png" src="${basePath}images/home/logo.gif" width="470" height="90" alt="" /></a>
			</td>
			<td width="135">&nbsp;</td>
			<td width="418">
			</td>
			<td width="300" align="right" valign="top">
				<table width="350" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17" height="9"></td>
                        <td width="66" height="9"></td>
                        <td width="120" height="5"></td>
                        <td width="17" height="9"></td>
						<td width="36" height="9"></td>
						<td width="17"></td>
						<td width="46"></td>
					</tr>
					<tr>
					    <td align="center"></td>
                        <td align="left"></td>
                        <td align="left"><a href="${basePath }grxx/home_frame.action" target="_top"><font color="red">欢迎您，${sessionScope.SYS_USER.name }</font></a></td>
                        <td align="center"><img src="${basePath}images/manage/help.png" width="12"height="17" /></td>
						<td align="left"><a href="javascript:void(0)">帮助</a></td>
						<td width="17" align="center"><img src="${basePath}images/manage/exit.png"width="14" height="14"/></td>
						<td align="left" valign="middle"><a href="${basePath}sys/login_logout.action" target="_top">退出</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!-- }头部 -->
	<!-- 导航{ -->
	<div class="banner">
		<div class="menu">
			<ul class="clearfix">
				<li><a href="${basePath}sys/home.action" target="_top">系统首页</a></li>
		        <li><a href="${basePath}cgzs/home_frame.action" target="_top">成果展示</a></li>
		        <li><a href="${basePath}xwgg/home_frame.action" target="_top">新闻公告</a></li>
		        <c:if test="${SYS_TEACHER!=null}"><li><a href="${basePath}yyxx/home_frame.action" target="_top">预约信息</a></li></c:if>
		        <c:if test="${SYS_ADMIN!=null}"><li class="hover"><a href="${basePath}manage/home_frame.action" target="_top">数据管理</a> </li></c:if>
		        <c:if test="${SYS_TEACHER!=null}"><li><a href="${basePath}skgl/home_frame.action" target="_top">授课管理</a> </li></c:if>
		        <li><a href="${basePath}tjbb/home_frame.action" target="_top">统计报表</a> </li>
		        <c:if test="${SYS_USER!=null}"><li><a href="${basePath}grxx/home_frame.action" target="_top">个人信息</a></li></c:if>
			</ul>
		</div>
	</div>
	<!-- }导航 -->
</body>
</html>
