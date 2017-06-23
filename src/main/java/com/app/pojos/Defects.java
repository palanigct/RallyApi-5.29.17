package com.app.pojos;

public class Defects
{
	private int backlogs;
	private int defined;
	private int in_progress;
	private int completed;
	private int accepted;
	private int total;	
	private String name;
	private String formattedID;
	private String status;
	private String sprintOrRelease;
	private String sprintname;
	private String releaseName;
	private String openedDate;
	
	private int submitted;
	private int open;
	private int fixed;
	private int closed;
	private int reopen;
	private int ready_for_test;
	private int total_severity;
	private int critical;
	private int major;
	private int average;
	private int minor;
	private int total_state;
	
	private Defect_Age defect_age;
	
	
	
	public Defect_Age getDefect_age() {
		return defect_age;
	}

	public void setDefect_age(Defect_Age defect_age) {
		this.defect_age = defect_age;
	}

	private int open_days;
	
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

	public void setAllState(int submitted,int open,int fixed,int closed,int reopen,int ready_for_test,int total_state)
	{
		this.submitted=submitted;
		this.open=open;
		this.fixed=fixed;
		this.closed=closed;
		this.reopen=reopen;
		this.ready_for_test=ready_for_test;
		this.total_state=total_state;
	}
	
	public void setAllSeverity(int critical,int major,int average,int minor,int total_severity)
	{
		this.critical=critical;
		this.major=major;
		this.average=average;
		this.minor=minor;	
		this.total_severity=total_severity;
	}
	
	
	
	
	public int getSubmitted() {
		return submitted;
	}

	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getFixed() {
		return fixed;
	}

	public void setFixed(int fixed) {
		this.fixed = fixed;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public int getReopen() {
		return reopen;
	}

	public void setReopen(int reopen) {
		this.reopen = reopen;
	}

	public int getReady_for_test() {
		return ready_for_test;
	}

	public void setReady_for_test(int ready_for_test) {
		this.ready_for_test = ready_for_test;
	}

	public int getTotal_severity() {
		return total_severity;
	}

	public void setTotal_severity(int total_severity) {
		this.total_severity = total_severity;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getTotal_state() {
		return total_state;
	}

	public void setTotal_state(int total_state) {
		this.total_state = total_state;
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
