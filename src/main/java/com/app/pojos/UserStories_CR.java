package com.app.pojos;

import java.util.ArrayList;

public class UserStories_CR 
{
	private  int[] backlogs = new int [10];
	private  int[] defined  = new int [10];
	private  int[] in_progress = new int [10];
	private  int[] completed = new int [10];
	private  int[] accepted = new int [10];
	private  int[] total = new int [10];
	private  int[] testableFieldCount = new int [10];
	
	private  int[] backlogs_testable = new int [10];
	private  int[] defined_testable = new int [10];
	private  int[] in_progress_testable = new int [10];
	private  int[] completed_testable = new int [10];
	private  int[] accepted_testable = new int [10];
	private  int[] total_testable = new int [10];
		
	private String name;
	private String formattedID;
	private String status;
	private String sprintOrRelease;
	private String sprintname;
	private String releaseName;
	private String testable; 
	
	private String severity; 
	private String state; 
	private String CRNumber;
	public int[] getBacklogs() {
		return backlogs;
	}
	public void setBacklogs(int[] backlogs) {
		this.backlogs = backlogs;
	}
	public int[] getDefined() {
		return defined;
	}
	public void setDefined(int[] defined) {
		this.defined = defined;
	}
	public int[] getIn_progress() {
		return in_progress;
	}
	public void setIn_progress(int[] in_progress) {
		this.in_progress = in_progress;
	}
	public int[] getCompleted() {
		return completed;
	}
	public void setCompleted(int[] completed) {
		this.completed = completed;
	}
	public int[] getAccepted() {
		return accepted;
	}
	public void setAccepted(int[] accepted) {
		this.accepted = accepted;
	}
	public int[] getTotal() {
		return total;
	}
	public void setTotal(int[] total) {
		this.total = total;
	}
	public int[] getTestableFieldCount() {
		return testableFieldCount;
	}
	public void setTestableFieldCount(int[] testableFieldCount) {
		this.testableFieldCount = testableFieldCount;
	}
	public int[] getBacklogs_testable() {
		return backlogs_testable;
	}
	public void setBacklogs_testable(int[] backlogs_testable) {
		this.backlogs_testable = backlogs_testable;
	}
	public int[] getDefined_testable() {
		return defined_testable;
	}
	public void setDefined_testable(int[] defined_testable) {
		this.defined_testable = defined_testable;
	}
	public int[] getIn_progress_testable() {
		return in_progress_testable;
	}
	public void setIn_progress_testable(int[] in_progress_testable) {
		this.in_progress_testable = in_progress_testable;
	}
	public int[] getCompleted_testable() {
		return completed_testable;
	}
	public void setCompleted_testable(int[] completed_testable) {
		this.completed_testable = completed_testable;
	}
	public int[] getAccepted_testable() {
		return accepted_testable;
	}
	public void setAccepted_testable(int[] accepted_testable) {
		this.accepted_testable = accepted_testable;
	}
	public int[] getTotal_testable() {
		return total_testable;
	}
	public void setTotal_testable(int[] total_testable) {
		this.total_testable = total_testable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormattedID() {
		return formattedID;
	}
	public void setFormattedID(String formattedID) {
		this.formattedID = formattedID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSprintOrRelease() {
		return sprintOrRelease;
	}
	public void setSprintOrRelease(String sprintOrRelease) {
		this.sprintOrRelease = sprintOrRelease;
	}
	public String getSprintname() {
		return sprintname;
	}
	public void setSprintname(String sprintname) {
		this.sprintname = sprintname;
	}
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
	public String getTestable() {
		return testable;
	}
	public void setTestable(String testable) {
		this.testable = testable;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCRNumber() {
		return CRNumber;
	}
	public void setCRNumber(String cRNumber) {
		CRNumber = cRNumber;
	}

    public void setAll( int[] backlogs , int[] defined ,  int[] in_progress ,  int[] completed , int[] accepted , int[] total )
    {
    	this.backlogs=backlogs;
    	this.defined=defined;
    	this.in_progress=in_progress;
    	this.completed=completed;
    	this.accepted=accepted;
    	this.total=total;
    }
	
    
    public void displayAll()
    {
    	for(int i=0;i<backlogs.length;i++,System.out.println(" "))
    	   System.out.print( "CR"+i+":-  back : "+this.backlogs[i]+" defin : "+this.defined[i]+" in_prog : "+this.in_progress[i]+" comp : "+this.completed[i]+" accp : "+this.accepted[i]+ " total : "+this.total[i]);
    }
}
