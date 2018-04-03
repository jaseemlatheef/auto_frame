package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import models.TestScript;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSuiteExcelSheet {
	protected int ActionKeywordColumnIndex;
	protected int ParameterColumnIndex;

	public int StartingRowIndex;
	public int ResultsColumnIndex;

	public String Path;
	public XSSFWorkbook ExcelWBook;
	public int numberOfSheets;

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public TestSuiteExcelSheet(String path, int actionKeywordColumnIndex,
			int parameterColumnIndex, int startingRowIndex,
			int resultsColumnIndex) throws IOException {
		this.Path = path;
		FileInputStream ExcelFile = new FileInputStream(path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ActionKeywordColumnIndex = actionKeywordColumnIndex;
		ParameterColumnIndex = parameterColumnIndex;

		StartingRowIndex = startingRowIndex;
		ResultsColumnIndex = resultsColumnIndex;
		numberOfSheets = ExcelWBook.getNumberOfSheets();
	}

	public ArrayList<TestScript> getTestScripts() throws Exception {
		ArrayList<TestScript> testScripts = new ArrayList<TestScript>();
		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet sheet = ExcelWBook.getSheetAt(i);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int noOfSteps = rowCount - StartingRowIndex;
			TestScript script = new TestScript(Path, sheet.getSheetName(),
					ActionKeywordColumnIndex, ParameterColumnIndex,
					StartingRowIndex, noOfSteps, ResultsColumnIndex);
			testScripts.add(script);
		}

		return testScripts;
	}

}
