package com.app.pojos;

public class TestCases_Application 
{

	  private int[] pass = new int[10];
	  private int[] fail  = new int[10];
	  private int[] in_progress  = new int[10];
	  private int[] blocked  = new int[10];
	  private int[] no_run  = new int[10];
	  private int[] total  = new int[10];
	  private int[] percentage_execute  = new int[10];
	  private int[] percentage_pass  = new int[10];
	  private int[] percentage_fail  = new int[10];
	  private int[] method_count  = new int[10];
	  private int[] automated_count  = new int[10];
	  private int[] executed  = new int[10];
	  
	  
	  
	  public void setAll(int[] pass ,int[] fail , int[] in_progress  , int[] blocked  , int[] no_run ,int[] executed ,int[] total )
	  {
		  this.pass=pass;
		  this.fail=fail;
		  this.in_progress=in_progress;
		  this.blocked=blocked;
		  this.no_run=no_run;
		  this.executed=executed;
		  this.total=total;
	  }
	  
	  public void displayAll()
	  {
		  for(int i=0;i<this.pass.length;i++,System.out.println(" "))
		  {
			  System.out.print(" CR : "+ i+ "  pass : "+this.pass[i]+" fail : "+this.fail[i]+" in prog : "+this.in_progress[i]+" block : "+this.blocked[i]+" no run : "+this.no_run[i]+ " exe : "+this.executed[i]+" total : "+this.total[i]);
		  }
	  }
	  
	  
	public int[] getPass() {
		return pass;
	}
	public void setPass(int[] pass) {
		this.pass = pass;
	}
	public int[] getFail() {
		return fail;
	}
	public void setFail(int[] fail) {
		this.fail = fail;
	}
	public int[] getIn_progress() {
		return in_progress;
	}
	public void setIn_progress(int[] in_progress) {
		this.in_progress = in_progress;
	}
	public int[] getBlocked() {
		return blocked;
	}
	public void setBlocked(int[] blocked) {
		this.blocked = blocked;
	}
	public int[] getNo_run() {
		return no_run;
	}
	public void setNo_run(int[] no_run) {
		this.no_run = no_run;
	}
	public int[] getTotal() {
		return total;
	}
	public void setTotal(int[] total) {
		this.total = total;
	}
	public int[] getPercentage_execute() {
		return percentage_execute;
	}
	public void setPercentage_execute(int[] percentage_execute) {
		this.percentage_execute = percentage_execute;
	}
	public int[] getPercentage_pass() {
		return percentage_pass;
	}
	public void setPercentage_pass(int[] percentage_pass) {
		this.percentage_pass = percentage_pass;
	}
	public int[] getPercentage_fail() {
		return percentage_fail;
	}
	public void setPercentage_fail(int[] percentage_fail) {
		this.percentage_fail = percentage_fail;
	}
	public int[] getMethod_count() {
		return method_count;
	}
	public void setMethod_count(int[] method_count) {
		this.method_count = method_count;
	}
	public int[] getAutomated_count() {
		return automated_count;
	}
	public void setAutomated_count(int[] automated_count) {
		this.automated_count = automated_count;
	}
	public int[] getExecuted() {
		return executed;
	}
	public void setExecuted(int[] executed) {
		this.executed = executed;
	}
	  
	  
}
