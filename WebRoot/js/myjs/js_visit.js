var dataMap;
var subCaption;
var cType="column3d";
$(document).ready(function(){
	$(".chart").hide();
	cType = $("#div-ct").html();
	subCaption = $("#div-subCaption").html();
	if(cType==""){
		cType="column3d";
	}
	if(subCaption==""){
		subCaption = "本周报表";
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/tjbb/home_getVisitData.action",
		type:"post",
		data:{"period":$("#div-pd").html()},
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

function periodChange(obj){
	var period = $(obj).val();
	if(period=="0"){
		alert("请选择统计时间！");
		$(obj).focus();
	}
	var chartType = $("#chartType").val();
	window.location="${pageContext.request.contextPath}/tjbb/home_visit.action?period="+period+"&chartType="+chartType;
}

function chartTypeChange(obj){
	var chartType = $(obj).val();
	if(chartType=="0"){
		alert("请选择图表类型！");
		$(obj).focus();
	}
	var period = $("#period").val();
	window.location="${pageContext.request.contextPath}/tjbb/home_visit.action?chartType="+chartType+"&period="+period;		
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
				"caption" : "系统访问量统计",
				"subCaption" : subCaption,
				"xAxisName" : "时间",
				"yAxisName" : "访问量",
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
	                "caption": "系统访问量统计",
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
	                "caption": "系统访问量统计",
	                "subCaption": subCaption,
	                "xAxisName": "时间",
	                "yAxisName": "访问量",
	                "theme":"fint",
	            },
	            "data": dataMap
	          
	        }
	    }).render();
})
	