package SeleniumTestAutomation;

class ObjectType 
{
	private String sRefName = null;
	private String sObjectName = null;
	
	public String getObjectName() 
	{
		return sObjectName;
	}
	public void setObjectName(String ObjectName) 
	{
		sObjectName = ObjectName;
	}
	public String getRefName() 
	{
		return sRefName;
	}
	public void setRefName(String refName) 
	{
		sRefName = refName;
	}
	
	public boolean equals(Object obj) 
	{
		return this.sObjectName.equalsIgnoreCase(((ObjectType) obj).getRefName());
		
	}
}//Class ObjectType