Dim nodeList, moduleList
Dim moduleCnt, nameModule
Dim configFile
'Err.Number = 0   
Call Test()
Sub Test()
MsgBox "Click OK to start conversion", ,"START"
'On Error Resume Next
	Set fso = CreateObject("Scripting.FileSystemObject")
    'configFile = "C:\Documents and Settings\sl052279\Desktop\xml\config.txt"
    configFile = fso.GetAbsolutePathName(".") & "\config.txt"  
    Call checkFilesandFolders(configFile, Result, ExcelPath, InputSheetname, ObjectSheetname, FunctionSheetName, TCFolderPath, FunctionFolderPath, isFile, isChildFolder, isFunFile, isFunChildFolder, Functionfolder) 
    if Result = False Then
		Exit Sub
	End If	    
     
    FunctionFolderParent=FunctionFolderPath
    If isFunFile=True or isFunChildFolder=True Then
		FunctionFolderParent=""
        f=Split(FunctionFolderPath, "\")
        For d=0 to uBound(f)-2
			if d=0 Then
				FunctionFolderParent=f(d)
				Elseif isFunFile=True Then
				FunctionFolderParent=FunctionFolderParent&"\"&f(d)
				FunctionChildFolder=f(uBound(f)-1)
				FunctionFiles=f(uBound(f))
				elseif isFunChildFolder=True Then
				FunctionFolderParent=FunctionFolderParent&"\"&f(d)
				FunParent=f(uBound(f)-1)
				FunctionChildFolder=f(uBound(f))              
			End If
        Next
    End If 
      
    If isFunChildFolder=True Then
		FunctionFolderParent=FunctionFolderParent&"\"&FunParent 
    End If   
     
    TestCaseParent=TCFolderPath     
    If isFile=True or isChildFolder=True Then
		TestCaseParent=""
		t=Split(TCFolderPath, "\")
		for c=0 to uBound(t)-2
			if c=0 Then
				TestCaseParent=t(c)
				Elseif isFile=True Then
				TestCaseParent=TestCaseParent&"\"&t(c)
				TestCaseChildFolder=t(uBound(t)-1)
				TestCaseFile=t(uBound(t))
				elseif isChildFolder=True Then
				TestCaseParent=TestCaseParent&"\"&t(c)
				Parent=t(uBound(t)-1)
				TestCaseChildFolder=t(uBound(t))
			End If
		Next
    End If
     
    If isChildFolder=True Then
		TestCaseParent=TestCaseParent&"\"&Parent 
    End If       
     
    Set excelObj = CreateObject("Excel.Application")
    Set wbObj = excelObj.Workbooks.Open(ExcelPath)     
    Set Folder = CreateObject("Scripting.FileSystemObject")
    Set TCFolderNames = Folder.GetFolder(TestCaseParent)
    Set TCSubFolderNames = TCFolderNames.SubFolders
    IF Functionfolder=True or isFunFile=True or isFunChildFolder=True Then
		Set FunctionFolderNames = Folder.GetFolder(FunctionFolderParent)
		Set FunctionSubFolderNames = FunctionFolderNames.SubFolders     
	End if
	 
    CallFunction  = 0
	'-------------------------------------------------------------------
	' Checking whether the contents available 
	' Comment here to append the test cases from line 77 to 93
	'IF wbObj.Worksheets(InputSheetname).Cells(3,8).value <> "" Then
	'	x=wbObj.Worksheets(InputSheetname).UsedRange.Rows.Count
	'	For i=3 to x
	'		For j=5 to 20
	'			wbObj.Worksheets(InputSheetname).Cells(i, j).Value=""
	'			resetcolor= "E"&i&":G"&j
	'			wbObj.Worksheets(InputSheetname).Range(resetcolor).Interior.ColorIndex = xlNone
	'			savechanges =True
	'		Next
	'	Next
	'End if
	  
	'IF wbObj.Worksheets(FunctionSheetName).Cells(3,1).value<>"" Then
	'	z=wbObj.Worksheets(FunctionSheetName).UsedRange.Rows.Count
	'	wbObj.Worksheets(FunctionSheetName).Range("A3:A" &z).EntireRow.Delete
	'	savechanges =True		
	'End if
	 
	 ' ---------Checking whether the contents available----------------
	  
    'FUNCTION
	IF Functionfolder=True or isFunFile=True or isFunChildFolder=True Then
		set funSheet = wbObj.Worksheets(FunctionSheetName) 	
		For Each FunctionSubFolderCount in FunctionSubFolderNames
			ChildFunFolder=FunctionSubFolderCount.Name
			if isFunFile=True Then
				ChildFunFolder=FunctionChildFolder
				FunFileName=FunctionFiles
			End If
			if isFunChildFolder=True Then
				ChildFunFolder=FunctionChildFolder
			End If    
			If Not ChildFunFolder = ".svn" Then
				Set FunctionFileNam = Folder.GetFolder(FunctionFolderParent&"\"&ChildFunFolder)
				Set funFolderCnt = FunctionFileNam.Files 
				For Each file in funFolderCnt 
					objCount = 0 
					Set ObjectRep = CreateObject("Scripting.Dictionary")   
					Set FunctionDoc = CreateObject("Msxml2.DOMDocument")
					FunName=file.name
					If  isFunFile=True Then
						FunName=FunFileName        
					End If       
					FunctionFile = FunctionFolderParent&"\"&ChildFunFolder&"\"&FunName
					FunctionDoc.Load (FunctionFile)
					Set FunIDObj = FunctionDoc.getElementsByTagName("title")
					FunID = FunIDObj.Item(0).Text
					Set FunctionnodeList = FunctionDoc.getElementsByTagName("td")
					funRowCount = funSheet.UsedRange.Rows.Count + 1 
					funSheet.Cells(funRowCount, 1) = FunID
					'IDColor="A"&(funRowCount)&":A"&(funRowCount)
					'funSheet.Range(IDColor).Interior.Color = RGB(245, 222, 179)
					FunctionName = FunctionSubFolderCount.Name
					For funCounter = 1 To FunctionnodeList.Length - 1 Step 3
						If InStr(FunctionnodeList.Item(funCounter).Text, "open") = 0 Then
							'If Not InStr(FunctionnodeList.Item(funCounter).Text, "verify") <> 0 Or InStr(FunctionnodeList.Item(funCounter).Text, "assert") <> 0 Then
								TempVariable=FunctionnodeList.Item(funCounter+1).Text
								TempVariable1=FunctionnodeList.Item(funCounter+2).Text 
								if Instr(FunctionnodeList.Item(funCounter).Text, "include") Then
									tempDependentFun = FunctionnodeList.Item(funCounter+1).Text
									tempDependentFun = Replace(tempDependentFun,"includes","")
									DependentFun = mid(tempDependentFun, 2, (instr(tempDependentFun, ".")-2))
									TempVariable1 = DependentFun
									TempVariable = ""				
								End If		 
								funSheet.Cells(funRowCount, 2).Value = FunctionnodeList.Item(funCounter).Text
								funSheet.Cells(funRowCount, 3).Value = TempVariable 
								funFormula=""
								If FunctionnodeList.Item(funCounter).Text<>"include" Then
									funFormula = "=VLOOKUP(C"&funRowCount&", 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
									if Instr(FunctionnodeList.Item(funCounter+1).Text, "~") Then
										funFormula = "=VLOOKUP(SUBSTITUTE(C"&funRowCount&",""~"",""~~""), 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
									End If 
								End If
								funSheet.Cells(funRowCount, 4).Value = funFormula
								funSheet.Cells(funRowCount, 5).Value = TempVariable1 
								If FunctionnodeList.Item(funCounter).Text<>"include" Then 
									ObjectRep.Add objCount, FunctionnodeList.Item(funCounter+1).Text          
									objCount = objCount + 1 
								'End If
								funRowCount = funRowCount+1
							End If
						End If
					Next 
					Call WriteinObjectWorkSheet(wbObj, ObjectSheetname, ObjectRep, CallFunction)
					If  isFunFile=True Then
						Exit For      
					End If     
				Next 
				If  isFunFile=True Then
					Exit For      
				End If
				IF isFunChildFolder=True Then
					Exit For
				End If    
			End If 
		Next  
	End if
   
    'TEST CASE
    For Each TCSubFolderCount in TCSubFolderNames
		ChildFolder=TCSubFolderCount.Name
		if isFile=True Then
			ChildFolder=TestCaseChildFolder
			FileName=TestCaseFile
			Else
			ChildFolder=TCSubFolderCount.Name
		End If
		if isChildFolder=True Then
			ChildFolder=TestCaseChildFolder
		End If
		If Not ChildFolder = ".svn" Then
			Set TCFile = Folder.GetFolder(TestCaseParent&"\"&ChildFolder)
			Set fileCnt = TCFile.Files   
			objWriteCount = 1 
			For Each tcFile in fileCnt
				Set ObjectRep = CreateObject("Scripting.Dictionary")
				Set wsheetInput = wbObj.Worksheets(InputSheetname)
				Set wsheetObj = wbObj.Worksheets(ObjectSheetname)	  	    
				Set TestDoc = CreateObject("Msxml2.DOMDocument")
				FName=tcFile.name
				If  isFile=True Then
					FName=TestCaseFile        
				End If
				HTMLFile = TestCaseParent&"\"&ChildFolder&"\"&FName
				TestDoc.Load (HTMLFile)
				Set TCIDObj = TestDoc.getElementsByTagName("title")
				TCID = TCIDObj.Item(0).Text
				Set nodeList = TestDoc.getElementsByTagName("td")
				for iTC=3 to 2000 'wsheetInput.UsedRange.Rows.Count
					if wsheetInput.Cells(iTC, 8).Value="" and wsheetInput.Cells(iTC, 9).Value="" and wsheetInput.Cells(iTC, 10).Value="" and wsheetInput.Cells(iTC, 11).Value="" and wsheetInput.Cells(iTC, 12).Value="" and wsheetInput.Cells(iTC, 13).Value="" and wsheetInput.Cells(iTC, 14).Value="" and wsheetInput.Cells(iTC, 15).Value="" and wsheetInput.Cells(iTC, 16).Value="" and wsheetInput.Cells(iTC, 17).Value="" and wsheetInput.Cells(iTC, 18).Value="" and wsheetInput.Cells(iTC, 19).Value="" Then
						inputRow = iTC
						Exit For
					End If
				Next
				cnt = 0
				wsheetInput.Cells(inputRow, 8) = ChildFolder&"."&TCID
				'IDColor="H"&(inputRow)&":H"&(inputRow)
				' wsheetInput.Range(IDColor).Interior.Color = RGB(245, 222, 179)
				wsheetInput.Cells(inputRow, 9) = "Yes"
				For Z = 1 To nodeList.Length - 1 Step 3
					tempObj=nodeList.Item(z + 1).Text
					If nodeList.Item(Z+1).Text="" and nodeList.Item(Z).Text <> "include" Then
						tempObj=nodeList.Item(Z).Text
					End IF
					If InStr(nodeList.Item(Z).Text, "include") = 0 Then
					ObjectRep.add cnt, tempObj
					cnt = cnt + 1
					End If
					If InStr(nodeList.Item(Z).Text, "verify") <> 0 Or InStr(nodeList.Item(Z).Text, "waitFor") <> 0 Then
						wsheetInput.Cells(inputRow, 17).Value = nodeList.Item(Z).Text
						wsheetInput.Cells(inputRow, 18).Value = nodeList.Item(Z + 1).Text
						wsheetInput.Cells(inputRow, 19).Value = nodeList.Item(Z + 2).Text
						'if nodeList.Item(Z).Text <> "verifyTextPresent" and nodeList.Item(Z).Text <> "verifyTitle" Then
							wsheetInput.Cells(inputRow, 20).Value = "=VLOOKUP(R"&inputRow&", 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
						'End If
						Else
						tempName = nodeList.Item(Z+2).Text
						tempName1 = nodeList.Item(Z+1).Text
						if instr(nodeList.Item(Z).Text, "setTimeout") Then
							tempName1 = null
							tempName = nodeList.Item(Z+1).Text
						End If
						if instr(nodeList.Item(Z).Text, "include") Then
							tempDependentTC = nodeList.Item(Z + 1).Text
							'tempDependentTC = Replace(tempDependentTC,"includes","")
							'DependentTC = mid(tempDependentTC, 2, (instr(tempDependentTC, ".")-2))
							tempName = tempDependentTC
							tempName1 = ""
						End If	
						if nodeList.Item(Z).Text<>"include" and nodeList.Item(Z).Text<>"waitForPrototypeLoaded" then 
							'ObjectRep.add cnt, nodeList.Item(z + 1).Text
							'cnt = cnt + 1
							if nodeList.Item(Z).Text="setTimeout" or nodeList.Item(Z).Text="deleteAllVisibleCookies" Then
								tcFormula = "=VLOOKUP(K"&inputRow&", 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
								Else
								tcFormula = "=VLOOKUP(L"&inputRow&", 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
							End If
							if Instr(tempName1, "~") Then
								tcFormula = "=VLOOKUP(SUBSTITUTE(L"&inputRow&",""~"",""~~""), 'Object-Repository'!$A$1:$B$1000, 2, FALSE)"
							End If 
							wsheetInput.Cells(inputRow, 13).Value = tcFormula
						End if
						wsheetInput.Cells(inputRow, 11).Value = nodeList.Item(Z).Text
						wsheetInput.Cells(inputRow, 12).Value = tempName1            
						wsheetInput.Cells(inputRow, 14).Value = tempName
						If wsheetInput.Cells(inputRow, 8).Value <> "" and  inputRow <> 3 Then
							wsheetInput.Cells(inputRow-1, 5).Value = "Expected Text"
							wsheetInput.Cells(inputRow-1, 6).Value = "Actual Pass Text"
							wsheetInput.Cells(inputRow-1, 7).Value = "Actual Fail Text"
							color= "E"&(inputRow-1)&":G"&(inputRow-1)
							wsheetInput.Range(color).Interior.Color = RGB(224, 255, 255)
						End If
					End If
					inputRow = inputRow + 1
				Next 
			wsheetInput.Cells(inputRow-1, 5).Value = "Expected Text"
			wsheetInput.Cells(inputRow-1, 6).Value = "Actual Pass Text"
			wsheetInput.Cells(inputRow-1, 7).Value = "Actual Fail Text"     
		    color= "E"&(inputRow-1)&":G"&(inputRow-1)
			wsheetInput.Range(color).Interior.Color = RGB(224, 255, 255)
			Call WriteinObjectWorkSheet(wbObj, ObjectSheetname, ObjectRep, CallFunction)
			if isFile=True Then
				wbObj.Save
				wbObj.Close
				Set excelObj = Nothing
				MsgBox "Conversion Completed", ,"STOP" 
				Exit Sub
			End If           
		Next 
	End If
	if isChildFolder=True Then
        wbObj.Save
        wbObj.Close
        Set excelObj = Nothing
        MsgBox "Conversion Completed", ,"STOP" 
		Exit Sub
	End If 
	Next  
              
     'If Err.Number <> 0 Then
      'msgbox Err.Description
      'wbObj.Save
      'wbObj.Close
      'Set excelObj = Nothing
     'End If
     wbObj.Save
     wbObj.Close
	 Set excelObj = Nothing  
   MsgBox "Conversion Completed", ,"STOP"  	 
 End Sub   
 
 Function WriteinObjectWorkSheet(wbObj, ObjectSheetname, ObjectRep, CallFunction)
    Set ObjWSheet = wbObj.WorkSheets(ObjectSheetname)
    Objrowcount = ObjWSheet.UsedRange.Rows.Count+1
    if Not Objrowcount=3 Then
      obj=0
          for iobjcnt=0 to ObjectRep.count
            objCheck=True
            for jobjCnt=3 to Objrowcount+obj-1
              if ((cStr(ObjectRep.Item(iobjcnt))=cStr(ObjWSheet.Cells(jobjCnt, 1).Value))) or (ObjectRep.Item(iobjcnt)="") Then
                objCheck=False
                Exit For
              End If
            Next
            if objCheck=True Then
              ObjWSheet.Cells((Objrowcount+obj), 1).Value= ObjectRep.Item(iobjcnt)
              obj = obj+1
            End If
       Next
       Exit Function
    End If    
     For iObjCounter = 0 to ObjectRep.count-1
          For jObjCounter = iObjCounter+1 to ObjectRep.count-1
             if ObjectRep.Item(iObjCounter)= ObjectRep.Item(jObjCounter)Then
                ObjectRep.Remove(iObjCounter)
             End if
          Next
        Next
          obj = 0
          for objcount = Objrowcount to ObjectRep.count+Objrowcount
           ObjWSheet.Cells((Objrowcount+obj), 1).Value= ObjectRep.Item(obj)
           obj = obj+1
          Next 
        for objcol = 3 to ObjWSheet.UsedRange.Rows.Count
           if ObjWSheet.Cells(objcol, 1).Value = "" Then
              ObjWSheet.Rows(objcol).EntireRow.Delete
           End If
        Next 
        ObjectRep.Removeall 
        Set ObjectRep = Nothing   
 End Function
 
 Function checkFilesandFolders(configFile, Result, ExcelPath, InputSheetname, ObjectSheetname, FunctionSheetName, TCFolderPath, FunctionFolderPath, isFile, isChildFolder, isFunFile, isFunChildFolder, Functionfolder)
    ifFile=False
    isChildFolder=False
    isFunFile=False
    isFunChildFolder=False
    Set objFile = CreateObject("Scripting.FileSystemObject")    
    Result = objFile.FileExists(configFile)
    if Result = False Then
        tempFileName = InStrRev(configFile, "\")
        FileName = mid(configFile, tempFileName+1)
        Msgbox "Config File not found in the path specified",,"Config file path"
        Exit Function
    Else 
            Set Checkdict = CreateObject("Scripting.Dictionary")
            Set Checkfile = objFile.OpenTextFile(configFile, 1)
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

     ExcelPath = Checkdict.Item("ExcelPath")
     InputSheetname = Checkdict.Item("InputSheetname")
     ObjectSheetname = Checkdict.Item("ObjectSheetname")
     FunctionSheetName = Checkdict.Item("FunctionSheetName")
     TCFolderPath = Checkdict.Item("TCFolder")
     FunctionFolderPath = Checkdict.Item("FunctionFolder")
     
     If Instr(TCFolderPath, ".html") Then
     Result = objFile.FileExists(TCFolderPath)
     isFile=True
     else
     Result = objFile.FolderExists(TCFolderPath)
      if Result=True Then
        Set FindChildFolder=objFile.GetFolder(TCFolderPath)
        fileCountinChildFolder=FindChildFolder.Files.Count
        if fileCountinChildFolder<>0 Then
        Set FindFiles=FindChildFolder.Files
        For Each xFiles in FindFiles
          FileType=xFiles.Type
          if FileType="HTML Document" or FileType="Firefox Document" Then
            isChildFolder=True
          End If
        Next
        End If 
      End If
     End If
     if Result=False Then
      MsgBox TCFolderPath&" doesnot exists.",,"TestCase Path"
      Exit Function
     End If
     
     If Instr(FunctionFolderPath, ".html") Then
     Result = objFile.FileExists(FunctionFolderPath)
     isFunFile=True
     Else
     Result = objFile.FolderExists(FunctionFolderPath)
     if Result=False Then
		   Functionfolder=False
	    else
		   Functionfolder=True 
        Set FindFunChildFolder=objFile.GetFolder(FunctionFolderPath)
        funfileCountinChildFolder=FindFunChildFolder.Files.Count
        if funfileCountinChildFolder<>0 Then
        Set FindFunFiles=FindFunChildFolder.Files
        For Each xFunFiles in FindFunFiles
          FunFileType=xFunFiles.Type
          if FunFileType="HTML Document" or FunFileType="Firefox Document" Then
            isFunChildFolder=True
          End If
        Next
        End If        
     End If
     End If       
     
     Result = objFile.FileExists(ExcelPath)
       if Result = False Then
        tempFileName = InStrRev(ExcelPath, "\")
        FileName = mid(ExcelPath, tempFileName+1)
        MsgBox FileName& " cannot found in the path specified",,"Excel Sheet path"
        Exit Function
        Else
        Set CheckExcelObj = CreateObject("Excel.Application")
        Set CheckWBObj = CheckExcelObj.WorkBooks.Open(ExcelPath)
        SheetCount = CheckWBObj.Sheets.Count
        for i = 1 to 4 'SheetCount
          SheetName = CheckWBObj.Sheets(i).Name
          if not i = 1 Then
             if InputSheetname=SheetName or ObjectSheetname=SheetName or FunctionSheetName=SheetName Then
              Else
               MsgBox SheetName& " worksheet doesnot exists.",,"Worksheets"
               Result = False
             End If 
          End If
        Next
        CheckWBObj.Close
        Set CheckExcelObj = Nothing 
       End If       
    End If              
 End Function