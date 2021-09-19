package com.mem.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

	     final String myGmail = "apetfa102team07@gmail.com";
	     final String myGmail_password = "as5566as";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   message.setSubject(subject);
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("嚙褒送嚙踝蕭嚙�!");
     }catch (MessagingException e){
	     System.out.println("嚙褒送嚙踝蕭嚙踝蕭!");
	     e.printStackTrace();
     }
   }
	public String genAuthCode() {

//		System.out.print("\n" + "print GenAuthCode :");
		String authCode = "";

		Set<Character> set1 = new HashSet<>();
		while (set1.size() != 6) {
			int r = (int) (Math.random() * 123) + 47;
			if (r < 48) {

			} else if (r < 58) {
				set1.add((char)r);
			}
			if (r < 65) {

			} else if (r < 91) {
				set1.add((char)r);
			} else if (r < 97) {

			} else if (r < 123) {
				set1.add((char)r);
			}
		}
		Iterator<Character> it = set1.iterator();
		while (it.hasNext()) {
			authCode += it.next();
		}
		return authCode;
	}
	
//	 public static void main (String args[]){
//
//      String to = "ixlogic.wu@gmail.com";
//      
//      String subject = "嚙皺嚙碼嚙緬嚙踝蕭";
//      
//      String ch_name = "peter1";
//      String passRandom = "111";
//      String messageText = "Hello! " + ch_name + " 嚙踝蕭嚙諂記嚙踝蕭嚙皺嚙碼: " + passRandom + "\n" +" (嚙緩嚙篇嚙課伐蕭)"; 
//       
//      MailService mailService = new MailService();
//      mailService.sendMail(to, subject, messageText);
//
//   }


}
