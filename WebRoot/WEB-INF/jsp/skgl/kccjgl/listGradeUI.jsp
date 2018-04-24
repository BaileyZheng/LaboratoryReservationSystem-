<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/yyxx/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/mycss/css_addGradeUI.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/myjs/js_addGradeUI.js"></script>
<title>成绩列表</title>
</head>
<body>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">
				成绩列表
				<c:if test="${o!=null }"> -- ${o.r_tea.course.CName }</c:if>
			</h3>
		</div>
		<div class="bz-form bz-form-aligned navigator">
			<button class="bz-button bz-button-primary" onclick="exportStudentToExcel()">导出学生到Excel</button>
			<c:if test="${gradeList!=null }"><button class="bz-button bz-button-primary" onclick="exportGradeToExcel()">导出成绩到Excel</button></c:if>
			<c:if test="${gradeMap!=null }"><button class="bz-button bz-button-primary" onclick="exportManyGradeToExcel()">导出成绩到Excel</button></c:if>
		</div>
		<div class="bz-panel-bd">
			<form id="fm1" action="" method="post">
				<input type="hidden" name="oid" value="${o.orderId }"/>
				<c:choose>
					<c:when test="${gradeList!=null }">
						<table id="stu-grade">
							<thead>
								<tr>
									<th width="40px">序号</th>
									<th width="120px">学号</th>
									<th width="80px">姓名</th>
									<c:if test="${cq!=null}">
										<th width="100px">出勤</th>
									</c:if>
									<c:if test="${kt!=null}">
										<th width="100px">课堂表现</th>
									</c:if>
									<c:if test="${sy!=null}">
										<th width="100px">实验情况</th>
									</c:if>
									<th width="100px">得分</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="grade" items="${gradeList }" varStatus="vs">
									<input type="hidden" name="stu_${vs.count }" value="${grade.student.id }"/>
									<tr>
										<td>${vs.count }</td>
										<td>${grade.student.account }</td>
										<td>${grade.student.name }</td>
										<c:if test="${cq!=null}">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<input class="grade" id="cq_${grade.student.account }" name="cqGrade_${vs.count }" type="text" readonly="readonly" value="${grade.cq}"/>
													</c:when>
													<c:otherwise>
														<input class="grade" id="cq_${grade.student.account }" name="cqGrade_${vs.count }" type="text" readonly="readonly" value=
															<c:if test="${grade.cq==95 }">"优"</c:if>
															<c:if test="${grade.cq==85 }">"良"</c:if>
															<c:if test="${grade.cq==75 }">"中"</c:if>
															<c:if test="${grade.cq==60 }">"及格"</c:if>
															<c:if test="${grade.cq==50 }">"不及格"</c:if>
														/>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${kt!=null}">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<input class="grade" id="kt_${grade.student.account }" name="ktGrade_${vs.count }" type="text" readonly="readonly" value="${grade.kt}"/>
													</c:when>
													<c:otherwise>
														<input class="grade" id="kt_${grade.student.account }" name="ktGrade_${vs.count }" type="text" readonly="readonly" value=
															<c:if test="${grade.kt==95 }">"优"</c:if>
															<c:if test="${grade.kt==85 }">"良"</c:if>
															<c:if test="${grade.kt==75 }">"中"</c:if>
															<c:if test="${grade.kt==60 }">"及格"</c:if>
															<c:if test="${grade.kt==50 }">"不及格"</c:if>
														/>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${sy!=null}">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<input class="grade" id="sy_${grade.student.account }" name="syGrade_${vs.count }" type="text" readonly="readonly" value="${grade.sy}"/>
													</c:when>
													<c:otherwise>
														<input class="grade" id="sy_${grade.student.account }" name="syGrade_${vs.count }" type="text" readonly="readonly" value=
															<c:if test="${grade.sy==95 }">"优"</c:if>
															<c:if test="${grade.sy==85 }">"良"</c:if>
															<c:if test="${grade.sy==75 }">"中"</c:if>
															<c:if test="${grade.sy==60 }">"及格"</c:if>
															<c:if test="${grade.sy==50 }">"不及格"</c:if>
														/>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<td><c:choose>
												<c:when test='${mode1 == "baifenzhi" }'>
														<input class="grade" id="grade_${grade.student.account }" name="grade_${vs.count }" type="text" readonly="readonly" value="${grade.grade}"/>
													</c:when>
													<c:otherwise>
														<input class="grade" id="grade_${grade.student.account }" name="grade_${vs.count }" type="text" readonly="readonly" value=
															<c:if test="${grade.grade==95 }">"优"</c:if>
															<c:if test="${grade.grade==85 }">"良"</c:if>
															<c:if test="${grade.grade==75 }">"中"</c:if>
															<c:if test="${grade.grade==60 }">"及格"</c:if>
															<c:if test="${grade.grade==50 }">"不及格"</c:if>
														/>
													</c:otherwise>
												</c:choose></td>
										<td><input class="ip-grade-memo" type="text" name="memo_${vs.count }" value="${grade.memo }" <c:if test="${grade.state=='submit' }">readonly="readonly"</c:if>/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${gradeMap!=null }">
								<input type="hidden" name="relation.id" value="${rid }"/>
								<table id="stu-grade">
									<thead>
										<tr>
											<th width="40px">序号</th>
											<th width="120px">学号</th>
											<th width="80px">姓名</th>
											<c:forEach var="i" begin="1" end="${count }">
												<th width="80px">成绩</th>
												<th width="120px">备注</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="map" items="${gradeMap }" varStatus="vs">
											<tr>
												<td>${vs.count }</td>
												<td>${map.key.account }</td>
												<td>${map.key.name }</td>
												<c:forEach var="g" items="${map.value }">
													<td>${g.grade }</td>
													<td>${g.memo }</td>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<table id="stu-grade">
									<tr>
										<td>没有成绩，请先录入-->
											<a href="${pageContext.request.contextPath }/skgl/home_addGradeUI.action?order.orderId=${o.orderId }">点击录入</a>
										</td>
									</tr>
								</table>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				
			</form>
		</div>
	</div>
</body>
</html>