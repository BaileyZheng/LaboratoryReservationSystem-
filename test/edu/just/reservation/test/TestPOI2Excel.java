package edu.just.reservation.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	@Test
	public void test03writeExcel() throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Hello");
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("hello world");
		
		FileOutputStream outputStream = new FileOutputStream(new File("D:\\≤‚ ‘.xls"));
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Test
	public void test03readExcel() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("D:\\≤‚ ‘.xls"));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheet("hello");
		HSSFRow row = sheet.getRow(2);
		HSSFCell cell = row.getCell(2);
		System.out.println(cell.getStringCellValue());
		workbook.close();
		inputStream.close();
	}
	
	@Test
	public void test07writeExcel() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Hello");
		XSSFRow row = sheet.createRow(2);
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("hello world");
		
		FileOutputStream outputStream = new FileOutputStream(new File("D:\\≤‚ ‘.xlsx"));
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Test
	public void test07readExcel() throws Exception {
		FileInputStream inputStream = new FileInputStream("D:\\≤‚ ‘.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("hello");
		XSSFRow row = sheet.getRow(2);
		XSSFCell cell = row.getCell(2);
		System.out.println(cell.getStringCellValue());
		workbook.close();
		inputStream.close();
	}

	@Test
	public void test03And07readExcel() throws Exception {
		String fileName = "D:\\≤‚ ‘.xlsx";
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			FileInputStream inputStream = new FileInputStream(fileName);
			Workbook workbook = is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet("hello");
			Row row = sheet.getRow(2);
			Cell cell = row.getCell(2);
			System.out.println(cell.getStringCellValue());
			workbook.close();
			inputStream.close();
		}
	}
	
	@Test
	public void test07writeExcelStyle() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellRangeAddress region = new CellRangeAddress(2,2,2,4);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		XSSFFont font = workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)16);
		
		style.setFont(font);
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		style.setFillForegroundColor(HSSFColor.RED.index);
		XSSFSheet sheet = workbook.createSheet("Hello");
		sheet.addMergedRegion(region);
		XSSFRow row = sheet.createRow(2);
		XSSFCell cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("Hello World!");
		
		FileOutputStream outputStream = new FileOutputStream(new File("D:\\≤‚ ‘.xlsx"));
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
