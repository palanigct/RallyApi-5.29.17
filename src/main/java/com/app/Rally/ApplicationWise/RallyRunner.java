package com.app.Rally.ApplicationWise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.app.drawchart.DrawChart_Excel;
import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;

public class RallyRunner 
{
	public static RallyRunner obj=new RallyRunner();
	public static Get_Iteration_data ite_obj=new Get_Iteration_data();
	public static Other_Functions other_fun_obj=new Other_Functions();
	public static Get_Release_data rele_obj=new Get_Release_data();	
	public static DrawChart_Excel drawchart=new DrawChart_Excel();
	
	public static ArrayList<String> team_list=new ArrayList<String>();
	public static ArrayList<String> CR_list=new ArrayList<String>();
	
	public static void main(String arg[]) throws Throwable
	{
				
		System.out.println(" \n Started");
		
		
		other_fun_obj.copy_output_folder();          //copy the output folder to system
				
		other_fun_obj.copy_template_folder();	     //copy template folder to output folder	
		
		
		/*
		team_list=other_fun_obj.get_team_List();     //get the team list
		
		ite_obj.Exe_Iteration(team_list);            //execute iteration function for get the iterations data's 
		
		//rele_obj.Exe_Release(team_list);		     //execute releases function for get the releases data's 		
		
		drawchart.draw_chart_in_excel();             //draw the chart in inside the excel sheet 
		
		*/	
						
		
		team_list=other_fun_obj.get_team_List();           //get the team list
		
		CR_list=other_fun_obj.get_CR_List();               //get the CR list
		
		ite_obj.Exe_Iteration_CR(team_list, CR_list);  	   //execute iteration function for get the iterations data's with CR wise details
		
		drawchart.draw_chart_in_excel();                   //draw the chart in inside the excel sheet 
		
		
		other_fun_obj.copy_output_folder();                //copy the output folder to system 
				
		System.out.println("\ncompleted");		
	}	
	
}



/* reports write method calling location 
 
   Report                         -  Class                 -  Method
   --------------------------------------------------------------------------------------------------
   Dashboard                      -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint
   Testable UserStories Count     -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint
   Testable UserStories           -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint
   Automatable TestCases          -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint
   TestCase Details               -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint  
   Defect Details Based on Reopen -  Get_Iteration_data    -  get_Iteration_Status_details_for_team_and_sprint
   CRWise UserStories             -  Get_Iteration_data    -  Exe_Iteration_CR
   CRWise Defect Details          -  
   CRWise TestCase Details        -  
   UserStories                    -  Common_Functions     -  callRestApi
   Defects                        -  Common_Functions     -  callRestApi
   TestCases                      -  Common_Functions     -  getTestcase_details
   
  
 
 */


