<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
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
<title>录入成绩</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/myjs/js_addGradeUI.js"></script>
</head>
<body>
	<c:if test="${flag==true }">
		<div id="dialog"></div>
	</c:if>
	<c:if test="${oid!=null }">
		<div id="oid" hidden>${oid }</div>
	</c:if>
	<c:if test="${rid!=null }">
		<div id="rid" hidden>${rid }</div>
	</c:if>
	<div id="mode" hidden>${mode1 }</div>
	<div class="bz-panel bz-panel-default">
		<div class="bz-panel-hd">
			<h3 class="bz-panel-title">
				成绩录入
				<c:if test="${o!=null }"> -- ${o.r_tea.course.CName }</c:if>
			</h3>
		</div>
		<div class="bz-form bz-form-aligned navigator">
			<button class="bz-button bz-button-primary" onclick="resetGradeMode()" <c:if test="${gradeState=='submit' }">disabled</c:if>>重设计分方式</button>
			<button class="bz-button bz-button-primary" onclick="resetGrade()" <c:if test="${gradeState=='submit' }">disabled</c:if>>重置成绩</button>
			<button class="bz-button bz-button-primary" onclick="saveGrade()" <c:if test="${gradeState=='submit' }">disabled</c:if>>保存</button>
			<button class="bz-button bz-button-primary" onclick="submitGrade()" <c:if test="${gradeState=='submit' }">disabled</c:if>>提交</button>
			<button class="bz-button bz-button-primary" onclick="exportStudentToExcel()">导出学生到Excel</button>
			<button class="bz-button bz-button-primary" onclick="exportGradeToExcel()">导出成绩到Excel</button>
		</div>
		<div class="bz-panel-bd">
			<form id="fm1" action="" method="post">
				<input type="hidden" name="order.orderId" value="${o.orderId }"/>
				<c:choose>
					<c:when test="${gradeList!=null }">
						<table id="stu-grade">
							<thead>
								<tr>
									<th width="40px">序号</th>
									<th width="120px">学号</th>
									<th width="80px">姓名</th>
									<c:if test="${cqrate!=null&&cqrate!=0 }">
										<div id="div_cqrate" hidden>${cqrate }</div>
										<th width="100px">出勤</th>
									</c:if>
									<c:if test="${ktrate!=null&&ktrate!=0 }">
										<div id="div_ktrate" hidden>${ktrate }</div>
										<th width="100px">课堂表现</th>
									</c:if>
									<c:if test="${syrate!=null&&syrate!=0 }">
										<div id="div_syrate" hidden>${syrate }</div>
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
										<c:if test="${cqrate!=null&&cqrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="cq_${grade.student.account }" name="cqGrade_${vs.count }" onchange="gChange(this)" <c:if test="${grade.state=='submit' }">class="dis" disabled="disabled"</c:if>>
															<c:forEach var="i" begin="0" end="50" step="5">
																<option value="${100-i }" <c:if test="${grade.cq==100-i }">selected</c:if>>--${100-i }--</option>
															</c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														<select id="cq_${grade.student.account }"
															name="cqGrade_${vs.count }" onchange="gChange(this)" class="se_wjfz<c:if test="${grade.state=='submit' }"> dis" disabled="disabled</c:if>">
															<option value="95" <c:if test="${grade.cq==95 }">selected</c:if>>-- 优 --</option>
															<option value="85" <c:if test="${grade.cq==85 }">selected</c:if>>-- 良 --</option>
															<option value="75" <c:if test="${grade.cq==75 }">selected</c:if>>-- 中 --</option>
															<option value="60" <c:if test="${grade.cq==60 }">selected</c:if>>-- 及格 --</option>
															<option value="50" <c:if test="${grade.cq==50 }">selected</c:if>>--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${ktrate!=null&&ktrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="kt_${grade.student.account }" name="ktGrade_${vs.count }" onchange="gChange(this)" <c:if test="${grade.state=='submit' }">class="dis" disabled="disabled"</c:if>>
														<c:forEach var="i" begin="0" end="50" step="5">
															<option value="${100-i }" <c:if test="${grade.kt==100-i }">selected</c:if>>--${100-i }--</option>
														</c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														<select id="kt_${grade.student.account }" name="ktGrade_${vs.count }" onchange="gChange(this)" class="se_wjfz<c:if test="${grade.state=='submit' }"> dis" disabled="disabled</c:if>">
															<option value="95" <c:if test="${grade.kt==95 }">selected</c:if>>-- 优 --</option>
															<option value="85" <c:if test="${grade.kt==85 }">selected</c:if>>-- 良 --</option>
															<option value="75" <c:if test="${grade.kt==75 }">selected</c:if>>-- 中 --</option>
															<option value="60" <c:if test="${grade.kt==60 }">selected</c:if>>-- 及格 --</option>
															<option value="50" <c:if test="${grade.kt==50 }">selected</c:if>>--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${syrate!=null&&syrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="sy_${grade.student.account }" name="syGrade_${vs.count }" onchange="gChange(this)" <c:if test="${grade.state=='submit' }">class="dis" disabled="disabled"</c:if>>
															<c:forEach var="i" begin="0" end="50" step="5">
																<option value="${100-i }" <c:if test="${grade.sy==100-i }">selected</c:if>>--${100-i }--</option>
															</c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														<select id="sy_${grade.student.account }"
															name="syGrade_${vs.count }" onchange="gChange(this)" class="se_wjfz<c:if test="${grade.state=='submit' }"> dis" disabled="disabled</c:if>">
															<option value="95" <c:if test="${grade.sy==95 }">selected</c:if>>-- 优 --</option>
															<option value="85" <c:if test="${grade.sy==85 }">selected</c:if>>-- 良 --</option>
															<option value="75" <c:if test="${grade.sy==75 }">selected</c:if>>-- 中 --</option>
															<option value="60" <c:if test="${grade.sy==60 }">selected</c:if>>-- 及格 --</option>
															<option value="50" <c:if test="${grade.sy==50 }">selected</c:if>>--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<td><c:choose>
												<c:when test='${mode1 == "baifenzhi" }'>
													<select name="grade_${vs.count }" id="grade_${grade.student.account }" <c:if test="${grade.state=='submit' }">class="dis" disabled="disabled"</c:if>>
														<c:forEach var="i" begin="0" end="100" step="1">
															<option value="${100-i }" <c:if test="${grade.grade==100-i }">selected</c:if>>--${100-i }--</option>
														</c:forEach>
													</select>
												</c:when>
												<c:otherwise>
													<select name="grade_${vs.count }" class="se_wjfz<c:if test="${grade.state=='submit' }"> dis" disabled="disabled</c:if>"
														id="grade_${grade.student.account }">
														<option value="95" <c:if test="${grade.grade==95 }">selected</c:if>>-- 优 --</option>
														<option value="85" <c:if test="${grade.grade==85 }">selected</c:if>>-- 良 --</option>
														<option value="75" <c:if test="${grade.grade==75 }">selected</c:if>>-- 中 --</option>
														<option value="60" <c:if test="${grade.grade==60 }">selected</c:if>>-- 及格 --</option>
														<option value="50" <c:if test="${grade.grade==50 }">selected</c:if>>--不及格--</option>
													</select>
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
						<table id="stu-grade">
							<thead>
								<tr>
									<th width="40px">序号</th>
									<th width="120px">学号</th>
									<th width="80px">姓名</th>
									<c:if test="${cqrate!=null&&cqrate!=0 }">
										<div id="div_cqrate" hidden>${cqrate }</div>
										<th width="100px">出勤</th>
									</c:if>
									<c:if test="${ktrate!=null&&ktrate!=0 }">
										<div id="div_ktrate" hidden>${ktrate }</div>
										<th width="100px">课堂表现</th>
									</c:if>
									<c:if test="${syrate!=null&&syrate!=0 }">
										<div id="div_syrate" hidden>${syrate }</div>
										<th width="100px">实验情况</th>
									</c:if>
									<th width="100px">得分</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="stu" items="${studentList }" varStatus="vs">
									<input type="hidden" name="stu_${vs.count }" value="${stu.id }"/>
									<tr>
										<td>${vs.count }</td>
										<td>${stu.account }</td>
										<td>${stu.name }</td>
										<c:if test="${cqrate!=null&&cqrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="cq_${stu.account }" class="se_bfz"
															name="cqGrade_${vs.count }" onchange="gChange(this)">
														</select>
													</c:when>
													<c:otherwise>
														<select id="cq_${stu.account }" class="se_wjfz"
															name="cqGrade_${vs.count }" onchange="gChange(this)">
															<option value="95">-- 优 --</option>
															<option value="85">-- 良 --</option>
															<option value="75">-- 中 --</option>
															<option value="60">-- 及格 --</option>
															<option value="50">--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${ktrate!=null&&ktrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="kt_${stu.account }" class="se_bfz"
															name="ktGrade_${vs.count }" onchange="gChange(this)">
														</select>
													</c:when>
													<c:otherwise>
														<select id="kt_${stu.account }" class="se_wjfz"
															name="ktGrade_${vs.count }" onchange="gChange(this)">
															<option value="95">-- 优 --</option>
															<option value="85">-- 良 --</option>
															<option value="75">-- 中 --</option>
															<option value="60">-- 及格 --</option>
															<option value="50">--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<c:if test="${syrate!=null&&syrate!=0 }">
											<td><c:choose>
													<c:when test='${mode1 == "baifenzhi" }'>
														<select id="sy_${stu.account }" class="se_bfz"
															name="syGrade_${vs.count }" onchange="gChange(this)">
														</select>
													</c:when>
													<c:otherwise>
														<select id="sy_${stu.account }" class="se_wjfz"
															name="syGrade_${vs.count }" onchange="gChange(this)">
															<option value="95">-- 优 --</option>
															<option value="85">-- 良 --</option>
															<option value="75">-- 中 --</option>
															<option value="60">-- 及格 --</option>
															<option value="50">--不及格--</option>
														</select>
													</c:otherwise>
												</c:choose></td>
										</c:if>
										<td><c:choose>
												<c:when test='${mode1 == "baifenzhi" }'>
													<select class="se_bfz_grade" name="grade_${vs.count }"
														id="grade_${stu.account }">
													</select>
												</c:when>
												<c:otherwise>
													<select class="se_wjfz" name="grade_${vs.count }"
														id="grade_${stu.account }">
														<option value="95">-- 优 --</option>
														<option value="85">-- 良 --</option>
														<option value="75">-- 中 --</option>
														<option value="60">-- 及格 --</option>
														<option value="50">--不及格--</option>
													</select>
												</c:otherwise>
											</c:choose></td>
										<td><input class="ip-grade-memo" type="text" name="memo_${vs.count }"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				
			</form>
		</div>
	</div>
</body>
</html>
