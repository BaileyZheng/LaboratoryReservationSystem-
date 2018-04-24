<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>个人资料</title>
<body class="rightBody">
	<form id="form" name="form" action="#">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>个人资料</strong>
						</div>
					</div>
					<div class="tableH2">个人资料</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tdBg" width="200px">用户名：</td>
							<td>${SYS_USER.name }</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">帐号：</td>
							<td>${SYS_USER.account }</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">密码：</td>
							<td>${SYS_USER.password}</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">头像：</td>
							<td><c:if
									test="${SYS_USER.headImg!=null && SYS_USER.headImg!=''}">
									<img
										src="${pageContext.request.contextPath }/upload/<s:property value='#session.SYS_USER.headImg'/>"
										width="100" height="100" />
								</c:if></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">性别：</td>
							<td><c:choose>
									<c:when test="${SYS_USER.gender =='true'}">男</c:when>
									<c:otherwise>女</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">角色：</td>
							<td>
								<c:choose>
									<c:when test="${roles!=null }">
										<c:forEach var="role" items="${roles }">
											${role}&nbsp;
										</c:forEach>
									</c:when>
									<c:otherwise>无</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">电子邮箱：</td>
							<td>${SYS_USER.email}</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">手机号：</td>
							<td>${SYS_USER.mobile}</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">生日：</td>
							<td><fmt:formatDate value="${SYS_USER.birthday }"
									pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">状态：</td>
							<td><c:choose>
									<c:when test="${SYS_USER.state =='1'}">有效</c:when>
									<c:otherwise>无效</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">备注：</td>
							<td>${SYS_USER.memo }</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>