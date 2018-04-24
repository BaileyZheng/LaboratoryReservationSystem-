<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="UTF-8">
<title>所有预约信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="author" content="ROC">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">

</head>
<body>
<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">所有预约信息</h3>
		</div>
		<div class="bz-panel-bd">
			<table style="width:98%;text-align:center;">
				<tr style="height:30px;">
					<th>序号</th>
					<th>教师</th>
					<th>专业</th>
					<th>班级</th>
					<th>课程</th>
					<th>实验室</th>
					<th>使用时间</th>
					<th>预约时间</th>
					<th>状态</th>
				</tr>
				<c:forEach var="order" items="${orderList }" varStatus="vs">
					<tr style="height:25px;">
						<td width="30px"><em>${vs.count }</em></td>
						<td width="60px"><em>${order.r_tea.teacher.name }</em></td>
						<td width="150px"><em>${order.r_tea.clazz.cname }</em></td>
						<td width="100px"><em>${order.r_tea.clazz.cnumber }</em></td>
						<td width="120px"><em>${order.r_tea.course.CName }</em></td>
						<td width="50px"><em>${order.room.RNumber }</em></td>
						<td width="220px"><em>${order.useTimeStr }</em></td>
						<td width="150px"><em>${order.orderTimeStr }</em></td>
						<td width="80px"><em><c:choose><c:when test="${order.OState == '1'}">待审核</c:when><c:otherwise>审核通过</c:otherwise></c:choose></em></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>