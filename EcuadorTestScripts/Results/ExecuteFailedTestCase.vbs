Call executeFailedTestCases() 
Sub executeFailedTestCases()
Set xlApp = CreateObject("Excel.Application")
Set fso = CreateObject("Scripting.FileSystemObject")
Set Checkdict = CreateObject("Scripting.Dictionary")
xmlFile = fso.GetAbsolutePathName(".") & "\Resultlog_firefox.xml"
Set xmlDoc = CreateObject("Msxml2.DOMDocument")
xmlDoc.load(xmlFile)
Set testcaseList = xmlDoc.getElementsByTagName("Header")
failcount=1
For tcCount=0 to testcaseList.length-1
	IF testcaseList.Item(tcCount).Attributes(3).Text="Fail" Then
		Checkdict.Add trim(xmlDoc.getElementsByTagName("TC").Item(tcCount).Attributes(0).Text), failcount
		failcount=failcount+1
	End If
Next
If Checkdict.Count=0 Then
	Msgbox "No Failed Cases", 0, "MESSAGE"
	Exit Sub
End If
configFilepath=split(fso.GetAbsolutePathName("."), "\Results")(0) & "\HTMLtoExcel_Converter\config.txt"
Set Checkfile = fso.OpenTextFile(configFilepath, 1)
checkRow = 0
Do Until Checkfile.AtEndOfStream
	CheckLine = Checkfile.Readline
	If (InStr(CheckLine, "=")) <> 0 Then
		CheckMyArray = Split(CheckLine, "=", -1, 1)
        Checkdict.Add CheckMyArray(0), CheckMyArray(1)
        checkRow = Row + 1
    End If
Loop
Checkfile.Close
InputSheetname = Checkdict.Item("InputSheetname")
ExcelPath = Checkdict.Item("ExcelPath")
Set xlBook = xlApp.Workbooks.Open(ExcelPath)
Set xlSheet = xlBook.Worksheets(InputSheetname)
maxRowCount=xlSheet.usedRange.Rows.Count
msgprompt=Msgbox("1.Click YES to execute failed Test Cases.  2. Click NO to execute all Test Cases.", 4, "Enter your choice:")
For rowcnt=3 to maxRowCount
	If Not xlSheet.Cells(rowcnt, 8).Value="" Then
		value=xlSheet.Cells(rowcnt, 8).Value
		If Not Checkdict.Exists(Trim(value)) Then
			xlSheet.Cells(rowcnt, 9).Value="No"
		End If	
		If msgprompt=7 Then
			xlSheet.Cells(rowcnt, 9).Value="Yes"
		End If
	End If
Next
xlBook.Save
xlBook.Close
Set xlApp=Nothing
Msgbox "Script Terminated", 0, "STOP"
End Sub