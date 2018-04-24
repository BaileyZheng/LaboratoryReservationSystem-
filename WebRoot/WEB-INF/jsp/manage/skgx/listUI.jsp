<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@include file="/common/header.jsp" %>
    <title>授课关系管理</title>
    <script type="text/javascript">
      	//全选、全反选
		function doSelectAll(){
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
		//添加
		function doAdd(){
			document.forms[0].action="${pageContext.request.contextPath}/manage/teach_addUI.action";
			document.forms[0].submit();
		}
		//编辑
		function doEdit(id){
			document.forms[0].action="${pageContext.request.contextPath}/manage/teach_editUI.action?teachRelation.id="+id;
			document.forms[0].submit();
		}
		//删除
		function doDelete(id){
			document.forms[0].action="${pageContext.request.contextPath}/manage/teach_delete.action?teachRelation.id="+id;
			document.forms[0].submit();
		}
		//批量删除
		function doDeleteAll(){
			document.forms[0].action="${pageContext.request.contextPath}/manage/teach_deleteSelected.action";
			document.forms[0].submit();
		}
		//导出到excel
		function doExportExcel(){
			window.open("${pageContext.request.contextPath}/manage/teach_exportExcel.action");
		}
		//导入excel
		function doImportExcel(){
			document.forms[0].action="${pageContext.request.contextPath}/manage/teach_importExcel.action";
			document.forms[0].submit();
		}
		var list_url="${basePath}manage/teach_listUI.action";
	  	function doSearch(){
	  		$("#pageNo").val(1);
		  	document.forms[0].action = list_url;
		  	document.forms[0].submit();
	  	}
    </script>
</head>
<body class="rightBody">
<form name="form1" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>授课关系管理</strong></div> </div>
                <div class="search_art">
                    <li>
                        教师工号：<s:select id="tea" name="teacher.id" cssClass="s_text" headerKey="0" headerValue="---请选择教师---" list="teacherList" listKey="id" listValue="account"></s:select>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                        <input type="button" value="导出" class="s_button" onclick="doExportExcel()"/>&nbsp;
                    	<input name="excel" type="file"/>
                        <input type="button" value="导入" class="s_button" onclick="doImportExcel()"/>&nbsp;

                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="80" align="center">教师工号</td>
                            <td width="100" align="center">教师姓名</td>
                            <td width="80" align="center">课程号</td>
                            <td width="100" align="center">课程名</td>
                            <td width="100" align="center">班级代号</td>
                            <td width="100" align="center">专业</td>
                            <td width="100" align="center">学期</td>
                            <td width="140" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if>>
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>" /></td>
                                <td align="center"><s:property value="teacher.account"/></td>
                                <td align="center"><s:property value="teacher.name"/></td>
                                <td align="center"><s:property value="course.CNumber"/></td>
                                <td align="center"><s:property value="course.CName"/></td>
                                <td align="center"><s:property value="clazz.cnumber"/></td>
                                <td align="center"><s:property value="clazz.cname"/></td>
                                <td align="center"><s:property value="term"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value='id'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='id'/>')">删除</a>
                                </td>
                            </tr>
                     	</s:iterator>  
                    </table>
                </div>
            </div>
        <jsp:include page="/common/pageNavigator.jsp"></jsp:include>
        </div>
    </div>
</form>

</body>
</html>