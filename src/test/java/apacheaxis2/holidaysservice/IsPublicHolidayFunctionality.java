package apacheaxis2.holidaysservice;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.IsHolidayType;
import apacheaxis2.holidaysservice.EnricoStub.IsPublicHoliday;
import apacheaxis2.holidaysservice.EnricoStub.IsPublicHolidayResponse;

public class IsPublicHolidayFunctionality 
{
	public static void main(String[] args) throws Exception
	{
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		//Create objects to inner classes and send request
		IsPublicHoliday req=new IsPublicHoliday();
		DateType dt=new DateType();
		dt.setDay(4);
		dt.setMonth(7);
		dt.setYear(2020);
		req.setDate(dt);
		req.setCountry("usa");
		req.setRegion("mn");
		//Get Response
		IsPublicHolidayResponse res=stub.isPublicHoliday(req);
		IsHolidayType iht=res.getIsPublicHoliday();
		if(iht.getIsHoliday())
		{
			System.out.println("Holiday");
		}
		else
		{
			System.out.println("Not a Holiday/Working Day");
		}
	}
}
