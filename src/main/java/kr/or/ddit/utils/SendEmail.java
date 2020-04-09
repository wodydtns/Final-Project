package kr.or.ddit.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	public int result = (int)((Math.random()*10000)+1);

	public void send(String mail) {
		
		// 보내는 사람
		String host     = "smtp.google.com"; // stmp host 
		final String user   = ""; // 보내는 사람 id
		final String password  = ""; // 보내는 사람 id의 비밀번호

		String to     = mail;// 받을 사람 메일 주소

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");



		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// 메세지 전송 부분
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// 1:N 메일 보내기
//			InternetAddress[] addArray = new InternetAddress[5]; 
//			addArray[0] = new InternetAddress("ktko0@ktko0.com"); 
//			addArray[1] = new InternetAddress("ktko1@ktko1.com");
//			addArray[2] = new InternetAddress("ktko2@ktko2.com"); 
//			addArray[3] = new InternetAddress("ktko3@ktko3.com"); 
//			addArray[4] = new InternetAddress("ktko4@ktko4.com"); 
//			message.addRecipients(Message.RecipientType.TO, addArray);

			// 메일 제목
			message.setSubject("취준진담 인증번호 발급");

			// 메일 내용
			
			message.setText("인증 번호는" + result + "입니다.");
			
			// 메일 전송
			Transport.send(message);
			System.out.println("message sent successfully...");
			System.err.println(result);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
				
	}
	
	public int num() {
		return result;
	}
}