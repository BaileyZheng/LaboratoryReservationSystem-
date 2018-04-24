$(document).ready(function(){
	$("#dialog").dialog({
		title:"请选择成绩录入方式",
		width:430,
		height:230,
		href:"${pageContext.request.contextPath}/skgl/home_selectGradeTypeUI.action?oid="+$("#oid").html()+"&rid="+$("#rid").html(),
		modal:true,
		closable:false
	});
	for(var i=100;i>=50;i-=5){
		$(".se_bfz").append("<option value='"+i+"'>--"+i+"--</option>");
	}
	for(var i=100;i>=0;i--){
		$(".se_bfz_grade").append("<option value='"+i+"'>--"+i+"--</option>");
	}
})
function rangeChange(obj){
	var oid = $(obj).attr("id");
	var cqrate = $("#se_cqrate").val();//出勤占成绩比率
	var ktrate = $("#se_ktrate").val();//课堂表现占成绩比率
	var syrate = $("#se_syrate").val();//实验情况占成绩比率
	if(oid=="se_cqrate"){//出勤比率发生变化
		cqrate = $(obj).val();
		$("#sp_cqrate").html(cqrate+"%");
		if($("#ck_shiyan").is(":checked")&&$("#ck_biaoxian").is(":checked")){
			ktrate = 100-cqrate-syrate;
			if(ktrate<=0){
				syrate = syrate-Math.abs(ktrate);
				$("#se_syrate").val(syrate);
				$("#sp_syrate").html(syrate+"%");
				$("#se_ktrate").val(0);
				$("#sp_ktrate").html("0%");
			}else{
				$("#se_ktrate").val(ktrate);
				$("#sp_ktrate").html(ktrate+"%");
			}
		}else if($("#ck_shiyan").is(":checked")){
			syrate = 100-cqrate;
			$("#se_syrate").val(syrate);
			$("#sp_syrate").html(syrate+"%");
			$("#se_ktrate").val(0);
			$("#sp_ktrate").html("0%");
		}else if($("#ck_biaoxian").is(":checked")){
			ktrate = 100-cqrate;
			$("#se_syrate").val(0);
			$("#sp_syrate").html("0%");
			$("#se_ktrate").val(ktrate);
			$("#sp_ktrate").html(ktrate+"%");
		}else{
			$("#se_cqrate").val(100);
			$("#sp_cqrate").html("100%");
		}
	}else if(oid=="se_ktrate"){
		ktrate = $(obj).val();
		$("#sp_ktrate").html(ktrate+"%");
		if($("#ck_chuqin").is(":checked")&&$("#ck_shiyan").is(":checked")){
			syrate = 100-ktrate-cqrate;
			if(syrate<=0){
				cqrate = cqrate-Math.abs(syrate);
				$("#se_cqrate").val(cqrate);
				$("#sp_cqrate").html(cqrate+"%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}else{
				$("#se_syrate").val(syrate);
				$("#sp_syrate").html(syrate+"%");
			}
		}else if($("#ck_chuqin").is(":checked")){
			cqrate=100-ktrate;
			$("#se_cqrate").val(cqrate);
			$("#sp_cqrate").html(cqrate+"%");
			$("#se_ktrate").val(ktrate);
			$("#sp_ktrate").html(ktrate+"%");
		}else if($("#ck_shiyan").is(":checked")){
			syrate = 100-ktrate;
			$("#se_syrate").val(syrate);
			$("#sp_syrate").html(syrate+"%");
			$("#se_ktrate").val(ktrate);
			$("#sp_ktrate").html(ktrate+"%");
		}else{
			$("#se_ktrate").val(100);
			$("#sp_ktrate").html("100%");
		}
	}else if(oid=="se_syrate"){
		syrate = $(obj).val();
		$("#sp_syrate").html(syrate+"%");
		if($("#ck_chuqin").is(":checked")&&$("#ck_biaoxian").is(":checked")){
			cqrate = 100-syrate-ktrate;
			if(cqrate<=0){
				ktrate = ktrate-Math.abs(cqrate);
				$("#se_ktrate").val(ktrate);
				$("#sp_ktrate").html(ktrate+"%");
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
			}else{
				$("#se_cqrate").val(cqrate);
				$("#sp_cqrate").html(cqrate+"%");
			}
		}else if($("#ck_chuqin").is(":checked")){
			cqrate = 100-syrate;
			$("#se_cqrate").val(cqrate);
			$("#sp_cqrate").html(cqrate+"%");
			$("#se_syrate").val(syrate);
			$("#sp_syrate").html(syrate+"%");
		}else if($("#ck_biaoxian").is(":checked")){
			ktrate = 100-syrate;
			$("#se_ktrate").val(ktrate);
			$("#sp_ktrate").html(ktrate+"%");
			$("#se_syrate").val(syrate);
			$("#sp_syrate").html(syrate+"%");
		}else{
			$("#se_syrate").val(100);
			$("#sp_syrate").html("100%");
		}
	}
	//checkbox选中变化
	if(cqrate<=0){
		$("#ck_chuqin").attr("checked",false);
	}else{
		document.getElementById("ck_chuqin").checked=true;
	}
	if(ktrate<=0){
		$("#ck_biaoxian").attr("checked",false);
	}else{
		document.getElementById("ck_biaoxian").checked=true;
	}
	if(syrate<=0){
		$("#ck_shiyan").attr("checked",false);
	}else{
		document.getElementById("ck_shiyan").checked=true;
	}
}
function ckChange(obj){
	var oid = $(obj).attr("id");
	if(oid=="ck_chuqin"){
		if(!$(obj).is(":checked")){
			$("#se_cqrate").val(0);
			$("#sp_cqrate").html("0%");
			if($("#ck_biaoxian").is(":checked")&&$("#ck_shiyan").is(":checked")){
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else if($("#ck_biaoxian").is(":checked")){
				$("#se_ktrate").val(100);
				$("#sp_ktrate").html("100%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}else if($("#ck_shiyan").is(":checked")){
				$("#se_ktrate").val(0);
				$("#sp_ktrate").html("0%");
				$("#se_syrate").val(100);
				$("#sp_syrate").html("100%");
			}
		}else{
			if($("#ck_biaoxian").is(":checked")&&$("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(30);
				$("#sp_cqrate").html("30%");
				$("#se_ktrate").val(30);
				$("#sp_ktrate").html("30%");
				$("#se_syrate").val(40);
				$("#sp_syrate").html("40%");
			}else if($("#ck_biaoxian").is(":checked")){
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}else if($("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
				$("#se_ktrate").val(0);
				$("#sp_ktrate").html("0%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else{
				$("#se_cqrate").val(100);
				$("#sp_cqrate").html("100%");
				$("#se_ktrate").val(0);
				$("#sp_ktrate").val("0%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}
		}
	}else if(oid=="ck_biaoxian"){
		if(!$(obj).is(":checked")){
			$("#se_ktrate").val(0);
			$("#sp_ktrate").html("0%");
			if($("#ck_chuqin").is(":checked")&&$("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else if($("#ck_chuqin").is(":checked")){
				$("#se_cqrate").val(100);
				$("#sp_cqrate").html("100%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}else if($("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
				$("#se_syrate").val(100);
				$("#sp_syrate").html("100%");
			}
		}else{
			if($("#ck_chuqin").is(":checked")&&$("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(30);
				$("#sp_cqrate").html("30%");
				$("#se_ktrate").val(30);
				$("#sp_ktrate").html("30%");
				$("#se_syrate").val(40);
				$("#sp_syrate").html("40%");
			}else if($("#ck_chuqin").is(":checked")){
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}else if($("#ck_shiyan").is(":checked")){
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else{
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
				$("#se_ktrate").val(100);
				$("#sp_ktrate").val("100%");
				$("#se_syrate").val(0);
				$("#sp_syrate").html("0%");
			}
		}
	}else if(oid=="ck_shiyan"){
		if(!$(obj).is(":checked")){
			$("#se_syrate").val(0);
			$("#sp_syrate").html("0%");
			if($("#ck_biaoxian").is(":checked")&&$("#ck_chuqin").is(":checked")){
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
			}else if($("#ck_biaoxian").is(":checked")){
				$("#se_ktrate").val(100);
				$("#sp_ktrate").html("100%");
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
			}else if($("#ck_chuqin").is(":checked")){
				$("#se_ktrate").val(0);
				$("#sp_ktrate").html("0%");
				$("#se_cqrate").val(100);
				$("#sp_cqrate").html("100%");
			}
		}else{
			if($("#ck_biaoxian").is(":checked")&&$("#ck_chuqin").is(":checked")){
				$("#se_cqrate").val(30);
				$("#sp_cqrate").html("30%");
				$("#se_ktrate").val(30);
				$("#sp_ktrate").html("30%");
				$("#se_syrate").val(40);
				$("#sp_syrate").html("40%");
			}else if($("#ck_biaoxian").is(":checked")){
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
				$("#se_ktrate").val(50);
				$("#sp_ktrate").html("50%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else if($("#ck_chuqin").is(":checked")){
				$("#se_cqrate").val(50);
				$("#sp_cqrate").html("50%");
				$("#se_ktrate").val(0);
				$("#sp_ktrate").html("0%");
				$("#se_syrate").val(50);
				$("#sp_syrate").html("50%");
			}else{
				$("#se_cqrate").val(0);
				$("#sp_cqrate").html("0%");
				$("#se_ktrate").val(0);
				$("#sp_ktrate").val("0%");
				$("#se_syrate").val(100);
				$("#sp_syrate").html("100%");
			}
		}
	}
}
function myReset(){
	$("#ck_chuqin").attr("checked",true);
	$("#ck_biaoxian").attr("checked",true);
	$("#ck_shiyan").attr("checked",true);
	$("#se_cqrate").val(30);
	$("#se_ktrate").val(30);
	$("#se_syrate").val(40);
	$("#sp_cqrate").html("30%");
	$("#sp_ktrate").html("30%");
	$("#sp_syrate").html("40%");
}
//出勤成绩发生变化
function gChange(obj){
	var oid = $(obj).attr("id");
	var gid = "#grade_"+oid.substring(3);
	var cqid = "#cq_"+oid.substring(3);
	var ktid = "#kt_"+oid.substring(3);
	var syid = "#sy_"+oid.substring(3);
	var cqrate = $("#div_cqrate").html();
	var ktrate = $("#div_ktrate").html();
	var syrate = $("#div_syrate").html();
	var cqgrade = $(cqid).val();
	var ktgrade = $(ktid).val();
	var sygrade = $(syid).val();
	var grade = 100;
	if(typeof(cqrate)!="undefined"&&typeof(ktrate)!="undefined"&&typeof(syrate)!="undefined"){
		grade = (cqrate*cqgrade+ktrate*ktgrade+syrate*sygrade)/100;
	}else if(typeof(ktrate)!="undefined"&&typeof(syrate)!="undefined"){
		grade=(ktrate*ktgrade+syrate*sygrade)/100;
	}else if(typeof(cqrate)!="undefined"&&typeof(ktrate)!="undefined"){
		grade = (cqrate*cqgrade+ktrate*ktgrade)/100;
	}else if(typeof(cqrate)!="undefined"&&typeof(syrate)!="undefined"){
		grade = (cqrate*cqgrade+syrate*sygrade)/100;
	}else if(typeof(cqrate)!="undefined"){
		grade = cqgrade;
	}else if(typeof(ktrate)!="undefined"){
		grade = ktgrade;
	}else if(typeof(syrate)!="undefined"){
		grade = sygrade;
	}
	var mode = $("#mode").html();
	if(mode=="wujifenzhi"){
		if(grade>=95){
			grade=95;
		}else if(grade>=85){
			grade = 85;
		}else if(grade>=75){
			grade = 75;
		}else if(grade>=60){
			grade = 60;
		}else {
			grade = 50;
		}
	}else{
		grade = Math.round(grade);
	}
	$(gid).val(grade);
}

//重置成绩
function resetGrade(){
	$(".se_bfz").val(100);
	$(".se_bfz_grade").val(100);
	$(".se_wjfz").val(95);
	$(".ip-grade-memo").val("");
}

//重置计分方式
function resetGradeMode(){
	if(confirm("重置计分方式会导致当前所保存的成绩失效，是否继续？")){
		window.location.href="${pageContext.request.contextPath}/skgl/home_resetGradeMode.action?oid="+$("#oid").html();
	}
}
//保存成绩
function saveGrade(){
	$("#fm1").attr("action","${pageContext.request.contextPath}/skgl/home_saveGrade.action");
	$("#fm1").submit();
}

//提交成绩
function submitGrade(){
	if(confirm("成绩提交后不可更改，确认提交吗？")){
		$("#fm1").attr("action","${pageContext.request.contextPath}/skgl/home_submitGrade.action");
		$("#fm1").submit();
	}
}

//将学生信息导出到excel
function exportStudentToExcel(){
	$("#fm1").attr("action","${pageContext.request.contextPath}/skgl/home_exportStudent.action");
	$("#fm1").submit();
}

//将当前学生成绩导出到excel
function exportGradeToExcel(){
	$(".dis").removeAttr("disabled");
	$("#fm1").attr("action","${pageContext.request.contextPath}/skgl/home_exportGrade.action");
	$("#fm1").submit();
	$(".dis").attr("disabled","disabled");
}

//将当前页面上的所有学生成绩导出到excel
function exportManyGradeToExcel(){
	$("#fm1").attr("action","${pageContext.request.contextPath}/skgl/home_exportManyGrade.action");
	$("#fm1").submit();
}