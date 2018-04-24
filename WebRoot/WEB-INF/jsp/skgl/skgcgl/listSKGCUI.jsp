<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/base.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<title>授课过程管理</title>
</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">授课过程管理</h3>
		</div>
		<div class="bz-panel-bd">
			<table style="width:98%;text-align:center;">
				<tr style="height:30px;">
					<th>序号</th>
					<th>实验室</th>
					<th>课程名</th>
					<th>班级</th>
					<th>专业</th>
					<th>上课时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="order" items="${list }" varStatus="vs">
					<tr style="height:25px;">
						<td width="40px"><em>${vs.count }</em></td>
						<td width="80px"><em>${order.room.RNumber }</em> </td>
						<td width="120px"><em>${order.r_tea.course.CName }</em> </td>
						<td width="100px"><em>${order.r_tea.clazz.cnumber }</em></td>
						<td width="150px"><em>${order.r_tea.clazz.cname }</em></td>
						<td width="200px"><em>${order.useTimeStr }</em></td>
						<td width="200px">
						<a href="${pageContext.request.contextPath }/skgl/home_addGradeUI.action?order.orderId=${order.orderId}" class="article"
						><em><i>录入成绩</i></em></a>
						<a href="${pageContext.request.contextPath }/skgl/home_listGradeUI.action?order.orderId=${order.orderId}" class="article"
						><em><i>查看成绩</i></em></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
