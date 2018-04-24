<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>学期时间管理</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/WdatePicker.js"></script></head>
<body class="rightBody">
<form id="form" name="form" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>学期时间管理</strong>&nbsp;-&nbsp;设置学期开始时间</div></div>
    <div class="tableH2">设置学期开始时间</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
        <c:choose>
        <c:when test="${TERM_START_TIME!=null }">
        	<td class="tdBg" style="text-align:center;">
        		本学期开始时间为：${TERM_START_TIME }
        	</td>
        	<td class="tdBg" style="text-align:center;">
        		<a href="javascript:doReset()">重置时间</a>
        	</td>
        </c:when>
        <c:otherwise>
            <td class="tdBg" style="text-align:center;" id="div1">
            </td>
        </c:otherwise>
        </c:choose>
        </tr>
    </table>
    <script type="text/javascript">
    	var timeStr;
		WdatePicker({eCont:'div1',onpicked:function(dp){
			timeStr = dp.cal.getDateStr();
			alert('你选择的日期是:'+timeStr)
		}});
		function  doSubmit(){
			document.forms[0].action="${pageContext.request.contextPath }/manage/time_saveTime.action?timeStr="+timeStr;
			document.forms[0].submit();
		}
		function doReset(){
			document.forms[0].action="${pageContext.request.contextPath }/manage/time_reset.action?timeStr="+timeStr;
			document.forms[0].submit();
		}
	</script>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>