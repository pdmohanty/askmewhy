package testcases;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import config.Locators;
import testscripts.DriverScript;
import testscripts.FunctionLibrary;
//import testscripts.ImplementationLibrary;
import testscripts.BaseLibrary;

public class Testcases extends DriverScript {

	//verifyCountrydropdown
	public static String verifyCountrydropdown() throws Exception {
		if (Mfunction.verifyElementDisplayed(By.xpath("//*[@text='Afghanistan']"), "Country Dropdown")==true)
		{
			result="Pass";
		}else result="Fail";
		return result;

	}
	//verifyShoedata
	public static String verifyShoedata() throws Exception {
		
		Mfunction.input(By.xpath("//*[@id='nameField']"), "username", "Test User");
		Mfunction.click(By.xpath("//*[@text='Male']"), "Male Checkbox");
		Mfunction.click(By.xpath("//*[@id='btnLetsShop']"), "Lets Shop Button");
		Mfunction.waitForElementToLoad(By.xpath("(//*[@id='rvProductList']/*/*/*[@id='productImage' and (./preceding-sibling::* | ./following-sibling::*)[@text]])[1]"), "Shoe Data");
		if (Mfunction.verifyElementDisplayed(By.xpath("(//*[@id='rvProductList']/*/*/*[@id='productImage' and (./preceding-sibling::* | ./following-sibling::*)[@text]])[1]"), "Shoe Data")==true)
			
		{
			result="Fail";
		}else result="Fail";
		return result;

	}

}

