<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>实验室统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/mycss/css_room.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/reporter/fusioncharts.charts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/reporter/fusioncharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/reporter/fusioncharts.theme.fint.js"></script><script type="text/javascript" src="../../../js/yyxx/layer.js"></script></head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myjs/js_room.js"></script>
<body>
	<div id="div-ct" hidden>${chartType }</div>
	<div id="div-pd" hidden>${period }</div>
	<div id="div-st" hidden>${statType }</div>
	<div id="div-dt" hidden>${detailTime }</div>
	<div id="div-caption" hidden>${caption }</div>
	<div id="div-subCaption" hidden>${subCaption }</div>
	<div id="div-xName" hidden>${xName }</div>
	<div id="div-yName" hidden>${yName }</div>
	<div id="div-operator">
		<s:select id="roomId" name="room.roomId" list="roomList"  listKey="roomId" listValue="RNumber" headerKey="0" headerValue="---请选择实验室---" onchange="roomChange(this)"></s:select>&nbsp;&nbsp;
		<s:select id="statType" name="statType" list="statTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="---请选择统计类别---" onchange="statTypeChange(this)"></s:select>&nbsp;&nbsp;
		<s:select id="chartType" name="chartType" list="chartTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="---请选择图表类型---" onchange="chartTypeChange(this)"></s:select>&nbsp;&nbsp;
		<s:if test="room==null||room.roomId=='0'">
			<s:select id="period" name="period" list="periodMap" listKey="key" listValue="value" headerKey="0" headerValue="---请选择统计时间段---" onchange="periodChange(this)"></s:select>&nbsp;&nbsp;
		</s:if>
		<s:else>
			<s:select id="period" name="period"  list="#{'day':'按天统计','week':'按周统计','month':'按月统计' }" headerKey="0" headerValue="---请选择统计时间段---" onchange="roomFixPeriodChange(this)"></s:select>&nbsp;&nbsp;
		</s:else>
		<s:if test="detailTime!=null&&detailTime!=''&&detailTime!='0'">
			<select id="detailTime" name="detailTime" onchange="detailTimeChange(this);">
				<s:if test="period=='week'">
					<option value="0">--请选择周--</option>
					<c:forEach var="i" begin="1" end="20">
						<option value="${i }" <c:if test="${i==detailTime }">selected="selected"</c:if>>第${i }周</option>
					</c:forEach>
				</s:if>
				<s:if test="period=='month'">
					<option value="0">--请选择月--</option>
					<c:forEach var="i" begin="1" end="12">
						<option value="${i }" <c:if test="${i==detailTime }">selected="selected"</c:if>>${i }月</option>
					</c:forEach>
				</s:if>
			</select> 
		</s:if>
	</div>
	<div id="chartContainer" class="chart"></div>
	<div id="lineChartContainer" class="chart"></div>
	<div id="pieChartContainer" class="chart"></div>
</body>
</html>
