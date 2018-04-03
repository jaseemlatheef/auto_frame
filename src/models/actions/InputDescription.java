package models.actions;

import models.ActionTypes;
import utility.TestBase;

public class InputDescription extends ActionDescriptionBase {
	public String value;

	public InputDescription(String byType, String byValue, String value,int rowIndex) {
		super(ActionTypes.INPUT, rowIndex);
		this.by = TestBase.getBy(byType, byValue);
		this.value = value;
	}
}
