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
<title>本人所有授课关系</title>
</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">本人所有授课关系</h3>
		</div>
		<div class="bz-panel-bd">
			<table style="width:98%;text-align:center;">
				<tr style="height:30px;">
					<th>序号</th>
					<th>课程号</th>
					<th>课程名</th>
					<th>班级代号</th>
					<th>专业</th>
					<th>授课学期</th>
					<th>操作</th>
				</tr>
				<c:forEach var="relation" items="${relationList }" varStatus="vs">
					<tr style="height:25px;">
						<td width="40px"><em>${vs.count }</em></td>
						<td width="150px"><em>${relation.course.CNumber }</em> </td>
						<td width="150px"><em>${relation.course.CName }</em></td>
						<td width="150px"><em>${relation.clazz.cnumber }</em></td>
						<td width="150px"><em>${relation.clazz.cname }</em></td>
						<td width="150px"><em>${relation.term }</em></td>
						<td width="350px"><a href="${pageContext.request.contextPath }/yyxx/order_orderFrame.action?r_tea.id=${relation.id}" target="_blank" class="article"
						><em><i>预约实验室</i></em></a>
						<a href="${pageContext.request.contextPath }/skgl/home_listGradeUI.action?relation.id=${relation.id}" class="article"
						><em><i>查看课程成绩</i></em></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
