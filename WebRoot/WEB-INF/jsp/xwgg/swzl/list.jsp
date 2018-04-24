<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE>
<html>
<head>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/base.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<title>失物招领</title>
</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">失物招领</h3>
		</div>
		<div class="bz-panel-bd">
			<ul>
				<s:iterator value="pageResult.items" status="st">
					<li><em><s:date name="createTime"
								format="yyyy-MM-dd HH:mm" /> </em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:property value="#infoTypeMap[type]" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="${pageContext.request.contextPath }/xwgg/home_detail.action?id=<s:property value='infoId'/>"
						class="article" title="<s:property value="title"/>"><s:property
								value="title" /></a>
					</li>
				</s:iterator>
			</ul>
		</div>
		</div>


</body>
</html>
