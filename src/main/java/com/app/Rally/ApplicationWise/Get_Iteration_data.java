package com.app.Rally.ApplicationWise;

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
import com.app.pojos.Defects_Application;
import com.app.pojos.Defects_CR;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_Application;
import com.app.pojos.TestCases_CR;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_Application;
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
		
	public void Exe_Iteration_Application(ArrayList<String> Application_list) throws Throwable
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
	        
	        team_status=ite_obj.get_Iteration_Status_details_for_team_and_sprint_Application(sprint_name, Application_list);
	    }
		finally 
		{
			if (restApi != null) { 	   restApi.close();   } 			
		} 		
		
		
				
		write2.write_US_Details_Testable_CT_APP(team_status, Application_list, "iteration");
		write2.write_US_Details_Testable_APP(team_status, Application_list, "iteration");	
		write2.write_DE_Details_Severity_APP(team_status, Application_list, "iteration");
		write2.write_DE_Details_Date_APP(team_status, Application_list, "iteration");
		write2.write_TC_Details_Automatable_APP(team_status, Application_list, "iteration");
		write2.write_TC_Details_APP(team_status,Application_list , "iteration");		
		write2.write_Iteration_Status_Dashboard_APP(team_status, Application_list,"iteration");

		//team_status.getUserstories_application().displayAll();
		//team_status.getDefects_application().displayAll();
		//team_status.getTestcases_application().displayAll();
		
		//write.write_ApplicationWise_userstories_and_defect(team_status, Application_list, "iteration");
		//write.write_ApplicationWise_defect_details(team_status, Application_list, "iteration");
		//write.write_ApplicationWise_testCase_details(team_status, Application_list, "iteration");					
	}
	
	public static TeamStatus get_Iteration_Status_details_for_team_and_sprint_Application(String sprint_name,ArrayList<String> Application_list) throws Exception
	{
		String type_story_or_defect="";
		
		UserStories_Application userstory_details_app=new UserStories_Application();
		Defects_Application defect_details_app=new Defects_Application();
		TestCases_Application testcase_details_app=new TestCases_Application();
		
		/*
		// get userstory values
		
		type_story_or_defect="userstory";	    
	    TeamStatus temp=common_fun_obj.callRestApi_Application(sprint_name, type_story_or_defect, "iteration", Application_list);
	    userstory_details_app=temp.getUserstories_application();   
	   	    */	    	    
	    // get defect values
		
	    type_story_or_defect="defects";		
	    TeamStatus temp=common_fun_obj.callRestApi_Application(sprint_name, type_story_or_defect, "iteration", Application_list);			
		defect_details_app=temp.getDefects_application();	
		
		defect_details_app.getDefect_age().displayAllSeverityday5();
		
		/*//get testcase values
		
		type_story_or_defect="testcase";
		temp=common_fun_obj.callRestApi_Application(sprint_name, type_story_or_defect, "iteration", Application_list);			
		testcase_details_app=temp.getTestcases_application();
		*/
		
		TeamStatus team_status=new TeamStatus();		 
		team_status.setAllApplication(userstory_details_app, defect_details_app, testcase_details_app);	
			
		return team_status;		
	}
	
}	


