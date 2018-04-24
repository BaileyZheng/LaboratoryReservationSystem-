﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("ctx", request.getContextPath()) ;
%>

<!DOCTYPE>
<html>
<head>
    <title>江苏科技大学实验室预约系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${ctx}/css/home.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/slide.js"></script>
</head>

<body>
<!-- 头部{ -->
<table width="1222" border="0" align="center" cellpadding="0"
       cellspacing="0" background="${ctx}/images/home/top_bg.png" class="top">
    <tr>
        <td width="32" height="106">&nbsp;</td>
        <td width="418" height="106" align="left" valign="middle">
        <a href="http://zjg.just.edu.cn/" target="blank"><img class="zxx_test_png" src="${ctx}/images/home/logo.gif" width="470" height="90" alt="" /></a>
        </td>
        <td width="211">&nbsp;</td>
        <td width="328">
        </td>
        <td width="331" align="right" valign="top">
            <table width="350" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="64" height="4"></td>
                    <td width="25" height="5"></td>
                    <td width="120" height="4"></td>
                    <td width="25" height="4"></td>
                    <td width="40" height="4"></td>
                    <td width="25" height="4"></td>
                    <td width="50" height="4"></td>
                </tr>
                <tr>
                    <td align="center"></td>
                    <td align="left"></td>
                    <td align="right">
                        <a><b></b><font color="red">欢迎您，</font>
                        <c:choose>
	                        <c:when test="${SYS_USER.name!=null&&SYS_USER.name!=''}">
	                        	<a href="${ctx}/grxx/home_frame.action" target="_top"><font color="red">${SYS_USER.name }</font></a>
	                        </c:when>
	                        <c:otherwise>
	                        	<font color="red">游客</font>
	                        </c:otherwise>
                        </c:choose> &nbsp; </a>
                    </td>
                    <td align="center"><img src="${ctx}/images/home/help.png" width="12" height="17"  /></td>
                    <td align="left"><a href="javascript:void(0);">帮助</a></td>
                    <td align="center"><img src="${ctx}/images/home/exit.png" width="14" height="14"   /></td>
                    <td align="left" valign="middle" >
                    	<c:choose>
                    		<c:when test="${SYS_USER!=null }">
                    			<a href="${ctx }/sys/login_logout.action">退出</a>
                    		</c:when>
                    		<c:otherwise>
								<a href="${ctx }/sys/login_toLoginUI.action">登录</a>                    		
                    		</c:otherwise>
                    	</c:choose>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!-- }头部 -->
<!-- 导航{ -->
<div class="menu">
    <ul class="clearfix">
        <li class="hover"><a href="${ctx }/sys/home.action">系统首页</a></li>
        <li><a href="${ctx }/cgzs/home_frame.action">成果展示</a></li>
        <li><a href="${ctx }/xwgg/home_frame.action">新闻公告</a></li>
        <c:if test="${SYS_TEACHER!=null}"><li><a href="${ctx }/yyxx/home_frame.action">预约信息</a></li></c:if>
        <c:if test="${SYS_ADMIN!=null }"><li><a href="${ctx }/manage/home_frame.action">数据管理</a> </li></c:if>
        <c:if test="${SYS_TEACHER!=null}"><li><a href="${ctx }/skgl/home_frame.action">授课管理</a></li></c:if>
        <li><a href="${ctx }/tjbb/home_frame.action">统计报表</a></li>
        <c:if test="${SYS_USER!=null }"><li><a href="${ctx }/grxx/home_frame.action">个人信息</a></li></c:if>
    </ul>
</div>
<!-- }导航 -->
<!-- 左{ -->
<div class="content">
    <div class="left">
        <!-- 个人资料{ -->
        <div class="left_grzx1">
            <div class="left_grzxbt">
                <h1>个人资料</h1>
            </div>
            <table width="98%" border="0" align="center">
                <tr>
                    <td width="76" height="100" align="center" valign="middle">
                        <div class="left-tx">
                            <s:if test="%{#session.SYS_USER.headImg != null && #session.SYS_USER.headImg !=''}">
                            	<a href="${pageContext.request.contextPath }/grxx/home_frame.action"><img src="${ctx}/upload/<s:property value='#session.SYS_USER.headImg'/>" width="70" height="70" /></a>
                            </s:if><s:else>
								<img src="${ctx}/images/home/gs09.png" width="70" height="70" />
							</s:else>
                        </div>
                    </td>
                    <td width="5%"><img src="${ctx}/images/home/gs10.png" width="4" height="59" alt="" /></td>
                    <td width="60%"><table width="95%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2" style=" font-weight:bold; color:#3a7daa;"><s:property value="#session.SYS_USER.account"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">用户名：
                            <s:if test="#session.SYS_USER.name!=null&&#session.SYS_USER.name!=''">
                        		<s:property value="#session.SYS_USER.name"/>
	                        </s:if>
	                        <s:else>
	                        	游客
	                        </s:else>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
        </div>
        <!-- }个人资料 -->
    </div>
<!-- }左 -->

    <!-- 右{ -->
    <div class="right">
        <div class="left_grzx1">
            <div class="left_grzxbt">
                <h1>信息列表</h1>
               	<div style="float:right;padding-top:3px;">
                	<a href="${pageContext.request.contextPath }/xwgg/home_frame.action">more&nbsp;>></a>&nbsp;&nbsp;
                </div>
            </div>
            <table width="98%" border="0" align="center">
            	<tr>
                        <td width="150px" align="center" height="25"><b>信息标题</b></td>
                        <td width="150px" align="center" height="25"><b>信息分类</b></td>
                        <td width="150px" align="center" height="25"><b>创建人</b></td>
                        <td width="150px" align="center" height="25"><b>创建时间</b></td>
                    </tr>
                    <s:iterator value="pageResult.items" status="st">
                        <tr height="23">
                            <td align="center">
                            	<a href="${pageContext.request.contextPath }/xwgg/home_detailFrame.action?id=<s:property value='infoId'/>"><s:property value="title"/></a>
                            </td>
                            <td align="center">
                            	<s:property value="#infoTypeMap[type]"/>	
                            </td>
                            <td align="center"><s:property value="creator"/></td>
                            <td align="center"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                    </s:iterator>
            </table>
        </div>
    </div>
    <!-- }右-->
    <div class="clear"></div>

    <div class="layout_center">
        <div class="lc_grzx1">
            <div class="lc_grzxbt">
                <h1>实验室展示</h1>
                <div style="float:right;padding-top:3px;">
                	<a href="${pageContext.request.contextPath }/cgzs/home_frame.action">more&nbsp;>></a>&nbsp;&nbsp;
                </div>
            </div>
            <div class="bottom">
               	<div class="video">
         			<ul>
	                <s:iterator value="roomList">
	              		<li><a href="${pageContext.request.contextPath }/cgzs/home_imageFrame.action?id=<s:property value='roomId'/>"><img width="160px" height="160px" alt="" src="${pageContext.request.contextPath }/upload/<s:property value='RImgPath'/>"/></a></li>
	                </s:iterator>
	                </ul>
               	</div>
            </div>
        </div>

        <div class="lc_grzx1">
            <div class="lc_grzxbt">
                <h1>最新预定</h1>
                <div style="float:right;padding-top:3px;">
                	<a href="${pageContext.request.contextPath }/yyxx/home_frame.action">more&nbsp;>></a>&nbsp;&nbsp;
                </div>
            </div>
            <table width="98%" border="0" align="center">
            	<tr>
                        <td width="100px" align="center" height="25"><b>预约教师</b></td>
                        <td width="150px" align="center" height="25"><b>预约实验室</b></td>
                        <td width="120px" align="center" height="25"><b>预约专业</b></td>
                        <td width="120px" align="center" height="25"><b>预约班级</b></td>
                        <td width="150px" align="center" height="25"><b>实验课程</b></td>
                        <td width="200px" align="center" height="25"><b>使用时间</b></td>
                        <td width="200px" align="center" height="25"><b>预约时间</b></td>
                        <td width="150px" align="center" height="25"><b>预约状态</b></td>
                    </tr>
                    <c:forEach var="item" items="${orderList }" begin="0" end="6">
                    	<tr>
                            <td width="100px" align="center" height="25">${item.r_tea.teacher.name }</td>
                            <td width="150px" align="center" height="25">${item.room.RName}-${item.room.RNumber }</td>
                            <td width="120px" align="center" height="25">${item.r_tea.clazz.cname }</td>
                            <td width="120px" align="center" height="25">${item.r_tea.clazz.cnumber }</td>
                            <td width="150px" align="center" height="25">${item.r_tea.course.CName }</td>
                            <td width="200px" align="center" height="25">${item.useTimeStr }</td>
                            <td width="200px" align="center" height="25">${item.orderTimeStr }</td>
                            <td width="150px" align="center" height="25">
                            	<c:choose>
                            		<c:when test="${item.OState=='1' }">
                            			审核中...
                            		</c:when>
                            		<c:otherwise>
                            			<c:choose>
		                            		<c:when test="${item.OState=='2' }">
		                            		审核通过&nbsp;
		                            		</c:when>
		                            		<c:otherwise>
		                            		</c:otherwise>
	                            		</c:choose>
                            		</c:otherwise>
                            	</c:choose>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </div>
</div>
<!-- 尾部{ -->
<div class="foot">版权所有©江苏科技大学 2017</div>
<!-- }尾部 -->
<script type='text/javascript'>
	 $('.video').slide({ mainCell:'ul',autoPlay:true,effect:'leftMarquee',interTime:50,vis:4.7 });
</script>	
</body>
</html>