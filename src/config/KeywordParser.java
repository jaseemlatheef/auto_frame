package config;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import utility.TestScriptExcelSheet;
import models.actions.*;

public class KeywordParser {

	private TestScriptExcelSheet TestScript;

	public KeywordParser(TestScriptExcelSheet testScript) {
		TestScript = testScript;
	}

	public ArrayList<ActionDescriptionBase> parseActions() throws Exception {
		ArrayList<ActionDescriptionBase> list = new ArrayList<ActionDescriptionBase>(
				TestScript.NoOfSteps);
		for (int iRow = TestScript.StartingRowIndex; iRow <= TestScript.NoOfSteps; iRow++) {
			// Storing the value of excel cell in sActionKeyword string variable
			String sActionKeyword = TestScript.getActionKeyword(iRow);
			String parametersString = TestScript.getParameters(iRow);
			String[] params = new String[0];
			if (parametersString != null) {
				params = parametersString.split(",");
			}
			ActionDescriptionBase action = getActionModel(sActionKeyword,
					params, iRow);
			if (action != null) {
				list.add(action);
			}
		}

		return list;
	}

	public ActionDescriptionBase getActionModel(String actionKeyword,
			String[] parameters, int rowIndex) throws Exception {
		if (actionKeyword == null) {

			return null;
		}
		// Comparing the value of Excel cell with all the project keywords
		if (actionKeyword.equals("openBrowser")) {
			if (parameters == null || parameters.length == 0) {
				throw new InvalidPropertiesFormatException(
						"Invalid openBrowser parameters");
			}
			return new OpenBrowserDescription(parameters[0], rowIndex);
		} else if (actionKeyword.equals("navigate")) {
			if (parameters == null || parameters.length == 0) {
				throw new InvalidPropertiesFormatException(
						"Invalid navigate parameters");
			}
			return new NavigationDescription(parameters[0], rowIndex);
		} else if (actionKeyword.equals("input")) {
			if (parameters == null || parameters.length < 3) {
				throw new InvalidPropertiesFormatException(
						"Invalid input parameters");
			}
			return new InputDescription(parameters[0], parameters[1],
					parameters[2], rowIndex);
		} else if (actionKeyword.equals("click")) {
			if (parameters == null || parameters.length < 2) {
				throw new InvalidPropertiesFormatException(
						"Invalid click parameters");
			}
			return new ClickDescription(parameters[0], parameters[1], rowIndex);
		} else if (actionKeyword.equals("hover")) {
			if (parameters == null || parameters.length < 2) {
				throw new InvalidPropertiesFormatException(
						"Invalid hover parameters");
			}
			return new HoverDescription(parameters[0], parameters[1], rowIndex);
			
		} else if (actionKeyword.equals("select")) {
			if (parameters == null || parameters.length < 3) {
				throw new InvalidPropertiesFormatException(
						"Invalid select parameters");
			}
			return new SelectDescription(parameters[0], parameters[1],
					parameters[2], rowIndex);
		} 
		
		else if (actionKeyword.equals("closeBrowser")) {
			return new CloseBrowserDescription(rowIndex);
		}	
			else if (actionKeyword.equals("wait")) {
				if (parameters == null || parameters.length < 1) {
					throw new InvalidPropertiesFormatException(
							"Invalid wait parameters");
				}
				return new WaitDescription(Integer.parseInt(parameters[0]) ,rowIndex);
		}
		return null;
	}
}