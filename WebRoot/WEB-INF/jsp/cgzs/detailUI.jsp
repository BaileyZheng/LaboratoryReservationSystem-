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
<title>实验室介绍</title>
</head>
<body>
<div class="bz-panel bz-panel-default" style="text-align: center;">
		<br/>
			<font class="center" size="5">实验室介绍--${room.RName }</font>
		<div>
		<br/>
		</div>
		<div class="bz-panel-hd">
			<a class="left" style="cursor:pointer;" onclick="javascript:history.go(-1);">&lt;&nbsp;返回</a>
			<strong>实验室号：<code>${room.RNumber }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室名：<code>${room.RName}</code> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室容量：<code>${room.RCapacity }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室类型：<code>${map[room.RType]}</code>
			</strong>
			<a class="right" target="_blank" href="${pageContext.request.contextPath }/yyxx/order_orderFrame.action?room.roomId=${room.roomId}">我要预约&nbsp;&gt;</a>
		</div>
	</div>

	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-bd">
			${room.RMemo }
		</div>
	</div>
</body>
</html>
