package models.actions;

import models.ActionTypes;
import utility.TestBase;

public class HoverDescription  extends ActionDescriptionBase {
		public HoverDescription(String byType, String byValue, int rowIndex) {
			super(ActionTypes.HOVER, rowIndex);
			this.by = TestBase.getBy(byType, byValue);
		}
}
