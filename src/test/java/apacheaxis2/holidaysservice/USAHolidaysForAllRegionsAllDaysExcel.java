package apacheaxis2.holidaysservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import apacheaxis2.holidaysservice.EnricoStub.DateType;
import apacheaxis2.holidaysservice.EnricoStub.IsHolidayType;
import apacheaxis2.holidaysservice.EnricoStub.IsPublicHoliday;
import apacheaxis2.holidaysservice.EnricoStub.IsPublicHolidayResponse;

public class USAHolidaysForAllRegionsAllDaysExcel 
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("usaallregionsholidays.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh1=wb.getSheet("Sheet1");
		int nour1=sh1.getPhysicalNumberOfRows();
		Sheet sh2=wb.getSheet("Sheet2");
		int nour2=sh2.getPhysicalNumberOfRows();
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		for(int i=1;i<nour2;i++)	//No of Regions loop
		{
			int nouc2=sh2.getRow(i).getLastCellNum();
			//Read data from excel
			DataFormatter df=new DataFormatter();
			String x=df.formatCellValue(sh2.getRow(i).getCell(0));
			System.out.println("Region \""+x+"\":");
			for(int j=1;j<nour1;j++)	//Month loop
			{
				String month=df.formatCellValue(sh1.getRow(j).getCell(0));
				if(month.equals("Jan") || month.equals("Mar") || month.equals("May") || month.equals("Jul") || month.equals("Aug") || month.equals("Oct") || month.equals("Dec"))
				{
					System.out.println(month);
					for(int k=1;k<=31;k++)	//Days in a month loop
					{
						//Create column headings
						if(i==1)
						{
							sh2.getRow(0).createCell(nouc2);
						}
						sh2.getRow(0).getCell(nouc2).setCellValue(month+" "+k);
						//Font settings for Headings
						Font font1=wb.createFont();
						font1.setColor(IndexedColors.BLUE.getIndex());
					    font1.setItalic(true);
					    font1.setBold(true);
					    //Cell Style settings for Headings
						CellStyle cs1=wb.createCellStyle();
						cs1.setFont(font1);
						cs1.setAlignment(HorizontalAlignment.CENTER);
						sh2.getRow(0).getCell(nouc2).setCellStyle(cs1);
						//Create objects to inner classes and send request
						IsPublicHoliday req=new IsPublicHoliday();
						DateType dt=new DateType();
						dt.setDay(k);
						dt.setMonth(j);
						dt.setYear(2020);
						req.setDate(dt);
						req.setCountry("usa");
						req.setRegion(x);
						//Get Response
						IsPublicHolidayResponse res=stub.isPublicHoliday(req);
						IsHolidayType iht=res.getIsPublicHoliday();
						if(iht.getIsHoliday())
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Holiday");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.RED.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						else
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Working Day");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.GREEN.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						sh2.autoSizeColumn(nouc2);
						nouc2++;
					}
				}
				else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
				{
					System.out.println(month);
					for(int k=1;k<=30;k++)
					{
						//Create column headings
						if(i==1)
						{
							sh2.getRow(0).createCell(nouc2);
						}
						sh2.getRow(0).getCell(nouc2).setCellValue(month+" "+k);
						//Font settings for Headings
						Font font1=wb.createFont();
						font1.setColor(IndexedColors.BLUE.getIndex());
					    font1.setItalic(true);
					    font1.setBold(true);
					    //Cell Style settings for Headings
						CellStyle cs1=wb.createCellStyle();
						cs1.setFont(font1);
						cs1.setAlignment(HorizontalAlignment.CENTER);
						sh2.getRow(0).getCell(nouc2).setCellStyle(cs1);
						//Create objects to inner classes and send request
						IsPublicHoliday req=new IsPublicHoliday();
						DateType dt=new DateType();
						dt.setDay(k);
						dt.setMonth(j);
						dt.setYear(2020);
						req.setDate(dt);
						req.setCountry("usa");
						req.setRegion(x);
						//Get Response
						IsPublicHolidayResponse res=stub.isPublicHoliday(req);
						IsHolidayType iht=res.getIsPublicHoliday();
						if(iht.getIsHoliday())
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Holiday");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.RED.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						else
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Working Day");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.GREEN.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						sh2.autoSizeColumn(nouc2);
						nouc2++;
					}
				}
				else if(month.equals("Feb"))
				{
					System.out.println(month);
					//For leap year 29 times and for non lead year change it to 28 times
					for(int k=1;k<=29;k++)
					{
						//Create column headings
						if(i==1)
						{
							sh2.getRow(0).createCell(nouc2);
						}
						sh2.getRow(0).getCell(nouc2).setCellValue(month+" "+k);
						//Font settings for Headings
						Font font1=wb.createFont();
						font1.setColor(IndexedColors.BLUE.getIndex());
					    font1.setItalic(true);
					    font1.setBold(true);
					    //Cell Style settings for Headings
						CellStyle cs1=wb.createCellStyle();
						cs1.setFont(font1);
						cs1.setAlignment(HorizontalAlignment.CENTER);
						sh2.getRow(0).getCell(nouc2).setCellStyle(cs1);
						//Create objects to inner classes and send request
						IsPublicHoliday req=new IsPublicHoliday();
						DateType dt=new DateType();
						dt.setDay(k);
						dt.setMonth(j);
						dt.setYear(2020);
						req.setDate(dt);
						req.setCountry("usa");
						req.setRegion(x);
						//Get Response
						IsPublicHolidayResponse res=stub.isPublicHoliday(req);
						IsHolidayType iht=res.getIsPublicHoliday();
						if(iht.getIsHoliday())
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Holiday");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.RED.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						else
						{
							sh2.getRow(i).createCell(nouc2).setCellValue("Working Day");
							//Font settings for Test Result
							Font font2=wb.createFont();
							font2.setColor(IndexedColors.GREEN.getIndex());
						    font2.setItalic(true);
						    //Cell Style settings for Test Result
							CellStyle cs2=wb.createCellStyle();
							cs2.setFont(font2);
							cs2.setAlignment(HorizontalAlignment.CENTER);
							sh2.getRow(i).getCell(nouc2).setCellStyle(cs2);
						}
						sh2.autoSizeColumn(nouc2);
						nouc2++;
					}
				}
				else
				{
					System.out.println("Given Month name is Wrong");
				}
			}	
		}
		
		//Save data back to excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fi.close();
		fo.close();
		wb.close();
	}
}
