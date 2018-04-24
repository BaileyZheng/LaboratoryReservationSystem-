<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>班级信息管理</title>
    <script type="text/javascript">
  	//全选、全反选
	function doSelectAll(){
		$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	}
  	//新增
  	function doAdd(){
  		document.forms[0].action = "${basePath}manage/clazz_addUI.action";
  		document.forms[0].submit();
  	}
  	//编辑
  	function doEdit(id){
  		document.forms[0].action = "${basePath}manage/clazz_editUI.action?clazz.clazzId=" + id;
  		document.forms[0].submit();
  	}
  	//删除
  	function doDelete(id){
  		document.forms[0].action = "${basePath}manage/clazz_delete.action?clazz.clazzId=" + id;
  		document.forms[0].submit();
  	}
  	//批量删除
  	function doDeleteAll(){
  		document.forms[0].action = "${basePath}manage/clazz_deleteSelected.action";
  		document.forms[0].submit();
  	}
  	var list_url="${basePath}manage/clazz_listUI.action";
  	function doSearch(){
  		$("#pageNo").val(1);
	  	document.forms[0].action = list_url;
	  	document.forms[0].submit();
  	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>班级信息管理</strong></div> </div>
                <div class="search_art">
                    <li>
                        专业：<s:textfield name="clazz.cname" cssClass="s_text" id="clazzName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td align="center">班级号</td>
                            <td width="150" align="center">专业</td>
                            <td width="150" align="center">班级人数</td>
                            <td width="150" align="center">状态</td>
                            <td width="260" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='clazzId'/>"/></td>
                                <td align="center"><s:property value="cnumber"/></td>
                                <td align="center"><s:property value="cname"/></td>
                                <td align="center"><s:property value="snumber"/></td>
                                <td id="show_<s:property value='clazzId'/>" align="center"><s:property value="clazzState==1?'有效':'无效'"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value='clazzId'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='clazzId'/>')">删除</a>
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