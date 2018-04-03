package models.actions;

import models.ActionTypes;
import models.BrowserTypes;

public class OpenBrowserDescription extends ActionDescriptionBase {
	public BrowserTypes browserType;

	public OpenBrowserDescription(String browserName, int rowIndex) {
		super(ActionTypes.OPEN_BROWSER, rowIndex);
		if (browserName.toLowerCase().equals("chrome")) {
			this.browserType = BrowserTypes.CHROME;
		}
		if (browserName.toLowerCase().equals("firefox")) {
			this.browserType = BrowserTypes.FIREFOX;
		}
		if (browserName.toLowerCase().equals("ie")) {
			this.browserType = BrowserTypes.IE;
		}
	}
}
