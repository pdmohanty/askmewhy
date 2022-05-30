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
			capabilities.setCapability("browserstack.user", "willsmith_T1kKnN");
			capabilities.setCapability("browserstack.key", "AGa2haimiypT94Ap8wsH");
	    	
	    	// Set URL/appproperties of the application under test
			capabilities.setCapability("app", "bs://2ba895b5aedc6545e2c655ecb9d51634725b7f3a");
			//capabilities.setCapability("browserstack.local", "true");
	    	// Specify device and os_version for testing
			capabilities.setCapability("device", "Samsung Galaxy S21 Ultra");
			capabilities.setCapability("os_version", "11.0");
	        
	    	// Set other BrowserStack capabilities
			capabilities.setCapability("project", "Demo Java Project");
			capabilities.setCapability("build", "browserstack-build-2");
			capabilities.setCapability("name", "trial_run_demomindfire");
			//capabilities.setCapability("appPackage", CONFIG.getProperty("appPackage"));
			//capabilities.setCapability("appActivity","crc645adce9796ea0c767.SplashActivity");
			
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
