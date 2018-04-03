package models;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import utility.TestSuiteExcelSheet;

public class TestSuite {
	public TestSuiteExcelSheet testSuite;

	public TestSuite(String path, int actionKeywordColumnIndex,
			int parameterColumnIndex, int startingRowIndex,
			int resultsColumnIndex) throws Exception {
		this.testSuite = new TestSuiteExcelSheet(path,
				actionKeywordColumnIndex, parameterColumnIndex,
				startingRowIndex, resultsColumnIndex);

		TestScripts = testSuite.getTestScripts();
	}

	public ArrayList<TestScript> TestScripts = new ArrayList<TestScript>();

	public void execute(PrintStream printStream) throws IOException {
		for (TestScript script : TestScripts) {
			if (beforeEveryTest != null) {
				script.beforeTest=beforeEveryTest;
			}
			if (afterEveryTest != null) {
				script.afterTest=afterEveryTest;
			}
			TestResult result = script.execute();
			if (printStream != null) {
				printStream.println(result.toString() + " - "
						+ script.getSheetName());
			}
		}
	}

	public IBeforeTest beforeEveryTest;

	public IAfterTest afterEveryTest;
}
