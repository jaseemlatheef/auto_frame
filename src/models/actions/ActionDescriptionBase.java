package models.actions;

import models.ActionTypes;

import org.openqa.selenium.By;

public abstract class ActionDescriptionBase {
	public ActionTypes actionType = ActionTypes.NO_ACTION;

	public By by = null;

	public int rowIndex = -1;

	public ActionDescriptionBase(ActionTypes actionType, int rowIndex) {
		this.actionType = actionType;
		this.rowIndex = rowIndex;
	}
}
