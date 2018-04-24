<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>修改个人资料</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/WdatePicker.js"></script></head>
	<script type="text/javascript">
		var vResult = false;
		function verifyAccount(){
			var account = $("#account").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/manage/user_verifyAccount.action",
				data:{"user.account":account,"user.id":"${user.id}"},
				type:"post",
				async:false,
				success:function(msg){
					if("true"==msg){
						alert("账号已经存在，请使用其他账号！");
						$("#account").focus();
					}else{
						vResult = true;
					}
				}
			});
		}
		function doSubmit(){
			var name = $("#name");
			var password = $("#password");
			if(name.val()==""){
				alert("用户名不能为空！");
				name.focus();
				return false;
			}
			if(password.val()==""){
				alert("密码不能为空!");
				password.focus();
				return false;
			}
			verifyAccount();
			if(vResult){
				document.forms[0].submit();
			}
		}
	</script>
<body class="rightBody">
<form id="form" name="form" action="${pageContext.request.contextPath }/grxx/home_saveEdit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>个人资料</strong>&nbsp;-&nbsp;修改</div></div>
    <div class="tableH2">修改个人资料</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name" id="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account" onchange="verifyAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password" id="password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <s:if test="%{user.headImg!=null && user.headImg!=''}">
                    <img src="${pageContext.request.contextPath }/upload/<s:property value='user.headImg'/> " width="100" height="100"/>
                    <s:hidden name="user.headImg"/>
                </s:if>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
			<td>
				<c:choose>
					<c:when test="${roles!=null }">
						<c:forEach var="role" items="${roles }">
							${role}&nbsp;
						</c:forEach>
					</c:when>
					<c:otherwise>无</c:otherwise>
				</c:choose>
			</td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>        
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'})">
            	<s:param name="value">
            		<s:date name="user.birthday" format="yyyy-MM-dd"/>
            	</s:param>
            </s:textfield></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td>
            	<s:if test='%{user.state=="1"}'>有效</s:if>
            	<s:else>无效</s:else>
            	<s:hidden name="user.state"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <s:hidden name="user.id" id="idHidden"></s:hidden>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>