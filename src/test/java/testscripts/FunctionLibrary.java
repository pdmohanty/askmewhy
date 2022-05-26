package testscripts;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

//import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibrary extends DriverScript {
	public static long start;
	public static long finish;
	public static String test="Fail";

	//Method for Clicking on Web Element
	public static void uncheckCheckBox(By locator, String elemName) {
		APPLICATION_LOGS.debug("Unchecking the checkbox: " + elemName);
		System.out.println("Unchecking the checkbox: " + elemName);
		try {
			FunctionLibrary.highlightElement(driver, locator);
			System.out.println(driver.findElement(locator).getAttribute("checked"));
			System.out.println(driver.findElement(locator).getAttribute("value"));
			if (driver.findElement(locator).getAttribute("value") == "on")
				//if (driver.findElement(locator).isSelected())
			{
				System.out.println("Checked Previosuly");
				//driver.findElement(By.id("showAvaialCheckbox")).getSize();
				FunctionLibrary.highlightElement(driver, locator);
				driver.findElement(locator).click();

				//driver.findElement(By.id("locator")).isEnabled();
			}
			System.out.println("Not selected");
		} catch (Throwable t) {
			// report error
			System.out.println("Error while unchecking checkbox for:  -" + elemName);
			APPLICATION_LOGS.debug("Error while unchecking checkbox for:  -" + elemName + t.getMessage());
		}
	}

	public static boolean verifyPageSource(String expText, String elemName) {
		APPLICATION_LOGS.debug("Verify Text from Page Source: " + elemName);
		System.out.println("Verify Text from Page Source: " + elemName);
		try {
			Assert.assertTrue(driver.getPageSource().contains(expText));
			return true;
		} catch (Throwable t) {
			// report error
			System.out.println("Error while Verifying Text from Page Source: " + elemName);
			APPLICATION_LOGS.debug("Error while Verifying Text from Page Source: " + elemName + t.getMessage());
			return false;
		}

	}

	//Method for Clicking on Web Element
	public static void clickLink(By locator,String elemName) {
		APPLICATION_LOGS.debug("Clicking on: "+elemName);
		System.out.println("Clicking on: "+elemName);
		try {

			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			driver.findElement(locator).click();
			System.out.println("clicked"+elemName);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while clicking on link -"+elemName);
			APPLICATION_LOGS.debug("Error while clicking on link -"+ elemName + t.getMessage());
		}
	}

	//Method for Clicking on Web Element
	public static void clickLink(By parentLoc,By childLoc,String elemName) {
		APPLICATION_LOGS.debug("Clicking on: "+elemName);
		System.out.println("Clicking on: "+elemName);
		try {
			waitForElementToLoad(parentLoc);
			FunctionLibrary.highlightElement(driver, childLoc);
			driver.findElement(parentLoc).findElement(childLoc).click();
		} catch(Throwable t) {
			// report error
			System.out.println("Error while clicking on link -"+elemName);
			APPLICATION_LOGS.debug("Error while clicking on link -"+ elemName + t.getMessage());
		}
	}

	//Method for Clearing the field
	public static void clearField(By locator,String elemName) {
		APPLICATION_LOGS.debug("Clearing field: "+elemName);
		System.out.println("Clearing field: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			driver.findElement(locator).clear();
		} catch(Throwable t) {
			// report error
			System.out.println("Error while clicking on link -"+elemName);
			APPLICATION_LOGS.debug("Error while clicking on link -"+ elemName + t.getMessage());
		}
	}

	//Method for Sending values to Input Box
	public static void input(By locator,String elemName,String Value) {
		APPLICATION_LOGS.debug("Sending Values in: "+elemName);
		System.out.println("Sending Values in: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			driver.findElement(locator).sendKeys(Value);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Sending Values in:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Sending Values in:  -"+ elemName + t.getMessage());
		}
	}

	//Method for Sending values to Input Box like Chord
	public static void inputChord(By locator,String elemName,String Value) {
		APPLICATION_LOGS.debug("Sending Values in: "+elemName);
		System.out.println("Sending Values in: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			driver.findElement(locator).sendKeys(Keys.chord(Value));
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Sending Values in:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Sending Values in:  -"+ elemName + t.getMessage());
		}
	}

	//Method for Verifying Text Present
	public static Boolean verifyTextPresent(String elemName,String textPresent) {
		APPLICATION_LOGS.debug("Verifying Text Present: "+elemName);
		System.out.println("Verifying Text Present: "+elemName);
		try {
			//System.out.println(driver.findElement(By.cssSelector("BODY")).getText());
			Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+textPresent+"[\\s\\S]*$"));
			return true;
		} catch (Error e) {
			APPLICATION_LOGS.debug("Verifying Text Present: "+elemName);
			System.out.println("Verifying Text Present: "+elemName);
			return false;
		}
	}

	//Method for Verifying Text
	public static Boolean verifyText(String elemName,String actValue, String expValue) {
		APPLICATION_LOGS.debug("Verifying Text for: "+elemName);
		System.out.println("Verifying Text for: "+elemName);
		try {
			Assert.assertEquals(expValue.trim(), actValue.trim());
			return true;
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Verifying Text for:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Verifying Text for:  -"+ elemName + t.getMessage());
			return false;
		}
	}

	//Method for Verifying Boolean Result
	public static void assertCheck(String elemName,Boolean condition) {
		APPLICATION_LOGS.debug("Verifying Condition for: "+elemName);
		System.out.println("Verifying Condition for: "+elemName);
		try {
			Assert.assertTrue(condition);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Verifying Condition for:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Verifying Condition for:  -"+ elemName + t.getMessage());
		}
	}

	//Method for Checking Condition
	public static Boolean assertCondition(String elemName,Boolean condition) {
		APPLICATION_LOGS.debug("Verifying Condition for: "+elemName);
		System.out.println("Verifying Condition for: "+elemName);
		try {
			Assert.assertTrue(condition);
			return true;
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Verifying Condition for:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Verifying Condition for:  -"+ elemName + t.getMessage());
			return false;
		}
	}

	//Close Pop Up Window
	
	//Swith to main Window
	

	//Close the Browser
	public static void closeDriver()throws InterruptedException {
		System.out.println("Closing the driver : ");
		APPLICATION_LOGS.debug("Executing closeDriver");
		try
		{
			driver.close();
			wbdv = null;
			if (CONFIG.getProperty("test_browser").equals("InternetExplorer")) {

				Runtime.getRuntime().exec("taskkill /F /IM iedriverserver.exe");
			}
		} catch(Throwable t)
		{
			System.out.println("Exception as:"+t) ;
		}
	}

	// Function for switching to pop up Window
	

	//Function for Selecting Value from Dropdown
	public static void selectValue(By Locator,String Value,String elemName) {
		APPLICATION_LOGS.debug("Selecting Value from : "+elemName);
		System.out.println("Selecting Value from : "+elemName);
		try {
			waitForElementToLoad(Locator);
			FunctionLibrary.highlightElement(driver, Locator);
			Select select = new Select(driver.findElement(Locator));
			select.selectByVisibleText(Value);
		} catch(Exception e) {
			System.out.println("Error while Selecting Value from :   -"+elemName);
			APPLICATION_LOGS.debug("Error while Selecting Value from :   -"+ elemName + e.getMessage());
		}
	}

	//Function for checking Element Present
	public static Boolean isElementPresent(By Locator,String elemName) {
		APPLICATION_LOGS.debug("Is Element Present : "+elemName);
		System.out.println("Is Element Present : "+elemName);
		try {
			//waitForElementToLoad(Locator);
			//FunctionLibrary.highlightElement(driver, Locator);
			Boolean present=driver.findElement(Locator).isDisplayed();
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			// WebElement present = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someid")));
			return present;
		} catch(Throwable e) {
			System.out.println("Error while veryfying Is Element Present  :   -"+elemName);
			APPLICATION_LOGS.debug("Error while veryfying Is Element Present  :   -"+ elemName + e.getMessage());
			return false;
		}
	}

	//Method for Retrieving Text
	public static String retrieveText(By locator,String elemName) {
		String expectedText = null;
		APPLICATION_LOGS.debug("Getting Text from: "+elemName);
		System.out.println("Getting Text from: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			expectedText= driver.findElement(locator).getText().trim();
			APPLICATION_LOGS.debug("Retrieved text : "+expectedText);
			System.out.println("Retrieved text : "+expectedText);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Getting Text from:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Getting Text from:  -"+ elemName + t.getMessage());

		}
		return expectedText;
	}

	//Method for Retrieving Value
	public static String retrieveAttributeValue(By locator,String value,String elemName) {
		String expectedValue = null;
		APPLICATION_LOGS.debug("Getting Attribute "+value+"  Value from: "+elemName);
		System.out.println("Getting Attribute Value "+value+"  from: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			expectedValue= driver.findElement(locator).getAttribute(value);
			System.out.println(expectedValue);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Getting Attribute: "+value+" Value from:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Getting Attribute "+value+"  Value from:  -"+ elemName + t.getMessage());
		}
		return expectedValue;
	}

	//Method for Creating List
	public static List<WebElement> createWebList(By locator,String elemName) {
		APPLICATION_LOGS.debug("Creating WebList reference for: "+elemName);
		System.out.println("Creating WebList reference for: "+elemName);
		List<WebElement> list=null;
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			list= driver.findElements(locator);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Creating WebList reference for: "+elemName);
			APPLICATION_LOGS.debug("Error while Creating WebList reference for:"+ elemName + t.getMessage());
		}
		return list;
	}

	//Method for Creating List
	public static List<WebElement> createWebList(By parentLoc,By childLoc,String elemName) {
		List<WebElement> list=null;
		APPLICATION_LOGS.debug("Creating WebList reference for: "+elemName);
		System.out.println("Creating WebList reference for: "+elemName);
		try {
			waitForElementToLoad(parentLoc);
			FunctionLibrary.highlightElement(driver, childLoc);
			list= driver.findElement(parentLoc).findElements(childLoc);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Creating WebList reference for: "+elemName);
			APPLICATION_LOGS.debug("Error while Creating WebList reference for:"+ elemName + t.getMessage());
		}
		return list;
	}

	//Method for clicking on all items
	public static void clickAllItems(List<WebElement> allItems,String elemName) {
		APPLICATION_LOGS.debug("Clicking all items of: "+elemName);
		System.out.println("Clicking all items of: "+elemName);
		try {
			for(int i=0; i<allItems.size(); i++) {
				allItems.get(i).click();
			}

		} catch(Throwable t) {
			// report error
			System.out.println("Error while Clicking all items of: "+elemName);
			APPLICATION_LOGS.debug("Error while Clicking all items of: "+ elemName + t.getMessage());
		}

	}

	//Function for switching back to default window
	
	// Wait for a new window to appear
	public static void waitForNewWindow(int prevWndCount)
	{
		
		final int currWndCount = prevWndCount;

		WebDriverWait wait = new WebDriverWait(driver, 30);

		try
		{Thread.sleep(4000L);

			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>()
					{

				public Boolean apply(WebDriver d)
				{

					return d.getWindowHandles().size() > currWndCount;

				}

					});

		}

		catch (Exception e)
		{

			APPLICATION_LOGS.debug("New window doesn't appears");

		}

	}

	// Wait for page to load
	public static String waitForPageToLoad() throws InterruptedException
	{

		// Wait for page to load
		//Thread.sleep(3000L);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try
		{	Thread.sleep(4000L);
			start = System.currentTimeMillis();
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>()
					{
				public Boolean apply(WebDriver d)
				{
					if (!(d instanceof JavascriptExecutor))
					{
						return true;
					}
					Object result = ((JavascriptExecutor) d)
							.executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

					if (result != null && result instanceof Boolean && (Boolean) result)
					{
						finish = System.currentTimeMillis();
						long totalTime = finish - start; 
						float totalTimee=totalTime/1000;
						//long.class.toString();
						//System.out.println("Total Time for page load - "+totalTime); 
						test=String.valueOf(totalTimee); 
						System.out.println("test: "+test);
						System.out.println("total time"+totalTime);
						return true;
					}

					return false;
				}
					});
			return test;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			//APPLICATION_LOGS.debug("waitForPageToLoad : Page loading timed out");
		}
		return test;

	}





	// Wait Untill the element is not visible
	public static void waitForElementToLoad(By locator) throws InterruptedException
	{
		// Wait for webdriver to find the element. Timeout after 30 seconds.
		try
		{	Thread.sleep(4000L);
			Wait<WebDriver> wait = new WebDriverWait(driver, 30);
			// Wait until the element is located on the page.
			@SuppressWarnings("unused")
			WebElement element = wait.until(visibilityOfElementLocated(locator));
		}

		catch (NoSuchElementException e)
		{
			APPLICATION_LOGS.debug("Exception came : " +e.getMessage());
			System.out.println("Exception came : " +e.getMessage());
		}
	}

	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator)
	{
		return new ExpectedCondition<WebElement>()
				{
			public WebElement apply(WebDriver driver) {
				FunctionLibrary.highlightElement(driver, locator);
				WebElement toReturn = driver.findElement(locator);
				if (toReturn.isDisplayed()) {
					return toReturn;
				}
				return null;
			}
				};
	}

	//Method of Verifying numbers shown
	public static boolean verifyNumber(String elemName,int actValue, int expValue) {
		APPLICATION_LOGS.debug("Verifying Number for: "+elemName);
		System.out.println("Verifying Number for: "+elemName);
		try {
			Assert.assertEquals(expValue, actValue);
			return true;
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Verifying Number for:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Verifying Number for:  -"+ elemName + t.getMessage());
			return false;
		}
	}


	//Accept Alert
	public static void acceptAlert() {
		APPLICATION_LOGS.debug("Executing acceptAlert");
		Alert javascriptFirstNameAlert = driver.switchTo().alert();
		javascriptFirstNameAlert.accept();
	}


	// Method for Clicking on Web Element and wait for page to load
	public static void clickAndWait(By locator,String elemName) {
		APPLICATION_LOGS.debug("Clicking on: "+elemName);
		System.out.println("Clicking on: "+elemName);
		try {
			waitForElementToLoad(locator);
			FunctionLibrary.highlightElement(driver, locator);
			driver.findElement(locator).click();
			waitForPageToLoad();
			driver.navigate().refresh();
		} catch(Throwable t) {
			// report error
			System.out.println("Error while clicking on link -"+elemName);
			APPLICATION_LOGS.debug("Error while clicking on link -"+ elemName + t.getMessage());
		}
	}


	//Method for Checking String comparison
	public static String assertText(String expectedString,String actualString)
	{

		APPLICATION_LOGS.debug("Asserting  Text  where : ExpectedText = " +expectedString +"  and ActualText = " +actualString);
		System.out.println("Asserting  Text  where : ExpectedText = " +expectedString +"  and ActualText = " +actualString);

		try
		{

			Assert.assertEquals(expectedString.trim(), actualString.trim());

			APPLICATION_LOGS.debug("Success : ExpectedText = " +expectedString +"  and ActualText = " +actualString +" and both are same");
			System.out.println("Success : ExpectedText = " +expectedString +"  and ActualText = " +actualString +" and both are same");

		}

		catch(Throwable t)
		{

			// report error
			System.out.println("Error while asserting text :- " +t.getMessage());
			APPLICATION_LOGS.debug("Error while asserting text :- " +t.getMessage());
			return "Fail : Error while asserting text :- " +t.getMessage();

		}

		return "Pass";

	}


	//Method for Checking page title
	public static String assertTitle(String expectedTitle)
	{
		String actualTitle = driver.getTitle();

		APPLICATION_LOGS.debug("Asserting  title  where : Expected title = " +expectedTitle +"  and Actual title = " +actualTitle);
		System.out.println("Asserting  title  where : Expected title = " +expectedTitle +"  and Actual title = " +actualTitle);

		try
		{

			Assert.assertEquals(expectedTitle.trim(), actualTitle.trim());

		}

		catch(Throwable t)
		{

			// report error
			System.out.println("Error while asserting title :- " +t.getMessage());
			APPLICATION_LOGS.debug("Error while asserting title :- " +t.getMessage());
			return "Fail : Error while asserting title :- " +t.getMessage();

		}

		return "Pass";

	}


	public static String verifyAlertAndAccept(String expectedAlertText)
	{

		APPLICATION_LOGS.debug("Executing : verifyAlertAndAccept() method");

		String actualAlertText = null;
		Alert alert = null;

		try
		{

			// Switch control to alert
			alert = driver.switchTo().alert();

			// Get the actual alert message
			actualAlertText = alert.getText();

			// Assert alert message
			Assert.assertEquals(expectedAlertText.trim(), actualAlertText.trim());
			Thread.sleep(3000L);

			// Accept alert message
			alert.accept();
			Thread.sleep(3000L);

			APPLICATION_LOGS.debug("Success : got the alert message saying - '" +actualAlertText +"'");
			System.out.println("Success : got the alert message saying - '" +actualAlertText +"'");

		}

		catch(Throwable t)
		{

			APPLICATION_LOGS.debug("Error : Unable to get the alert" +t.getMessage());
			return "Fail : Unable to get the alert";

		}

		return "Pass";

	}
	public static Boolean isEnabled(By Locator,String elemName) {
		APPLICATION_LOGS.debug("Is Element enabled : "+elemName);
		System.out.println("Is Element enabled : "+elemName);
		try {
			waitForElementToLoad(Locator);
			FunctionLibrary.highlightElement(driver, Locator);
			Boolean present=driver.findElement(Locator).isEnabled();
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			// WebElement present = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someid")));
			return present;
		} catch(Throwable e) {
			System.out.println("Error while veryfying Is Element Enabled :   -"+elemName);
			APPLICATION_LOGS.debug("Error while veryfying Is Element Enabled  :   -"+ elemName + e.getMessage());
			return false;
		}


	}


	//highlight the element on which action will be performed
	public static void highlightElement(WebDriver driver, By Locator) {

		try
		{

			/*for (int i = 0; i < 3; i++) 
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				//js.executeScript("arguments[0].setAttribute('style', arguments[1]);",driver.findElement(Locator), "color: red; border: 2px solid red;");
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);",driver.findElement(Locator), "background-color: yellow; outline: 1px solid rgb(136, 255, 136);");

			}*/

		}

		catch(Throwable t)
		{
			System.out.println("Error came : " +t.getMessage());
			APPLICATION_LOGS.debug("Error came : " +t.getMessage());
		}

	}


	// Check a check-box
	public static void checkCheckBox(By locator, String elemName) 
	{

		APPLICATION_LOGS.debug("Clicking on: "+elemName);
		System.out.println("Clicking on: "+elemName);

		try 
		{

			waitForElementToLoad(locator);

			if(!driver.findElement(locator).isSelected())
			{
				driver.findElement(locator).click();
			}

		} 

		catch(Throwable t) 
		{

			// report error
			System.out.println("Error while clicking on link -"+elemName);
			APPLICATION_LOGS.debug("Error while clicking on link -"+ elemName + t.getMessage());

		}

	}

	//Method for verifying presence of a sub-string inbetween a larger string
	public static Boolean verifyPartialText(String elemName,String expValue, String actValue) 
	{
		APPLICATION_LOGS.debug("Verifying Partial Text for: "+elemName);
		System.out.println("Verifying Partial Text for: "+elemName);
		try {
			Boolean check = actValue.trim().contains(expValue.trim());
			if(check)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Verifying Partial Text for:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Verifying Partial Text for:  -"+ elemName + t.getMessage());
			return false;
		}
	}

	//Converts integer to string and inputs to text box
	public static void inputInteger(By locator,String elemName,int Value) {
		APPLICATION_LOGS.debug("Sending Values in: "+elemName);
		System.out.println("Sending Values in: "+elemName);
		try {
			waitForElementToLoad(locator);
			String Value1 = Integer.toString(Value);
			driver.findElement(locator).sendKeys(Value1);
		} catch(Throwable t) {
			// report error
			System.out.println("Error while Sending Values in:  -"+elemName);
			APPLICATION_LOGS.debug("Error while Sending Values in:  -"+ elemName + t.getMessage());
		}
	}


	// Get date and time of a specific time zone
	public static String getDateAndTimeOfSpecificTimeZone(String dateAndTimeFormat, String timeZone)
	{

		APPLICATION_LOGS.debug("Executing : getDateAndTimeOfSpecificTimeZone() method");

		// Locale date and time
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat(dateAndTimeFormat);

		// Get US/Eastern time
		formatter.setTimeZone(TimeZone.getTimeZone(timeZone)); 
		String dateAndTime = formatter.format(date);

		APPLICATION_LOGS.debug("Got date and time specific to timezone. Timezone = " +timeZone +" and DateTime = " +dateAndTime);
		System.out.println("Got date and time specific to timezone. Timezone = " +timeZone +" and DateTime = " +dateAndTime);

		// Prints the date in the US timezone  
		return dateAndTime;

	}


	// Maximize a window
	public static void maximizeWindow()
	{

		APPLICATION_LOGS.debug("Executing : maximizeWindow() method");

		try
		{

			APPLICATION_LOGS.debug("Maximizing Browser window");
			System.out.println("Maximizing Browser window");

			// Maximize browser window
			driver.manage().window().maximize();

			APPLICATION_LOGS.debug("Browser window successfully maximized");
			System.out.println("Browser window successfully maximized");

		}

		catch(Throwable windowMaximizeException)
		{

			APPLICATION_LOGS.debug("Exception came while maximizing window : " +windowMaximizeException.getMessage());
			System.err.println("Exception came while maximizing window : " +windowMaximizeException.getMessage());

		}

	}


	// Refreshing a page
	public static void refreshPage()
	{

		APPLICATION_LOGS.debug("Executing : refreshPage() method");

		try
		{

			APPLICATION_LOGS.debug("Refreshing page");
			System.out.println("Refreshing page");

			// Maximize browser window
			driver.navigate().refresh();

			APPLICATION_LOGS.debug("Page successfully refreshed");
			System.out.println("Page successfully refreshed");

		}

		catch(Throwable pageRefreshException)
		{

			APPLICATION_LOGS.debug("Exception came while refreshing page : " +pageRefreshException.getMessage());
			System.err.println("Exception came while refreshing page : " +pageRefreshException.getMessage());

		}

	}

	// Get the current date and time in desired format
	public static String getCurrentDateTime(String dateFormat) {
		String currentDateTime = null;
		APPLICATION_LOGS.debug("\n\n Executing getCurrentDateTime");
		try {
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			currentDateTime = formatter.format(currentDate.getTime());
			System.out.println(currentDateTime);
		} catch (Exception e) {
			System.out.println("Error while getting current datetime");
			APPLICATION_LOGS.debug("\\Error while getting current datetime");
			e.getCause();
		}
		return currentDateTime;
	}

	//generate random name
	public static String generateStringOfLengthl(int length){

		char[] charSequence = new char[length];

		for (int i = 0; i < length; i++)
		{
			int randomInt = 97 + (int)(Math.random() * ((122 - 97) + 1));
			charSequence[i] = (char)randomInt;
		}
		String randomStr = new String(charSequence);
		System.out.println(randomStr);
		return randomStr;
	}

	// Get the Decimal value for the full app version (ie. 12.5.2)
	public static Double getAppDecimalVersion(String stringAppVersion){
		// Find the position of the 1st decimal
		int majorDecimalPos = stringAppVersion.indexOf(".");
		// Find the position of the 2nd decimal
		int minorDecimalPos = stringAppVersion.indexOf(".", majorDecimalPos);
		// Parse out the string from the start to the 2nd decimal then convert to Double
		return Double.parseDouble(stringAppVersion.substring(0, minorDecimalPos+majorDecimalPos));
	}

	public static long generateNumber()  
	{  
		String s1 = "33333";  
		double d = Math.random();  
		d=d*100000.0;  
		int i = (int) d;  
		String s2 = String.valueOf(i);  
		String s3=s1+s2;  
		long m = Long.parseLong(s3);  
		return m;
	}  

	public static String getRndNumber(int i) 
	{
		Random random=new Random();
		int randomNumber=0;
		boolean loop=true;
		while(loop) {
			randomNumber=random.nextInt();
			if(Integer.toString(randomNumber).length()==i && !Integer.toString(randomNumber).startsWith("-")) {
				loop=false;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(randomNumber);
		String strI = sb.toString();
		return strI;
	}

	/**
	 * 
	 * Waits for web element to disappear
	 * 
	 * @param locator Locator of the web element
	 * @param elemName Name of the web element
	 * 
	 * @return : void
	 */


	public static void waitForElementToDisappear(final By locator,
			String elemName) {

		APPLICATION_LOGS.debug("Waiting for " + elemName + " to disappear ...");
		System.out.println("Waiting for " + elemName + " to disappear ...");

		try {

			// Waits for 60 seconds
			Wait<WebDriver> wait = new WebDriverWait(driver, 60);

			// Wait until the element get disappeared
			@SuppressWarnings("unused")
			WebElement element = wait.until(visibilityOfElementLocated(locator));
			//WebElement element = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			//ExpectedConditions.invisibilityOfElementLocated(locator);
			// Log result
			APPLICATION_LOGS.debug("Waiting ends ... " + elemName
					+ " disappeared");

			System.out.println("Waiting ends ... " + elemName + " disappeared");

		}

		catch (Throwable waitForElementException) {

			// Log error
			APPLICATION_LOGS
			.debug("Error came while waiting for element to disappear : "
					+ waitForElementException.getMessage());

			System.out
			.println("Error came while waiting for element to disappear : "
					+ waitForElementException.getMessage());

		}

	}

	/**
	 * 
	 * Returns when web element disappears
	 * 
	 * @param locator Locator of the web element
	 * 
	 * @return ExpectedCondition of the web element
	 * 
	 */
	public static ExpectedCondition<WebElement> ElementLocatedToGetDisappear(
			final By locator) {

		return new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {

				// Store the web element
				WebElement toReturn = driver.findElement(locator);

				// Check whether the web element is disappeared
				if (!toReturn.isDisplayed())
					return toReturn;

				return null;

			}

		};

	}




}
