package SeleniumTestAutomation;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class FileFormatCheck 
{
	ArrayList <String> pathlist= new ArrayList<String>(); 
	JOptionPane optionPane;
	public String testfile = null;
	public void checkxml() throws Exception
	{
		pathlist.add(Configure.inputfile);
		pathlist.add(Configure.objectfile);
		//System.out.println("before " + pathlist.size());
		for (int pathcnt = 0; pathcnt<=pathlist.size()-1;pathcnt++)
		{
			System.out.println("path size " +pathlist.size());
			File file = new File(pathlist.get(pathcnt));
			if (file.exists())
			{
				try 
				{
				    DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				    DocumentBuilder builder = dBF.newDocumentBuilder();      
					Document doc = builder.parse(file);
				}
				catch (Exception e) 
				{
					testfile = e.getMessage();
				    if (testfile != null)
				    {
				    	JOptionPane pane = new JOptionPane(file +" file is in incorrect xml format : "+ testfile);
				    	JDialog dialog = pane.createDialog(new JFrame(), "XML Format Alert");
				    	dialog.show();
				    	System.exit(0);				        	    
				    }				        	
				}					
			}
			else
			{
				JOptionPane pane = new JOptionPane(file +" file not found");
		        JDialog dialog = pane.createDialog(new JFrame(), "File Not Found Alert");
		        dialog.show();
		        System.exit(0);	
			}
		}
	}
}
 

