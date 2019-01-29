package SeleniumTestAutomation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class ObjectRepository 
{
	Configure parserObj = new Configure();
	static ArrayList<ObjectType> listObjectType = null;
	static ArrayList<String> ObjectNamelist = null;
	public Document docObject = null;
	
	public String loadObjectDetails() throws ParserConfigurationException, SAXException, IOException 
	{
		File xmlObjectFile = new File(Configure.objectfile);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
		DocumentBuilder db = dbf.newDocumentBuilder();//Defines the API to obtain DOM Document instances from an XML document. Using this class, an application programmer can obtain a Document from XML.
		docObject = db.parse(xmlObjectFile);
		docObject.getDocumentElement().normalize();
		try 
		{
			listObjectType = new ArrayList<ObjectType>();
			ObjectNamelist = new ArrayList<String>();
			NodeList ObjRefLst = docObject.getChildNodes();
			for (int ObjRef = 0; ObjRef < ObjRefLst.getLength(); ObjRef++) 
			{
				Node ObjRefLstNode = ObjRefLst.item(ObjRef);
				if (ObjRefLstNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					NodeList ModulenameLst = ObjRefLstNode.getChildNodes();
					for (int Modulename = 0; Modulename < ModulenameLst.getLength(); Modulename++) 
					{
						Node ModulenameLstNode = ModulenameLst.item(Modulename);
						if (ModulenameLstNode.getNodeType() == Node.ELEMENT_NODE) 
						{
							NodeList ObjLst = ModulenameLstNode.getChildNodes();
							for (int Obj = 0; Obj < ObjLst.getLength(); Obj++) 
							{
								Node ObjLstNode = ObjLst.item(Obj);
								if (ObjLstNode.getNodeType() == Node.ELEMENT_NODE) 
								{
									ObjectType obj = new ObjectType();
									obj.setObjectName(getAttribute(ObjLstNode,"ObjName"));
									obj.getObjectName().replaceAll("&apos;","'");
									obj.getObjectName().replaceAll("&amp;","&");
									obj.getObjectName().replaceAll("&lt;","<");
									obj.getObjectName().replaceAll("&gt;",">");
									//obj.getObjectName().replaceAll("&quot;","");
									obj.setRefName(getAttribute(ObjLstNode,"RefName"));
									listObjectType.add(obj);
									ObjectNamelist.add(obj.getRefName());
									//System.out.println("shan"+obj.getRefName());
								}
							}//Obj for
						}
					}//Modulename for
				}
			}//ObjRef for
		}     
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}//loadObjectDetails

	public static String getAttribute(Node node, String attribute) 
	{
		Node namedItem = node.getAttributes().getNamedItem(attribute);
		if (namedItem != null) 
		{
			return namedItem.getNodeValue();
		}
		return null;
	}
	
	public ObjectType getObjectName(String sObjectName) 
	{
		ObjectType obj = new ObjectType();
		obj.setObjectName(sObjectName);
		try
		{
			ObjectType objtyp = (ObjectType) listObjectType.get(listObjectType.indexOf(obj));
			return objtyp;
		}
	catch(ArrayIndexOutOfBoundsException ex)
	{
		ex.printStackTrace();
		System.out.println("Error for ObjectType: "+ sObjectName);
	}
	return null;
	}

}//Class ObjectRepository
