package models.actions;

import models.ActionTypes;
import utility.TestBase;

public class SelectDescription extends ActionDescriptionBase {
	public String value;

	public SelectDescription(String byType, String byValue, String value,
			int rowIndex) {
		super(ActionTypes.SELECT, rowIndex);
		this.by = TestBase.getBy(byType, byValue);
		this.value = value;
	}
}
