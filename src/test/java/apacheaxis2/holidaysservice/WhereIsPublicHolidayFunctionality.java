package apacheaxis2.holidaysservice;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.LocalizedStringType;
import apacheaxis2.holidaysservice.EnricoStub.WhereIsPublicHoliday;
import apacheaxis2.holidaysservice.EnricoStub.WhereIsPublicHolidayCountryListType;
import apacheaxis2.holidaysservice.EnricoStub.WhereIsPublicHolidayResponse;
import apacheaxis2.holidaysservice.EnricoStub.WhereIsPublicHolidayType;

public class WhereIsPublicHolidayFunctionality
{
	public static void main(String[] args) throws Exception
	{
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		//Create objects to inner classes and send request
		WhereIsPublicHoliday req=new WhereIsPublicHoliday();
		DateType dt=new DateType();
		dt.setDay(5);
		dt.setMonth(7);
		dt.setYear(2020);
		req.setDate(dt);
		//Get response
		WhereIsPublicHolidayResponse res=stub.whereIsPublicHoliday(req);
		WhereIsPublicHolidayCountryListType wiphclt=res.getCountryList();
		try
		{
			WhereIsPublicHolidayType[] wipht=wiphclt.getCountry();
			for(int i=0;i<wipht.length;i++)
			{
				String countrycode=wipht[i].getCountryCode();
				System.out.println("Country code is "+countrycode);
				
				String countryfullname=wipht[i].getCountryFullName();
				System.out.println("Country full name is "+countryfullname);
				
				LocalizedStringType[] lst=wipht[i].getHolidayName();
				for(int j=0;j<lst.length;j++)
				{
					String lang=lst[j].getLang();
					System.out.println("Language is "+lang);
					String text=lst[j].getText();
					System.out.println("Text is "+text);
				}
				System.out.println();
			}
		}
		catch(Exception ex)
		{
			System.out.println("No public holiday type");
		}
	}
}
