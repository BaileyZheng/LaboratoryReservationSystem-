<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("ctx", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>
    <link href="${pageContext.request.contextPath}/css/manage/css.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/manage/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        //隐藏菜单
        $(document).ready(function () {
            $("dt a").click(function () {
                var cur = $(this);
                cur.parent().next().toggle(700);
                var cur_dl = cur.parent().parent();
                doRemoveCurClass();
                $(cur_dl).addClass("curr");
            });

            $("dd a").each(function () {
                $(this).bind("click", function () {
                    doRemoveCurClass();
                    $(this).addClass("cur");
                });
            });
           
        });

        function doRemoveCurClass() {
            $("dl").each(function () {
                $(this).removeClass("curr");
                $("dd a").each(function () {
                    $(this).removeClass("cur");
                });
            });
        }

        function closeOtherDD(id) {
            $("dd").each(function () {
                if ($(this).attr("id") != id + "dd") {
                    $(this).hide(700);
                }
            });
        }
    </script>
    <style>
        * {
            scrollbar-face-color: #dee3e7; /*立体滚动条的颜色（包括箭头部分的背景色）*/
            scrollbar-highlight-color: #ffffff; /*滚动条的高亮颜色（左阴影？）*/
            scrollbar-shadow-color: #dee3e7; /*立体滚动条阴影的颜色*/
            scrollbar-3dlight-color: #eceaea; /*立体滚动条亮边的颜色*/
            scrollbar-arrow-color: #006699; /*三角箭头的颜色*/
            scrollbar-track-color: #efefef; /*立体滚动条背景颜色*/
            scrollbar-darkshadow-color: #eceaea; /*滚动条的基色*/
        }
    </style>
</head>

<body>
<div class="xzfw" style="width: 210px;">
    <div class="xzfw_nav" style="width:214px;min-height:500px;">
        <div class="nBox" style="width:214px;">
            <div class="x_top" style="width:214px;"></div>
            <div class="sm">
				<dl class="">
                    <dt><a class="yh" href="${pageContext.request.contextPath}/manage/role_listUI.action" target="mainFrame"><b></b>角色管理<s class="down"></s>
                    </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="yh" href="${pageContext.request.contextPath}/manage/user_listUI.action" target="mainFrame"><b></b>用户管理<s class="down"></s>
                    </a></dt>
                </dl>
                 <dl class="">
                    <dt><a class="yh" href="${pageContext.request.contextPath}/manage/teacher_listUI.action" target="mainFrame"><b></b>教师管理<s class="down"></s>
                    </a></dt>
                </dl>
				 <dl class="">
                    <dt><a class="yh" href="${pageContext.request.contextPath}/manage/student_listUI.action" target="mainFrame"><b></b>学生管理<s class="down"></s>
                    </a></dt>
                </dl>
                 <dl class="">
                    <dt><a class="yh" href="${pageContext.request.contextPath}/manage/teach_listUI.action" target="mainFrame"><b></b>授课关系<s class="down"></s>
                    </a></dt>
                </dl>
                 <dl class="">
                    <dt><a class="bjgl" href="${pageContext.request.contextPath}/manage/clazz_listUI.action" target="mainFrame"><b></b>班级管理<s class="down"></s> </a></dt>
                </dl>

				<dl class="">
                    <dt><a class="kc" href="${pageContext.request.contextPath}/manage/course_listUI.action" target="mainFrame"><b></b>课程管理<s class="down"></s> </a></dt>
                </dl>

                <dl class="">
                    <dt><a class="sys" href="${pageContext.request.contextPath}/manage/room_listUI.action" target="mainFrame"><b></b>实验室管理<s
                            class="down"></s> </a></dt>
                </dl>
                <dl>
                    <dt><a class="xxfb" href="${pageContext.request.contextPath}/manage/info_listUI.action" target="mainFrame"><b></b>信息发布管理<s
                            class="down"></s> </a></dt>
                </dl>
                
                <dl class="">
                    <dt><a class="skgx" href="${pageContext.request.contextPath }/manage/time_timeUI.action" target="mainFrame"><b></b>学期时间管理<s class="down"></s> </a></dt>
                </dl>
                
                <dl class="">
                    <dt><a class="yyxx" style="cursor: pointer;"><b></b>预约信息管理<s class="down"></s> </a></dt>
                    <dd id="fwyygl" style="display:none;">
                        <a class="" href="${pageContext.request.contextPath }/manage/order_listUI.action?type=jf" target="mainFrame"><b></b>预约机房</a>
                        <a class="" href="${pageContext.request.contextPath }/manage/order_listUI.action?type=dqsys" target="mainFrame"><b></b>预约电气实验室</a>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</div>
</body>
</html>
