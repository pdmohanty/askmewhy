package util;



//set CLASSPATH=%CLASSPATH%;activation.jar;mail.jar

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;



import testscripts.DriverScript;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SendMail extends DriverScript

{
	public static void zipAndSendReport()

	{
		//TestUtil.zip("C:\\rep");
		//Input below proper email ids
		String[] to={"priyadarshanm@mindfiresolutions.com","cpat13@gmail.com","Swayamshreen@mindfiresolutions.com","cpat@conveymd.com"};
	//	'cpat@conveymd.com'; Chris Patteson <cpat13@gmail.com>		Swayamshreen@mindfiresolutions.com
		String[] cc={};
		String[] bcc={};
		TestUtil.zip(System.getProperty("user.dir") +"/Report");
		//Add below proper credentials
		SendMail.sendMail("conveyauto@gmail.com",
				"Weekdays7@",
				"smtp.gmail.com",
				"587",
				"true",
				"true",
				true,
				"javax.net.ssl.SSLSocketFactory",
				"true",
				to,
				cc,
				bcc,
				"QA Automation test Reports",
				"Hi Everyone, \n\nPlease find the reports attached.\n\nRegards\nAutomation Team ConveyMD",
				System.getProperty("user.dir")+"\\Reports.zip",
				"Reports.zip");
	}

	public  static boolean sendMail(String userName, String passWord,
			String host,
			String port,
			String starttls,
			String auth,
			boolean debug,
			String socketFactoryClass,
			String fallback,
			String[] to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			String attachmentPath,
			String attachmentName){


		Properties props = new Properties();

		//Properties props=System.getProperties();

		//props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		if(!"".equals(port))

			props.put("mail.smtp.port", port);

		
		  if(!"".equals(starttls))
		  
		  props.put("mail.smtp.starttls.enable",starttls);
		  
		  props.put("mail.smtp.auth", auth);
		 
		// props.put("mail.smtps.auth", "true");


		if(debug){

			props.put("mail.smtp.debug", "true");

		}else{

			props.put("mail.smtp.debug", "false");         

		}

		if(!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if(!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class",socketFactoryClass);

		if(!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);



		try

		{

			//Session session = Session.getDefaultInstance(props, null);

			Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication()

				{ 
					return new PasswordAuthentication("conveyauto@gmail.com","Weekdays7@");    
				}
			});

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setText(text);

			msg.setSubject(subject);
			//attachment start
			// create the message part 

			//Multipart multipart = new MimeMultipart();
			// MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText(text);
			multipart.addBodyPart(textPart);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = 
					new FileDataSource(attachmentPath);
			messageBodyPart.setDataHandler(
					new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart);

			// attachment ends

			// Put parts in message
			msg.setContent(multipart);
			msg.setFrom(new InternetAddress("conveyauto@gmail.com"));
			System.out.println(to.length);
		
			for(int i=0;i<to.length;i++){

				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
				//msg.addRecipients(Message.RecipientType.TO, to[i]);
				//msg.addRecipients(Message.RecipientType.TO, new InternetAddress(to[i]));

			}

			for(int i=0;i<cc.length;i++){

				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

			}

			for(int i=0;i<bcc.length;i++){

				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));

			}

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		}

		catch (Exception mex)

		{

			mex.printStackTrace();

			return false;

		}

	}



}