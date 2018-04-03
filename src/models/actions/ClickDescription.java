package models.actions;

import utility.TestBase;
import models.ActionTypes;

public class ClickDescription extends ActionDescriptionBase {
	public ClickDescription(String byType, String byValue, int rowIndex) {
		super(ActionTypes.CLICK, rowIndex);
		this.by = TestBase.getBy(byType, byValue);
	}

}
