package com.app.excelwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;


import com.app.excelread.Readfile;
import com.app.pojos.Defects;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.UserStories;

public class Excel_Write 
{
	public static int currentRow_ite=3;
	public static int currentRow_rele=3;	
	public static Readfile read = new Readfile();
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/main/resources/INPUT.xls", "APP_DATA");
	
	public static void write_Iteration_Status(TeamStatus team_status,String team_name) throws Exception
	{	
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
               
        HSSFCellStyle style1=sheet.getRow(2).getCell(24).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(23).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(team_name, "total"))
        	style1=style2=style3;
        
        	
        
        row=sheet.createRow(currentRow_ite);
        
       
        UserStories userstory= team_status.getUserStories();
		Defects defects=team_status.getDefects();
		TestCases testCase=team_status.getTestCases();	
		String per_exe="";
		String per_pass="";
		String per_fail="";
      
		if(testCase.getTotal()==0)
		{
			per_exe  = "N/A";
			per_pass = "N/A";
			per_fail = "N/A";			
		}
		else
		{
			per_exe  = testCase.getPercentage_execute()+"%";
			per_pass = testCase.getPercentage_pass()+"%";
			per_fail = testCase.getPercentage_fail()+"%";
		}
		
        for(short i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	
        	switch(i)
        	{
        	    case 0:   cell.setCellValue(team_name); 				 cell.setCellStyle(style1); break;
        		case 1:   cell.setCellValue(userstory.getBacklogs());    cell.setCellStyle(style2); break;
        		case 2:   cell.setCellValue(userstory.getDefined());     cell.setCellStyle(style2);break;
        		case 3:   cell.setCellValue(userstory.getIn_progress()); cell.setCellStyle(style2);break;
        		case 4:   cell.setCellValue(userstory.getCompleted());   cell.setCellStyle(style2);break;
        		case 5:   cell.setCellValue(userstory.getAccepted());    cell.setCellStyle(style2);break;        		
        		case 6:   cell.setCellValue(userstory.getTotal());       cell.setCellStyle(style3);break;
        		case 7:   cell.setCellValue(defects.getBacklogs());      cell.setCellStyle(style2);break;
        		case 8:   cell.setCellValue(defects.getDefined());       cell.setCellStyle(style2);break;
        		case 9:   cell.setCellValue(defects.getIn_progress());   cell.setCellStyle(style2);break;        	
        		case 10:  cell.setCellValue(defects.getCompleted());     cell.setCellStyle(style2);break;
        		case 11:  cell.setCellValue(defects.getAccepted());      cell.setCellStyle(style2);break;
        		case 12:   cell.setCellValue(defects.getTotal()); 	     cell.setCellStyle(style3);break;
        		case 13:   cell.setCellValue(testCase.getPass()); 	     cell.setCellStyle(style2);break;
        		case 14:   cell.setCellValue(testCase.getFail());        cell.setCellStyle(style2);break;        	
        		case 15:   cell.setCellValue(testCase.getIn_progress()); cell.setCellStyle(style2);break;
        		case 16:   cell.setCellValue(testCase.getBlocked()); cell.setCellStyle(style2);break;
        		case 17:   cell.setCellValue(testCase.getNo_run());  cell.setCellStyle(style2);break;      		
        		case 18:   cell.setCellValue(testCase.getTotal());	 cell.setCellStyle(style3);break; 
        		case 19:   cell.setCellValue(per_exe); 				 cell.setCellStyle(style2);break; 
        		case 20:   cell.setCellValue(per_pass); 			 cell.setCellStyle(style2);break; 
        		case 21:   cell.setCellValue(per_fail);	 			 cell.setCellStyle(style2);break; 
        		default:   cell.setCellValue("");  					break;
        	}        	
        }//end for
        
        
        currentRow_ite++;
        file.close();
 
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	public static void write_Release_Status(TeamStatus team_status,String team_name) throws Exception
	{		
		
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;       
        
        HSSFCellStyle style1=sheet.getRow(2).getCell(24).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(23).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(team_name, "total"))
        	style1=style2=style3;
        
        row=sheet.createRow(currentRow_rele);
       
        
        UserStories userstory= team_status.getUserStories();
		Defects defects=team_status.getDefects();
		TestCases testCase=team_status.getTestCases();	
		
		String per_exe="";
		String per_pass="";
		String per_fail="";
      
		if(testCase.getTotal()==0)
		{
			per_exe  = "N/A";
			per_pass = "N/A";
			per_fail = "N/A";			
		}
		else
		{
			per_exe  = testCase.getPercentage_execute()+"%";
			per_pass = testCase.getPercentage_pass()+"%";
			per_fail = testCase.getPercentage_fail()+"%";
		}
      
        for(short i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	
        	switch(i)
        	{
        	    case 0:   cell.setCellValue(team_name);  					cell.setCellStyle(style1); break;
        		case 1:   cell.setCellValue(userstory.getBacklogs()); 		cell.setCellStyle(style2); break;
        		case 2:   cell.setCellValue(userstory.getDefined()); 		cell.setCellStyle(style2); break;
        		case 3:   cell.setCellValue(userstory.getIn_progress()); 	cell.setCellStyle(style2); break;
        		case 4:   cell.setCellValue(userstory.getCompleted()); 		cell.setCellStyle(style2); break;
        		case 5:   cell.setCellValue(userstory.getAccepted()); 		cell.setCellStyle(style2); break;        		
        		case 6:   cell.setCellValue(userstory.getTotal()); 			cell.setCellStyle(style3); break;
        		case 7:   cell.setCellValue(defects.getBacklogs()); 		cell.setCellStyle(style2); break;
        		case 8:   cell.setCellValue(defects.getDefined()); 			cell.setCellStyle(style2); break;
        		case 9:   cell.setCellValue(defects.getIn_progress()); 		cell.setCellStyle(style2); break;        	
        		case 10:  cell.setCellValue(defects.getCompleted()); 		cell.setCellStyle(style2); break;
        		case 11:  cell.setCellValue(defects.getAccepted()); 		cell.setCellStyle(style2); break;
        		case 12:   cell.setCellValue(defects.getTotal()); 			cell.setCellStyle(style3); break;
        		case 13:   cell.setCellValue(testCase.getPass()); 			cell.setCellStyle(style2); break;
        		case 14:   cell.setCellValue(testCase.getFail()); 			cell.setCellStyle(style2); break;        	
        		case 15:   cell.setCellValue(testCase.getIn_progress()); 	cell.setCellStyle(style2); break;
        		case 16:   cell.setCellValue(testCase.getBlocked()); 		cell.setCellStyle(style2); break;
        		case 17:   cell.setCellValue(testCase.getNo_run()); 		cell.setCellStyle(style2); break;      		
        		case 18:   cell.setCellValue(testCase.getTotal()); 			cell.setCellStyle(style3); break; 
        		case 19:   cell.setCellValue(per_exe); 						cell.setCellStyle(style2); break; 
        		case 20:   cell.setCellValue(per_pass); 					cell.setCellStyle(style2); break; 
        		case 21:   cell.setCellValue(per_fail); 					cell.setCellStyle(style2); break; 
        		default:   cell.setCellValue(""); break;
        	}        	
        }//end for
        
        
        currentRow_rele++;
        file.close();
 
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	public static void write_sprint_number(String name) throws IOException
	{
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        cell=sheet.getRow(0).getCell(20);
        cell.setCellValue(name);
        
        file.close();
        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();       
	}
	
	public static void write_release_number(String name) throws IOException
	{
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        cell=sheet.getRow(0).getCell(20);
        cell.setCellValue(name);
        
        file.close();
        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();       
	}
	
	public static void write_userstoryAndDefect(UserStories story,String teamName,String statusType,String type) throws IOException
	{
		String 	filename="";
		if(StringUtils.containsIgnoreCase(statusType, "iteration"))
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		else if(StringUtils.containsIgnoreCase(statusType, "release"))
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
		
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null; 
        
        if(StringUtils.containsIgnoreCase(type, "UserStory"))
        	 sheet = workbook.getSheet("UserStories");
        else if(StringUtils.containsIgnoreCase(type, "Defect"))
        	 sheet = workbook.getSheet("Defects");
        else if(StringUtils.containsIgnoreCase(type, "TestCase"))
       	 sheet = workbook.getSheet("TestCases");     
               
        
        int currentRow=sheet.getLastRowNum()+1;
        //int currentRow=2;
        //System.out.println("file path : "+filename+" "+sheet.getLastRowNum());
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName); break;
        		case 1: cell.setCellValue(story.getFormattedID()); break;
        		case 2: cell.setCellValue(story.getStatus());break;
        		case 3: cell.setCellValue(story.getSprintname());break;
        		case 4: cell.setCellValue(story.getReleaseName());break;
        		case 5: cell.setCellValue(story.getName());break;
        		case 6: cell.setCellValue(story.getTestable());break;
        		case 7: cell.setCellValue(story.getSeverity());break;
        		case 8: cell.setCellValue(story.getState());break;
        		case 9: cell.setCellValue(story.getCRNumber());break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	

	public static void write_automated_testcase_count(TestCases testcase,String teamName,String type_iteration_or_release) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
			else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null; 
                     
        sheet = workbook.getSheet("Automatable TestCases");
        HSSFCellStyle style1=sheet.getRow(2).getCell(4).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(5).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(6).getCellStyle();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<10;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName); 						cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(testcase.getTotal()); 			cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(testcase.getAutomated_count());	cell.setCellStyle(style2); break;
        		case 3: cell.setCellValue(testcase.getMethod_count());		cell.setCellStyle(style2); break; 
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	
	public static void write_testable_userstory_field_count(UserStories story,String teamName,String type_iteration_or_release,String type) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null; 
        
        /*if(StringUtils.containsIgnoreCase(type, "UserStory"))
        	 sheet = workbook.getSheet("UserStories");
        else if(StringUtils.containsIgnoreCase(type, "Defect"))
        	 sheet = workbook.getSheet("Defects");
        else if(StringUtils.containsIgnoreCase(type, "TestCase"))
       	 sheet = workbook.getSheet("TestCases"); */    
               
        sheet = workbook.getSheet("Testable UserStories Count");
        
        HSSFCellStyle style1=sheet.getRow(2).getCell(3).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(4).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(5).getCellStyle();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<10;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName); 						cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(story.getTotal());  				cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(story.getTestableFieldCount()); 	cell.setCellStyle(style2); break;      		
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
		
	public static void write_testable_userstories(UserStories story,String teamName,String type_iteration_or_release,String type) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("Testable UserStories");
        HSSFCellStyle style1=sheet.getRow(2).getCell(7).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(8).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(9).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(teamName, "total"))
        	style1=style2=style3;
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<10;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName);                        cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(story.getBacklogs_testable());    cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(story.getDefined_testable());     cell.setCellStyle(style2);break;
        		case 3: cell.setCellValue(story.getIn_progress_testable()); cell.setCellStyle(style2); break;
        		case 4: cell.setCellValue(story.getCompleted_testable());   cell.setCellStyle(style2);break;
        		case 5: cell.setCellValue(story.getAccepted_testable());    cell.setCellStyle(style2);break;
        		case 6: cell.setCellValue(story.getTotal_testable());       cell.setCellStyle(style3);break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	
	public static void write_Defect_Details_with_state_and_severity(Defects defects,String teamName,String type_iteration_or_release,String type_story_or_defect) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("Defect Details for Severity");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(teamName, "total"))
        	style1=style2=style3;
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName);                        cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(defects.getSubmitted());    cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(defects.getOpen());     cell.setCellStyle(style2);break;
        		case 3: cell.setCellValue(defects.getFixed()); cell.setCellStyle(style2); break;
        		case 4: cell.setCellValue(defects.getClosed());   cell.setCellStyle(style2);break;
        		case 5: cell.setCellValue(defects.getReopen());    cell.setCellStyle(style2);break;
        		case 6: cell.setCellValue(defects.getReady_for_test());       cell.setCellStyle(style2);break;
        		case 7: cell.setCellValue(defects.getTotal_state());       cell.setCellStyle(style3);break;
        		case 8: cell.setCellValue(defects.getCritical());       cell.setCellStyle(style2);break;
        		case 9: cell.setCellValue(defects.getAverage());       cell.setCellStyle(style2);break;
        		case 10: cell.setCellValue(defects.getMajor());       cell.setCellStyle(style2);break;
        		case 11: cell.setCellValue(defects.getMinor());       cell.setCellStyle(style2);break;
        		case 12: cell.setCellValue(defects.getTotal_severity());       cell.setCellStyle(style3);break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
		
	public static void write_testcase(TestCases testcase,String statusType) throws IOException
	{
		String 	filename="";
		if(StringUtils.containsIgnoreCase(statusType, "iteration"))
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		else
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null; 
        
        sheet = workbook.getSheet("TestCases");        
            
        int currentRow=sheet.getLastRowNum()+1;
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<10;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(testcase.getTeam_name()); break;
        		case 1: cell.setCellValue(testcase.getFormattedId()); break;
        		case 2: cell.setCellValue(testcase.getStatus());break;
        		case 3: cell.setCellValue("");break;
        		case 4: cell.setCellValue("");break;
        		case 5: cell.setCellValue(testcase.getWorkProduct_name());break;
        		case 6: cell.setCellValue(testcase.getAutomated());break; 
        		case 7:	cell.setCellValue(testcase.getMethod());break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}
	
	public static void write_testcase_details(TestCases testcase,String teamName,String type_iteration_or_release) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("TestCase Details");
        HSSFCellStyle style1=sheet.getRow(2).getCell(11).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(12).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(13).getCellStyle();
        
                
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName);                        cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(testcase.getExecuted());    cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(testcase.getPass());     cell.setCellStyle(style2);break;
        		case 3: cell.setCellValue(testcase.getFail()); cell.setCellStyle(style2); break;
        		case 4: cell.setCellValue(testcase.getIn_progress());   cell.setCellStyle(style2);break;
        		case 5: cell.setCellValue(testcase.getBlocked());    cell.setCellStyle(style2);break;
        		case 6: cell.setCellValue(testcase.getNo_run());       cell.setCellStyle(style2);break;
        		case 7: cell.setCellValue(testcase.getTotal());       cell.setCellStyle(style3);break;
        		case 8: cell.setCellValue(testcase.getPercentage_execute());       cell.setCellStyle(style2);break;
        		case 9: cell.setCellValue(testcase.getPercentage_pass());       cell.setCellStyle(style2);break;
        		case 10: cell.setCellValue(testcase.getPercentage_fail());       cell.setCellStyle(style2);break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
        	
        
		
	}
	
	public static void write_CRwise_userstories_and_defect(TeamStatus team_status,String teamName,String type_iteration_or_release) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("CRWise UserStories");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();
        
        UserStories userstory= team_status.getUserStories();
		Defects defects=team_status.getDefects();
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        	 		case 0:   cell.setCellValue(teamName); 				     cell.setCellStyle(style1); break;
        	 		case 1:   cell.setCellValue(userstory.getBacklogs());    cell.setCellStyle(style2); break;
        	 		case 2:   cell.setCellValue(userstory.getDefined());     cell.setCellStyle(style2);break;
        	 		case 3:   cell.setCellValue(userstory.getIn_progress()); cell.setCellStyle(style2);break;
        	 		case 4:   cell.setCellValue(userstory.getCompleted());   cell.setCellStyle(style2);break;
        	 		case 5:   cell.setCellValue(userstory.getAccepted());    cell.setCellStyle(style2);break;        		
        	 		case 6:   cell.setCellValue(userstory.getTotal());       cell.setCellStyle(style3);break;
        	 		case 7:   cell.setCellValue(defects.getBacklogs());      cell.setCellStyle(style2);break;
        	 		case 8:   cell.setCellValue(defects.getDefined());       cell.setCellStyle(style2);break;
        	 		case 9:   cell.setCellValue(defects.getIn_progress());   cell.setCellStyle(style2);break;        	
        	 		case 10:  cell.setCellValue(defects.getCompleted());     cell.setCellStyle(style2);break;
        	 		case 11:  cell.setCellValue(defects.getAccepted());      cell.setCellStyle(style2);break;
        	 		case 12:  cell.setCellValue(defects.getTotal()); 	     cell.setCellStyle(style3);break;
        	 		default:  break;        			
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
		
	public static void write_CRwise_defect_details(Defects defects,String teamName,String type_iteration_or_release) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("CRWise Defect Details");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();        
        
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        			case 0: cell.setCellValue(teamName);                        cell.setCellStyle(style1); break;
        			case 1: cell.setCellValue(defects.getSubmitted());    cell.setCellStyle(style2); break;
        			case 2: cell.setCellValue(defects.getOpen());     cell.setCellStyle(style2);break;
        			case 3: cell.setCellValue(defects.getFixed()); cell.setCellStyle(style2); break;
        			case 4: cell.setCellValue(defects.getClosed());   cell.setCellStyle(style2);break;
        			case 5: cell.setCellValue(defects.getReopen());    cell.setCellStyle(style2);break;
        			case 6: cell.setCellValue(defects.getReady_for_test());       cell.setCellStyle(style2);break;
        			case 7: cell.setCellValue(defects.getTotal_state());       cell.setCellStyle(style3);break;
        			case 8: cell.setCellValue(defects.getCritical());       cell.setCellStyle(style2);break;
        			case 9: cell.setCellValue(defects.getAverage());       cell.setCellStyle(style2);break;
        			case 10: cell.setCellValue(defects.getMajor());       cell.setCellStyle(style2);break;
        			case 11: cell.setCellValue(defects.getMinor());       cell.setCellStyle(style2);break;
        			case 12: cell.setCellValue(defects.getTotal_severity());       cell.setCellStyle(style3);break;
        			default : break;        			
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
	
	public static void write_CRwise_testCase_details(TestCases testcase,String teamName,String type_iteration_or_release) throws IOException
	{
		String 	filename="";
		int currentRow=0;
		if(StringUtils.containsIgnoreCase(type_iteration_or_release, "iteration"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			currentRow=currentRow_ite;
		}
		else if(StringUtils.containsIgnoreCase(type_iteration_or_release, "release"))
		{
			filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			currentRow=currentRow_rele;
		}
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null;        
               
        
        sheet = workbook.getSheet("CRWise TestCase Details");
        HSSFCellStyle style1=sheet.getRow(2).getCell(11).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(12).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(13).getCellStyle();        
        
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName);                        cell.setCellStyle(style1); break;
        		case 1: cell.setCellValue(testcase.getExecuted());    cell.setCellStyle(style2); break;
        		case 2: cell.setCellValue(testcase.getPass());     cell.setCellStyle(style2);break;
        		case 3: cell.setCellValue(testcase.getFail()); cell.setCellStyle(style2); break;
        		case 4: cell.setCellValue(testcase.getIn_progress());   cell.setCellStyle(style2);break;
        		case 5: cell.setCellValue(testcase.getBlocked());    cell.setCellStyle(style2);break;
        		case 6: cell.setCellValue(testcase.getNo_run());       cell.setCellStyle(style2);break;
        		case 7: cell.setCellValue(testcase.getTotal());       cell.setCellStyle(style3);break;
        		case 8: cell.setCellValue(testcase.getPercentage_execute());       cell.setCellStyle(style2);break;
        		case 9: cell.setCellValue(testcase.getPercentage_pass());       cell.setCellStyle(style2);break;
        		case 10: cell.setCellValue(testcase.getPercentage_fail());       cell.setCellStyle(style2);break;
        		default : break;       			
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
	
	
	public static int getCurrentRow_ite() {
		return currentRow_ite;
	}

	public static void setCurrentRow_ite(int currentRow_ite) {
		Excel_Write.currentRow_ite = currentRow_ite;
	}

	public static int getCurrentRow_rele() {
		return currentRow_rele;
	}

	public static void setCurrentRow_rele(int currentRow_rele) {
		Excel_Write.currentRow_rele = currentRow_rele;
	}

	public static void createRow_with_styleFormat() throws IOException
	{
		String newFilename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		FileInputStream Newfile = new FileInputStream(new File(newFilename));
        HSSFWorkbook newWorkbook = new HSSFWorkbook(Newfile);
        HSSFSheet newSheet = newWorkbook.getSheet("Sheet1");
        
        String oldFilename="src\\main\\resources\\Folder Templates\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		FileInputStream oldfile = new FileInputStream(new File(newFilename));
        HSSFWorkbook oldWorkbook = new HSSFWorkbook(oldfile);
        HSSFSheet oldSheet = oldWorkbook.getSheet("Sheet1");
             
        HSSFRow oldrow=null;
        HSSFRow newrow= null;        
        HSSFCell oldcell = null;
        HSSFCell newcell = null;
        
        newrow=newSheet.createRow(10);
        newrow.createCell(0).setCellValue("test1");
        newrow.createCell(1).setCellValue("test2");
        newrow.createCell(2).setCellValue("test3");
        
        //oldrow=oldSheet.getRow(11);
        
        HSSFCellStyle style1=newSheet.getRow(2).getCell(0).getCellStyle();
        HSSFCellStyle style2=newSheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=newSheet.getRow(2).getCell(23).getCellStyle();
        
        newrow.getCell(0).setCellStyle(style1); 
        newrow.getCell(1).setCellStyle(style2); 
        newrow.getCell(2).setCellStyle(style3); 
        
        /*for(int i=0;i<oldrow.getLastCellNum()-oldrow.getFirstCellNum();i++)
        {  
        	HSSFCellStyle style1=oldrow.getCell(i).getCellStyle();        	
        	newrow.getCell(i).setCellStyle(style1);     	
        }
        */
        
        
        Newfile.close();        
        FileOutputStream outFile =new FileOutputStream(new File(newFilename));
        newWorkbook.write(outFile);
        outFile.close();
	}
		
	public static void copy_cell_format()
	{
		
	}
	
	public static void main(String arg[]) throws IOException
	{
		/*Excel_Write obj=new Excel_Write();
		obj.createRow_with_styleFormat();*/
		
String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\TeamStatus.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("BCE - Sprint Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
               
        HSSFCellStyle style1=sheet.getRow(3).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(3).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(3).getCell(15).getCellStyle();
         
        System.out.println(style1+" "+style2+" "+style3);
        
        row=sheet.getRow(3);
        for(int i=0; i<row.getLastCellNum()-row.getFirstCellNum();i++)
        {
        	System.out.println(i+" "+row.getCell(i).getStringCellValue());
        }
		
	}
}   
        