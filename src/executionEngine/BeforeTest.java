package executionEngine;

import models.BrowserTypes;
import models.IBeforeTest;
import models.TestScript;

import org.openqa.selenium.By;

public class BeforeTest implements IBeforeTest {

	@Override
	public void executeBeforeTest(TestScript script) {
		if (!script.getSheetName().equals("Login")) {
			script.initializeDriver(BrowserTypes.CHROME);
			login(script);
		}
	}

	private void login(TestScript script) {

		script.goToUrl("http://52.179.80.121:8080");
		script.sendKeys(
				By.id("user"),"ammar@kurabi.net");// "");
		script.sendKeys(
				By.id("password"),"taokey");
		script.click(By
				.xpath("//*[@id=\"login-form\"]/div/div[2]/div[3]/button"));

	} 
	
/*	private void login(TestScript script) {

		script.goToUrl("http://188.166.30.136/login\r\n" + 
				"");
		script.sendKeys(
				By.cssSelector("input[data-id=username]"),"atmaram@qburst.com");// "");
		script.sendKeys(
				By.cssSelector("input[data-id=password]"),"qburst");
		script.click(By
				.cssSelector("button[data-id=submit-btn]"));

	}*/

}
