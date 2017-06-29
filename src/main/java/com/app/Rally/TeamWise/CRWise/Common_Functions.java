package com.app.Rally.TeamWise.CRWise;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

//import com.app.drawchart.DrawChart;
import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;
import com.app.excelwrite.Excel_Write_Way2;
import com.app.pojos.Defect_Age;
import com.app.pojos.Defects;
import com.app.pojos.Defects_CR;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.TestCases_CR;
import com.app.pojos.UserStories;
import com.app.pojos.UserStories_CR;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;

public class Common_Functions 
{

	public static Excel_Write write=new Excel_Write();
	public static Excel_Write_Way2 write2=new Excel_Write_Way2();
	public static Other_Functions other_functions=new Other_Functions();
	public static Common_Functions common_fun_obj=new Common_Functions();
	public static RallyRestApi restApi = null;
	public static final ArrayList<String> CR_list=other_functions.get_CR_List();	
	
		
	public static TeamStatus callRestApi(String team_name,String name_release_or_sprint,String type_story_or_defect,String type_sprint_or_release) throws IOException, URISyntaxException
	{
		TeamStatus team_status=new TeamStatus();
		Defect_Age defect_age=new Defect_Age();
		
		
		int total_count=0;		
		int backlogs=0;
		int defined=0;
		int in_progress=0;
		int completed=0;
		int accepted=0;
		
		
		
		int total_count_testable=0;		
		int backlogs_testable=0;
		int defined_testable=0;
		int in_progress_testable=0;
		int completed_testable=0;
		int accepted_testable=0;
		
		int total_tc=0;
		int pass_tc=0;
		int fail_tc=0;
		int in_progress_tc=0;
		int blocked_tc=0;
		int no_run_tc=0;
		int method_count_tc=0;
		int automated_count_tc=0;
		
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
		
		
		int testable_field_count=0;		
		
		int[] available_CRindex =new int [100];		
		Arrays.fill(available_CRindex, 101);
		
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
		
		int[] testable_field_count_cr=new int [10];		
		int[] backlogs_testable_cr = new int [10];
		int[] defined_testable_cr  = new int [10];
		int[] in_progress_testable_cr = new int [10];
		int[] completed_testable_cr = new int [10];
		int[] accepted_testable_cr = new int [10];
		int[] total_testable_cr = new int [10];
		
		
  	    String iterationName	= name_release_or_sprint;
  	    String reqKey="";
        String testable="";  
        String openedDate="Null";
        
        if(type_story_or_defect.contains("userstory")) reqKey="HierarchicalRequirement";
        else reqKey="Defects";

        try {
             QueryRequest storyRequest = new QueryRequest(reqKey);          
             storyRequest.setPageSize(10);
             storyRequest.setLimit(1000);
             storyRequest.setScopedDown(false);
             storyRequest.setScopedUp(false);
             if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Release"))
             {
            	 storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",name_release_or_sprint).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
             }
             else if(StringUtils.containsIgnoreCase(type_sprint_or_release, "Iteration"))
             {
            	 storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",name_release_or_sprint).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
             }
                    
             QueryResponse storyQueryResponse = restApi.query(storyRequest);
             total_count=storyQueryResponse.getTotalResultCount();
           
             int j=0;
             
             for ( JsonElement jsonElement : storyQueryResponse.getResults())
             {
           	     j++;
                 JsonObject storyJsonObject = jsonElement.getAsJsonObject();
                                  
                 int totaltc=storyJsonObject.get("TestCaseCount").getAsInt();
                 int passtc=storyJsonObject.get("PassingTestCaseCount").getAsInt();
                 String testCaseStatus=storyJsonObject.get("TestCaseStatus").getAsString(); 
                              
                 String state_temp=storyJsonObject.get("ScheduleState").getAsString();
                 String name=storyJsonObject.get("Name").getAsString();
                 String formId=storyJsonObject.get("FormattedID").getAsString();                 
                 
                
                //String creationDate=storyJsonObject.get("CreationDate").getAsString();
                // String lastUpdateDate=storyJsonObject.get("LastUpdateDate").getAsString();
                // String acceptedDate=storyJsonObject.get("AcceptedDate").getAsString();
                // String closedDate=storyJsonObject.get("ClosedDate").getAsString();
                // String inProgressDate=storyJsonObject.get("InProgressDate").getAsString();
                // String openedDate=storyJsonObject.get("OpenedDate").getAsString();
                // String targetDate=storyJsonObject.get("TargetDate").getAsString();
                 
                 JsonObject releaseJson=null;
                 JsonObject sprintJson=null;
                 String releaseName="Null";
                 String sprintName="Null";                                
                 String severity  ="Null";               
                 String defectState ="Null";
                 String CRNumber="Null";
                 int open_days=0;
                 
                 CRNumber=other_functions.getCRnumber(name);
                 
                 
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
                     
                 try
                 {
                	 if(!(storyJsonObject.get("Severity").toString().equals("null"))) 	
                     {
                		 severity=storyJsonObject.get("Severity").getAsString();
                		 total_severity++;
                     }
                	  
                 }
                 catch(Exception e)
                 {
                	 severity="Field Does not Exist";
                 }
                 
                 try
                 {
                	 if(!(storyJsonObject.get("State").toString().equals("null"))) 	
                     {
                		 defectState=storyJsonObject.get("State").getAsString();
                		 total_state++;
                     }
                	  
                 }
                 catch(Exception e)
                 {
                	 defectState="Field Does not Exist";
                 }
                 
                 try
                 {
                	 if(storyJsonObject.get("c_Testable").toString().equals("null"))
                		 testable="Null";
                	 else                	 
                		 { testable=storyJsonObject.get("c_Testable").getAsString(); testable_field_count++;			}
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
                    		defect_age=common_fun_obj.getDefectAge(openedDate,defect_age,severity);
                    		open_days=defect_age.getOpen_days();
                         }                                    	  
                     }catch(Exception e)
                     {
                  	   openedDate="Field Does not Exist";
                     }
                  }
                 
                 UserStories story=new UserStories();
                 story.setFormattedID(formId);
                 story.setName(name);
                 story.setStatus(state_temp);
                 story.setSprintname(sprintName);
                 story.setReleaseName(releaseName);
                 story.setTestable(testable);
                 story.setSeverity(severity);
                 story.setState(defectState);
                 story.setCRNumber(CRNumber);
                 story.setOpenedDate(openedDate);
                 story.setOpen_days(open_days);
                 
                 write.write_userstoryAndDefect(story, team_name, type_sprint_or_release, type_story_or_defect);           
                 
                 TestCases TC=common_fun_obj.getTestcase_details(name,type_sprint_or_release);
                 //TestCases TC=new TestCases();
                 
                 pass_tc  		+= TC.getPass();
                 fail_tc  		+= TC.getFail();
                 in_progress_tc += TC.getIn_progress();
                 blocked_tc  	+= TC.getBlocked();
                 no_run_tc  	+= TC.getNo_run();
                 total_tc   	+= TC.getTotal();
                 automated_count_tc += TC.getAutomated_count();
                 method_count_tc    += TC.getMethod_count();
                
                 // TargetDate  CreationDate  LastUpdateDate AcceptedDate ClosedDate InProgressDate OpenedDate
                 // Severity="Average" Critical Major Average Minor  
                 // State="Closed"  Submitted Open Fixed Ready for Test Reopen              	 	
                 
                            
                 
                 if(StringUtils.containsIgnoreCase(defectState, "Submitted")) submitted++; 
                 if(StringUtils.equalsIgnoreCase(defectState, "Open")) 	  open++;
                 if(StringUtils.containsIgnoreCase(defectState, "Fixed"))  fixed++;
                 if(StringUtils.containsIgnoreCase(defectState, "Ready for Test")) ready_for_test++;
                 if(StringUtils.equalsIgnoreCase(defectState, "Reopen"))  reopen++;
                 if(StringUtils.containsIgnoreCase(defectState, "Closed")) 	 closed++;
                	 
                 if(StringUtils.containsIgnoreCase(severity, "Average")) 	  average++;
                 if(StringUtils.containsIgnoreCase(severity, "Critical"))   critical++; 
                 if(StringUtils.containsIgnoreCase(severity, "Major"))  major++;
                 if(StringUtils.containsIgnoreCase(severity, "Minor")) minor++;
                	 
                 if(state_temp.contains("Backlog")) 	backlogs++;
                 if(state_temp.contains("Defined")) 	defined++;
                 if(state_temp.contains("In-Progress")) in_progress++;
                 if(state_temp.contains("Completed")) 	completed++;
                 if(state_temp.contains("Accepted")) 	accepted++;
                 
                 if(StringUtils.containsIgnoreCase(testable, "yes"))
                 {
                	 if(state_temp.contains("Backlog")) 	backlogs_testable++;
                     if(state_temp.contains("Defined")) 	defined_testable++;
                     if(state_temp.contains("In-Progress")) in_progress_testable++;
                     if(state_temp.contains("Completed")) 	completed_testable++;
                     if(state_temp.contains("Accepted")) 	accepted_testable++;  
                     
                     total_count_testable++;
                 }
                 
                 int index =0;
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
             		 available_CRindex[i]=1;
             		  
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
                       	   if(state_temp.contains("Backlog")) 	backlogs_testable_cr[i]++;
                            if(state_temp.contains("Defined")) 	defined_testable_cr[i]++;
                            if(state_temp.contains("In-Progress")) in_progress_testable_cr[i]++;
                            if(state_temp.contains("Completed")) 	completed_testable_cr[i]++;
                            if(state_temp.contains("Accepted")) 	accepted_testable_cr[i]++;  
                            
                            total_testable_cr[i]++;
                        }
                        
                      pass_tc_cr[i]+=TC.getPass();
               	      fail_tc_cr[i]+=TC.getFail();
               	      in_progress_tc_cr[i]+=TC.getIn_progress();
               	      blocked_tc_cr[i]+=TC.getBlocked();
               	      no_run_tc_cr[i]+=TC.getNo_run();
               	      total_tc_cr	[i]+=TC.getTotal();            			
               	      automated_count_tc_cr[i]+=TC.getAutomated_count(); 
               	      method_count_tc_cr [i]+=TC.getMethod_count();
               	      exe_tc_cr  [i]+=TC.getExecuted();
                       
               	      total_cr[i]++;
             	} 
                else
                {
                	 //System.out.println(" CR name list is an empty array");
                }  
            
                 
              }//end of for --loop for all stories	 
     
        }finally {    }
		
        
         String cr_list_string=common_fun_obj.getAvailable_CRlist(available_CRindex);
         
         System.out.println(cr_list_string);
        
    	 UserStories userstory_details=new UserStories();
    	 Defects defect_details=new Defects();
    	 TestCases testcase_details=new TestCases();
    	 
    	
    	 userstory_details.setAllTestable(backlogs_testable, defined_testable, in_progress_testable, completed_testable, accepted_testable, total_count_testable);
    	 userstory_details.setAll(backlogs, defined, in_progress, completed, accepted, total_count);
    	 defect_details.setAll(backlogs, defined, in_progress, completed, accepted, total_count);
    	 defect_details.setAllSeverity(critical,major, average, minor, total_severity);
    	 defect_details.setDefect_age(defect_age);
    	 defect_details.setAllState(submitted, open, fixed, closed, reopen, ready_for_test, total_state);
    	 testcase_details.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc,0,0,0);    	 
    	 testcase_details.setAutomated_count(automated_count_tc);
    	 testcase_details.setMethod_count(method_count_tc);    	 
    	 userstory_details.setTestableFieldCount(testable_field_count);
    	 
    	 //===================CR details==============================
    	 	
    	 UserStories_CR userstory_details_cr=new UserStories_CR(); 
    	 Defects_CR defect_details_cr= new Defects_CR();
    	 TestCases_CR testcase_details_cr= new TestCases_CR();
    	
    	    	 
    	 userstory_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 userstory_details_cr.setAllTestable(backlogs_testable_cr, defined_testable_cr, in_progress_testable_cr, completed_testable_cr, accepted_testable_cr, total_testable_cr, testable_field_count_cr);
    	 defect_details_cr.setAll(backlogs_cr, defined_cr, in_progress_cr, completed_cr, accepted_cr, total_cr);
    	 defect_details_cr.setAllSeverity(critical_cr, major_cr, average_cr, minor_cr, total_severity_cr);
    	 defect_details_cr.setAllState(submitted_cr, open_cr, fixed_cr, closed_cr, reopen_cr, ready_for_test_cr, total_state_cr);
    	 defect_details_cr.setDefect_age(defect_age);
    	 testcase_details_cr.setAll(pass_tc_cr, fail_tc_cr, in_progress_tc_cr, blocked_tc_cr, no_run_tc_cr, exe_tc_cr, total_tc_cr);
    	 testcase_details_cr.setAutomated_count(automated_count_tc_cr);
    	 testcase_details_cr.setMethod_count(method_count_tc_cr);
    	 
    	 
    	 //=============================================================
    	
    	 
    	    	 
    	 team_status.setAll(userstory_details, defect_details, testcase_details);    	 
    	 team_status.setAll_CR(userstory_details_cr, defect_details_cr, testcase_details_cr);
    	 team_status.setAvailable_CRindex(available_CRindex);
    	 return team_status;     
	
	}

	public static Defect_Age getDefectAge(String openedDateString,Defect_Age defect_age,String severity)
	{
		
		//=========================================defect agging======================
		
			 int critic_day1 = defect_age.getCritic_day1();
			 int maj_day1 = defect_age.getMaj_day1(); 
			 int ave_day1 = defect_age.getAve_day1();
			 int min_day1 = defect_age.getMin_day1();
			 int tot_severity_day1 = defect_age.getTot_severity_day1();
			
			 int critic_day2 = defect_age.getCritic_day2();
			 int maj_day2 = defect_age.getMaj_day2();
			 int ave_day2 = defect_age.getAve_day2();
			 int min_day2 = defect_age.getMin_day2();
			 int tot_severity_day2 = defect_age.getTot_severity_day2();
			
			 int critic_day3 = defect_age.getCritic_day3();
			 int maj_day3 = defect_age.getMaj_day3();
			 int ave_day3 = defect_age.getAve_day3();
			 int min_day3 = defect_age.getMin_day3();
			 int tot_severity_day3 = defect_age.getTot_severity_day3();
			
			 int critic_day3_5 = defect_age.getCritic_day3_5();
			 int maj_day3_5 = defect_age.getMaj_day3_5();
			 int ave_day3_5 = defect_age.getAve_day3_5();
			 int min_day3_5 = defect_age.getMin_day3_5();
			 int tot_severity_day3_5 = defect_age.getTot_severity_day3_5();
			
			 int critic_day5 = defect_age.getCritic_day5();
			 int maj_day5 = defect_age.getMaj_day5();
			 int ave_day5 = defect_age.getAve_day5();
			 int min_day5 = defect_age.getMin_day5();
			 int tot_severity_day5 = defect_age.getTot_severity_day5();
			
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
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critic_day1 =critic_day1 +1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  maj_day1 =maj_day1 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  ave_day1 =ave_day1 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  min_day1 =min_day1 +1;  }
            
        	tot_severity_day1 =tot_severity_day1 +1;        			
        }
        
        if(days==2)
        {
        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critic_day2 =critic_day2 +1;    }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  maj_day2 =maj_day2 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  ave_day2 =ave_day2 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  min_day2 =min_day2 +1;  }
                    	
        	tot_severity_day1 =tot_severity_day1 +1;        			
        }
        
        if(days==3)
        {        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {  critic_day3 =critic_day3 +1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {  maj_day3 =maj_day3 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {  ave_day3 =ave_day3 +1;  }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {  min_day3 =min_day3 +1;  }
            
        	tot_severity_day1 =tot_severity_day1 +1;        			
        }
        
        if(days==4||days==5)
        {
        	
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {   critic_day3_5 =critic_day3_5 +1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {   maj_day3_5 =maj_day3_5 +1; }
            if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {   ave_day3_5 =ave_day3_5 +1; }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {   min_day3_5 =min_day3_5 +1; }
        	        	
        	tot_severity_day1 =tot_severity_day1 +1;        			
        }
        
        if(days>5)
        {        
            if(StringUtils.containsIgnoreCase(severity, "Critical"))     {   critic_day5 =critic_day5 +1;   }
            if(StringUtils.containsIgnoreCase(severity, "Major"))        {   maj_day5 =maj_day5 +1; }
        	if(StringUtils.containsIgnoreCase(severity, "Average")) 	 {   ave_day5 =ave_day5 +1; }
            if(StringUtils.containsIgnoreCase(severity, "Minor"))        {   min_day5 =min_day5 +1; }
        	
        	tot_severity_day5 =tot_severity_day5+1;        			
        }
		
        defect_age.setOpen_days(days);
        
        defect_age.setAllSeverday1(critic_day1, maj_day1, ave_day1, min_day1, tot_severity_day1);
        defect_age.setAllSeverday2(critic_day2, maj_day2, ave_day2, min_day2, tot_severity_day2);
        defect_age.setAllSeverday3(critic_day3, maj_day3, ave_day3, min_day3, tot_severity_day3);
        defect_age.setAllSeverday3_5(critic_day3_5, maj_day3_5, ave_day3_5, min_day3_5, tot_severity_day3_5);
        defect_age.setAllSeverday5(critic_day5, maj_day5, ave_day5, min_day5, tot_severity_day5);
        
        return 	defect_age;	
		
				
	}
			
	public static String getCRName_using_workproduct(String name)
	{
		String crName="Null";
		String[] split=name.split(" ");
				
		for(int i=0;i<split.length;i++)
		{
			if(split[i].contains("CR"))
			{
				crName = split[i];				
				break;
			}
		}
		
		return crName;
	}
	
	public static TestCases getTestcase_details(String workProduct_name,String type_sprint_or_release) throws IOException
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
        testcaseRequest.setQueryFilter(new QueryFilter("WorkProduct.Name", "=",workProduct_name  ));  //new QueryFilter("Iteration.Name", "=", iterationName).and
        QueryResponse testcaseResponse = restApi.query(testcaseRequest);
        total=testcaseResponse.getResults().size();
        
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
        
        return testcases;
	}
	
	
	public static String getAvailable_CRlist(int [] available_index)
	{
		String crlist="";
		System.out.println(CR_list);
		for(int i=0;i<CR_list.size();i++)
		{
			if(available_index[i]==1)
			{
				crlist+=CR_list.get(available_index[i])+",";		
			}
		}
		
		return crlist;
	}
	
	public static TestCases addTwoTestCases(TestCases testcase1,TestCases testcase2)
	{
		TestCases temp =new TestCases();	
		
		temp.setPass(testcase1.getPass()+testcase2.getPass());
		temp.setFail(testcase1.getFail()+testcase2.getFail());
		temp.setIn_progress(testcase1.getIn_progress()+testcase2.getIn_progress());
		temp.setBlocked(testcase1.getBlocked()+testcase2.getBlocked());
		temp.setNo_run(testcase1.getNo_run()+testcase2.getNo_run());
		temp.setTotal(testcase1.getTotal()+testcase2.getTotal());	
		temp.setAutomated_count(testcase1.getAutomated_count()+testcase2.getAutomated_count());
		temp.setMethod_count(testcase1.getMethod_count()+testcase2.getMethod_count());
		
		return temp;
	}
	
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
