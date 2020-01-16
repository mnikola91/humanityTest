package humanityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private XSSFWorkbook wb = null;
	private XSSFSheet sheet = null;
	private String excellPath;

	public boolean setExcell(String path) 
	{
		if (wb != null) 
		{
			try 
			{
				wb.close();
			} 
			
			catch (Exception e) 
			{
				System.out.println(e.toString());
				return false;
			}
		}
		
		File f = new File(path);
		
		try 
		{
			FileInputStream fis = new FileInputStream(f);

			wb = new XSSFWorkbook(fis);
			excellPath = path;
			return true;
		} 
		
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("File is not opened correctly!");
			return false;
		}
	}

	public boolean setWorkSheet(int index) 
	{
		try 
		{
			sheet = wb.getSheetAt(index);
			return true;
		} 
		
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Worksheet is not opened properly!");
			return false;
		}
	}

	public String getDataAt(int row, int column) 
	{
		
		try 
		{
			XSSFRow r = sheet.getRow(row);
			XSSFCell cell = r.getCell(column);
			return cell.toString();
		} 
		
		catch (NullPointerException e) 
		{
			System.out.println(e.toString());
			System.out.println("Something is null?!");
		} 
		
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Error!");
		}
		return "";
	}

	public boolean setDataAt(int row, int column, String data) 
	{
		try {
			XSSFRow r = sheet.getRow(row);
			if (r == null) {
				r = sheet.createRow(row);
			}
			
			XSSFCell cell = r.getCell(column);
			
			if (cell == null) 
			{
				cell = r.createCell(column);
			}
			
			cell.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(excellPath);
			wb.write(fos);
			return true;
		} 
		
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Error!");
			return false;
		}
	}

	public int getRowNumber() 
	{
		try 
		{
			return sheet.getLastRowNum() + 1;
		} 
		
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Error!");
			return -1;
		}
	}

	public boolean closeExcell() 
	{
		if (wb != null) 
		{
			try 
			{
				wb.close();
				wb = null;
				return true;
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
				wb = null;
				return false;
			}

		}
		return true;
	}
}
