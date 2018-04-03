package utility;

import models.TestResult;

public class TestScriptExcelSheet extends ExcelSheetBase {
	protected int ActionKeywordColumnIndex;
	protected int ParameterColumnIndex;

	public int StartingRowIndex;
	public int NoOfSteps;
	public int ResultsColumnIndex;

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public TestScriptExcelSheet(String path, String sheetName,
			int actionKeywordColumnIndex, int parameterColumnIndex,
			int startingRowIndex, int noOfSteps, int resultsColumnIndex)
			throws Exception {

		super(path, sheetName);

		ActionKeywordColumnIndex = actionKeywordColumnIndex;
		ParameterColumnIndex = parameterColumnIndex;

		StartingRowIndex = startingRowIndex;
		NoOfSteps = noOfSteps;
		ResultsColumnIndex = resultsColumnIndex;
	}

	public String getActionKeyword(int iRow) throws Exception {
		return getCellData(iRow, ActionKeywordColumnIndex);
	}

	public String getParameters(int iRow) throws Exception {
		return getCellData(iRow, ParameterColumnIndex);
	}

	public void writeResult(int iRow, TestResult result) {
		this.setCellData(iRow, ResultsColumnIndex, result.toString());
	}

	public String getSheetName() {
		return SheetName;
	}
}