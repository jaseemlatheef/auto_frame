package executionEngine;

import models.IAfterTest;
import models.TestScript;

public class AfterTest implements IAfterTest {

	@Override
	public void executeAfterTest(TestScript script) {

		if (!script.getSheetName().equals("Login")) {
			script.closeDriver();
		}
	}
}
