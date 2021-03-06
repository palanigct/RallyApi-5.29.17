package com.app.Rally.CRWise;

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
import com.app.pojos.Defects_CR;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_CR;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_CR;
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
	
	
	public static TeamStatus callRestApi_CR(String name_release_or_sprint,String type_story_or_defect,String type_sprint_or_release,ArrayList<String> CR_list) throws IOException, URISyntaxException
	{
		TeamStatus team_status=new TeamStatus();
		Defect_Age defect_age=new Defect_Age();
		
		/*int backlogs=0;
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
		
		int[] backlogs_cr = new int [10];
		int[] defined_cr  = new int [10];
		int[] in_progress_cr = new int [10];
		int[] completed_cr = new int [10];
		int[] accepted_cr = new int [10];
		int[] total_cr = new int [10];
		
		int[] testable_field_count=new int [10];		
		int[] backlogs_testable = new int [10];
		int[] defined_testable  = new int [10];
		int[] in_progress_testable = new int [10];
		int[] completed_testable = new int [10];
		int[] accepted_testable = new int [10];
		int[] total_testable = new int [10];
		
		int[] submitted_cr= new int [10];
		int[] open_cr= new int [10];
		int[] fixed_cr=new int [10];
		int[] closed_cr=new int [10];
		int[] reopen_cr=new int [10];
		int[] ready_for_test_cr=new int [10];
		int[] total_severity_cr=new int [10];
		int[] critical_cr=new int [10];
		int[] major_cr=new int [10];
		int[] average_cr=new int [10];
		int[] minor_cr=new int [10];
		int[] total_state_cr=new int [10];	
		
		
		
		int[] pass_tc_cr		= new int[10];
		int[] fail_tc_cr  		=new int[10];
		int[] in_progress_tc_cr = new int[10];
		int[] blocked_tc_cr		= new int[10];
		int[] no_run_tc_cr		= new int[10];
		int[] total_tc_cr	    = new int[10];		
		int[] extra_cr		= new int[10];
		int[] automated_count_tc_cr =new int[10];
		int[] method_count_tc_cr =new int[10];
		int[] exe_tc_cr=new int[10];
				
		String state_temp="";
        String workproduct_name="";
        String formId="";
        String openedDate="Null";
        
        // String creationDate="";
        // String lastUpdateDate="";
        // String acceptedDate="";
        // String closedDate="";
        // String inProgressDate="";
        // String openedDate="";
        // String targetDate="";
        
        JsonObject releaseJson=null;
        JsonObject sprintJson=null;
        String releaseName="Null";
        String sprintName="Null";                                
        String severity  ="Null";               
        String defectState ="Null";
        String CRNumber="Null";
		
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
                   
                 //================================  CR  =======================================
                 
                 try{
              	   		JsonObject CR_number = (JsonObject) storyJsonObject.get("c_CR");              	               
              	   		JsonArray list =new JsonArray();
                        if(CR_number.get("_tagsNameArray").getAsJsonArray().size()!=0)
                        {                            	                  	   
                        	list=CR_number.get("_tagsNameArray").getAsJsonArray();                         	              	   
                        	JsonObject appobj=(JsonObject) list.get(0) ;                        	
                        	CRNumber=appobj.get("Name").getAsString();                    
                        	
                        	int index=0;
                        	boolean check=false;
                        	
                        	for(int i=0;i<CR_list.size();i++)          //check the CR number in list
                            { 
                        		if(StringUtils.containsIgnoreCase(CRNumber, CR_list.get(i)))
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
                                   
                                   try
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
                                   story.setCRNumber(CRNumber);                                   
                                   story.setOpenedDate(openedDate);
                                   story.setOpen_days(open_days);
                                   //story.setApplication(application_name);
                                   
                                   
                                   write.write_userstoryAndDefect(story, team_name, type_sprint_or_release, type_story_or_defect);
                               	
                                   if(state_temp.contains("Backlog")) 	  backlogs_cr[i]++;
                                   if(state_temp.contains("Defined")) 	  defined_cr[i]++;
                                   if(state_temp.contains("In-Progress")) in_progress_cr[i]++;
                                   if(state_temp.contains("Completed"))   completed_cr[i]++;
                                   if(state_temp.contains("Accepted")) 	  accepted_cr[i]++; 
                                              
                                   if(StringUtils.containsIgnoreCase(defectState, "Submitted"))      { submitted_cr[i]++;      total_state_cr[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Open")) 	         { open_cr[i]++;           total_state_cr[i]++;    }
                                   if(StringUtils.containsIgnoreCase(defectState, "Fixed"))          { fixed_cr[i]++;          total_state_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Ready for Test")) { ready_for_test_cr[i]++; total_state_cr[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Reopen"))         { reopen_cr[i]++;         total_state_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Closed")) 	     { closed_cr[i]++;         total_state_cr[i]++;}
                                             	 
                                   if(StringUtils.containsIgnoreCase(severity, "Average")) 	    { average_cr[i]++;  total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Critical"))     { critical_cr[i]++; total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Major"))        { major_cr[i]++;    total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Minor"))        { minor_cr[i]++;    total_severity_cr[i]++; }
                                   
                                   if(StringUtils.containsIgnoreCase(testable, "yes"))
                                   {
                                  	   if(state_temp.contains("Backlog")) 	backlogs_testable[i]++;
                                       if(state_temp.contains("Defined")) 	defined_testable[i]++;
                                       if(state_temp.contains("In-Progress")) in_progress_testable[i]++;
                                       if(state_temp.contains("Completed")) 	completed_testable[i]++;
                                       if(state_temp.contains("Accepted")) 	accepted_testable[i]++;  
                                       
                                       total_testable[i]++;
                                   }
                                   
                                   total_cr[i]++;
                        	}    
                        }
                        else
                        {
                        	 //System.out.println(" CR name list is an empty array");
                        }  
                 	}catch(Exception e)
                 		{              
                 			//System.out.println("CR field is not available");
                 		}
             }//end of for loop (result loop)   
            
             // =======================================  END CR ==========================================            
           
        }catch(Exception e)
        	{
        			
        	}
      
        
        if((type_story_or_defect.contains("testcase")))
        {
        	   String reqKey2="HierarchicalRequirement";
        	
        	
        	  try {
                  QueryRequest storyRequest = new QueryRequest(reqKey2);          
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
                            
                  //result looping is start here
                  //result looping is start here
                  System.out.println(" TC: total story or defect  response count : "+storyQueryResponse.getTotalResultCount());
                  
                  int j=0;
                  
                  for ( JsonElement jsonElement : storyQueryResponse.getResults())
                  {
                	     j++;
                      JsonObject storyJsonObject = jsonElement.getAsJsonObject();
                          
                      int index_cr=100;
                      boolean check=false;
                      //================================ getting CR  =======================================
                      
                      try{
                   	   		JsonObject CR_number = (JsonObject) storyJsonObject.get("c_CR");              	               
                   	   		JsonArray list =new JsonArray();
                             if(CR_number.get("_tagsNameArray").getAsJsonArray().size()!=0)
                             {                            	                  	   
                             	list=CR_number.get("_tagsNameArray").getAsJsonArray();                         	              	   
                             	JsonObject crobj=(JsonObject) list.get(0) ;                        	
                             	CRNumber=crobj.get("Name").getAsString();
                             	check=true;
                              }
                             else
                             {
                             	//CRNumber=call getcr method //System.out.println(" CR name list is an empty array");
                             }  
                      	  }catch(Exception e) //for getting cr 
                      	  {              
                      		  	//CRNumber=call getcr method //System.out.println("CR field is not available");
                      	  }
                  
                    if(check!=false) 
                    {	
                    	System.out.println("inside check");
                        for(int i=0;i<CR_list.size();i++)          //check the CR number in list
                        { 
                   		   if(StringUtils.containsIgnoreCase(CRNumber, CR_list.get(i)))
                      	 	{                      			 
                   			  index_cr=i;  
                   			  workproduct_name=storyJsonObject.get("Name").getAsString();
                     	      formId=storyJsonObject.get("FormattedID").getAsString(); 
                     	      
                     	      System.out.println("workproduct_name : "+workproduct_name);
                     	      TestCases testcase_details=common_fun_obj.getTestcase_details(workproduct_name, "iteration");
                     	     //TestCases testcase_details=new TestCases();
                     	     
                     	      pass_tc_cr[index_cr]+=testcase_details.getPass();
                     	      fail_tc_cr[index_cr]+=testcase_details.getFail();
                     	      in_progress_tc_cr[index_cr]+=testcase_details.getIn_progress();
                     	      blocked_tc_cr[index_cr]+=testcase_details.getBlocked();
                     	      no_run_tc_cr[index_cr]+=testcase_details.getNo_run();
                     	      total_tc_cr	[index_cr]+=testcase_details.getTotal();            			
                     	      automated_count_tc_cr[index_cr]+=testcase_details.getAutomated_count(); 
                     	      method_count_tc_cr [index_cr]+=testcase_details.getMethod_count();
                     	      exe_tc_cr  [index_cr]+=testcase_details.getExecuted();
                   			  break;                                   
                   		    }                        	                  
                        }                   	                      
                     }
                  
                  }//end of for loop (result loop)   
                 
                  // =======================================  END CR TC ==========================================            
                
             }catch(Exception e)
             	{
             		System.out.println("not able to query the REST API service");	
             	}
                    	
        }// testcase end if 
        
        
     
    	 UserStories_CR userstory_details_cr=new UserStories_CR();
    	 Defects_CR defect_details_cr= new Defects_CR();
    	 TestCases_CR testcase_details_cr= new TestCases_CR();
    	
    	 userstory_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 userstory_details_cr.setAllTestable(backlogs_testable, defined_testable, in_progress_testable, completed_testable, accepted_testable, total_testable, testable_field_count);
    	 defect_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 defect_details_cr.setAllSeverity(critical_cr, major_cr, average_cr, minor_cr, total_severity_cr);
    	 defect_details_cr.setAllState(submitted_cr, open_cr, fixed_cr, closed_cr, reopen_cr, ready_for_test_cr, total_state_cr);
    	 defect_details_cr.setDefect_age(defect_age);
    	 testcase_details_cr.setAll(pass_tc_cr, fail_tc_cr, in_progress_tc_cr, blocked_tc_cr, no_run_tc_cr, exe_tc_cr, total_tc_cr);
    	 testcase_details_cr.setAutomated_count(automated_count_tc_cr);
    	 testcase_details_cr.setMethod_count(method_count_tc_cr);
    	 
    	 team_status.setDefects_cr(defect_details_cr);
    	 team_status.setUserstories_cr(userstory_details_cr);
    	 team_status.setTestcases_cr(testcase_details_cr);    	 
    	   	 
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
        
       /* System.out.println(" opened date : "+openedDate+" || "+dateString);
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
        QueryResponse testcaseResponse = restApi.query(testcaseRequest);
        total=testcaseResponse.getResults().size();
        
        System.out.println(" tc total : "+total);
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
        
        testcases.displayAll();        
        return testcases;
	}
	
	
	public static void getTestCases_CR(String name_release_or_sprint,String type_story_or_defect,String type_sprint_or_release,ArrayList<String> CR_list)
	{
		TeamStatus team_status=new TeamStatus();
		
		
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
		
		
		int[] backlogs_cr = new int [10];
		int[] defined_cr  = new int [10];
		int[] in_progress_cr = new int [10];
		int[] completed_cr = new int [10];
		int[] accepted_cr = new int [10];
		int[] total_cr = new int [10];
		
		int[] submitted_cr= new int [10];
		int[] open_cr= new int [10];
		int[] fixed_cr=new int [10];
		int[] closed_cr=new int [10];
		int[] reopen_cr=new int [10];
		int[] ready_for_test_cr=new int [10];
		int[] total_severity_cr=new int [10];
		int[] critical_cr=new int [10];
		int[] major_cr=new int [10];
		int[] average_cr=new int [10];
		int[] minor_cr=new int [10];
		int[] total_state_cr=new int [10];		
		
		int[] pass_tc_cr		= new int[10];
		int[] fail_tc_cr  		=new int[10];
		int[] in_progress_tc_cr = new int[10];
		int[] blocked_tc_cr		= new int[10];
		int[] no_run_tc_cr		= new int[10];
		int[] total_tc_cr	    = new int[10];		
		int[] extra_cr		= new int[10];
		int[] automated_count_tc_cr =new int[10];
		int[] method_count_tc_cr =new int[10];
		int[] exe_tc_cr=new int[10];
				
		String state_temp="";
        String name="";
        String formId="";
       
        //String creationDate="";
        // String lastUpdateDate="";
        // String acceptedDate="";
        // String closedDate="";
        // String inProgressDate="";
        // String openedDate="";
        // String targetDate="";
        
        JsonObject releaseJson=null;
        JsonObject sprintJson=null;
        String releaseName="Null";
        String sprintName="Null";                                
        String severity  ="Null";               
        String defectState ="Null";
        String CRNumber="Null";
		
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
             total_count=storyQueryResponse.getTotalResultCount();
           
             //result looping is start here
             for (int j=0; j<storyQueryResponse.getTotalResultCount();j++)
             {
                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
                 
                 //================================  CR  =======================================
                 
                 try{
              	   		JsonObject CR_number = (JsonObject) storyJsonObject.get("c_CR");              	               
              	   		JsonArray list =new JsonArray();
                        if(CR_number.get("_tagsNameArray").getAsJsonArray().size()!=0)
                        {                            	                  	   
                        	list=CR_number.get("_tagsNameArray").getAsJsonArray();                         	              	   
                        	JsonObject appobj=(JsonObject) list.get(0) ;                        	
                        	CRNumber=appobj.get("Name").getAsString();                    
                        	
                        	int index=0;
                        	boolean check=false;
                        	
                        	for(int i=0;i<CR_list.size();i++)          //check the CR number in list
                            { 
                        		if(StringUtils.containsIgnoreCase(CRNumber, CR_list.get(i)))
                           	 	{                      			 
                        			 index=i;  
                        			 check=true;
                                     break;                                   
                        		}                        	                  
                            }
                        	
                        	if(check==true)
                        	{
                        		 int i=index;
                        		
                        		 state_temp=storyJsonObject.get("ScheduleState").getAsString();
                      	         name=storyJsonObject.get("Name").getAsString();
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
                      	         
                      	           try //get severity field
                                   {
                                  	 if(!(storyJsonObject.get("Severity").toString().equals("null"))) 	
                                       {
                                  		 severity=storyJsonObject.get("Severity").getAsString();
                                  		 total_severity++;
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
                                  		 total_state++;
                                       }                                    	  
                                   }catch(Exception e)
                                   {
                                  	 defectState="Field Does not Exist";
                                   }
                                   
                                   //====================== set values and write to excel======
                                   story.setFormattedID(formId);
                                   story.setName(name);
                                   story.setStatus(state_temp);
                                   story.setSprintname(sprintName);
                                   story.setReleaseName(releaseName);
                                   story.setTestable(testable);
                                   story.setSeverity(severity);
                                   story.setState(defectState);
                                   story.setCRNumber(CRNumber);
                                   write.write_userstoryAndDefect(story, team_name, type_sprint_or_release, type_story_or_defect);
                               	
                                   if(state_temp.contains("Backlog")) 	  backlogs_cr[i]++;
                                   if(state_temp.contains("Defined")) 	  defined_cr[i]++;
                                   if(state_temp.contains("In-Progress")) in_progress_cr[i]++;
                                   if(state_temp.contains("Completed"))   completed_cr[i]++;
                                   if(state_temp.contains("Accepted")) 	  accepted_cr[i]++; 
                                              
                                   if(StringUtils.containsIgnoreCase(defectState, "Submitted"))      { submitted_cr[i]++;      total_state_cr[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Open")) 	         { open_cr[i]++;           total_state_cr[i]++;    }
                                   if(StringUtils.containsIgnoreCase(defectState, "Fixed"))          { fixed_cr[i]++;          total_state_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Ready for Test")) { ready_for_test_cr[i]++; total_state_cr[i]++; }
                                   if(StringUtils.equalsIgnoreCase(defectState, "Reopen"))         { reopen_cr[i]++;         total_state_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(defectState, "Closed")) 	     { closed_cr[i]++;         total_state_cr[i]++;}
                                             	 
                                   if(StringUtils.containsIgnoreCase(severity, "Average")) 	    { average_cr[i]++;  total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Critical"))     { critical_cr[i]++; total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Major"))        { major_cr[i]++;    total_severity_cr[i]++; }
                                   if(StringUtils.containsIgnoreCase(severity, "Minor"))        { minor_cr[i]++;    total_severity_cr[i]++; }
                                              
                                   total_cr[i]++;
                        	}    
                        }
                        else
                        {
                        	 //System.out.println(" CR name list is an empty array");
                        }  
                 	}catch(Exception e)
                 		{              
                 			//System.out.println("CR field is not available");
                 		}
             }//end of for loop (result loop)   
            
             // =======================================  END CR ==========================================            
           
        }catch(Exception e)
        	{
        			
        	}
        finally 
            {   
        		
            }
		
        
        if((type_story_or_defect.contains("testcase")))
        {
        	System.out.println("test case");
        }
        
        
     
    	 UserStories_CR userstory_details_cr=new UserStories_CR();
    	 Defects_CR defect_details_cr= new Defects_CR();
    	 TestCases_CR testcase_details_cr= new TestCases_CR();
    	
    	 userstory_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 defect_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 defect_details_cr.setAllSeverity(critical_cr, major_cr, average_cr, minor_cr, total_severity_cr);
    	 defect_details_cr.setAllState(submitted_cr, open_cr, fixed_cr, closed_cr, reopen_cr, ready_for_test_cr, total_state_cr);
    	 testcase_details_cr.setAll(pass_tc_cr, fail_tc_cr, in_progress_tc_cr, blocked_tc_cr, no_run_tc_cr, exe_tc_cr, total_tc_cr);
    	 testcase_details_cr.setAutomated_count(automated_count_tc_cr);
    	 testcase_details_cr.setMethod_count(method_count_tc_cr);
    	 
    	 team_status.setDefects_cr(defect_details_cr);
    	 team_status.setUserstories_cr(userstory_details_cr);
    	 team_status.setTestcases_cr(testcase_details_cr);    	 
    	   	 
    	 //return team_status;   
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
