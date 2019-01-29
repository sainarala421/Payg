function ShowAll(butonType) 
{
	 var HeaderTh="";
	 var tblStr="";
	 var hpCount=0;
	 var hfCount=0;
	 var htCount=0;
	 var topTbl="";
	 var secondTbl="";
	 var Time="";
	 var Date="";
	 var TotalTC="";
	 var BV="";
	 var Passedpercentage="";
	 var Failedpercentage="";
	 var NotExecutedpercentage="";
     var i,j,k,m,l,oneRecord,Header;
     var data = xDoc.getElementsByTagName("Results")[0];
     Time=Time+xDoc.getElementsByTagName("Results")[0].getAttribute("Time");
     TotalTC=TotalTC+xDoc.getElementsByTagName("Results")[0].getAttribute("TotalTC");
     Date=Date+xDoc.getElementsByTagName("Results")[0].getAttribute("Date");
     BV=BV+xDoc.getElementsByTagName("Results")[0].getAttribute("BV");
     for (i = 0; i < data.childNodes.length; i++)
	 
    	 	{
    		
               	tblStr=tblStr+"<table id='gradient-style'  class ='sample' cellSpacing=0 cellPadding=0 width='1000' border=0 style='table-layout:fixed; width:1000px'>";
                oneRecord = data.childNodes[i];
        				var Header=oneRecord.getElementsByTagName("Header");
				for(k=0;k<Header.length;k++)
				{
					if(butonType == "Summary")
					{
						if(Header[k].getAttribute('Result')== "Pass")
						{
							tblStr=tblStr+"<tr class='dPass' ><td style='word-wrap: break-word' width='85'><font size=2px color=green>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
							tblStr=tblStr+"<td  width='85'style='word-wrap: break-word'></td>";						
							tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('Content')+"</font></td>";
							tblStr=tblStr+"<td  width='230'align='left' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
							
							tblStr=tblStr+"<td  width='230' align='left' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('ActualResult')+"</font></td>";
							tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('TCTime')+"</font></td>";
							tblStr=tblStr+"<td  width='50'  align='center' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('Result')+"</font></td>";
							tblStr+="</tr>";
							hpCount++;
						}
						if(Header[k].getAttribute('Result')== "Fail")
							{
								tblStr=tblStr+"<tr class='dFail' style='word-wrap: break-word'><td style='word-wrap: break-word' width='85'><font size=2px color=red>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
								tblStr=tblStr+"<td  width='85' style='word-wrap: break-word'></td>";						
								tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('Content')+"</font></td>";
								tblStr=tblStr+"<td  width='230' align='left' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
								tblStr=tblStr+"<td   width='230' align='left' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ActualResult')+"</font></td>";
								tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('TCTime')+"</font></td>";
								tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=red >"+Header[k].getAttribute('Result')+"</font></td>";
								tblStr+="</tr>";
								hfCount++;
							}	
						if(Header[k].getAttribute('Result')== "NotExecuted")
						{
							tblStr=tblStr+"<tr class='dne'><td style='word-wrap: break-word' width='85' ><font size=2px color=#FF8000>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
							tblStr=tblStr+"<td  width='85' ></td>";						
							tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Content')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ActualResult')+"</font></td>";
							tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('TCTime')+"</font></td>";
							tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Result')+"</font></td>";
							tblStr+="</tr>";}
					}
					else if(butonType == "Pass")
					   {
						if(Header[k].getAttribute('Result')== "Pass")
						{
							tblStr=tblStr+"<tr class='dPass'><td style='word-wrap: break-word' width='85' ><font size=2px color= #ff8000>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
							tblStr=tblStr+"<td  width='85'></td>";						
							tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=#ff8000>"+Header[k].getAttribute('Content')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#ff8000>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#ff8000>"+Header[k].getAttribute('ActualResult')+"</font></td>";
							tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=#ff8000>"+Header[k].getAttribute('TCTime')+"</font></td>";
							tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color= #ff8000 >"+Header[k].getAttribute('Result')+"</font></td>";
							tblStr+="</tr>";
							hpCount++;
						}
						if(Header[k].getAttribute('Result')== "Fail")
						{
								
								hfCount++;
						}	
						//tblStr=tblStr+xmlMicoxTree(oneRecord);
					    }
					else if(butonType == "NE")
					   {
						if(Header[k].getAttribute('Result')== "NotExecuted")
						{
							tblStr=tblStr+"<tr class='dne'><td style='word-wrap: break-word' width='85' ><font size=2px color=#FF8000>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
							tblStr=tblStr+"<td  width='85'></td>";						
							tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Content')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
							tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ActualResult')+"</font></td>";
							tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('TCTime')+"</font></td>";
							tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Result')+"</font></td>";
							tblStr+="</tr>";	//tblStr=tblStr+xmlMicoxTree(oneRecord);
							
						}
						if(Header[k].getAttribute('Result')== "Pass")
						{
							hpCount++;
						
						}	
						if(Header[k].getAttribute('Result')== "Fail")
						{
						
							hfCount++;
						}
					    }
					else if(butonType == "Fail")
					   {
						if(Header[k].getAttribute('Result')== "Fail")
						{
									tblStr=tblStr+"<tr class='dFail'><td style='word-wrap: break-word' width='85' ><font size=2px color=red>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
									tblStr=tblStr+"<td  width='85'></td>";						
									tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('Content')+"</font></td>";
									tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
									tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ActualResult')+"</font></td>";
									tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('TCTime')+"</font></td>";
									tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=red >"+Header[k].getAttribute('Result')+"</font></td>";
									tblStr+="</tr>";
									hfCount++;
									//tblStr=tblStr+xmlMicoxTree(oneRecord);
							}	
						if(Header[k].getAttribute('Result')== "Pass")
						{
							hpCount++;
							}
						
						}
					
								else if(butonType == "ShowAll")
						   {
							if(Header[k].getAttribute('Result')== "Pass")
							{
								tblStr=tblStr+"<tr class='dPass'><td style='word-wrap: break-word' width='85' ><font size=2px color=green>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
								tblStr=tblStr+"<td  width='85'></td>";						
								tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('Content')+"</font></td>";
								tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
								tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('ActualResult')+"</font></td>";
								tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=green>"+Header[k].getAttribute('TCTime')+"</font></td>";
								tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=green >"+Header[k].getAttribute('Result')+"</font></td>";
								tblStr+="</tr>";
								hpCount++;
							}
							if(Header[k].getAttribute('Result')== "Fail")
							{
									tblStr=tblStr+"<tr class='dFail'><td style='word-wrap: break-word' width='85' ><font size=2px color=red>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
									tblStr=tblStr+"<td  width='85'></td>";						
									tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('Content')+"</font></td>";
									tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
									tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('ActualResult')+"</font></td>";
									tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=red>"+Header[k].getAttribute('TCTime')+"</font></td>";
									tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=red >"+Header[k].getAttribute('Result')+"</font></td>";
									tblStr+="</tr>";
									hfCount++;
							}	
							if(Header[k].getAttribute('Result')== "NotExecuted")
							{
								tblStr=tblStr+"<tr class='dne'><td style='word-wrap: break-word' width='85' ><font size=2px color=#FF8000>"+data.childNodes[i].getAttribute("ID")+"</font></td>";
								tblStr=tblStr+"<td  width='85' style='word-wrap: break-word'></td>";						
								tblStr=tblStr+"<td  width='200' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Content')+"</font></td>";
								tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ExpectedResult')+"</font></td>";
								tblStr=tblStr+"<td  width='230' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('ActualResult')+"</font></td>";
								tblStr=tblStr+"<td  width='75' align='right' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('TCTime')+"</font></td>";
								tblStr=tblStr+"<td  width='50' align='center' style='word-wrap: break-word'><font size=2px color=#FF8000>"+Header[k].getAttribute('Result')+"</font></td>";
								tblStr+="</tr>";	//tblStr=tblStr+xmlMicoxTree(oneRecord);
								
							}
							tblStr=tblStr+xmlMicoxTree(oneRecord);
						    }					
									htCount=htCount+parseInt(Header[k].getAttribute('TCTime'));
									
									
			}		
    }
     var NotExecuted=TotalTC-(hpCount+hfCount); 
     
     htCount=(htCount/60).toFixed(1);
    tblStr=tblStr+" </tbody></table>";
	HeaderTh=HeaderTh+"<table bgColor='#58ACFA' cellSpacing=5 cellPadding=0 width='1000' border=0>";
	HeaderTh=HeaderTh+"<thead><tr ><th width='85'  size=2px  align='left'><font color='white'>TC_ID</font></th><th width='85' size=2px  align='center'><font color='white'>Step/Exp</font></th><th width='230' size=2px  align='center'><font color='white'>TC Description</font></th><th width='230' size=2px  align='center'><font color='white'>Expected Result</font></th><th width='230'  size=2px  align='center'><font color='white'>Actual Result</font></th><th width='75' size=2px scope='col' align='center'><font color='white'>Time</font></th><th width='50' size=2px  align='center'><font color='white'>Result</font></th></tr></thead></tbody>";
	HeaderTh=HeaderTh+"</table>";
	tblStr=HeaderTh+tblStr;

	function drawVisualization()
	{
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'TestCase');
		data.addColumn('number', 'RESULT');
		
		data.addRows([
		              
		             ['Passed TestCases',{v:hpCount}],
		             ['Failed TestCases',{v:hfCount}],
		             ['Not Executed',{v:NotExecuted}]
		 			 
		 			]);		 			        
		new google.visualization.PieChart(
		document.getElementById('visualization')).
		draw(data, { is3D:true});
	}
	google.setOnLoadCallback(drawVisualization);
	if(butonType== "Summary")
	{
	 	document.getElementById('Summary').style.display ='block';
		document.getElementById('Pass').style.display ='none';
		document.getElementById('Fail').style.display ='none';
		document.getElementById('ShowAll').style.display ='none';
		var divCtrl = document.getElementById('Summary');
		divCtrl.innerHTML = tblStr;
	}  
	if(butonType== "Pass")
	{document.getElementById('Summary').style.display ='none';
		document.getElementById('Summary').style.display ='none';
		document.getElementById('Pass').style.display ='block';
		document.getElementById('Fail').style.display ='none';
		document.getElementById('ShowAll').style.display ='none';
		var divCtrl = document.getElementById('Pass');
		divCtrl.innerHTML = tblStr;
	}
	if(butonType== "Fail")
	{document.getElementById('Summary').style.display ='none';
		document.getElementById('Summary').style.display ='none';
		document.getElementById('Pass').style.display ='none';
		document.getElementById('Fail').style.display ='block';
		document.getElementById('ShowAll').style.display ='none';
		var divCtrl = document.getElementById('Fail');
		divCtrl.innerHTML = tblStr;
	}
	if(butonType== "NE")
	{document.getElementById('Summary').style.display ='none';
		document.getElementById('Summary').style.display ='none';
		document.getElementById('Pass').style.display ='none';
		document.getElementById('Fail').style.display ='block';
		document.getElementById('ShowAll').style.display ='none';
		var divCtrl = document.getElementById('Fail');
		divCtrl.innerHTML = tblStr;
	}
	if(butonType== "ShowAll")
	{document.getElementById('Summary').style.display ='none';
		document.getElementById('Summary').style.display ='none';
		document.getElementById('Pass').style.display ='none';
		document.getElementById('Fail').style.display ='none';
		document.getElementById('ShowAll').style.display ='block';
		var divCtrl = document.getElementById('ShowAll');
		if( divCtrl)
		{
							
			divCtrl.innerHTML = tblStr;
		}
	}
	Passedpercentage=Passedpercentage+(hpCount/TotalTC*100).toFixed(1);
	Failedpercentage=Failedpercentage+(hfCount/TotalTC*100).toFixed(1);
	NotExecutedpercentage=NotExecutedpercentage+(NotExecuted/TotalTC*100).toFixed(1);
	topTbl="<table id='newspaper-b' bgcolor=#F2F2F2 cellSpacing=2 cellPadding=2 width=500 border=0>";
	topTbl=topTbl+"<tr><td>"+"Build Version</td><td>:&nbsp &nbsp"+BV+"</td></tr>";
	topTbl=topTbl+"<tr><td>"+"Date of Test Execution</td><td>:&nbsp &nbsp"+Date+"</td></tr>";
	topTbl=topTbl+"<tr><td>"+"Time of Test Execution</td><td>:&nbsp &nbsp"+Time+"</td></tr>";
	topTbl=topTbl+"<tr><td>"+"Total No.of.TestCases</td><td>:&nbsp &nbsp<b><font  color='blue'>"+TotalTC+"</font></td></tr>";
	topTbl=topTbl+"<tr><td>"+"Passed TestCases</td><td>:&nbsp &nbsp<b><font  color='green'>"+hpCount+"&nbsp("+Passedpercentage+"%)</font></td></tr>";
	topTbl=topTbl+"<tr><td>"+"Failed TestCases</td><td>:&nbsp &nbsp<b><font  color='red'>"+hfCount+"&nbsp("+Failedpercentage+"%)</font></td></tr>";
	topTbl=topTbl+"<tr><td>"+"Not Executed</td><td>:&nbsp &nbsp<b><font  color='#FF8000'>"+NotExecuted+"&nbsp("+NotExecutedpercentage+"%)</font></td></tr>";
	topTbl=topTbl+"<tr><td>"+"Total Execution Time</td><td>:&nbsp &nbsp"+htCount+"&nbspmin</td></tr>";
	topTbl=topTbl+"</table>";
	var calc = document.getElementById('calc');
	calc.innerHTML = topTbl;
	
	}
function xmlMicoxTree(xmlNode,Step,Exp)
{ //var first=rootTagNo+1;
	var tempStr="";
	var Step=0;
	var Exp=0;

	for(nd=0;nd<xmlNode.childNodes.length;nd++)
    {
			
		var curNode=xmlNode.childNodes[nd];
		
		if(curNode.nodeType==1)
			if(nd%2==0)
				{
					tempStr=tempStr+"<tr class='d1'>";
					tempStr=tempStr+displayAtrib(curNode,Step,Exp);    
					tempStr=tempStr+"</tr>"; 
					if(curNode.nodeName=="Step")
					{
						Step++;
					}
					if(curNode.nodeName=="Expected")
					{
						Exp++;
					}
				}
			else
				{
	      		tempStr=tempStr+"<tr class='d0'>";
				tempStr=tempStr+displayAtrib(curNode,Step,Exp);    
				tempStr=tempStr+"</tr>"; 
				
				if(curNode.nodeName=="Step")
					{
					Step++;
					}
				if(curNode.nodeName=="Expected")
					{
						Exp++;
					}
				} 
	}  
	
return tempStr;
}

function displayAtrib(chNode,Sid,Eid)
{ 
	var tStr="";
	var conStr="";
	var Result="";
	var Path="";
	var ActualResult="";
	var ExpectedResult="";
	Stepid=Sid+1;
	Expid=Eid+1;

		if (chNode.nodeName == "Step")
		{	

			for(i=0;i<chNode.attributes.length;i++)
				{	
						if (chNode.attributes[i].nodeName=="StepResult")
						{
							if (chNode.attributes[i].nodeValue=="Pass")
							Result=Result+"<td   width='50' align='center' style='word-wrap: break-word'><font size=2px color='green'>"+chNode.attributes[i].nodeValue + "</font></td>";
							else
							Result=Result+"<td   width='50' align='center' style='word-wrap: break-word'><font size=2px color='red'>"+chNode.attributes[i].nodeValue + "</font></td>";
						}
						else if (chNode.attributes[i].nodeName=="ActualResult")
						{
							str=chNode.attributes[i].nodeValue;
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)	
							ActualResult=ActualResult+"<td  width='230' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
						}	
						else if (chNode.attributes[i].nodeName=="Path")
						{
							
								if (chNode.attributes[i].nodeValue!="")
									Path=Path+"<td   width='85' style='word-wrap: break-word'><center><A href="+chNode.attributes[i].nodeValue+" > <img align='center' src="+chNode.attributes[i].nodeValue+"   width='25'></img></A></center></td>";
								else
									Path=Path+"<td  width='85' style='word-wrap: break-word'></td>";
						}
						else if (chNode.attributes[i].nodeName=="ExpectedResult")
						{
							str=chNode.attributes[i].nodeValue;	
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)
							ExpectedResult=ExpectedResult+"<td  width='230' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
						}
						else if (chNode.attributes[i].nodeName=="Content")
							{
							str=chNode.attributes[i].nodeValue;	
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)
								conStr=conStr+"<td  width='200' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
							}	
											
						else
							tStr=tStr+"<td align='right'  width='75' style='word-wrap: break-word'><font size=2px >"+chNode.attributes[i].nodeValue + "</font></td>";
					}
			tStr=Path+"</td><td   width='85' align='center' style='word-wrap: break-word'><font size=2px >Step</font>"+Stepid+conStr+ExpectedResult+ActualResult+tStr+Result;
			
		}			
		if (chNode.nodeName == "Expected")
		{		
			for(i=0;i<chNode.attributes.length;i++)
				{
					if (chNode.attributes[i].nodeName=="ExpResult")
						{
							if (chNode.attributes[i].nodeValue=="Pass")
								Result=Result+"<td  width='50'  align='center' style='word-wrap: break-word'><font size=2px color='green'>"+chNode.attributes[i].nodeValue + "</font></td>";
								else
									Result=Result+"<td   width='50' align='center' style='word-wrap: break-word'><font size=2px color='red'>"+chNode.attributes[i].nodeValue + "</font></td>";
						}
					else if (chNode.attributes[i].nodeName=="ActualResult")
					{
							str=chNode.attributes[i].nodeValue;	
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)	
							
							ActualResult=ActualResult+"<td  width='230' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
					}
					else if (chNode.attributes[i].nodeName=="Path")
					{
						
							if (chNode.attributes[i].nodeValue!="")
								Path=Path+"<td   width='85' style='word-wrap: break-word'><center><A href="+chNode.attributes[i].nodeValue+" > <img align='center' src="+chNode.attributes[i].nodeValue+"   width='25'></img></A></center></td>";
							else
								Path=Path+"<td  width='85' style='word-wrap: break-word'></td>";
					}
					else if (chNode.attributes[i].nodeName=="ExpectedResult")
					{
							str=chNode.attributes[i].nodeValue;	
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)
							ExpectedResult=ExpectedResult+"<td  width='230' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
					}
					else if (chNode.attributes[i].nodeName=="Content")
					{
							str=chNode.attributes[i].nodeValue;
							var first=str.split("'");
							var last=str.split("'").reverse();
							var pos=str.indexOf("'")
							var pos1=str.lastIndexOf("'")
							var test=str.substring(pos,pos1)
							conStr=conStr+"<td  width='200' style='word-wrap: break-word'><font size=2px >"+first[0]+"<font color='#000000'>"+test+"'</font>"+last[0]+"</td>";
						}		
				else				
							tStr=tStr+"<td align='right'  width='75' style='word-wrap: break-word'><font size=2px >"+chNode.attributes[i].nodeValue + "</font></td>";
				}
			tStr=Path+"</td><td   width='85'align='center' style='word-wrap: break-word'><font size=2px >Expected"+Expid+"</font></td>"+conStr+ExpectedResult+ActualResult+tStr+Result;
		}
return tStr;
}
var xDoc;
//verify that browser supports XML features and load external .xml file
function verifySupport(xFile)
{
	    if (document.implementation && document.implementation.createDocument) {
     // this is the W3C DOM way, supported so far only in NN6+
     xDoc = document.implementation.createDocument("", "theXdoc", null);
 } else if (typeof ActiveXObject != "undefined") {
     // make sure real object is supported (sorry, IE5/Mac)
     if (document.getElementById("msxml").async) {
         xDoc = new ActiveXObject("Msxml.DOMDocument");
         
     }
 }
 if (xDoc && typeof xDoc.load != "undefined") {
     // load external file (from same domain)
     xDoc.load(xFile);
     
     return true;
 } else {
     var reply = confirm("This example requires a browser with XML support, " +
         "such as IE5+/Windows or Netscape 6+.\n \nGo back to previous page?");
     if (reply) {
         history.back();
              }
 }
 return false;
 
}
function init(xFile) {
    // confirm browser supports needed features and load .xml file
    if (verifySupport(xFile)) {
        // let file loading catch up to execution thread
        setTimeout("");
    }
}