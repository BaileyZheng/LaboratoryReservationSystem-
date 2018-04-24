<%@ page language="java" import="java.util.*,edu.just.reservation.core.constant.Constant,edu.just.reservation.manage.order.entity.Orders,edu.just.reservation.manage.teacher.entity.Teacher" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="UTF-8">
<title>实验室预约页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="author" content="ROC">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/yyxx/layer.js"></script>
<style type="text/css">
.cancelText{
	color:white;
	background-color:#02CF21;
	border-radius:5px;
	height:20px;
	width:100%;
	border:0px;
}
</style>
</head>
<body>
	<!-- 预约信息筛选区 -->
	<div class="bz-panel bz-panel-default" style="text-align: center;">
		<br />
		<div class="bz-panel-bd">
			<div class="bz-form bz-form-aligned">
				<div class="bz-control-group">
					<label for="moudle"><em>请选择查询筛选条件：</em> </label>
					<s:select id="type" name="room.RType" headerKey="0"
						headerValue="---请选择实验室类型---" list="roomTypeMap"
						onchange="changeType()"></s:select>
					&nbsp;
					<s:select id="rm" name="room.roomId" list="roomList"
						listKey="roomId" listValue="RNumber" headerKey="0"
						headerValue="---请选择实验室---"></s:select>
					&nbsp;
					<button class="bz-button bz-button-primary" onclick="goSearch()">
						查询</button>
				</div>
			</div>
			<ul class="bz-pagination" style="text-align: center;">
				<li>请选择预约周次：</li>
				<c:forEach var="i" begin="${weekNum }" end="20">
					<c:choose>
						<c:when test="${i==selectedWeek }">
							<li class="bz-active"><c:choose>
									<c:when test="${room!=null&&room.roomId!='' }">
										<a
											href="${pageContext.request.contextPath }/yyxx/order_toOrderUI.action?selectedWeek=${i}&room.roomId=${room.roomId }"
											onclick="changeWeek(this)">${i }</a>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath }/yyxx/order_toOrderUI.action?selectedWeek=${i}"
											onclick="changeWeek(this)">${i }</a>
									</c:otherwise>
								</c:choose></li>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${room!=null&&room.roomId!='' }">
									<li><a
										href="${pageContext.request.contextPath }/yyxx/order_toOrderUI.action?selectedWeek=${i}&room.roomId=${room.roomId }"
										onclick="changeWeek(this)">${i }</a>
									</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/yyxx/order_toOrderUI.action?selectedWeek=${i}"
										onclick="changeWeek(this)">${i }</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<div class="bz-panel-hd">
			<strong> 当前第 <code>${weekNum }</code> 周（日期：${firstDay } ~
				${lastDay }） <c:if test="${room!=null }">
				正在查询 <code>${map[room.RType] }: ${room.RNumber }</code>
				</c:if> ，预约周次：第 <code>
					<c:choose>
						<c:when test="${selectedWeek!=null }">${selectedWeek }</c:when>
						<c:otherwise>${weekNum }</c:otherwise>
					</c:choose>
				</code> 周（ <c:choose>
					<c:when test="${o_firstDay!=null }">${o_firstDay }</c:when>
					<c:otherwise>${firstDay }</c:otherwise>
				</c:choose> ~ <c:choose>
					<c:when test="${o_lastDay!=null }">${o_lastDay }</c:when>
					<c:otherwise>${lastDay }</c:otherwise>
				</c:choose>） </strong>
		</div>
	</div>

	<!-- 预约筛选查询结果区 -->
	<table id="tb-o" class="bz-table">
		<thead>
			<tr>
				<th></th>
				<th>星期一</th>
				<th>星期二</th>
				<th>星期三</th>
				<th>星期四</th>
				<th>星期五</th>
				<th>星期六</th>
				<th>星期日</th>
			</tr>
		</thead>
		<tbody>
		<%
			Map<Integer,List<Orders>> o_map = (Map<Integer,List<Orders>>)request.getAttribute("ordersMap");
			Teacher teacher = (Teacher)request.getSession().getAttribute(Constant.TEACHER);
			int weekday = (Integer)request.getAttribute("weekday");
			int weekNum = (Integer)request.getAttribute("weekNum");
			int selectedWeek = Integer.parseInt((String)request.getAttribute("selectedWeek"));
			for(int i = 1;i<=5;i++){
		%>
			<tr>
				<td class="leftTd"><%=i*2-1%> ~ <%=i*2 %> </td>
		<%
				for(int j = 1;j<=7;j++){
		%>
				<td id="${selectedWeek}-<%=i %>-<%=j %>-${room.RNumber}"
		<%
				List<Orders> os = null;
				if(o_map!=null){
					os = o_map.get(j-1);
				}
				Orders this_o = null;
				if(os!=null){
					for(int k = 0;k<os.size();k++){
						if(os.get(k).getUseTime()==i){
							this_o = os.get(k);
							if(this_o.getR_tea().getTeacher().getId().equals(teacher.getId())){//本人
								if(this_o.getOState().equals(Orders.ORDER_STATE_APPLYED)&&((j>=weekday&&weekNum==selectedWeek)||weekNum!=selectedWeek)){
		%>
									style="background-color:pink;"
		<%							
								}else if(this_o.getOState().equals(Orders.ORDER_STATE_APPLYED)){
		%>	
									class="selected"
		<%							
								}else if(this_o.getOState().equals(Orders.ORDER_STATE_CHECKED)){
		%>
									style="background-color:#FAFAA0;"
		<%							
								}else{//本人申请不通过或失效
									if(j<weekday&&weekNum==selectedWeek){
		%>	
										class="selected"
		<%								
									}else{
		%>		
										class="unSelected" onclick="prepareOrder(this)" onmouseenter="showPointLetter(this)" onmouseleave="removeLetter(this)"
		<%						
									}							
								}
		
							}else if(this_o.getOState().equals(Orders.ORDER_STATE_CHECKED)){//非本人预约成功
		%>	
								class="selected"
		<%			
							}else{//非本人预约不成功
								if(j<weekday&&weekNum==selectedWeek){
		%>	
									class="selected"
		<%								
								}else{
		%>		
								class="unSelected" onclick="prepareOrder(this)" onmouseenter="showPointLetter(this)" onmouseleave="removeLetter(this)"
		<%						
							
								}
							}
						}
					}
				}
				if(this_o==null){
					if(j<weekday&&weekNum==selectedWeek){
		%>
					class="selected"
		<%				
					}else{
		%>
					class="unSelected" onclick="prepareOrder(this)" onmouseenter="showPointLetter(this)" onmouseleave="removeLetter(this)"
		<%
					}
				}
		%>
				>
		<% 
				if(this_o!=null){
					if(this_o.getR_tea().getTeacher().getId().equals(teacher.getId())){//本人
						if(this_o.getOState().equals(Orders.ORDER_STATE_APPLYED)&&((j>=weekday&&weekNum==selectedWeek)||weekNum!=selectedWeek)){//待审核
		%>
							<strong>信息审核中</strong><br/>
							<%=this_o.getR_tea().getCourse().getCName() %><br/>
							<%=this_o.getR_tea().getClazz().getCname() %><br/>
							<br/><button id="<%=this_o.getOrderId() %>" onclick="cancelApply(this)" class="cancelText">取消申请</button>
		<%
						}else if(this_o.getOState().equals(Orders.ORDER_STATE_CHECKED)){//审核通过
		%>						
							<strong>预约成功</strong><br/>
							<%=this_o.getR_tea().getCourse().getCName() %><br/>
							<%=this_o.getR_tea().getClazz().getCname() %><br/>
		<% 
							if((j>=weekday&&weekNum==selectedWeek)||weekNum!=selectedWeek){
		%>
							<br/><button id="<%=this_o.getOrderId() %>" onclick="cancelApply(this)" class="cancelText">取消预约</button>
							
		<%				
							}
						}
					}else if(this_o.getOState().equals(Orders.ORDER_STATE_CHECKED)){//非本人预约成功
		%>
						<strong><%=this_o.getR_tea().getCourse().getCName() %></strong><br/>
						<%=this_o.getR_tea().getClazz().getCname() %><br/>
						<%=this_o.getR_tea().getTeacher().getName() %>
		<% 	
					}
				}
		%>
				</td>
		<% 
				}
		%>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
	<div>
		<br />
	</div>
</body>
<script type="text/javascript">
	function changeType() {
		var type = $("#type");
		$("#rm").empty();
		$("#rm").append($('<option value="0">---请选择实验室---</option>'));
		if (type.val() == "0") {
			alert("请选择实验室类型！");
			$("#type").focus();
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/yyxx/order_getRooms.action",
			type : "post",
			async : false,
			data : {"room.RType" : type.val()},
			success : function(res) {
				var arr = eval(res);
				$(arr).each(
					function(index, entity) {
						$("#rm").append($('<option value="'+entity['roomId']+'">'+ entity['RNumber']+ '</option>'));
					});
			}
		});
	}
	function goSearch() {
		window.location.href = "${pageContext.request.contextPath}/yyxx/order_searchRoomInfo.action?room.roomId="
				+ $("#rm").val() + "&selectedWeek=" + $(".bz-active a").html();
	}
	function changeWeek(e) {
		$(".bz-active").attr("class", "");
		$(e).attr("class", ".bz-active");
	}
	function showPointLetter(o) {
		$(o).html('<strong>点击预约</strong>');
	}
	function removeLetter(o) {
		$(o).html('');
	}
	function prepareOrder(o) {
		var strs = $(o).attr("id").split('-');
		var w = strs[0];
		var t = strs[1];
		var d = strs[2];
		var r = strs[3];
		if(r==""){
			alert("请选择实验室！");
			return false;
		}
		var height = $("#tb-o").height()-30;
		$.post("${pageContext.request.contextPath}/yyxx/order_getTeachRelation.action",
		{"selectedWeek":w,"useDay":d,"useTime":t},
		function(res) {
			var msg = eval(res);
			if(!msg){
				alert("不能在同一时间预约多个实验室哦~");
				return false;
			}
			content_str = '<option value="0">---请选择班级---</option>';
			for ( var key in res[0]) {
				content_str += "<option value='"+key+"'>"+ res[0][key] + "</option>";
			}
			layer.open({
				type : 1,
				area : [ '490px', '220px' ],
				shadeClose : true,
				title : '',
				content : '<div id="floatFlow" style="position:absolute;left:125px;top:242px;background-color:rgba(216,216,216,0.5);width:85%;height:'+height+'px;z-index:2;padding-top:50px;" class="bz-form bz-form-aligned"><center>正在预约：<strong><code>'
						+ r	+ '</code>实验室，第<code>'+ w+ '</code>周星期<code>'+ d+ '</code>，第<code>'+ (2 * t - 1)
						+ '~'+ 2* t+ '</code>节课</strong></center><fieldset style="margin-left:200px;margin-top:20px;"><div class="bz-control-group"><label for="name">选择班级</label><select name="teachclass" id="teachclass" onchange="getCourse();">'
						+ content_str+ '</select></div><div class="bz-control-group"><label for="name">选择课程</label><select name="lessonname" id="lessonname"><option value="0">---请选择课程---</option></select></div><div style="margin-left:100px;margin-top:30px;"><button class="bz-button bz-button-primary" id="doCancel-btn" onclick="doCancel();">取消预约</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="bz-button bz-button-primary" id="doSign-btn" onclick="doOrder('
						+ w+ ','+ d+ ','+ t+ ',\''+ r+ '\');">确认预约</button></div></fieldset></div>'
			});
		}, 'json');
	}
	function doCancel() {
		$("#floatFlow").remove();
	}
	function getCourse() {
		var clz = $("#teachclass").val();
		$("#lessonname").empty();
		$("#lessonname").append($('<option value="0">---请选择课程---</option>'));
		$.ajax({
			url : "${pageContext.request.contextPath}/yyxx/order_getCourse.action",
			type : "post",
			async : false,
			data : {"clazz.clazzId" : clz},
			success : function(res) {
				var arr = eval(res);
				for ( var key in arr[0]) {
					$("#lessonname").append($('<option value="'+key+'">' + arr[0][key]+ '</option>'));
				}
			}
		});
	}
	function doOrder(w, d, t, r) {
		var clz = $("#teachclass").val();
		var cou = $("#lessonname").val();
		var rid = $("#rm").val();
		window.location.href = "${pageContext.request.contextPath}/yyxx/order_signOrder.action?room.roomId="
				+ rid+ "&selectedWeek="+ w+ "&useDay="+ d+ "&useTime="+ t+ "&clazz.clazzId=" + clz + "&course.courseId=" + cou;
	}
	function cancelApply(obj){
		var oid = $(obj).attr("id");
		window.location.href = "${pageContext.request.contextPath}/yyxx/order_cancel.action?room.roomId="
			+ $("#rm").val() + "&selectedWeek=" + $(".bz-active a").html()+"&order.orderId="+oid;
	}
</script>
</html>
