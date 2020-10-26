package apacheaxis2.holidaysservice;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.GetSupportedCountries;
import apacheaxis2.holidaysservice.EnricoStub.GetSupportedCountriesResponse;
import apacheaxis2.holidaysservice.EnricoStub.SupportedCountriesType;
import apacheaxis2.holidaysservice.EnricoStub.SupportedCountryType;

public class GetSupportedCountriesFunctionality
{
	public static void main(String[] args) throws Exception
	{
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		//Create objects to inner classes and send request
		GetSupportedCountries req=new GetSupportedCountries();
		//Get response
		GetSupportedCountriesResponse res=stub.getSupportedCountries(req);
		SupportedCountriesType sct=res.getSupportedCountries();
		try
		{
			SupportedCountryType[] sctryt=sct.getSupportedCountry();
			System.out.println("Supported countries are:");
			System.out.println();
			for(int i=0;i<sctryt.length;i++)
			{
				String fullname=sctryt[i].getFullName();
				System.out.println("Full name is "+fullname);
				
				String countrycode=sctryt[i].getCountryCode();
				System.out.println("Country code is "+countrycode);
				
				try
				{
					String[] region=sctryt[i].getRegion();
					System.out.print("Regions are: ");
					for(int j=0;j<region.length;j++)
					{
						System.out.print(region[j]+"\t");
					}
					System.out.println();
				}
				catch(Exception exe)
				{
					System.out.println("No regions in supported country");
				}
				
				DateType fromdate=sctryt[i].getFromDate();
				int day1=fromdate.getDay();
				int month1=fromdate.getMonth();
				int year1=fromdate.getYear();
				System.out.println("From date is: "+day1+"-"+month1+"-"+year1);
				
				DateType todate=sctryt[i].getToDate();
				int day2=todate.getDay();
				int month2=todate.getMonth();
				int year2=todate.getYear();
				System.out.println("To date is: "+day2+"-"+month2+"-"+year2);
				
				try
				{
					String[] holidaytype=sctryt[i].getHolidayType();
					System.out.print("Holiday Types are: ");
					for(int k=0;k<holidaytype.length;k++)
					{
						System.out.print(holidaytype[k]+"\t");
					}
					System.out.println();
				}
				catch(Exception exe)
				{
					System.out.println("No holiday type in "+sctryt[i]);
				}
				System.out.println();
			}
		}
		catch(Exception ex)
		{
			System.out.println("No country is supported");
		}
	}
}
