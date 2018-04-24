<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>教师管理</title>
</head>
	<script type="text/javascript">
		var vResult = false;
		function verifyAccount(){
			var account = $("#account").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/manage/teacher_verifyAccount.action",
				type:"post",
				async:false,
				data:{"teacher.account":account},
				success:function(msg){
					if("true"==msg){
						alert("该教师已经存在，请不要重复添加！");
						$("#account").focus();
					}else{
						vResult = true
					}
				}
			});
		}
		function doSubmit(){
			var name = $("#name");
			var account = $("#account");
			if(name.val()==""){
				alert("教师姓名不能为空！");
				name.focus();
				return false;
			}
			if(account.val()==""){
				alert("教师工号不能为空!");
				account.focus();
				return false;
			}
			verifyAccount();
			if(vResult){
				document.forms[0].submit();
			}
		}
	</script>
<body class="rightBody">
<form id="form" name="form" action="${pageContext.request.contextPath }/manage/teacher_add.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>教师管理</strong>&nbsp;-&nbsp;新增教师</div></div>
    <div class="tableH2">新增教师</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">姓名：</td>
            <td><s:textfield name="teacher.name" id="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">工号：</td>
            <td><s:textfield id="account" name="teacher.account" onchange="verifyAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">职称：</td>
            <td><s:textfield name="teacher.proTitle"/></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="teacher.state" value="1"/></td>
        </tr>
        <s:hidden name="strName"></s:hidden>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>