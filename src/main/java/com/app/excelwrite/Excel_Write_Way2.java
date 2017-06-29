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

import com.app.Rally.TeamWise.CRWise.Other_Functions;
import com.app.excelread.Readfile;
import com.app.pojos.Defect_Age;
import com.app.pojos.Defects;
import com.app.pojos.Defects_Application;
import com.app.pojos.Defects_CR;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_Application;
import com.app.pojos.TestCases_CR;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_Application;
import com.app.pojos.UserStories_CR;

public class Excel_Write_Way2 
{
	public static int currentRow_ite=3;
	public static int currentRow_rele=3;	
	public static Readfile read = new Readfile();
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/main/resources/INPUT.xls", "APP_DATA");
	public static Other_Functions other_functions=new Other_Functions();
	public static ArrayList<String> CR_list=other_functions.get_CR_List();	
	/*
	 
	TMWise-Dashboard
	TMWise-US-Details-Testable-CT
	TMWise-US-Details-Testable
	TMWise-DE-Details-Severity
	TMWise-DE-Details-Date
	TMWise-TC-Details
	TMWise-TC-Details-Automatable
	
	CRWise-Dashboard
	CRWise-US-Details-Testable-CT
	CRWise-US-Details-Testable 
	CRWise-DE-Details-Severity
	CRWise-DE-Details-Date
	CRWise-TC-Details
	CRWise-TC-Details-Automatable
	
	APPWise-Dashboard
	APPWise-US-Details-Testable-CT
	APPWise-US-Details-Testable
	APPWise-DE-Details-Severity
	APPWise-DE-Details-Date
	APPWise-TC-Details
	APPWise-TC-Details-Automatable

	Dashboard
	Testable UserStories Count
	Testable Userstories
	Defect Details for Severity
	Defect-Date	
	TestCase Details
	Automatable TestCase
	 
	 
	 */
	
	//===============================================TEAM========================================================
	
	public static void write_Iteration_Status_Dashboard(TeamStatus team_status,String team_name) throws Exception
	{	
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("TMWise-Dashboard");
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
	
	public static void write_Iteration_Status_Dashboard_TM_CRWise(TeamStatus team_status,String team_name) throws Exception
	{	
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		UserStories_CR userstory_details_cr=team_status.getUserstories_cr();
		Defects_CR defect_details_cr = team_status.getDefects_cr();
		
		
		int[] available_CRindex=team_status.getAvailable_CRindex();
		
		int[] backlogs_cr_us = userstory_details_cr.getBacklogs();
		int[] defined_cr_us  = userstory_details_cr.getDefined();
		int[] in_progress_cr_us = userstory_details_cr.getIn_progress();
		int[] completed_cr_us = userstory_details_cr.getCompleted();
		int[] accepted_cr_us = userstory_details_cr.getAccepted();
		int[] total_cr_us = userstory_details_cr.getTotal();
		
		int[] backlogs_cr_de = defect_details_cr.getBacklogs();
		int[] defined_cr_de  = defect_details_cr.getDefined();
		int[] in_progress_cr_de = defect_details_cr.getIn_progress();
		int[] completed_cr_de = defect_details_cr.getCompleted();
		int[] accepted_cr_de = defect_details_cr.getAccepted();
		int[] total_cr_de = defect_details_cr.getTotal();
		
		int[] pass = new int[10];
		int[] fail  = new int[10];
		int[] in_progress  = new int[10];
		int[] blocked  = new int[10];
		int[] no_run  = new int[10];
		int[] total  = new int[10];
		int[] percentage_execute  = new int[10];
		int[] percentage_pass  = new int[10];
		int[] percentage_fail  = new int[10];		
		int[] executed  = new int[10];
		
		
		
		
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("TM-CRWise-Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        int del[] = team_status.getAvailable_CRindex();
		System.out.println(del[0]+" "+del[1]+" "+del[2] );
        System.out.println(sheet.getLastRowNum()+ "-- row");
               
        HSSFCellStyle style1=sheet.getRow(2).getCell(24).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(23).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(24).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(team_name, "total"))
        	style1=style2=style3;
        
        	
        int currentRow=sheet.getLastRowNum()+1;       
        row=sheet.createRow(currentRow);        
        
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
        	    case 0:   cell.setCellValue(team_name); 
        	    case 1:   cell.setCellValue(userstory.getCRList());cell.setCellStyle(style1); break;
        		case 2:   cell.setCellValue(userstory.getBacklogs());    cell.setCellStyle(style2); break;
        		case 3:   cell.setCellValue(userstory.getDefined());     cell.setCellStyle(style2);break;
        		case 4:   cell.setCellValue(userstory.getIn_progress()); cell.setCellStyle(style2);break;
        		case 5:   cell.setCellValue(userstory.getCompleted());   cell.setCellStyle(style2);break;
        		case 6:   cell.setCellValue(userstory.getAccepted());    cell.setCellStyle(style2);break;        		
        		case 7:   cell.setCellValue(userstory.getTotal());       cell.setCellStyle(style3);break;
        		case 8:   cell.setCellValue(defects.getBacklogs());      cell.setCellStyle(style2);break;
        		case 9:   cell.setCellValue(defects.getDefined());       cell.setCellStyle(style2);break;
        		case 10:   cell.setCellValue(defects.getIn_progress());   cell.setCellStyle(style2);break;        	
        		case 11:  cell.setCellValue(defects.getCompleted());     cell.setCellStyle(style2);break;
        		case 12:  cell.setCellValue(defects.getAccepted());      cell.setCellStyle(style2);break;
        		case 13:   cell.setCellValue(defects.getTotal()); 	     cell.setCellStyle(style3);break;
        		case 14:   cell.setCellValue(testCase.getPass()); 	     cell.setCellStyle(style2);break;
        		case 15:   cell.setCellValue(testCase.getFail());        cell.setCellStyle(style2);break;        	
        		case 16:   cell.setCellValue(testCase.getIn_progress()); cell.setCellStyle(style2);break;
        		case 17:   cell.setCellValue(testCase.getBlocked()); cell.setCellStyle(style2);break;
        		case 18:   cell.setCellValue(testCase.getNo_run());  cell.setCellStyle(style2);break;      		
        		case 19:   cell.setCellValue(testCase.getTotal());	 cell.setCellStyle(style3);break; 
        		case 20:   cell.setCellValue(per_exe); 				 cell.setCellStyle(style2);break; 
        		case 21:   cell.setCellValue(per_pass); 			 cell.setCellStyle(style2);break; 
        		case 22:   cell.setCellValue(per_fail);	 			 cell.setCellStyle(style2);break; 
        		default:   cell.setCellValue("");  					break;
        	}        	
        }//end for
        
        
		
         currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int k=0;k<available_CRindex.length;k++)        	
        {
        	int i=100;
        	boolean check =false;
        	
        	if(available_CRindex[k]!=-1)
        	{    
        	   i=available_CRindex[k];
        	   row=sheet.createRow(currentRow+k);
        	   check=true;
        	}
           
        	
        	for(int j=0;j<25&&check==true;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            			case 0:   cell.setCellValue(" "); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 2:   cell.setCellValue(backlogs_cr_us[i]);    		 cell.setCellStyle(style2); break;
            	 		case 3:   cell.setCellValue(defined_cr_us[i]);     		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(in_progress_cr_us[i]); 		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(completed_cr_us[i]);   		 cell.setCellStyle(style2);break;
            	 		case 6:   cell.setCellValue(accepted_cr_us[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 7:   cell.setCellValue(total_cr_us[i]);       		 cell.setCellStyle(style3);break;
            	 		case 8:   cell.setCellValue(backlogs_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(defined_cr_de[i]);        cell.setCellStyle(style2);break;
            	 		case 10:   cell.setCellValue(in_progress_cr_de[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 11:  cell.setCellValue(completed_cr_de[i]);     cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(accepted_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 13:  cell.setCellValue(total_cr_de[i]);  	     cell.setCellStyle(style3);break;
            	 		case 14:  cell.setCellValue(pass[i]);        cell.setCellStyle(style2);break;
            	 		case 15:  cell.setCellValue(fail[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 16:  cell.setCellValue(in_progress[i]);     cell.setCellStyle(style2);break;
            	 		case 17:  cell.setCellValue(blocked[i]);      cell.setCellStyle(style2);break;
            	 		case 18:  cell.setCellValue(no_run[i]);  	     cell.setCellStyle(style2);break;
            	 		case 19:  cell.setCellValue(total[i]); 	   cell.setCellStyle(style3);break;        	
            	 		case 20:  cell.setCellValue(percentage_execute[i]);     cell.setCellStyle(style2);break;
            	 		case 21:  cell.setCellValue(percentage_pass[i]);      cell.setCellStyle(style2);break;
            	 		case 22:  cell.setCellValue(percentage_fail[i]);  	     cell.setCellStyle(style2);break;
            	 	
            	 		default:  break;        			
            	}//end of switch j            	
            } //end of for j                 	
        } //end of for k
        
        
        currentRow_ite++;
        file.close();
 
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	
	public static void write_Release_Status_Dashboard(TeamStatus team_status,String team_name) throws Exception
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
	
	public static void write_US_Details_Testable_CT(UserStories story,String teamName,String type_iteration_or_release,String type) throws IOException
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
               
        sheet = workbook.getSheet("TMWise-US-Details-Testable-CT");
        
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
		
	public static void write_US_Details_Testable(UserStories story,String teamName,String type_iteration_or_release,String type) throws IOException
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
               
        
        sheet = workbook.getSheet("TMWise-US-Details-Testable");
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
	
	public static void write_DE_Details_Severity(Defects defects,String teamName,String type_iteration_or_release,String type_story_or_defect) throws IOException
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
               
        
        sheet = workbook.getSheet("TMWise-DE-Details-Severity");
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
	
	public static void write_DE_Details_Date(Defects defects,String teamName,String type_iteration_or_release,String type_story_or_defect) throws IOException
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
               
        
        sheet = workbook.getSheet("TMWise-DE-Details-Date");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle(); 
        HSSFCellStyle style4=sheet.getRow(2).getCell(16).getCellStyle(); 
        HSSFCellStyle style5=sheet.getRow(2).getCell(17).getCellStyle(); 
      
        
        int k=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        // row=sheet.createRow(currentRow);       
        
		   Defect_Age defect_age=defects.getDefect_age();		         
          
		
        	row=sheet.createRow(k++);
        	cell=row.createCell(0);	cell.setCellValue(teamName); 	cell.setCellStyle(style1); 
        	cell=row.createCell(1); cell.setCellValue("Day 1"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(2); cell.setCellValue("Day 2"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(3); cell.setCellValue("Day 3"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(4); cell.setCellValue("Day 4,5"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(5); cell.setCellValue("Day above 5"); 	cell.setCellStyle(style5); 
        	
        	
        	Row row_critical=sheet.createRow(k++);
        	Row row_major=sheet.createRow(k++);
        	Row row_average=sheet.createRow(k++);
        	Row row_minor=sheet.createRow(k++);
        	Row row_total=sheet.createRow(k++);
        	
        	for(int j=0;j<25;j++)
            {
            	Cell cell_critical  = row_critical.createCell(j);
            	Cell cell_major     = row_major.createCell(j);
            	Cell cell_average   = row_average.createCell(j);
            	Cell cell_minor     = row_minor.createCell(j);
            	Cell cell_total     = row_total.createCell(j);
            	
            	switch(j)
            	{
            	 		case 0:  cell_critical.setCellValue("Critical"); cell_critical.setCellStyle(style4); 
            	 				 cell_major.setCellValue("Major"); cell_major.setCellStyle(style4);	
            	 				 cell_average.setCellValue("Average"); cell_average.setCellStyle(style4);
            	 				 cell_minor.setCellValue("Minor"); cell_minor.setCellStyle(style4);
            	 				 cell_total.setCellValue("Total"); cell_total.setCellStyle(style3);
            	 				 break;
            	 		case 1:  cell_critical.setCellValue(defect_age.getCritic_day1()); cell_critical.setCellStyle(style2); 
   	 				 			 cell_major.setCellValue(defect_age.getMaj_day1()); cell_major.setCellStyle(style2);	
   	 				 			 cell_average.setCellValue(defect_age.getAve_day1()); cell_average.setCellStyle(style2);
   	 				 			 cell_minor.setCellValue(defect_age.getMin_day1()); cell_minor.setCellStyle(style2);
   	 				 			 cell_total.setCellValue(defect_age.getTot_severity_day1() ); cell_total.setCellStyle(style3);
   	 				 			 break;
            	 		case 2:  cell_critical.setCellValue(defect_age.getCritic_day2() ); cell_critical.setCellStyle(style2); 
 				 			 	 cell_major.setCellValue(defect_age.getMaj_day2() ); cell_major.setCellStyle(style2);	
 				 			 	 cell_average.setCellValue(defect_age.getAve_day2() ); cell_average.setCellStyle(style2);
 				 			 	 cell_minor.setCellValue( defect_age.getMin_day2()); cell_minor.setCellStyle(style2);
 				 			 	 cell_total.setCellValue( defect_age.getTot_severity_day2()); cell_total.setCellStyle(style3);
 				 			 	 break;
            	 		case 3:  cell_critical.setCellValue(defect_age.getCritic_day3() ); cell_critical.setCellStyle(style2); 
 				 			 	 cell_major.setCellValue( defect_age.getMaj_day3()); cell_major.setCellStyle(style2);	
 				 			 	 cell_average.setCellValue(defect_age.getAve_day3() ); cell_average.setCellStyle(style2);
 				 			 	 cell_minor.setCellValue( defect_age.getMin_day3()  ); cell_minor.setCellStyle(style2);
 				 			 	 cell_total.setCellValue( defect_age.getTot_severity_day3()); cell_total.setCellStyle(style3);
 				 			 	 break;
            	 		case 4: cell_critical.setCellValue(defect_age.getCritic_day3_5() ); cell_critical.setCellStyle(style2); 
 				 			 	cell_major.setCellValue(defect_age.getMaj_day3_5() ); cell_major.setCellStyle(style2);	
 				 			 	cell_average.setCellValue( defect_age.getAve_day3_5()); cell_average.setCellStyle(style2);
 				 			 	cell_minor.setCellValue(defect_age.getMin_day3_5() ); cell_minor.setCellStyle(style2);
 				 			 	cell_total.setCellValue(defect_age.getTot_severity_day3_5() ); cell_total.setCellStyle(style3);
 				 			 	break;
            	 		case 5: cell_critical.setCellValue( defect_age.getCritic_day5()); cell_critical.setCellStyle(style2); 
 				 			 	cell_major.setCellValue( defect_age.getMaj_day5()); cell_major.setCellStyle(style2);	
 				 			 	cell_average.setCellValue(defect_age.getAve_day5() ); cell_average.setCellStyle(style2);
 				 			 	cell_minor.setCellValue(defect_age.getMin_day5() ); cell_minor.setCellStyle(style2);
 				 			 	cell_total.setCellValue( defect_age.getTot_severity_day5()); cell_total.setCellStyle(style3);
 				 			 	break;


            	 		default:  break;        			
            	}            	
            }
        	row=sheet.createRow(k++);      	
        	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	

	public static void write_TC_Details(TestCases testcase,String teamName,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("TMWise-TC-Details");
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
	
	public static void write_TC_Details_Automatable(TestCases testcase,String teamName,String type_iteration_or_release) throws IOException, Exception
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
               
        
        //sheet = workbook.getSheet("TMWise-TC-Details-Auto");
        sheet = workbook.getSheet("TMWise-TC-Details-Automatable");
       
        HSSFCellStyle style1=sheet.getRow(2).getCell(4).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(5).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(6).getCellStyle();
        
                
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        row=sheet.createRow(currentRow);
               
        for(int i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName); 					    cell.setCellStyle(style1); break;
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
	
	//=============================================CR==========================================================
	

	public static void write_Iteration_Status_Dashboard_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws Exception
	{	
		//String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		

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
               
        
        sheet = workbook.getSheet("CRWise-Dashboard");
        HSSFCellStyle style1=sheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(23).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(24).getCellStyle();
        
     		
		UserStories_CR userstory_details_cr=team_status.getUserstories_cr();
		Defects_CR defect_details_cr = team_status.getDefects_cr();
		
		
		int[] backlogs_cr_us = userstory_details_cr.getBacklogs();
		int[] defined_cr_us  = userstory_details_cr.getDefined();
		int[] in_progress_cr_us = userstory_details_cr.getIn_progress();
		int[] completed_cr_us = userstory_details_cr.getCompleted();
		int[] accepted_cr_us = userstory_details_cr.getAccepted();
		int[] total_cr_us = userstory_details_cr.getTotal();
		
		int[] backlogs_cr_de = defect_details_cr.getBacklogs();
		int[] defined_cr_de  = defect_details_cr.getDefined();
		int[] in_progress_cr_de = defect_details_cr.getIn_progress();
		int[] completed_cr_de = defect_details_cr.getCompleted();
		int[] accepted_cr_de = defect_details_cr.getAccepted();
		int[] total_cr_de = defect_details_cr.getTotal();
		
		int[] pass = new int[10];
		int[] fail  = new int[10];
		int[] in_progress  = new int[10];
		int[] blocked  = new int[10];
		int[] no_run  = new int[10];
		int[] total  = new int[10];
		int[] percentage_execute  = new int[10];
		int[] percentage_pass  = new int[10];
		int[] percentage_fail  = new int[10];		
		int[] executed  = new int[10];
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(backlogs_cr_us[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(defined_cr_us[i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(in_progress_cr_us[i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(completed_cr_us[i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(accepted_cr_us[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(total_cr_us[i]);       		 cell.setCellStyle(style3);break;
            	 		case 7:   cell.setCellValue(backlogs_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 8:   cell.setCellValue(defined_cr_de[i]);        cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(in_progress_cr_de[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(completed_cr_de[i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(accepted_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_cr_de[i]);  	     cell.setCellStyle(style3);break;
            	 		case 13:  cell.setCellValue(pass[i]);        cell.setCellStyle(style2);break;
            	 		case 14:  cell.setCellValue(fail[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 15:  cell.setCellValue(in_progress[i]);     cell.setCellStyle(style2);break;
            	 		case 16:  cell.setCellValue(blocked[i]);      cell.setCellStyle(style2);break;
            	 		case 17:  cell.setCellValue(no_run[i]);  	     cell.setCellStyle(style2);break;
            	 		case 18:  cell.setCellValue(total[i]); 	   cell.setCellStyle(style3);break;        	
            	 		case 19:  cell.setCellValue(percentage_execute[i]);     cell.setCellStyle(style2);break;
            	 		case 20:  cell.setCellValue(percentage_pass[i]);      cell.setCellStyle(style2);break;
            	 		case 21:  cell.setCellValue(percentage_fail[i]);  	     cell.setCellStyle(style2);break;
            	 	
            	 		default:  break;        			
            	}            	
            }
        }
        
        
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	public static void write_Release_Status_Dashboard_CR() throws Exception
	{		
		
	}
	
	public static void write_US_Details_Testable_CT_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-US-Details-Testable-CT");
        HSSFCellStyle style1=sheet.getRow(2).getCell(3).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(4).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(5).getCellStyle();  
       
		UserStories_CR userstories_cr=new UserStories_CR();		
		int[] total = 	userstories_cr.getTotal();
		int[] testableFieldCount = userstories_cr.getTestableFieldCount();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0: cell.setCellValue(CR_list.get(i)); 						cell.setCellStyle(style1); break;
        				case 1: cell.setCellValue(total[i]);  				cell.setCellStyle(style2); break;
        				case 2: cell.setCellValue(testableFieldCount[i]); 	cell.setCellStyle(style2); break;      		
        				default : break;     			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();		
	}	
		
	public static void write_US_Details_Testable_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-US-Details-Testable");
        HSSFCellStyle style1=sheet.getRow(2).getCell(7).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(8).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(9).getCellStyle();        
        
        Defects_CR defect_details  = team_status.getDefects_cr();
        UserStories_CR userstories_cr=new UserStories_CR();		
		
	    int[] backlogs_testable = userstories_cr.getBacklogs_testable();
	    int[] defined_testable = userstories_cr.getDefined_testable();
		int[] in_progress_testable = userstories_cr.getIn_progress_testable();
		int[] completed_testable = userstories_cr.getCompleted_testable();
		int[] accepted_testable = userstories_cr.getAccepted_testable();
		int[] total_testable = userstories_cr.getTotal_testable();
		
        
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(backlogs_testable [i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(defined_testable [i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(in_progress_testable [i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(completed_testable [i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(accepted_testable [i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(total_testable [i]);       		 cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}	
	
	public static void write_DE_Details_Severity_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-DE-Details-Severity");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();        
        
        Defects_CR defect_details  = team_status.getDefects_cr();
				
        int[] submitted = defect_details .getSubmitted();
		int[] open = defect_details .getOpen();
		int[] fixed =defect_details .getFixed();
		int[] closed =defect_details .getClosed();
		int[] reopen =defect_details .getReopen();
		int[] ready_for_test =defect_details .getReady_for_test();
		int[] total_severity =defect_details .getTotal_severity();
		int[] critical =defect_details .getCritical();
		int[] major=defect_details .getMajor();
		int[] average =defect_details .getAverage();
		int[] minor =defect_details .getMinor();
		int[] total_state =defect_details .getTotal_state();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(submitted [i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(open [i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(fixed [i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(closed [i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(reopen [i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(ready_for_test [i]);       		 cell.setCellStyle(style2);break;
            	 		case 7:    cell.setCellValue(total_state [i]);      cell.setCellStyle(style3);break;
            	 		case 8:    cell.setCellValue(critical [i]);        cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(major[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(average [i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(minor [i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_severity [i]);  	     cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	
	public static void write_DE_Details_Date_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-DE-Details-Date");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle(); 
        HSSFCellStyle style4=sheet.getRow(2).getCell(16).getCellStyle(); 
        HSSFCellStyle style5=sheet.getRow(2).getCell(17).getCellStyle(); 
        
        Defects_CR defect_details_cr = team_status.getDefects_cr();
		Defect_Age defect_age=defect_details_cr.getDefect_age();
		

		 int[] critical_day1 = defect_age.getCritical_day1();
		 int[] major_day1 = defect_age.getMajor_day1();
		 int[] average_day1 = defect_age.getAverage_day1();
		 int[] minor_day1 = defect_age.getMinor_day1();
		 int[] total_severity_day1 = defect_age.getTotal_severity_day1();
		
		 int[] critical_day2 = defect_age.getCritical_day2();
		 int[] major_day2 = defect_age.getMajor_day2();
		 int[] average_day2 = defect_age.getAverage_day2();
		 int[] minor_day2 = defect_age.getMinor_day2();
		 int[] total_severity_day2 = defect_age.getTotal_severity_day2();
		
		 int[] critical_day3 = defect_age.getCritical_day3();
		 int[] major_day3 = defect_age.getMajor_day3();
		 int[] average_day3 = defect_age.getAverage_day3();
		 int[] minor_day3 = defect_age.getMinor_day3();
		 int[] total_severity_day3 = defect_age.getTotal_severity_day3();
		
		 int[] critical_day3_5 = defect_age.getCritical_day3_5();
		 int[] major_day3_5 = defect_age.getMajor_day3_5();
		 int[] average_day3_5 = defect_age.getAverage_day3_5();
		 int[] minor_day3_5 = defect_age.getMinor_day3_5();
		 int[] total_severity_day3_5 = defect_age.getTotal_severity_day3_5();
		
		 int[] critical_day5 = defect_age.getCritical_day5();
		 int[] major_day5 = defect_age.getMajor_day5();
		 int[] average_day5 = defect_age.getAverage_day5();
		 int[] minor_day5 = defect_age.getMinor_day5();
		 int[] total_severity_day5 = defect_age.getTotal_severity_day5();
        
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
		int k=3;			
				
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(k++);
        	cell=row.createCell(0);	cell.setCellValue(CR_list.get(i)); 	cell.setCellStyle(style1); 
        	cell=row.createCell(1); cell.setCellValue("Day 1"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(2); cell.setCellValue("Day 2"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(3); cell.setCellValue("Day 3"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(4); cell.setCellValue("Day 4,5"); 	cell.setCellStyle(style5); 
        	cell=row.createCell(5); cell.setCellValue("Day above 5"); 	cell.setCellStyle(style5); 
        	
        	
        	Row row_critical=sheet.createRow(k++);
        	Row row_major=sheet.createRow(k++);
        	Row row_average=sheet.createRow(k++);
        	Row row_minor=sheet.createRow(k++);
        	Row row_total=sheet.createRow(k++);
        	
        	for(int j=0;j<25;j++)
            {
            	Cell cell_critical  = row_critical.createCell(j);
            	Cell cell_major     = row_major.createCell(j);
            	Cell cell_average   = row_average.createCell(j);
            	Cell cell_minor     = row_minor.createCell(j);
            	Cell cell_total     = row_total.createCell(j);
            	
            	switch(j)
            	{
            	 		case 0:  cell_critical.setCellValue("Critical"); cell_critical.setCellStyle(style4); 
            	 				 cell_major.setCellValue("Major"); cell_major.setCellStyle(style4);	
            	 				 cell_average.setCellValue("Average"); cell_average.setCellStyle(style4);
            	 				 cell_minor.setCellValue("Minor"); cell_minor.setCellStyle(style4);
            	 				 cell_total.setCellValue("Total"); cell_total.setCellStyle(style3);
            	 				 break;
            	 		case 1:  cell_critical.setCellValue(critical_day1[i]); cell_critical.setCellStyle(style2); 
   	 				 			 cell_major.setCellValue(major_day1[i]); cell_major.setCellStyle(style2);	
   	 				 			 cell_average.setCellValue(average_day1[i]); cell_average.setCellStyle(style2);
   	 				 			 cell_minor.setCellValue(minor_day1[i]); cell_minor.setCellStyle(style2);
   	 				 			 cell_total.setCellValue(total_severity_day1[i]); cell_total.setCellStyle(style3);
   	 				 			 break;
            	 		case 2:  cell_critical.setCellValue(critical_day2[i]); cell_critical.setCellStyle(style2); 
 				 			 	 cell_major.setCellValue(major_day2[i]); cell_major.setCellStyle(style2);	
 				 			 	 cell_average.setCellValue(average_day2[i]); cell_average.setCellStyle(style2);
 				 			 	 cell_minor.setCellValue(minor_day2[i]); cell_minor.setCellStyle(style2);
 				 			 	 cell_total.setCellValue(total_severity_day2[i]); cell_total.setCellStyle(style3);
 				 			 	 break;
            	 		case 3:  cell_critical.setCellValue(critical_day3[i]); cell_critical.setCellStyle(style2); 
 				 			 	 cell_major.setCellValue(major_day3[i]); cell_major.setCellStyle(style2);	
 				 			 	 cell_average.setCellValue(average_day3[i]); cell_average.setCellStyle(style2);
 				 			 	 cell_minor.setCellValue(minor_day3[i]); cell_minor.setCellStyle(style2);
 				 			 	 cell_total.setCellValue(total_severity_day3[i]); cell_total.setCellStyle(style3);
 				 			 	 break;
            	 		case 4:  cell_critical.setCellValue(critical_day3_5[i]); cell_critical.setCellStyle(style2); 
 				 			 	cell_major.setCellValue(major_day3_5[i]); cell_major.setCellStyle(style2);	
 				 			 	cell_average.setCellValue(average_day3_5[i]); cell_average.setCellStyle(style2);
 				 			 	cell_minor.setCellValue(minor_day3_5[i]); cell_minor.setCellStyle(style2);
 				 			 	cell_total.setCellValue(total_severity_day3_5[i]); cell_total.setCellStyle(style3);
 				 			 	break;
            	 		case 5: cell_critical.setCellValue(critical_day5[i]); cell_critical.setCellStyle(style2); 
 				 			 	cell_major.setCellValue(major_day5[i]); cell_major.setCellStyle(style2);	
 				 			 	cell_average.setCellValue(average_day5[i]); cell_average.setCellStyle(style2);
 				 			 	cell_minor.setCellValue(minor_day5[i]); cell_minor.setCellStyle(style2);
 				 			 	cell_total.setCellValue(total_severity_day5[i]); cell_total.setCellStyle(style3);
 				 			 	break;


            	 		default:  break;        			
            	}            	
            }
        	row=sheet.createRow(k++);
        	
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
		
	}	
	
	public static void write_TC_Details_CR(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-TC-Details");
        HSSFCellStyle style1=sheet.getRow(2).getCell(11).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(12).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(13).getCellStyle();        
        
		
        TestCases_CR testcase_details_cr=team_status.getTestcases_cr();
        
        int[] pass = testcase_details_cr.getPass();
	    int[] fail  = testcase_details_cr.getFail();
	    int[] in_progress =testcase_details_cr.getIn_progress();
	    int[] blocked  = testcase_details_cr.getBlocked();
	    int[] no_run = testcase_details_cr.getNo_run();
	    int[] total= testcase_details_cr.getTotal();		  
	    int[] method_count  =testcase_details_cr.getMethod_count();
	    int[] automated_count  = testcase_details_cr.getAutomated_count();
	    int[] executed  = testcase_details_cr.getExecuted();
	    
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<APP_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(executed[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(pass[i]);    		 cell.setCellStyle(style2); break;
            	 		case 3:   cell.setCellValue(fail[i]);     		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(in_progress[i]); 		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(blocked[i]);   		 cell.setCellStyle(style2);break;
            	 		case 6:   cell.setCellValue(no_run[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 7:   cell.setCellValue(total[i]);       		 cell.setCellStyle(style3);break;
            	 		case 8:    cell.setCellValue(1);      cell.setCellStyle(style2);break;
            	 		case 9:    cell.setCellValue(1);        cell.setCellStyle(style2);break;
            	 		case 10:  cell.setCellValue(1); 	   cell.setCellStyle(style2);break;        	
            	 			default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
        	
        
		
	}
	
	public static void write_TC_Details_Automatable_CR(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("CRWise-TC-Details-Automatable");
        HSSFCellStyle style1=sheet.getRow(2).getCell(4).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(5).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(6).getCellStyle();        
        
        TestCases_CR testcase_cr  = team_status.getTestcases_cr();
        int[] total        = testcase_cr.getTotal();
        int[] automated_count  = testcase_cr.getAutomated_count();
        int[] method_count  = testcase_cr.getMethod_count();
       
      
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(total [i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(automated_count [i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(method_count [i]); 		 cell.setCellStyle(style2);break;
            	 		
            	 		default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	
	
	
	//==========================================APPLICATION=============================================================


		public static void write_Iteration_Status_Dashboard_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws Exception
		{	
			//String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
			

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
	               
	        
	        sheet = workbook.getSheet("APPWise-Dashboard");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(22).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(23).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(24).getCellStyle();
	        
	     		
			UserStories_Application userstory_details_app=team_status.getUserstories_application();
			Defects_Application defect_details_app = team_status.getDefects_application();
			TestCases_Application testcase_details_app= team_status.getTestcases_application();
			
			int[] backlogs_app_us = userstory_details_app.getBacklogs();
			int[] defined_app_us  = userstory_details_app.getDefined();
			int[] in_progress_app_us = userstory_details_app.getIn_progress();
			int[] completed_app_us = userstory_details_app.getCompleted();
			int[] accepted_app_us = userstory_details_app.getAccepted();
			int[] total_app_us = userstory_details_app.getTotal();
			
			int[] backlogs_app_de = defect_details_app.getBacklogs();
			int[] defined_app_de  = defect_details_app.getDefined();
			int[] in_progress_app_de = defect_details_app.getIn_progress();
			int[] completed_app_de = defect_details_app.getCompleted();
			int[] accepted_app_de = defect_details_app.getAccepted();
			int[] total_app_de = defect_details_app.getTotal();
			
			int[] pass =  testcase_details_app.getPass();
			int[] fail  =  testcase_details_app.getFail();
			int[] in_progress  =  testcase_details_app.getIn_progress();
			int[] blocked  =  testcase_details_app.getBlocked();
			int[] no_run  =  testcase_details_app.getNo_run();
			int[] total  =  testcase_details_app.getTotal();
			int[] percentage_execute  =  testcase_details_app.getPercentage_execute();
			int[] percentage_pass  =  testcase_details_app.getPercentage_pass();
			int[] percentage_fail  = 		testcase_details_app.getPercentage_fail();
			int[] executed  =  testcase_details_app.getExecuted();
			  
			
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(backlogs_app_us[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(defined_app_us[i]);     		 cell.setCellStyle(style2);break;
	            	 		case 3:   cell.setCellValue(in_progress_app_us[i]); 		 cell.setCellStyle(style2);break;
	            	 		case 4:   cell.setCellValue(completed_app_us[i]);   		 cell.setCellStyle(style2);break;
	            	 		case 5:   cell.setCellValue(accepted_app_us[i]);   			 cell.setCellStyle(style2);break;        		
	            	 		case 6:   cell.setCellValue(total_app_us[i]);       		 cell.setCellStyle(style3);break;
	            	 		case 7:   cell.setCellValue(backlogs_app_de[i]);      cell.setCellStyle(style2);break;
	            	 		case 8:   cell.setCellValue(defined_app_de[i]);        cell.setCellStyle(style2);break;
	            	 		case 9:   cell.setCellValue(in_progress_app_de[i]); 	   cell.setCellStyle(style2);break;        	
	            	 		case 10:  cell.setCellValue(completed_app_de[i]);     cell.setCellStyle(style2);break;
	            	 		case 11:  cell.setCellValue(accepted_app_de[i]);      cell.setCellStyle(style2);break;
	            	 		case 12:  cell.setCellValue(total_app_de[i]);  	     cell.setCellStyle(style3);break;
	            	 		case 13:  cell.setCellValue(pass[i]);      cell.setCellStyle(style2);break;
	            	 		case 14:  cell.setCellValue(fail[i]);        cell.setCellStyle(style2);break;
	            	 		case 15:  cell.setCellValue(in_progress[i]); 	   cell.setCellStyle(style2);break;        	
	            	 		case 16:  cell.setCellValue(blocked[i]);     cell.setCellStyle(style2);break;
	            	 		case 17:  cell.setCellValue(no_run[i]);      cell.setCellStyle(style2);break;
	            	 		case 18:  cell.setCellValue(total[i]);  	     cell.setCellStyle(style3);break;
	            	 		case 19:  cell.setCellValue(percentage_execute[i]); 	   cell.setCellStyle(style2);break;        	
	            	 		case 20:  cell.setCellValue(percentage_pass[i]);     cell.setCellStyle(style2);break;
	            	 		case 21:  cell.setCellValue(percentage_fail[i]);      cell.setCellStyle(style2);break;
	            	 		
	            	 		default:  break;        			
	            	}            	
	            }
	        }
	        
	        
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
		}
		
		public static void write_Release_Status_Dashboard_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws Exception
		{		
			
		}
		
		
		public static void write_US_Details_Testable_CT_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-US-Details-Testable-CT");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(3).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(4).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(5).getCellStyle();
	        
	     		
			UserStories_Application userstory_details_app=team_status.getUserstories_application();
				
			int[] testableFieldCount = userstory_details_app.getTestableFieldCount();
			int[] total= userstory_details_app.getTotal();
			
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(total[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(testableFieldCount[i]);     		 cell.setCellStyle(style2);break;
	            	 
	            	 		default:  break;        			
	            	}            	
	            }
	        }
	        
	        
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
			
		}	
			
		public static void write_US_Details_Testable_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-US-Details-Testable");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(7).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(8).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(9).getCellStyle();
	        
	     		
			UserStories_Application userstory_details_app=team_status.getUserstories_application();
					
			
			int[] backlogs_testable = userstory_details_app.getBacklogs_testable();
			int[] defined_testable = userstory_details_app.getDefined_testable();
			int[] in_progress_testable = userstory_details_app.getIn_progress_testable();
			int[] completed_testable = userstory_details_app.getCompleted_testable();
			int[] accepted_testable = userstory_details_app.getAccepted_testable();
			int[] total_testable = userstory_details_app.getTotal_testable();
			
			
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(backlogs_testable[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(defined_testable[i]);     		 cell.setCellStyle(style2);break;
	            	 		case 3:   cell.setCellValue(in_progress_testable[i]); 		 cell.setCellStyle(style2);break;
	            	 		case 4:   cell.setCellValue(completed_testable[i]);   		 cell.setCellStyle(style2);break;
	            	 		case 5:   cell.setCellValue(accepted_testable[i]);   			 cell.setCellStyle(style2);break;        		
	            	 		case 6:   cell.setCellValue(total_testable[i]);      		 cell.setCellStyle(style3);break;
	            	 			     
	            	 		default:  break;        			
	            	}            	
	            }
	        }
	        
	        
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
			
		}	
		
		public static void write_DE_Details_Severity_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-DE-Details-Severity");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();        
	        
	        Defects_Application defect_details_app = team_status.getDefects_application();
					
	        int[] submitted_app= defect_details_app.getSubmitted();
			int[] open_app= defect_details_app.getOpen();
			int[] fixed_app=defect_details_app.getFixed();
			int[] closed_app=defect_details_app.getClosed();
			int[] reopen_app=defect_details_app.getReopen();
			int[] ready_for_test_app=defect_details_app.getReady_for_test();
			int[] total_severity_app=defect_details_app.getTotal_severity();
			int[] critical_app=defect_details_app.getCritical();
			int[] major_app=defect_details_app.getMajor();
			int[] average_app=defect_details_app.getAverage();
			int[] minor_app=defect_details_app.getMinor();
			int[] total_state_app=defect_details_app.getTotal_state();
	        
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(submitted_app[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(open_app[i]);     		 cell.setCellStyle(style2);break;
	            	 		case 3:   cell.setCellValue(fixed_app[i]); 		 cell.setCellStyle(style2);break;
	            	 		case 4:   cell.setCellValue(closed_app[i]);   		 cell.setCellStyle(style2);break;
	            	 		case 5:   cell.setCellValue(reopen_app[i]);   			 cell.setCellStyle(style2);break;        		
	            	 		case 6:   cell.setCellValue(ready_for_test_app[i]);       		 cell.setCellStyle(style2);break;
	            	 		case 7:    cell.setCellValue(total_state_app[i]);      cell.setCellStyle(style3);break;
	            	 		case 8:    cell.setCellValue(critical_app[i]);        cell.setCellStyle(style2);break;
	            	 		case 9:   cell.setCellValue(major_app[i]); 	   cell.setCellStyle(style2);break;        	
	            	 		case 10:  cell.setCellValue(average_app[i]);     cell.setCellStyle(style2);break;
	            	 		case 11:  cell.setCellValue(minor_app[i]);      cell.setCellStyle(style2);break;
	            	 		case 12:  cell.setCellValue(total_severity_app[i]);  	     cell.setCellStyle(style3);break;
	            	 		default:  break;        			
	            	}            	
	            }
	        }	
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
			
		}	
		
		public static void write_DE_Details_Date_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-DE-Details-Date");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle(); 
	        HSSFCellStyle style4=sheet.getRow(2).getCell(16).getCellStyle();
	        HSSFCellStyle style5=sheet.getRow(2).getCell(17).getCellStyle(); 
	        
	        Defects_Application defect_details_app = team_status.getDefects_application();
			Defect_Age defect_age=defect_details_app.getDefect_age();
			

			 int[] critical_day1 = defect_age.getCritical_day1();
			 int[] major_day1 = defect_age.getMajor_day1();
			 int[] average_day1 = defect_age.getAverage_day1();
			 int[] minor_day1 = defect_age.getMinor_day1();
			 int[] total_severity_day1 = defect_age.getTotal_severity_day1();
			
			 int[] critical_day2 = defect_age.getCritical_day2();
			 int[] major_day2 = defect_age.getMajor_day2();
			 int[] average_day2 = defect_age.getAverage_day2();
			 int[] minor_day2 = defect_age.getMinor_day2();
			 int[] total_severity_day2 = defect_age.getTotal_severity_day2();
			
			 int[] critical_day3 = defect_age.getCritical_day3();
			 int[] major_day3 = defect_age.getMajor_day3();
			 int[] average_day3 = defect_age.getAverage_day3();
			 int[] minor_day3 = defect_age.getMinor_day3();
			 int[] total_severity_day3 = defect_age.getTotal_severity_day3();
			
			 int[] critical_day3_5 = defect_age.getCritical_day3_5();
			 int[] major_day3_5 = defect_age.getMajor_day3_5();
			 int[] average_day3_5 = defect_age.getAverage_day3_5();
			 int[] minor_day3_5 = defect_age.getMinor_day3_5();
			 int[] total_severity_day3_5 = defect_age.getTotal_severity_day3_5();
			
			 int[] critical_day5 = defect_age.getCritical_day5();
			 int[] major_day5 = defect_age.getMajor_day5();
			 int[] average_day5 = defect_age.getAverage_day5();
			 int[] minor_day5 = defect_age.getMinor_day5();
			 int[] total_severity_day5 = defect_age.getTotal_severity_day5();
	        
	        
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
			int k=3;			
					
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(k++);
	        	cell=row.createCell(0);	cell.setCellValue(APP_list.get(i)); 	cell.setCellStyle(style1); 
	        	cell=row.createCell(1); cell.setCellValue("Day 1"); 	cell.setCellStyle(style5); 
	        	cell=row.createCell(2); cell.setCellValue("Day 2"); 	cell.setCellStyle(style5); 
	        	cell=row.createCell(3); cell.setCellValue("Day 3"); 	cell.setCellStyle(style5); 
	        	cell=row.createCell(4); cell.setCellValue("Day 4,5"); 	cell.setCellStyle(style5); 
	        	cell=row.createCell(5); cell.setCellValue("Day above 5"); 	cell.setCellStyle(style5); 
	        	
	        	
	        	Row row_critical=sheet.createRow(k++);
	        	Row row_major=sheet.createRow(k++);
	        	Row row_average=sheet.createRow(k++);
	        	Row row_minor=sheet.createRow(k++);
	        	Row row_total=sheet.createRow(k++);
	        	
	        	for(int j=0;j<25;j++)
	            {
	            	Cell cell_critical  = row_critical.createCell(j);
	            	Cell cell_major     = row_major.createCell(j);
	            	Cell cell_average   = row_average.createCell(j);
	            	Cell cell_minor     = row_minor.createCell(j);
	            	Cell cell_total     = row_total.createCell(j);
	            	
	            	switch(j)
	            	{
	            	 		case 0:  cell_critical.setCellValue("Critical"); cell_critical.setCellStyle(style4); 
	            	 				 cell_major.setCellValue("Major"); cell_major.setCellStyle(style4);	
	            	 				 cell_average.setCellValue("Average"); cell_average.setCellStyle(style4);
	            	 				 cell_minor.setCellValue("Minor"); cell_minor.setCellStyle(style4);
	            	 				 cell_total.setCellValue("Total"); cell_total.setCellStyle(style3);
	            	 				 break;
	            	 		case 1:  cell_critical.setCellValue(critical_day1[i]); cell_critical.setCellStyle(style2); 
       	 				 			 cell_major.setCellValue(major_day1[i]); cell_major.setCellStyle(style2);	
       	 				 			 cell_average.setCellValue(average_day1[i]); cell_average.setCellStyle(style2);
       	 				 			 cell_minor.setCellValue(minor_day1[i]); cell_minor.setCellStyle(style2);
       	 				 			 cell_total.setCellValue(total_severity_day1[i]); cell_total.setCellStyle(style3);
       	 				 			 break;
	            	 		case 2:  cell_critical.setCellValue(critical_day2[i]); cell_critical.setCellStyle(style2); 
	 				 			 	 cell_major.setCellValue(major_day2[i]); cell_major.setCellStyle(style2);	
	 				 			 	 cell_average.setCellValue(average_day2[i]); cell_average.setCellStyle(style2);
	 				 			 	 cell_minor.setCellValue(minor_day2[i]); cell_minor.setCellStyle(style2);
	 				 			 	 cell_total.setCellValue(total_severity_day2[i]); cell_total.setCellStyle(style3);
	 				 			 	 break;
	            	 		case 3:  cell_critical.setCellValue(critical_day3[i]); cell_critical.setCellStyle(style2); 
	 				 			 	 cell_major.setCellValue(major_day3[i]); cell_major.setCellStyle(style2);	
	 				 			 	 cell_average.setCellValue(average_day3[i]); cell_average.setCellStyle(style2);
	 				 			 	 cell_minor.setCellValue(minor_day3[i]); cell_minor.setCellStyle(style2);
	 				 			 	 cell_total.setCellValue(total_severity_day3[i]); cell_total.setCellStyle(style3);
	 				 			 	 break;
	            	 		case 4:  cell_critical.setCellValue(critical_day3_5[i]); cell_critical.setCellStyle(style2); 
	 				 			 	cell_major.setCellValue(major_day3_5[i]); cell_major.setCellStyle(style2);	
	 				 			 	cell_average.setCellValue(average_day3_5[i]); cell_average.setCellStyle(style2);
	 				 			 	cell_minor.setCellValue(minor_day3_5[i]); cell_minor.setCellStyle(style2);
	 				 			 	cell_total.setCellValue(total_severity_day3_5[i]); cell_total.setCellStyle(style3);
	 				 			 	break;
	            	 		case 5: cell_critical.setCellValue(critical_day5[i]); cell_critical.setCellStyle(style2); 
	 				 			 	cell_major.setCellValue(major_day5[i]); cell_major.setCellStyle(style2);	
	 				 			 	cell_average.setCellValue(average_day5[i]); cell_average.setCellStyle(style2);
	 				 			 	cell_minor.setCellValue(minor_day5[i]); cell_minor.setCellStyle(style2);
	 				 			 	cell_total.setCellValue(total_severity_day5[i]); cell_total.setCellStyle(style3);
	 				 			 	break;


	            	 		default:  break;        			
	            	}            	
	            }
	        	row=sheet.createRow(k++);
	        	
	        }	
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
			
		}	
		
		public static void write_TC_Details_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-TC-Details");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(11).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(12).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(13).getCellStyle();        
	        
			
	        TestCases_Application testcase_details_app=team_status.getTestcases_application();
	        
	        int[] pass = testcase_details_app.getPass();
		    int[] fail  = testcase_details_app.getFail();
		    int[] in_progress =testcase_details_app.getIn_progress();
		    int[] blocked  = testcase_details_app.getBlocked();
		    int[] no_run = testcase_details_app.getNo_run();
		    int[] total= testcase_details_app.getTotal();		  
		    int[] executed  = testcase_details_app.getExecuted();
		    
		    int[] percentage_execute  = testcase_details_app.getPercentage_execute();
			int[] percentage_pass  = testcase_details_app.getPercentage_pass();
			int[] percentage_fail  = testcase_details_app.getPercentage_fail();
	        
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(executed[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(pass[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 3:   cell.setCellValue(fail[i]);     		 cell.setCellStyle(style2);break;
	            	 		case 4:   cell.setCellValue(in_progress[i]); 		 cell.setCellStyle(style2);break;
	            	 		case 5:   cell.setCellValue(blocked[i]);   		 cell.setCellStyle(style2);break;
	            	 		case 6:   cell.setCellValue(no_run[i]);   			 cell.setCellStyle(style2);break;        		
	            	 		case 7:   cell.setCellValue(total[i]);       		 cell.setCellStyle(style3);break;
	            	 		case 8:    cell.setCellValue(percentage_execute[i]);      cell.setCellStyle(style2);break;
	            	 		case 9:    cell.setCellValue(percentage_pass[i]);        cell.setCellStyle(style2);break;
	            	 		case 10:  cell.setCellValue(percentage_fail[i]); 	   cell.setCellStyle(style2);break;        	
	            	 			default:  break;        			
	            	}            	
	            }
	        }	
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
	        	
	        
			
		}
		
		
		public static void write_TC_Details_Automatable_APP(TeamStatus team_status,ArrayList<String> APP_list,String type_iteration_or_release) throws IOException
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
	               
	        
	        sheet = workbook.getSheet("APPWise-TC-Details-Automatable");
	        HSSFCellStyle style1=sheet.getRow(2).getCell(4).getCellStyle();
	        HSSFCellStyle style2=sheet.getRow(2).getCell(5).getCellStyle();
	        HSSFCellStyle style3=sheet.getRow(2).getCell(6).getCellStyle();        
	        
			
	        TestCases_Application testcase_details_app=team_status.getTestcases_application();
	        	        
		    int[] total= testcase_details_app.getTotal();		 
		    int[] automated_count  = testcase_details_app.getAutomated_count();
		    int[] method_count  =testcase_details_app.getMethod_count();
		  
		   	        
	        //int currentRow=sheet.getLastRowNum()+1;       
	        //row=sheet.createRow(currentRow);currentRow_ite
	        //row=sheet.createRow(currentRow);
	        
	        for(int i=0;i<APP_list.size();i++)        	
	        {
	        	row=sheet.createRow(i+3);
	        	for(int j=0;j<25;j++)
	            {
	            	cell=row.createCell(j);
	            	switch(j)
	            	{
	            	 		case 0:   cell.setCellValue(APP_list.get(i)); 			 cell.setCellStyle(style1); break;
	            	 		case 1:   cell.setCellValue(total[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 2:   cell.setCellValue(automated_count[i]);    		 cell.setCellStyle(style2); break;
	            	 		case 3:   cell.setCellValue(method_count[i]);     		 cell.setCellStyle(style2);break;
	            	 		default:  break;        			
	            	}            	
	            }
	        }	
	        
	        file.close();        
	        FileOutputStream outFile =new FileOutputStream(new File(filename));
	        workbook.write(outFile);
	        outFile.close();
	        	
			
		}	
		
	//=====================================others old ========================================================================================	
		
		//======================================Others old =================================================================
		
	
	
	public static void write_US_Details_Dummy(UserStories story,String teamName,String statusType,String type_story_or_defect) throws IOException
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
        
        if(StringUtils.containsIgnoreCase(type_story_or_defect, "UserStory"))
        	 sheet = workbook.getSheet("UserStories");
        else if(StringUtils.containsIgnoreCase(type_story_or_defect, "Defect"))
        	 sheet = workbook.getSheet("Defects");
        else if(StringUtils.containsIgnoreCase(type_story_or_defect, "TestCase"))
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
        		case 10: cell.setCellValue(story.getApplication());break;
        		case 11: cell.setCellValue(story.getOpenedDate());break;
        		case 12: cell.setCellValue(story.getOpen_days());break;default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	public static void write_DE_Details_Dummy(UserStories story,String teamName,String statusType,String type_story_or_defect) throws IOException
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
        
        if(StringUtils.containsIgnoreCase(type_story_or_defect, "UserStory"))
        	 sheet = workbook.getSheet("UserStories");
        else if(StringUtils.containsIgnoreCase(type_story_or_defect, "Defect"))
        	 sheet = workbook.getSheet("Defects");
        else if(StringUtils.containsIgnoreCase(type_story_or_defect, "TestCase"))
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
        		case 10: cell.setCellValue(story.getApplication());break;
        		case 11: cell.setCellValue(story.getOpenedDate());break;
        		case 12: cell.setCellValue(story.getOpen_days());break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	public static void write_TC_Details_Dummy(TestCases testcase,String statusType) throws IOException
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

	public static void write_sprint_number(String name) throws IOException
	{
		String filename="src\\main\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("TMWise-Dashboard");
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
        HSSFSheet sheet = workbook.getSheet("TMWise-Dashboard");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        cell=sheet.getRow(0).getCell(20);
        cell.setCellValue(name);
        
        file.close();
        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();       
	}
	
	public static void write_CRwise_userstories_and_defect(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
		UserStories_CR userstory_details_cr=team_status.getUserstories_cr();
		Defects_CR defect_details_cr = team_status.getDefects_cr();
		
		//userstory_details_cr.displayAll();
		
		int[] backlogs_cr_us = userstory_details_cr.getBacklogs();
		int[] defined_cr_us  = userstory_details_cr.getDefined();
		int[] in_progress_cr_us = userstory_details_cr.getIn_progress();
		int[] completed_cr_us = userstory_details_cr.getCompleted();
		int[] accepted_cr_us = userstory_details_cr.getAccepted();
		int[] total_cr_us = userstory_details_cr.getTotal();
		
		int[] backlogs_cr_de = defect_details_cr.getBacklogs();
		int[] defined_cr_de  = defect_details_cr.getDefined();
		int[] in_progress_cr_de = defect_details_cr.getIn_progress();
		int[] completed_cr_de = defect_details_cr.getCompleted();
		int[] accepted_cr_de = defect_details_cr.getAccepted();
		int[] total_cr_de = defect_details_cr.getTotal();
		
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(backlogs_cr_us[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(defined_cr_us[i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(in_progress_cr_us[i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(completed_cr_us[i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(accepted_cr_us[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(total_cr_us[i]);       		 cell.setCellStyle(style3);break;
            	 		case 7:    cell.setCellValue(backlogs_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 8:    cell.setCellValue(defined_cr_de[i]);        cell.setCellStyle(style2);break;
            	 		case 9:  cell.setCellValue(in_progress_cr_de[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(completed_cr_de[i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(accepted_cr_de[i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_cr_de[i]);  	     cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }
        
        
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
		
	public static void write_CRwise_defect_details(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
        
        Defects_CR defect_details_cr = team_status.getDefects_cr();
		
        int[] submitted_cr= defect_details_cr.getSubmitted();
		int[] open_cr= defect_details_cr.getOpen();
		int[] fixed_cr=defect_details_cr.getFixed();
		int[] closed_cr=defect_details_cr.getClosed();
		int[] reopen_cr=defect_details_cr.getReopen();
		int[] ready_for_test_cr=defect_details_cr.getReady_for_test();
		int[] total_severity_cr=defect_details_cr.getTotal_severity();
		int[] critical_cr=defect_details_cr.getCritical();
		int[] major_cr=defect_details_cr.getMajor();
		int[] average_cr=defect_details_cr.getAverage();
		int[] minor_cr=defect_details_cr.getMinor();
		int[] total_state_cr=defect_details_cr.getTotal_state();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(submitted_cr[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(open_cr[i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(fixed_cr[i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(closed_cr[i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(reopen_cr[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(ready_for_test_cr[i]);       		 cell.setCellStyle(style2);break;
            	 		case 7:    cell.setCellValue(total_state_cr[i]);      cell.setCellStyle(style3);break;
            	 		case 8:    cell.setCellValue(critical_cr[i]);        cell.setCellStyle(style2);break;
            	 		case 9:  cell.setCellValue(major_cr[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(average_cr[i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(minor_cr[i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_severity_cr[i]);  	     cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
	
	public static void write_CRwise_testCase_details(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
        
		
        TestCases_CR testcase_details_cr=team_status.getTestcases_cr();
        
        int[] pass = testcase_details_cr.getPass();
	    int[] fail  = testcase_details_cr.getFail();
	    int[] in_progress =testcase_details_cr.getIn_progress();
	    int[] blocked  = testcase_details_cr.getBlocked();
	    int[] no_run = testcase_details_cr.getNo_run();
	    int[] total= testcase_details_cr.getTotal();		  
	    int[] method_count  =testcase_details_cr.getMethod_count();
	    int[] automated_count  = testcase_details_cr.getAutomated_count();
	    int[] executed  = testcase_details_cr.getExecuted();
	    
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(executed[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(pass[i]);    		 cell.setCellStyle(style2); break;
            	 		case 3:   cell.setCellValue(fail[i]);     		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(in_progress[i]); 		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(blocked[i]);   		 cell.setCellStyle(style2);break;
            	 		case 6:   cell.setCellValue(no_run[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 7:   cell.setCellValue(total[i]);       		 cell.setCellStyle(style3);break;
            	 		case 8:    cell.setCellValue(1);      cell.setCellStyle(style2);break;
            	 		case 9:    cell.setCellValue(1);        cell.setCellStyle(style2);break;
            	 		case 10:  cell.setCellValue(1); 	   cell.setCellStyle(style2);break;        	
            	 			default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
	
	public static void write_ApplicationWise_userstories_and_defect(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("ApplicationWise UserStories");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();
        
     		
		UserStories_Application userstory_details_app=team_status.getUserstories_application();
		Defects_Application defect_details_app = team_status.getDefects_application();
		
		
		int[] backlogs_app_us = userstory_details_app.getBacklogs();
		int[] defined_app_us  = userstory_details_app.getDefined();
		int[] in_progress_app_us = userstory_details_app.getIn_progress();
		int[] completed_app_us = userstory_details_app.getCompleted();
		int[] accepted_app_us = userstory_details_app.getAccepted();
		int[] total_app_us = userstory_details_app.getTotal();
		
		int[] backlogs_app_de = defect_details_app.getBacklogs();
		int[] defined_app_de  = defect_details_app.getDefined();
		int[] in_progress_app_de = defect_details_app.getIn_progress();
		int[] completed_app_de = defect_details_app.getCompleted();
		int[] accepted_app_de = defect_details_app.getAccepted();
		int[] total_app_de = defect_details_app.getTotal();
		
		
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(backlogs_app_us[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(defined_app_us[i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(in_progress_app_us[i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(completed_app_us[i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(accepted_app_us[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(total_app_us[i]);       		 cell.setCellStyle(style3);break;
            	 		case 7:   cell.setCellValue(backlogs_app_de[i]);      cell.setCellStyle(style2);break;
            	 		case 8:   cell.setCellValue(defined_app_de[i]);        cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(in_progress_app_de[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(completed_app_de[i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(accepted_app_de[i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_app_de[i]);  	     cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }
        
        
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
		
	public static void write_ApplicationWise_defect_details(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("ApplicationWise Defect Details");
        HSSFCellStyle style1=sheet.getRow(2).getCell(13).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(14).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(15).getCellStyle();        
        
        Defects_Application defect_details_app = team_status.getDefects_application();
				
        int[] submitted_app= defect_details_app.getSubmitted();
		int[] open_app= defect_details_app.getOpen();
		int[] fixed_app=defect_details_app.getFixed();
		int[] closed_app=defect_details_app.getClosed();
		int[] reopen_app=defect_details_app.getReopen();
		int[] ready_for_test_app=defect_details_app.getReady_for_test();
		int[] total_severity_app=defect_details_app.getTotal_severity();
		int[] critical_app=defect_details_app.getCritical();
		int[] major_app=defect_details_app.getMajor();
		int[] average_app=defect_details_app.getAverage();
		int[] minor_app=defect_details_app.getMinor();
		int[] total_state_app=defect_details_app.getTotal_state();
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(submitted_app[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(open_app[i]);     		 cell.setCellStyle(style2);break;
            	 		case 3:   cell.setCellValue(fixed_app[i]); 		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(closed_app[i]);   		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(reopen_app[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 6:   cell.setCellValue(ready_for_test_app[i]);       		 cell.setCellStyle(style2);break;
            	 		case 7:    cell.setCellValue(total_state_app[i]);      cell.setCellStyle(style3);break;
            	 		case 8:    cell.setCellValue(critical_app[i]);        cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(major_app[i]); 	   cell.setCellStyle(style2);break;        	
            	 		case 10:  cell.setCellValue(average_app[i]);     cell.setCellStyle(style2);break;
            	 		case 11:  cell.setCellValue(minor_app[i]);      cell.setCellStyle(style2);break;
            	 		case 12:  cell.setCellValue(total_severity_app[i]);  	     cell.setCellStyle(style3);break;
            	 		default:  break;        			
            	}            	
            }
        }	
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();	
	}
	
	public static void write_ApplicationWise_testCase_details(TeamStatus team_status,ArrayList<String> CR_list,String type_iteration_or_release) throws IOException
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
               
        
        sheet = workbook.getSheet("ApplicationWise TestCase Detail");
        HSSFCellStyle style1=sheet.getRow(2).getCell(11).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(12).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(13).getCellStyle();        
        
		
        TestCases_Application testcase_details_app=team_status.getTestcases_application();
        
        int[] pass = testcase_details_app.getPass();
	    int[] fail  = testcase_details_app.getFail();
	    int[] in_progress =testcase_details_app.getIn_progress();
	    int[] blocked  = testcase_details_app.getBlocked();
	    int[] no_run = testcase_details_app.getNo_run();
	    int[] total= testcase_details_app.getTotal();		  
	    int[] method_count  =testcase_details_app.getMethod_count();
	    int[] automated_count  = testcase_details_app.getAutomated_count();
	    int[] executed  = testcase_details_app.getExecuted();
	    
        
        //int currentRow=sheet.getLastRowNum()+1;       
        //row=sheet.createRow(currentRow);currentRow_ite
        //row=sheet.createRow(currentRow);
        
        for(int i=0;i<CR_list.size();i++)        	
        {
        	row=sheet.createRow(i+3);
        	for(int j=0;j<25;j++)
            {
            	cell=row.createCell(j);
            	switch(j)
            	{
            	 		case 0:   cell.setCellValue(CR_list.get(i)); 			 cell.setCellStyle(style1); break;
            	 		case 1:   cell.setCellValue(executed[i]);    		 cell.setCellStyle(style2); break;
            	 		case 2:   cell.setCellValue(pass[i]);    		 cell.setCellStyle(style2); break;
            	 		case 3:   cell.setCellValue(fail[i]);     		 cell.setCellStyle(style2);break;
            	 		case 4:   cell.setCellValue(in_progress[i]); 		 cell.setCellStyle(style2);break;
            	 		case 5:   cell.setCellValue(blocked[i]);   		 cell.setCellStyle(style2);break;
            	 		case 6:   cell.setCellValue(no_run[i]);   			 cell.setCellStyle(style2);break;        		
            	 		case 7:   cell.setCellValue(total[i]);       		 cell.setCellStyle(style3);break;
            	 		case 8:   cell.setCellValue(1);      cell.setCellStyle(style2);break;
            	 		case 9:   cell.setCellValue(1);        cell.setCellStyle(style2);break;
            	 		case 10:  cell.setCellValue(1); 	   cell.setCellStyle(style2);break;        	
            	 		default:  break;        			
            	}            	
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
		Excel_Write_Way2.currentRow_ite = currentRow_ite;
	}
	
	public static int getCurrentRow_rele() {
		return currentRow_rele;
	}

	public static void setCurrentRow_rele(int currentRow_rele) {
		Excel_Write_Way2.currentRow_rele = currentRow_rele;
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

//=========================================================================

/*
write2.write_US_Details_Testable_CT_CR(team_status, CR_list, "iteration");
write2.write_US_Details_Testable_CR(team_status, CR_list, "iteration");	
write2.write_DE_Details_Severity_CR(team_status, CR_list, "iteration");
write2.write_DE_Details_Date_CR(team_status, CR_list, "iteration");
write2.write_TC_Details_Automatable_CR(team_status, CR_list, "iteration");
write2.write_TC_Details_CR(team_status,CR_list , "iteration");		
write2.write_Iteration_Status_Dashboard_CR(team_status, CR_list,"iteration");
*/

/*write2.write_US_Details_Testable_CT(team_status.getUserStories(), "teamname", "iteration","userstory");
write2.write_US_Details_Testable(team_status.getUserstories(), "teamname", "iteration","userstory");
write2.write_DE_Details_Severity(team_status.getDefects(), "teamname", "iteration","userstory");
write2.write_DE_Details_Date(team_status.getDefects(), "teamname", "iteration","userstory");
write2.write_TC_Details_Automatable(team_status.getTestCases(), "teamname", "iteration" );
write2.write_TC_Details(team_status.getTestCases(), "teamname", "iteration");		
write2.write_Iteration_Status_Dashboard(team_status, "teamname");
*/		

/*
write2.write_US_Details_Testable_CT_APP(team_status, APP_list, "iteration");
write2.write_US_Details_Testable_APP(team_status, APP_list, "iteration");	
write2.write_DE_Details_Severity_APP(team_status, APP_list, "iteration");
write2.write_DE_Details_Date_APP(team_status, APP_list, "iteration");
write2.write_TC_Details_Automatable_APP(team_status, APP_list, "iteration");
write2.write_TC_Details_APP(team_status,APP_list , "iteration");		
write2.write_Iteration_Status_Dashboard_APP(team_status, APP_list,"iteration");
*/

        