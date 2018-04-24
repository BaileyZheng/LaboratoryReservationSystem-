<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>预约信息管理</title>
    <script type="text/javascript">
    function doSelect(way){
    	document.forms[0].action = "${basePath}manage/order_listUI.action?way=" + way;
  		document.forms[0].submit();
    }
  	//全选、全反选
	function doSelectAll(){
		$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	}
  	//删除
  	function doDelete(id){
  		document.forms[0].action = "${basePath}manage/order_delete.action?order.orderId=" + id;
  		document.forms[0].submit();
  	}
  	//批量删除
  	function doDeleteAll(){
  		document.forms[0].action = "${basePath}manage/order_deleteSelected.action";
  		document.forms[0].submit();
  	}
  	//
  	function doSomething(orderId, state){
  		//1、更新信息状态
  		$.ajax({
  			url:"${basePath}manage/order_doSomething.action",
  			data:{"order.orderId":orderId, "order.OState":state},
  			type:"post",
  			success: function(msg){
  				//2、更新状态栏、操作栏的显示值
  				if("更新状态成功" == msg){
  					if(state == 0){
  						$("#show_"+orderId).html("未通过");
  						$("#oper_"+orderId).html('<a href="javascript:doSomething(\''+orderId+'\',3)">失效</a>&nbsp;&nbsp;'
  											+'<a href="javascript:doDelete(\''+orderId+'\')">删除</a>');
  					} else if(state==2){
  						$("#show_"+orderId).html("通过");
  						$("#oper_"+orderId).html('<a href="javascript:doSomething(\''+orderId+'\',3)">失效</a>&nbsp;&nbsp;'
  											+'<a href="javascript:doDelete(\''+orderId+'\')">删除</a>');
  					}else if(state==3){
  						$("#show_"+orderId).html("失效");
  						$("#oper_"+orderId).html('<a href="javascript:doDelete(\''+orderId+'\')">删除</a>');
  					}
  				} else if("同一教师不可在同一时间预约成功两个实验室！"==msg){
  					alert("同一教师不可在同一时间预约成功两个实验室！");
  				}else{alert("更新状态失败！");}
  			},
  			error: function(){
  				alert("更新状态失败！");
  			}
  		});
  	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
	<s:hidden name="type"></s:hidden>
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>预约信息管理 </strong></div> </div>
                <div class="search_art">
                    <li><input type="button" class="s_button" value="全部" onclick="doSelect(1)"/></li>
                    <li><input type="button" class="s_button" value="有效" class="s_button" onclick="doSelect(2)"/></li>
                    <li><input type="button" class="s_button" value="通过" class="s_button" onclick="doSelect(5)"/></li>
                    <li><input type="button" class="s_button" value="待审核" class="s_button" onclick="doSelect(3)"/></li>
                    <li style="float:right;">
                    	<input type="button" value="未通过" class="s_button" onclick="doSelect(4)"/>&nbsp;
                    	<input type="button" value="失效" class="s_button" onclick="doSelect(0)"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td align="center">教师</td>
                            <td align="center">班级</td>
                            <td align="center">课程</td>
                            <td align="center">实验室</td>
                            <td align="center">使用时间</td>
                            <td align="center">预约时间</td>
                            <td align="center">状态</td>
                            <td align="center">操作</td>
                        </tr>
                       		<s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='orderId'/>"/></td>
                                <td align="center"><s:property value="r_tea.teacher.name"/></td>
                                <td align="center"><s:property value="r_tea.clazz.cname"/></td>
                                <td align="center"><s:property value="r_tea.course.CName"/></td>
                                <td align="center"><s:property value="room.RNumber"/></td>
                                <td align="center"><s:property value="useTimeStr"/></td>
                                <td align="center"><s:property value="orderTimeStr"/></td>
                                <td align="center">
                                	<span id="show_<s:property value='orderId'/>">
	                                	<s:if test="OState==0">未通过</s:if>
	                                	<s:elseif test="OState==1">待审核</s:elseif>
	                                	<s:elseif test="OState==2">通过</s:elseif>
	                                	<s:elseif test="OState==3">失效</s:elseif>
                                	</span>
                                </td>
                                <td align="center">
                                	<span  id="oper_<s:property value='orderId'/>">
	                                	<s:if test="OState==1">
	                                		<a href="javascript:doSomething('<s:property value='orderId'/>',2)">通过</a>&nbsp;&nbsp;
	                                		<a href="javascript:doSomething('<s:property value='orderId'/>',0)">不通过</a>&nbsp;&nbsp;
	                                		<a href="javascript:doSomething('<s:property value='orderId'/>',3)">失效</a>&nbsp;&nbsp;
	                                		<a href="javascript:doDelete('<s:property value='orderId'/>')">删除</a>
	                                	</s:if><s:elseif test="OState==0">
	                                		<a href="javascript:doSomething('<s:property value='orderId'/>',3)">失效</a>&nbsp;&nbsp;
	                                		<a href="javascript:doDelete('<s:property value='orderId'/>')">删除</a>
	                                	</s:elseif><s:elseif test="OState==2">
	                                		<a href="javascript:doSomething('<s:property value='orderId'/>',3)">失效</a>&nbsp;&nbsp;
	                                		<a href="javascript:doDelete('<s:property value='orderId'/>')">删除</a>
	                                	</s:elseif><s:elseif test="OState==3">
	                                		<a href="javascript:doDelete('<s:property value='orderId'/>')">删除</a>
	                                	</s:elseif>
                                	</span>
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