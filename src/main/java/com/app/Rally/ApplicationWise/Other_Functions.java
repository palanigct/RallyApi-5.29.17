package com.app.Rally.ApplicationWise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;

public class Other_Functions 
{
	public static Readfile read = new Readfile();	
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/main/resources/INPUT.xls", "APP_DATA");
	public static ArrayList<String> team_list=new ArrayList<String>();
	public static ArrayList<String> CRList=new ArrayList<String>();
	public static ArrayList<String> AppList=new ArrayList<String>();
	
	public static ArrayList<String> get_team_List()
	{
		ArrayList<String> team_list=new ArrayList<String>();
		
		for(int i=0;i<mydata_App_Data.size();i++)
		{
			String exe_status=mydata_App_Data.get(i).get("Execution Status");			
			
			if(exe_status.equals("YES"))
			{				
			   team_list.add(mydata_App_Data.get(i).get("Scrum Team"));
			}
		}
		return team_list;		
	}
	
	public static ArrayList<String> get_CR_List()
	{
		for(int i=0;i<mydata_App_Data.size();i++)
		{	
			 if(!(mydata_App_Data.get(i).get("CRNumbers").equals("")))
			  CRList.add(mydata_App_Data.get(i).get("CRNumbers"));			
		}
		return CRList;		
	}
	
	public static ArrayList<String> get_Application_List()
	{
		for(int i=0;i<mydata_App_Data.size();i++)
		{	
			 if(!(mydata_App_Data.get(i).get("Applications").equals("")))
				 AppList.add(mydata_App_Data.get(i).get("Applications"));			
		}
		return AppList;		
	}
	
	public static void copy_template_folder() throws Throwable
	{		
		File index = new File("src\\main\\resources\\Output Folder\\Rally Report");	
		
		if(index.exists())
		{			
			FileUtils.deleteDirectory(index);			
		}		
		
		File source = new File("src\\main\\resources\\Folder Templates");
		File dest = new File("src\\main\\resources\\Output Folder");
		try {			
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void copy_output_folder() throws Throwable
	{
		String filepath=mydata_App_Data.get(0).get("Rally Report Path");		
		File index = new File(filepath+"\\Rally Report");
		filepath+="\\";
		
		if(index.exists())
		{			
			FileUtils.deleteDirectory(index);			
		}		
		
		File source = new File("src\\main\\resources\\Output Folder");
		File dest = new File(filepath);
		try {			
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static String getCRnumber(String name)
	{
		String cr="NULL";
		String [] splitted=name.split(" ");
		for(int i=0;i<splitted.length;i++)
		{
			String str=splitted[i];			
			if(str.contains("CR"))
			{
				cr=str;				
				break;
			}
		}
				
		return cr;		
	}
	
	public static void main(String str[])
	{
		
		for(int i=0;i<mydata_App_Data.size();i++)
		{
			System.out.println(mydata_App_Data.get(i));
		}
		System.out.println(mydata_App_Data);		
	}
}
