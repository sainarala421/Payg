package SeleniumTestAutomation;


import java.io.IOException;

import org.jawin.DispatchPtr;
import org.jawin.win32.Ole32;

public class ReadExcel 
{
	public int searchxls(String pattern, String filePath) throws IOException 
	{
		int flag=0;
		try
		{
		System.out.println("Wait.....");
        System.out.println("Reading Excel file in the path -->" + filePath); 
		Ole32.CoInitialize();
		DispatchPtr app = new DispatchPtr("Excel.Application");
		app.put("Visible", 0);
		DispatchPtr books = (DispatchPtr)app.get("Workbooks");
		DispatchPtr book =(DispatchPtr) books.invoke("Open",filePath);
		DispatchPtr sheets = (DispatchPtr)book.get("Worksheets");
		DispatchPtr sheet = (DispatchPtr)sheets.get("Item", new Integer(1));
		DispatchPtr range = (DispatchPtr)sheet.get("Range", "A1:S13");
		int count= (Integer)range.get("Count");
	//	System.out.println("count"+count);
	//	System.out.println("Pattern : "+pattern);
		for(int i=1;i<=count;i++)
		{
		DispatchPtr range2 = (DispatchPtr)range.get("Item", new Integer(i));
		String value=(String)range2.get("Formula");
	//	System.out.println("value=" + value);
		if(value.equalsIgnoreCase(pattern))
		flag=1;
		}
		app.invoke("Quit");
		Ole32.CoUninitialize();}
		catch(Exception e){System.out.println("Exception: "+e);}
		return flag;
	}

}
