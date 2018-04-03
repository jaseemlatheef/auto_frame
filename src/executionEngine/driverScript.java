package executionEngine;

import models.TestScript;
import models.TestSuite;

public class driverScript {

	public static void main(String[] args) throws Exception {
		executeSuite(); // If you need to run the entire test suite	
		//executeScript(); // If you need to run the test case
	}

	public static void executeSuite() throws Exception {
		String syspath = System.getProperty("user.dir");
		String sPath = syspath+ "/src/dataEngine/DataEnginetest.xlsx";
		//String sPath = "\\home\\qbuser\\eclipse-workspace\\Framework.tar.gz_expanded\\src\\dataEngine\\DataEngine.xlsx";
		TestSuite suite = new TestSuite(sPath, 2, 3, 1, 4);
		suite.beforeEveryTest = new BeforeTest();
		suite.execute(System.out);
		suite.afterEveryTest = new AfterTest();
	}

	public static void executeScript() throws Exception {
		String syspath = System.getProperty("user.dir");
		String sPath = syspath+ "/src/dataEngine/DataEngine.xlsx";
		System.out.println(sPath);
		//String sPath = "\\home\\qbuser\\eclipse-workspace\\Framework.tar.gz_expanded\\src\\dataEngine\\DataEngine.xlsx";
		String sheetName="Manage organization";
		TestScript script = new TestScript(sPath,sheetName, 2, 3, 1, 10 , 4); 
		script.beforeTest = new BeforeTest();
		script.execute();
		script.afterTest = new AfterTest();
	}
}
