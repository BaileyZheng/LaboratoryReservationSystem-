<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<form action="${pageContext.request.contextPath }/skgl/home_getMode.action?oid=${oid}&rid=${rid}" method="post">
		<table width="92%" height="98%" style="padding:10px;margin:12px;">
			<tr height="30px">
				<td>参与计算成绩的内容：</td>
				<td>
					<input type="checkbox" id="ck_chuqin" name="chuqin" value="chuqin" checked="true" onchange="ckChange(this)"/>&nbsp;出勤&nbsp;&nbsp;
					<input type="checkbox" id="ck_biaoxian" name="biaoxian" value="biaoxian" checked="true" onchange="ckChange(this)" />&nbsp;课堂表现&nbsp;&nbsp;
					<input type="checkbox" id="ck_shiyan" name="shiyan" value="shiyan" checked="true" onchange="ckChange(this)" />&nbsp;实验情况
				</td>
			</tr>
			<tr height="30px">
				<td>计分比率：</td>
				<td>
					<span id="sp_rate">
					出　　勤：<input style="width:100px;" name="cqrate" type="range" id="se_cqrate" min="0" max="100" value="30" onchange="rangeChange(this)"/> <span id="sp_cqrate">30%</span><br/>
					课堂表现：<input style="width:100px;" name="ktrate" type="range" id="se_ktrate" min="0" max="100" value="30" onchange="rangeChange(this)"/> <span id="sp_ktrate">30%</span><br/>
					实验情况：<input style="width:100px;" name="syrate" type="range" id="se_syrate" min="0" max="100" value="40" onchange="rangeChange(this)"/> <span id="sp_syrate">40%</span>
					</span>				
				</td>
			</tr>
			<tr height="30px">
				<td>计分形式：</td>
				<td>
					<select id="se_mode" name="mode">
						<option value="baifenzhi">百分制</option>
						<option value="wujifenzhi">五级分制</option>
					</select>
				</td>
			</tr>
			<tr height="40px">
				<td>　　　
					<input style="width:100px;height:25px;background-color:#EBF3FF;" type="reset" value="重置" onclick="myReset()"/>
				</td>
				<td>　　　　
					<input style="width:100px;height:25px;background-color:#EBF3FF;" type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
