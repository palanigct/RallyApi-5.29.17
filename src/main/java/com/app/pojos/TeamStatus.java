package com.app.pojos;

public class TeamStatus
{	
	UserStories userstories=new UserStories();
	Defects defects=new Defects();
	TestCases testcases=new TestCases();

	UserStories_CR userstories_cr=new UserStories_CR();
	
	public void setAll(UserStories userstories,Defects defects,TestCases testcases)
	{
		this.userstories=userstories;
		this.defects=defects;
		this.testcases=testcases;		
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
