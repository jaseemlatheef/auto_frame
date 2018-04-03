package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import models.actions.*;
import utility.TestBase;
import utility.TestScriptExcelSheet;
import config.KeywordParser;

public class TestScript extends TestBase {
	TestScriptExcelSheet testScript;

	public TestScript(String path, String sheetName,
			int actionKeywordColumnIndex, int parameterColumnIndex,
			int startingRowIndex, int noOfSteps, int resultsColumnIndex)
			throws Exception {
		testScript = new TestScriptExcelSheet(path, sheetName,
				actionKeywordColumnIndex, parameterColumnIndex,
				startingRowIndex, noOfSteps, resultsColumnIndex);
	}

	public TestResult execute() throws IOException {
		KeywordParser keywordParser = new KeywordParser(testScript);
		ArrayList<ActionDescriptionBase> actionList = null;
		try {
			actionList = keywordParser.parseActions();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return TestResult.FAILED;
		}

		if (beforeTest != null) {
			beforeTest.executeBeforeTest(this);
		}

		for (ActionDescriptionBase action : actionList) {

			try {

				Perform(action);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				testScript.writeResult(action.rowIndex, TestResult.FAILED);
				if (afterTest != null) {
					afterTest.executeAfterTest(this);
				}
				
				return TestResult.FAILED;
			}
			testScript.writeResult(action.rowIndex, TestResult.PASSED);
			//finishExecution();
		}
		if (afterTest != null) {
			afterTest.executeAfterTest(this);
		}
		finishExecution();

		return TestResult.PASSED;

	}

	public void Perform(ActionDescriptionBase action) {

		switch (action.actionType) {
		case OPEN_BROWSER:
			OpenBrowserDescription browser = (OpenBrowserDescription) action;
			Perform(browser);
			break;
		case CLICK:
			ClickDescription click = (ClickDescription) action;
			Perform(click);
			break;
		case HOVER:
			HoverDescription hover = (HoverDescription) action;
			Perform(hover);
			break;
		case INPUT:
			InputDescription input = (InputDescription) action;
			Perform(input);
			break;
		case SELECT:
			SelectDescription select = (SelectDescription) action;
			Perform(select);
			break;
		case WAIT:
			WaitDescription wait = (WaitDescription) action;
			Perform(wait);
			break;
		case CLOSE_BROWSER:
			CloseBrowserDescription close = (CloseBrowserDescription) action;
			Perform(close);
			break;
		case NAVIGATE:
			NavigationDescription navigation = (NavigationDescription) action;
			Perform(navigation);
			break;
		case NO_ACTION:
			break;
		default:
			break;
		}

	}

	public void Perform(OpenBrowserDescription browser) {
		initializeDriver(browser.browserType);
	}

	public void Perform(ClickDescription action) {
		click(action.by);
	}
	public void Perform(HoverDescription action) {
		hover(action.by);
	}

	public void Perform(InputDescription action) {
		sendKeys(action.by, action.value.trim());
	}

	public void Perform(SelectDescription action) {
		select(action.by, action.value.trim());
	}
	public void Perform(WaitDescription action) {
		wait(action.seconds);
	}

	public void Perform(CloseBrowserDescription action) {
		closeDriver();
	}
	

	public void Perform(NavigationDescription action) {
		goToUrl(action.url.trim());
	}

	protected void finishExecution() throws IOException {
		testScript.saveData();
	}

	public String getSheetName() {
		return testScript.getSheetName();
	}

	public IBeforeTest beforeTest;

	public IAfterTest afterTest;
}
