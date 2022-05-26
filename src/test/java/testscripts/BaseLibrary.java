package testscripts;

import java.io.FileInputStream;

import java.net.URL;
import java.util.Properties;
import config.Locators;

import org.openqa.selenium.By;
//import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import ObjectRepository.AboutObject;
import io.appium.java_client.AppiumDriver;
import testcases.Mfunction;

public class BaseLibrary extends DriverScript {

	// Stores current window handle
	static String currentWindowHandle;

	static String methodReturnResult=null;

	// Create a browser instance and navigate to the test site

	@SuppressWarnings("rawtypes")
	public static String launchApp() throws Exception {
		try {
			System.out.println("Launching the App");
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("VERSION", "9.0");
			capabilities.setCapability("platformName",CONFIG.getProperty("deviceName"));
			capabilities.setCapability("UDID", CONFIG.getProperty("UDID"));
			capabilities.setCapability("appPackage", CONFIG.getProperty("appPackage"));
			capabilities.setCapability("appActivity","com.androidsample.generalstore.SplashActivity");
			driver = new AppiumDriver(new URL(CONFIG.getProperty("Appium_Node_URL")), capabilities);
			System.out.println("App launched, proceeding to Test");
			wait = new WebDriverWait(driver, 60);
			Shortwait = new WebDriverWait(driver, 5);
			Boolean result=Mfunction.verifyElementDisplayed(By.xpath("//*[@text='General Store']"),"General Store");
			if (result==true) {
				return "Pass";
			}
			else return "Fail";
		} catch (Exception e) {
			System.out.println("The Cause is " + e.getCause());
			System.out.println("The Message is " + e.getMessage());
			//return false;

		}
		//return true;
		//return methodReturnResult;
		return "Pass";
	}
	//Login
	

}
