package utility;

import models.BrowserTypes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public WebDriver driver;

	public void initializeDriver(BrowserTypes type) {
		switch (type) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					"/home/qbuser/eclipse-workspace/Framework.tar.gz_expanded/External lib/chromedriver");
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case IE:
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}
	}

	public void goToUrl(String url) {
		driver.get(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void click(By by) {
		waitUntilClickable(by, 10);
		driver.findElement(by).click();
	}
	public void hover(By by) {
		waitUntilClickable(by, 10);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by)).perform();
	}

	public void sendKeys(By by, String keys) {
		waitUntilClickable(by, 10);
		driver.findElement(by).sendKeys(keys);
	}

	public void sendKeys(String byType, String byValue, String value) {
		By by = getBy(byType, byValue);
		sendKeys(by, value);
	}

	public void click(String byType, String byValue) {
		By by = getBy(byType, byValue);
		click(by);
	}

	public static By getBy(String byType, String byValue) {
		By by = null;
		if (byType.equals("id")) {
			by = By.id(byValue);
		} else if (byType.equals("xpath")) {
			by = By.xpath(byValue);
		} else if (byType.equals("cssSelector")) {
			by = By.cssSelector(byValue);
		} else if (byType.equals("linkText")) {
			by = By.linkText(byValue);
		}
		else if (byType.equals("partialLinkText")) {
			by = By.partialLinkText(byValue);
		}
		return by;
	}

	public void waitUntilClickable(By by, int upto) {
		WebDriverWait wait = new WebDriverWait(driver, upto);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	public void wait(int upto) {
		try {
			Thread.sleep(upto * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(upto, TimeUnit.SECONDS);
	}

	public void select(By by, String value) {
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);
	}
}