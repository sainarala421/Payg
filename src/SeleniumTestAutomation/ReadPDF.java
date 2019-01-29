package SeleniumTestAutomation;

import java.io.*;
import com.lowagie.text.pdf.*;

	public class ReadPDF 
	{
			public int searchPDF(String pattern, String filePath) throws IOException 
			{
					int flag=0;
	                try 
	                {
	                    System.out.println("Wait.....");
	                	System.out.println("Reading PDF file in the path -->" + filePath); 
	                	PdfReader reader = new PdfReader(filePath);
	                     int lines=reader.getNumberOfPages();
	             //        System.out.println("Number of pages: "+lines);
	                     for(int i=1;i<=lines;i++)
	                     {
	                       	PdfDictionary dictionary = reader.getPageN(i);
	                        PRIndirectReference reference = (PRIndirectReference) dictionary
	                                        .get(PdfName.CONTENTS);
	                        PRStream stream = (PRStream) PdfReader.getPdfObject(reference);
	                        byte[] bytes = PdfReader.getStreamBytes(stream);
	                        PRTokeniser tokenizer = new PRTokeniser(bytes);
	                        StringBuffer buffer = new StringBuffer();
	                        while (tokenizer.nextToken()) 
	                        {
	                                if (tokenizer.getTokenType() == PRTokeniser.TK_STRING)
	                                {
	                                        buffer.append(tokenizer.getStringValue()+"");
	                                }
	                                
	                        }
	                        String test = buffer.toString();
	           
	                        if((test.toUpperCase()).contains((pattern.toUpperCase()))){flag=1;}
	                      }
	                } catch (Exception e) {}
	               return flag; 
	        }

	}


