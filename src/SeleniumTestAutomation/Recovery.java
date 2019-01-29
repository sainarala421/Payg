package SeleniumTestAutomation;

import java.io.File;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Recovery {
	public static Document rdocInput = null;
	static TestCase TC=new TestCase();
	static ObjectRepository objxml = new ObjectRepository();
	static ObjectType ot = null;
	static String recAction;
	static String recVal;
	static StepValidate objStep=new StepValidate();
	public static void callRecovery()
	{		
		try
		{
			System.out.println("Inside recovery");
		PrintWriter pw=new PrintWriter(System.out, true);
		File f=new File(Configure.inputfile);
		DocumentBuilderFactory rdbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder rdb=rdbf.newDocumentBuilder();
		rdocInput=rdb.parse(f);
		rdocInput.getDocumentElement().normalize();
		NodeList inputNode=rdocInput.getChildNodes();
		for(int icounter=0; icounter<inputNode.getLength(); icounter++)
		{
			Node recInputNode=inputNode.item(icounter);
			if(recInputNode.getNodeType()==Node.ELEMENT_NODE)
			{
				pw.println("input node name = " + recInputNode.getNodeName());
				NodeList projNode=recInputNode.getChildNodes();
				for(int projCounter=0; projCounter<projNode.getLength(); projCounter++)
				{
					Node pNode=projNode.item(projCounter);
					if(pNode.getNodeType()==Node.ELEMENT_NODE)
					{
						pw.println("project node = " + pNode.getNodeName());
						NodeList modNode=pNode.getChildNodes();
						for (int modCounter=0;modCounter<modNode.getLength();modCounter++)
						{
							Node mNode=modNode.item(modCounter);
							if(mNode.getNodeType()==Node.ELEMENT_NODE)	
							{
								pw.println("module node = " + mNode.getNodeName());
								NodeList recNode=mNode.getChildNodes();
								for(int recCount=0; recCount<recNode.getLength();recCount++)
								{
									Node rNode=recNode.item(recCount);
									if (rNode.getNodeType()==Node.ELEMENT_NODE)
									{
										pw.println("recovery name = " + rNode.getNodeName());
										if(rNode.getNodeName().equalsIgnoreCase("Recovery"))
										{
											NodeList actRecNode=rNode.getChildNodes();
											for(int actCounter=0; actCounter<actRecNode.getLength(); actCounter++)
											{
												Node aNode=actRecNode.item(actCounter);
												if(aNode.getNodeType()==Node.ELEMENT_NODE)
												{
													pw.println("rec name = " + aNode.getNodeName());
													if(aNode.getNodeName().equalsIgnoreCase("loginfunction"))
													{
														NodeList stepRecNode=aNode.getChildNodes();
														for(int stepCounter=0; stepCounter<stepRecNode.getLength();stepCounter++)
														{
															Node sNode=stepRecNode.item(stepCounter);
															if(sNode.getNodeType()==Node.ELEMENT_NODE)
															{
																recAction=sNode.getAttributes().getNamedItem("Action").getNodeValue();
																recVal=sNode.getAttributes().getNamedItem("Val").getNodeValue();
																ot = objxml.getObjectName(sNode.getAttributes().getNamedItem("RefObj").getNodeValue());
																TC.checkRecovery=true;
																//TC.navigate(recAction, recVal, ot);
																boolean result=objStep.exp(ot.getObjectName(),recAction,recVal, Session.sel);
																String actResult;
																String Time = "Recovery";
																String Path = null;
																String sValue = null;
																if (result == true)
																{
																	actResult="Pass";
																}
																else
																{
																	actResult="Fail";
																}
																
																XMLResultsModifier.XmlModifier(recAction,ot.getRefName(), ot.getObjectName(),"Step", recVal, StepValidate.StepActual, actResult, Time, Path, sValue);
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
					}
				}
			}			
		}
	}
	catch(Exception e)
	{
		System.out.println("Exception : " + e.getMessage());
	}
	}
}
