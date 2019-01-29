//Author: Shankar
//File Name: Compare.java

package SeleniumTestAutomation;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Compare extends ObjectRepository
{
	public Document docInput = null;
	public String nameModule,nameApplication,Action;
	public int test=0;
	ObjectType objtype = new ObjectType();

	public void compareXML() 
	{
		try
		{
		File xmlInputFile = new File(Configure.inputfile);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		docInput = db.parse(xmlInputFile);
		docInput.getDocumentElement().normalize();
		try 
		{
			
			NodeList inputLst = docInput.getChildNodes();
			for (int input = 0; input < inputLst.getLength(); input++) 
			{
				Node inputLstNode = inputLst.item(input);
				if (inputLstNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					NodeList ApplicationLst = inputLstNode.getChildNodes();
					for (int Application = 0; Application < ApplicationLst.getLength(); Application++) 
					{
						Node ApplicationLstNode = ApplicationLst.item(Application);
						nameApplication = ApplicationLst.item(Application).getNodeName();
						if (ApplicationLstNode.getNodeType() == Node.ELEMENT_NODE) 
						{
							
							NodeList ModulenameLst = ApplicationLstNode.getChildNodes();
							
							for (int Modulename = 0; Modulename < ModulenameLst.getLength(); Modulename++) 
							{
								Node ModulenameLstNode = ModulenameLst.item(Modulename);
								nameModule = ModulenameLst.item(Modulename).getNodeName();
								if (ModulenameLstNode.getNodeType() == Node.ELEMENT_NODE) 
								{
									NodeList TCLst = ModulenameLstNode.getChildNodes();
									for (int TC = 0; TC < TCLst.getLength(); TC++) 
									{
										Node TCLstNode = TCLst.item(TC);
										if (TCLstNode.getNodeType() == Node.ELEMENT_NODE) 
										{
											NodeList StepLst = TCLstNode.getChildNodes();
											for (int Step = 0; Step < StepLst.getLength(); Step++) 
											{
												Node StepLstNode = StepLst.item(Step);
												if (StepLstNode.getNodeType() == Node.ELEMENT_NODE) 
												{
													objtype.setRefName(getAttribute(StepLstNode,"RefObj"));
													if(StepLstNode.getNodeName().equalsIgnoreCase("Step"))
													{
														Action = getAttribute(StepLstNode,"Action");
														if(Action.indexOf("include") != 0)
														{
														if (ObjectNamelist.contains(objtype.getRefName()))
														{
														}
														else
														{
															if(test==0)
															{
																System.out.println("Reference Name not available:");
															}
														
															System.out.println(objtype.getRefName());
															test = test+1;
														}
														}
													}

												}

											}
										}
									}
									
								}
								
							}
	
							
						}
						if (test != 0)
						{
							System.exit(0);
						}
						
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		}
		catch (Exception exp)
		{
			System.out.println("exception:::::"+exp.getMessage());
		}
	}
	
	public static String getAttribute(Node node, String attribute) 
	{
		Node namedItem = node.getAttributes().getNamedItem(attribute);
		if (namedItem != null) 
		{
			return namedItem.getNodeValue();
		}
		return null;
	}
}// Class Compare