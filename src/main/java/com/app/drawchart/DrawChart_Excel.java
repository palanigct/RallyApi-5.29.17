package com.app.drawchart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Name;

import com.app.excelwrite.Excel_Write;

public class DrawChart_Excel 
{
	public static Excel_Write excelsheet=new Excel_Write();
	
	
	public static void draw_chart_in_excel() throws IOException
	{
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";	
		FileInputStream file = new FileInputStream(new File(filename));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet("Dashboard");
		int ite_rowNum=excelsheet.getCurrentRow_ite();
		int rele_rowNum=excelsheet.getCurrentRow_rele();
		
		Name rangeCell = workbook.getName("UserStories_values");
		String reference = "Dashboard" + "!$B$" + ( ite_rowNum ) + ":$F$" + (ite_rowNum); 		
		rangeCell.setRefersToFormula(reference);

		rangeCell = workbook.getName("Defects_values"); 
		reference = "Dashboard" + "!$H$"+(ite_rowNum) + ":$L$" + (ite_rowNum);
		rangeCell.setRefersToFormula(reference); 
		
		rangeCell = workbook.getName("TestCases_values"); 
		reference ="Dashboard" + "!$N$"+(ite_rowNum) + ":$R$" + (ite_rowNum);
		rangeCell.setRefersToFormula(reference); 
	 
		FileOutputStream f = new FileOutputStream(filename);
		workbook.write(f);
		f.close();

		//System.out.println("work draw chart ");
		//System.out.println(excelsheet.getCurrentRow_ite()+ " "+excelsheet.getCurrentRow_rele());
		
	    filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";

	    file = new FileInputStream(new File(filename));
		workbook = new HSSFWorkbook(file);
		sheet = workbook.getSheet("Dashboard");
		
		rangeCell = workbook.getName("UserStories_values");
		reference = "Dashboard" + "!$B$" + ( rele_rowNum ) + ":$F$" + (rele_rowNum); 		
		rangeCell.setRefersToFormula(reference);

		rangeCell = workbook.getName("Defects_values"); 
		reference = "Dashboard" + "!$H$"+(rele_rowNum) + ":$L$" + (rele_rowNum);
		rangeCell.setRefersToFormula(reference); 
		
		rangeCell = workbook.getName("TestCases_values"); 
		reference = "Dashboard" + "!$N$"+(rele_rowNum) + ":$R$" + (rele_rowNum);
		rangeCell.setRefersToFormula(reference); 
		
		//Sheet1!$N$4:$R$4  Sheet1!$H$4:$L$4  Sheet1!$B$4:$F$4
	
	    f = new FileOutputStream(filename);
		workbook.write(f);
		f.close();

	}

}
