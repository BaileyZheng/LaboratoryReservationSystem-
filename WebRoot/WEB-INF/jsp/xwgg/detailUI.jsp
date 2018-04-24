<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<title>新闻公告详情</title>
</head>
<body>
<div class="bz-panel bz-panel-default" style="text-align: center;">
		<br/>
			<font class="center" size="5">${info.title }</font>
		<div>
		<br/>
		</div>
		<div class="bz-panel-hd">
			<strong>类型：<code>${type }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				来源：<code>${info.source }</code> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				作者：<code>${info.creator }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				创建时间：<code><fmt:formatDate value="${info.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></code>
			</strong>
		</div>
	</div>

	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-bd">
			${info.content }
		</div>
	</div>
</body>
</html>
