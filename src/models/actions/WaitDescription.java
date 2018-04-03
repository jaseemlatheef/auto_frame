package models.actions;

import models.ActionTypes;

public class WaitDescription extends ActionDescriptionBase {
	public int seconds=10;
	public WaitDescription(int seconds, int rowIndex) {
		super(ActionTypes.WAIT, rowIndex);
		this.seconds=seconds;
	}
}
