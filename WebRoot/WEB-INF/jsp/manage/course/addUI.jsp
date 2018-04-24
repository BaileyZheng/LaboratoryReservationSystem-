<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>课程管理</title>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }manage/course_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>课程管理</strong>&nbsp;-&nbsp;新增课程</div></div>
    <div class="tableH2">新增课程</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">课程号：</td>
            <td><s:textfield name="course.cNumber" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">课程名：</td>
            <td>
            	<s:textfield name="course.cName" />
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="course.courseState"/></td>
        </tr>
    </table>
    
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
    
   
</form>
</body>
</html>