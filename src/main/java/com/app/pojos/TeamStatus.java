package com.app.pojos;

public class TeamStatus
{	
	UserStories userstories=new UserStories();
	Defects defects=new Defects();
	TestCases testcases=new TestCases();

	UserStories_CR userstories_cr=new UserStories_CR();
	Defects_CR defects_cr=new Defects_CR();
	TestCases_CR testcases_cr=new TestCases_CR();
	
	UserStories_Application userstories_application=new UserStories_Application();
	Defects_Application defects_application=new Defects_Application();
	TestCases_Application testcases_application=new TestCases_Application();
	
	private int[] available_CRindex= new int [100];
	
	public int[] getAvailable_CRindex() {
		return available_CRindex;
	}

	public void setAvailable_CRindex(int[] available_CRindex) {
		this.available_CRindex = available_CRindex;
	}

	public void setAll(UserStories userstories,Defects defects,TestCases testcases)
	{
		this.userstories=userstories;
		this.defects=defects;
		this.testcases=testcases;		
	}
	
	public void setAll_CR(UserStories_CR userstories_cr,Defects_CR defects_cr,TestCases_CR testcases_cr)
	{
		this.userstories_cr=userstories_cr;
		this.defects_cr=defects_cr;
		this.testcases_cr=testcases_cr;
	}
	
	public void setAllApplication(UserStories_Application userstories_application,Defects_Application defects_application,TestCases_Application testcases_application)
	{
		this.userstories_application=userstories_application;
		this.defects_application=defects_application;
		this.testcases_application=testcases_application;
	}
   
	
	
	public UserStories_Application getUserstories_application() {
		return userstories_application;
	}

	public void setUserstories_application(UserStories_Application userstories_application) {
		this.userstories_application = userstories_application;
	}

	public Defects_Application getDefects_application() {
		return defects_application;
	}

	public void setDefects_application(Defects_Application defects_application) {
		this.defects_application = defects_application;
	}

	public TestCases_Application getTestcases_application() {
		return testcases_application;
	}

	public void setTestcases_application(TestCases_Application testcases_application) {
		this.testcases_application = testcases_application;
	}

	public UserStories getUserstories() {
		return userstories;
	}

	
	
	public void setUserstories(UserStories userstories) {
		this.userstories = userstories;
	}

	public TestCases getTestcases() {
		return testcases;
	}

	public void setTestcases(TestCases testcases) {
		this.testcases = testcases;
	}

	public UserStories_CR getUserstories_cr() {
		return userstories_cr;
	}

	public void setUserstories_cr(UserStories_CR userstories_cr) {
		this.userstories_cr = userstories_cr;
	}

	
	
	public Defects_CR getDefects_cr() {
		return defects_cr;
	}

	public void setDefects_cr(Defects_CR defects_cr) {
		this.defects_cr = defects_cr;
	}

	public TestCases_CR getTestcases_cr() {
		return testcases_cr;
	}

	public void setTestcases_cr(TestCases_CR testcases_cr) {
		this.testcases_cr = testcases_cr;
	}

	public UserStories getUserStories()
	{
		return userstories;
	}
	public void setUserStories(UserStories userstories)
	{
		this.userstories=userstories;
	}
	
	public Defects getDefects()
	{
		return defects;
	}
	public void setDefects(Defects defects)
	{
		this.defects=defects;
	}

	public TestCases getTestCases()
	{
		return testcases;
	}
	public void setTestCases(TestCases testcases)
	{
		this.testcases=testcases;
	}
	
	public void displayAll()
	{		
		System.out.println("User story  == defi "+userstories.getDefined()+" || back "+userstories.getBacklogs()+" || in-prog "+userstories.getIn_progress()+" || comp "+userstories.getCompleted()+" || accep "+userstories.getAccepted()+" || tota "+userstories.getTotal());
		System.out.println("Defects == defi "+defects.getDefined()+" || back "+defects.getBacklogs()+" || in-prog "+defects.getIn_progress()+" || comp "+defects.getCompleted()+" || accp "+defects.getAccepted()+" || tota "+defects.getTotal());
		System.out.println("Testcases == pass "+testcases.getPass()+" || fail "+testcases.getFail()+" || in-prog "+testcases.getIn_progress()+" || blog "+testcases.getBlocked()+" || no-run "+testcases.getNo_run()+" || tota "+testcases.getTotal());
	}
}
