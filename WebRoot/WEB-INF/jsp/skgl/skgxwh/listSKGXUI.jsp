<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<title>授课关系维护</title>
<script type="text/javascript">
		var vResult = false;
		
		function verifyAccount(){
			var cour = $("#cour");
			var clz = $("#clz");
			var ter = $("#ter");
			$.ajax({
				url:"${pageContext.request.contextPath}/skgl/home_verify.action",
				type:"post",
				async:false,
				data:{"course.courseId":cour.val(),"clazz.clazzId":clz.val(),"relation.term":ter.val()},
				success:function(msg){
					if("true"==msg){
						alert("该授课关系已经存在，请不要重复添加！");
					}else{
						vResult = true
					}
				}
			});
		}
		function doSubmit(){
			var cour = $("#cour");
			var clz = $("#clz");
			var ter = $("#ter");
			if(cour.val()=="0"){
				alert("请选择课程!");
				account.focus();
				return false;
			}
			if(clz.val()=="0"){
				alert("请选择班级！");
				clz.focus();
				return false;
			}
			if(ter.val()=="0"){
				alert("请选择学期！");
				clz.focus();
				return false;
			}
			//verifyAccount();
			if(vResult){
				document.forms[0].submit();
			}
		}
	</script>
</head>
<body>
	<!-- 添加授课关系 -->
	<div class="bz-panel bz-panel-default" style="text-align: center;">
		<br />
		<div class="bz-panel-bd">
			<div class="bz-form bz-form-aligned">
				<div class="bz-control-group center">
				<form id="form" name="form" action="${pageContext.request.contextPath }/skgl/home_addSkgx.action" method="post">
					<label for="moudle"><em>请选择授课关系：</em> </label>
					<s:select id="clz" name="clazz.clazzId" headerKey="0"
						headerValue="---请选择班级---" list="clazzList" listKey="clazzId"
						listValue="cnumber"></s:select>
					&nbsp;
					<s:select id="cour" name="course.courseId" headerKey="0"
						headerValue="---请选择课程---" list="courseList" listKey="courseId"
						listValue="CNumber"></s:select>
					&nbsp;
					<s:select id="ter" name="relation.term" headerKey="0"
						headerValue="---请选择学期---" list="termList"></s:select>
					&nbsp;
					<button class="bz-button bz-button-primary"
						onclick="doSubmit()">添加</button>
					</form>
				</div>
			</div>
		</div>
		<div class="bz-panel-hd">
			<strong><code>${studyYearStr }</code>学年 第<code>${termInYear }</code>学期， 当前第 <code>${weekNum }</code> 周（日期：${firstDay } ~ ${lastDay}）
			</strong>
		</div>
	</div>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">我的授课关系</h3>
		</div>
		<div class="bz-panel-bd">
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
							<td width="50px"><em>${vs.count }</em>
							</td>
							<td width="150px"><em>${relation.course.CNumber }</em></td>
							<td width="150px"><em>${relation.course.CName }</em>
							</td>
							<td width="150px"><em>${relation.clazz.cnumber }</em>
							</td>
							<td width="180px"><em>${relation.clazz.cname }</em>
							</td>
							<td width="150px"><em>${relation.term }</em>
							</td>
							<td width="350px"><a
								href="${pageContext.request.contextPath }/yyxx/order_orderFrame.action?r_tea.id=${relation.id}"
								target="_blank" class="article"><em><i>预约实验室</i>
								</em>
							</a><a
								href="${pageContext.request.contextPath }/skgl/home_listGradeUI.action?relation.id=${relation.id}"
								class="article"><em><i>查看课程成绩</i>
								</em>
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
