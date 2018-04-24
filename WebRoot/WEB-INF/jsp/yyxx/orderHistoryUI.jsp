<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/base.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<title>预约历史</title>
</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">我的预约历史</h3>
		</div>
		<div class="bz-panel-bd">
			<table style="width:98%;text-align:center;">
				<tr style="height:30px;">
					<th>序号</th>
					<th>专业</th>
					<th>班级</th>
					<th>课程</th>
					<th>实验室</th>
					<th>使用时间</th>
					<th>预约时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach var="order" items="${orderList }" varStatus="vs">
					<tr style="height:25px;">
						<td width="30px"><em>${vs.count }</em></td>
						<td width="150px"><em>${order.r_tea.clazz.cname }</em></td>
						<td width="90px"><em>${order.r_tea.clazz.cnumber }</em></td>
						<td width="120px"><em>${order.r_tea.course.CName }</em></td>
						<td width="50px"><em>${order.room.RNumber }</em></td>
						<td width="200px"><em>${order.useTimeStr }</em></td>
						<td width="150px"><em>${order.orderTimeStr }</em></td>
						<td width="70px">
							<em><c:choose>
								<c:when test="${order.OState == '1'}">待审核</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${order.OState=='2' }">
											审核通过
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${order.OState=='0' }">
													审核未通过
												</c:when>
												<c:otherwise>
													失效
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>	
								</c:otherwise>
							</c:choose></em></td>
						<td width="150px">
						<c:choose>
							<c:when test="${order.OState=='2' }"><a href="${pageContext.request.contextPath }/skgl/home_addGradeUI.action?order.orderId=${order.orderId}" target="_blank" class="article"
							><em><i>录入成绩</i></em></a>
							<a href="${pageContext.request.contextPath }/skgl/home_listGradeUI.action?order.orderId=${order.orderId}" target="_blank" class="article"
							><em><i>查看成绩</i></em></a></c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${order.OState=='1' }">
										<a href="${pageContext.request.contextPath}/yyxx/order_cancel.action?order.orderId=${order.orderId}" class="article"
										><em><i>取消预约</i></em></a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath }/yyxx/order_delete.action?order.orderId=${order.orderId}" class="article" 
										><em><i>删除</i></em></a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>


</body>
</html>
