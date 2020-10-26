package apacheaxis2.holidaysservice;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.IsWorkDay;
import apacheaxis2.holidaysservice.EnricoStub.IsWorkDayResponse;
import apacheaxis2.holidaysservice.EnricoStub.IsWorkDayType;

public class IsWorkDayFunctionality 
{
	public static void main(String[] args) throws Exception
	{
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		//Create objects to inner classes and send request
		IsWorkDay req=new IsWorkDay();
		DateType dt=new DateType();
		dt.setDay(4);
		dt.setMonth(7);
		dt.setYear(2020);
		req.setDate(dt);
		req.setCountry("usa");
		req.setRegion("mn");
		//Get response
		IsWorkDayResponse res=stub.isWorkDay(req);
		IsWorkDayType iwdt=res.getIsWorkDay();
		if(iwdt.getIsWorkDay())
		{
			System.out.println("Working Day");
		}
		else
		{
			System.out.println("Not Working Day/Holiday");
		}	
	}
}
