package models.actions;

import models.ActionTypes;

public class NavigationDescription extends ActionDescriptionBase {
	public String url;

	public NavigationDescription(String url, int rowIndex) {
		super(ActionTypes.NAVIGATE, rowIndex);
		this.url = url;
	}
}
