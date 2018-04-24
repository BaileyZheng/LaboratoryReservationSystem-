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
	<div class="bz-panel-hd">
		<a class="left" href="${pageContext.request.contextPath }/cgzs/home_welcome.action?">&nbsp;&lt;&nbsp;其他</a>
		<strong>实验室号：<code>${room.RNumber }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室名：<code>${room.RName}</code> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室容量：<code>${room.RCapacity }</code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			实验室类型：<code>${map[room.RType] }</code>
		</strong>
		<a class="right" href="${pageContext.request.contextPath }/cgzs/home_detailUI.action?id=${room.roomId}">详情&nbsp;&gt;&nbsp;</a>
	</div>
	<div>
		<br/>
		<img  width="95%" src="${pageContext.request.contextPath }/upload/${room.RImgPath }"/>
		<br/>
	</div>
	</div>
</body>
</html>
