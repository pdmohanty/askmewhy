package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import testscripts.DriverScript;

public class write extends DriverScript {
	public static PrintWriter writer;

	//Method to write to automation data file
	public static void write(String option) {

		try{
			File file = new File(System.getProperty("user.dir")+"/Report/automationproductiondata.txt");
			PrintWriter out = new PrintWriter(new FileWriter(file, true));
			BufferedWriter test=new BufferedWriter(out);
			test.write(option);
			test.newLine();
			test.close();

		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
			DATA_LOGS.debug("Print Writer has Failed to Print");

		}
	}
	
	//Method to Delete the automation data file
	public static void delete()
	{	
		try{

			File file = new File(System.getProperty("user.dir")+"/Report/automationproductiondata.txt");

			if(file.delete()){
				System.out.println(file.getName() + " is deleted!. New Data would be populated");
			}else{
				System.out.println("File was not there earlier");
			}

		}catch(Exception e){

			e.printStackTrace();

		}

	}

}
