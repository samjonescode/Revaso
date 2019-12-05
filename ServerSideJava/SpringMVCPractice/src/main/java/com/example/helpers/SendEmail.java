package com.example.helpers;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;


public class SendEmail {	
	
		
	public static String sendMail(String email) {
				
		Random ran = new Random(); //create a random number to send as a temporary password to login.
		int acc = ran.nextInt(1000000);
		String accNumber = Integer.toString(acc);
		
		final String fromEmail = "revaturesocial@gmail.com"; // from email.created this Gmail for this project purpose.
		
		final String password = "socialappproject2"; //From email password
		String toEmail = "samuelriley1393@gmail.com"; //to eamil.we need to get this password from user table when he request new password.
		String subject = "Your Temporary password";
		String message = "<p>Here is your temporary password to login. Plaese change a new password once you login by using this password.</p>"+accNumber;
		Properties prop = System.getProperties();				
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		//write a method here to update this random number password in user table's password field. When user login he can change a new password.
		
		
		Session mailSession = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		mailSession.setDebug(true);
		
		System.out.println("Revaturesocial@gmail.com authenticated");
		Message mailMessage = new MimeMessage(mailSession);
		
		
		try {
			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setSubject(subject);
			mailMessage.setContent(message,"text/html");

			
			Transport.send(mailMessage);
			

			return accNumber;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}				
		
		return "false";
	}
}