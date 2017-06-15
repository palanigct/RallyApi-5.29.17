package com.app.Rally.ApplicationWise;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.app.pojos.TestCases;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;


/*
<dependency>
	<groupId>org.json</groupId>
<artifactId>json</artifactId>
<version>20090211</version>
</dependency>
*/


public class test {
	

	public static String uuid="cc844b31-bc0f-4637-a8cc-2857ed438664";
	public static String usname="CR6176 SLSL Add address line two for qualification";
	

	
	public static void main(String[] args) throws IOException, URISyntaxException 
	{
			test tst=new test();
			
			System.out.println("delete this ");
			
			//test.onlyQuery();
			//test.delete_fun();
			test.maxgetvalues();
			/*System.out.println("*************************user story*******************************");
			//test.userstoryreq();
			System.out.println("*************************test case*******************************");
			//test.testcasereq("ffdhd");
			System.out.println("*************************Iteration*******************************");
			//test.iterationreq();
			System.out.println("*************************Release*******************************");
			test.releasereq();
			System.out.println("*************************test *******************************");
			//test.testreq();				
*/	}    
	
	public static void onlyQuery() throws IOException
	{
		    System.out.println(" inside only query");
	  	   // String reqKey2="HierarchicalRequirement";
	  	    String reqKey2="Defect";
	  	    String iterationName	= "Sprint 238";
			String releaseName      = "PI10 2017.04";
			String team_name        = "Apttus PS";
		    String scheduleState    = "Accepted";
			
			String host 			= "https://rally1.rallydev.com";
			String username 		= "palanisamy.subramani@centurylink.com";
			String password 		= "Lalith@93";        
			String applicationName  = "Find Defects by Iterations and Team";
			RallyRestApi restApi = null;

	       try
	       {
	    	   restApi = new RallyRestApi(new URI(host),username,password);
	       }catch(Exception e)
	       {
	    	   System.out.println(e);
	       }
	      
	       QueryRequest storyRequest = new QueryRequest(reqKey2);          
           storyRequest.setPageSize(Integer.MAX_VALUE);
           storyRequest.setLimit(Integer.MAX_VALUE);
          
           
           storyRequest.setQueryFilter((new QueryFilter("Application.Count", "=", "2")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       QueryResponse storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count c_testable yes : "+storyQueryResponse.getTotalResultCount());
           
           
          /* storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Accepted")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       QueryResponse storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count accepted : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Backlog")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count backlog : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "In-Progress")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count in-progress : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Completed")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count completed : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Defined")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count defined : "+storyQueryResponse.getTotalResultCount());
           */
           
           /*storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",releaseName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Accepted")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       QueryResponse storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count accepted : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",releaseName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Backlog")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count backlog : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",releaseName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "In-Progress")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count in-progress : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",releaseName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Completed")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count completed : "+storyQueryResponse.getTotalResultCount());
           
           storyRequest.setQueryFilter(new QueryFilter("Release.Name", "=",releaseName).and(new QueryFilter("Project.Name", "=", team_name)).and(new QueryFilter("ScheduleState", "=", "Defined")));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")           
	       storyQueryResponse = restApi.query(storyRequest);      
           System.out.println(" total response count defined : "+storyQueryResponse.getTotalResultCount());
           
           */
	       /*
	        
	        delete this 
exe Successful: true
US Size: 2
not able to get file
-c_Testable  --nulll---
 US===form id : "US60939" state : "Accepted"
 obj id : 55938035051 totalcount : 0 pass : 0 test status : "NONE"
str : _rallyAPIMajor="2"
str : _rallyAPIMinor="0"
str : _ref="https://rally1.rallydev.com/slm/webservice/v2.0/hierarchicalrequirement/55938035051"
str : _refObjectUUID="eb43dd5a-59c0-4432-845b-953dc2118561"
str : _objectVersion="81"
str : _refObjectName="[Continued] CR5367: Support for Large Quotes Scenarios"
str : CreationDate="2016-05-16T17:05:54.683Z"
str : _CreatedAt="May 16, 2016"
str : ObjectID=55938035051
str : ObjectUUID="eb43dd5a-59c0-4432-845b-953dc2118561"
str : VersionId="81"
str : Subscription={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/subscription/3204830344","_refObjectUUID":"b1d45877-7371-4bb3-b434-b42a9b02b674","_refObjectName":"Qwest Communications (Network Operations)","_type":"Subscription"}
str : Workspace={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/workspace/3204830354","_refObjectUUID":"69359dcb-4e28-434d-bf2f-fa06b8ae16db","_refObjectName":"CenturyLink Workspace","_type":"Workspace"}
str : Changesets={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Changesets","_type":"Changeset","Count":0}
str : Description="<div>9/27 (Ahmed) - Closing this as we have other specific LQ user stories and defects</div><div><br /></div><div>8/24 - Functionality completed, defects being addressed</div><div><br /></div>Support for Large Quote Scenarios -&nbsp;<div><br /></div><div>this User Story is to support Large Quotes (large renewals and other use cases with a high number of line items.) This includes performance related items such as Heap errors, CPU Time outs, too many SOQL;s, &nbsp;Apptus Product team owning this effort at this time. &nbsp;Will update as needed.</div><div><br /></div><div><div><br /></div><div>Apttus PS Primary SME&nbsp;for this&nbsp;US is Aravind Chelliah</div></div><div><br /></div><div>[Ariana] Moving this from 214 to 216 as it has not been accepted yet</div>"
str : Discussion={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Discussion","_type":"ConversationPost","Count":0}
str : DisplayColor="#21a2e0"
str : Expedite=false
str : FormattedID="US60939"
str : LastUpdateDate="2016-10-26T20:53:46.981Z"
str : LatestDiscussionAgeInMinutes=null
str : Milestones={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Milestones","_type":"Milestone","_tagsNameArray":[{"Name":"2016.08 Enterprise Deployment","_ref":"/milestone/53684941867","DisplayColor":"#848689","TargetDate":"2016-08-15T06:00:00.000Z"}],"Count":1}
str : Name="[Continued] CR5367: Support for Large Quotes Scenarios"
str : Notes=""
str : Owner={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/user/48292746369","_refObjectUUID":"a9928ca6-0003-4230-886c-5b8c4bf732fc","_refObjectName":"Shivam Aditya","_type":"User"}
str : Project={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/project/33944249560","_refObjectUUID":"a7ee937b-c139-4717-bf47-67506b052232","_refObjectName":"Apttus PS","_type":"Project"}
str : Ready=false
str : RevisionHistory={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/revisionhistory/55938035052","_refObjectUUID":"d80d2c34-800c-4917-b75a-f915ab560c62","_type":"RevisionHistory"}
str : Tags={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Tags","_type":"Tag","_tagsNameArray":[{"Name":"LargeQuote","_ref":"/tag/55784806447"}],"Count":1}
str : FlowState={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/flowstate/96471900936","_refObjectUUID":"49b81a39-c4ee-444a-e053-1403020adda5","_refObjectName":"Accepted","_type":"FlowState"}
str : LastBuild=null
str : LastRun=null
str : PassingTestCaseCount=0
str : ScheduleState="Accepted"
str : ScheduleStatePrefix="A"
str : TestCaseCount=0
str : Attachments={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Attachments","_type":"Attachment","Count":0}
str : Package=null
str : AcceptedDate="2016-09-27T16:40:22.856Z"
str : Blocked=false
str : BlockedReason=null
str : Blocker=null
str : Children={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Children","_type":"HierarchicalRequirement","Count":0}
str : DefectStatus="NONE"
str : Defects={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Defects","_type":"Defect","Count":0}
str : DirectChildrenCount=0
str : DragAndDropRank="P!'&rtb!'uj@keAB,ScA*h65u/Ta'|kv2[gO~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
str : HasParent=false
str : InProgressDate="2016-05-27T14:41:30.481Z"
str : Iteration={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/iteration/49778201898","_refObjectUUID":"c2ef4d70-7281-4f75-9b11-09480d2a40cc","_refObjectName":"Sprint 219","_type":"Iteration"}
str : Parent=null
str : PlanEstimate=20.0
str : Predecessors={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Predecessors","_type":"HierarchicalRequirement","Count":0}
str : Recycled=false
str : Release={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/release/49777195822","_refObjectUUID":"a6ee2a0e-c029-403e-84ec-73143049460c","_refObjectName":"PI8 2016.11","_type":"Release"}
str : Successors={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Successors","_type":"HierarchicalRequirement","Count":0}
str : TaskActualTotal=18.0
str : TaskEstimateTotal=103.0
str : TaskRemainingTotal=0.0
str : TaskStatus="COMPLETED"
str : Tasks={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/Tasks","_type":"Task","Count":7}
str : TestCaseStatus="NONE"
str : TestCases={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/TestCases","_type":"TestCase","Count":0}
str : c_Application={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/55938035051/c_Application","_type":"AllowedAttributeValue","_tagsNameArray":[],"Count":0}
str : c_AutomationReason=null
str : c_CaliberStake=null
str : c_CanBeAutomated=null
str : c_DeployedtoProduction=null
str : c_Developer=null
str : c_DueDate=null
str : Feature={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/portfolioitem/feature/60571273936","_refObjectUUID":"45710e8d-1fe1-4244-a451-159e78e6e765","_refObjectName":"CR5367 (Carryover) Critical GA Hosting Enhancements","_type":"PortfolioItem/Feature"}
str : c_KanbanState=null
str : c_NonDevelopment=null
str : c_PointofContact=null
str : PortfolioItem={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/portfolioitem/feature/60571273936","_refObjectUUID":"45710e8d-1fe1-4244-a451-159e78e6e765","_refObjectName":"CR5367 (Carryover) Critical GA Hosting Enhancements","_type":"PortfolioItem/Feature"}
str : c_PostDeploymentSteps=null
str : c_PreDeploymentSteps=null
str : c_ProductionDevOwner=null
str : c_RefinementState=null
str : c_ReportingNotice=null
str : c_SoftwareComponents=null
str : c_Stake=null
str : c_Testfield=null
str : c_Testable=null
str : c_TestableOLD=false
str : c_WBSCode=null
str : c_WorkinPhase=null
str : _type="HierarchicalRequirement"

	        
	        
	        
	        */
	       
	}

	public static  void maxgetvalues() throws IOException
	{
		

	  		System.out.println(" inside maxgetvalues case");
	  		
	  		String reqKey2			="HierarchicalRequirement";
	  		String iterationName	= "Sprint 238";
			String releaseName      = "PI10 2017.04";
			String team_name        = "NerdHerd";
		
			String host 			= "https://rally1.rallydev.com";
			String username 		= "palanisamy.subramani@centurylink.com";
			String password 		= "Lalith@93";        
			String applicationName  = "Find Defects by Iterations and Team";
			RallyRestApi restApi 	= null;

			try
			{
	    	   restApi = new RallyRestApi(new URI(host),username,password);
			}catch(Exception e)
			{
	    	   System.out.println(e);
			}      
		
		// NOTE : no pagination needed for defect or iteration, but required for hierarchicalrequirement or user
		
		QueryRequest queryRequest = new QueryRequest("hierarchicalrequirement");
		//queryRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)));
		queryRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName));		
		queryRequest.setLimit(Integer.MAX_VALUE);
		//queryRequest.setPageSize(2000);
		
		int responseTotalResultsCount = Integer.MAX_VALUE;
		int actualResultsProcessedSoFar = 0;
		
		QueryResponse queryResponse = restApi.query(queryRequest);
	    responseTotalResultsCount = queryResponse.getTotalResultCount();
	    System.out.println("total response actualResultsProcessedSoFar : "+responseTotalResultsCount);
	    int count=responseTotalResultsCount/2000;
	    int temp=0;
	    int i=0;
	    
	    while(i++<=count&&actualResultsProcessedSoFar<responseTotalResultsCount)
		{
	    	JsonArray results = queryResponse.getResults();
	    	System.out.println("while count " +i);
	    	
	    	for (JsonElement jsonElement : queryResponse.getResults())
	        {
	    		actualResultsProcessedSoFar++;	
	    		if(actualResultsProcessedSoFar>responseTotalResultsCount)
	    		{	System.out.println(" inside break "); break;}
	        }
	    	
	    	if(actualResultsProcessedSoFar<responseTotalResultsCount)
	    	{
	    		queryRequest.setStart(queryRequest.getStart()+2000);
	    		queryResponse = restApi.query(queryRequest);
	    		temp = queryResponse.getTotalResultCount();
	    		System.out.println("total response temp : "+temp);  
	    	}
		}
			
	    System.out.println("total response  actualResultsProcessedSoFar : "+actualResultsProcessedSoFar);
	    
	    
	    
		/*QueryResponse queryResponse = restApi.query(queryRequest);
				
		System.out.println(" response result : "+queryResponse.getTotalResultCount());
		System.out.println(" page size : "+queryRequest.getPageSize());
		System.out.println(" start : "+queryRequest.getStart());*/
			
		
		/*while ((queryRequest.getStart()) < responseTotalResultsCount && actualResultsProcessedSoFar < responseTotalResultsCount) 
		{		
			
			
			QueryResponse queryResponse = restApi.query(queryRequest);
		    responseTotalResultsCount = queryResponse.getTotalResultCount();// Set to correct value here
		    JsonArray results = queryResponse.getResults();

		    System.out.println(" inside while : "+ i++ +" responseTotalResultsCount : "+responseTotalResultsCount);
		    
		    if (results.size() == 0) 
		    {
		    	System.out.println(" inside if : i="+i);
		        return;
		    } 
		    else 
		    {
		    	for (JsonElement jsonElement : queryResponse.getResults())
		        {
		    		actualResultsProcessedSoFar++;		           
		            System.out.println("actualResultsProcessedSoFar ="+actualResultsProcessedSoFar);
		        }
		    	
		    	System.out.println("out side for ===============================================================");
		    }   		         
		    queryRequest.setStart(queryRequest.getStart() + queryRequest.getPageSize());
		} 
		*/
		
		
		
		
		
		
/*
        	  	System.out.println(" inside test case");
        	  	String reqKey2="HierarchicalRequirement";
        	  	String iterationName	= "Sprint 238";
      			String releaseName      = "PI10 2017.04";
      			String team_name        = "NerdHerd";
      		
      			String host 			= "https://rally1.rallydev.com";
      			String username 		= "palanisamy.subramani@centurylink.com";
      			String password 		= "Lalith@93";        
      			String applicationName  = "Find Defects by Iterations and Team";
      			RallyRestApi restApi = null;
    
      			try {
      				    restApi = new RallyRestApi(new URI(host),username,password);
      					QueryRequest storyRequest = new QueryRequest(reqKey2);          
      					storyRequest.setPageSize(Integer.MAX_VALUE);
      					storyRequest.setLimit(Integer.MAX_VALUE);
      					storyRequest.setScopedDown(false);
      					storyRequest.setScopedUp(false);
      					storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName));//.and(new QueryFilter("ScheduleState", "=", "In-Progress") 
      					QueryResponse storyQueryResponse = restApi.query(storyRequest);
                            
      					//result looping is start here
      					System.out.println(" total us response count : "+storyQueryResponse.getTotalResultCount());
      					
      					for (int j=0; j<storyQueryResponse.getTotalResultCount();j++)
      					{
      						System.out.println(j+" inside loop : ");                	  
      						JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
      						System.out.println("storyJsonObject :"+ storyJsonObject);
      					}                  
                  
      			}catch(Exception e)
      			{
      				System.out.println("not able to query the REST API service");
      				System.out.println(e);
      			}
                    	*/
	}
	
	public static void userstoryreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 238";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "NerdHerd";
		//String team_name        = "SDWAN Kenobi";
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Defects by Iterations and Team";
       
        int application_count=0;
        int cr_count=0;
        System.out.println(" test delete ");
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            //restApi.setApplicationName(applicationName); 
            int count_0=0;
            int count_1=0;
            int count_nill=0;
           // QueryRequest userstoryRequest = new QueryRequest("HierarchicalRequirement"); 
            QueryRequest userstoryRequest = new QueryRequest("Defects"); 
            //userstoryRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and
            //userstoryRequest.setQueryFilter(new QueryFilter("FormattedID", "=", "US168722"));  //new QueryFilter("Project.Name", "=", team_name).and
           //  userstoryRequest.setQueryFilter(new QueryFilter("c_Application.Count", "=", "0"));  //new QueryFilter("Project.Name", "=", team_name).and
             
            //c_Application={"_rallyAPIMajor":"2","_rallyAPIMinor":"0","_ref":"https://rally1.rallydev.com/slm/webservice/v2.0/HierarchicalRequirement/123479683440/c_Application","_type":"AllowedAttributeValue","_tagsNameArray":[{"Name":"SFAINT","_ref":"/allowedattributevalue/78432701188"}],"Count":1}
           // FormattedID = US168722  c_Application.Name =SFAINT
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
            userstoryRequest.setLimit(Integer.MAX_VALUE);
            userstoryRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName));

            QueryResponse userstoryResponse = restApi.query(userstoryRequest);
            System.out.println("exe Successful: " + userstoryResponse.wasSuccessful());
            System.out.println("US Size: " + userstoryResponse.getTotalResultCount());
                       
            
            for (int i=0; i<userstoryResponse.getTotalResultCount()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = userstoryResponse.getResults().get(i).getAsJsonObject();
                 
                Object fmid=userstoryJsonObject.get("FormattedID");   // "FormattedID","ScheduleState"
                Object stat=userstoryJsonObject.get("ScheduleState");
                Object obid=userstoryJsonObject.get("ObjectID");
                Object tccount=userstoryJsonObject.get("TestCaseCount");
                Object passTc=userstoryJsonObject.get("PassingTestCaseCount");
                Object testCaseStatus=userstoryJsonObject.get("TestCaseStatus"); 
                JsonObject testcasejson = (JsonObject) userstoryJsonObject.get("TestCases");   
                JsonObject workspacejson = (JsonObject) userstoryJsonObject.get("Workspace");    
                Object uuids=userstoryJsonObject.get("UUID"); 
                Object name=userstoryJsonObject.get("Name"); 
                String str1=userstoryJsonObject.get("Name").getAsString();
                Object workref=workspacejson.get("_ref");
               
                String work_ref=workref.toString();
                String objid=obid.toString();
                
               // JsonObject release=(JsonObject) userstoryJsonObject.get("Release");
                JsonObject sprint=null;//(JsonObject) userstoryJsonObject.get("Iteration");
                Object obj=userstoryJsonObject.get("Iteration");
                //String str1=obj.toString();
                String appname="";
              
                
                //============================appliction=================================
            /* try{
                	   JsonObject application = (JsonObject) userstoryJsonObject.get("c_Application"); 
                	               
                	   JsonArray list =new JsonArray();
                       if(application.get("_tagsNameArray").getAsJsonArray().size()!=0)
                       {
                    	   
                    	   
                    	   //System.out.println(" not an  empty array");
                    	   
                    	   list=application.get("_tagsNameArray").getAsJsonArray();                    	   
                    	   //list=(List<String>) application.get("_tagsNameArray");
                    	   
                    	  // System.out.println("app list : "+list+" size : "+list.size());
                    	   
                    	   JsonObject appobj=(JsonObject) list.get(0) ;
                    	   //System.out.println("app obj : "+appobj+ " app name : "+appobj.get("Name").getAsString());
                    	   appname=appobj.get("Name").getAsString();
                       
                    	   
                    	   if(appname.contains("SFAINT"))
                           {
                        	   System.out.println(" application sfaint is there");
                        	   application_count++;
                        	   System.out.println("app list : "+list+" size : "+list.size()+"  "+userstoryJsonObject.get("FormattedID"));
                           }
                    	  // CR5981
                       
                       }
                       else
                       {
                    	   //System.out.println(" empty array");
                       }
                	 
                	
                	 //System.out.println(i+" "+" true  "+application.get("_tagsNameArray"));
                }catch(Exception e){
                	count_nill++;
                	System.out.println("application false");
                }
                */
              //================================  cr  =======================================
               
                try{
                	
                	//CR5571  CR5367 CR4594
             	   JsonObject crnumber = (JsonObject) userstoryJsonObject.get("c_CR"); 
             	               
             	   JsonArray list =new JsonArray();
                    if(crnumber.get("_tagsNameArray").getAsJsonArray().size()!=0)
                    {
                 	   
                 	   
                 	   //System.out.println(" not an  empty array");
                 	   
                 	   list=crnumber.get("_tagsNameArray").getAsJsonArray();   
                 	   //System.out.println("CR list : "+list+" size : "+list.size());
                 	   
                 	   JsonObject appobj=(JsonObject) list.get(0) ;
                 	   //System.out.println("app obj : "+appobj+ " CR name : "+appobj.get("Name").getAsString());
                 	   appname=appobj.get("Name").getAsString();
                    
                 	  System.out.println("CR list : "+list+" size : "+list.size()+"  "+userstoryJsonObject.get("FormattedID"));
                 	   
                 	  /* if(appname.contains("CR5571"))
                        {
                     	   System.out.println(" cr CR5981 is there");
                     	  cr_count++;
                     	   System.out.println("CR list : "+list+" size : "+list.size()+"  "+userstoryJsonObject.get("FormattedID"));
                        }*/
                 	  // CR5981
                  
                    }
                    else
                    {
                 	  // System.out.println(" empty array");
                    }
             	 
             	
             	 //System.out.println(i+" "+" true  "+application.get("_tagsNameArray"));
             }catch(Exception e){
             	count_nill++;
             	System.out.println("CR false");
             }
      // ==================================================================================            
                
                
             
                
            /*	   
                
              
                
                try
                {
                	if(userstoryJsonObject.get("c_Testable5").toString().equals("null"))
                	System.out.println("getscuccess");
                }catch(Exception e){
                	System.out.println("not able to get file");
                }
                
                //if(userstoryJsonObject.get("c_Testable").toString().equals("null"))
                {
                	System.out.println("-c_Testable  --nulll---");
                	//sprint=(JsonObject) userstoryJsonObject.get("Iteration");
                }
               // else
                {
                	//System.out.println("-------not null------"+userstoryJsonObject.get("c_Testable").toString());
                }
                
               // usname=name.toString();
               // Object relName=release.get("_refObjectName");
                Object sprName="";//sprint.get("_refObjectName");
                
                System.out.println(" US===form id : " +fmid+" state : "+ stat);
                //System.out.println("uuid : "+uuids+" name: "+name);
                //System.out.println(" US===form id : " +fmid+" state : "+ stat+" rele Name : "+relName+ " spr name : "+sprName+ " obj "+obj);
                System.out.println(" obj id : "+obid+" totalcount : "+ tccount +" pass : "+passTc +" test status : "+testCaseStatus);
               
                //test.testcasereq(str1);
                
                Set<?> key=userstoryJsonObject.entrySet();
                Iterator itr=key.iterator();
                while(itr.hasNext())
                {
                	String str=itr.next().toString();	                
                	System.out.println("str : "+str);  
                }
                 
         */  
                
                
            //==============================================================================================    
                /*
                System.out.println("work ref : "+workref);
                // System.out.println("obid:TC:PTC"+obid+":"+tccount+":"+passTc+"\n\n"+testcasejson);
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                testcaseRequest.setWorkspace(work_ref);
                testcaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));// .and(new QueryFilter("ObjectID", "=" ,objid))
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());   
	            */
	            /*for (int j=0; j<testcaseQueryResponse.getResults().size()&&j<10;j++)
	            {
	            	JsonObject testcaseJsonObject = testcaseQueryResponse.getResults().get(i).getAsJsonObject();
	            	 Object tcobid=testcaseJsonObject.get("ObjectID");
	            	 System.out.println(tcobid);
	            	
	            }
	            */
	          
                
                /*JsonObject testcase=(JsonObject) userstoryJsonObject.get("TestCases");
               
                
                Object ref=userstoryJsonObject.get("ObjectID");
                System.out.println("testcases"+ testcase);
                String refs=ref.toString();
                System.out.println("ObjectID "+ref+"\n\n");
                
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                //testcaseRequest.setQueryFilter((new QueryFilter(" ref", "=", refs)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testSetRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testcaseRequest.setWorkspace(refs);
              
                testcaseRequest.setFetch(new Fetch("FormattedID"));	                
                testcaseRequest.setQueryFilter(new QueryFilter("ObjectID", "=", "56503916587"));
                
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());               
               */
                
            }

        } finally {
        	System.out.println("sfaint app count : "+application_count);
        	System.out.println("cr  count : "+cr_count);
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	
	public static void delete_fun() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 219";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Apttus PS";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            //restApi.setApplicationName(applicationName); 

           // QueryRequest userstoryRequest = new QueryRequest("HierarchicalRequirement"); 
            QueryRequest userstoryRequest = new QueryRequest("HierarchicalRequirement"); 
            userstoryRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and
            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse userstoryResponse = restApi.query(userstoryRequest);
            System.out.println("exe Successful: " + userstoryResponse.wasSuccessful());
            System.out.println("US Size: " + userstoryResponse.getTotalResultCount());
           
            for (int i=0; i<userstoryResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = userstoryResponse.getResults().get(i).getAsJsonObject();
                 
                Object fmid=userstoryJsonObject.get("FormattedID");   // "FormattedID","ScheduleState"
                Object stat=userstoryJsonObject.get("ScheduleState");
                Object obid=userstoryJsonObject.get("ObjectID");
                Object tccount=userstoryJsonObject.get("TestCaseCount");
                Object passTc=userstoryJsonObject.get("PassingTestCaseCount");
                Object testCaseStatus=userstoryJsonObject.get("TestCaseStatus"); 
                JsonObject testcasejson = (JsonObject) userstoryJsonObject.get("TestCases");   
                JsonObject workspacejson = (JsonObject) userstoryJsonObject.get("Workspace");    
                Object uuids=userstoryJsonObject.get("UUID"); 
                Object name=userstoryJsonObject.get("Name"); 
                String str1=userstoryJsonObject.get("Name").getAsString();
                Object workref=workspacejson.get("_ref");
               
                String work_ref=workref.toString();
                String objid=obid.toString();
                
                JsonObject release=(JsonObject) userstoryJsonObject.get("Release");
                JsonObject sprint=null;//(JsonObject) userstoryJsonObject.get("Iteration");
                Object obj=userstoryJsonObject.get("Iteration");
                //String str1=obj.toString();
               
                try
                {
                	if(userstoryJsonObject.get("c_Testable5").toString().equals("null"))
                	System.out.println("getscuccess");
                }catch(Exception e){
                	System.out.println("not able to get file");
                }
                
                //if(userstoryJsonObject.get("c_Testable").toString().equals("null"))
                {
                	System.out.println("-c_Testable  --nulll---");
                	//sprint=(JsonObject) userstoryJsonObject.get("Iteration");
                }
               // else
                {
                	//System.out.println("-------not null------"+userstoryJsonObject.get("c_Testable").toString());
                }
                
               // usname=name.toString();
                Object relName=release.get("_refObjectName");
                Object sprName="";//sprint.get("_refObjectName");
                
                System.out.println(" US===form id : " +fmid+" state : "+ stat);
                //System.out.println("uuid : "+uuids+" name: "+name);
                //System.out.println(" US===form id : " +fmid+" state : "+ stat+" rele Name : "+relName+ " spr name : "+sprName+ " obj "+obj);
                System.out.println(" obj id : "+obid+" totalcount : "+ tccount +" pass : "+passTc +" test status : "+testCaseStatus);
                
                //test.testcasereq(str1);
                
                Set<?> key=userstoryJsonObject.entrySet();
                Iterator itr=key.iterator();
                while(itr.hasNext())
                {
                	String str=itr.next().toString();	                
                	System.out.println("str : "+str);  
                }
                 
                
                /*
                System.out.println("work ref : "+workref);
                // System.out.println("obid:TC:PTC"+obid+":"+tccount+":"+passTc+"\n\n"+testcasejson);
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                testcaseRequest.setWorkspace(work_ref);
                testcaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));// .and(new QueryFilter("ObjectID", "=" ,objid))
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());   
	            */
	            /*for (int j=0; j<testcaseQueryResponse.getResults().size()&&j<10;j++)
	            {
	            	JsonObject testcaseJsonObject = testcaseQueryResponse.getResults().get(i).getAsJsonObject();
	            	 Object tcobid=testcaseJsonObject.get("ObjectID");
	            	 System.out.println(tcobid);
	            	
	            }
	            */
	          
                
                /*JsonObject testcase=(JsonObject) userstoryJsonObject.get("TestCases");
               
                
                Object ref=userstoryJsonObject.get("ObjectID");
                System.out.println("testcases"+ testcase);
                String refs=ref.toString();
                System.out.println("ObjectID "+ref+"\n\n");
                
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                //testcaseRequest.setQueryFilter((new QueryFilter(" ref", "=", refs)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testSetRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testcaseRequest.setWorkspace(refs);
              
                testcaseRequest.setFetch(new Fetch("FormattedID"));	                
                testcaseRequest.setQueryFilter(new QueryFilter("ObjectID", "=", "56503916587"));
                
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());               
               */
                
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void testcasereq(String str1) throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 219";
	    String team_name        = "ConnectIT";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest testcaseRequest = new QueryRequest("TestCase");           
            testcaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", "NerdHerd"));
          //  testcaseRequest.setQueryFilter(new QueryFilter("WorkProduct.Name", "=",str1  ));  //new QueryFilter("Iteration.Name", "=", iterationName).and
         
            //  https://rally1.rallydev.com/slm/webservice/v2.0/testcase/62489186472    new QueryFilter("Project.Name", "=", team_name).and new QueryFilter("_ref","=","https://rally1.rallydev.com/slm/webservice/v2.0/testcase/62489186472")

            QueryResponse testcaseResponse = restApi.query(testcaseRequest);
            System.out.println("     exe Successful: " + testcaseResponse.wasSuccessful());
            System.out.println("     TC Size: " + testcaseResponse.getTotalResultCount());
           
            for (int i=0; i<testcaseResponse.getResults().size()&&i<1;i++)
            {
                JsonObject testcaseJsonObject = testcaseResponse.getResults().get(i).getAsJsonObject();
                
                Object ref=testcaseJsonObject.get("ObjectID");
                Object lastVerdict =testcaseJsonObject.get("LastVerdict");
                String formid=testcaseJsonObject.get("FormattedID").getAsString();
                try
                {
                	if(testcaseJsonObject.get("c_Testable5").toString().equals("null"))
                	System.out.println("getscuccess");
                }catch(Exception e){
                	System.out.println("not able to get file");
                }
               // Method
               // c_CanbeAutomated
                
                System.out.println("     TC : "+formid+" : "+lastVerdict);
                
                Set<?> key=testcaseJsonObject.entrySet();
                Iterator itr=key.iterator();
                while(itr.hasNext())
                {
                	String str=itr.next().toString();	                
                	System.out.println("str : "+str);  
                }
                
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
			
	public static void iterationreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest iterationRequest = new QueryRequest("Iteration"); 
            //iterationRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse iterationResponse = restApi.query(iterationRequest);
            System.out.println("Successful: " + iterationResponse.wasSuccessful());
            System.out.println("iteration Size: " + iterationResponse.getTotalResultCount());
           
            for (int i=0; i<iterationResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = iterationResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void releasereq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest iterationRequest = new QueryRequest("Release"); 
            //iterationRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse iterationResponse = restApi.query(iterationRequest);
            System.out.println("Successful: " + iterationResponse.wasSuccessful());
            System.out.println("release Size: " + iterationResponse.getTotalResultCount());
           
            for (int i=0; i<iterationResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = iterationResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void testreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 
           
            QueryRequest testRequest = new QueryRequest("Defects");  //Releases , Iterations , Project ,  HierarchicalRequirement , TestCases , Defects , 
            testRequest.setQueryFilter((new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //testRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse testResponse = restApi.query(testRequest);
            System.out.println("Successful: " + testResponse.wasSuccessful());
            System.out.println("responce Size: " + testResponse.getTotalResultCount());
           
            for (int i=0; i<testResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = testResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	
	//obj id : 63562622913	
	//ObjectID=63267832232
	
	//_refObjectUUID="5af00ef8-672e-419a-b576-7581355fa457"
	//_refObjectUUID="a28f3e65-bc8d-469e-8a32-2cec8465623b"
	
	//ObjectUUID="a28f3e65-bc8d-469e-8a32-2cec8465623b"
	//ObjectUUID="5af00ef8-672e-419a-b576-7581355fa457"
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	        //=============================================================================================================
	        /*
	        RallyRestApi restApi = null;
	       // String reqfor="HierarchicalRequirement";	        
	        String reqfor="TestCase";	 
	        System.out.println("strated\n");
	        
	        
	        restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName);
            System.out.println(restApi.getWsapiVersion()); 
	        
	        try {
	             restApi = new RallyRestApi(new URI(host),username,password);
	             restApi.setApplicationName(applicationName); 

	             //System.out.println(restApi.getWsapiVersion()); 
	             
	             QueryRequest storyRequest = new QueryRequest(reqfor);
	             storyRequest.setFetch(new Fetch(new String[] {"Name", "FormattedID","ScheduleState","TestSet","LastVerdict","Verdict"}));
	             storyRequest.setPageSize(10);
	             storyRequest.setLimit(10);
	             storyRequest.setScopedDown(false);
	             storyRequest.setScopedUp(false);
	             storyRequest.setQueryFilter((new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                   
	             QueryResponse storyQueryResponse = restApi.query(storyRequest);
	             total_count=storyQueryResponse.getTotalResultCount();
	             System.out.println("query result count: " + iterationName + " :" + storyQueryResponse.getTotalResultCount());
	             
	             for (int j=0; j<storyQueryResponse.getResults().size();j++)
	             {
	                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
	                 //String state_temp=storyJsonObject.get("ScheduleState").getAsString();	                 
	                 
	                 System.out.println( "FormattedID: " + storyJsonObject.get("FormattedID") + " LastVerdict: " + storyJsonObject.get("LastVerdict")+ " TestSet: " + storyJsonObject.get("TestSet")); //"Name: " + storyJsonObject.get("Name") +
	                 //System.out.println( "testcase: " + storyJsonObject.get("TestCase") + " Verdict: " + storyJsonObject.get("LastVerdict")); //"Name: " + storyJsonObject.get("Name") +
	 	            
	             }	 
	     
	     } catch (URISyntaxException e)
	     {
			
			e.printStackTrace();
		 }finally {  if (restApi != null) { 	   restApi.close();   } System.out.println("\ncompleted");   }
	
	        
*/	        //==========================================================================================
	/*        
	        
	        RallyRestApi restApi = null;
	        //String reqfor="HierarchicalRequirement";	        
	        String reqfor="testcase";	 
	        System.out.println("strated\n");
	        
	        
	        restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName);
            System.out.println(restApi.getWsapiVersion()); 
            
	        QueryRequest testCaseRequest = new QueryRequest("TestCase");
	        testCaseRequest.setFetch(new Fetch("Build","TestCase","TestSet", "Verdict","FormattedID"));
	        testCaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));   //.and(new QueryFilter("ScheduleState", "=", "In-Progress")
            
	        QueryResponse testCaseResponse = restApi.query(testCaseRequest);
	        int numberTestCases = testCaseResponse.getTotalResultCount();
	        System.out.println("tc count = "+numberTestCases);
	        
	        for (int j=0; j<testCaseResponse.getResults().size()&&j<5;j++)
            {
                JsonObject storyJsonObject = testCaseResponse.getResults().get(j).getAsJsonObject(); 
                String frid=storyJsonObject.get("FormattedID").getAsString();
                System.out.println( j+"  == FormattedID: " +frid+ " Verdict: " + storyJsonObject.get("Verdict")); //"Name: " + storyJsonObject.get("Name") +
               
                
                QueryRequest testCaseResultRequest = new QueryRequest("TestCaseResult");
    	        testCaseResultRequest.setFetch(new Fetch("Build","TestCase","TestSet", "Verdict","FormattedID","Iteration.Name"));
    	        
    	        
    	        testCaseResultRequest.setQueryFilter(new QueryFilter("TestCase.FormattedID", "=",frid));
    	        QueryResponse testCaseResultResponse = restApi.query(testCaseResultRequest);
    	        int numberTestCaseResults = testCaseResultResponse.getTotalResultCount();
    	        
    	        System.out.println("TCR "+numberTestCaseResults);
    	        if(numberTestCaseResults >0)
    	        {
    	            System.out.println("state "+testCaseResultResponse.getResults().get(0).getAsJsonObject().get("Verdict").getAsString());
    	        	System.out.println("state "+testCaseResultResponse.getResults().get(0).getAsJsonObject().get("Iteration.Name").getAsString());
    	        }
    	        	else
    	            System.out.println("Not executed");
            }	 
    
	        
	        System.out.println("\ncompleted");
	   
	  */      
	    //====================================================================================================    
	     /*try {
	             restApi = new RallyRestApi(new URI(host),username,password);
	             restApi.setApplicationName(applicationName); 

	             //System.out.println(restApi.getWsapiVersion()); 
	             
	             QueryRequest storyRequest = new QueryRequest(reqfor);
	             storyRequest.setFetch(new Fetch(new String[] {"Name", "FormattedID","ScheduleState"}));
	             storyRequest.setPageSize(1);
	             storyRequest.setLimit(1000);
	             storyRequest.setScopedDown(false);
	             storyRequest.setScopedUp(false);
	             storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                    
	             QueryResponse storyQueryResponse = restApi.query(storyRequest);
	             total_count=storyQueryResponse.getTotalResultCount();
	             System.out.println("query result count: " + iterationName + " :" + storyQueryResponse.getTotalResultCount());
	             
	             for (int j=0; j<storyQueryResponse.getResults().size();j++)
	             {
	                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
	                 String state_temp=storyJsonObject.get("ScheduleState").getAsString();
	                 
	                 if(state_temp.contains("Backlog")) 	backlogs++;
	                 if(state_temp.contains("Defined")) 	defined++;
	                 if(state_temp.contains("In-Progress")) in_progress++;
	                 if(state_temp.contains("Completed")) 	completed++;
	                 if(state_temp.contains("Accepted")) 	accepted++;
	                 
	                 System.out.println( "FormattedID: " + storyJsonObject.get("FormattedID") + " ScheduleState: " + storyJsonObject.get("ScheduleState")); //"Name: " + storyJsonObject.get("Name") +
	             }	 
	     
	     } catch (URISyntaxException e)
	     {
			
			e.printStackTrace();
		 }finally {  if (restApi != null) { 	   restApi.close();   } System.out.println("\ncompleted");   }
	
	     */
	     
	     
	     
	
	
	

}
