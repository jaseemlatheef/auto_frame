package models.actions;

import models.ActionTypes;

public class CloseBrowserDescription extends ActionDescriptionBase {
	public CloseBrowserDescription(int rowIndex) {
		super(ActionTypes.CLOSE_BROWSER, rowIndex);
	}
}
