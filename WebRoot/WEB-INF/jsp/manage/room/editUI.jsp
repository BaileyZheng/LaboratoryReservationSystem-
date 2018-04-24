<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>实验室管理</title>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script>
   		window.UEDITOR_HOME_URL = "${basePath }js/ueditor/";
    	var ue = UE.getEditor('editor');
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }manage/room_saveEdit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>实验室管理</strong>&nbsp;-&nbsp;编辑实验室</div></div>
    <div class="tableH2">编辑实验室</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
       <tr>
            <td class="tdBg" width="200px">实验室号：</td>
            <td><s:textfield name="room.RNumber" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">实验室名称：</td>
            <td><s:textfield name="room.RName" /></td>
        </tr>
        <tr>
        	<td class="tdBg" width="200pd">实验室类别：</td>
        	<td>
        		<s:select list="#roomTypeMap" name="room.RType"></s:select>
        	</td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">实验室容量：</td>
            <td><s:textfield name="room.RCapacity" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">实验室图片：</td>
            <td>
                <s:if test="%{room.RImgPath!=null && room.RImgPath!=''}">
                    <img src="${pageContext.request.contextPath }/upload/<s:property value='room.RImgPath'/> " width="100" height="100"/>
                    <s:hidden name="room.RImgPath"/>
                </s:if>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">实验室视频：</td>
            <td>
           	 	<s:if test="%{room.RFilePath!=null && room.RFilePath!=''}">
                    <embed src="${pageContext.request.contextPath }/upload/<s:property value='room.RFilePath'/> " width="100" height="100"/>
                    <s:hidden name="room.RFilePath"/>
                </s:if>
                <input type="file" name="roomFile"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="room.RState"/></td>
        </tr>
         <tr>
            <td class="tdBg" width="200px">实验室介绍：</td>
            <td><s:textarea id="editor" name="room.RMemo" cssStyle="width:90%;height:160px;" /></td>
        </tr>
    </table>
    <s:hidden name="room.roomId"/>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
   
</form>
</body>
</html>