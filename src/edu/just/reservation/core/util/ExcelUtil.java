package edu.just.reservation.core.util;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public class ExcelUtil {

	//导出同一个授课关系的所有成绩
	public static void exportExcelManyGrade(Map<Student, List<Grade>> gradeMap,
			ServletOutputStream outputStream) {
		try {
			List<Grade> list=null;
			int count = 0;
			for(Student stu:gradeMap.keySet()){
				list = gradeMap.get(stu);
				break;
			}
			if(list!=null){
				count = list.size();
			}
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 2*count+1);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("成绩列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(16);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("成绩列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "学生学号", "学生姓名","成绩","备注"};
			// 遍历创建列标题单元格
			for (int i = 0; i < 2+2*count; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				if(i<=3){
					cell.setCellValue(colTitleArray[i]);
				}else if(i%2==1){
					cell.setCellValue(colTitleArray[3]);
				}else{
					cell.setCellValue(colTitleArray[2]);
				}
			}
			// 将成绩写入excel
			if (gradeMap != null) {
				int rowIndex = 2;
				for(Student stu:gradeMap.keySet()){
					HSSFRow row = sheet.createRow(rowIndex);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(stu.getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(stu.getName());
					list = gradeMap.get(stu);
					for(int j = 0;j<list.size();j++){
						Grade g = list.get(j);
						HSSFCell cell3 = row.createCell(2+j*2);
						cell3.setCellValue(g.getGrade());
						HSSFCell cell4 = row.createCell(3+j*2);
						cell4.setCellValue(g.getMemo());
					}
					rowIndex++;
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//导出成绩
	public static void exportExcelGrade(List<Grade> gradeList,
			ServletOutputStream outputStream) {
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 6);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("成绩列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(20);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("成绩列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "学生学号", "学生姓名","出勤","课堂表现","实验情况","成绩","备注"};
			// 遍历创建列标题单元格
			for (int i = 0; i < 7; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将成绩写入excel
			if (gradeList != null) {
				for (int j = 0; j < gradeList.size(); j++) {
					Grade grade = gradeList.get(j);
					HSSFRow row = sheet.createRow(2 + j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(grade.getStudent().getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(grade.getStudent().getName());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(grade.getCq());
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue(grade.getKt());
					HSSFCell cell5 = row.createCell(4);
					cell5.setCellValue(grade.getSy());
					HSSFCell cell6 = row.createCell(5);
					cell6.setCellValue(grade.getGrade());
					HSSFCell cell7 = row.createCell(6);
					cell7.setCellValue(grade.getMemo());
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//导出学生名单，用于成绩登记
	public static void exportStudentExcel(List<Student> stuList,
			ServletOutputStream outputStream) {
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 3);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("学生列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("学生列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "学生学号", "学生姓名","成绩","备注"};
			// 遍历创建列标题单元格
			for (int i = 0; i < 4; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将学生数据写入excel
			if (stuList != null) {
				for (int j = 0; j < stuList.size(); j++) {
					Student student = stuList.get(j);
					HSSFRow row = sheet.createRow(2 + j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(student.getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(student.getName());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue("");
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue("");
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导出学生信息
	public static void exportExcelStudent(List<Student> studentList,ServletOutputStream outputStream){
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 4);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("学生列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("学生列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "学生学号", "学生姓名", "学生所在班级","用户账号","用户名"};
			// 遍历创建列标题单元格
			for (int i = 0; i < 5; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将学生数据写入excel
			if (studentList != null) {
				for (int j = 0; j < studentList.size(); j++) {
					Student student = studentList.get(j);
					HSSFRow row = sheet.createRow(2 + j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(student.getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(student.getName());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(student.getClazz().getCnumber());
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue(student.getUser().getAccount());
					HSSFCell cell5 = row.createCell(4);
					cell5.setCellValue(student.getUser().getName());
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导出教师信息
	public static void exportExcelTeacher(List<Teacher> teacherList,ServletOutputStream outputStream){
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 4);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("教师列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("教师列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "教师工号", "教师姓名", "教师职称", "用户账号","用户名" };
			// 遍历创建列标题单元格
			for (int i = 0; i < 5; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将教师数据写入excel
			if (teacherList != null) {
				for (int j = 0; j < teacherList.size(); j++) {
					Teacher teacher = teacherList.get(j);
					HSSFRow row = sheet.createRow(2 + j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(teacher.getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(teacher.getName());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(teacher.getProTitle());
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue(teacher.getUser().getAccount());
					HSSFCell cell5 = row.createCell(4);
					cell5.setCellValue(teacher.getUser().getName());
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//导出用户信息
	public static void exportExcelUser(List<User> userList,
			ServletOutputStream outputStream) {
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 3);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("用户列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "用户名", "帐号", "性别", "电子邮箱" };
			// 遍历创建列标题单元格
			for (int i = 0; i < 4; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将用户数据写入excel
			if (userList != null) {
				for (int j = 0; j < userList.size(); j++) {
					HSSFRow row = sheet.createRow(2 + j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(userList.get(j).getName());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(userList.get(j).getAccount());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(userList.get(j).isGender() ? "男" : "女");
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue(userList.get(j).getEmail());
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将授课关系信息导出到excel，导出的excel样式
	 * 	1、授课关系列表
	 * 	2、教师工号、教师姓名、课程号、课程名、班级代号、专业、学期
	 * @param list
	 * @param outputStream
	 */
	public static void exportExcelTeachRelation(List<TeachRelation> list,
			ServletOutputStream outputStream) {
		try {
			// 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress mergeCells = new CellRangeAddress(0, 0, 0, 6);
			// 创建头标题样式
			HSSFCellStyle headTitleStyle = getStyle(workbook, (short) 16);
			// 创建列标题样式
			HSSFCellStyle columnTitleStyle = getStyle(workbook, (short) 13);
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("授课关系列表");
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 加载合并单元格对象
			sheet.addMergedRegion(mergeCells);
			// 创建头标题行
			HSSFRow rowTitle = sheet.createRow(0);
			// 创建头标题单元格
			HSSFCell cellHeaderTitle = rowTitle.createCell(0);
			// 设置头标题单元格样式
			cellHeaderTitle.setCellStyle(headTitleStyle);
			// 设置头标题单元格内容
			cellHeaderTitle.setCellValue("授课关系列表");
			// 创建列标题行
			HSSFRow cellColumnTitle = sheet.createRow(1);
			// 列标题数组
			String[] colTitleArray = new String[] { "教师工号","教师姓名","课程号","课程名","班级代号","专业","学期" };
			// 遍历创建列标题单元格
			for (int i = 0; i < 7; i++) {
				HSSFCell cell = cellColumnTitle.createCell(i);
				cell.setCellStyle(columnTitleStyle);
				cell.setCellValue(colTitleArray[i]);
			}
			// 将授课关系数据写入excel
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					HSSFRow row = sheet.createRow(2 + j);
					TeachRelation relation = list.get(j);
					HSSFCell cell1 = row.createCell(0);
					cell1.setCellValue(relation.getTeacher().getAccount());
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(relation.getTeacher().getName());
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(relation.getCourse().getCNumber());
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue(relation.getCourse().getCName());
					HSSFCell cell5 = row.createCell(4);
					cell5.setCellValue(relation.getClazz().getCnumber());
					HSSFCell cell6= row.createCell(5);
					cell6.setCellValue(relation.getClazz().getCname());
					HSSFCell cell7 = row.createCell(6);
					cell7.setCellValue(relation.getTerm());
				}
			}
			// 输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle getStyle(HSSFWorkbook workbook, short fontSize) {
		// 创建样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体大小
		font.setFontHeightInPoints(fontSize);
		style.setFont(font);
		return style;
	}


}
