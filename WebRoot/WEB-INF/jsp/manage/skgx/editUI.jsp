<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>授课关系管理</title>
</head>
	<script type="text/javascript">
	var vResult = false;
	
	function verifyAccount(){
		var tea = $("#tea");
		var cour = $("#cour");
		var clz = $("#clz");
		var ter = $("#ter");
		$.ajax({
			url:"${pageContext.request.contextPath}/manage/teach_verifyAccount.action",
			type:"post",
			async:false,
			data:{"teacher.id":tea.val(),"course.courseId":cour.val(),"clazz.clazzId":clz.val(),"teachRelation.term":ter.val()},
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
		var tea = $("#tea");
		var cour = $("#cour");
		var clz = $("#clz");
		var ter = $("#ter");
		if(tea.val()=="0"){
			alert("请选择教师！");
			name.focus();
			return false;
		}
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
		verifyAccount();
		if(vResult){
			document.forms[0].submit();
		}
	}
	</script>
<body class="rightBody">
<form id="form" name="form" action="${pageContext.request.contextPath }/manage/teach_saveEdit.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>授课关系管理</strong>&nbsp;-&nbsp;编辑授课关系</div></div>
    <div class="tableH2">编辑授课关系</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">学期：</td>
            <td>
            	<s:select id="ter" name="teachRelation.term" headerKey="0" headerValue="---请选择学期---" list="termList"></s:select>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">教师工号：</td>
            <td>
            	<s:select id="tea" name="teacher.id" headerKey="0" headerValue="---请选择教师---" list="teacherList" listKey="id" listValue="account"></s:select>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">课程号：</td>
            <td>
            	<s:select id="cour" name="course.courseId" headerKey="0" headerValue="---请选择课程---" list="courseList" listKey="courseId" listValue="CNumber"></s:select>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">班级代号：</td>
            <td>
            	<s:select id="clz" name="clazz.clazzId" headerKey="0" headerValue="---请选择班级---" list="clazzList" listKey="clazzId" listValue="cnumber"></s:select>
            </td>
        </tr>
    </table>
    <s:hidden name="teachRelation.id" id="idHidden"></s:hidden>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>