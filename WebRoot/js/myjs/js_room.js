var dataMap;
var caption;
var subCaption;
var xName;
var yName;
var cType="column3d";
$(document).ready(function(){
	$(".chart").hide();
	cType = $("#div-ct").html();
	caption = $("#div-caption").html();
	subCaption = $("#div-subCaption").html();
	xName = $("#div-xName").html();
	yName = $("#div-yName").html();
	if(cType==""){
		cType="column3d";
	}
	if(caption==""){
		caption = "实验室使用时长统计";
	}
	if(subCaption==""){
		subCaption = "本周报表";
	}
	if(xName==""){
		xName = "实验室";
	}
	if(yName==""){
		yName="使用时长";
	}
	var roomId = $("#roomId").val();
	if(roomId=="0"){
		roomId = null;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/tjbb/home_getData.action",
		type:"post",
		data:{"period":$("#div-pd").html(),"detailTime":$("#div-dt").html(),"statType":$("#div-st").html(),"room.roomId":roomId},
		async:false,
		success:function(res){
			var arr = eval(res);
			dataMap = new Array();
			var i = 0;
			for(var key in arr[0]){
				dataMap[i++]={'label':key,'value':arr[0][key]};
			}
		}
	});	
	switch(cType){
	case "column2d":;
	case "column3d":$(".chart").hide();$("#chartContainer").show();break;
	case "pie2d":;
	case "pie3d":$(".chart").hide();$("#pieChartContainer").show();break;
	case "line":$(".chart").hide();$("#lineChartContainer").show();break;
	default:cType="column3d";$(".chart").hide();$("#chartContainer").show();break;
	}
});
function roomChange(obj){
	var roomId = $(obj).val();
	if(roomId=="0"){
		alert('请选择实验室！');
		$(obj).focus();
	}
	var statType = $("#statType").val();
	var period = $("#period").val();
	var chartType = $("#chartType").val();
	$("#period").remove();
	$("#div-operator").append('<select id="period" name="period" onchange="roomFixPeriodChange(this);"></select>');
	$('<option value="0">--请选择统计时间--</option>').appendTo($("#period"));
	$('<option value="day">按天统计</option>').appendTo($("#period"));
	$('<option value="week">按周统计</option>').appendTo($("#period"));
	$('<option value="month">按月统计</option>').appendTo($("#period"));
	window.location="${pageContext.request.contextPath}/tjbb/home_room.action?room.roomId="+roomId+"&statType="+statType+"&period="+period+"&chartType="+chartType;
}

function roomFixPeriodChange(obj){
	var roomId = $("#roomId").val();
	var chartType = $("#chartType").val();
	var statType = $("#statType").val();
	var period = $(obj).val();
	window.location="${pageContext.request.contextPath}/tjbb/home_room.action?period="+period+"&room.roomId="+roomId+"&statType="+statType+"&chartType="+chartType;
}

function statTypeChange(obj){
	var statType = $(obj).val();
	if(statType=="0"){
		alert("请选择统计类型！");
		$(obj).focus();
	}
	var roomId = $("#roomId").val();
	var period = $("#period").val();
	var chartType = $("#chartType").val();
	window.location="${pageContext.request.contextPath}/tjbb/home_room.action?statType="+statType+"&room.roomId="+roomId+"&period="+period+"&chartType="+chartType;
}
function periodChange(obj){
	var period = $(obj).val();
	$("#detailTime").remove();
	if(period=="0"){
		alert("请选择统计时间！");
		$(obj).focus();
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/tjbb/home_getDetailTimeMaps.action",
		type:"POST",
		data:{"period":period},
		async:false,
		success:function(res){
			res = eval(res);
			var i = 0;
			for(var key in res[0]){
				if(i==0){
					$("#div-operator").append('<select id="detailTime" name="detailTime" onchange="detailTimeChange(this);"></select>');
				}
				i++;
				$('<option value="'+key+'">'+res[0][key]+'</option>').appendTo($("#detailTime"));
			}
		}
	});
	var roomId = $("#roomId").val();
	var chartType = $("#chartType").val();
	var statType = $("#statType").val();
	if(period=="day"||period=="year"){
		window.location="${pageContext.request.contextPath}/tjbb/home_room.action?period="+period+"&room.roomId="+roomId+"&statType="+statType+"&chartType="+chartType;
	}
}

function detailTimeChange(obj){
	var detailTime = $(obj).val();
	var roomId = $("#roomId").val();
	var chartType = $("#chartType").val();
	var statType = $("#statType").val();
	var period = $("#period").val();
	window.location = "${pageContext.request.contextPath}/tjbb/home_room.action?period="+period+"&room.roomId="+roomId+"&statType="+statType+"&chartType="+chartType+"&detailTime="+detailTime;
}

function chartTypeChange(obj){
	var chartType = $(obj).val();
	if(chartType=="0"){
		alert("请选择图表类型！");
		$(obj).focus();
	}
	var roomId = $("#roomId").val();
	var statType = $("#statType").val();
	var period = $("#period").val();
	window.location="${pageContext.request.contextPath}/tjbb/home_room.action?chartType="+chartType+"&room.roomId="+roomId+"&statType="+statType+"&period="+period;		
}
FusionCharts.ready(function() {
	var revenueChart = new FusionCharts({
		"type" : cType,
		"renderAt" : "chartContainer",
		"width" : "80%",
		"height" : "100%",
		"dataFormat" : "json",
		"dataSource" : {
			"chart" : {
				"caption" : caption,
				"subCaption" : subCaption,
				"xAxisName" : xName,
				"yAxisName" : yName,
				"theme" : "fint",
			},
			"data" : dataMap
		}

	}).render();
	var pieChart = new FusionCharts({
		"type" : cType,
		"renderAt" : "pieChartContainer",
		"width" : "80%",
		"height" : "100%",
		"dataFormat" : "json",
		"dataSource" : {
			 "chart": {
	                "caption": caption,
	                "subCaption":subCaption ,
	                "showPercentValues": "1",
	                "showPercentInTooltip": "0",
	                "decimals": "1",
	                "theme":"fint",
	            },
	            "data": dataMap
	        }
	    }).render();
	 var lineChart = new FusionCharts({
	        type: 'line',
	        renderAt: 'lineChartContainer',
	        width: '80%',
	        height: '100%',
	        dataFormat: 'json',
	        dataSource: {
	            "chart": {
	                "caption": caption,
	                "subCaption": subCaption,
	                "xAxisName": xName,
	                "yAxisName": yName,
	                "theme":"fint",
	            },
	            "data": dataMap
	          
	        }
	    }).render();
})
	