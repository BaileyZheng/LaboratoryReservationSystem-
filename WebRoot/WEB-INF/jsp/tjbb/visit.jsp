<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
<title>系统访问量统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/mycss/css_room.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/reporter/fusioncharts.charts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/reporter/fusioncharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/reporter/fusioncharts.theme.fint.js"></script><script type="text/javascript" src="../../../js/yyxx/layer.js"></script></head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myjs/js_visit.js"></script>
<body>
	<div id="div-ct" hidden>${chartType }</div>
	<div id="div-pd" hidden>${period }</div>
	<div id="div-subCaption" hidden>${subCaption }</div>
	<div id="div-operator">
		<s:select id="chartType" name="chartType" list="chartTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="---请选择图表类型---" onchange="chartTypeChange(this)"></s:select>&nbsp;&nbsp;
		<s:select id="period" name="period"  list="#{'day':'按天统计','week':'按周统计','month':'按月统计' }" headerKey="0" headerValue="---请选择统计时间段---" onchange="periodChange(this)"></s:select>&nbsp;&nbsp;
	</div>
	<div id="chartContainer" class="chart"></div>
	<div id="lineChartContainer" class="chart"></div>
	<div id="pieChartContainer" class="chart"></div>
</body>
</html>
