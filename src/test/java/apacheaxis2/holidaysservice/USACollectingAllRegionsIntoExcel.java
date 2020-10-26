package apacheaxis2.holidaysservice;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apacheaxis2.holidaysservice.EnricoStub.GetSupportedCountries;
import apacheaxis2.holidaysservice.EnricoStub.GetSupportedCountriesResponse;
import apacheaxis2.holidaysservice.EnricoStub.SupportedCountriesType;
import apacheaxis2.holidaysservice.EnricoStub.SupportedCountryType;

public class USACollectingAllRegionsIntoExcel
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("usaallregions.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		//Sheet1
		Sheet sh1=wb.createSheet("Sheet1");
		Row r1=sh1.createRow(0);
		Cell c1=r1.createCell(0);
		c1.setCellValue("Month");
		//Create font for Headings
        Font font1=wb.createFont();
        font1.setColor(IndexedColors.BROWN.getIndex());
        font1.setBold(true);
        font1.setItalic(true);
        //Cell Style settings for Headings
		CellStyle cs1=wb.createCellStyle();
		cs1.setFont(font1);
		cs1.setAlignment(HorizontalAlignment.CENTER);
		sh1.getRow(0).getCell(0).setCellStyle(cs1);
		
		sh1.createRow(1).createCell(0).setCellValue("Jan");
		sh1.createRow(2).createCell(0).setCellValue("Feb");
		sh1.createRow(3).createCell(0).setCellValue("Mar");
		sh1.createRow(4).createCell(0).setCellValue("Apr");
		sh1.createRow(5).createCell(0).setCellValue("May");
		sh1.createRow(6).createCell(0).setCellValue("Jun");
		sh1.createRow(7).createCell(0).setCellValue("Jul");
		sh1.createRow(8).createCell(0).setCellValue("Aug");
		sh1.createRow(9).createCell(0).setCellValue("Sep");
		sh1.createRow(10).createCell(0).setCellValue("Oct");
		sh1.createRow(11).createCell(0).setCellValue("Nov");
		sh1.createRow(12).createCell(0).setCellValue("Dec");
		//Create font for data
		Font font2=wb.createFont();
		font2.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
		font2.setBold(true);
	    font2.setItalic(true);
	    //Cell Style settings for data
		CellStyle cs2=wb.createCellStyle();
		cs2.setFont(font2);
		cs2.setAlignment(HorizontalAlignment.CENTER);
		cs2.setFillPattern(FillPatternType.ALT_BARS);
		for(int k=1;k<=12;k++)
		{
			sh1.getRow(k).getCell(0).setCellStyle(cs2);
		}
		
		//Sheet2
		Sheet sh2=wb.createSheet("Sheet2");
		Row r2=sh2.createRow(0);
		Cell c2=r2.createCell(0);
		c2.setCellValue("Regions");
		sh2.getRow(0).getCell(0).setCellStyle(cs1);
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
			for(int i=0;i<sctryt.length;i++)
			{
				String x=sctryt[i].getCountryCode();
				if(x.equalsIgnoreCase("usa"))
				{
					try
					{
						String[] region=sctryt[i].getRegion();
						for(int j=0;j<region.length;j++)
						{
							sh2.createRow(j+1).createCell(0).setCellValue(region[j]);
							sh2.getRow(j+1).getCell(0).setCellStyle(cs2);
						}
					}
					catch(Exception exe)
					{
						System.out.println("No regions in supported country");
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("No country is supported");
		}
		
		//Save data back to excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fo.close();
		wb.close();
	}
}
