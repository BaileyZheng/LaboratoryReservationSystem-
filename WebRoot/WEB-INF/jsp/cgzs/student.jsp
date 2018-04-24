<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>风采展示</title>
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">

</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">风采展示</h3>
		</div>
		<div class="bz-panel-bd">
			<ul>
				<s:iterator value="pageResult.items" status="st">
					<li><em><s:date name="createTime"
								format="yyyy-MM-dd HH:mm" /> </em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:property value="#infoTypeMap[type]" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="${pageContext.request.contextPath }/cgzs/home_stuDetailUI.action?id=<s:property value='infoId'/>"
						class="article" title="<s:property value="title"/>"><s:property
								value="title" /></a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</body>
</html>
