package com.app.pojos;

public class UserStories 
{
	private int backlogs;
	private int defined;
	private int in_progress;
	private int completed;
	private int accepted;
	private int total;
	private int testableFieldCount;
	
	private int backlogs_testable;
	private int defined_testable;
	private int in_progress_testable;
	private int completed_testable;	
	private int accepted_testable;
	private int total_testable;

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
	private String application;
	
	private String openedDate;
	private int open_days;
	
	private String CRList;
	
	
	public String getCRList() {
		return CRList;
	}

	public void setCRList(String cRList) {
		CRList = cRList;
	}

	public int getOpen_days() {
		return open_days;
	}

	public void setOpen_days(int open_days) {
		this.open_days = open_days;
	}

	public String getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(String openedDate) {
		this.openedDate = openedDate;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
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

	public int getTestableFieldCount() {
		return testableFieldCount;
	}

	public void setTestableFieldCount(int testableFieldCount) {
		this.testableFieldCount = testableFieldCount;
	}
	public String getTestable() {
		return testable;
	}

	public void setTestable(String testable) {
		this.testable = testable;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCRNumber() {
		return CRNumber;
	}

	public void setCRNumber(String cRNumber) {
		CRNumber = cRNumber;
	}

	public int getBacklogs_testable() {
		return backlogs_testable;
	}

	public void setBacklogs_testable(int backlogs_testable) {
		this.backlogs_testable = backlogs_testable;
	}

	public int getDefined_testable() {
		return defined_testable;
	}

	public void setDefined_testable(int defined_testable) {
		this.defined_testable = defined_testable;
	}

	public int getIn_progress_testable() {
		return in_progress_testable;
	}

	public void setIn_progress_testable(int in_progress_testable) {
		this.in_progress_testable = in_progress_testable;
	}

	public int getCompleted_testable() {
		return completed_testable;
	}

	public void setCompleted_testable(int completed_testable) {
		this.completed_testable = completed_testable;
	}

	public int getAccepted_testable() {
		return accepted_testable;
	}

	public void setAccepted_testable(int accepted_testable) {
		this.accepted_testable = accepted_testable;
	}

	public int getTotal_testable() {
		return total_testable;
	}

	public void setTotal_testable(int total_testable) {
		this.total_testable = total_testable;
	}

	public void setAllTestable(int backlogs_testable,int defined_testable ,int in_progress_testable,  int completed_testable,  int accepted_testable, int total_testable)
	{
		this.backlogs_testable=backlogs_testable;
		this.in_progress_testable=in_progress_testable;
		this.defined_testable=defined_testable;
		this.completed_testable=completed_testable;
		this.accepted_testable=accepted_testable;
		this.total_testable=total_testable;
	}
	
	public void displayTestable()
	{
		System.out.println(this.total_testable+" "+this.accepted_testable+" "+this.completed_testable+" "+this.defined_testable+" "+this.in_progress_testable);
	}
	public void setAll(int backlogs,int defined,int in_progress,int completed, int accepted, int total)
	{
		this.backlogs=backlogs;
		this.defined=defined;
		this.in_progress=in_progress;
		this.completed=completed;
		this.accepted=accepted;
		this.total=total;
	}	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getBacklogs() {
		return backlogs;
	}
	public void setBacklogs(int backlogs) {
		this.backlogs = backlogs;
	}
	public int getDefined() {
		return defined;
	}
	public void setDefined(int defined) {
		this.defined = defined;
	}
	public int getIn_progress() {
		return in_progress;
	}
	public void setIn_progress(int in_progress) {
		this.in_progress = in_progress;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	
	public void displayAll()
	{
		System.out.println("backlogs : "+backlogs);
		System.out.println("defined  : "+defined);
		System.out.println("in-progress: "+in_progress);
		System.out.println("completed : "+completed);
		System.out.println("accepted : "+accepted);
		System.out.println("total : "+total);
	}
}
