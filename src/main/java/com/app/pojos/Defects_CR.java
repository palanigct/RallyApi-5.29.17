package com.app.pojos;

import java.util.Date;

public class Defects_CR
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
	
	private int[] submitted = new int [10];
	private int[] open = new int [10];
	private int[] fixed = new int [10];
	private int[] closed = new int [10];
	private int[] reopen = new int [10];
	private int[] ready_for_test = new int [10];
	private int[] total_state = new int [10];
	
	private int[] critical = new int [10];
	private int[] major = new int [10];
	private int[] average = new int [10];
	private int[] minor = new int [10];
	private int[] total_severity = new int [10];
		
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
	
	private Date opendate;
	private Date closeddate;

	private Defect_Age defect_age=new Defect_Age();
	
	
	
	
	
	
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getCloseddate() {
		return closeddate;
	}
	public void setCloseddate(Date closeddate) {
		this.closeddate = closeddate;
	}
	public Defect_Age getDefect_age() {
		return defect_age;
	}
	public void setDefect_age(Defect_Age defect_age) {
		this.defect_age = defect_age;
	}

	public int[] getSubmitted() {
		return submitted;
	}
	public void setSubmitted(int[] submitted) {
		this.submitted = submitted;
	}
	public int[] getOpen() {
		return open;
	}
	public void setOpen(int[] open) {
		this.open = open;
	}
	public int[] getFixed() {
		return fixed;
	}
	public void setFixed(int[] fixed) {
		this.fixed = fixed;
	}
	public int[] getClosed() {
		return closed;
	}
	public void setClosed(int[] closed) {
		this.closed = closed;
	}
	public int[] getReopen() {
		return reopen;
	}
	public void setReopen(int[] reopen) {
		this.reopen = reopen;
	}
	public int[] getReady_for_test() {
		return ready_for_test;
	}
	public void setReady_for_test(int[] ready_for_test) {
		this.ready_for_test = ready_for_test;
	}
	public int[] getTotal_severity() {
		return total_severity;
	}
	public void setTotal_severity(int[] total_severity) {
		this.total_severity = total_severity;
	}
	public int[] getCritical() {
		return critical;
	}
	public void setCritical(int[] critical) {
		this.critical = critical;
	}
	public int[] getMajor() {
		return major;
	}
	public void setMajor(int[] major) {
		this.major = major;
	}
	public int[] getAverage() {
		return average;
	}
	public void setAverage(int[] average) {
		this.average = average;
	}
	public int[] getMinor() {
		return minor;
	}
	public void setMinor(int[] minor) {
		this.minor = minor;
	}
	public int[] getTotal_state() {
		return total_state;
	}
	public void setTotal_state(int[] total_state) {
		this.total_state = total_state;
	}
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
	
    public void setAllSeverity(int[] critical, int[] major , int[] average , int[] minor , int[] total_severity )
    {
        this.critical =critical;
        this.major=major;
        this.average =average;
        this.minor =minor;
        this.total_severity=total_severity;
    }
    
    public void setAllState( int[] submitted , int[] open, int[] fixed , int[] closed , int[] reopen ,int[] ready_for_test , int[] total_state)
    {
         this.submitted=submitted;
         this.open=open;
         this.fixed =fixed;
         this.closed=closed;
         this.reopen=reopen;
         this.ready_for_test=ready_for_test;
         this.total_state=total_state;
    }
    
    
    public void displayAllSeverity()
    {
    	for(int i=0;i<this.critical.length;i++,System.out.println(" "))
     	   System.out.print( "CR"+i+":-  critical : "+this.critical[i]+" major : "+this.major[i]+" avera : "+this.average[i]+" minor : "+this.minor[i]+" tot sev: "+this.total_severity[i]);
    }
    
    public void displayAllState()
    {
    	for(int i=0;i<this.submitted.length;i++,System.out.println(" "))
      	   System.out.print( "CR"+i+":-  submit : "+this.submitted[i]+" open : "+this.open[i]+" fixed : "+this.fixed[i]+" closed : "+this.closed[i]+" reopen: "+this.reopen[i]+" ready open : "+ this.ready_for_test[i]+" tot state: "+this.total_state[i]);
    }
    
    public void displayAll()
    {
    	for(int i=0;i<this.backlogs.length;i++,System.out.println(" "))
    	   System.out.print( "CR"+i+":-  back : "+this.backlogs[i]+" defin : "+this.defined[i]+" in_prog : "+this.in_progress[i]+" comp : "+this.completed[i]+" accp : "+this.accepted[i]+ " total : "+this.total[i]);
    }
}
