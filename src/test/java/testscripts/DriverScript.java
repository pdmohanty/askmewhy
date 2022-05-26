package testscripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import jxl.JXLException;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
//import org.junit.Before;
/*import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;*/
import org.openqa.selenium.WebDriver;
import datatable.XlsReader;
import io.appium.java_client.AppiumDriver;
import reports.*;
import util.SendMail;
import util.TestUtil;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
//import util.sendcura;

public class DriverScript {
	
	public static Properties CONFIG;
	public static Properties OR;
	public static Properties APPTEXT;
	public static Properties LOG;
	public static XlsReader controller;
	public static XlsReader testData;
	public static WebDriver wbdv = null;
	public static AppiumDriver driver = null;
	public static String firstSheetName;
	public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	public static Logger DATA_LOGS = Logger.getLogger("dataLogger");
	public static String currentTest;
	public static int testRepeat;
	public static String object;
	public static int Data_Row_No;
	public static String keyword;
	public static String currentTSID;
	public static String stepDescription;
	public static String proceedOnFail;
	public static String testStatus;
	public static String ReqDate;
	public static String failTest = "Fail";
	public static String screenshotPath = System.getProperty("user.dir")+"/Report/";
	public static String lastName;
	public static String result;
	public static WebDriverWait wait;
	public static WebDriverWait Shortwait;

	@BeforeClass

	public static void initialize() throws IOException {

		// Override default J2SE built-in workable logger built-in
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		//writer("test driverscript");


		write.delete();//Deletes the automation data file created to be sent for data dependency

		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/config.properties");
		CONFIG.load(fs);

		//Workbook workbook=

		// Locate Application Log

		LOG = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/Log4j.properties");
		LOG.load(fs);
		LOG.setProperty("log4j.appender.dest1.File",System.getProperty("user.dir")+"/src/test/java/config/application.log");
		LOG.store(new FileOutputStream(System.getProperty("user.dir")+"/src/test/java/Log4j.properties"), null);
		controller = new XlsReader(System.getProperty("user.dir")+"/src/test/java/config/controller.xls");
		testData = new XlsReader(System.getProperty("user.dir")+"/src/test/java/config/testData.xls");

		//testData = new XlsReader(System.getProperty("user.dir")+"/src/test/java/config/testData.xls");

		// Start the process of HTML report generation
		ReportUtil.startTesting(System.getProperty("user.dir")
				+ "/Report/index.html",
				TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
				CONFIG.getProperty("env"), CONFIG.getProperty("version"),
				CONFIG.getProperty("test_browser"),
				CONFIG.getProperty("testSiteURL"));
	}


	@Test
	public void testApp() throws NumberFormatException, BiffException,
	JXLException, IOException {

		String startTime = null;

		// Get the first sheet name under 'controller.xls'
		firstSheetName = controller.getFirstSheetname();

		ReportUtil.startSuite(firstSheetName);

		for (int tcid = 1; tcid < controller.getRowCount(firstSheetName); tcid++) {

			// Stores the current sub-module
			currentTest = controller.getCellData(firstSheetName, "TCID", tcid)
					.trim();

			// Runs the respective sub-module if Runmode for the
			// sub-module is
			// 'Y'
			if (controller.getCellData(firstSheetName, "Runmode", tcid).equals(
					"Y")) {

				APPLICATION_LOGS.debug("Executing test : " + currentTest);

				// Initialize start time of test
				startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");

				// Implement keyword
				for (int tsid = 1; tsid < controller.getRowCount(currentTest); tsid++) {

					// values from xls
					// Stores the current keyword
					keyword = controller.getCellData(currentTest, "Keyword",
							tsid).trim();

					// Stores the current TSID
					currentTSID = controller.getCellData(currentTest, "TSID",
							tsid).trim();

					// Stores the current description
					stepDescription = controller.getCellData(currentTest,
							"Description", tsid).trim();

					proceedOnFail = controller.getCellData(currentTest,
							"ProceedOnFail", tsid).trim();

					if (!controller.getCellData(currentTest, "Data_Row_No",
							tsid).isEmpty()) {
						Data_Row_No = Integer.parseInt(controller.getCellData(
								currentTest, "Data_Row_No", tsid).trim());
					}

					try {

						Method method = Keywords.class.getMethod(keyword);
						String result = (String) method.invoke(method);
						APPLICATION_LOGS
						.debug("Result of test case execution - "
								+ result);

						if (!result.startsWith("Fail")) {
							ReportUtil.addKeyword(stepDescription, keyword,
									result, null);
						}

						// Take screenshot - only on
						// error
						if (result.startsWith("Fail")) {

							testStatus = "Fail";

							// Give a fileName for
							// the screenshot and
							// store
							String fileName = "Suite1_TC" + tcid + "_TS" + tsid
									+ "_" + keyword + testRepeat + ".jpg";
							String path = screenshotPath + fileName;
							TestUtil.takeScreenShot(path);
							APPLICATION_LOGS.debug("SCREENSHOT taken under : "
									+ path);

							// Write the test result
							// to HTML report
							ReportUtil.addKeyword(stepDescription, keyword,
									result, fileName);

							if (proceedOnFail.equalsIgnoreCase("N")) {
								break;
							}

						}

						if (wbdv != null) {
							FunctionLibrary.closeDriver();
						}

					}


					catch (Throwable testException) {
						APPLICATION_LOGS.debug("Error came : "
								+ testException.getMessage());
					}

				} // keywords -inner for loop

				// Report pass or fail
				if (testStatus == null) {
					testStatus = "Pass";
				}

				APPLICATION_LOGS.debug("Result of the '" + currentTest
						+ "' test suite execution - " + testStatus);

				ReportUtil.addTestCase(currentTest, startTime,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			else {

				APPLICATION_LOGS.debug("Skipping the test : " + currentTest);
				testStatus = "Skip";

				// Report skipped
				ReportUtil.addTestCase(currentTest,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			testStatus = null;

		}

		// End test reporting
		ReportUtil.endSuite();
	}

	@AfterClass
	public static void endScript() throws InterruptedException {

		ReportUtil.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));
		//
		FunctionLibrary.closeDriver();

		//SendMail.zipAndSendReport();

	}

}
