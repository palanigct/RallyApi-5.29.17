package com.app.Rally.ApplicationWise;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

//import com.app.drawchart.DrawChart;
import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;
import com.app.pojos.Defect_Age;
import com.app.pojos.Defects;
import com.app.pojos.Defects_Application;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_Application;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_Application;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;

public class Common_Functions 
{

	public static Excel_Write write=new Excel_Write();
	public static Other_Functions other_functions=new Other_Functions();
	public static Common_Functions common_fun_obj=new Common_Functions();
	public static RallyRestApi restApi = null;
	public static String program_name="CR5981";
	
	
	public static TeamStatus add_total(TeamStatus teams_status,TeamStatus teams_status_total)
	{
		int total_us=0;
		int Backlog_us=0;
		int Defined_us=0;
		int In_Progress_us=0;
		int Completed_us=0;
		int Accepted_us=0;
		
		int total_de=0;
		int Backlog_de=0;
		int Defined_de=0;
		int In_Progress_de=0;
		int Completed_de=0;
		int Accepted_de=0;
		
		int pass_tc=0;
		int fail_tc=0;
		int in_progress_tc=0;
		int blocked_tc=0;
		int no_run_tc=0;
		int total_tc=0;
		
		int per_exe_tc=0;
		int per_pass_tc=0;
		int per_fail_tc=0;
		    
		    Backlog_us     = teams_status.getUserStories().getBacklogs()+teams_status_total.getUserStories().getBacklogs();  
		    Defined_us     = teams_status.getUserStories().getDefined()+teams_status_total.getUserStories().getDefined();  
		    In_Progress_us = teams_status.getUserStories().getIn_progress()+teams_status_total.getUserStories().getIn_progress();    
		    Completed_us   = teams_status.getUserStories().getCompleted()+teams_status_total.getUserStories().getCompleted(); 			  
		    Accepted_us    = teams_status.getUserStories().getAccepted()+teams_status_total.getUserStories().getAccepted();  			  
		    total_us       = teams_status.getUserStories().getTotal()+teams_status_total.getUserStories().getTotal();  
			  
		    Backlog_de     = teams_status.getDefects().getBacklogs()+teams_status_total.getDefects().getBacklogs();  
		    Defined_de     = teams_status.getDefects().getDefined()+teams_status_total.getDefects().getDefined();  
		    In_Progress_de = teams_status.getDefects().getIn_progress()+teams_status_total.getDefects().getIn_progress();    
		    Completed_de   = teams_status.getDefects().getCompleted()+teams_status_total.getDefects().getCompleted(); 			  
		    Accepted_de    = teams_status.getDefects().getAccepted()+teams_status_total.getDefects().getAccepted();  			  
		    total_de       = teams_status.getDefects().getTotal()+teams_status_total.getDefects().getTotal();  
			  	
		    pass_tc        = teams_status.getTestCases().getPass()+teams_status_total.getTestCases().getPass();
		    fail_tc        = teams_status.getTestCases().getFail()+teams_status_total.getTestCases().getFail();
		    in_progress_tc = teams_status.getTestCases().getIn_progress()+teams_status_total.getTestCases().getIn_progress();
		    blocked_tc     = teams_status.getTestCases().getBlocked()+teams_status_total.getTestCases().getBlocked();
		    no_run_tc      = teams_status.getTestCases().getNo_run()+teams_status_total.getTestCases().getNo_run();
		    total_tc       = teams_status.getTestCases().getTotal()+teams_status_total.getTestCases().getTotal();
		    	
		    
		    UserStories userstory_total=new UserStories();
			Defects defect_total= new Defects();
			TestCases testcase_total= new TestCases();
			
			userstory_total.setAll(Backlog_us, Defined_us, In_Progress_us, Completed_us, Accepted_us, total_us);
			defect_total.setAll(Backlog_de, Defined_de, In_Progress_de, Completed_de, Accepted_de, total_de);
			testcase_total.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc,per_exe_tc,per_pass_tc,per_fail_tc);
			
			
			teams_status_total.setUserStories(userstory_total);
			teams_status_total.setDefects(defect_total);
			teams_status_total.setTestCases(testcase_total);
		    
		
		return teams_status_total;
		
	}
	
	public static void draw_pie_chart(String name_release_or_sprint,TeamStatus teams_status_total) throws Throwable
	{
//		DrawChart drawchart=new DrawChart("");
//		drawchart.draw_Piechart(teams_status_total,name_release_or_sprint);
	}
	
	public static TestCases calculate_percent_testcase(TestCases testcase)
	{
		
		int total_tc=testcase.getTotal();
		int pass_tc=testcase.getPass();
		int fail_tc=testcase.getFail();
		int in_progress_tc=testcase.getIn_progress();
		int blocked_tc=testcase.getBlocked();
		int no_run_tc=testcase.getNo_run();
		int per_exe=0;
		int per_pass=0;
		int per_fail=0;
		
		int executed_tc=pass_tc+fail_tc;
		int non_executed_tc=total_tc-executed_tc;
		
		double temp=((double)executed_tc/(double)total_tc)*100;
		per_exe=(int)temp;
		
		temp=((double)pass_tc/(double)executed_tc)*100;
		per_pass=(int)temp;
		
		temp=((double)fail_tc/(double)executed_tc)*100;
		per_fail=(int)temp;	
		
		//testcase.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc, per_exe, per_pass, per_fail);
		
		testcase.setPercentage_execute(per_exe);
		testcase.setPercentage_pass(per_pass);
		testcase.setPercentage_fail(per_fail);
		
		return testcase;
	}
	
	public static TeamStatus callRestApi_Application(String name_release_or_sprint,String type_story_or_defect,String type_sprint_or_release,ArrayList<String> Application_list) throws IOException, URISyntaxException
	{
		TeamStatus team_status=new TeamStatus();
		Defect_Age defect_age=new Defect_Age();
		
		/*
		int backlogs=0;
		int defined=0;
		int in_progress=0;
		int completed=0;
		int accepted=0;
		int total_count=0;	
		
		int total_count_testable=0;		
		int backlogs_testable=0;
		int defined_testable=0;
		int in_progress_testable=0;
		int completed_testable=0;
		int accepted_testable=0;
		
		int submitted=0;
		int open=0;
		int fixed=0;
		int closed=0;
		int reopen=0;
		int ready_for_test=0;
		int total_severity=0;
		int critical=0;
		int major=0;
		int average=0;
		int minor=0;
		int total_state=0;
		
		int pass_tc=0;
		int fail_tc=0;
		int in_progress_tc=0;
		int blocked_tc=0;
		int no_run_tc=0;
		int total_tc=0;
		int method_count_tc=0;
		int automated_count_tc=0;
		int exe_tc=0;
		
		*/
		int[] backlogs_app = new int [10];
		int[] defined_app  = new int [10];
		int[] in_progress_app = new int [10];
		int[] completed_app = new int [10];
		int[] accepted_app = new int [10];
		int[] total_app = new int [10];
		
		int[] testable_field_count=new int [10];		
		int[] backlogs_testable = new int [10];
		int[] defined_testable  = new int [10];
		int[] in_progress_testable = new int [10];
		int[] completed_testable = new int [10];
		int[] accepted_testable = new int [10];
		int[] total_testable = new int [10];
		
		int[] submitted_app= new int [10];
		int[] open_app= new int [10];
		int[] fixed_app=new int [10];
		int[] closed_app=new int [10];
		int[] reopen_app=new int [10];
		int[] ready_for_test_app=new int [10];
		int[] total_severity_app=new int [10];
		int[] critical_app=new int [10];
		int[] major_app=new int [10];
		int[] average_app=new int [10];
		int[] minor_app=new int [10];
		int[] total_state_app=new int [10];		
		
		int[] pass_tc_app		= new int[10];
		int[] fail_tc_app  		=new int[10];
		int[] in_progress_tc_app = new int[10];
		int[] blocked_tc_app		= new int[10];
		int[] no_run_tc_app		= new int[10];
		int[] total_tc_app	    = new int[10];		
		int[] extra_app		= new int[10];
		int[] automated_count_tc_app =new int[10];
		int[] method_count_tc_app =new int[10];
		int[] exe_tc_app=new int[10];
				
		String state_temp="Null";
        String workproduct_name="Null";
        String formId="Null";
        String openedDate="Null";
       
        //String creationDate="";
        // String lastUpdateDate="";
        // String acceptedDate="";
        // String closedDate="";
        // String inProgressDate="";        // 
        // String targetDate="";
        
        JsonObject releaseJson=null;
        JsonObject sprintJson=null;
        String releaseName="Null";
        String sprintName="Null";                                
        String severity  ="Null";               
        String defectState ="Null";
        String application_name="Null";
		
        UserStories story=new UserStories();
        String team_name="Null";					
  	    String iterationName	= name_release_or_sprint;
  	    String reqKey="";
        String testable="";     
        
        //==========================================================================================
        
        if(type_story_or_defect.contains("testcase"))       reqKey="TestCase";  
        else if(type_story_or_defect.contains("userstory")) reqKey="HierarchicalRequirement";
        else  if(type_story_or_defect.contains("defect"))   reqKey="Defects";
        else reqKey="Defects";
        
        if(!(type_story_or_defect.contains("testcase"))) 
        try {
             QueryRequest storyRequest = new QueryRequest(reqKey);          
             storyRequest.setPageSize(Integer.MAX_VALUE);
             storyRequest.setLimit(Integer.MAX_VALUE);
             storyRequest.setScopedDown(false);
             storyRequest.setScopedUp(false);
             if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Release"))
             {
            	 storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",name_release_or_sprint));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
             }
             else if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Iteration"))
             {
            	 storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",name_release_or_sprint));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
             }
                    
             QueryResponse storyQueryResponse = restApi.query(storyRequest);            
             System.out.println(" total story or defect  response count : "+storyQueryResponse.getTotalResultCount());
                          
             //result looping is start here
             int j=0;
             
             for ( JsonElement jsonElement : storyQueryResponse.getResults())
             {
           	     j++;
                 JsonObject storyJsonObject = jsonElement.getAsJsonObject();
                 
                 
                 //================================  APP  =======================================
                 
                 try{
              	   		JsonObject c_Application = (JsonObject) storyJsonObject.get("c_Application"); 
              	   		
              	   		JsonArray list =new JsonArray();
                        if(c_Application.get("_tagsNameArray").getAsJsonArray().size()!=0)
                        {                            	                  	   
                        	list=c_Application.get("_tagsNameArray").getAsJsonArray();                         	              	   
                        	JsonObject appobj=(JsonObject) list.get(0) ;                        	
                        	application_name=appobj.get("Name").getAsString();                    
                        	                        	
                        	int index=0;
                        	boolean check=false;
                        	
                        	for(int i=0;i<Application_list.size();i++)          //check the Application name in list
                            { 
                        		if(StringUtils.containsIgnoreCase(application_name, Application_list.get(i)))
                           	 	{                      			 
                        			 index=i;  
                        			 check=true;
                                     break;                                   
                        		}                        	                  
                            }
                        	
                        	if(check==true)
                        	{
                        		 int i=index;
                        		 int open_days=0;
                        		 
                        		 state_temp=storyJsonObject.get("ScheduleState").getAsString();
                        		 workproduct_name=storyJsonObject.get("Name").getAsString();
                      	         formId=storyJsonObject.get("FormattedID").getAsString(); 
                      	       
                      	         // creationDate=storyJsonObject.get("CreationDate").getAsString();
                      	         // lastUpdateDate=storyJsonObject.get("LastUpdateDate").getAsString();
                      	         // acceptedDate=storyJsonObject.get("AcceptedDate").getAsString();
                      	         // closedDate=storyJsonObject.get("ClosedDate").getAsString();
                      	         // inProgressDate=storyJsonObject.get("InProgressDate").getAsString();
                      	         // openedDate=storyJsonObject.get("OpenedDate").getAsString();
                      	         // targetDate=storyJsonObject.get("TargetDate").getAsString();
                      	         
                      	          
                      	           if(!(storyJsonObject.get("Project").toString().equals("null"))) 	
                                   {
                                  	 releaseJson=(JsonObject) storyJsonObject.get("Project");                	
                                  	 team_name=releaseJson.get("_refObjectName").getAsString();
                                   } 
                      	         
                      	           if(!(storyJsonObject.get("Release").toString().equals("null"))) 	
                                   {
                                  	 releaseJson=(JsonObject) storyJsonObject.get("Release");                	
                                  	 releaseName=releaseJson.get("_refObjectName").getAsString();
                                   }                
                                   
                                   if(!(storyJsonObject.get("Iteration").toString().equals("null"))) 	
                                   {
                                  	 sprintJson=(JsonObject) storyJsonObject.get("Iteration");                	 
                                  	 sprintName=sprintJson.get("_refObjectName").getAsString();
                                   }
                      	         
                                   try //get testable field
                                   {
                                  	 if(storyJsonObject.get("c_Testable").toString().equals("null"))
                                  		 testable="Null";
                                  	 else                	 
                                  		 { testable=storyJsonObject.get("c_Testable").getAsString(); testable_field_count[i]++;	}
                                   }
                                   catch(Exception e)
                                   {
                                  	 	testable="Field Does not Exist";
                                   }
                                   
                                   
                                   
                      	           try //get severity field
                                   {
                                  	 if(!(storyJsonObject.get("Severity").toString().equals("null"))) 	
                                       {
                                  		 severity=storyJsonObject.get("Severity").getAsString();
                                  		 //total_severity++;
                                       }                                    	  
                                   }catch(Exception e)
                                   {
                                  	 severity="Field Does not Exist";
                                   }
                                   
                                   try //get state field
                                   {
                                  	 if(!(storyJsonObject.get("State").toString().equals("null"))) 	
                                       {
                                  		 defectState=storyJsonObject.get("State").getAsString();
                                  		 //total_state++;
                                       }                                    	  
                                   }catch(Exception e)
                                   {
                                  	 defectState="Field Does not Exist";
                                   }
                                   
                                   if(StringUtils.equalsIgnoreCase(defectState, "Open"))
                                   {
                                	   try //get opened date field
                                       {
                                      	 if(!(storyJsonObject.get("OpenedDate").toString().equals("null"))) 	
                                           {
                                      		openedDate=storyJsonObject.get("OpenedDate").getAsString();                                      		
                                      		defect_age=common_fun_obj.getDefectAge(openedDate,i,defect_age,severity);
                                      		open_days=defect_age.getOpen_days();
                                           }                                    	  
                                       }catch(Exception e)
                                       {
                                    	   openedDate="Field Does not Exist";
                                       }
                                    }
                                   
                                   
                                   //====================== set values and write to excel======
                                   story.setFormattedID(formId);
                                   story.setName(workproduct_name);
                                   story.setStatus(state_temp);
                                   story.setSprintname(sprintName);
                                   story.setReleaseName(releaseName);
                                   story.setTestable(testable);
                                   story.setSeverity(severity);
                                   story.setState(defectState);
                                   story.setApplication(application_name);
                                   story.setOpenedDate(openedDate);
                                   story.setOpen_days(open_days);
                                   //story.setCRNumber(CRNumber);
                                   
                                   write.write_userstoryAndDefect(story, team_name, type_sprint_or_release, type_story_or_defect);
                               	
                                   if(state_temp.contains("Backlog")) 	  backlogs_app[i]++;
                                   if(state_temp.contains("Defined")) 	  defined_app[i]++;
                                   if(state_temp.contains("In-Progress")) in_progress_app[i]++;
                                   if(state_temp.contains("Completed"))   completed_app[i]++;
                                   if(state_temp.contains("Accepted")) 	  accepted_app[i]++; 
                                              
                                   if(StringUtils.containsIgnoreCase(defectState, "Submitted"))      { submitted_app[i]++;      total_state_app[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Open")) 	         { open_app[i]++;           total_state_app[i]++;    }
                                   if(StringUtils.containsIgnoreCase(defectState, "Fixed"))          { fixed_app[i]++;          total_state_app[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Ready for Test")) { ready_for_test_app[i]++; total_state_app[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Reopen"))         { reopen_app[i]++;         total_state_app[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Closed")) 	     { closed_app[i]++;         total_state_app[i]++;}
                                             	 
                                   if(StringUtils.containsIgnoreCase(severity, "Average")) 	    { average_app[i]++;  total_severity_app[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Critical"))     { critical_app[i]++; total_severity_app[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Major"))        { major_app[i]++;    total_severity_app[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Minor"))        { minor_app[i]++;    total_severity_app[i]++; }
                                          
                                   if(StringUtils.containsIgnoreCase(testable, "yes"))
                                   {
                                  	   if(state_temp.contains("Backlog")) 	backlogs_testable[i]++;
                                       if(state_temp.contains("Defined")) 	defined_testable[i]++;
                                       if(state_temp.contains("In-Progress")) in_progress_testable[i]++;
                                       if(state_temp.contains("Completed")) 	completed_testable[i]++;
                                       if(state_temp.contains("Accepted")) 	accepted_testable[i]++;  
                                       
                                       total_testable[i]++;
                                   }
                                   
                                   
                                   total_app[i]++;
                        	}    
                        }
                        else
                        {
                        	 //System.out.println(" CR name list is an empty array ");
                        }  
                 	}catch(Exception e)
                 		{              
                 			//System.out.println("CR field is not available "+e);
                 		}
             }//end of for loop (result loop)   
            
             // =======================================  END CR ==========================================            
           
        }catch(Exception e)
        	{
        			//System.out.println(e);
        	}
        
        
        if((type_story_or_defect.contains("testcase")))
        {        	
        	String type_us_or_def="userstory";       	
        	TestCases_Application tc_app_us=common_fun_obj.get_TC_details_US_or_DE(name_release_or_sprint, type_us_or_def, type_sprint_or_release, Application_list);
        	type_us_or_def="Defect";
        	TestCases_Application tc_app_def=common_fun_obj.get_TC_details_US_or_DE(name_release_or_sprint, type_us_or_def, type_sprint_or_release, Application_list);
        	
        	for(int i=0;i<pass_tc_app.length;i++)
        	{
        	  pass_tc_app [i]=tc_app_us.getPass()[i]+tc_app_def.getPass()[i];
			  fail_tc_app [i] =tc_app_us.getFail()[i]+tc_app_def.getFail()[i];
			  in_progress_tc_app[i]=tc_app_us.getIn_progress()[i]+tc_app_def.getIn_progress()[i];
			  blocked_tc_app[i]=tc_app_us.getBlocked()[i]+tc_app_def.getBlocked()[i];
			  no_run_tc_app[i]=tc_app_us.getNo_run()[i]+tc_app_def.getNo_run()[i];
			  total_tc_app	[i]=tc_app_us.getTotal()[i]+tc_app_def.getTotal()[i];            			
			  automated_count_tc_app[i]=tc_app_us.getAutomated_count()[i]+tc_app_def.getAutomated_count()[i]; 
			  method_count_tc_app[i] =tc_app_us.getMethod_count()[i]+tc_app_def.getMethod_count()[i];
			  exe_tc_app [i]=tc_app_us.getExecuted()[i]+tc_app_def.getExecuted()[i];
        	}
        }        
        
       
    	 UserStories_Application userstory_details_app=new UserStories_Application();
    	 Defects_Application defect_details_app= new Defects_Application();
    	 TestCases_Application testcase_details_app= new TestCases_Application();
    	
    	 userstory_details_app.setAll(backlogs_app, defined_app, in_progress_app, completed_app, accepted_app, total_app);
    	 userstory_details_app.setAllTestable(backlogs_testable, defined_testable, in_progress_testable, completed_testable, accepted_testable, total_testable, testable_field_count);
    	 defect_details_app.setAll(backlogs_app, defined_app, in_progress_app, completed_app, accepted_app, total_app);
    	 defect_details_app.setAllSeverity(critical_app, major_app, average_app, minor_app, total_severity_app);
    	 defect_details_app.setAllState(submitted_app, open_app, fixed_app, closed_app, reopen_app, ready_for_test_app, total_state_app);
    	 defect_details_app.setDefect_age(defect_age);
    	 testcase_details_app.setAll(pass_tc_app, fail_tc_app, in_progress_tc_app, blocked_tc_app, no_run_tc_app, exe_tc_app, total_tc_app);
    	 testcase_details_app.setAutomated_count(automated_count_tc_app);
    	 testcase_details_app.setMethod_count(method_count_tc_app);
    	 
    	 team_status.setAllApplication(userstory_details_app, defect_details_app, testcase_details_app);  
    	 
    	 //testcase_details_app.displayAll();
    	 //System.out.println(testcase_details_app.getAutomated_count());
    	 
    	 //defect_details_app.displayAll();
    	 //defect_details_app.displayAllSeverity();
    	 //defect_details_app.displayAllState();
    	  	 
    	   	 
    	 ///team_status.getUserstories_application().displayAll();
 		 //team_status.getDefects_application().displayAll();
    	 return team_status; 
	}

	
	public static Defect_Age getDefectAge(String openedDateString,int index,Defect_Age defect_age,String severity)
	{
		
		//=========================================defect agging======================
		
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
				
			//===================================================================================
		
		
		Date systemDate=new Date();
		Date openedDate=new Date();
		int days=0;
		
		String datearray[]=openedDateString.split("T");				
		String dateString = datearray[0];
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		   		    		
		try {
				openedDate = df.parse(dateString);
				String newDateString = df.format(openedDate);
				//System.out.println(newDateString+" "+openedDate);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			

        long diff = systemDate.getTime() - openedDate.getTime();
        days= (int) (diff / 1000 / 60 / 60 / 24);
       /* 
        System.out.println(" opened date : "+openedDate+" || "+dateString);
        System.out.println(" system date : "+systemDate);
        System.out.println (" Days: " + days+"\n================================\n");
		
      */
        
                
        
        if(days==1)
        {        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critical_day1[index]=critical_day1[index]+1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  major_day1[index]=major_day1[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  average_day1[index]=average_day1[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  minor_day1[index]=minor_day1[index]+1;  }
            
        	total_severity_day1[index]=total_severity_day1[index]+1;        			
        }
        
        if(days==2)
        {
        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critical_day2[index]=critical_day2[index]+1;    }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  major_day2[index]=major_day2[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  average_day2[index]=average_day2[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  minor_day2[index]=minor_day2[index]+1;  }
                    	
        	total_severity_day1[index]=total_severity_day1[index]+1;        			
        }
        
        if(days==3)
        {        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critical_day3[index]=critical_day3[index]+1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  major_day3[index]=major_day3[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  average_day3[index]=average_day3[index]+1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  minor_day3[index]=minor_day3[index]+1;  }
            
        	total_severity_day1[index]=total_severity_day1[index]+1;        			
        }
        
        if(days==4||days==5)
        {
        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {   critical_day3_5[index]=critical_day3_5[index]+1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {   major_day3_5[index]=major_day3_5[index]+1; }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {   average_day3_5[index]=average_day3_5[index]+1; }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {   minor_day3_5[index]=minor_day3_5[index]+1; }
        	        	
        	total_severity_day1[index]=total_severity_day1[index]+1;        			
        }
        
        if(days>5)
        {        
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {   critical_day5[index]=critical_day5[index]+1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {   major_day5[index]=major_day5[index]+1; }
        	if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {   average_day5[index]=average_day5[index]+1; }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {   minor_day5[index]=minor_day5[index]+1; }
        	
        	total_severity_day5[index]=total_severity_day5[index]+1;        			
        }
		
        defect_age.setOpen_days(days);
        
        defect_age.setAllSeverityday1(critical_day1, major_day1, average_day1, minor_day1, total_severity_day1);
        defect_age.setAllSeverityday2(critical_day2, major_day2, average_day2, minor_day2, total_severity_day2);
        defect_age.setAllSeverityday3(critical_day3, major_day3, average_day3, minor_day3, total_severity_day3);
        defect_age.setAllSeverityday3_5(critical_day3_5, major_day3_5, average_day3_5, minor_day3_5, total_severity_day3_5);
        defect_age.setAllSeverityday5(critical_day5, major_day5, average_day5, minor_day5, total_severity_day5);
        
        return 	defect_age;	
		
				
	}
	
	public static TestCases_Application get_TC_details_US_or_DE(String name_release_or_sprint,String type_story_or_defect,String type_sprint_or_release,ArrayList<String> Application_list)
	{
		TestCases_Application tc_app=new TestCases_Application();
		String application_name="";
		String workproduct_name=" ";
		String formId="";
		String reqKey="";
		 	
		int[] pass_tc		= new int[10];
		int[] fail_tc  		=new int[10];
		int[] in_progress_tc = new int[10];
		int[] blocked_tc		= new int[10];
		int[] no_run_tc		= new int[10];
		int[] total_tc	    = new int[10];		
		int[] extra		= new int[10];
		int[] automated_count_tc =new int[10];
		int[] method_count_tc =new int[10];
		int[] exe_tc=new int[10];
		
		
		if(type_story_or_defect.contains("userstory")) reqKey="HierarchicalRequirement";
        else  if(type_story_or_defect.contains("defect"))   reqKey="Defects";
        else reqKey="Defects";
		
  	
  	    try {
            QueryRequest storyRequest = new QueryRequest(reqKey);          
            //storyRequest.setPageSize(Integer.MAX_VALUE);
            storyRequest.setLimit(Integer.MAX_VALUE);                  
            if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Release"))
            {
           	 storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",name_release_or_sprint));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
            }
            else if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Iteration"))
            {
           	 storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",name_release_or_sprint));//.and(new QueryFilter("ScheduleState", "=", "In-Progress") .and(new QueryFilter("Project.Name", "=", "NerdHerd"))
            }
                   
            QueryResponse storyQueryResponse = restApi.query(storyRequest);
                      
            //result looping is start here
            System.out.println(" TC: total story or defect  response count : "+storyQueryResponse.getTotalResultCount());
            
            int j=0;
            
            for ( JsonElement jsonElement : storyQueryResponse.getResults())
            {
          	  j++;
          	  //System.out.println(j+" userstory response loop inside  : ");
          	  
                JsonObject storyJsonObject = jsonElement.getAsJsonObject();
                
                int index_app=100;
                boolean check=false;
                //================================ getting app  =======================================
                
                try{
             	   		JsonObject CR_number = (JsonObject) storyJsonObject.get("c_Application");              	               
             	   		JsonArray list =new JsonArray();
                       if(CR_number.get("_tagsNameArray").getAsJsonArray().size()!=0)
                       {                            	                  	   
                       	list=CR_number.get("_tagsNameArray").getAsJsonArray();                         	              	   
                       	JsonObject appobj=(JsonObject) list.get(0) ;                        	
                       	application_name=appobj.get("Name").getAsString();  
                       	check=true;
                        }
                       else
                       {
                       	//application_name=call getapplication_name method //System.out.println(" CR name list is an empty array");
                       }  
                	  }catch(Exception e) //for getting cr 
                	  {              
                		  	//application_name=call getapplication_name method //System.out.println("CR field is not available");
                	  }
            
              if(check!=false) 
              {	
              	//System.out.println(j+" inside app  check=true (app was matched)");
                  for(int i=0;i<Application_list.size();i++)          //check the CR number in list
                  { 
             		   if(StringUtils.containsIgnoreCase(application_name, Application_list.get(i)))
                	 	{                      			 
             			  index_app=i;  
             			  workproduct_name=storyJsonObject.get("Name").getAsString();
             			  formId=storyJsonObject.get("FormattedID").getAsString(); 
               	      
             			  //System.out.println(" workproduct_name : "+workproduct_name);
             			  //TestCases testcase_details=new TestCases();
             			  TestCases testcase_details=common_fun_obj.getTestcase_details(workproduct_name, "iteration");
             			 
               	     
             			  pass_tc[index_app]+=testcase_details.getPass();
             			  fail_tc[index_app]+=testcase_details.getFail();
             			  in_progress_tc[index_app]+=testcase_details.getIn_progress();
             			  blocked_tc[index_app]+=testcase_details.getBlocked();
             			  no_run_tc[index_app]+=testcase_details.getNo_run();
             			  total_tc	[index_app]+=testcase_details.getTotal();            			
             			  automated_count_tc[index_app]+=testcase_details.getAutomated_count(); 
             			  method_count_tc [index_app]+=testcase_details.getMethod_count();
             			  exe_tc  [index_app]+=testcase_details.getExecuted();
             			  break;                                   
             		    }                        	                  
                  }                   	                      
               }
            
            }//end of for loop (result loop)      // =======================================  END APP TC ==========================================            
          
       }catch(Exception e)
       	{
       		System.out.println("not able to query the REST API service");	
       	}
		
  	  tc_app.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, exe_tc, total_tc);
  	  tc_app.setAutomated_count(automated_count_tc);
  	  tc_app.setMethod_count(method_count_tc);
	  return tc_app;
	}
	
	
	public static TestCases getTestcase_details(String workProduct_name,String type_sprint_or_release ) throws IOException
	{
		int pass		= 0;
		int fail  		= 0;
		int in_progress = 0;
		int blocked		= 0;
		int no_run		= 0;
		int total	    = 0;		
		int extra		= 0;
		int automated_count =0;
		int method_count =0;
		
		String status      = "Null";
		String formattedId = "Null";		
		String team_Name   = "Null";
		String method      = "Null";
		String automated   = "Null";
		
		
		QueryRequest testcaseRequest = new QueryRequest("TestCases"); 
		testcaseRequest.setPageSize(Integer.MAX_VALUE);
		testcaseRequest.setLimit(Integer.MAX_VALUE);
		testcaseRequest.setScopedDown(false);
		testcaseRequest.setScopedUp(false);
        testcaseRequest.setQueryFilter(new QueryFilter("WorkProduct.Name", "=",workProduct_name  ));  //new QueryFilter("Iteration.Name", "=", iterationName).and
        QueryResponse testcaseResponse=null;
        
        try
        {
              testcaseResponse = restApi.query(testcaseRequest);
        }catch(Exception e)
        {
        	   System.out.println(" not able to query --inside the get testcase");
        }
        
        total=testcaseResponse.getResults().size();
        
        //System.out.println("   TC total : "+total);
        
        for (int i=0; i<testcaseResponse.getResults().size();i++)
        {
            JsonObject testcaseJsonObject = testcaseResponse.getResults().get(i).getAsJsonObject();
            formattedId = testcaseJsonObject.get("FormattedID").getAsString();
            
            if(!(testcaseJsonObject.get("LastVerdict").toString().equals("null"))) 	
            {
            	 status  = testcaseJsonObject.get("LastVerdict").getAsString();
            }            
                      
            if(!(testcaseJsonObject.get("Project").toString().equals("null"))) 	
            {
            	JsonObject projectJson = (JsonObject) testcaseJsonObject.get("Project");                	
           	    team_Name = projectJson.get("_refObjectName").getAsString();
            }            
           
            if(!(testcaseJsonObject.get("Method").toString().equals("null"))) 	
            {
            	method = testcaseJsonObject.get("Method").getAsString();
            	method_count++;
            }  
            
            try
            {
            	 if(!(testcaseJsonObject.get("c_CanbeAutomated").toString().equals("null"))) 	
                 {
            		 automated = testcaseJsonObject.get("c_CanbeAutomated").getAsString();
            		 if(StringUtils.containsIgnoreCase(automated, "yes"))
            			 automated_count++;
                 }
            }
            catch(Exception e)
            {
            	automated="CanbeAutomated Field Not Exist";
            }
            
            
            TestCases TC = new TestCases();
            TC.setFormattedId(formattedId);
            TC.setStatus(status);
            TC.setWorkProduct_name(workProduct_name);
            TC.setTeam_name(team_Name);
            TC.setMethod(method);
            TC.setAutomated(automated);
            
            //System.out.println("     TC : "+formattedId+" : "+status);
            write.write_testcase(TC, type_sprint_or_release);
            
            if(StringUtils.containsIgnoreCase(status, "Pass")) 	{	  pass++; }
            else if(StringUtils.containsIgnoreCase(status, "Fail")) 	{	  fail++; }	
            else if(StringUtils.containsIgnoreCase(status, "In Progress")){ in_progress++; }
            else  if(StringUtils.containsIgnoreCase(status, "Blocked")) {		  blocked++;  }
            else if(StringUtils.containsIgnoreCase(status, "No Run"))  {	  no_run++;     }            
            else if(StringUtils.containsIgnoreCase(status, "Null")) { extra++; }
            else { extra++; }
            
         }
        
        TestCases testcases = new TestCases();
        testcases.setAll(pass, fail, in_progress, blocked, no_run, total, 0 , 0 , 0); 
        testcases.setAutomated_count(automated_count);
        testcases.setMethod_count(method_count);
        
        //testcases.displayAll();        
        return testcases;
	}
	
	
	public static RallyRestApi getRestApi() {
		return restApi;
	}

	public static void setRestApi(RallyRestApi restApi) {
		Common_Functions.restApi = restApi;
	}


}


	
    //================================================= not in  use =====  testing purpose only ================================	
	
	
	//=============================== for program wise (CR) =============================
   /* if(name.contains(program_name))
    {
   	 write.write_userstoryAndDefect(story, team_name, "Release", type);
   	 TestCases TC=rele_obj.getTestcase_details(name);
   	 
   	    pass_tc  		+= TC.getPass();
        fail_tc  		+= TC.getFail();
        in_progress_tc += TC.getIn_progress();
        blocked_tc  	+= TC.getBlocked();
        no_run_tc  	+= TC.getNo_run();
        total_tc   	+= TC.getTotal();
    }*/
                     
   //================================== for program wise (CR) =============================== 
	

/*
	public static void main(String arg[]) throws Exception
	{
		Get_Iteration_data obj=new Get_Iteration_data();
		obj.get_Iteration_Status_details_for_team_and_sprint("SDWAN Kenobi", "Sprint 229");
	}
	
	public static TeamStatus callRestApi_dummy(String team_name,String sprint_name,String type)
	{
		TeamStatus temp=new TeamStatus();
		
		UserStories userstory_details=new UserStories();
   	 	Defects defect_details=new Defects();
   	 	TestCases testcase_details=new TestCases();
   	 
   	 	userstory_details.setAll(1, 2, 3, 4, 5, 6);
	 	defect_details.setAll(6, 5, 4, 3, 2, 1);
	 	testcase_details.setAll(7, 8, 9, 10, 11, 12, 0, 0, 0);
   	 	
   	 	temp.setAll(userstory_details, defect_details, testcase_details);
   	 
		return temp;
	}
	
	
*/
