package testscripts;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import testcases.Testcases;
import jxl.read.biff.BiffException;

public class Keywords extends DriverScript {
	/* Start of the Login Testcases */ 
	//Launch of the App without any crash
	public static String launching() throws Exception{
		try{
			System.out.println("Launch call started");
			result=BaseLibrary.launchApp();

			System.out.println("Launch call passed");

		} catch (Throwable e) {
			System.out.println(e.getMessage());
			return result;
		}
		return result;
	}

	public static String verifyCountrydropdown() throws Exception{
		try{
			result=Testcases.verifyCountrydropdown();
			

		} catch (Throwable e) {
			System.out.println(e.getMessage());
			return result;
		}
		return result;
	}
	
	public static String verifyShoedata() throws Exception{
		try{
			result=Testcases.verifyShoedata();
			

		} catch (Throwable e) {
			System.out.println(e.getMessage());
			return result;
		}
		return result;
	}

	

}