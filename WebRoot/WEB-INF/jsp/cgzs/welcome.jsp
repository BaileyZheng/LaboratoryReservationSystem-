<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>实验室介绍</title>
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">

</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">实验室介绍</h3>
		</div>
		<div class="bz-panel-bd">
			<table style="width:98%;text-align:center;">
				<tr style="height:30px;">
					<th>序号</th>
					<th>实验室类型</th>
					<th>实验室名</th>
					<th>实验室号</th>
					<th>容量</th>
					<th>详情</th>
					<th>操作</th>
				</tr>
				<c:forEach var="room" items="${roomList }" varStatus="vs">
					<tr style="height:25px;">
						<td width="30px"><em>${vs.count }</em></td>
						<td width="160px"><em>${map[room.RType] }</em> </td>
						<td width="160px"><a href="${pageContext.request.contextPath }/cgzs/home_detailUI.action?id=${room.roomId}" class="article"
						title="${room.RName }"><em>${room.RName }</em></a></td>
						<td width="60px"><a href="${pageContext.request.contextPath }/cgzs/home_detailUI.action?id=${room.roomId}" class="article"
						title="${room.RNumber }"><em>${room.RNumber }</em></a></td>
						<td width="160px"><em>${room.RCapacity }</em></td>
						<td width="80px"><a href="${pageContext.request.contextPath }/cgzs/home_detailUI.action?id=${room.roomId}" class="article"
						title="${room.RName }"><em><i>详情</i></em></a></td>
						<td width="120px"><a href="${pageContext.request.contextPath }/yyxx/order_orderFrame.action?id=${room.roomId}" target="_blank" class="article"
						title="${room.RName }"><em><i>我要预约</i></em></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
