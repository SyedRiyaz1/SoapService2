package apacheaxis2.holidaysservice;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.GetHolidaysForDateRange;
import apacheaxis2.holidaysservice.EnricoStub.GetHolidaysForDateRangeResponse;
import apacheaxis2.holidaysservice.EnricoStub.HolidayCollectionType;
import apacheaxis2.holidaysservice.EnricoStub.HolidayType;
import apacheaxis2.holidaysservice.EnricoStub.LocalizedStringType;

public class GetHolidaysForDateRangeFunctionality 
{
	public static void main(String[] args) throws Exception
	{
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		//Create objects to inner classes and send request
		GetHolidaysForDateRange req=new GetHolidaysForDateRange();
		DateType dt1=new DateType();
		dt1.setDay(1);
		dt1.setMonth(1);
		dt1.setYear(2020);
		DateType dt2=new DateType();
		dt2.setDay(31);
		dt2.setMonth(12);
		dt2.setYear(2020);
		req.setFromDate(dt1);
		req.setToDate(dt2);
		req.setCountry("usa");
		req.setRegion("mn");
		req.setHolidayType("public_holiday");
		//Get Response
		GetHolidaysForDateRangeResponse res=stub.getHolidaysForDateRange(req);
		HolidayCollectionType hct=res.getHolidays();
		try
		{
			HolidayType[] ht=hct.getHoliday();
			for(int i=0;i<ht.length;i++)
			{
				//1. Date
				DateType gd=ht[i].getDate();
				System.out.println("Date is: "+gd.getDay()+"-"+gd.getMonth()+"-"+gd.getYear());
				
				//2. Dateto
				try
				{
					DateType gdt=ht[i].getDateTo();
					System.out.println("Date to is: "+gdt.getDay()+"-"+gdt.getMonth()+"-"+gdt.getYear());
				}
				catch(Exception exe)
				{
					System.out.println(exe.getMessage());
				}
				
				//3. Observed On
				try
				{
					DateType goo=ht[i].getObservedOn();
					System.out.println("Observed on: "+goo.getDay()+"-"+goo.getMonth()+"-"+goo.getYear());
				}
				catch(Exception exe)
				{
					System.out.println(exe.getMessage());
				}
				
				//4. Name
				LocalizedStringType[] lst=ht[i].getName();
				for(int j=0;j<lst.length;j++)
				{
					String lang=lst[i].getLang();
					String text=lst[i].getText();
					System.out.println("Language is "+lang);
					System.out.println("Text is "+text);
				}
				
				//5. Flags
				try
				{
					String[] flags=ht[i].getFlags();
					System.out.print("Flags are: ");
					for(int k=0;k<flags.length;k++)
					{
						System.out.print(flags[k]+"\t");
					}
					System.out.println();
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				//6. Note
				try
				{
					LocalizedStringType[] lstnote=ht[i].getNote();
					for(int j=0;j<lstnote.length;j++)
					{
						String lang=lstnote[i].getLang();
						String text=lstnote[i].getText();
						System.out.println("Language is "+lang);
						System.out.println("Text is "+text);
					}
				}
				catch(Exception exec)
				{
					System.out.println(exec.getMessage());
				}
				
				//7. Holiday Type
				String ght=ht[i].getHolidayType();
				System.out.println("Holiday type is: "+ght);
				
				System.out.println();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Not holiday/Working day");
		}	
	}
}
