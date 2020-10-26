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

public class USAHolidaysForAllRegionsOnSpecifiedDayExcel 
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("usaallregions.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet2");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		//Create column headings
		sh.getRow(0).createCell(nouc).setCellValue("Holiday/Working Day");
		//Font settings for Headings
		Font font1=wb.createFont();
		font1.setColor(IndexedColors.BLUE.getIndex());
	    font1.setItalic(true);
	    font1.setBold(true);
	    //Cell Style settings for Headings
		CellStyle cs1=wb.createCellStyle();
		cs1.setFont(font1);
		cs1.setAlignment(HorizontalAlignment.CENTER);
		sh.getRow(0).getCell(nouc).setCellStyle(cs1);
		//Create object to Stub class
		EnricoStub stub=new EnricoStub();
		for(int i=1;i<nour;i++)
		{
			//Read data from excel
			DataFormatter df=new DataFormatter();
			String x=df.formatCellValue(sh.getRow(i).getCell(0));
			//Create objects to inner classes and send request
			IsPublicHoliday req=new IsPublicHoliday();
			DateType dt=new DateType();
			dt.setDay(4);
			dt.setMonth(7);
			dt.setYear(2020);
			req.setDate(dt);
			req.setCountry("usa");
			req.setRegion(x);
			//Get Response
			IsPublicHolidayResponse res=stub.isPublicHoliday(req);
			IsHolidayType iht=res.getIsPublicHoliday();
			if(iht.getIsHoliday())
			{
				sh.getRow(i).createCell(nouc).setCellValue("Holiday");
				//Font settings for Test Result
				Font font2=wb.createFont();
				font2.setColor(IndexedColors.RED.getIndex());
			    font2.setItalic(true);
			    //Cell Style settings for Test Result
				CellStyle cs2=wb.createCellStyle();
				cs2.setFont(font2);
				cs2.setAlignment(HorizontalAlignment.CENTER);
				sh.getRow(i).getCell(nouc).setCellStyle(cs2);
			}
			else
			{
				sh.getRow(i).createCell(nouc).setCellValue("Working Day");
				//Font settings for Test Result
				Font font2=wb.createFont();
				font2.setColor(IndexedColors.GREEN.getIndex());
			    font2.setItalic(true);
			    //Cell Style settings for Test Result
				CellStyle cs2=wb.createCellStyle();
				cs2.setFont(font2);
				cs2.setAlignment(HorizontalAlignment.CENTER);
				sh.getRow(i).getCell(nouc).setCellStyle(cs2);
			}
		}
		
		sh.autoSizeColumn(nouc);
		
		//Save data back to excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fi.close();
		fo.close();
		wb.close();
	}
}
