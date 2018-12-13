#include <Excel.au3>
#include <MsgBoxConstants.au3>

; Create application object
Local $oExcel = _Excel_Open()
If @error Then Exit MsgBox(16, "Excel UDF: _Excel_BookOpen Example", "Error creating the Excel application object." & @CRLF & "@error = " & @error & ", @extended = " & @extended)

; *****************************************************************************
; Open an existing workbook and return its object identifier.
; *****************************************************************************
Local $sWorkbook = "D:\Project Status via JIRA\inputFile\Timesheet Report.xls"
Local $oWorkbook = _Excel_BookOpen($oExcel, $sWorkbook)

$oExcel.ActiveWorkbook.SaveAs ( "D:\Project Status via JIRA\inputFile\Timesheet Report.xlsx",51,"","",False,False)
_Excel_Close($oExcel)