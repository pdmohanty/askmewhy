package testcases;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import testscripts.DriverScript;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;



import static org.testng.Assert.assertTrue;

//import io.appium.java_client.driver;

public class Mfunction extends DriverScript {

//    static WebDriverWait wait = new WebDriverWait(driver, 60);
//    static WebDriverWait Shortwait = new WebDriverWait(driver, 5);
	
	public static boolean enter() {
	//	((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.SEARCH));
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		return true;
	}
    public static void getScreenShotMobile(String methodName) throws IOException {
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir") + "//FailureScreenShots//" + methodName + ".png"));
    }

    public static boolean click(By locator, String elemName) throws Exception {

        try {
            System.out.println("Waiting till " + elemName + " is displayed");
            waitForElementToLoad(locator, elemName);
            System.out.println("Clicking on " + elemName);
            driver.findElement(locator).click();
            System.out.println("Clicked on " + elemName);

        } catch (Throwable e) {
            System.err.println("Error with clicking on " + elemName + " , The Message is " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean fastClick(By locator, String elemName) throws Exception {

        try {
            System.out.println("Waiting till " + elemName + " is displayed");
            verifyQuicklyElementDisplayed(locator, elemName);
            System.out.println("Clicking on " + elemName);
            driver.findElement(locator).click();
            System.out.println("Clicked on " + elemName);

        } catch (Throwable e) {
            System.err.println("Error with clicking on " + elemName + " , The Message is " + e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * public static String retrieveAttributeValue(By locator,String value,String elemName) method specification :-
     * <p>
     * 1) Return retrieved HTML attribute value from webpage
     * 2) driver.findElement(locator).getAttribute(value) -> Retrieves attribute (present under a web element) value
     *
     * @param : Locator for the web element, Attribute name, Name of the web element
     * @return : Attribute value retrieved
     */

    public static String retrieveAttributeValue(By locator, String value, String elemName) {

        String attributeValue = null;

        System.out.println("Getting value of Attribute '" + value + "' from : " + elemName);

        try {

            // Wait for web element to load
            waitForElementToLoad(locator, elemName);

            // Get attribute value for the web element
            attributeValue = driver.findElement(locator).getAttribute(value);

        } catch (Throwable retrieveAttributeValueException) {

            // report error
            System.err.println("Error while Getting value of Attribute '" + value + "' from '"
                    + elemName + "' : " + retrieveAttributeValueException.getMessage());

            return "Fail : Error while Getting value of Attribute '" + value + "' from '"
                    + elemName + "' : " + retrieveAttributeValueException.getMessage();
        }

        return attributeValue;

    }

    public static boolean scrollTillElement(String locatorClass, String eleName)
    {
        try{

            RemoteWebElement element = (RemoteWebElement)driver.findElement(By.name(locatorClass));
            String elementID = element.getId();
            HashMap<String, String> scrollObject = new HashMap<String, String>();
           // scrollObject.put("element", elementID);
            scrollObject.put("direction", "up");
            //scrollObject.put("name", "Scenes");
            System.out.println("Starting the scroll");
            Thread.sleep(2000);
            driver.executeScript("mobile: swipe", scrollObject);
            System.out.println("Scroll successful");

        }
        catch (Throwable e)
        {
            System.out.println("The Error is: "+e.getMessage());
            return false;
        }
        return true;
    }


    public static void swipeAfterElementVisible(By locator, String EleName) throws Exception {

        verifyElementDisplayed(locator, EleName);
        Thread.sleep(2000);
        System.out.println("Swiping up");
        singleSwipeUp();

    }
    public static void singleSwipeUp() throws Exception {
        //verifyElementDisplayed(By.xpath("(//XCUIElementTypeStaticText)[1]"), "First Text");
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "up");
        driver.executeScript("mobile: swipe", scrollObject);
        System.out.println("Scroll successful");

    }

    //Swiping from one element to another
    public static String slightTouchUp() {
        System.out.println("Swiping From one lement to another using Single Finger");

        try {
            TouchAction action = new TouchAction(driver);
            action.press(PointOption.point(480, 700))
                    .moveTo(PointOption.point(480, 580))
                    .release().perform();
            Thread.sleep(1000);
            // waitForElementToAppear(By.xpath("//*[@XCElementType='XCUIElementTypeCell' and @name='Geo-Services']"), 30);


        } catch (Throwable e) {
            System.out.println("Swiping from Element to Element failed using single finger failed " + e.getMessage());

            return "Fail: Swipe from Right to Left with Single Finger was unsuccessful" + e.getMessage();
        }

        return "Pass: Swipe from Element to Element was successful";
    }

    public static void singleSwipeDown() throws Exception {
        //verifyElementDisplayed(By.xpath("(//XCUIElementTypeStaticText)[1]"), "First Text");
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        driver.executeScript("mobile: swipe", scrollObject);
        System.out.println("Scroll successful");

    }

    public static void singleSwipeRight() throws Exception {
        //verifyElementDisplayed(By.xpath("(//XCUIElementTypeStaticText)[1]"), "First Text");
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "right");
        driver.executeScript("mobile: swipe", scrollObject);
        System.out.println("Scroll successful");

    }

    public static void singleSwipeLeft() throws Exception {
        //verifyElementDisplayed(By.xpath("(//XCUIElementTypeStaticText)[1]"), "First Text");
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "left");
        driver.executeScript("mobile: swipe", scrollObject);
        System.out.println("Scroll successful");

    }

    public static boolean ShortWaitAndClick(By locator, String elemName) throws Exception {

        try {
            System.out.println("Waiting till " + elemName + " is displayed");
            ShortWaitForElementToLoad(locator, elemName);
            System.out.println("Clicking on " + elemName);
            driver.findElement(locator).click();
            System.out.println("Clicked on " + elemName);

        } catch (Throwable e) {
            System.err.println("Error with clicking on " + elemName + " , The Message is " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean input(By locator, String elemName, String Value) throws Exception {

        try {
            waitForElementToLoad(locator, elemName);
            System.out.println("Entering " + Value + " in " + elemName);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(Value);
            System.out.println("Entered " + Value + " in " + elemName);
        } catch (Throwable e) {
            System.err.println("The Error Message is: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean scrollAndInput(By locator, String elemName, String Value) throws Exception {

        try {

            scrollDownToBottom();
            verifyElementDisplayed(locator, elemName);
            System.out.println("Entering " + Value + " in " + elemName);
            click(locator, elemName);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(Value);
            System.out.println("Entered " + Value + " in " + elemName);
        } catch (Throwable e) {
            System.err.println("The Error Message is: " + e.getMessage());
            return false;
        }
        return true;
    }

//    public static boolean clearAndInputInChrome(By locator, String elemName, String Value) {
//
//        System.out.println("Sending Values in : " + elemName);
//
//        try {
//
//            // Clear the input field before sending values
//            //FunctionLibrary.clearField(locator, elemName);
//            chrome.findElement(locator).click();
//
//            // Send values to the input box
//            chrome.findElement(locator).sendKeys(Value);
//
//            // Log result
//            System.out.println("input '" + Value + "' text into : '" + elemName + "'");
//
//            return true;
//
//        } catch (Throwable inputException) {
//
//            // Log error
//            System.err.println("Error while inputting into - '" + elemName + "' : " + inputException.getMessage());
//
//            return false;
//        }
//
//    }

//    public static void ScrollChromeBy1000() {
//        System.out.println("Scrolling Up");
//
//        try {
//            Thread.sleep(1500);
//            JavascriptExecutor js = (JavascriptExecutor) chrome;
//
//
//            js.executeScript("window.scrollBy(0,1000)");
//           // js.executeScript("window.scrollBy(0,-250)", "");
//        } catch (Throwable ScrollDownExecption) {
//            System.out.println("Scroll Down failed : " + ScrollDownExecption.getMessage());
//
//        }
//    }

//    public static String waitForElementToAppearChrome(final By locator, long timeout) {
//
//        System.out.println("Waiting for web element to Appear on the page");
//
//        try {
//
//            // Waits for 'timeout' seconds
//            Wait<WebDriver> wait3 = new WebDriverWait(chrome, timeout);
//
//            // Wait until the element is located on the page
//            @SuppressWarnings("unused")
//            WebElement element = wait3.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//            System.out.println("Waiting ends ... Web element Appeared in the page within " + timeout + " seconds.");
//
//        } catch (Throwable waitForElementException) {
//
//            // Log error
//            System.err.println("Error came while waiting for element to appear : " + waitForElementException.getMessage());
//            return failTest + ": Error came while waiting for element to appear : " + waitForElementException.getMessage();
//
//        }
//
//        return passTest + ": Waiting ends ... Web element Appeared in the page within " + timeout + " seconds.";
//    }

//    public static boolean clickLinkInChrome(By locator, String elemName) {
//
//        System.out.println("Clicking on : " + elemName);
//
//        try {
//
//            //waitForElementToLoad(locator);
//            waitForElementToAppearChrome(locator, 30);
//            //chrome.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//
//            // Click on the link
//
//            chrome.findElement(locator).click();
//
//            // Log result
//            System.out.println("Clicked on : '" + elemName + "'");
//
//            return true;
//
//        } catch (Throwable clickLinkException) {
//
//            // Log error
//            System.err.println("Error while clicking on - '" + elemName + "' : " + clickLinkException.getMessage());
//
//            return false;
//
//        }
//
//    }


    public static boolean iOSScrollToElement(String ElementName)
    {

        System.out.println("Scrooling till partners");
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            RemoteWebElement element = (RemoteWebElement) driver.findElement(By.name(ElementName));
            String ElementID = element.getId();
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("element", ElementID);
            scrollObject.put("toVisible", "Test Element");
           //driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
            System.out.println("New Method passed");
            driver.executeScript("mobile: scroll", scrollObject);
            System.out.println("Scroll successful Now waiting");
            Thread.sleep(5000);
        }
        catch(Throwable e)
        {
            System.out.println("The error is"+e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean scrollDownToBottom()
    {
        System.out.println("Scrolling down to the bottom");
        try {
            driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
        }
        catch(Throwable e)
        {
            System.out.println("Failed to scroll because: "+e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean TapAtPointUsingCoordinates(int x, int y, String ElementName) {
        System.out.println("Tapping on the given coordinates for the element: " + ElementName);

        try {


            TouchAction a2 = new TouchAction(driver);
            a2.tap(PointOption.point(x,y)).perform();
            Thread.sleep(3000);

            System.out.println("Tapped on the given coordinates for the element: " + ElementName);

        } catch (Throwable e) {
            System.out.println("Failed to tap on the given coordinates for element " + ElementName + " Error Message: " + e.getMessage());
                return false;
        }

        return true;

    }

    public static boolean scrollUpToTop()
    {
        System.out.println("Scrolling up to the top");
        try {
            driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "up"));
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Failed to scroll because: "+e.getMessage());
            return false;
        }
        return true;
    }

	/*
	 * public static boolean swipeAndClickInMenu(By locator, String EleName) {
	 * System.out.println("Scrolling down to " + EleName + " and clicking"); try {
	 * click(Menu, "Menu Button"); // singleSwipeUp(); slightTouchUp();
	 * click(locator, EleName); System.out.println("Successfully clicked on " +
	 * EleName);
	 * 
	 * 
	 * } catch (Throwable e) { System.out.println("Could not click because of " +
	 * e.getMessage()); return false; } return true; }
	 */

	/*
	 * public static boolean scrollAndClickInMenu(By locator, String EleName) {
	 * System.out.println("Scrolling down to " + EleName + " and clicking"); try {
	 * click(Menu, "Menu Button"); scrollDownToBottom(); click(locator, EleName);
	 * System.out.println("Successfully clicked on " + EleName);
	 * 
	 * 
	 * } catch (Throwable e) { System.out.println("Could not click because of " +
	 * e.getMessage()); return false; } return true; }
	 */

	/*
	 * public static boolean clickInMenu(By locator, String EleName) {
	 * System.out.println("Scrolling down to " + EleName + " and clicking"); try {
	 * click(Menu, "Menu Button"); click(locator, EleName);
	 * System.out.println("Successfully clicked on " + EleName);
	 * 
	 * 
	 * } catch (Throwable e) { System.out.println("Could not click because of " +
	 * e.getMessage()); return false; } return true; }
	 */

	/*
	 * public static boolean scrollAndLogout(By locator, String EleName) {
	 * System.out.println("Scrolling down to " + EleName + " and clicking"); try {
	 * click(Menu, "Menu Button"); scrollDownToBottom(); scrollDownToBottom();
	 * click(locator, EleName); System.out.println("Successfully clicked on " +
	 * EleName);
	 * 
	 * 
	 * } catch (Throwable e) { System.out.println("Could not click because of " +
	 * e.getMessage()); return false; } return true; }
	 */

    public static boolean scrollDownAndClick(By locator, String EleName) {
        System.out.println("Scrolling down to " + EleName + " and clicking");
        try {
            scrollDownToBottom();
            click(locator, EleName);
            System.out.println("Successfully clicked on " + EleName);


        } catch (Throwable e) {
            System.out.println("Could not click because of " + e.getMessage());
            return false;
        }
        return true;
    }



    public static boolean clearAndInput(By locator, String elemName, String Value) {
        System.out.println("Sending Values in : " + elemName);
        try {
            driver.findElement(locator).sendKeys(Value);
            System.out.println("input '" + Value + "' text into : '" + elemName + "'");
            return true;
        } catch (Throwable inputException) {
            System.err.println("Error while inputting into - '" + elemName + "' : " + inputException.getMessage());
            return false;
        }
    }

    public static String verifyTextPresent(String expText) {

        System.out.println("Verifying Text : '" + expText + "' " + "present in the Page Source");

        try {

            System.out.println("Verifying " + expText + " is present or not");
            // Verify page source contains expected text
            assertTrue(driver.getPageSource().contains(expText));

            // Log result
            System.out.println("'" + expText + "' present in the Page Source");

            return "Pass : '" + expText + "' present in the Page Source";

        } catch (Throwable verifyTextPresentError) {

            // report error
            System.err.println("Error while Verifying Text from Page Source : " + verifyTextPresentError.getMessage());

            return "Fail : Error while Verifying Text from Page Source : " + verifyTextPresentError.getMessage();
        }

    }

    public static Boolean verifyElementDisplayed(By locator, String elemName) throws Exception {
        System.out.println("Verifying whether Element : '" + elemName + "' is present");

        try {
            waitForElementToLoad(locator, elemName);
            driver.findElement(locator).isDisplayed();
            System.out.println(elemName + " is present");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static String retrieveText(By locator, String elemName) {

        String retrievedText = null;

        System.out.println("Retrieving Text from : " + elemName);

        try {

            // Wait for web element to load on the page
            waitForElementToLoad(locator, elemName);

            // Retrieve text from web element
            retrievedText = driver.findElement(locator).getText().trim();

            // Log result
            System.out.println("Retrieved text : " + retrievedText);

        } catch (Throwable retrieveTextException) {

            // Log error
            System.err.println("Error while Getting Text from '" + elemName + "' : " + retrieveTextException.getMessage());

            return "Fail : Error while Getting Text from '" + elemName + "' : " + retrieveTextException.getMessage();

        }

        return retrievedText;

    }


    public static Boolean verifyQuicklyElementDisplayed(By locator, String elemName) throws Exception {
        System.out.println("Verifying whether Element : '" + elemName + "' is present");

        try {
            ShortWaitForElementToLoad(locator, elemName);
            driver.findElement(locator).isDisplayed();
            System.out.println(elemName + " is present");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static Boolean verifyElementDisappeared(By locator, String elemName) throws Exception {
        System.out.println("Verifying whether Element : '" + elemName + "' is present");

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println(elemName + " is present");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static boolean waitForElementToLoad(By locator, String elemName)
    {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println(elemName + " loaded");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static boolean waitForElementToToClickable(By locator, String elemName)
    {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println(elemName + " loaded");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static boolean ShortWaitForElementToLoad(By locator, String elemName)
    {

        try {
            Shortwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println(elemName + " loaded");

        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }

    public static Boolean verifyElementAppearedAndDisappeared(By locator, String elemName) throws Exception {

        try {
            System.out.println("Verifying whether Element : '" + elemName + "' Appeared");
            waitForElementToLoad(locator, elemName);
            System.out.println(elemName + " Appeared, waiting for disappearance");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println(elemName + " Disappeared");
        } catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - '" + elemName + "' element Present : " + isElementPresentException.getMessage());
            return false;
        }
        return true;
    }


    public void waitForAWhile(By locator) {
        System.out.println("Waiting for environment to be ready");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        System.out.println("Waiting Ends, element appeared");
    }


}
