package com.app.Rally.CRWise;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;
import com.app.excelwrite.Excel_Write_Way2;
import com.app.pojos.Defects;
import com.app.pojos.Defects_CR;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_CR;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_CR;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;



public class Get_Iteration_data 
{
	private static final String Static = null;
	public static Readfile read = new Readfile();
	public static Excel_Write write=new Excel_Write();
	public static Excel_Write_Way2 write2=new Excel_Write_Way2();
	public static List<HashMap<String,String>> mydata_App_Login = read.data("src/main/resources/INPUT.xls", "APP_LOGIN");
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/main/resources/INPUT.xls", "APP_DATA");
	public static Get_Iteration_data ite_obj=new Get_Iteration_data();
	public static TeamStatus teams_status_total=new TeamStatus();	
	public static RallyRestApi restApi = null;
	public static Common_Functions common_fun_obj=new Common_Functions();
		
	public void Exe_Iteration_CR(ArrayList<String> CR_list) throws Throwable
	{

		String host 			  =  mydata_App_Login.get(0).get("Host");
        String username 		  =  mydata_App_Login.get(0).get("Username");
        String password 		  =  mydata_App_Login.get(0).get("Password");
        String release_name   	  =  mydata_App_Data.get(0).get("Release Number");
        String sprint_number 	  =  mydata_App_Data.get(0).get("Sprint Number");
        String applicationName    = "Find Userstories and Defects by Iterations and Team";  		
		String sprint_name	 	  = "Sprint "+sprint_number;

		TeamStatus team_status=new TeamStatus();
       
		System.out.println("\n Sprint Number : "+sprint_number);
				
		try
		{
			restApi = new RallyRestApi(new URI(host),username,password);
	        restApi.setApplicationName(applicationName); 
	        common_fun_obj.setRestApi(restApi);
	        
	        team_status=ite_obj.get_Iteration_Status_details_for_team_and_sprint_CR(sprint_name, CR_list);
	    }
		finally 
		{
			if (restApi != null) { 	   restApi.close();   } 			
		} 		
		

		write2.write_Iteration_Status_Dashboard_CR(team_status, CR_list,"iteration");
		write2.write_DE_Details_Severity_CR(team_status, CR_list, "iteration");
		write2.write_TC_Details_CR(team_status,CR_list , "iteration");
		
		
		//write.write_CRwise_userstories_and_defect(team_status, CR_list, "iteration");
		//write.write_CRwise_defect_details(team_status, CR_list, "iteration");
		//write.write_CRwise_testCase_details(team_status, CR_list, "iteration");					
	}
	
	public static TeamStatus get_Iteration_Status_details_for_team_and_sprint_CR(String sprint_name,ArrayList<String> CR_list) throws Exception
	{
		String type_story_or_defect="";
		
		UserStories_CR userstory_details_cr=new UserStories_CR();
		Defects_CR defect_details_cr=new Defects_CR();
		TestCases_CR testcase_details_cr=new TestCases_CR();
		
		
		// get userstory values
		
		type_story_or_defect="userstory";	    
	    TeamStatus temp=common_fun_obj.callRestApi_CR(sprint_name, type_story_or_defect, "iteration", CR_list);
	    userstory_details_cr=temp.getUserstories_cr();	    	    
	   	    	    	    
	    // get defect values
		
	    type_story_or_defect="defect";		
	    temp=common_fun_obj.callRestApi_CR(sprint_name, type_story_or_defect, "iteration", CR_list);			
		defect_details_cr=temp.getDefects_cr();		
		
		// get testcase values
		
	    //type_story_or_defect="testcase";		
	    //TeamStatus temp=common_fun_obj.callRestApi_CR(sprint_name, type_story_or_defect, "iteration", CR_list);			
	    // testcase_details_cr=temp.getTestcases_cr();		
		
		
		
		TeamStatus team_status=new TeamStatus();		
		 
		team_status.setUserstories_cr(userstory_details_cr);
		team_status.setDefects_cr(defect_details_cr);
		team_status.setTestcases_cr(testcase_details_cr);	
			
		return team_status;		
	}

	
}	


